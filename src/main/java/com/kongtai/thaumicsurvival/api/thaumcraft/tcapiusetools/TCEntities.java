package com.kongtai.thaumicsurvival.api.thaumcraft.tcapiusetools;

/**
 * ============================================================
 * 【神秘时代实体名大词典】
 * ============================================================
 * 用途：把研究节点、实体要素注册、刷怪等场合用到的实体名，
 * 统一成有名字的字符串常量，避免手拼字符串拼错。
 *
 * 重要说明：
 * 1. 这里全是【字符串常量】，不是实体对象。
 * 用于 setEntityTriggers / registerEntityTag / addSpawn 等 API。
 * 2. 原版实体用纯类名（"Zombie"），
 * 神秘时代实体用 "Thaumcraft.XXX" 前缀。
 * 3. 内部注册名（registerModEntity 用的）不带 "Thaumcraft." 前缀，
 * 一般只有 TC4 自己用，外部引用较少，单独列在 Section 3。
 * 4. TC4 停留在 1.7.10 最终版，这些名字不会再变。
 * ============================================================
 */
public class TCEntities {

    // ============================================================
    // 【Section 1】原版实体名
    // ============================================================
    // 用于 ThaumcraftApi.registerEntityTag("Zombie", ...)
    // 以及 ResearchItem.setEntityTriggers(new String[]{"Enderman"})
    // 注意：原版实体不加 "Thaumcraft." 前缀。
    // ============================================================

    // ---- 亡灵类 ----
    public static final String ZOMBIE = "Zombie";
    public static final String GIANT = "Giant";
    public static final String SKELETON = "Skeleton";
    public static final String WITHER_SKELETON = "Skeleton"; // 配 NBT SkeletonType=1
    public static final String WITHER_BOSS = "WitherBoss";
    public static final String PIG_ZOMBIE = "PigZombie";

    // ---- 苦力怕类 ----
    public static final String CREEPER = "Creeper";
    public static final String CHARGED_CREEPER = "Creeper"; // 配 NBT powered=1

    // ---- 动物类 ----
    public static final String HORSE = "EntityHorse";
    public static final String PIG = "Pig";
    public static final String SHEEP = "Sheep";
    public static final String COW = "Cow";
    public static final String MOOSHROOM = "MushroomCow";
    public static final String CHICKEN = "Chicken";
    public static final String SQUID = "Squid";
    public static final String WOLF = "Wolf";
    public static final String OCELOT = "Ozelot";
    public static final String BAT = "Bat";

    // ---- 怪物类 ----
    public static final String SPIDER = "Spider";
    public static final String CAVE_SPIDER = "CaveSpider";
    public static final String SILVERFISH = "Silverfish";
    public static final String SLIME = "Slime";
    public static final String MAGMA_CUBE = "LavaSlime";
    public static final String GHAST = "Ghast";
    public static final String BLAZE = "Blaze";
    public static final String ENDERMAN = "Enderman";
    public static final String ENDER_DRAGON = "EnderDragon";
    public static final String WITCH = "Witch";

    // ---- 人形 ----
    public static final String VILLAGER = "Villager";
    public static final String IRON_GOLEM = "VillagerGolem";
    public static final String SNOW_GOLEM = "SnowMan";

    // ---- 非生物实体 ----
    public static final String XP_ORB = "XPOrb";
    public static final String BOAT = "Boat";
    public static final String ITEM_FRAME = "ItemFrame";
    public static final String PAINTING = "Painting";
    public static final String ENDER_CRYSTAL = "EnderCrystal";

    // ---- 矿车 ----
    public static final String MINECART = "MinecartRideable";
    public static final String MINECART_CHEST = "MinecartChest";
    public static final String MINECART_FURNACE = "MinecartFurnace";
    public static final String MINECART_TNT = "MinecartTNT";
    public static final String MINECART_HOPPER = "MinecartHopper";
    public static final String MINECART_SPAWNER = "MinecartSpawner";

    // ============================================================
    // 【Section 2】神秘时代实体名（带 "Thaumcraft." 前缀）
    // ============================================================
    // 用于 ThaumcraftApi.registerEntityTag("Thaumcraft.Wisp", ...)
    // 以及 ResearchItem.setEntityTriggers(new String[]{"Thaumcraft.Firebat"})
    // ============================================================

