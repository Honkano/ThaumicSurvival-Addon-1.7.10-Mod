package com.kongtai.thaumicsurvival.api.tsapi;

import net.minecraft.item.ItemStack;

import com.kongtai.thaumicsurvival.items.ModItems;

/**
 * 【神秘生存学 · 物品快速调用词典】
 */
public class TSItems {

    // ==========================================
    // 1. 苹果系列
    // ==========================================

    /**
     * 秘源金苹果（奥数工作台合成，无 Meta）
     */
    public static ItemStack arcaneApple() {
        return new ItemStack(ModItems.simpleApple);
    }

    // ==========================================
    // 2. 神器 / 特殊物品系列
    // ==========================================
    /**
     * /**
     * 魔铸金苹果（注魔合成，通过 tier 指定 0~6 等级）
     * 
     * @param tier 0 到 6
     */
    public static ItemStack greedApple(int tier) {
        // 防呆设计：如果传入的等级越界，强制设为 0 级或最高级
        if (tier < 0) tier = 0;
        if (tier > 7) tier = 7;
        return new ItemStack(ModItems.enchantedApple, 1, tier);
    }

    // --- 方便快捷的专项调用（如果你平时固定用某几个等级） ---
    public static ItemStack greedAppleT0() {
        return greedApple(0);
    }

    public static ItemStack greedAppleT1() {
        return greedApple(1);
    }

    public static ItemStack greedAppleT2() {
        return greedApple(2);
    }

    public static ItemStack greedAppleT3() {
        return greedApple(3);
    }

    public static ItemStack greedAppleT4() {
        return greedApple(4);
    }

    public static ItemStack greedAppleT5() {
        return greedApple(5);
    }

    public static ItemStack greedAppleT6() {
        return greedApple(6);
    }

    public static ItemStack greedAppleT7() {
        return greedApple(7);
    }
    // ==========================================
    // 2. 未来预留：铁砧、神器、武器抽屉
    // ==========================================
    // public static ItemStack simpleAnvil() { ... }
    // public static ItemStack bloodSword() { ... }
}
