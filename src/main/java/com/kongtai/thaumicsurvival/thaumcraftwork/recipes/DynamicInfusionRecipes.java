package com.kongtai.thaumicsurvival.thaumcraftwork.recipes;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.item.ItemStack;

import com.kongtai.thaumicsurvival.api.thaumcraft.tcapiusetools.TCAspects;
import com.kongtai.thaumicsurvival.api.thaumcraft.tcapiusetools.TCBlocks;
import com.kongtai.thaumicsurvival.api.thaumcraft.tcapiusetools.TCItems;
import com.kongtai.thaumicsurvival.api.thaumcraft.thaumcrafttools.TCRecipes;
import com.kongtai.thaumicsurvival.items.ModItems;

import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.crafting.InfusionRecipe;

/**
 * 【动态注魔配方管理类】
 * 所有多级、材料递减/递增、倍率递增、需要一页循环显示的注魔配方，全部放这里。
 * 普通单级注魔配方请放 InfusionRecipes。
 */
public class DynamicInfusionRecipes {

    public static List<InfusionRecipe> infusionAnvilRecipes = new ArrayList<>();
    public static List<InfusionRecipe> greedyAppleRecipes = new ArrayList<>();

    public static void init() {
        initAnvilRecipes();
        initGreedyAppleRecipes();
        // 以后新增动态注魔物品，在这里加一行 initXxxRecipes()
    }

    // ==========================================
    // 1. 注魔铁砧（多段注魔配方）
    // 中心永远是铁锭，基座铁粒数量从 8 递减到 1
    // 材料越少 → 源质倍率越大 → 不稳定度越高
    // ==========================================
    private static void initAnvilRecipes() {
        AspectList baseEssentiaAnvil = TCAspects
            .of(TCAspects.METAL, 8, TCAspects.TOOL, 4, TCAspects.CRAFT, 4, TCAspects.EARTH, 4, TCAspects.FIRE, 2);

        // 每一级：铁粒数量、倍率、不稳定度
        Object[][] anvilLevels = { { 8, 1, 2 }, { 7, 2, 3 }, { 6, 3, 4 }, { 5, 4, 5 }, { 4, 5, 6 }, { 3, 6, 7 },
            { 2, 7, 8 }, { 1, 8, 10 } };

        infusionAnvilRecipes.clear();
        for (int i = 0; i < anvilLevels.length; i++) {
            int nuggetCount = (Integer) anvilLevels[i][0];
            int multiplier = (Integer) anvilLevels[i][1];
            int instability = (Integer) anvilLevels[i][2];

            AspectList cost = new AspectList();
            for (Aspect aspect : baseEssentiaAnvil.getAspects()) {
                cost.add(aspect, baseEssentiaAnvil.getAmount(aspect) * multiplier);
            }

            ItemStack[] components = new ItemStack[nuggetCount];
            for (int j = 0; j < nuggetCount; j++) {
                components[j] = TCItems.nuggetIron();
            }

            InfusionRecipe recipe = TCRecipes.infusion(
                "THAUMICSURVIVAL_INFUSION_ANVIL",
                TCBlocks.anvil(),
                instability,
                cost,
                TCItems.ironIngot(),
                components);
            infusionAnvilRecipes.add(recipe);
        }
    }

    // ==========================================
    // 2. 魔铸金苹果（多段注魔配方）
    // 中心永远是普通苹果，靠基座材料数量区分等级
    // 材料越少 → 源质倍率越大 → 不稳定度越高
    // ==========================================
    private static void initGreedyAppleRecipes() {
        AspectList baseEssentia = TCAspects
            .of(TCAspects.FIRE, 32, TCAspects.LIFE, 48, TCAspects.MAGIC, 64, TCAspects.GREED, 64, TCAspects.HUNGER, 32);

        // 每一级需要的材料和倍率 (组件, 倍率, 不稳定度)
        Object[][] levels = {
            { new ItemStack[] { TCBlocks.goldBlock(), TCBlocks.goldBlock(), TCBlocks.goldBlock(),
                TCBlocks.goldBlock() }, 1, 1 },
            { new ItemStack[] { TCBlocks.goldBlock(), TCBlocks.goldBlock(), TCBlocks.goldBlock() }, 2, 2 },
            { new ItemStack[] { TCBlocks.goldBlock(), TCBlocks.goldBlock() }, 4, 4 },
            { new ItemStack[] { TCBlocks.goldBlock() }, 8, 8 },
            { new ItemStack[] { TCItems.goldIngot(), TCItems.goldIngot(), TCItems.goldIngot(), TCItems.goldIngot() },
                16, 15 },
            { new ItemStack[] { TCItems.goldIngot(), TCItems.goldIngot(), TCItems.goldIngot() }, 32, 18 },
            { new ItemStack[] { TCItems.goldIngot(), TCItems.goldIngot() }, 64, 19 },
            { new ItemStack[] { TCItems.goldIngot() }, 128, 25 } };

        greedyAppleRecipes.clear();
        for (int i = 0; i < levels.length; i++) {
            ItemStack[] components = (ItemStack[]) levels[i][0];
            int multiplier = (Integer) levels[i][1];
            int instability = (Integer) levels[i][2];

            AspectList cost = new AspectList();
            for (Aspect aspect : baseEssentia.getAspects()) {
                cost.add(aspect, baseEssentia.getAmount(aspect) * multiplier);
            }

            ItemStack tierApple = ModItems.createGoldenApple(
                1,
                "§c魔铸金苹果",
                i,
                "§7蕴含大量原始要素",
                "§7食用后可获得§r§6" + (64 * (int) Math.pow(2, i)) + " 点原始要素研究点§7",
                "§c注：此物蕴含危险的力量");

            InfusionRecipe recipe = TCRecipes.infusion(
                "THAUMICSURVIVAL_ENCHANTED_GAPPLE",
                tierApple,
                instability,
                cost,
                TCItems.apple(),
                components);
            greedyAppleRecipes.add(recipe);
        }
    }
}