    // ---- 生物 ----
    public static final String TC_WISP = "Thaumcraft.Wisp";
    public static final String TC_FIREBAT = "Thaumcraft.Firebat";
    public static final String TC_PECH = "Thaumcraft.Pech";
    public static final String TC_THAUM_SLIME = "Thaumcraft.ThaumSlime";
    public static final String TC_BRAINY_ZOMBIE = "Thaumcraft.BrainyZombie";
    public static final String TC_GIANT_BRAINY_ZOMBIE = "Thaumcraft.GiantBrainyZombie";
    public static final String TC_MIND_SPIDER = "Thaumcraft.MindSpider";
    public static final String TC_INHABITED_ZOMBIE = "Thaumcraft.InhabitedZombie";
    public static final String TC_GOLEM = "Thaumcraft.Golem";
    public static final String TC_TRAVELING_TRUNK = "Thaumcraft.TravelingTrunk";

    // ---- 腐化生物 ----
    public static final String TC_TAINTACLE = "Thaumcraft.Taintacle";
    public static final String TC_TAINTACLE_TINY = "Thaumcraft.TaintacleTiny";
    public static final String TC_TAINTACLE_GIANT = "Thaumcraft.TaintacleGiant";
    public static final String TC_TAINT_SPIDER = "Thaumcraft.TaintSpider";
    public static final String TC_TAINT_SPORE = "Thaumcraft.TaintSpore";
    public static final String TC_TAINT_SWARMER = "Thaumcraft.TaintSwarmer";
    public static final String TC_TAINT_SWARM = "Thaumcraft.TaintSwarm";
    public static final String TC_TAINTED_PIG = "Thaumcraft.TaintedPig";
    public static final String TC_TAINTED_SHEEP = "Thaumcraft.TaintedSheep";
    public static final String TC_TAINTED_COW = "Thaumcraft.TaintedCow";
    public static final String TC_TAINTED_CHICKEN = "Thaumcraft.TaintedChicken";
    public static final String TC_TAINTED_CREEPER = "Thaumcraft.TaintedCreeper";
    public static final String TC_TAINTED_VILLAGER = "Thaumcraft.TaintedVillager";

    // ---- 邪术 ----
    public static final String TC_ELDRITCH_GUARDIAN = "Thaumcraft.EldritchGuardian";
    public static final String TC_ELDRITCH_ORB = "Thaumcraft.EldritchOrb";
    public static final String TC_ELDRITCH_CRAB = "Thaumcraft.EldritchCrab";

    // ---- 血腥邪徒 ----
    public static final String TC_CULTIST_KNIGHT = "Thaumcraft.CultistKnight";
    public static final String TC_CULTIST_CLERIC = "Thaumcraft.CultistCleric";
    public static final String TC_CULTIST_LEADER = "Thaumcraft.CultistLeader";
    public static final String TC_CULTIST_PORTAL = "Thaumcraft.CultistPortal";

    // ---- 投射物 / 特殊实体 ----
    public static final String TC_PRIMAL_ORB = "Thaumcraft.PrimalOrb";
    public static final String TC_BOTTLE_TAINT = "Thaumcraft.BottleTaint";
    public static final String TC_ASPECT_ORB = "Thaumcraft.AspectOrb";
    public static final String TC_FALLING_TAINT = "Thaumcraft.FallingTaint";
    public static final String TC_ALUMENTUM = "Thaumcraft.Alumentum";
    public static final String TC_GOLEM_ORB = "Thaumcraft.GolemOrb";
    public static final String TC_FROST_SHARD = "Thaumcraft.FrostShard";
    public static final String TC_SHOCK_ORB = "Thaumcraft.ShockOrb";
    public static final String TC_EXPLOSIVE_ORB = "Thaumcraft.ExplosiveOrb";
    public static final String TC_PECH_BLAST = "Thaumcraft.PechBlast";
    public static final String TC_DART = "Thaumcraft.Dart";
    public static final String TC_PRIMAL_ARROW = "Thaumcraft.PrimalArrow";
    public static final String TC_GOLEM_BOBBER = "Thaumcraft.GolemBobber";

