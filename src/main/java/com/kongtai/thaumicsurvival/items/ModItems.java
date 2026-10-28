package com.kongtai.thaumicsurvival.items;

import net.minecraft.item.Item;

import com.kongtai.thaumicsurvival.creativetab.TSTabs;
import com.kongtai.thaumicsurvival.items.debug.ItemDebugMenu;
import com.kongtai.thaumicsurvival.items.debug.ItemDebugWand;
import com.kongtai.thaumicsurvival.items.food.ItemThaumicApple;

import cpw.mods.fml.common.registry.GameRegistry;

public class ModItems {

    public static Item simpleApple;
    public static Item enchantedApple;

    public static Item debugMenu;
    public static Item debugWand;

    // 这里其实不需要注册原版苹果，因为原版苹果已经有了，我们只是给它挂上 NBT
    public static void registerItems() {
        // 实例化，并直接通过 .setCreativeTab() 丢进咱们自己的分类
        simpleApple = new ItemThaumicApple(false).setCreativeTab(TSTabs.TAB_THAUMICSURVIVAL);
        enchantedApple = new ItemThaumicApple(true).setCreativeTab(TSTabs.TAB_THAUMICSURVIVAL);

        debugMenu = new ItemDebugMenu().setCreativeTab(TSTabs.TAB_THAUMICSURVIVAL);
        debugWand = new ItemDebugWand().setCreativeTab(TSTabs.TAB_THAUMICSURVIVAL);

        // 注册到游戏（名字保持你之前的独立命名）
        GameRegistry.registerItem(simpleApple, "thaumicsurvival.simple_apple");
        GameRegistry.registerItem(enchantedApple, "thaumicsurvival.enchanted_apple");
        GameRegistry.registerItem(debugMenu, "thaumicsurvival.debug_menu");
        GameRegistry.registerItem(debugWand, "thaumicsurvival.debug_wand");

    }

}
