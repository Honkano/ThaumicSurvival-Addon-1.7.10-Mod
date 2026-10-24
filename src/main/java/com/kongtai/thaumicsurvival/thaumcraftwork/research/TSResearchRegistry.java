package com.kongtai.thaumicsurvival.thaumcraftwork.research;

import net.minecraft.util.ResourceLocation;

import com.kongtai.thaumicsurvival.api.thaumcraft.tcapiusetools.TCAspects;
import com.kongtai.thaumicsurvival.api.thaumcraft.tcapiusetools.TCBlocks;
import com.kongtai.thaumicsurvival.api.thaumcraft.tcapiusetools.TCItems;
import com.kongtai.thaumicsurvival.api.thaumcraft.thaumcrafttools.TCResearchHelper;
import com.kongtai.thaumicsurvival.thaumcraftwork.recipes.ArcaneShapedRecipes;
import com.kongtai.thaumicsurvival.thaumcraftwork.recipes.ArcaneShapelessRecipes;

import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.research.ResearchCategories;
import thaumcraft.api.research.ResearchItem;
import thaumcraft.api.research.ResearchPage;

/**
 * ============================================================
 * 【研究页写法指南 · 按配方类分类】
 * ============================================================
 * 核心原则：一个配方类，对应一种研究页写法。
 *
 * 配方类 研究页怎么写
 * ─────────────────────────────────────────────
 * ArcaneShapedRecipes new ResearchPage(recipeXxx)
 * ArcaneShapelessRecipes new ResearchPage(recipeXxx)
 * InfusionRecipes new ResearchPage(recipeXxx)
 * DynamicInfusionRecipes DynamicResearchPages.buildXxxPages()
 * CrucibleRecipes new ResearchPage(recipeXxx)
 * 或 .toArray(new CrucibleRecipe[0])
 *
 * 配方页构造器接收的是“配方对象”，不是字符串。
 * 所以配方必须先注册，并存到静态字段或 List，
 * 研究页里直接引用那个字段/List。
 *
 * 本类只放注释模板，不注册任何东西，不会崩。
 * 写新研究页时，按“配方类”找对应 section，照抄改参数。
 * ============================================================
 */

public class TSResearchRegistry {

