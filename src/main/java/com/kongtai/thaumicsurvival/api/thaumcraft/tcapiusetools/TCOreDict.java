package com.kongtai.thaumicsurvival.api.thaumcraft.tcapiusetools;

/**
 * ============================================================
 * 【矿物词典名大词典】
 * ============================================================
 * 用途：把 OreDictionary 的字符串名字统一成常量，
 * 写 ShapedOreRecipe / ShapelessOreRecipe / 注魔配方时，
 * 直接用常量，避免手拼字符串拼错。
 *
 * 【重要说明】
 * 1. 这里全是【字符串常量】，不是 ItemStack。
 * 矿辞名是运行时匹配的，编译期只是字符串。
 *
 * 2. 来源：ConfigItems.init() 的 OreDictionary.registerOre(...)
 * 以及 ConfigAspects 里 registerObjectTag("xxx", ...) 的字符串。
 *
 * 3. 矿辞名大小写敏感，拼错运行时静默失败（不崩，但配方不工作）。
 *
 * 4. TC4 停留在 1.7.10 最终版，矿辞名不会再变。
 * ============================================================
 */
public class TCOreDict {

    // ============================================================
    // 【Section 1】矿石（oreXxx）
    // ============================================================
    // 用于 ShapedOreRecipe 里 "oreIron" 这类写法。
    // TC4 自己注册的矿石：oreCinnabar, oreInfusedXxx, oreAmber。
    // 金属矿（oreCopper 等）依赖 Config.foundXxxOre，可能不存在。
    // ============================================================

    /** 朱砂矿石（TC4 注册） */
    public static final String ORE_CINNABAR = "oreCinnabar";
    /** 风之蕴魔石（TC4 注册） */
    public static final String ORE_INFUSED_AIR = "oreInfusedAir";
    /** 火之蕴魔石（TC4 注册） */
    public static final String ORE_INFUSED_FIRE = "oreInfusedFire";
    /** 水之蕴魔石（TC4 注册） */
    public static final String ORE_INFUSED_WATER = "oreInfusedWater";
    /** 地之蕴魔石（TC4 注册） */
    public static final String ORE_INFUSED_EARTH = "oreInfusedEarth";
    /** 秩序蕴魔石（TC4 注册） */
    public static final String ORE_INFUSED_ORDER = "oreInfusedOrder";
    /** 混沌蕴魔石（TC4 注册） */
    public static final String ORE_INFUSED_ENTROPY = "oreInfusedEntropy";
    /** 琥珀矿石（TC4 注册） */
    public static final String ORE_AMBER = "oreAmber";

    /** 铁矿（原版 / 其他模组注册，TC 也认） */
    public static final String ORE_IRON = "oreIron";
    /** 金矿 */
    public static final String ORE_GOLD = "oreGold";
    /** 青金石矿 */
    public static final String ORE_LAPIS = "oreLapis";
    /** 钻石矿 */
    public static final String ORE_DIAMOND = "oreDiamond";
    /** 绿宝石矿 */
    public static final String ORE_EMERALD = "oreEmerald";
    /** 红石矿 */
    public static final String ORE_REDSTONE = "oreRedstone";
    /** 石英矿 */
    public static final String ORE_QUARTZ = "oreQuartz";
    /** 煤矿（TC4 没注册，但原版有 "oreCoal"） */
    public static final String ORE_COAL = "oreCoal";

    // 下面这些只有装了对应矿物模组才存在，TC 自己会判断 Config.foundXxxOre
    public static final String ORE_COPPER = "oreCopper";
    public static final String ORE_TIN = "oreTin";
    public static final String ORE_SILVER = "oreSilver";
    public static final String ORE_LEAD = "oreLead";

    // ============================================================
    // 【Section 2】锭（ingotXxx）
    // ============================================================

    /** 神秘锭（TC4 注册） */
    public static final String INGOT_THAUMIUM = "ingotThaumium";
    /** 虚空锭（TC4 注册） */
    public static final String INGOT_VOID = "ingotVoid";
    /** 铁锭 */
    public static final String INGOT_IRON = "ingotIron";
    /** 金锭 */
    public static final String INGOT_GOLD = "ingotGold";

