package com.kongtai.thaumicsurvival.items.food;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.lib.network.PacketHandler;
import thaumcraft.common.lib.network.playerdata.PacketAspectPool;
import thaumcraft.common.lib.research.ResearchManager;

public class ItemThaumicApple extends ItemFood {

    // ==========================================
    // ★ 常量配置区 (想改数值，只改这里就行了)
    // ==========================================

    // ---- 扭曲值产量配置表 ----
    // 对应 Tier 0 到 Tier 7 的苹果。
    // 意思是：0级给1点扭曲，1级给2点，以此类推，7级直接给100点。
    // 如果觉得太高，直接把后面的 100 改成 50，重启游戏立马生效！
    public static final int[] WARP_AMOUNTS = { 100, 200, 400, 800, 1600, 3200, 6400, 10000 };

    // ---- 魔铸金苹果 (附魔金苹果) 的 Buff 配置 ----
    public static final int ENCHANTED_REGEN_TIME = 400; // 生命恢复时长：400 tick = 20秒
    public static final int ENCHANTED_REGEN_LEVEL = 3; // 生命恢复等级：1 (即 II 级)
    public static final int ENCHANTED_RESIST_TIME = 6000; // 抗性提升时长：6000 tick = 5分钟
    public static final int ENCHANTED_ABSORB_TIME = 2400; // 伤害吸收时长：2400 tick = 2分钟
    public static final int ENCHANTED_ABSORB_LEVEL = 3; // 伤害吸收等级：3 (即 IV 级，给黄心)

    // ---- 秘源金苹果 (普通金苹果) 的 Buff 配置 ----
    public static final int NORMAL_REGEN_TIME = 100; // 生命恢复时长：100 tick = 5秒
    public static final int NORMAL_REGEN_LEVEL = 1; // 生命恢复等级：0 (即 I 级)
    public static final int NORMAL_ABSORB_TIME = 2400; // 伤害吸收时长：2400 tick = 2分钟
    public static final int NORMAL_ABSORB_LEVEL = 1; // 伤害吸收等级：0 (即 I 级)

    private boolean isEnchanted;
    private IIcon[] icons;

    public ItemThaumicApple(boolean isEnchanted) {
        // 饱食度 4，饱和度 1.2F（原版金苹果数值），给狗吃 false
        super(4, 1.2F, false);
        this.isEnchanted = isEnchanted;
        this.setAlwaysEdible(); // 满饥饿度也能吃
        this.setMaxStackSize(64);
        this.setHasSubtypes(isEnchanted); // 只有魔铸金苹果有子类型(Meta)

        if (isEnchanted) {
            this.setUnlocalizedName("thaumicsurvival.greed_apple");
        } else {
            this.setUnlocalizedName("thaumicsurvival.arcane_apple");
        }
    }

