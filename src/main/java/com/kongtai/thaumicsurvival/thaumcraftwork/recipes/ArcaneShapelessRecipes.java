package com.kongtai.thaumicsurvival.thaumcraftwork.recipes;

import net.minecraft.item.ItemStack;

import com.kongtai.thaumicsurvival.api.thaumcraft.tcapiusetools.TCAspects;
import com.kongtai.thaumicsurvival.api.thaumcraft.tcapiusetools.TCBlocks;
import com.kongtai.thaumicsurvival.api.thaumcraft.tcapiusetools.TCItems;
import com.kongtai.thaumicsurvival.api.thaumcraft.thaumcrafttools.TCRecipes;

import thaumcraft.api.aspects.AspectList;

/**
 * ============================================================
 * 【无序奥术配方管理类（终极保姆级·分拣机版）】
 * ============================================================
 * 专门放奥术工作台的“无序”配方（随便摆，只要材料对就行）。
 * 
 * 【⭐⭐⭐ 绝对红线：奥术工作台只能用六大原始要素！】
 * 绝对不能写：MAGIC, LIFE, GREED, MIND 等复合要素！
 * 只能写：AIR(风), EARTH(地), FIRE(火), WATER(水), ORDER(秩序), ENTROPY(混沌)！
 * 
 * 【⭐ 架构大升级】
 * 和有序配方一样，抛弃 public static 变量，直接调 TCRecipes！
 * 研究页里一句 .arcanePage() 自动全抓取！
 * ============================================================
 */
public class ArcaneShapelessRecipes {

    public static void init() {

        // ==========================================
        // 👇 示例 1：简易黑曜石（水火不容的碰撞）
        // ==========================================
        AspectList visCostObsidian = TCAspects.of(TCAspects.FIRE, 1, TCAspects.WATER, 1);

        // 拿一个黑曜石，把数量改成 2
        ItemStack doubleObsidian = TCBlocks.obsidian();
        doubleObsidian.stackSize = 2;

        TCRecipes.arcaneShapeless(
            "THAUMICSURVIVAL_SIMPLE_OBSIDIAN", // 研究 Key
            doubleObsidian, // 输出物品（2个黑曜石）
            visCostObsidian, // 魔力消耗
            TCItems.waterBucket(), // 材料 1
            TCItems.lavaBucket() // 材料 2
        );

        // ==========================================
        // 👇 示例 2（演示用）：任意组合的材料大乱炖
        // ==========================================
        /*
         * TCRecipes.arcaneShapeless(
         * "THAUMICSURVIVAL_MAGIC_MIX",
         * TCItems.diamond(),
         * TCAspects.of(TCAspects.AIR, 5),
         * TCItems.ironIngot(),
         * TCItems.goldIngot(),
         * TCItems.salisMundus()
         * );
         */
    }
}

/*
 * ============================================================
 * 👇👇👇 【狗狗级奥术无序配方小抄】 👇👇👇
 * ============================================================
 * 【小抄 1：无序配方怎么写？】
 * 不需要写九宫格字符串！
 * 直接按顺序把材料丢进去就行，有几个写几个，玩家在工作台里随便摆。
 * 【小抄 2：怎么修改输出数量？】
 * 就像上面的黑曜石，你可以直接拿一个物品，然后 .stackSize = 2;
 * 但小心别改错了共享的 ItemStack 引用。
 * 【小抄 3：书里怎么显示？】
 * 和有序奥术一模一样：
 * .arcanePage()
 * 底层会自动识别，是 Shaped 就画九宫格，是 Shapeless 就画无序排列。
 * 它们可以混在同一个节点的同一页里！因为底层都存进了 ARCANE_MAP！
 * 【小抄 4：和注魔的区别？】
 * 奥术台只需要扣 Vis（六大要素），不需要注魔祭坛的基座和不稳定度。
 * ============================================================
 */