    // 只有装对应矿物模组才存在
    public static final String INGOT_COPPER = "ingotCopper";
    public static final String INGOT_TIN = "ingotTin";
    public static final String INGOT_SILVER = "ingotSilver";
    public static final String INGOT_LEAD = "ingotLead";

    // ============================================================
    // 【Section 3】粒（nuggetXxx）
    // ============================================================

    /** 铁粒（TC4 注册） */
    public static final String NUGGET_IRON = "nuggetIron";
    /** 神秘粒（TC4 注册） */
    public static final String NUGGET_THAUMIUM = "nuggetThaumium";
    /** 虚空粒（TC4 注册） */
    public static final String NUGGET_VOID = "nuggetVoid";
    /** 金粒（TC4 注册，指向 itemResource meta 18） */
    public static final String NUGGET_GOLD = "nuggetGold";
    /** 水银液滴（TC4 注册，指向 itemResource meta 3） */
    public static final String NUGGET_QUICKSILVER = "nuggetQuicksilver";

    // 只有装对应矿物模组才存在
    public static final String NUGGET_COPPER = "nuggetCopper";
    public static final String NUGGET_TIN = "nuggetTin";
    public static final String NUGGET_SILVER = "nuggetSilver";
    public static final String NUGGET_LEAD = "nuggetLead";

    // ============================================================
    // 【Section 4】矿簇（clusterXxx）
    // ============================================================
    // 冶炼矿簇得到双倍锭，是 TC4 的特色。
    // ============================================================

    public static final String CLUSTER_IRON = "clusterIron";
    public static final String CLUSTER_GOLD = "clusterGold";
    public static final String CLUSTER_CINNABAR = "clusterCinnabar";
    public static final String CLUSTER_COPPER = "clusterCopper";
    public static final String CLUSTER_TIN = "clusterTin";
    public static final String CLUSTER_SILVER = "clusterSilver";
    public static final String CLUSTER_LEAD = "clusterLead";

    // ============================================================
    // 【Section 5】宝石 / 水晶 / 碎片
    // ============================================================

    /** 琥珀（TC4 注册） */
    public static final String GEM_AMBER = "gemAmber";
    /** 钻石 */
    public static final String GEM_DIAMOND = "gemDiamond";
    /** 绿宝石 */
    public static final String GEM_EMERALD = "gemEmerald";

    // TC4 的六种碎片，OreDictionary 里用 "shardXxx"
    public static final String SHARD_AIR = "shardAir";
    public static final String SHARD_FIRE = "shardFire";
    public static final String SHARD_WATER = "shardWater";
    public static final String SHARD_EARTH = "shardEarth";
    public static final String SHARD_ORDER = "shardOrder";
    public static final String SHARD_ENTROPY = "shardEntropy";

    // ============================================================
    // 【Section 6】粉末（dustXxx）
    // ============================================================

    public static final String DUST_IRON = "dustIron";
    public static final String DUST_GOLD = "dustGold";
    public static final String DUST_REDSTONE = "dustRedstone";
    /** 萤石粉（注意：TC4 用 "dustGlowstone"，原版矿辞是 "glowstone"） */
    public static final String DUST_GLOWSTONE = "dustGlowstone";

    // 只有装对应矿物模组才存在
    public static final String DUST_COPPER = "dustCopper";
    public static final String DUST_TIN = "dustTin";
    public static final String DUST_SILVER = "dustSilver";
    public static final String DUST_LEAD = "dustLead";

    // ============================================================
    // 【Section 7】木材类
    // ============================================================
    // TC4 的宏伟之木、银树都会注册进这些矿辞。
    // 所以用 "logWood" / "plankWood" 能同时匹配原版和 TC 的木材。
    // ============================================================

    /** 任意原木（含 TC 宏伟之木、银树） */
    public static final String LOG_WOOD = "logWood";
    /** 任意木板（含 TC 宏伟之木、银树木板） */
    public static final String PLANK_WOOD = "plankWood";
    /** 任意木台阶（含 TC 宏伟之木、银树台阶） */
    public static final String SLAB_WOOD = "slabWood";
    /** 任意木棍 */
    public static final String STICK_WOOD = "stickWood";
    /** 任意木楼梯 */
    public static final String STAIR_WOOD = "stairWood";
    /** 任意树苗（含 TC 宏伟之木、银树树苗） */
    public static final String TREE_SAPLING = "treeSapling";
    /** 任意树叶（含 TC 宏伟之木、银树树叶） */
    public static final String TREE_LEAVES = "treeLeaves";

