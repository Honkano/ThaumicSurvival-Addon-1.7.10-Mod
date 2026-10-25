package com.kongtai.thaumicsurvival.thaumcraftwork.recipes;

import net.minecraft.item.ItemStack;

import com.kongtai.thaumicsurvival.api.thaumcraft.tcapiusetools.TCAspects;
import com.kongtai.thaumicsurvival.api.thaumcraft.tcapiusetools.TCBlocks;
import com.kongtai.thaumicsurvival.api.thaumcraft.tcapiusetools.TCItems;
import com.kongtai.thaumicsurvival.api.thaumcraft.thaumcrafttools.TCRecipes;
import com.kongtai.thaumicsurvival.items.ModItems;

import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.crafting.IArcaneRecipe;

public class ArcaneShapedRecipes {

    // 声明为静态变量，供研究页面调用
    public static IArcaneRecipe recipeSimpleApple;// 简易金苹果
    public static IArcaneRecipe recipeSimpleAnvil;// 简易铁砧

    public static void init() {
        // ==========================================
        // 1. 简易金苹果（奥术有序配方）
        // ==========================================
        AspectList visCost = TCAspects.of(TCAspects.EARTH, 8, TCAspects.FIRE, 8, TCAspects.WATER, 8, TCAspects.AIR, 8, TCAspects.ORDER, 8, TCAspects.ENTROPY, 8);
        ItemStack simpleApple = ModItems.createGoldenApple(0, "§6秘源金苹果", 0, "§7蕴含微量原始要素", "§7食用后可获得§r§6原始要素研究点§7");

        recipeSimpleApple = TCRecipes.arcaneShaped(
            "THAUMICSURVIVAL_SIMPLE_GAPPLE",
            simpleApple,
            visCost,
            new Object[] { " G ", "GAG", " G ", 'G', TCItems.goldIngot(), 'A', TCItems.apple() });

        // ==========================================
        // 2. 简易铁砧（奥术有序配方）
        // ==========================================
        // 抛弃了水火，改为主打金属、工具和工艺，更符合铁砧的物理特性
        AspectList visCostAnvil = TCAspects.of(
            TCAspects.EARTH,
            12, // 大地：绝对的主导，铁块铁锭的沉重感
            TCAspects.FIRE,
            6, // 火焰：打铁怎么能没有火？
            TCAspects.ORDER,
            4 // 秩序：将散乱的铁塑造成规整的砧板
        );

        recipeSimpleAnvil = TCRecipes.arcaneShaped( // ★ 注意这里从 Shapeless 换成了 Shaped
            "THAUMICSURVIVAL_SIMPLE_ANVIL",
            TCBlocks.anvil(),
            visCostAnvil,
            new Object[] { " A ", // 第一行：中间放铁块
                "BBB", // 第二行：三个铁锭排开
                'A', TCBlocks.ironBlock(), // A 代表铁块
                'B', TCItems.ironIngot() // B 代表铁锭
            });

    }

    // ============================================================
    // 【下面以后加新有序配方时照抄，改参数】
    // ============================================================
    //
    // 1. 先在类顶部加静态字段：
    // public static IArcaneRecipe recipeXxx;
    //
    // 2. 在 init() 里加一段：
    // AspectList visCostXxx = TCAspects.of(TCAspects.AIR, 10, TCAspects.ORDER, 5);
    //
    // recipeXxx = TCRecipes.arcaneShaped(
    // "你的研究KEY",
    // TCItems.diamond(), // 输出
    // visCostXxx,
    // new Object[] {
    // "ABA",
    // "BCB",
    // "ABA",
    // 'A', TCItems.goldIngot(),
    // 'B', TCItems.ironIngot(),
    // 'C', TCItems.diamond()
    // });
    //
    // 3. 研究页里引用：
    // new ResearchPage(ArcaneShapedRecipes.recipeXxx)
    //
    // 注意：
    // TCRecipes.arcaneShaped 内部就是调 ThaumcraftApi.addArcaneCraftingRecipe，
    // 参数顺序和原生一致，只是少了手写 Aspect.XXX 和 new ItemStack(...)。
    //
    // ============================================================
}
