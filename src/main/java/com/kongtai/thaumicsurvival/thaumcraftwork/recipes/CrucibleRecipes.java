package com.kongtai.thaumicsurvival.thaumcraftwork.recipes;

import thaumcraft.api.crafting.CrucibleRecipe;

/**
 * ============================================================
 * 【坩埚配方管理类】
 * ============================================================
 * 这个类专门放“坩埚炼金配方”。
 * 现在暂时是空的，只留结构和注释，以后你往里填就行。
 *
 * 【坩埚配方是什么】
 * 玩家把“催化剂”丢进装满水的坩埚，再往里面丢够源质，
 * 坩埚就会把催化剂转化成输出物品。
 *
 * 【和注魔配方的区别】
 * 注魔祭坛：需要祭坛 + 基座 + 源质，有不稳定度
 * 坩埚：只需要坩埚 + 水 + 源质，没有不稳定度
 *
 * 【怎么加一个新坩埚配方】
 * 在 init() 里照着下面注释写一段：
 *
 * CrucibleRecipe recipe = TCRecipes.crucible(
 * "你的研究KEY", // 研究 key，必须和研究节点一致
 * TCItems.thaumiumIngot(), // 合成出来的东西
 * TCItems.ironIngot(), // 丢进坩埚的引子
 * TCAspects.of(TCAspects.MAGIC, 8, TCAspects.TOOL, 4)); // 源质消耗
 *
 * crucibleRecipes.add(recipe); // 存进列表，研究页引用
 *
 * ============================================================
 */
public class CrucibleRecipes {

    /**
     * 所有坩埚配方注册后存到这里。
     * 研究页里用 CrucibleRecipes.crucibleRecipes 引用。
     * 现在先留着，以后加配方就往里 add。
     */
    public static java.util.List<CrucibleRecipe> crucibleRecipes = new java.util.ArrayList<>();

    /**
     * 总入口。
     * 在 CommonProxy.init() 里调用 CrucibleRecipes.init()。
     * 现在什么都不做，只是把结构占住，以后往里填。
     */
    public static void init() {
        // ==========================================
        // 【空】坩埚配方写在这里
        // ==========================================
        //
        // 以后加配方的模板（复制下面这段，改参数）：
        //
        // CrucibleRecipe recipe = TCRecipes.crucible(
        // "THAUMICSURVIVAL_XXX", // ← 研究 key
        // TCItems.thaumiumIngot(), // ← 输出
        // TCItems.ironIngot(), // ← 催化剂
        // TCAspects.of(TCAspects.MAGIC, 8, TCAspects.TOOL, 4)); // ← 源质消耗
        //
        // crucibleRecipes.add(recipe);
        //
        // ==========================================
    }
}
