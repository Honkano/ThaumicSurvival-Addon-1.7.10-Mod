package com.kongtai.thaumicsurvival.thaumcraftwork.research;

import net.minecraft.util.ResourceLocation;

import com.kongtai.thaumicsurvival.api.thaumcraft.tcapiusetools.TCAspects;
import com.kongtai.thaumicsurvival.api.thaumcraft.tcapiusetools.TCBlocks;
import com.kongtai.thaumicsurvival.api.thaumcraft.tcapiusetools.TCItems;
import com.kongtai.thaumicsurvival.api.thaumcraft.thaumcrafttools.Research;
import com.kongtai.thaumicsurvival.api.thaumcraft.thaumcrafttools.TCResearchHelper;
import com.kongtai.thaumicsurvival.api.tsapi.TSItems;
import com.kongtai.thaumicsurvival.thaumcraftwork.recipes.MultiblockStructures;

import thaumcraft.api.research.ResearchCategories;

/**
 * ============================================================
 * 【神秘生存学 · 研究注册总站】
 * ============================================================
 * 大肥鱼倾情重写，全部使用最新 Research.of() 丝滑写法。
 * ============================================================
 */
public class TSResearchRegistry {

    public static void init() {
        // ============================================================
        // 👇 1. 注册自定义分类
        // ============================================================
        ResearchCategories.registerCategory(
            TCResearchHelper.CATEGORY_THAUMICSURVIVAL,
            new ResourceLocation("thaumicsurvival", "textures/gui/TSResearchmain/TSiconpng.png"),
            new ResourceLocation("thaumicsurvival", "textures/gui/TSResearchmain/gui_researchback.png"));

        // ============================================================
        // 👇 2. 注册所有研究节点（宝宝级图文说明）
        // ============================================================

        // 【节点 01】主节点
        Research.of("THAUMICSURVIVAL_MASTER")
            .at(0, 0) // 坐标：正中心
            .tier(1) // 难度：1
            .icon(new ResourceLocation("thaumicsurvival", "textures/gui/TSResearchmain/page/main.png")) // 图标：使用贴图
            .autoUnlock() // 开关：进游戏直接送
            .stub() // 开关：空壳节点（没配方）
            .round() // 开关：圆形边框
            .textPages(2) // 页面：自动生成 tc.research_page.THAUMICSURVIVAL_MASTER.1 和 .2
            .build(); // 组装并注册！

        // 【节点 02】简易黑曜石
        Research.of("THAUMICSURVIVAL_SIMPLE_OBSIDIAN")
            .at(0, -4)
            .tier(1)
            .aspects(TCAspects.of(TCAspects.EARTH, 8, TCAspects.FIRE, 8, TCAspects.WATER, 4)) // 要素：地8 火8 水4
            .icon(TCBlocks.obsidian()) // 图标：用物品
            .parents("THAUMICSURVIVAL_MASTER") // 前置：必须解锁主节点
            .textPages(2)
            // 加点配方页：引用无序配方
            .arcanePage()
            .build();

        // 【节点 03】简易金苹果
        Research.of("THAUMICSURVIVAL_SIMPLE_GAPPLE")
            .at(2, -11)
            .tier(1)
            .aspects(TCAspects.of(TCAspects.GREED, 64, TCAspects.LIFE, 32, TCAspects.FIRE, 64, TCAspects.MAGIC, 16))
            .icon(TSItems.arcaneApple())
            .parents("THAUMICSURVIVAL_SIMPLE_ANVIL")
            .textPages(1) // 只有 1 页文本
            .arcanePage()
            .concealed() // 开关：前置未完成时隐藏（连灰影都看不见）
            .build();

        // 【节点 04】简易铁砧
        Research.of("THAUMICSURVIVAL_SIMPLE_ANVIL")
            .at(3, -7)
            .tier(1)
            .aspects(TCAspects.of(TCAspects.CRAFT, 8, TCAspects.METAL, 8, TCAspects.TOOL, 4))
            .icon(TCBlocks.anvil())
            .parents("THAUMICSURVIVAL_SIMPLE_OBSIDIAN")
            .textPages(1)
            .arcanePage()
            .concealed()
            .build();

        // 【节点 05】注魔铁砧
        Research.of("THAUMICSURVIVAL_INFUSION_ANVIL")
            .at(7, -8)
            .tier(2)
            .aspects(TCAspects.of(TCAspects.CRAFT, 16, TCAspects.METAL, 16, TCAspects.TOOL, 8, TCAspects.MAGIC, 8))
            .icon(TCBlocks.anvil())
            .parents("THAUMICSURVIVAL_SIMPLE_ANVIL", "MIRROR_INFUSION") // 可见前置
            .parentsHidden(TCResearchHelper.RESEARCH_INFUSION) // 隐藏前置（原版注魔，不画线）
            // 动态页面：直接塞入 DynamicResearchPages 生成好的页面数组
            .textPages(2)
            .infusionPage()
            .concealed()
            .special() // 开关：带刺六边形
            .build();

        // 【节点 06】魔铸金苹果
        Research.of("THAUMICSURVIVAL_ENCHANTED_GAPPLE")
            .at(6, -11)
            .tier(3) // 难度 3
            .aspects(TCAspects.of(TCAspects.GREED, 16, TCAspects.MAGIC, 16, TCAspects.LIFE, 12, TCAspects.HUNGER, 8))
            .icon(TSItems.greedApple(7)) // 用 7 级苹果当图标
            .parents("THAUMICSURVIVAL_SIMPLE_GAPPLE", "MIRROR_INFUSION")
            .parentsHidden(TCResearchHelper.RESEARCH_INFUSION)
            .textPages(3)
            .infusionPage()
            .concealed()
            .special()
            .build();

        // 【节点 07】简易附魔台（4级极高难度）
        Research.of("THAUMICSURVIVAL_SIMPLE_ENCHANTING_TABLE")
            .at(0, -6)
            .tier(4) // 难度 4，极其庞大的拼图！
            .aspects(
                TCAspects.of(
                    TCAspects.MAGIC,
                    16,
                    TCAspects.MIND,
                    12,
                    TCAspects.CRAFT,
                    10,
                    TCAspects.TOOL,
                    10,
                    TCAspects.ORDER,
                    8,
                    TCAspects.GREED,
                    6,
                    TCAspects.ENERGY,
                    6))
            .icon(TCBlocks.enchantingTable())
            .parents("THAUMICSURVIVAL_SIMPLE_OBSIDIAN", "MIRROR_INFUSIONENCHANTMENT")
            .textPages(2)
            .arcanePage()
            .concealed()
            .build();

        // 【节点 08】黑曜石图腾（可购买）
        Research.of("THAUMICSURVIVAL_OBSIDIAN_TOTEM")
            .at(-2, -4)
            .tier(2)
            .aspects(TCAspects.of(TCAspects.MAGIC, 10, TCAspects.DARKNESS, 8)) // 这是购买价格
            .icon(TCBlocks.obsidianTotem())
            .parents("THAUMICSURVIVAL_SIMPLE_OBSIDIAN", "MIRROR_INFUSIONENCHANTMENT")
            .textPages(3) // 生成 1, 2, 3 三页
            .cruciblePage() // 👈 终极无参大招，自动去货架拿配方！
            .secondary() // 开关：次要节点，花要素直接买通！
            .concealed()
            .build();

        // ============================================================
        // 🧪 【临时测试节点】：用来测试要素展示页和你的 Mixin
        // ============================================================
        Research.of("THAUMICSURVIVAL_TEST_NODE_DELETE_ME")
            .at(10, 10) // 放在偏僻的角落，防撞车
            .tier(1)
            .icon(TCItems.diamond()) // 随便给个图标
            .parents("THAUMICSURVIVAL_MASTER")
            .textPages(1) // 第 1 页是普通文字
            // 👇 第 2 页：测试要素展示页！
            // 这里放了 10 点魔力、8 点火焰、5 点水
            .aspectsPage(TCAspects.of(TCAspects.MAGIC, 10, TCAspects.FIRE, 8, TCAspects.WATER, 5))
            .build();

        // ============================================================
        // 🧪 【测试节点】多方块结构展示（用来跑游戏看效果的）
        // ============================================================
        Research.of("THAUMICSURVIVAL_TEST_MULTIBLOCK")
            .at(12, 12) // 找个没人的角落
            .tier(2)
            .icon(TCBlocks.infusionMatrix()) // 图标用符文矩阵
            .parents("THAUMICSURVIVAL_MASTER") // 前置要求主节点
            .textPages(1) // 给一页文本（你可以随便写点啥）
            // 👇 直接用施工队（MultiblockStructures）里的图纸！
            .multiblock(MultiblockStructures.getTestAltar())
            .build();

        // ============================================================
        // 👇 3. 添加扭曲值
        // ============================================================
        TCResearchHelper.warp("THAUMICSURVIVAL_SIMPLE_GAPPLE", 3);
        TCResearchHelper.warp("THAUMICSURVIVAL_ENCHANTED_GAPPLE", 10);
        TCResearchHelper.warp("THAUMICSURVIVAL_OBSIDIAN_TOTEM", 5);
    }

