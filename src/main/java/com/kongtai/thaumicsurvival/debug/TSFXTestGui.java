package com.kongtai.thaumicsurvival.debug;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

import org.lwjgl.input.Keyboard;

import com.kongtai.thaumicsurvival.ThaumicSurvival;
import com.kongtai.thaumicsurvival.api.thaumcraft.tcapiusetools.TCParticles;
import com.kongtai.thaumicsurvival.api.thaumcraft.tcapiusetools.TCSounds;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import thaumcraft.api.aspects.Aspect;

/**
 * ============================================================
 * 【粒子 / 音效测试 GUI】
 * ============================================================
 * 9x6 = 54 槽箱子大小。
 *
 * 玻璃板约定：
 * 绿色玻璃板 = 音效条目（点击 → 在玩家位置播声音）
 * 红色玻璃板 = 粒子条目（点击 → 在玩家位置出粒子）
 * 黄色玻璃板 = 上一页 / 下一页
 * 灰色玻璃板 = 翻页到头，不可点
 *
 * 点击任意条目：
 * 1. 立即关闭 GUI
 * 2. 在玩家位置播放对应音效 / 粒子
 * 3. LOG 打一行硬编码日志
 *
 * 翻页：
 * 槽位 45 = 上一页
 * 槽位 53 = 下一页
 *
 * 打开方式：/tsfx
 * ============================================================
 */
@SideOnly(Side.CLIENT)
public class TSFXTestGui extends GuiScreen {

    /** 指令执行后置 true，下一 tick 由 ClientProxy 打开 GUI */
    public static boolean pendingOpen = false;

    // ---- 布局常量 ----
    private static final int COLS = 9;
    private static final int ROWS = 6;
    private static final int SLOT = 18;
    private static final int ITEMS_PER_PAGE = 45; // 前 5 行放条目
    private static final int GUI_W = 176;
    private static final int GUI_H = 140;

    private static final int SLOT_PREV = 45; // 第 6 行第 0 列
    private static final int SLOT_NEXT = 53; // 第 6 行第 8 列

    // ---- 状态 ----
    private int page = 0;
    private static List<Entry> ENTRIES = null;

    // ============================================================
    // 内部类
    // ============================================================
    private interface PlayAction {

        void play(World w, double x, double y, double z);
    }

    private static class Entry {

        final String label;
        final ItemStack icon;
        final PlayAction action;
        final boolean isSound;

        Entry(String label, ItemStack icon, PlayAction action, boolean isSound) {
            this.label = label;
            this.icon = icon;
            this.action = action;
            this.isSound = isSound;
        }
    }

    // ============================================================
    // 图标工厂
    // ============================================================
    private static ItemStack greenPane() {
        return new ItemStack(Blocks.stained_glass_pane, 1, 13);
    }

    private static ItemStack redPane() {
        return new ItemStack(Blocks.stained_glass_pane, 1, 14);
    }

    private static ItemStack yellowPane() {
        return new ItemStack(Blocks.stained_glass_pane, 1, 4);
    }

    private static ItemStack grayPane() {
        return new ItemStack(Blocks.stained_glass_pane, 1, 7);
    }

    // ============================================================
    // 条目登记
    // ============================================================
    private static void addSound(String label, final String sound) {
        ENTRIES.add(new Entry(label, greenPane(), new PlayAction() {

            @Override
            public void play(World w, double x, double y, double z) {
                TCSounds.play(w, x, y, z, sound, 1.0F, 1.0F);
            }
        }, true));
    }

    private static void addParticle(String label, PlayAction action) {
        ENTRIES.add(new Entry(label, redPane(), action, false));
    }

    private static List<Entry> entries() {
        if (ENTRIES == null) initEntries();
        return ENTRIES;
    }

