package com.kongtai.thaumicsurvival.api.thaumcraft.thaumcrafttools;

import net.minecraft.item.ItemStack;

import thaumcraft.api.research.ResearchPage;

/**
 * ============================================================
 * 【神秘时代研究节点工具类】
 * ============================================================
 * 用途：把研究节点创建、文本页命名、镜像研究封装一下，
 * 写研究注册代码时少点重复字符串拼接。
 *
 * 【设计原则】
 * 1. 不做过度封装。研究节点参数太多，硬封反而更难读。
 * 2. 只封装两个真正痛的点：
 * - textPage：不用再手拼 "tc.research_page.XXX.N"
 * - mirror：不用再记 MirrorResearchItem 的参数顺序
 * 3. 其他一切照旧 new ResearchItem(...) 链式调用。
 * ============================================================
 */
public class TCResearchHelper {

    // ============================================================
    // 【文本页】
    // ============================================================

    /**
     * 生成一个文本页。
     *
     * 等价于：
     * new ResearchPage("tc.research_page." + researchKey + "." + pageNum)
     *
     * 例：
     * textPage("THAUMICSURVIVAL_XXX", 1)
     * → new ResearchPage("tc.research_page.THAUMICSURVIVAL_XXX.1")
     */
    public static ResearchPage textPage(String researchKey, int pageNum) {
        return new ResearchPage("tc.research_page." + researchKey + "." + pageNum);
    }

    /**
     * 生成一组文本页（从第 1 页到第 count 页）。
     *
     * 例：
     * textPages("THAUMICSURVIVAL_XXX", 3)
     * → { 第1页, 第2页, 第3页 }
     */
    public static ResearchPage[] textPages(String researchKey, int count) {
        ResearchPage[] pages = new ResearchPage[count];
        for (int i = 0; i < count; i++) {
            pages[i] = textPage(researchKey, i + 1);
        }
        return pages;
    }

    /**
     * 生成一个条件显示的文本页（只有解锁了 requiredResearch 才显示）。
     *
     * 等价于：
     * new ResearchPage(requiredResearch, "tc.research_page." + researchKey + "." + pageNum)
     */
    public static ResearchPage textPageConcealed(String requiredResearch, String researchKey, int pageNum) {
        return new ResearchPage(requiredResearch, "tc.research_page." + researchKey + "." + pageNum);
    }

    // ============================================================
    // 【镜像研究】
    // ============================================================

    /**
     * 创建一个镜像研究节点并直接注册。
     *
     * 镜像研究 = 把原版/其他模组的研究节点，复制一份到你的分类里，
     * 文本和配方都借用原研究，原版更新镜像自动跟着更新。
     *
     * @param newKey           新的研究 key（你的分类里唯一）
     * @param newCategory      你的分类 key（比如 "THAUMICSURVIVAL"）
     * @param originalCategory 原研究所在分类（比如 "ARTIFICE"）
     * @param originalKey      原研究 key（比如 "ELEMENTALPICK"）
     * @param col              坐标 x
     * @param row              坐标 y
     * @param icon             图标物品（必须自己给）
     *
     *                         注意：
     *                         必须在 postInit 阶段调用，因为 init 阶段原研究还没加载完。
     *
     *                         例：
     *                         mirror("MIRROR_PICK", "THAUMICSURVIVAL",
     *                         "ARTIFICE", "ELEMENTALPICK",
     *                         3, 2, TCItems.pickElemental());
     */
    public static void mirror(String newKey, String newCategory, String originalCategory, String originalKey, int col,
        int row, ItemStack icon) {
        new com.kongtai.thaumicsurvival.thaumcraftwork.research.MirrorResearchItem(
            newKey,
            newCategory,
            originalCategory,
            originalKey,
            col,
            row,
            icon).registerResearchItem();
    }

    // ============================================================
    // 【扭曲值快捷方式】
    // ============================================================

    /**
     * 给研究加扭曲。
     *
     * 等价于：
     * ThaumcraftApi.addWarpToResearch(researchKey, warp)
     */
    public static void warp(String researchKey, int warp) {
        thaumcraft.api.ThaumcraftApi.addWarpToResearch(researchKey, warp);
    }

    /**
     * 给物品加扭曲。
     *
     * 等价于：
     * ThaumcraftApi.addWarpToItem(stack, warp)
     */
    public static void warpItem(ItemStack stack, int warp) {
        thaumcraft.api.ThaumcraftApi.addWarpToItem(stack, warp);
    }

    // ============================================================
    // 【常量：研究分类 key】
    // ============================================================
    // 避免手拼字符串拼错分类名。
    // ============================================================

    /** 你自己的分类 */
    public static final String CATEGORY_MY_MOD = "THAUMICSURVIVAL";
    /** 原版分类：基本信息 */
    public static final String CATEGORY_BASICS = "BASICS";
    /** 原版分类：神秘学 */
    public static final String CATEGORY_THAUMATURGY = "THAUMATURGY";
    /** 原版分类：炼金术 */
    public static final String CATEGORY_ALCHEMY = "ALCHEMY";
    /** 原版分类：炼化学 */
    public static final String CATEGORY_ARTIFICE = "ARTIFICE";
    /** 原版分类：傀儡学 */
    public static final String CATEGORY_GOLEMANCY = "GOLEMANCY";
    /** 原版分类：邪术学 */
    public static final String CATEGORY_ELDRITCH = "ELDRITCH";

    // ============================================================
    // 【常量：原版常用研究 key】
    // ============================================================
    // 设置 parentsHidden / 条件页面时常用，避免拼错。
    // ============================================================

    /** 注魔（原始研究） */
    public static final String RESEARCH_INFUSION = "INFUSION";
    /** 神秘学基础（相当于根节点） */
    public static final String RESEARCH_BASICTHAUMATURGY = "BASICTHAUMATURGY";
    /** 神秘锭 */
    public static final String RESEARCH_THAUMIUM = "THAUMIUM";
    /** 炼化学基础 */
    public static final String RESEARCH_BASICARTIFACE = "BASICARTIFACE";
    /** 要素蒸馏 */
    public static final String RESEARCH_DISTILESSENTIA = "DISTILESSENTIA";
    /** 坩埚 */
    public static final String RESEARCH_CRUCIBLE = "CRUCIBLE";
    /** 魔导透镜 */
    public static final String RESEARCH_THAUMOMETER = "THAUMOMETER";
    /** 揭示之护目镜 */
    public static final String RESEARCH_GOGGLES = "GOGGLES";
    /** 傀儡学基础（稻草傀儡） */
    public static final String RESEARCH_GOLEMSTRAW = "GOLEMSTRAW";
    /** 符文护盾 */
    public static final String RESEARCH_RUNICARMOR = "RUNICARMOR";
    /** 虚空金属 */
    public static final String RESEARCH_VOIDMETAL = "VOIDMETAL";
    /** 邪术揭谛（邪术学根节点） */
    public static final String RESEARCH_ELDRITCHMINOR = "ELDRITCHMINOR";
}