    // ============================================================
    // 【Section 3】内部注册名（registerModEntity 用，不带前缀）
    // ============================================================
    // 这些是 ConfigEntities 里 EntityRegistry.registerModEntity(...) 用的名字，
    // 也是 ItemSpawnerEgg.addMapping(...) 用的名字。
    // 外部模组很少直接引用，但如果你想动态生成刷怪蛋/召唤实体，可能会用到。
    // ============================================================

    public static final String REG_SPECIAL_ITEM = "SpecialItem";
    public static final String REG_PERMANENT_ITEM = "PermanentItem";
    public static final String REG_FOLLOW_ITEM = "FollowItem";
    public static final String REG_ASPECT_ORB = "AspectOrb";
    public static final String REG_FALLING_TAINT = "FallingTaint";
    public static final String REG_ALUMENTUM = "Alumentum";
    public static final String REG_PRIMAL_ORB = "PrimalOrb";
    public static final String REG_FROST_SHARD = "FrostShard";
    public static final String REG_DART = "Dart";
    public static final String REG_PRIMAL_ARROW = "PrimalArrow";
    public static final String REG_PECH_BLAST = "PechBlast";
    public static final String REG_ELDRITCH_ORB = "EldritchOrb";
    public static final String REG_BOTTLE_TAINT = "BottleTaint";
    public static final String REG_GOLEM_ORB = "GolemOrb";
    public static final String REG_SHOCK_ORB = "ShockOrb";
    public static final String REG_EXPLOSIVE_ORB = "ExplosiveOrb";
    public static final String REG_EMBER = "Ember";
    public static final String REG_GOLEM = "Golem";
    public static final String REG_TRAVELING_TRUNK = "TravelingTrunk";
    public static final String REG_BRAINY_ZOMBIE = "BrainyZombie";
    public static final String REG_GIANT_BRAINY_ZOMBIE = "GiantBrainyZombie";
    public static final String REG_WISP = "Wisp";
    public static final String REG_FIREBAT = "Firebat";
    public static final String REG_PECH = "Pech";
    public static final String REG_MIND_SPIDER = "MindSpider";
    public static final String REG_ELDRITCH_GUARDIAN = "EldritchGuardian";
    public static final String REG_ELDRITCH_WARDEN = "EldritchWarden";
    public static final String REG_CULTIST_KNIGHT = "CultistKnight";
    public static final String REG_CULTIST_CLERIC = "CultistCleric";
    public static final String REG_CULTIST_LEADER = "CultistLeader";
    public static final String REG_CULTIST_PORTAL = "CultistPortal";
    public static final String REG_ELDRITCH_GOLEM = "EldritchGolem";
    public static final String REG_ELDRITCH_CRAB = "EldritchCrab";
    public static final String REG_INHABITED_ZOMBIE = "InhabitedZombie";
    public static final String REG_THAUM_SLIME = "ThaumSlime";
    public static final String REG_TAINT_SPIDER = "TaintSpider";
    public static final String REG_TAINTACLE = "Taintacle";
    public static final String REG_TAINTACLE_TINY = "TaintacleTiny";
    public static final String REG_TAINT_SPORE = "TaintSpore";
    public static final String REG_TAINT_SWARMER = "TaintSwarmer";
    public static final String REG_TAINT_SWARM = "TaintSwarm";
    public static final String REG_TAINTED_CHICKEN = "TaintedChicken";
    public static final String REG_TAINTED_COW = "TaintedCow";
    public static final String REG_TAINTED_CREEPER = "TaintedCreeper";
    public static final String REG_TAINTED_PIG = "TaintedPig";
    public static final String REG_TAINTED_SHEEP = "TaintedSheep";
    public static final String REG_TAINTED_VILLAGER = "TaintedVillager";
    public static final String REG_TAINTACLE_GIANT = "TaintacleGiant";
    public static final String REG_ITEM_GRATE = "SpecialItemGrate";
    public static final String REG_GOLEM_BOBBER = "GolemBobber";
}