    public static void init() {
        // 注册自定义分类
        ResearchCategories.registerCategory(
            "THAUMICSURVIVAL",
            new ResourceLocation("thaumicsurvival", "textures/items/golden_apple_icon.png"),
            new ResourceLocation("thaumcraft", "textures/gui/gui_researchback.png"));

        // 1. 主节点
        new ResearchItem(
            "THAUMICSURVIVAL_MASTER",
            "THAUMICSURVIVAL",
            new AspectList(),
            0,
            0,
            1,
            new ResourceLocation("thaumicsurvival", "textures/items/golden_apple_icon.png"))
                .setAutoUnlock()
                .setStub()
                .setRound()
                .setPages(
                    TCResearchHelper.textPage("THAUMICSURVIVAL_MASTER", 1),
                    TCResearchHelper.textPage("THAUMICSURVIVAL_MASTER", 2))
                .registerResearchItem();


        // 2. 简易黑曜石
        new ResearchItem(
            "THAUMICSURVIVAL_SIMPLE_OBSIDIAN",
            "THAUMICSURVIVAL",
            TCAspects.of(
                TCAspects.EARTH,
                8, // 黑曜石偏大地
                TCAspects.FIRE,
                8, // 岩浆有火
                TCAspects.WATER,
                4), // 水桶有水
            1,
            -1,
            1,
            TCBlocks.obsidian()).setParents("THAUMICSURVIVAL_MASTER")
                .setPages(
                    TCResearchHelper.textPage("THAUMICSURVIVAL_SIMPLE_OBSIDIAN", 1),
                    TCResearchHelper.textPage("THAUMICSURVIVAL_SIMPLE_OBSIDIAN", 2),
                    new ResearchPage(ArcaneShapelessRecipes.recipeSimpleObsidian)) // 引用无序配方
                .registerResearchItem();


        // 3. 秘源金苹果（直接调用拆分好的配方变量）
        new ResearchItem(
            "THAUMICSURVIVAL_SIMPLE_GAPPLE",
            "THAUMICSURVIVAL",
            TCAspects.of(TCAspects.GREED, 64, TCAspects.LIFE, 32, TCAspects.FIRE, 64, TCAspects.MAGIC, 16),
            3,
            0,
            1,
            TCItems.goldenApple()).setParents("THAUMICSURVIVAL_MASTER")
                .setPages(
                    TCResearchHelper.textPage("THAUMICSURVIVAL_SIMPLE_GAPPLE", 1),
                    new ResearchPage(ArcaneShapedRecipes.recipeSimpleApple)) // 引用有序配方
                .setConcealed()
                .registerResearchItem();

        // 4. 简易铁砧
        new ResearchItem(
            "THAUMICSURVIVAL_SIMPLE_ANVIL",
            "THAUMICSURVIVAL",
            TCAspects.of(TCAspects.CRAFT, 8, TCAspects.METAL, 8, TCAspects.TOOL, 4),
            2,
            3,
            1,
            TCBlocks.anvil()).setParents("THAUMICSURVIVAL_MASTER")
                .setPages(
                    TCResearchHelper.textPage("THAUMICSURVIVAL_SIMPLE_ANVIL", 1),
                    new ResearchPage(ArcaneShapedRecipes.recipeSimpleAnvil)) // 引用有序配方
                .registerResearchItem();

        // 5. 注魔铁砧（页面组装交给 DynamicResearchPages 处理）
        new ResearchItem(
            "THAUMICSURVIVAL_INFUSION_ANVIL",
            "THAUMICSURVIVAL",
            TCAspects.of(TCAspects.CRAFT, 16, TCAspects.METAL, 16, TCAspects.TOOL, 8, TCAspects.MAGIC, 8),
            1,
            5,
            2,
            TCBlocks.anvil()).setParents("THAUMICSURVIVAL_SIMPLE_ANVIL", "MIRROR_INFUSION")
                .setParentsHidden(TCResearchHelper.RESEARCH_INFUSION)
                .setPages(DynamicResearchPages.buildAnvilPages())
                .setConcealed()
                .setSpecial()
                .registerResearchItem();

        // 6. 魔铸金苹果（页面组装交给 DynamicResearchPages 处理）
        new ResearchItem(
            "THAUMICSURVIVAL_ENCHANTED_GAPPLE",
            "THAUMICSURVIVAL",
            TCAspects.of(TCAspects.GREED, 16, TCAspects.MAGIC, 16, TCAspects.LIFE, 12, TCAspects.HUNGER, 8),
            5,
            5,
            3,
            TCItems.notchApple())
                .setParents("THAUMICSURVIVAL_SIMPLE_GAPPLE", "MIRROR_INFUSION")
                .setParentsHidden(TCResearchHelper.RESEARCH_INFUSION)
                .setPages(DynamicResearchPages.buildGreedyApplePages())
                .setConcealed()
                .setSpecial()
                .registerResearchItem();



        // 添加扭曲值
        TCResearchHelper.warp("THAUMICSURVIVAL_SIMPLE_GAPPLE", 3);
        TCResearchHelper.warp("THAUMICSURVIVAL_ENCHANTED_GAPPLE", 10);

        // ============================================================
        // 【Section 1】ArcaneShapedRecipes → 有序奥术配方页
        // ============================================================
        //
        // 配方法在哪：recipes/ArcaneShapedRecipes.java
        // 配方字段：public static IArcaneRecipe recipeXxx;
        //
        // 研究页写法：
        // new ResearchPage(ArcaneShapedRecipes.recipeXxx)
        //
        // 完整节点示例：
        // new ResearchItem(
        // "THAUMICSURVIVAL_XXX", // 研究 key
        // "THAUMICSURVIVAL", // 分类 key
        // TCAspects.of(TCAspects.MAGIC, 8), // 连连看要素
        // 1, 0, 1, // x, y, 难度
        // TCItems.diamond()) // 图标
        // .setParents("THAUMICSURVIVAL_MASTER")
        // .setPages(
        // TCResearchHelper.textPage("THAUMICSURVIVAL_XXX", 1),
        // new ResearchPage(ArcaneShapedRecipes.recipeXxx)
        // )
        // .registerResearchItem();
        //
        // 注意：
        // ResearchPage 构造器认 IArcaneRecipe 和 ShapedArcaneRecipe，
        // 字段类型是哪个都行。

        // ============================================================
        // 【Section 2】ArcaneShapelessRecipes → 无序奥术配方页
        // ============================================================
        //
        // 配方法在哪：recipes/ArcaneShapelessRecipes.java
        // 配方字段：public static IArcaneRecipe recipeXxx;
        //
        // 研究页写法：
        // new ResearchPage(ArcaneShapelessRecipes.recipeXxx)
        //
        // 完整节点示例：
        // new ResearchItem(
        // "THAUMICSURVIVAL_XXX",
        // "THAUMICSURVIVAL",
        // TCAspects.of(TCAspects.CRAFT, 8),
        // 1, 1, 1,
        // TCBlocks.anvil())
        // .setParents("THAUMICSURVIVAL_MASTER")
        // .setPages(
        // TCResearchHelper.textPage("THAUMICSURVIVAL_XXX", 1),
        // new ResearchPage(ArcaneShapelessRecipes.recipeXxx)
        // )
        // .registerResearchItem();
        //
        // 注意：
        // 有序/无序奥术用的是同一个 ResearchPage 构造器，
        // 传进去它自己识别类型，不用你区分。

        // ============================================================
        // 【Section 3】InfusionRecipes → 普通注魔配方页
        // ============================================================
        //
        // 配方法在哪：recipes/InfusionRecipes.java
        // 配方字段：public static InfusionRecipe recipeXxx;
        // 注意：这里只放“单级、静态”的普通注魔配方。
        // 多级动态注魔看 Section 4。
        //
        // 研究页写法（单配方）：
        // new ResearchPage(InfusionRecipes.recipeXxx)
        //
        // 研究页写法（多静态配方塞一页）：
        // new ResearchPage(new InfusionRecipe[] {
        // InfusionRecipes.recipeA,
        // InfusionRecipes.recipeB
        // })
        //
        // 完整节点示例：
        // new ResearchItem(
        // "THAUMICSURVIVAL_XXX",
        // "THAUMICSURVIVAL",
        // TCAspects.of(TCAspects.MAGIC, 8),
        // 2, 4, 2,
        // TCBlocks.anvil())
        // .setParents("THAUMICSURVIVAL_SIMPLE_ANVIL")
        // .setParentsHidden(TCResearchHelper.RESEARCH_INFUSION)
        // .setPages(
        // TCResearchHelper.textPage("THAUMICSURVIVAL_XXX", 1),
        // new ResearchPage(InfusionRecipes.recipeXxx)
        // )
        // .setSpecial()
        // .registerResearchItem();
        //
        // 注意：
        // 动态注魔配方（8 级铁砧、8 级苹果）不要在这里写，
        // 看 Section 4。

        // ============================================================
        // 【Section 4】DynamicInfusionRecipes → 动态注魔配方页
        // ============================================================
        //
        // 配方法在哪：recipes/DynamicInfusionRecipes.java
        // 配方字段：public static List<InfusionRecipe> xxxRecipes;
        //
        // 研究页写法：
        // 不走 new ResearchPage(单个配方)，
        // 走 DynamicResearchPages.buildXxxPages()。
        //
        // 例：
        // .setPages(DynamicResearchPages.buildGreedyApplePages())
        // .setPages(DynamicResearchPages.buildAnvilPages())
        //
        // 为什么走装配层：
        // 动态配方是 List，需要“文本页 + 配方数组页”组合，
        // buildXxxPages() 已经帮你把 List 转成 ResearchPage[]，
        // 你只管调用。
        //
        // 完整节点示例：
        // new ResearchItem(
        // "THAUMICSURVIVAL_ENCHANTED_GAPPLE",
        // "THAUMICSURVIVAL",
        // TCAspects.of(TCAspects.GREED, 16),
        // 5, 0, 3,
        // TCItems.notchApple())
        // .setParents("THAUMICSURVIVAL_SIMPLE_GAPPLE", TCResearchHelper.RESEARCH_INFUSION)
        // .setPages(DynamicResearchPages.buildGreedyApplePages())
        // .setSpecial()
        // .registerResearchItem();
        //
        // 注意：
        // 动态配方页内部用 InfusionRecipe[] 数组构造器，
        // GUI 会循环翻，不用你写多个 ResearchPage。

        // ============================================================
        // 【Section 5】CrucibleRecipes → 坩埚配方页
        // ============================================================
        //
        // 配方法在哪：recipes/CrucibleRecipes.java
        // 配方字段：
        // public static CrucibleRecipe recipeXxx; （单条）
        // 或 CrucibleRecipes.crucibleRecipes （List）
        //
        // 研究页写法（单配方）：
        // new ResearchPage(CrucibleRecipes.recipeXxx)
        //
        // 研究页写法（List 取第 0 个）：
        // new ResearchPage(CrucibleRecipes.crucibleRecipes.get(0))
        //
        // 研究页写法（List 全部塞一页）：
        // new ResearchPage(
        // CrucibleRecipes.crucibleRecipes.toArray(new CrucibleRecipe[0]))
        //
        // 完整节点示例：
        // new ResearchItem(
        // "THAUMICSURVIVAL_XXX",
        // "THAUMICSURVIVAL",
        // TCAspects.of(TCAspects.MAGIC, 8),
        // 3, 0, 1,
        // TCItems.diamond())
        // .setParents("THAUMICSURVIVAL_MASTER")
        // .setPages(
        // TCResearchHelper.textPage("THAUMICSURVIVAL_XXX", 1),
        // new ResearchPage(CrucibleRecipes.recipeXxx)
        // )
        // .registerResearchItem();

        // ============================================================
        // 【Section 6】文本页（通用，和配方类无关）
        // ============================================================
        //
        // 文本页只显示文字，不绑配方。
        // 每个研究节点都可以有文本页，放在配方页前面或后面。
        //
        // 用法：
        // TCResearchHelper.textPage("你的研究KEY", 1)
        //
        // 语言文件 key：
        // tc.research_page.你的研究KEY.1=第一页文字
        // tc.research_page.你的研究KEY.2=第二页文字
        //
        // 注意：
        // 字符串里只写 key，中文写在语言文件里。
        // 语言文件里用 <BR> 换行，不是 \n。

        // ============================================================
        // 【Section 7】研究节点注册参数（通用）
        // ============================================================
        //
        // 每个节点都走这个模板，按配方类选 setPages 的内容。
        //
        // new ResearchItem(
        // "研究KEY", // ① 唯一 key
        // "分类KEY", // ② 你的分类
        // TCAspects.of(TCAspects.MAGIC, 8), // ③ 连连看要素
        // x, y, 难度, // ④⑤⑥ 坐标 + 难度(1~3)
        // TCItems.diamond()) // ⑦ 图标
        // .setParents("前置KEY") // ⑧ 可见前置（画线）
        // .setParentsHidden(TCResearchHelper.RESEARCH_INFUSION) // ⑨ 隐藏前置（不画线）
        // .setSiblings("兄弟KEY") // ⑩ 兄弟节点（并排）
        // .setPages( // ⑪ 页面，按配方类选
        // TCResearchHelper.textPage("你的研究KEY", 1),
        // new ResearchPage(对应配方类的字段或方法)
        // )
        // .setConcealed() // ⑫ 前置没完成时隐藏
        // .setSpecial() // ⑬ 尖刺边框（里程碑）
        // .setRound() // ⑭ 圆形边框（基础）
        // .setAutoUnlock() // ⑮ 自动解锁
        // .setStub() // ⑯ 占位节点
        // .setSecondary() // ⑰ 次要节点
        // .registerResearchItem(); // ⑱ 真正注册
        //
        // 注意：
        // 前置 key 拼错不会崩，但节点永远不显示。
        // 研究 key 必须和配方的第一个参数一致，否则配方不认。

        // ============================================================
        // 【Section 8】扭曲值（通用）
        // ============================================================
        //
        // 节点注册之后调用：
        // TCResearchHelper.warp("你的研究KEY", 2);
        //
        // 给物品加扭曲：
        // TCResearchHelper.warpItem(TCItems.diamond(), 1);
        //
        // 数字原版一般 1~5。

        // ============================================================
        // 【Section 9】语言文件 key 规则（通用）
        // ============================================================
        //
        // 节点名字：
        // tc.research_name.你的研究KEY=节点名字
        // 节点简介：
        // tc.research_text.你的研究KEY=节点简介
        // 文本页：
        // tc.research_page.你的研究KEY.1=第一页文字
        // tc.research_page.你的研究KEY.2=第二页文字
        //
        // 注意：
        // key 必须和研究 key 完全一致，大小写敏感。
        // 换行用 <BR>，不是 \n。

    }

