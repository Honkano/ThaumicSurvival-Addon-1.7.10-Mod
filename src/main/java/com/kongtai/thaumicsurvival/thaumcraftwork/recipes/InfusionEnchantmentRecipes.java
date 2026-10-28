package com.kongtai.thaumicsurvival.thaumcraftwork.recipes;

/**
 * ============================================================
 * 【注魔附魔配方管理类（终极保姆级·万花筒版）】
 * ============================================================
 * 这个类专门放“注魔附魔”配方。
 * 
 * 【⭐ 架构大升级（不需要写变量了）】
 * 以前你要在顶部写 public static InfusionEnchantmentRecipe recipeXxx;
 * 现在完全不需要！直接调 TCRecipes.infusionEnchant(...)，
 * 底层自动把它塞进专属货架（ENCHANT_MAP）里。
 * ============================================================
 */
public class InfusionEnchantmentRecipes {

    public static void init() {

        // =================================================================
        // 👇 下面是各种附魔配方的“万花筒组合”示例（全部已注释，按需抄写）
        // =================================================================

        // ==========================================
        // 【组合 1】：最基础的“单附魔”（比如耐久）
        // ==========================================
        /*
         * TCRecipes.infusionEnchant(
         * "THAUMICSURVIVAL_ENCHANT_UNBREAKING", // ① 研究 Key（必须和研究节点一字不差）
         * Enchantment.unbreaking, // ② 原版的附魔对象（耐久）
         * 2, // ③ 不稳定度（安全范围 1~5）
         * TCAspects.of(TCAspects.TOOL, 16, TCAspects.CRAFT, 8), // ④ 源质消耗（工具16，工艺8）
         * new ItemStack[] { // ⑤ 基座材料（4个，对称摆放）
         * TCItems.salisMundus(),
         * TCItems.nitor(),
         * TCItems.nuggetThaumium(),
         * TCItems.nuggetThaumium()
         * }
         * );
         */

        // ==========================================
        // 【组合 2】：最基础的“单附魔”（比如时运）
        // ==========================================
        /*
         * TCRecipes.infusionEnchant(
         * "THAUMICSURVIVAL_ENCHANT_FORTUNE",
         * Enchantment.fortune, // 附魔对象（时运）
         * 3,
         * TCAspects.of(TCAspects.GREED, 32, TCAspects.METAL, 16),
         * new ItemStack[] {
         * TCItems.salisMundus(),
         * TCItems.goldIngot(),
         * TCItems.goldIngot(),
         * TCItems.diamond()
         * }
         * );
         */

        // ==========================================
        // 【组合 3】：武器专属附魔（比如锋利），8个基座材料拉满
        // ==========================================
        /*
         * TCRecipes.infusionEnchant(
         * "THAUMICSURVIVAL_ENCHANT_SHARPNESS",
         * Enchantment.sharpness, // 附魔对象（锋利）
         * 4, // 武器附魔稍微危险一点
         * TCAspects.of(TCAspects.WEAPON, 32, TCAspects.ENERGY, 16, TCAspects.METAL, 16),
         * new ItemStack[] { // 8个材料（每个基座放2个）
         * TCItems.salisMundus(), TCItems.salisMundus(),
         * TCItems.thaumiumIngot(), TCItems.thaumiumIngot(),
         * TCItems.quicksilver(), TCItems.quicksilver(),
         * TCItems.shardEntropy(), TCItems.shardEntropy()
         * }
         * );
         */

        // ==========================================
        // 【组合 4】：防具专属附魔（比如保护）
        // ==========================================
        /*
         * TCRecipes.infusionEnchant(
         * "THAUMICSURVIVAL_ENCHANT_PROTECTION",
         * Enchantment.protection, // 附魔对象（保护）
         * 2,
         * TCAspects.of(TCAspects.ARMOR, 32, TCAspects.LIFE, 16),
         * new ItemStack[] {
         * TCItems.salisMundus(),
         * TCItems.enchantedFabric(), // 魔力布匹
         * TCItems.enchantedFabric(),
         * TCItems.amber()
         * }
         * );
         */

        // ==========================================
        // 【组合 5】：使用本模组（TSItems）的物品做材料
        // ==========================================
        /*
         * TCRecipes.infusionEnchant(
         * "THAUMICSURVIVAL_ENCHANT_INFINITY", // 比如无尽附魔
         * Enchantment.infinity, // 附魔对象（无限）
         * 10, // 极高的危险度
         * TCAspects.of(TCAspects.MAGIC, 64, TCAspects.VOID, 32, TCAspects.GREED, 64),
         * new ItemStack[] {
         * TSItems.arcaneApple(), // 用你的秘源金苹果当材料
         * TCItems.primalCharm(), // 元始魔力
         * TCItems.voidMetalIngot(), // 虚空锭
         * TCItems.voidMetalIngot()
         * }
         * );
         */

        // ==========================================
        // 【组合 6】：给同一个研究节点塞“多个附魔”
        // ==========================================
        // 比如你想做一个“全能武器强化”节点，同时解锁耐久和锋利。
        // 你只需要在配方这里把两个都注册了，研究 Key 填一样的。
        /*
         * TCRecipes.infusionEnchant(
         * "THAUMICSURVIVAL_ENCHANT_ALL_WEAPON", // Key 叫全能武器强化
         * Enchantment.unbreaking, 2, TCAspects.of(TCAspects.TOOL, 16),
         * new ItemStack[] { TCItems.salisMundus(), TCItems.nitor() }
         * );
         * TCRecipes.infusionEnchant(
         * "THAUMICSURVIVAL_ENCHANT_ALL_WEAPON", // Key 一模一样
         * Enchantment.sharpness, 4, TCAspects.of(TCAspects.WEAPON, 32),
         * new ItemStack[] { TCItems.salisMundus(), TCItems.thaumiumIngot() }
         * );
         */
    }
}

