package com.kongtai.thaumicsurvival.api.thaumcraft.thaumcrafttools;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.ItemStack;

import cpw.mods.fml.common.registry.GameRegistry;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.crafting.CrucibleRecipe;
import thaumcraft.api.crafting.IArcaneRecipe;
import thaumcraft.api.crafting.InfusionEnchantmentRecipe;
import thaumcraft.api.crafting.InfusionRecipe;

/**
 * ============================================================
 * 【神秘时代 · 企业级配方注册大典】
 * ============================================================
 * 包含所有配方的重载方法，以及自动分拣机（Map）。
 * ============================================================
 */
public class TCRecipes {

    // ============================================================
    // 👇 【核心黑科技：自动分拣机】
    // ============================================================
    // 配方注册时，会自动根据研究 Key 塞进对应的 Map 里。
    public static final Map<String, List<CrucibleRecipe>> CRUCIBLE_MAP = new HashMap<>();
    public static final Map<String, List<IArcaneRecipe>> ARCANE_MAP = new HashMap<>();
    public static final Map<String, List<InfusionRecipe>> INFUSION_MAP = new HashMap<>();
    public static final Map<String, List<InfusionEnchantmentRecipe>> ENCHANT_MAP = new HashMap<>();

    // 注魔附魔
    public static InfusionEnchantmentRecipe infusionEnchant(String research, Enchantment enchantment, int instability,
        AspectList aspects, ItemStack[] components) {
        InfusionEnchantmentRecipe recipe = ThaumcraftApi
            .addInfusionEnchantmentRecipe(research, enchantment, instability, aspects, components);
        ENCHANT_MAP.computeIfAbsent(research, k -> new ArrayList<>())
            .add(recipe);
        return recipe;
    }

    // ============================================================
    // 【一、注魔祭坛配方 (Infusion)】
    // ============================================================

    public static InfusionRecipe infusion(String research, ItemStack output, int instability, AspectList aspects,
        ItemStack central, ItemStack[] components) {
        InfusionRecipe recipe = ThaumcraftApi
            .addInfusionCraftingRecipe(research, output, instability, aspects, central, components);
        INFUSION_MAP.computeIfAbsent(research, k -> new ArrayList<>())
            .add(recipe); // 自动分拣
        return recipe;
    }

    public static InfusionRecipe infusionNBT(String research, Object output, int instability, AspectList aspects,
        ItemStack central, ItemStack[] components) {
        InfusionRecipe recipe = ThaumcraftApi
            .addInfusionCraftingRecipe(research, output, instability, aspects, central, components);
        INFUSION_MAP.computeIfAbsent(research, k -> new ArrayList<>())
            .add(recipe); // 自动分拣
        return recipe;
    }

    // ============================================================
    // 【三、坩埚配方 (Crucible)】
    // ============================================================

    public static CrucibleRecipe crucible(String research, ItemStack output, ItemStack catalyst, AspectList aspects) {
        CrucibleRecipe recipe = ThaumcraftApi.addCrucibleRecipe(research, output, catalyst, aspects);
        CRUCIBLE_MAP.computeIfAbsent(research, k -> new ArrayList<>())
            .add(recipe); // 自动分拣
        return recipe;
    }

    public static CrucibleRecipe crucibleOre(String research, ItemStack output, String oreDict, AspectList aspects) {
        CrucibleRecipe recipe = ThaumcraftApi.addCrucibleRecipe(research, output, oreDict, aspects);
        CRUCIBLE_MAP.computeIfAbsent(research, k -> new ArrayList<>())
            .add(recipe); // 自动分拣
        return recipe;
    }

    // ============================================================
    // 【四、奥术工作台配方 (Arcane)】
    // ============================================================

    public static IArcaneRecipe arcaneShaped(String research, ItemStack output, AspectList vis, Object[] recipe) {
        IArcaneRecipe r = ThaumcraftApi.addArcaneCraftingRecipe(research, output, vis, recipe);
        ARCANE_MAP.computeIfAbsent(research, k -> new ArrayList<>())
            .add(r); // 自动分拣
        return r;
    }

    public static IArcaneRecipe arcaneShapeless(String research, ItemStack output, AspectList vis,
        Object... ingredients) {
        IArcaneRecipe r = ThaumcraftApi.addShapelessArcaneCraftingRecipe(research, output, vis, ingredients);
        ARCANE_MAP.computeIfAbsent(research, k -> new ArrayList<>())
            .add(r); // 自动分拣
        return r;
    }

    // ============================================================
    // 【五、普通工作台配方 (Vanilla Workbench)】
    // ============================================================

    public static void workbench(ItemStack output, Object[] recipe) {
        GameRegistry.addRecipe(output, recipe);
    }

    public static void workbenchShapeless(ItemStack output, Object... ingredients) {
        GameRegistry.addShapelessRecipe(output, ingredients);
    }

    public static void workbenchOre(ItemStack output, Object[] recipe) {
        GameRegistry.addRecipe(new net.minecraftforge.oredict.ShapedOreRecipe(output, recipe));
    }

    public static void workbenchOreShapeless(ItemStack output, Object... ingredients) {
        GameRegistry.addRecipe(new net.minecraftforge.oredict.ShapelessOreRecipe(output, ingredients));
    }

    // ============================================================
    // 【六、熔炉配方 (Smelting)】
    // ============================================================

    public static void smelting(ItemStack input, ItemStack output, float xp) {
        GameRegistry.addSmelting(input, output, xp);
    }
}
