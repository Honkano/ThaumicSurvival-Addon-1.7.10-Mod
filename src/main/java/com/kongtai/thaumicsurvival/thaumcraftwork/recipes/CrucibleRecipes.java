package com.kongtai.thaumicsurvival.thaumcraftwork.recipes;

import com.kongtai.thaumicsurvival.api.thaumcraft.tcapiusetools.TCAspects;
import com.kongtai.thaumicsurvival.api.thaumcraft.tcapiusetools.TCBlocks;
import com.kongtai.thaumicsurvival.api.thaumcraft.thaumcrafttools.TCRecipes;

/**
 * ============================================================
 * 【坩埚配方管理类】
 * ============================================================
 * 这个类专门放“坩埚炼金配方”。
 * 
 * 【坩埚配方是什么】
 * 玩家把“催化剂”丢进装满水的坩埚，再往里面丢够源质，
 * 坩埚就会把催化剂转化成输出物品。
 *
 * 【和注魔配方的区别】
 * 注魔祭坛：需要祭坛 + 基座 + 源质，有不稳定度
 * 坩埚：只需要坩埚 + 水 + 源质，没有不稳定度
 *
 * 【⭐ 今天的最新架构说明（极其重要）】
 * 因为我们升级了 TCRecipes 的“自动分拣机（Map）”系统：
 * 1. 你在这里只管调 TCRecipes.crucible(...) 把配方造出来。
 * 2. TCRecipes 底层会根据你填的“研究 KEY”，自动把配方塞进专属的抽屉里。
 * 3. 以前那个 public static List<CrucibleRecipe> 已经不需要了，彻底删掉！
 * 4. 研究节点那边，只需要写一句 .cruciblePage()，就会自动根据 Key 把这里的配方全抓过去排版！
 *
 * 【以后怎么加一个新坩埚配方？】
 * 在 init() 里照着下面这个模板写就行了：
 * 
 * TCRecipes.crucible(
 * "你的研究KEY", // 必须和研究节点一致
 * TCItems.thaumiumIngot(), // 合成出来的东西
 * TCItems.ironIngot(), // 丢进坩埚的引子
 * TCAspects.of(TCAspects.MAGIC, 8, TCAspects.TOOL, 4) // 源质消耗
 * );
 * 
 * 注意：写完上面这句就结束了！不需要再写任何 .add() ！底层自动接管！
 * ============================================================
 */
public class CrucibleRecipes {

    /**
     * 总入口。
     * 在 CommonProxy.init() 里调用 CrucibleRecipes.init()。
     */
    public static void init() {

        // ==========================================
        // 黑曜石三兄弟：坩埚炼金配方
        // ==========================================

        // 1. 基础黑曜石 ➡ 黑曜石图腾
        // 丢入黑曜石，注入黑暗、大地与魔力，捏出图腾。
        TCRecipes.crucible(
            "THAUMICSURVIVAL_OBSIDIAN_TOTEM", // 研究 Key
            TCBlocks.obsidianTotem(), // 输出：黑曜石图腾 (meta 0)
            TCBlocks.obsidian(), // 催化剂：原版黑曜石
            TCAspects.of(
                TCAspects.DARKNESS,
                8, // 黑暗：图腾的本源
                TCAspects.EARTH,
                8, // 地：石头的沉重
                TCAspects.MAGIC,
                4 // 魔力：注魔的引子
            ));

        // 2. 基础黑曜石 ➡ 黑曜石瓦块
        // 丢入黑曜石，注入秩序与工艺，打磨成规整的瓦块。
        TCRecipes.crucible(
            "THAUMICSURVIVAL_OBSIDIAN_TOTEM",
            TCBlocks.obsidianTile(), // 输出：黑曜石瓦块 (meta 1)
            TCBlocks.obsidian(), // 催化剂：原版黑曜石
            TCAspects.of(
                TCAspects.ORDER,
                8, // 秩序：规整的形状
                TCAspects.EARTH,
                8,
                TCAspects.CRAFT,
                4 // 工艺：打磨
            ));

        // ==========================================
        // 【空】以后新坩埚配方写在这里
        // ==========================================
        //
        // 模板（复制下面这段，改参数）：
        // TCRecipes.crucible(
        // "THAUMICSURVIVAL_XXX",
        // TCItems.thaumiumIngot(),
        // TCItems.ironIngot(),
        // TCAspects.of(TCAspects.MAGIC, 8, TCAspects.TOOL, 4)
        // );
        //
        // ==========================================
    }
}
