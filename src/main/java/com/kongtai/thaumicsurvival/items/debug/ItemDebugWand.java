package com.kongtai.thaumicsurvival.items.debug;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;

import thaumcraft.common.Thaumcraft;
import thaumcraft.common.lib.network.PacketHandler;
import thaumcraft.common.lib.network.playerdata.PacketSyncWarp;
import thaumcraft.common.lib.research.ResearchManager;

public class ItemDebugWand extends Item {

    public ItemDebugWand() {
        super();
        setUnlocalizedName("thaumicsurvival.debug_wand");
        setTextureName("thaumicsurvival:debug/ts_debug_wand");
        setMaxStackSize(1);
    }

    @Override
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean advanced) {
        list.add(EnumChatFormatting.LIGHT_PURPLE + "右键：一键解锁全研究 / 给满要素");
        list.add(EnumChatFormatting.LIGHT_PURPLE + "右键：回满状态 / 清除药水效果 / 给一组 T6 苹果");
        list.add(EnumChatFormatting.RED + "潜行 + 右键：清除所有扭曲值（虚空低语消散）");
        list.add(EnumChatFormatting.DARK_GRAY + "（仅限创造模式生效）");
    }

    @Override
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
        if (world.isRemote) return stack;

        // 强制要求创造模式
        if (!player.capabilities.isCreativeMode) {
            player.addChatComponentMessage(new ChatComponentText(EnumChatFormatting.RED + "你尚未拥有神明般的力量（仅限创造模式）！"));
            return stack;
        }

        if (player.isSneaking()) {
            // 1. 潜行+右键：清除所有扭曲值
            clearAllWarp(player);
        } else {
            // 2. 普通右键：解锁全研究 + 999要素
            unlockAllResearch(player);
            unlockAllResearch(player);
        }
        return stack;
    }

    private void unlockAllResearch(EntityPlayer player) {
        net.minecraft.server.MinecraftServer server = net.minecraft.server.MinecraftServer.getServer();
        if (server != null) {
            String playerName = player.getCommandSenderName();

            // 先执行：全部要素各给 1 点
            server.getCommandManager()
                .executeCommand(server, "thaumcraft aspect " + playerName + " all 1");

            // 再执行：全解研究
            server.getCommandManager()
                .executeCommand(server, "thaumcraft research " + playerName + " all");

            player.addChatComponentMessage(new ChatComponentText(EnumChatFormatting.GREEN + "已给予全部要素各 1 点，并一键解锁全部研究！"));
        }
    }

    private void clearAllWarp(EntityPlayer player) {
        String name = player.getCommandSenderName();
        // 1. 数据层面清空
        Thaumcraft.proxy.playerKnowledge.setWarpPerm(name, 0);
        Thaumcraft.proxy.playerKnowledge.setWarpSticky(name, 0);
        Thaumcraft.proxy.playerKnowledge.setWarpTemp(name, 0);

        // 重置总扭曲计数
        Thaumcraft.proxy.playerKnowledge.setWarpCounter(name, 0);

        ResearchManager.scheduleSave(player);

        // 2. 发送同步包给客户端 (修复2：补上 byte type 参数)
        EntityPlayerMP playerMP = (EntityPlayerMP) player;
        PacketHandler.INSTANCE.sendTo(new PacketSyncWarp(player, (byte) 0), playerMP); // 永久扭曲
        PacketHandler.INSTANCE.sendTo(new PacketSyncWarp(player, (byte) 1), playerMP); // 粘性扭曲
        PacketHandler.INSTANCE.sendTo(new PacketSyncWarp(player, (byte) 2), playerMP); // 临时扭曲

        player.addChatComponentMessage(new ChatComponentText(EnumChatFormatting.GOLD + "已清除所有永久/粘性/临时扭曲值！虚空低语已消散。"));
    }
}