    // ==========================================
    // 1. 贴图注册 (客户端专属，防服务器崩溃)
    // ==========================================
    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister register) {
        if (this.isEnchanted) {
            // 图里有8张（t0到t7），数组就改成8
            this.icons = new IIcon[8];
            for (int i = 0; i < 8; i++) {
                this.icons[i] = register.registerIcon("thaumicsurvival:apple/ts_greed_apple/ts_greed_apple_t" + i);
            }
            this.itemIcon = this.icons[0]; // 默认拿0级
        } else {
            this.itemIcon = register.registerIcon("thaumicsurvival:apple/ts_arcane_apple");
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIconFromDamage(int meta) {
        if (this.isEnchanted && this.icons != null) {
            if (meta < 0) meta = 0;
            if (meta > 7) meta = 7;
            return this.icons[meta];
        }
        return this.itemIcon;
    }

    // ==========================================
    // 2. 附魔特效 (紫光)
    // ==========================================
    // 重写此方法，让魔铸金苹果自带附魔光效，且鼠标放上去不会显示任何附魔文字
    @Override
    @SideOnly(Side.CLIENT)
    public boolean hasEffect(ItemStack stack, int pass) {
        // 如果是魔铸金苹果（有等级的），直接让它发光。秘源金苹果不发光。
        return this.isEnchanted;
    }

    // ==========================================
    // 3. 动态 Lore 显示 (包含常亮扭曲值)
    // ==========================================
    @Override
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean advanced) {
        super.addInformation(stack, player, list, advanced);

        if (this.isEnchanted) {
            int tier = stack.getItemDamage();
            int points = 64 * (int) Math.pow(2, tier);
            // 这里读取常量的配置，拿到当前等级应该显示的扭曲值
            int warp = WARP_AMOUNTS[tier];

            list.add(
                EnumChatFormatting.GRAY
                    + StatCollector.translateToLocalFormatted("item.thaumicsurvival.greed_apple.lore.points", points));
            list.add(
                EnumChatFormatting.RED
                    + StatCollector.translateToLocalFormatted("item.thaumicsurvival.greed_apple.lore.warp", warp));
            list.add(
                EnumChatFormatting.DARK_RED + ""
                    + EnumChatFormatting.ITALIC
                    + StatCollector.translateToLocal("item.thaumicsurvival.greed_apple.lore.danger"));
        } else {
            list.add(
                EnumChatFormatting.GRAY
                    + StatCollector.translateToLocal("item.thaumicsurvival.arcane_apple.lore.points"));
        }
    }

    // ==========================================
    // 4. 吃下去的效果 (Buff + 研究点 + 扭曲)
    // ==========================================
    @Override
    protected void onFoodEaten(ItemStack stack, World world, EntityPlayer player) {
        super.onFoodEaten(stack, world, player);

        if (world.isRemote) return;

        int amount = 1;
        int warpAmount = 0;
        int tier = stack.getItemDamage();

        if (this.isEnchanted) {
            // ★ 继承原版附魔金苹果的 Buff（使用常量配置）
            player
                .addPotionEffect(new PotionEffect(Potion.regeneration.id, ENCHANTED_REGEN_TIME, ENCHANTED_REGEN_LEVEL));
            player.addPotionEffect(new PotionEffect(Potion.resistance.id, ENCHANTED_RESIST_TIME, 0));
            player.addPotionEffect(new PotionEffect(Potion.fireResistance.id, ENCHANTED_RESIST_TIME, 0));
            // 伤害吸收 (1.7.10 中 field_76444_x 就是伤害吸收)
            player.addPotionEffect(
                new PotionEffect(Potion.field_76444_x.id, ENCHANTED_ABSORB_TIME, ENCHANTED_ABSORB_LEVEL));

            amount = 64 * (int) Math.pow(2, tier);

            // ★ 核心：从常量数组里读取该等级的扭曲值，并用神秘时代API增加永久扭曲
            warpAmount = WARP_AMOUNTS[tier];
            thaumcraft.api.ThaumcraftApiHelper.addWarpToPlayer(player, warpAmount, false);

        } else {
            // ★ 继承原版普通金苹果的 Buff（使用常量配置）
            player.addPotionEffect(new PotionEffect(Potion.regeneration.id, NORMAL_REGEN_TIME, NORMAL_REGEN_LEVEL));
            player.addPotionEffect(new PotionEffect(Potion.field_76444_x.id, NORMAL_ABSORB_TIME, NORMAL_ABSORB_LEVEL));
        }

        // 给予 6 种要素研究点
        for (Aspect primal : Aspect.getPrimalAspects()) {
            Thaumcraft.proxy.playerKnowledge.addAspectPool(player.getCommandSenderName(), primal, (short) amount);
        }
        ResearchManager.scheduleSave(player);

        for (Aspect primal : Aspect.getPrimalAspects()) {
            PacketHandler.INSTANCE.sendTo(
                new PacketAspectPool(
                    primal.getTag(),
                    (short) amount,
                    Thaumcraft.proxy.playerKnowledge.getAspectPoolFor(player.getCommandSenderName(), primal)),
                (EntityPlayerMP) player);
        }

        // 计算 6 种要素加起来的【总点数】
        int totalAmount = amount * 6;

        // 聊天栏提示
        if (this.isEnchanted) {
            player.addChatComponentMessage(
                new net.minecraft.util.ChatComponentText(
                    StatCollector
                        .translateToLocalFormatted("item.thaumicsurvival.greed_apple.eaten", totalAmount, warpAmount)));
        } else {
            player.addChatComponentMessage(
                new net.minecraft.util.ChatComponentText(
                    StatCollector.translateToLocalFormatted("item.thaumicsurvival.arcane_apple.eaten", totalAmount)));
        }
    }

    // ==========================================
    // 5. 创造模式物品栏显示多等级 (Meta 0-7)
    // ==========================================
    @Override
    @SideOnly(Side.CLIENT)
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public void getSubItems(Item item, net.minecraft.creativetab.CreativeTabs tab, List list) {
        if (this.isEnchanted) {
            for (int i = 0; i < 8; i++) {
                list.add(new ItemStack(item, 1, i));
            }
        } else {
            list.add(new ItemStack(item, 1, 0));
        }
    }
}
