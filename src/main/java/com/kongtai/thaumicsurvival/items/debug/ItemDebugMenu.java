package com.kongtai.thaumicsurvival.items.debug;

import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;

import com.kongtai.thaumicsurvival.ThaumicSurvival;
import com.kongtai.thaumicsurvival.debug.TSFXTestGui;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemDebugMenu extends Item {

    public ItemDebugMenu() {
        super();
        setUnlocalizedName("thaumicsurvival.debug_menu");
        setTextureName("thaumicsurvival:debug/ts_debug_menu");
        setMaxStackSize(1);
    }

    @Override
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean advanced) {
        list.add(EnumChatFormatting.AQUA + "右键：打开粒子与音效测试菜单");
        list.add(EnumChatFormatting.GRAY + "包含神秘时代所有粒子特效与音效");
        list.add(EnumChatFormatting.DARK_RED + "（仅限创造模式生效）");
    }

    @Override
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
        // 强制要求创造模式
        if (!player.capabilities.isCreativeMode) {
            if (!world.isRemote) {
                player.addChatComponentMessage(new ChatComponentText(EnumChatFormatting.RED + "该工具仅限创造模式使用！"));
            }
            return stack;
        }

        // 只在客户端打开 GUI
        if (world.isRemote) {
            openGui();
        }
        return stack;
    }

    @SideOnly(Side.CLIENT)
    private void openGui() {
        // 直接调用，不需要 pendingOpen 延迟了，因为右键没有聊天栏关闭的干扰
        Minecraft.getMinecraft()
            .displayGuiScreen(new TSFXTestGui());
        ThaumicSurvival.LOG.info("[TSFXTest] 通过物品打开了调试 GUI");
    }
}
