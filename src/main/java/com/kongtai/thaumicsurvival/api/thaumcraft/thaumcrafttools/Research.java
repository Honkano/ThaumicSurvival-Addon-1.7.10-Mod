package com.kongtai.thaumicsurvival.api.thaumcraft.thaumcrafttools;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.crafting.CrucibleRecipe;
import thaumcraft.api.crafting.IArcaneRecipe;
import thaumcraft.api.crafting.InfusionRecipe;
import thaumcraft.api.crafting.ShapedArcaneRecipe;
import thaumcraft.api.research.ResearchItem;
import thaumcraft.api.research.ResearchPage;

public class Research {

    private final String key;
    private String category = TCResearchHelper.CATEGORY_THAUMICSURVIVAL;
    private AspectList aspects = new AspectList();
    private int col = 0, row = 0;
    private int tier = 1;
    private ItemStack iconItem;
    private ResourceLocation iconRes;

    private final List<ResearchPage> pages = new ArrayList<>();
    private final List<String> parents = new ArrayList<>();
    private final List<String> parentsHidden = new ArrayList<>();
    private final List<String> siblings = new ArrayList<>();
    private final List<ItemStack> itemTriggers = new ArrayList<>();
    private final List<String> entityTriggers = new ArrayList<>();
    private final List<Aspect> aspectTriggers = new ArrayList<>();

    private boolean round, special, concealed, secondary, autoUnlock, stub, hidden, lost, virtual;

    private Research(String key) {
        this.key = key;
    }

    public static Research of(String key) {
        return new Research(key);
    }

    public Research category(String cat) {
        this.category = cat;
        return this;
    }

    public Research at(int col, int row) {
        this.col = col;
        this.row = row;
        return this;
    }

    public Research tier(int tier) {
        this.tier = tier;
        return this;
    }

    public Research aspects(AspectList aspects) {
        this.aspects = aspects;
        return this;
    }

    public Research icon(ItemStack icon) {
        this.iconItem = icon;
        this.iconRes = null;
        return this;
    }

    public Research icon(ResourceLocation icon) {
        this.iconRes = icon;
        this.iconItem = null;
        return this;
    }

    public Research parents(String... keys) {
        for (String k : keys) parents.add(k);
        return this;
    }

    public Research parentsHidden(String... keys) {
        for (String k : keys) parentsHidden.add(k);
        return this;
    }

    public Research siblings(String... keys) {
        for (String k : keys) siblings.add(k);
        return this;
    }

    public Research pages(ResearchPage... pages) {
        for (ResearchPage p : pages) this.pages.add(p);
        return this;
    }

    public Research textPages(int count) {
        for (int i = 1; i <= count; i++) this.pages.add(TCResearchHelper.textPage(this.key, i));
        return this;
    }

    public Research itemTriggers(ItemStack... stacks) {
        for (ItemStack s : stacks) itemTriggers.add(s);
        return this;
    }

    public Research entityTriggers(String... entities) {
        for (String e : entities) entityTriggers.add(e);
        return this;
    }

    public Research aspectTriggers(Aspect... aspects) {
        for (Aspect a : aspects) aspectTriggers.add(a);
        return this;
    }

    public Research aspectsPage(AspectList aspects) {
        this.pages.add(new ResearchPage(aspects));
        return this;
    }

    public Research smeltingPage(ItemStack input) {
        this.pages.add(new ResearchPage(input));
        return this;
    }

    /**
     * 多方块结构展示页（自动适配 A 哥反人类的 GUI 渲染顺序）
     * 
     * @param layers 按【从下到上】的顺序传入每一层的结构配方
     */
    /**
     * 多方块结构展示页（完美适配 A 哥的底层逻辑）
     * 
     * @param layers 按【从下到上】的顺序传入每一层的结构配方
     */
    /**
     * 多方块结构展示页（完美适配 A 哥的底层逻辑）
     * 
     * @param layers 按【从下到上】的顺序传入每一层的结构配方
     */
    /**
     * 多方块结构展示页（终极扒底裤版）
     * 自动把你传入的从下到上的层数，展平成一维数组，完美喂给 A 哥的底层！
     */
    /**
     * 多方块结构展示页（完美对齐 A 哥底层，终极修正版）
     * 
     * @param layers 按【从下到上】的顺序传入每一层的结构配方
     */
    /**
     * 多方块结构展示页（绝对不报错·免导包终极版）
     * 
     * @param layers 按【从下到上】的顺序传入每一层的结构配方
     */
    public Research multiblock(ShapedArcaneRecipe... layers) {
        if (layers == null || layers.length == 0) return this;

        int dy = layers.length; // Y轴，高度（层数）
        int dx = layers[0].width; // X轴，宽度
        int dz = layers[0].height; // Z轴，深度

        List<ItemStack> items = new ArrayList<>();

        // 👇 A 哥渲染时是从顶层(y=0)开始画的，所以我们直接倒着遍历传入的 layers！
        // 这样连 Collections.reverse 都不需要了！
        for (int j = 0; j < dy; j++) {
            // j=0 时，取最后一层（最顶层）；j=dy-1 时，取第一层（最底层）
            ShapedArcaneRecipe layer = layers[dy - 1 - j];
            Object[] input = layer.getInput();
            int w = layer.width;

            // 👇 A 哥的遍历是 Z 反向，X 反向
            for (int k = dz - 1; k >= 0; --k) {
                for (int i = dx - 1; i >= 0; --i) {
                    int index = i + k * w;
                    if (index >= 0 && index < input.length && input[index] != null) {
                        Object obj = input[index];
                        if (obj instanceof ItemStack) {
                            items.add((ItemStack) obj);
                        } else if (obj instanceof List) { // 兼容矿辞
                            List<?> list = (List<?>) obj;
                            if (!list.isEmpty()) items.add((ItemStack) list.get(0));
                            else items.add(null);
                        } else {
                            items.add(null);
                        }
                    } else {
                        items.add(null);
                    }
                }
            }
        }

        // 组装成 A 哥唯一认的格式： [Aspects, dx, dy, dz, items]
        List<Object> recipeList = new ArrayList<>();
        recipeList.add(new AspectList());
        recipeList.add(dx);
        recipeList.add(dy);
        recipeList.add(dz);
        recipeList.add(items);

        this.pages.add(new ResearchPage(recipeList));
        return this;
    }

