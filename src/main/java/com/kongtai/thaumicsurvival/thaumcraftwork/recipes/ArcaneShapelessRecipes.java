package com.kongtai.thaumicsurvival.thaumcraftwork.recipes;

import com.kongtai.thaumicsurvival.api.thaumcraft.tcapiusetools.TCAspects;
import com.kongtai.thaumicsurvival.api.thaumcraft.tcapiusetools.TCBlocks;
import com.kongtai.thaumicsurvival.api.thaumcraft.tcapiusetools.TCItems;
import com.kongtai.thaumicsurvival.api.thaumcraft.thaumcrafttools.TCRecipes;

import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.crafting.IArcaneRecipe;

public class ArcaneShapelessRecipes {

    // 声明为静态变量
    public static IArcaneRecipe recipeSimpleObsidian;// 简易黑曜石

    public static void init() {
        // ==========================================
        // 1. 简易黑曜石（奥术无序配方）
        // ==========================================
        AspectList visCostObsidian = TCAspects.of(TCAspects.FIRE, 1, TCAspects.WATER, 1);

        // 拿一个黑曜石，然后把它的数量改成 2
        net.minecraft.item.ItemStack doubleObsidian = TCBlocks.obsidian();
        doubleObsidian.stackSize = 2;

        recipeSimpleObsidian = TCRecipes.arcaneShapeless(
            "THAUMICSURVIVAL_SIMPLE_OBSIDIAN",
            doubleObsidian, // ← 把改了数量的黑曜石丢进去
            visCostObsidian,
            TCItems.waterBucket(),
            TCItems.lavaBucket());

    }

    // ============================================================
    // 【下面以后加新无序配方时照抄，改参数】
    // ============================================================
    //
    // 1. 先在类顶部加静态字段：
    // public static IArcaneRecipe recipeXxx;
    //
    // 2. 在 init() 里加一段：
    // AspectList visCostXxx = TCAspects.of(TCAspects.AIR, 10, TCAspects.ORDER, 5);
    //
    // recipeXxx = TCRecipes.arcaneShapeless(
    // "你的研究KEY",
    // TCItems.diamond(), // 输出
    // visCostXxx,
    // TCItems.ironIngot(), // 材料 1
    // TCItems.ironIngot(), // 材料 2
    // TCItems.goldIngot()); // 材料 3
    //
    // 3. 研究页里引用：
    // new ResearchPage(ArcaneShapelessRecipes.recipeXxx)
    //
    // 注意：
    // TCRecipes.arcaneShapeless 内部就是调
    // ThaumcraftApi.addShapelessArcaneCraftingRecipe，
    // 参数顺序和原生一致，材料是可变参数，有几个写几个。
    //
    // ============================================================
}
