package com.kongtai.thaumicsurvival.api.thaumcraft.tcapiusetools;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagByte;

import thaumcraft.api.wands.WandCap;
import thaumcraft.api.wands.WandRod;
import thaumcraft.common.config.ConfigItems;
import thaumcraft.common.items.wands.ItemWandCasting;

/**
 * ============================================================
 * 【法杖成品皮肤库（出院即满血·绝对不用自己拼版）】
 * ============================================================
 * 这里的全是一比一原装拼好的成品！直接 .icon(TCWands.xxx()) 即可！
 * ============================================================
 */
public class TCWands {

    // ============================================================
    // 【一、普通法杖（短棍形态，单手）】
    // ============================================================

    // --- 铁杖端系列 ---
    public static ItemStack wandIronWood() {
        return buildWand(ConfigItems.WAND_CAP_IRON, ConfigItems.WAND_ROD_WOOD, false);
    }

    public static ItemStack wandIronGreatwood() {
        return buildWand(ConfigItems.WAND_CAP_IRON, ConfigItems.WAND_ROD_GREATWOOD, false);
    }

    public static ItemStack wandIronSilverwood() {
        return buildWand(ConfigItems.WAND_CAP_IRON, ConfigItems.WAND_ROD_SILVERWOOD, false);
    }

    // --- 金杖端系列 ---
    public static ItemStack wandGoldWood() {
        return buildWand(ConfigItems.WAND_CAP_GOLD, ConfigItems.WAND_ROD_WOOD, false);
    }

    public static ItemStack wandGoldGreatwood() {
        return buildWand(ConfigItems.WAND_CAP_GOLD, ConfigItems.WAND_ROD_GREATWOOD, false);
    }

    public static ItemStack wandGoldSilverwood() {
        return buildWand(ConfigItems.WAND_CAP_GOLD, ConfigItems.WAND_ROD_SILVERWOOD, false);
    }

    // --- 神秘杖端系列 ---
    public static ItemStack wandThaumiumGreatwood() {
        return buildWand(ConfigItems.WAND_CAP_THAUMIUM, ConfigItems.WAND_ROD_GREATWOOD, false);
    }

    public static ItemStack wandThaumiumSilverwood() {
        return buildWand(ConfigItems.WAND_CAP_THAUMIUM, ConfigItems.WAND_ROD_SILVERWOOD, false);
    }

    // --- 虚空杖端系列 ---
    public static ItemStack wandVoidSilverwood() {
        return buildWand(ConfigItems.WAND_CAP_VOID, ConfigItems.WAND_ROD_SILVERWOOD, false);
    }

    // --- 元素特化系列（金头 + 元素杖柄） ---
    public static ItemStack wandBlazeGold() {
        return buildWand(ConfigItems.WAND_CAP_GOLD, ConfigItems.WAND_ROD_BLAZE, false);
    }

    public static ItemStack wandIceGold() {
        return buildWand(ConfigItems.WAND_CAP_GOLD, ConfigItems.WAND_ROD_ICE, false);
    }

    public static ItemStack wandObsidianGold() {
        return buildWand(ConfigItems.WAND_CAP_GOLD, ConfigItems.WAND_ROD_OBSIDIAN, false);
    }

    public static ItemStack wandQuartzGold() {
        return buildWand(ConfigItems.WAND_CAP_GOLD, ConfigItems.WAND_ROD_QUARTZ, false);
    }

    public static ItemStack wandReedGold() {
        return buildWand(ConfigItems.WAND_CAP_GOLD, ConfigItems.WAND_ROD_REED, false);
    }

    public static ItemStack wandBoneGold() {
        return buildWand(ConfigItems.WAND_CAP_GOLD, ConfigItems.WAND_ROD_BONE, false);
    }

    // ============================================================
    // 【二、手杖（长棍形态，双手）】
    // ============================================================

    public static ItemStack staffIronGreatwood() {
        return buildWand(ConfigItems.WAND_CAP_IRON, ConfigItems.STAFF_ROD_GREATWOOD, false);
    }

    public static ItemStack staffThaumiumSilverwood() {
        return buildWand(ConfigItems.WAND_CAP_THAUMIUM, ConfigItems.STAFF_ROD_SILVERWOOD, false);
    }

    public static ItemStack staffVoidSilverwood() {
        return buildWand(ConfigItems.WAND_CAP_VOID, ConfigItems.STAFF_ROD_SILVERWOOD, false);
    }

    public static ItemStack staffBlazeGold() {
        return buildWand(ConfigItems.WAND_CAP_GOLD, ConfigItems.STAFF_ROD_BLAZE, false);
    }

    // ============================================================
    // 【三、权杖（发光短棍，魔力减半）】
    // ============================================================

    public static ItemStack scepterThaumiumSilverwood() {
        return buildWand(ConfigItems.WAND_CAP_THAUMIUM, ConfigItems.WAND_ROD_SILVERWOOD, true);
    }

    public static ItemStack scepterVoidSilverwood() {
        return buildWand(ConfigItems.WAND_CAP_VOID, ConfigItems.WAND_ROD_SILVERWOOD, true);
    }

    // ============================================================
    // 【四、底层手术台（如果你以后想自己拼，再用这个）】
    // ============================================================
    // 这个就是用来把“458件残骸”拼起来的底层工具。
    // 上面的所有成品，底层都是调用的它。不用你手动拼，我帮你拼好了！

    public static ItemStack buildWand(WandCap cap, WandRod rod, boolean isScepter) {
        ItemStack wand = new ItemStack(ConfigItems.itemWandCasting, 1, 0);
        ItemWandCasting wandItem = (ItemWandCasting) wand.getItem();

        wandItem.setCap(wand, cap);
        wandItem.setRod(wand, rod);

        if (isScepter) {
            wand.setTagInfo("sceptre", new NBTTagByte((byte) 1));
        }

        return wand;
    }
}
// ============================================================
// 【三、狗狗级换装小抄】
// ============================================================
//
// 以后你想在魔导书里展示一把炫酷的法杖，别去管那堆 ConfigItems 了，直接看这里：
//
// 比如你想展示一把“黑曜石杖柄 + 虚空杖端”的法杖：
// 用法 A：直接用我上面写好的成品 -> .icon(TCWands.wandSilverwoodVoid())
//
// 用法 B：用底层方法自己拼 -> .icon(TCWands.buildWand(ConfigItems.WAND_CAP_VOID, ConfigItems.WAND_ROD_OBSIDIAN, false))
//
// 杖端材质 (Cap)：
// WAND_CAP_IRON (铁 - 最基础)
// WAND_CAP_GOLD (金 - 耗魔100%)
// WAND_CAP_THAUMIUM(神秘 - 耗魔90%)
// WAND_CAP_VOID (虚空 - 耗魔80%)
//
// 杖柄材质 (Rod)：
// WAND_ROD_WOOD (木 - 容量 25)
// WAND_ROD_GREATWOOD (宏伟之木 - 容量 50)
// WAND_ROD_SILVERWOOD (银树 - 容量 100)
// WAND_ROD_BLAZE (烈焰 - 火特化)
// WAND_ROD_ICE (寒霜 - 水特化)
//
// 想要长棍手杖？把 WAND_ROD_XXX 换成 STAFF_ROD_XXX 即可！
// ============================================================