/*
 * ============================================================
 * 👇👇👇 【狗狗级避坑小抄（写新配方时必看）】 👇👇👇
 * ============================================================
 * 【小抄 1：研究书里怎么画出来？（无循环 vs 有循环）】
 * 🔹 方案 A（全自动抓取）：
 * 因为 A 哥底层不支持附魔数组（会报 undefined），所以咱们底层写了一个 for 循环，
 * 帮你把所有附魔拆成一页一个。
 * Research.of("你的KEY")
 * .textPages(1)
 * .enchantPage() // 👈 自动抓取所有 KEY 相同的附魔，自动分页
 * .build();
 * 🔹 方案 B（手动指定，不循环）：
 * 你就可以这样手动指定塞进去（每页一个）：
 * .pages(
 * new ResearchPage(InfusionEnchantmentRecipes.recipeXxx),
 * new ResearchPage(InfusionEnchantmentRecipes.recipeYyy)
 * )
 * ------------------------------------------------------------
 * 【小抄 2：为什么书上的物品会发光？】
 * 只要用了 TCRecipes.infusionEnchant(...)，
 * A 哥的 GUI 一拿到这个配方，就会自动把产出物渲染成带紫色附魔光效的！
 * 完全不需要你写任何渲染代码，这是原版自带的逼格。
 * ------------------------------------------------------------
 * 【小抄 3：附魔等级怎么控制的？】
 * A 哥底层写死了。比如原版耐久上限是 3 级。
 * 玩家如果拿个“耐久 3”的装备去注魔，祭坛会自动判定不匹配，
 * 不会让玩家白白浪费材料。
 * （如果你加了“极限附魔”之类的模组，A 哥会动态去读模组的扩展上限）。
 * ------------------------------------------------------------
 * 【小抄 4：不稳定度填多少？（极度重要）】
 * 注魔附魔往往是给玩家辛辛苦苦打出来的神装用的！
 * 如果你填个 30（高危），结果祭坛炸了，玩家的神剑灰飞烟灭，玩家当场退游！
 * 所以，附魔配方强烈建议填 1 ~ 5 之间，保证安全第一。
 * ------------------------------------------------------------
 * 【小抄 5：基座材料放几个？】
 * 祭坛只有 4 个基座。
 * 所以材料填 4 个（每个放1个）、8 个（每个放2个）、或者 12 个（每个放3个）。
 * 千万不要填 3 个或者 5 个！不然玩家强迫症摆不对称，会来骂你的。
 * ------------------------------------------------------------
 * 【小抄 6：为什么不能像坩埚那样一键全自动到“单页跳舞”？】
 * 因为 A 哥的 ResearchPage 源码里，只写了普通注魔的数组构造器，
 * 没写附魔的数组构造器。如果强行写，游戏直接报 undefined 崩溃。
 * 所以咱们的 .enchantPage() 走的是“物理分页（一页一个）”路线，
 * 虽然不能跳舞，但全自动抓取依然很爽！
 * ============================================================
 */
