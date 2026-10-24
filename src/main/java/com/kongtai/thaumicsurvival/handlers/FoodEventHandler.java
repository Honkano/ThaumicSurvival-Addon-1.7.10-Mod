package com.kongtai.thaumicsurvival.handlers;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.event.entity.player.PlayerUseItemEvent;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.lib.network.PacketHandler;
import thaumcraft.common.lib.network.playerdata.PacketAspectPool;
import thaumcraft.common.lib.research.ResearchManager;

public class FoodEventHandler {

    @SubscribeEvent
    public void onItemUseFinish(PlayerUseItemEvent.Finish event) {
        EntityPlayer player = event.entityPlayer;
        ItemStack stack = event.item;

        if (stack == null || stack.getItem() != net.minecraft.init.Items.golden_apple) return;

        NBTTagCompound nbt = stack.getTagCompound();
        if (nbt == null || !nbt.hasKey("thaumicsurvival:apple_type")) return;

        String type = nbt.getString("thaumicsurvival:apple_type");
        boolean isEnchanted = "enchanted".equals(type);

        int amount;
        if (isEnchanted) {
            int tier = nbt.hasKey("thaumicsurvival:tier") ? nbt.getInteger("thaumicsurvival:tier") : 0;
            amount = 64 * (int) Math.pow(2, tier);
        } else {
            amount = 1;
        }

        if (!player.worldObj.isRemote) {
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
            player.addChatComponentMessage(new net.minecraft.util.ChatComponentText("§a获得了 " + amount + " 点原始要素研究点！"));
        }
    }
}
