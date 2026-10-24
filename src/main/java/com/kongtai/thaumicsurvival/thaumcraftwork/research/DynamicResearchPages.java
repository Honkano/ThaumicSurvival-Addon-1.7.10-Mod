package com.kongtai.thaumicsurvival.thaumcraftwork.research;

import java.util.ArrayList;
import java.util.List;

import com.kongtai.thaumicsurvival.api.thaumcraft.thaumcrafttools.TCResearchHelper;
import com.kongtai.thaumicsurvival.thaumcraftwork.recipes.DynamicInfusionRecipes;

import thaumcraft.api.crafting.InfusionRecipe;
import thaumcraft.api.research.ResearchPage;

/**
 * 【动态配方页创建类】
 * 专门负责把“配方列表”组装成 ResearchPage[]，
 * 让 TSResearchRegistry 不再写 for 循环，保持研究注册类的整洁。
 */
public class DynamicResearchPages {

    /**
     * 构建注魔铁砧的研究页面数组：
     * 第 1 页是文本，第 2 页用 InfusionRecipe[] 数组构造器，GUI 循环显示 8 个等级。
     */
    public static ResearchPage[] buildAnvilPages() {
        List<ResearchPage> pages = new ArrayList<ResearchPage>();

        pages.add(TCResearchHelper.textPage("THAUMICSURVIVAL_INFUSION_ANVIL", 1));
        pages.add(TCResearchHelper.textPage("THAUMICSURVIVAL_INFUSION_ANVIL", 2));

        // ★ 一个页面装整个数组，GUI 循环翻
        pages.add(new ResearchPage(DynamicInfusionRecipes.infusionAnvilRecipes.toArray(new InfusionRecipe[0])));

        return pages.toArray(new ResearchPage[0]);
    }

    /**
     * 构建魔铸金苹果的研究页面数组：
     * 前 2 页是文本，第 3 页用 InfusionRecipe[] 数组构造器，
     * GUI 翻页时会自动循环显示 8 个等级的配方。
     */
    public static ResearchPage[] buildGreedyApplePages() {
        List<ResearchPage> pages = new ArrayList<ResearchPage>();

        // 文本页（保留你原来的页面 key）
        pages.add(TCResearchHelper.textPage("THAUMICSURVIVAL_GREED", 1));
        pages.add(TCResearchHelper.textPage("THAUMICSURVIVAL_GREED", 2));

        // ★ 一个页面装整个数组，GUI 循环翻
        pages.add(new ResearchPage(DynamicInfusionRecipes.greedyAppleRecipes.toArray(new InfusionRecipe[0])));

        return pages.toArray(new ResearchPage[0]);
    }
}
