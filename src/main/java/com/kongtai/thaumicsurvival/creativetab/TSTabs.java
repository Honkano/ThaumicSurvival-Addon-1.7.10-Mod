package com.kongtai.thaumicsurvival.creativetab;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

import com.kongtai.thaumicsurvival.items.ModItems;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * ============================================================
 * 【神秘生存学 · 创造模式物品栏词典】
 * ============================================================
 * 集中管理本模组所有的创造模式标签页。
 * 绝不和原版的乱七八糟分类混在一起。
 * ============================================================
 */
public class TSTabs {

    // ==========================================
    // 神秘生存学主创造栏
    // ==========================================
    public static final CreativeTabs TAB_THAUMICSURVIVAL = new CreativeTabs("thaumicsurvival") {

        @Override
        @SideOnly(Side.CLIENT)
        public Item getTabIconItem() {
            // 用咱们的秘源金苹果作为这个分类的图标
            // （注意：这里直接返回 ModItems.simpleApple，如果它还没初始化，游戏会显示默认物品，不会崩）
            return ModItems.debugWand != null ? ModItems.debugWand : net.minecraft.init.Items.apple;
        }
    };
}