    private static void initEntries() {
        ENTRIES = new ArrayList<Entry>();

        // ============================================================
        // 音效（66 个，绿玻璃板）
        // ============================================================
        addSound("swing", TCSounds.SOUND_SWING);
        addSound("gore", TCSounds.SOUND_GORE);
        addSound("tool", TCSounds.SOUND_TOOL);
        addSound("wind", TCSounds.SOUND_WIND);
        addSound("golemironshoot", TCSounds.SOUND_GOLEM_IRON_SHOOT);
        addSound("shock", TCSounds.SOUND_SHOCK);
        addSound("zap", TCSounds.SOUND_ZAP);
        addSound("jacobs", TCSounds.SOUND_JACOBS);
        addSound("rumble", TCSounds.SOUND_RUMBLE);
        addSound("fireloop", TCSounds.SOUND_FIRE_LOOP);
        addSound("ice", TCSounds.SOUND_ICE);
        addSound("wand", TCSounds.SOUND_WAND);
        addSound("wandfail", TCSounds.SOUND_WAND_FAIL);
        addSound("crystal", TCSounds.SOUND_CRYSTAL);
        addSound("runicShieldEffect", TCSounds.SOUND_RUNIC_SHIELD_EFFECT);
        addSound("runicShieldCharge", TCSounds.SOUND_RUNIC_SHIELD_CHARGE);
        addSound("craftstart", TCSounds.SOUND_CRAFT_START);
        addSound("craftfail", TCSounds.SOUND_CRAFT_FAIL);
        addSound("upgrade", TCSounds.SOUND_UPGRADE);
        addSound("erase", TCSounds.SOUND_ERASE);
        addSound("bubble", TCSounds.SOUND_BUBBLE);
        addSound("spill", TCSounds.SOUND_SPILL);
        addSound("alembicknock", TCSounds.SOUND_ALEMBIC_KNOCK);
        addSound("pump", TCSounds.SOUND_PUMP);
        addSound("jar", TCSounds.SOUND_JAR);
        addSound("urnbreak", TCSounds.SOUND_URN_BREAK);
        addSound("infuser", TCSounds.SOUND_INFUSER);
        addSound("infuserstart", TCSounds.SOUND_INFUSER_START);
        addSound("page", TCSounds.SOUND_PAGE);
        addSound("write", TCSounds.SOUND_WRITE);
        addSound("learn", TCSounds.SOUND_LEARN);
        addSound("key", TCSounds.SOUND_KEY);
        addSound("doorfail", TCSounds.SOUND_DOOR_FAIL);
        addSound("pech_idle", TCSounds.SOUND_PECH_IDLE);
        addSound("pech_trade", TCSounds.SOUND_PECH_TRADE);
        addSound("pech_dice", TCSounds.SOUND_PECH_DICE);
        addSound("pech_hit", TCSounds.SOUND_PECH_HIT);
        addSound("pech_death", TCSounds.SOUND_PECH_DEATH);
        addSound("pech_charge", TCSounds.SOUND_PECH_CHARGE);
        addSound("wisplive", TCSounds.SOUND_WISP_LIVE);
        addSound("wispdead", TCSounds.SOUND_WISP_DEAD);
        addSound("fly", TCSounds.SOUND_FLY);
        addSound("swarm", TCSounds.SOUND_SWARM);
        addSound("swarmattack", TCSounds.SOUND_SWARM_ATTACK);
        addSound("egidle", TCSounds.SOUND_EG_IDLE);
        addSound("egattack", TCSounds.SOUND_EG_ATTACK);
        addSound("egdeath", TCSounds.SOUND_EG_DEATH);
        addSound("egscreech", TCSounds.SOUND_EG_SCREECH);
        addSound("crabclaw", TCSounds.SOUND_CRAB_CLAW);
        addSound("crabdeath", TCSounds.SOUND_CRAB_DEATH);
        addSound("crabtalk", TCSounds.SOUND_CRAB_TALK);
        addSound("brain", TCSounds.SOUND_BRAIN);
        addSound("tentacle", TCSounds.SOUND_TENTACLE);
        addSound("roots", TCSounds.SOUND_ROOTS);
        addSound("heartbeat", TCSounds.SOUND_HEARTBEAT);
        addSound("whispers", TCSounds.SOUND_WHISPERS);
        addSound("monolith", TCSounds.SOUND_MONOLITH);
        addSound("evilportal", TCSounds.SOUND_EVIL_PORTAL);
        addSound("chant", TCSounds.SOUND_CHANT);
        addSound("coins", TCSounds.SOUND_COINS);
        addSound("creak", TCSounds.SOUND_CREAK);
        addSound("squeek", TCSounds.SOUND_SQUEEK);
        addSound("cameraticks", TCSounds.SOUND_CAMERA_TICKS);
        addSound("cameraclack", TCSounds.SOUND_CAMERA_CLACK);
        addSound("hhon", TCSounds.SOUND_HH_ON);
        addSound("hhoff", TCSounds.SOUND_HH_OFF);

        // ============================================================
        // 粒子（33 个，红玻璃板）
        // 需要额外上下文的，统一用玩家 / 玩家视线代替
        // ============================================================

        // FXArc
        addParticle("arc", new PlayAction() {

            @Override
            public void play(World w, double x, double y, double z) {
                EntityPlayer p = Minecraft.getMinecraft().thePlayer;
                if (p == null) return;
                Vec3 look = p.getLook(1.0F);
                TCParticles.arc(w, x, y, z, x + look.xCoord * 5, y + look.yCoord * 5, z + look.zCoord * 5, 0xFF9933FF);
            }
        });

        // FXBeam
        addParticle("beam", new PlayAction() {

            @Override
            public void play(World w, double x, double y, double z) {
                EntityPlayer p = Minecraft.getMinecraft().thePlayer;
                if (p == null) return;
                Vec3 look = p.getLook(1.0F);
                TCParticles.beam(
                    w,
                    x,
                    y,
                    z,
                    x + look.xCoord * 5,
                    y + look.yCoord * 5,
                    z + look.zCoord * 5,
                    0.4F,
                    0.8F,
                    1.0F,
                    20,
                    1,
                    2.0F);
            }
        });

        // FXBeamBore
        addParticle("beamBore", new PlayAction() {

            @Override
            public void play(World w, double x, double y, double z) {
                EntityPlayer p = Minecraft.getMinecraft().thePlayer;
                if (p == null) return;
                Vec3 look = p.getLook(1.0F);
                TCParticles.beamBore(
                    w,
                    x,
                    y,
                    z,
                    x + look.xCoord * 5,
                    y + look.yCoord * 5,
                    z + look.zCoord * 5,
                    1.0F,
                    0.9F,
                    0.3F,
                    20,
                    1,
                    4);
            }
        });

        // FXBeamGolemBoss
        addParticle("beamGolemBoss", new PlayAction() {

            @Override
            public void play(World w, double x, double y, double z) {
                EntityPlayer p = Minecraft.getMinecraft().thePlayer;
                if (p == null) return;
                TCParticles.beamGolemBoss(w, p, p, 0.07F, 0.376F, 0.325F, 20, 2, 3.0F);
            }
        });

        // FXBeamPower
        addParticle("beamPower", new PlayAction() {

            @Override
            public void play(World w, double x, double y, double z) {
                EntityPlayer p = Minecraft.getMinecraft().thePlayer;
                if (p == null) return;
                Vec3 look = p.getLook(1.0F);
                TCParticles.beamPower(w, x, y, z, x + look.xCoord * 5, y + look.yCoord * 5, z + look.zCoord * 5, 20);
            }
        });

        // FXBeamWand
        addParticle("beamWand", new PlayAction() {

            @Override
            public void play(World w, double x, double y, double z) {
                EntityPlayer p = Minecraft.getMinecraft().thePlayer;
                if (p == null) return;
                Vec3 look = p.getLook(1.0F);
                TCParticles.beamWand(
                    w,
                    p,
                    x + look.xCoord * 5,
                    y + look.yCoord * 5,
                    z + look.zCoord * 5,
                    1.0F,
                    0.5F,
                    1.0F,
                    20,
                    1,
                    2.0F);
            }
        });

        // FXLightningBolt
        addParticle("lightning", new PlayAction() {

            @Override
            public void play(World w, double x, double y, double z) {
                EntityPlayer p = Minecraft.getMinecraft().thePlayer;
                if (p == null) return;
                Vec3 look = p.getLook(1.0F);
                TCParticles
                    .lightning(w, x, y, z, x + look.xCoord * 5, y + look.yCoord * 5, z + look.zCoord * 5, 2, 0.08F);
            }
        });

        // FXBlockWard
        addParticle("blockWard", new PlayAction() {

            @Override
            public void play(World w, double x, double y, double z) {
                TCParticles.blockWard(w, x, y, z, ForgeDirection.UP, 0.5F, 0.5F, 0.5F);
            }
        });

        // FXShieldRunes
        addParticle("shieldRunes", new PlayAction() {

            @Override
            public void play(World w, double x, double y, double z) {
                EntityPlayer p = Minecraft.getMinecraft().thePlayer;
                if (p == null) return;
                TCParticles.shieldRunes(w, x, y, z, p, 0.0F, 90.0F);
            }
        });

        // FXSonic
        addParticle("sonic", new PlayAction() {

            @Override
            public void play(World w, double x, double y, double z) {
                EntityPlayer p = Minecraft.getMinecraft().thePlayer;
                if (p == null) return;
                TCParticles.sonic(w, x, y, z, p);
            }
        });

        // FXBlockRunes
        addParticle("blockRunes", new PlayAction() {

            @Override
            public void play(World w, double x, double y, double z) {
                TCParticles.blockRunes(w, x, y, z, 0.8F, 0.4F, 1.0F, 3);
            }
        });

        // FXBoreParticles（方块版）
        addParticle("boreParticles", new PlayAction() {

            @Override
            public void play(World w, double x, double y, double z) {
                EntityPlayer p = Minecraft.getMinecraft().thePlayer;
                if (p == null) return;
                Vec3 look = p.getLook(1.0F);
                TCParticles.boreParticlesBlock(
                    w,
                    x,
                    y,
                    z,
                    x + look.xCoord * 3,
                    y + look.yCoord * 3,
                    z + look.zCoord * 3,
                    Blocks.stone,
                    1,
                    0);
            }
        });

        // FXBoreSparkle
        addParticle("boreSparkle", new PlayAction() {

            @Override
            public void play(World w, double x, double y, double z) {
                EntityPlayer p = Minecraft.getMinecraft().thePlayer;
                if (p == null) return;
                Vec3 look = p.getLook(1.0F);
                TCParticles.boreSparkle(w, x, y, z, x + look.xCoord * 3, y + look.yCoord * 3, z + look.zCoord * 3);
            }
        });

        // FXBreaking
        addParticle("breaking", new PlayAction() {

            @Override
            public void play(World w, double x, double y, double z) {
                TCParticles.breaking(w, x, y, z, Items.diamond);
            }
        });

        // FXBubble
        addParticle("bubble", new PlayAction() {

            @Override
            public void play(World w, double x, double y, double z) {
                TCParticles.bubble(w, x, y, z, 0.0, 0.02, 0.0, 0xFF9933FF, 8);
            }
        });

        // FXBubbleAlt
        addParticle("bubbleAlt", new PlayAction() {

            @Override
            public void play(World w, double x, double y, double z) {
                TCParticles.bubbleAlt(w, x, y, z, 0.0, 0.02, 0.0, 0xFF9933FF, 8);
            }
        });

        // FXBurst
        addParticle("burst", new PlayAction() {

            @Override
            public void play(World w, double x, double y, double z) {
                TCParticles.burst(w, x, y, z, 1.0F);
            }
        });

        // FXDrop
        addParticle("drop", new PlayAction() {

            @Override
            public void play(World w, double x, double y, double z) {
                TCParticles.drop(w, x, y, z, 0.5F, 0.5F, 1.0F);
            }
        });

        // FXEssentiaTrail
        addParticle("essentiaTrail", new PlayAction() {

            @Override
            public void play(World w, double x, double y, double z) {
                EntityPlayer p = Minecraft.getMinecraft().thePlayer;
                if (p == null) return;
                Vec3 look = p.getLook(1.0F);
                TCParticles.essentiaTrail(
                    w,
                    x,
                    y,
                    z,
                    x + look.xCoord * 4,
                    y + look.yCoord * 4,
                    z + look.zCoord * 4,
                    Aspect.FIRE,
                    1.0F);
            }
        });

        // FXGeneric
        addParticle("generic", new PlayAction() {

            @Override
            public void play(World w, double x, double y, double z) {
                TCParticles.generic(w, x, y, z, 0, 0, 0, 1.0F, 20, 0, 4, 1);
            }
        });

        // FXScorch
        addParticle("scorch", new PlayAction() {

            @Override
            public void play(World w, double x, double y, double z) {
                EntityPlayer p = Minecraft.getMinecraft().thePlayer;
                if (p == null) return;
                TCParticles.scorch(w, x, y, z, p.getLook(1.0F), 2.0F, false);
            }
        });

        // FXSlimyBubble
        addParticle("slimyBubble", new PlayAction() {

            @Override
            public void play(World w, double x, double y, double z) {
                TCParticles.slimyBubble(w, x, y, z, 1.0F);
            }
        });

        // FXSmokeSpiral
        addParticle("smokeSpiral", new PlayAction() {

            @Override
            public void play(World w, double x, double y, double z) {
                TCParticles.smokeSpiral(w, x, y, z, 1.0F, 0, (int) y);
            }
        });

        // FXSmokeTrail
        addParticle("smokeTrail", new PlayAction() {

            @Override
            public void play(World w, double x, double y, double z) {
                EntityPlayer p = Minecraft.getMinecraft().thePlayer;
                if (p == null) return;
                TCParticles.smokeTrail(w, x, y, z, p, 0.4F, 0.4F, 0.4F);
            }
        });

        // FXSpark
        addParticle("spark", new PlayAction() {

            @Override
            public void play(World w, double x, double y, double z) {
                TCParticles.spark(w, x, y, z, 1.0F);
            }
        });

        // FXSparkle
        addParticle("sparkle", new PlayAction() {

            @Override
            public void play(World w, double x, double y, double z) {
                TCParticles.sparkle(w, x, y, z, TCParticles.COLOR_WHITE, 2);
            }
        });

        // FXSparkleTrail
        addParticle("sparkleTrail", new PlayAction() {

            @Override
            public void play(World w, double x, double y, double z) {
                EntityPlayer p = Minecraft.getMinecraft().thePlayer;
                if (p == null) return;
                TCParticles.sparkleTrail(w, x, y, z, p, 1.0F, 1.0F, 1.0F);
            }
        });

        // FXSwarm
        addParticle("swarm", new PlayAction() {

            @Override
            public void play(World w, double x, double y, double z) {
                EntityPlayer p = Minecraft.getMinecraft().thePlayer;
                if (p == null) return;
                TCParticles.swarm(w, x, y, z, p, 0.6F, 0.2F, 0.2F);
            }
        });

        // FXVent
        addParticle("vent", new PlayAction() {

            @Override
            public void play(World w, double x, double y, double z) {
                TCParticles.vent(w, x, y, z, 0.0, 0.05, 0.0, 0xFFCCCCCC);
            }
        });

        // FXVisSparkle
        addParticle("visSparkle", new PlayAction() {

            @Override
            public void play(World w, double x, double y, double z) {
                EntityPlayer p = Minecraft.getMinecraft().thePlayer;
                if (p == null) return;
                Vec3 look = p.getLook(1.0F);
                TCParticles
                    .visSparkle(w, x, y, z, x + look.xCoord * 4, y + look.yCoord * 4, z + look.zCoord * 4, Aspect.FIRE);
            }
        });

        // FXWisp
        addParticle("wisp", new PlayAction() {

            @Override
            public void play(World w, double x, double y, double z) {
                TCParticles.wisp(w, x, y, z, TCParticles.COLOR_PINK_PURPLE);
            }
        });

        // FXWispArcing
        addParticle("wispArcing", new PlayAction() {

            @Override
            public void play(World w, double x, double y, double z) {
                EntityPlayer p = Minecraft.getMinecraft().thePlayer;
                if (p == null) return;
                Vec3 look = p.getLook(1.0F);
                TCParticles.wispArcing(
                    w,
                    x,
                    y,
                    z,
                    x + look.xCoord * 4,
                    y + look.yCoord * 4,
                    z + look.zCoord * 4,
                    1.0F,
                    0.6F,
                    1.0F);
            }
        });

        // FXWispEG
        addParticle("wispEG", new PlayAction() {

            @Override
            public void play(World w, double x, double y, double z) {
                EntityPlayer p = Minecraft.getMinecraft().thePlayer;
                if (p == null) return;
                TCParticles.wispEG(w, x, y, z, p);
            }
        });
    }