    // ============================================================
    // 👇 【点读机页面大招】：一键打包 List 成页面，无需手动 toArray
    // ============================================================
    // 👇 无参点读机：直接去 TCRecipes 的分拣机里，根据 key 抓取！
    public Research cruciblePage() {
        List<CrucibleRecipe> list = TCRecipes.CRUCIBLE_MAP.get(this.key);
        if (list != null && !list.isEmpty()) {
            this.pages.add(new ResearchPage(list.toArray(new CrucibleRecipe[0])));
        }
        return this;
    }

    public Research arcanePage() {
        List<IArcaneRecipe> list = TCRecipes.ARCANE_MAP.get(this.key);
        if (list != null && !list.isEmpty()) {
            this.pages.add(new ResearchPage(list.toArray(new IArcaneRecipe[0])));
        }
        return this;
    }

    public Research infusionPage() {
        List<InfusionRecipe> list = TCRecipes.INFUSION_MAP.get(this.key);
        if (list != null && !list.isEmpty()) {
            this.pages.add(new ResearchPage(list.toArray(new InfusionRecipe[0])));
        }
        return this;
    }

    // 👇 自动挡（无参）：底层用 for 循环帮你拆页
    public Research enchantPage() {
        List<thaumcraft.api.crafting.InfusionEnchantmentRecipe> list = TCRecipes.ENCHANT_MAP.get(this.key);
        if (list != null && !list.isEmpty()) {
            for (thaumcraft.api.crafting.InfusionEnchantmentRecipe recipe : list) {
                this.pages.add(new ResearchPage(recipe)); // 底层帮你一页放一个
            }
        }
        return this;
    }

    // 👇 手动挡（带参）：你想塞哪个塞哪个，想塞几个塞几个！
    public Research enchantPage(thaumcraft.api.crafting.InfusionEnchantmentRecipe... recipes) {
        for (thaumcraft.api.crafting.InfusionEnchantmentRecipe recipe : recipes) {
            this.pages.add(new ResearchPage(recipe));
        }
        return this;
    }

    public Research cruciblePage(CrucibleRecipe... recipes) {
        this.pages.add(new ResearchPage(recipes));
        return this;
    }

    public Research arcanePage(IArcaneRecipe... recipes) {
        this.pages.add(new ResearchPage(recipes));
        return this;
    }

    public Research infusionPage(InfusionRecipe... recipes) {
        this.pages.add(new ResearchPage(recipes));
        return this;
    }

    // ============================================================
    // 👇 【状态开关】
    // ============================================================

    public Research round() {
        this.round = true;
        return this;
    }

    public Research special() {
        this.special = true;
        return this;
    }

    public Research concealed() {
        this.concealed = true;
        return this;
    }

    public Research secondary() {
        this.secondary = true;
        return this;
    }

    public Research autoUnlock() {
        this.autoUnlock = true;
        return this;
    }

    public Research stub() {
        this.stub = true;
        return this;
    }

    public Research hidden() {
        this.hidden = true;
        return this;
    }

    public Research lost() {
        this.lost = true;
        return this;
    }

    public Research virtual() {
        this.virtual = true;
        return this;
    }

    public ResearchItem build() {
        ResearchItem item = (iconItem != null) ? new ResearchItem(key, category, aspects, col, row, tier, iconItem)
            : new ResearchItem(key, category, aspects, col, row, tier, iconRes);

        if (!pages.isEmpty()) item.setPages(pages.toArray(new ResearchPage[0]));
        if (!parents.isEmpty()) item.setParents(parents.toArray(new String[0]));
        if (!parentsHidden.isEmpty()) item.setParentsHidden(parentsHidden.toArray(new String[0]));
        if (!siblings.isEmpty()) item.setSiblings(siblings.toArray(new String[0]));
        if (!itemTriggers.isEmpty()) item.setItemTriggers(itemTriggers.toArray(new ItemStack[0]));
        if (!entityTriggers.isEmpty()) item.setEntityTriggers(entityTriggers.toArray(new String[0]));
        if (!aspectTriggers.isEmpty()) item.setAspectTriggers(aspectTriggers.toArray(new Aspect[0]));

        if (round) item.setRound();
        if (special) item.setSpecial();
        if (concealed) item.setConcealed();
        if (secondary) item.setSecondary();
        if (autoUnlock) item.setAutoUnlock();
        if (stub) item.setStub();
        if (hidden) item.setHidden();
        if (lost) item.setLost();
        if (virtual) item.setVirtual();

        item.registerResearchItem();
        return item;
    }
}
