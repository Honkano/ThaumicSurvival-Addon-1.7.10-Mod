package com.kongtai.thaumicsurvival.thaumcraftwork.recipes;

import com.kongtai.thaumicsurvival.api.thaumcraft.tcapiusetools.TCAspects;
import com.kongtai.thaumicsurvival.api.thaumcraft.tcapiusetools.TCBlocks;
import com.kongtai.thaumicsurvival.api.thaumcraft.tcapiusetools.TCItems;
import com.kongtai.thaumicsurvival.api.thaumcraft.thaumcrafttools.TCRecipes;
import com.kongtai.thaumicsurvival.api.tsapi.TSItems;

import thaumcraft.api.aspects.AspectList;

/**
 * ============================================================
 * 【有序奥术配方管理类（终极保姆级·分拣机版）】
 * ============================================================
 * 专门放奥术工作台的“有序”配方（必须按九宫格摆好）。
 * 
 * 【⭐⭐⭐ 绝对红线：奥术工作台只能用六大原始要素！】
 * 绝对不能写：MAGIC, LIFE, GREED, MIND 等复合要素！
 * 只能写：AIR(风), EARTH(地), FIRE(火), WATER(水), ORDER(秩序), ENTROPY(混沌)！
 * 
 * 【⭐ 架构大升级】
 * 不需要在顶部写 public static 变量了！
 * 你只要在这里调 TCRecipes.arcaneShaped(...)，底层自动塞进分拣机。
 * 在研究节点里写一句 .arcanePage()，书里就会自动抓取并排版。
 * ============================================================
 */
public class ArcaneShapedRecipes {

    public static void init() {

        // ==========================================
        // 👇 示例 1：简易金苹果（全要素均衡消耗）
        // ==========================================
        AspectList visCostApple = TCAspects.of(
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

        TCRecipes.arcaneShaped(
            "THAUMICSURVIVAL_SIMPLE_GAPPLE", // 研究 Key
            TSItems.arcaneApple(), // 输出物品
            visCostApple, // 魔力消耗
            new Object[] { " G ", "GAG", " G ", 'G', TCItems.goldIngot(), // G 代表金锭
                'A', TCItems.apple() // A 代表苹果
            });

        // ==========================================
        // 👇 示例 2：简易铁砧（主打土、火、秩序）
        // ==========================================
        AspectList visCostAnvil = TCAspects.of(
            TCAspects.EARTH,
            12, // 大地：绝对的主导
            TCAspects.FIRE,
            6, // 火焰：打铁要有火
            TCAspects.ORDER,
            4 // 秩序：规整的砧板
        );

        TCRecipes.arcaneShaped(
            "THAUMICSURVIVAL_SIMPLE_ANVIL",
            TCBlocks.anvil(),
            visCostAnvil,
            new Object[] { " A ", "BBB", 'A', TCBlocks.ironBlock(), // A 代表铁块
                'B', TCItems.ironIngot() // B 代表铁锭
            });

        // ==========================================
        // 👇 示例 3：简易附魔台（微量全属性消耗）
        // ==========================================
        AspectList visCostEnchant = TCAspects.of(
            TCAspects.EARTH,
            2,
            TCAspects.FIRE,
            2,
            TCAspects.WATER,
            2,
            TCAspects.AIR,
            2,
            TCAspects.ORDER,
            2,
            TCAspects.ENTROPY,
            2);

        TCRecipes.arcaneShaped(
            "THAUMICSURVIVAL_SIMPLE_ENCHANTING_TABLE",
            TCBlocks.enchantingTable(),
            visCostEnchant,
            new Object[] { " B ", "POP", "PDP", 'B', TCItems.book(), 'P', TCItems.paper(), 'O', TCBlocks.obsidian(),
                'D', TCItems.diamond() });
    }
}

/*
 * ============================================================
 * 👇👇👇 【狗狗级奥术有序配方小抄】 👇👇👇
 * ============================================================
 * 【小抄 1：以后怎么加新配方？】
 * 直接照着上面的例子，改掉字符串网格，改掉 Key，改掉输出。
 * 不需要去理会变量声明！
 * 【小抄 2：九宫格怎么画？】
 * 必须是一个 3x3 的数组，类似这样：
 * new Object[] {
 * "ABA",
 * "BCB",
 * "ABA",
 * 'A', 物品1,
 * 'B', 物品2,
 * 'C', 物品3
 * }
 * 空格就代表这里不需要放东西。
 * 【小抄 3：书里怎么显示？】
 * 在 TSResearchRegistry 里：
 * Research.of("你的KEY")
 * .textPages(1)
 * .arcanePage() // 👈 自动抓取！如果同一个节点有多个配方，会自动做成幻灯片！
 * .build();
 * 【小抄 4：魔力消耗（visCost）能填什么？】
 * 只能是六大原始要素（AIR, EARTH, FIRE, WATER, ORDER, ENTROPY）。
 * 填复合要素（比如 MAGIC）会导致工作台消耗魔力异常！
 * ============================================================
 */
