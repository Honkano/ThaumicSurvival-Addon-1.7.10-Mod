package com.kongtai.thaumicsurvival.api.tsapi;

import net.minecraft.item.ItemStack;

import com.kongtai.thaumicsurvival.blocks.ModBlocks;

/**
 * 【神秘生存学 · 方块快速调用词典】
 * 
 * 用途：把 ModBlocks.xxx 包一层，写配方/研究页/注魔输出时
 * 不用再 new ItemStack(ModBlocks.xxx)，直接 TSBlocks.xxx()。
 * 
 * 规则：
 * 1. 每个方法返回【新的】ItemStack，不共享实例。
 * 2. 有 meta 的方块用参数控制，越界自动夹到合法范围。
 * 3. 方块本体（放置用）仍走 ModBlocks.xxx，本类只给 ItemStack。
 */
public class TSBlocks {

    // ==========================================
    // 1. 图腾系列
    // ==========================================

    /**
     * 蕴灵黑曜石图腾（无 meta）
     * 注意：不带节点 NBT，是空壳。
     * 需要带节点的，用 BlockEnergizedTotem.writeRandomNodeToStack(...)
     */
    public static ItemStack energizedTotem() {
        return new ItemStack(ModBlocks.energizedTotem);
    }

    // ==========================================
    // 2. 未来预留：其他方块
    // ==========================================
    // public static ItemStack xxx() { ... }
}
