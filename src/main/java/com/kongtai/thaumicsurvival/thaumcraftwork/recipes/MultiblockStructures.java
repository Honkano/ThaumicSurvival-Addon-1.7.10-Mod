package com.kongtai.thaumicsurvival.thaumcraftwork.recipes;

import net.minecraft.item.ItemStack;

import com.kongtai.thaumicsurvival.api.thaumcraft.tcapiusetools.TCBlocks;
import com.kongtai.thaumicsurvival.api.thaumcraft.tcapiusetools.TCItems;
import com.kongtai.thaumicsurvival.api.thaumcraft.tcapiusetools.TCWands;
import com.kongtai.thaumicsurvival.api.tsapi.TSItems;

import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.crafting.ShapedArcaneRecipe;

/**
 * ============================================================
 * 【多方块结构图纸库（终极保姆级·防崩溃版）】
 * ============================================================
 * 这个类专门用来画多方块结构（魔导书里的 3D 虚影）。
 * 
 * 【⭐ 核心避坑原则】
 * 1. 必须返回 ShapedArcaneRecipe 数组。
 * 2. 数组的顺序必须是：【从下到上】（底层放第0个，顶层放最后）。
 * 3. 输出的物品（dummyOutput）不能传 null，随便塞个钻石/泥土当占位符，
 * 因为 A 哥渲染底层会去读它，传 null 直接崩溃。
 * 4. 没有方块的位置，强烈建议用 TCBlocks.hole()（隙间/空气墙）占位，
 * 或者直接用空格 " "。注魔桩等复杂 3D 模型可能会引发渲染 Bug，建议用隙间替代。
 * ============================================================
 */
public class MultiblockStructures {

    public static ShapedArcaneRecipe[] getTestAltar() {

        // 👇 占位输出物品，绝对不能传 null，否则渲染空指针崩溃！
        ItemStack dummyOutput = TCItems.diamond();

        // ==========================================
        // 【第一层】：3x3 黑曜石底座（最底层）
        // ==========================================
        // 全是实心方块，没有任何空隙。
        ShapedArcaneRecipe layer2 = new ShapedArcaneRecipe(
            "",
            dummyOutput,
            new AspectList(),
            new Object[] { "OOO", "OOO", "OOO", 'O', TCBlocks.obsidian() });

        // ==========================================
        // 【第二层】：四角放【隙间】，中间放符文矩阵
        // ==========================================
        // 原本四角是注魔桩，但注魔桩渲染容易有 Bug，这里全换成了透明的隙间 'H'。
        ShapedArcaneRecipe layer1 = new ShapedArcaneRecipe(
            "",
            dummyOutput,
            new AspectList(),
            new Object[] { "HHH", // 👈 原本的注魔桩全换成了 H（隙间）
                "HMH", "HHH", 'H', TCWands.staffIronGreatwood(), // 隙间（透明的空气墙）
                'M', TCWands.scepterThaumiumSilverwood() // 符文矩阵
            });

        // ==========================================
        // 【第三层】：四角神秘块，中间放 2D 苹果，其余全是隙间
        // ==========================================
        // 在这里，隙间 'H' 充当了虚拟的框架，让神秘块和苹果看起来像悬浮在空中。
        ShapedArcaneRecipe layer0 = new ShapedArcaneRecipe(
            "",
            dummyOutput,
            new AspectList(),
            new Object[] { "THT", "HAH", "THT", 'T', TCWands.wandBlazeGold(), // 3D 方块：神秘块
                'A', TSItems.greedApple(7), // 2D 物品：魔铸金苹果
                'H', TCBlocks.hole() // 隙间（空气墙）
            });

        return new ShapedArcaneRecipe[] { layer2, layer1, layer0 };
    }

    // ============================================================
    // 👇👇👇 【狗狗级多方块写码小抄（写新建筑时必看）】 👇👇👇
    // ============================================================
    //
    // 【小抄 1：最简单的三层塔（全实心方块）】
    // public static ShapedArcaneRecipe[] getTower() {
    // ItemStack dummy = TCItems.diamond();
    // ShapedArcaneRecipe l0 = new ShapedArcaneRecipe("", dummy, new AspectList(), new Object[]{"OOO", "OOO", "OOO",
    // 'O', TCBlocks.obsidian()});
    // ShapedArcaneRecipe l1 = new ShapedArcaneRecipe("", dummy, new AspectList(), new Object[]{"SSS", "SSS", "SSS",
    // 'S', TCBlocks.stone()});
    // return new ShapedArcaneRecipe[] { l0, l1 }; // 返回底到顶
    // }
    //
    // ------------------------------------------------------------
    //
    // 【小抄 2：展示单个漂浮的 2D 物品（比如一把剑、一个苹果）】
    // 用隙间（hole）或者空格包围它，让它处于正中间。
    // new Object[] {
    // "HHH",
    // "HAH", // A 是苹果
    // "HHH",
    // 'A', TSItems.greedApple(0),
    // 'H', TCBlocks.hole()
    // }
    //
    // ------------------------------------------------------------
    //
    // 【小抄 3：展示单个漂浮的 3D 方块（比如一个法杖基座）】
    // new Object[] {
    // " ", // 上面直接空着
    // " T ", // T 是基座
    // " ",
    // 'T', TCBlocks.wandPedestal()
    // }
    // （注意：字符串里直接打空格 " "，A哥会自动渲染成虚线空气框，比 hole 更好看！）
    //
    // ------------------------------------------------------------
    //
    // 【小抄 4：隙间（hole）到底有什么用？】
    // 1. 它等价于空气。
    // 2. 当你想强制 A 哥的画图窗口缩小（自适应缩小），但又不希望玩家看到大片的方块时，
    // 用隙间填满边缘，A哥底层计算体积时会把隙间算进去，从而让中间的物品显得特别小、特别精致。
    //
    // ------------------------------------------------------------
    //
    // 【小抄 5：为什么一定要用 ShapedArcaneRecipe？】
    // 因为 A 哥的 ResearchPage 只认这个类的字符串网格。
    // 研究 Key 填 ""（空字符串），输出随便填个 dummy，千万别传 null！
    //
    // ============================================================
}