    // ============================================================
    // 👇 4. 镜像研究（postInit 阶段）
    // ============================================================
    // TODO: 明天把这块代码迁移到独立的研究类里，现在先放在这里。
    // ============================================================
    public static void postInit() {
        // 注魔：手动给符文矩阵作为物品图标
        TCResearchHelper.mirror(
            "MIRROR_INFUSION",
            TCResearchHelper.CATEGORY_THAUMICSURVIVAL,
            TCResearchHelper.CATEGORY_ARTIFICE,
            TCResearchHelper.RESEARCH_INFUSION,
            4,
            -9,
            TCBlocks.infusionMatrix());

        // 注魔附魔：不传图标，镜子自己照原研究的纹理图标
        TCResearchHelper.mirror(
            "MIRROR_INFUSIONENCHANTMENT",
            TCResearchHelper.CATEGORY_THAUMICSURVIVAL,
            TCResearchHelper.CATEGORY_ARTIFICE,
            TCResearchHelper.RESEARCH_INFUSIONENCHANTMENT,
            -2,
            -6);
    }

    /*
     * ============================================================
     * // TODO: 👇👇👇 【Research API 终极小抄（宝宝都能看懂版）】 👇👇👇
     * ============================================================
     * 【一、基础属性（搭骨架）】
     * Research.of("KEY") 必须写在第一行！KEY 必须和配方里的一字不差。
     * .category("分类KEY") 极少用，因为默认就是放在咱们的 THAUMICSURVIVAL 里。
     * .at(x, y) 坐标。原版是 24 像素一格，注意别和别的节点撞车。
     * .tier(难度) 1~3 正常，4 是究极折磨王（拼图会极其巨大）。
     * .aspects(AspectList) 连连看消耗的要素。比如 TCAspects.of(TCAspects.MAGIC, 8)。
     * 【二、图标（脸面）】
     * .icon(ItemStack) 用物品当图标，比如 .icon(TCBlocks.obsidian())。
     * .icon(ResourceLocation) 用贴图当图标，比如 .icon(new ResourceLocation("xxx.png"))。
     * 【三、页面（书里翻的内容）】
     * .textPages(2) 自动生成纯文本页，比如生成 .1 和 .2 两页。
     * .pages(ResearchPage...) 手动塞页面，比如 .pages(new ResearchPage(配方))。
     * 【四、高级页面黑科技】
     * .aspectsPage(AspectList) 专门展示一堆要素图标的页面（适合做纯文字说明）。
     * .smeltingPage(ItemStack) 自动去查熔炉配方，画出“A烧成B”的页面。
     * .multiblock(recipe...) 多方块展示页，传 ShapedArcaneRecipe，从下往上传，内部自动反转！
     * 【五、前置与关联（怎么解锁）】
     * .parents("KEY1", "KEY2") 可见前置：必须解锁这些，才能看到它，并且会画连线。
     * .parentsHidden("KEY") 隐藏前置：必须解锁它，但是不画连线（防止连线太乱）。
     * .siblings("KEY") 兄弟节点：和谁是一起解锁的，会并排显示。
     * 【六、状态开关（加特效）】
     * .round() 圆形边框（适合基础被动、无需合成）。
     * .special() 带刺六边形边框（适合终极科技、危险黑暗魔法）。
     * .concealed() 隐藏轮廓（前置没解锁时，连灰色影子都不显示）。
     * .secondary() 次要节点（允许玩家花研究点直接买通，不用拼图）。
     * .autoUnlock() 进游戏直接送给玩家（通常只给根节点用）。
     * .stub() 空壳存根（没有页面，没有内容，纯占位用）。
     * 【七、终极冷门隐藏开关】
     * .hidden() 彻底隐身：必须靠扫描物品/实体/要素才能点亮。
     * .lost() 失落节点：没点亮前显示为乱码，点亮才显示真名。
     * .virtual() 完全隐形：玩家根本看不见它！常用于纯后台解锁配方。
     * 【八、触发器（配合 hidden 和 lost 使用）】
     * .itemTriggers(stack...) 扫描指定物品才会解锁（探险必用）。
     * .entityTriggers("名字") 扫描指定实体才解锁（比如 "Zombie"）。
     * .aspectTriggers(Aspect...) 扫描指定要素才解锁（比如 TCAspects.FIRE）。
     * 【九、最后一步】
     * .build(); 必须加在链式调用的最后！不写这个等于白写！
     * ============================================================
     * ⚠️ 【避坑指南】
     * 1. 研究 KEY 绝对不能和原版或其他模组撞车，否则原版的会被覆盖。
     * 2. 如果两个节点坐标重叠，A 哥的底层会直接报红字错误并拒绝注册第二个节点！
     * 3. 如果用了 .virtual() 隐形开关，就可以无视坐标重叠，随便塞。
     * ============================================================
     */
}