    // ============================================================
    // 工具方法
    // ============================================================
    private int totalPages() {
        return Math.max(1, (entries().size() + ITEMS_PER_PAGE - 1) / ITEMS_PER_PAGE);
    }

    private int guiLeft() {
        return (width - GUI_W) / 2;
    }

    private int guiTop() {
        return (height - GUI_H) / 2;
    }

    private int slotX(int slot) {
        return guiLeft() + 8 + (slot % COLS) * SLOT;
    }

    private int slotY(int slot) {
        return guiTop() + 18 + (slot / COLS) * SLOT;
    }

    // ============================================================
    // 生命周期
    // ============================================================
    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        // 全屏遮罩
        drawRect(0, 0, width, height, 0x80000000);

        int gx = guiLeft();
        int gy = guiTop();

        // GUI 背景
        drawRect(gx, gy, gx + GUI_W, gy + GUI_H, 0xC0000000);

        // 槽位背景
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                int sx = gx + 8 + col * SLOT;
                int sy = gy + 18 + row * SLOT;
                drawRect(sx, sy, sx + 16, sy + 16, 0xFF373737);
                drawRect(sx, sy, sx + 16, sy + 1, 0xFF8B8B8B);
                drawRect(sx, sy, sx + 1, sy + 16, 0xFF8B8B8B);
            }
        }

        // 标题
        drawCenteredString(
            fontRendererObj,
            "粒子/音效测试  -  第 " + (page + 1) + "/" + totalPages() + " 页",
            gx + GUI_W / 2,
            gy + 5,
            0xFFFFFF);

        // 绘制条目物品
        RenderHelper.enableGUIStandardItemLighting();
        for (int i = 0; i < ITEMS_PER_PAGE; i++) {
            int idx = page * ITEMS_PER_PAGE + i;
            if (idx >= entries().size()) break;
            int sx = gx + 8 + (i % COLS) * SLOT;
            int sy = gy + 18 + (i / COLS) * SLOT;
            itemRender
                .renderItemAndEffectIntoGUI(fontRendererObj, mc.getTextureManager(), entries().get(idx).icon, sx, sy);
        }
        RenderHelper.disableStandardItemLighting();

        // 上一页按钮
        {
            int sx = slotX(SLOT_PREV);
            int sy = slotY(SLOT_PREV);
            ItemStack icon = page > 0 ? yellowPane() : grayPane();
            RenderHelper.enableGUIStandardItemLighting();
            itemRender.renderItemAndEffectIntoGUI(fontRendererObj, mc.getTextureManager(), icon, sx, sy);
            RenderHelper.disableStandardItemLighting();
        }

        // 下一页按钮
        {
            int sx = slotX(SLOT_NEXT);
            int sy = slotY(SLOT_NEXT);
            ItemStack icon = page < totalPages() - 1 ? yellowPane() : grayPane();
            RenderHelper.enableGUIStandardItemLighting();
            itemRender.renderItemAndEffectIntoGUI(fontRendererObj, mc.getTextureManager(), icon, sx, sy);
            RenderHelper.disableStandardItemLighting();
        }

        // 悬停提示
        int hovered = getHoveredSlot(mouseX, mouseY);
        if (hovered >= 0 && hovered < ITEMS_PER_PAGE) {
            int idx = page * ITEMS_PER_PAGE + hovered;
            if (idx < entries().size()) {
                Entry e = entries().get(idx);
                List<String> lines = new ArrayList<String>();
                lines.add(e.label);
                lines.add(EnumChatFormatting.GRAY + (e.isSound ? "音效" : "粒子"));
                lines.add(EnumChatFormatting.DARK_GRAY + "点击触发");
                drawHoveringText(lines, mouseX, mouseY, fontRendererObj);
            }
        } else if (hovered == SLOT_PREV) {
            drawCreativeTabHoveringText(page > 0 ? "上一页" : "已经是第一页", mouseX, mouseY);
        } else if (hovered == SLOT_NEXT) {
            drawCreativeTabHoveringText(page < totalPages() - 1 ? "下一页" : "已经是最后一页", mouseX, mouseY);
        }
    }

    private int getHoveredSlot(int mouseX, int mouseY) {
        int gx = guiLeft();
        int gy = guiTop();
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                int sx = gx + 8 + col * SLOT;
                int sy = gy + 18 + row * SLOT;
                if (mouseX >= sx && mouseX < sx + 16 && mouseY >= sy && mouseY < sy + 16) {
                    return row * COLS + col;
                }
            }
        }
        return -1;
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int button) {
        if (button != 0) return;

        int slot = getHoveredSlot(mouseX, mouseY);
        if (slot < 0) return;

        // 翻页
        if (slot == SLOT_PREV) {
            if (page > 0) page--;
            return;
        }
        if (slot == SLOT_NEXT) {
            if (page < totalPages() - 1) page++;
            return;
        }

        // 条目
        if (slot >= ITEMS_PER_PAGE) return;
        int idx = page * ITEMS_PER_PAGE + slot;
        if (idx >= entries().size()) return;

        Entry e = entries().get(idx);
        EntityPlayer player = mc.thePlayer;
        if (player == null) return;

        World w = player.worldObj;
        double x = player.posX;
        double y = player.posY + player.getEyeHeight();
        double z = player.posZ;

        // 先关 GUI
        mc.displayGuiScreen(null);

        // 日志（硬编码）
        ThaumicSurvival.LOG.info("[TSFXTest] 触发: " + e.label + " (" + (e.isSound ? "音效" : "粒子") + ")");

        // 执行
        try {
            e.action.play(w, x, y, z);
        } catch (Throwable t) {
            ThaumicSurvival.LOG.error("[TSFXTest] 执行失败: " + e.label, t);
        }
    }

    @Override
    protected void keyTyped(char typedChar, int keyCode) {
        if (keyCode == Keyboard.KEY_ESCAPE) {
            mc.displayGuiScreen(null);
            return;
        }
        super.keyTyped(typedChar, keyCode);
    }

    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }
}