    // ============================================================
    // 【Section 8】染料（dyeXxx）
    // ============================================================
    // 顺序和原版 meta 一致：0 黑 → 15 白。
    // ConfigAspects.dyes 里也是这个顺序，用于给物品打源质标签。
    // ============================================================

    public static final String DYE_BLACK = "dyeBlack"; // 0
    public static final String DYE_RED = "dyeRed"; // 1
    public static final String DYE_GREEN = "dyeGreen"; // 2
    public static final String DYE_BROWN = "dyeBrown"; // 3
    public static final String DYE_BLUE = "dyeBlue"; // 4
    public static final String DYE_PURPLE = "dyePurple"; // 5
    public static final String DYE_CYAN = "dyeCyan"; // 6
    public static final String DYE_LIGHT_GRAY = "dyeLightGray"; // 7
    public static final String DYE_GRAY = "dyeGray"; // 8
    public static final String DYE_PINK = "dyePink"; // 9
    public static final String DYE_LIME = "dyeLime"; // 10
    public static final String DYE_YELLOW = "dyeYellow"; // 11
    public static final String DYE_LIGHT_BLUE = "dyeLightBlue"; // 12
    public static final String DYE_MAGENTA = "dyeMagenta"; // 13
    public static final String DYE_ORANGE = "dyeOrange"; // 14
    public static final String DYE_WHITE = "dyeWhite"; // 15

    // ============================================================
    // 【Section 9】原版常见矿辞（TC4 也认，写配方常用）
    // ============================================================

    public static final String STONE = "stone";
    public static final String COBBLESTONE = "cobblestone";
    public static final String GLOWSTONE = "glowstone"; // 注意：这是原版矿辞，TC 用 DUST_GLOWSTONE
    public static final String BLOCK_GLASS = "blockGlass";
    public static final String BLOCK_GLASS_COLORLESS = "blockGlassColorless";
    public static final String SAND = "sand";
    public static final String SANDSTONE = "sandstone";
    public static final String OBSIDIAN = "obsidian";
    public static final String REDSTONE_BLOCK = "blockRedstone";
    public static final String DIAMOND_BLOCK = "blockDiamond";
    public static final String EMERALD_BLOCK = "blockEmerald";
    public static final String GOLD_BLOCK = "blockGold";
    public static final String IRON_BLOCK = "blockIron";
    public static final String LAPIS_BLOCK = "blockLapis";
    public static final String QUARTZ_BLOCK = "blockQuartz";

    // ============================================================
    // 【Section 10】TC4 特有矿辞（原版没有，只 TC4 自己注册）
    // ============================================================

    /**
     * 水银。
     * 这个不是标准矿辞命名规范，TC4 自己起的名字。
     * 用 "quicksilver" 能匹配 itemResource meta 3（水银）。
     */
    public static final String QUICKSILVER = "quicksilver";

    // ============================================================
    // 【Section 11】按原版 meta 顺序访问 dyes 数组的辅助方法
    // ============================================================
    // ConfigAspects.dyes 是 String[]，直接拿安全。
    // 这里提供方法只是方便你按 meta 0~15 取名字，不用记。
    // ============================================================

    private static final String[] DYES = { DYE_BLACK, DYE_RED, DYE_GREEN, DYE_BROWN, DYE_BLUE, DYE_PURPLE, DYE_CYAN,
        DYE_LIGHT_GRAY, DYE_GRAY, DYE_PINK, DYE_LIME, DYE_YELLOW, DYE_LIGHT_BLUE, DYE_MAGENTA, DYE_ORANGE, DYE_WHITE };

    /**
     * 按原版染料 meta 取矿辞名。
     * 例：dye(0) → "dyeBlack"，dye(15) → "dyeWhite"
     * meta 越界返回 DYE_WHITE。
     */
    public static String dye(int meta) {
        if (meta < 0 || meta >= DYES.length) return DYE_WHITE;
        return DYES[meta];
    }
}