    // =========================================================
    // 👇 【镜像研究注册入口】 👇
    // =========================================================
    // 在 CommonProxy.postInit() 里调用 TSResearchRegistry.postInit()。
    // 只有到了后初始化阶段，神秘时代才加载完了原版节点，这时候才能开镜子。
    //
    // 使用示例（取消注释即可）：
    //
    // TCResearchHelper.mirror(
    // "MIRROR_ELEMENTALPICK", // 新Key
    // TCResearchHelper.CATEGORY_MY_MOD, // 放到你的分类里
    // TCResearchHelper.CATEGORY_ARTIFICE, // 原版分类（奥术）
    // "ELEMENTALPICK", // 原版研究 Key（炽心镐）
    // 10, 10, // 坐标（别和已有节点重叠）
    // TCItems.pickElemental()); // 图标
    //
    // 注意：
    // 1. originalCategory 和 originalKey 必须和 ConfigResearch 里一字不差。
    // 2. 图标必须自己给，镜像不会借原研究的图标。
    // 3. 原研究不存在时镜像自动隐藏，不崩。
    // =========================================================
    public static void postInit() {
        // 把原版的“注魔”节点，镜像到我们的分类里
        TCResearchHelper.mirror(
            "MIRROR_INFUSION", // 镜像的新 Key
            "THAUMICSURVIVAL", // ★ 用你刚改的新常量名
            TCResearchHelper.CATEGORY_ARTIFICE, // 原版注魔在奥术（ARTIFICE）分类
            "INFUSION", // 原版研究 Key（注魔）
            3,
            4, // 坐标（找个空地放，比如铁砧旁边）
            TCBlocks.infusionMatrix() // 图标（用符文矩阵）
        );

    }

}
