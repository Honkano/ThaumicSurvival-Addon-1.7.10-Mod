package com.kongtai.thaumicsurvival.thaumcraftwork.recipes;

import net.minecraft.item.ItemStack;

import com.kongtai.thaumicsurvival.api.thaumcraft.tcapiusetools.TCAspects;
import com.kongtai.thaumicsurvival.api.thaumcraft.tcapiusetools.TCBlocks;
import com.kongtai.thaumicsurvival.api.thaumcraft.tcapiusetools.TCItems;
import com.kongtai.thaumicsurvival.api.thaumcraft.thaumcrafttools.TCRecipes;
import com.kongtai.thaumicsurvival.api.tsapi.TSItems;

import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;

public class DynamicInfusionRecipes {

    public static void init() {
        initAnvilRecipes();
        initGreedyAppleRecipes();
    }

    private static void initAnvilRecipes() {
        AspectList baseEssentiaAnvil = TCAspects
            .of(TCAspects.METAL, 8, TCAspects.TOOL, 4, TCAspects.CRAFT, 4, TCAspects.EARTH, 4, TCAspects.FIRE, 2);

        Object[][] anvilLevels = { { 8, 1, 2 }, { 7, 2, 3 }, { 6, 3, 4 }, { 5, 4, 5 }, { 4, 5, 6 }, { 3, 6, 7 },
            { 2, 7, 8 }, { 1, 8, 10 } };

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

            // 👇 直接注册，底层自动放进分拣机，不需要再 add 进 List 了！
            TCRecipes.infusion(
                "THAUMICSURVIVAL_INFUSION_ANVIL",
                TCBlocks.anvil(),
                instability,
                cost,
                TCItems.ironIngot(),
                components);
        }
    }

    private static void initGreedyAppleRecipes() {
        AspectList baseEssentia = TCAspects.of(
            TCAspects.FIRE,
            32,
            TCAspects.LIFE,
            48,
            TCAspects.MAGIC,
            64,
            TCAspects.GREED,
            64,
            TCAspects.HUNGER,
            32,
            TCAspects.EARTH,
            8,
            TCAspects.FIRE,
            8,
            TCAspects.WATER,
            8,
            TCAspects.AIR,
            8,
            TCAspects.ORDER,
            8,
            TCAspects.ENTROPY,
            8);

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

        for (int i = 0; i < levels.length; i++) {
            ItemStack[] components = (ItemStack[]) levels[i][0];
            int multiplier = (Integer) levels[i][1];
            int instability = (Integer) levels[i][2];

            AspectList cost = new AspectList();
            for (Aspect aspect : baseEssentia.getAspects()) {
                cost.add(aspect, baseEssentia.getAmount(aspect) * multiplier);
            }

            ItemStack tierApple = TSItems.greedApple(i);

            // 👇 同样直接注册！
            TCRecipes.infusion(
                "THAUMICSURVIVAL_ENCHANTED_GAPPLE",
                tierApple,
                instability,
                cost,
                TCItems.apple(),
                components);
        }
    }
}
