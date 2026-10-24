package com.kongtai.thaumicsurvival.api.thaumcraft.thaumcrafttools;

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
 * 【神秘时代配方注册工具类】
 * ============================================================
 * 用途：把 ThaumcraftApi 的注册方法包一层，
 * 让你写配方时参数顺序更清晰，少踩坑。
 *
 * 【设计原则】
 * 1. 所有方法都直接调 ThaumcraftApi 原始方法，不改变行为。
 * 2. 每个方法返回注册后的配方对象，方便存到 Map/List 里。
 * 3. 参数顺序固定：研究 key → 输出 → 消耗/风险 → 材料。
 * 4. 不自动注册进任何 List/Map，由调用方决定存哪里。
 *
 * 【和原生 API 的关系】
 * 这个类不是替代，是封装。想用原生 API 随时可以绕过。
 * 比如 addInfusionCraftingRecipe 内部就调 ThaumcraftApi 原始方法。
 * ============================================================
 */
public class TCRecipes {

    // ============================================================
    // 【注魔配方】
    // ============================================================

    /**
     * 注册一个普通注魔配方。
     *
     * @param research    研究 key（必须和研究节点 key 一致）
     * @param output      输出物品（ItemStack 或 Object[] 打 NBT）
     * @param instability 不稳定度（0~10 日常，20~50 危险）
     * @param aspects     源质消耗
     * @param central     中心物品（放祭坛中央）
     * @param components  基座材料（建议 4/6/8/12 个）
     * @return 注册后的 InfusionRecipe 对象
     *
     *         注意：
     *         components 必须是 ItemStack[]，不能传矿辞字符串。
     *         output 传 Object[] 时格式为 { "nbtKey", NBTBase }。
     */
    public static InfusionRecipe infusion(String research, ItemStack output, int instability, AspectList aspects,
        ItemStack central, ItemStack[] components) {
        return ThaumcraftApi.addInfusionCraftingRecipe(research, output, instability, aspects, central, components);
    }

    /**
     * 注魔配方的 Object[] 输出重载（给输出打 NBT）。
     *
     * 例：
     * infusionWithNBT(key, new Object[]{"thaumicsurvival:anvil_tier", new NBTTagByte((byte)1)}, ...)
     */
    public static InfusionRecipe infusionWithNBT(String research, Object output, int instability, AspectList aspects,
        ItemStack central, ItemStack[] components) {
        return ThaumcraftApi.addInfusionCraftingRecipe(research, output, instability, aspects, central, components);
    }

    // ============================================================
    // 【注魔附魔配方】
    // ============================================================

    /**
     * 注册一个注魔附魔配方。
     *
     * @param research    研究 key
     * @param enchantment 要附加的附魔
     * @param instability 不稳定度
     * @param aspects     源质消耗
     * @param components  附魔材料
     * @return 注册后的 InfusionEnchantmentRecipe 对象
     */
    public static InfusionEnchantmentRecipe infusionEnchant(String research, Enchantment enchantment, int instability,
        AspectList aspects, ItemStack[] components) {
        return ThaumcraftApi.addInfusionEnchantmentRecipe(research, enchantment, instability, aspects, components);
    }

    // ============================================================
    // 【坩埚配方】
    // ============================================================

    /**
     * 注册一个坩埚配方（催化剂是 ItemStack）。
     *
     * @param research 研究 key
     * @param output   输出物品
     * @param catalyst 催化剂（丢进坩埚的引子）
     * @param aspects  源质消耗
     * @return 注册后的 CrucibleRecipe 对象
     */
    public static CrucibleRecipe crucible(String research, ItemStack output, ItemStack catalyst, AspectList aspects) {
        return ThaumcraftApi.addCrucibleRecipe(research, output, catalyst, aspects);
    }

    /**
     * 注册一个坩埚配方（催化剂是矿辞名）。
     *
     * 例：
     * crucibleOre(key, TCItems.thaumiumIngot(), TCOreDict.INGOT_IRON, aspects)
     * → 任何铁矿（含其他模组的）都能当催化剂。
     */
    public static CrucibleRecipe crucibleOre(String research, ItemStack output, String oreDict, AspectList aspects) {
        return ThaumcraftApi.addCrucibleRecipe(research, output, oreDict, aspects);
    }

    // ============================================================
    // 【奥术工作台配方】
    // ============================================================

    /**
     * 注册一个有序奥术配方。
     *
     * @param research 研究 key
     * @param output   输出物品
     * @param vis      魔力消耗
     * @param recipe   网格 + 字母对应表（照抄原版 Object[] 格式）
     * @return 注册后的 IArcaneRecipe 对象
     *
     *         例：
     *         arcaneShaped(key, output, vis, new Object[] {
     *         "ABA", "BCB", "ABA",
     *         'A', TCItems.goldIngot(),
     *         'B', TCItems.ironIngot(),
     *         'C', TCItems.diamond()
     *         })
     */
    public static IArcaneRecipe arcaneShaped(String research, ItemStack output, AspectList vis, Object[] recipe) {
        return ThaumcraftApi.addArcaneCraftingRecipe(research, output, vis, recipe);
    }

    /**
     * 注册一个无序奥术配方。
     *
     * @param research    研究 key
     * @param output      输出物品
     * @param vis         魔力消耗
     * @param ingredients 可变参数，每个都是一个材料
     * @return 注册后的 IArcaneRecipe 对象
     *
     *         例：
     *         arcaneShapeless(key, output, vis,
     *         TCItems.ironIngot(),
     *         TCItems.ironIngot(),
     *         TCItems.goldIngot())
     */
    public static IArcaneRecipe arcaneShapeless(String research, ItemStack output, AspectList vis,
        Object... ingredients) {
        return ThaumcraftApi.addShapelessArcaneCraftingRecipe(research, output, vis, ingredients);
    }

    // ============================================================
    // 【普通工作台配方】
    // ============================================================

    /**
     * 注册一个普通工作台有序配方（原版 GameRegistry，不走 TC）。
     *
     * @param output 输出
     * @param recipe 网格 + 字母对应表
     */
    public static void workbench(ItemStack output, Object[] recipe) {
        GameRegistry.addRecipe(output, recipe);
    }

    /**
     * 注册一个普通工作台无序配方。
     *
     * @param output      输出
     * @param ingredients 可变参数，每个都是一个材料
     */
    public static void workbenchShapeless(ItemStack output, Object... ingredients) {
        GameRegistry.addShapelessRecipe(output, ingredients);
    }

    /**
     * 注册一个普通工作台有序配方（支持矿物词典）。
     *
     * @param output 输出
     * @param recipe 网格 + 字母对应表（字母可对应 String 矿辞名）
     */
    public static void workbenchOre(ItemStack output, Object[] recipe) {
        GameRegistry.addRecipe(new net.minecraftforge.oredict.ShapedOreRecipe(output, recipe));
    }

    /**
     * 注册一个普通工作台无序配方（支持矿物词典）。
     */
    public static void workbenchOreShapeless(ItemStack output, Object... ingredients) {
        GameRegistry.addRecipe(new net.minecraftforge.oredict.ShapelessOreRecipe(output, ingredients));
    }

    // ============================================================
    // 【熔炉配方】
    // ============================================================

    /**
     * 注册一个熔炉配方。
     *
     * @param input  输入
     * @param output 输出
     * @param xp     经验值（0.0F 没经验，1.0F 等于烧一个铁锭）
     */
    public static void smelting(ItemStack input, ItemStack output, float xp) {
        GameRegistry.addSmelting(input, output, xp);
    }
}
