package com.kongtai.thaumicsurvival.api.thaumcraft.tcapiusetools;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import thaumcraft.common.config.ConfigItems;

/**
 * ============================================================
 * 【神秘时代物品大词典】
 * ============================================================
 * 用途：把 ConfigItems.itemXxx + meta 这种反人类写法，
 * 翻译成有名字的静态方法，写配方时不用查 meta。
 *
 * 规则：
 * 1. 每个方法返回【新的】ItemStack，不共享实例。
 * 2. meta 来源：ConfigItems / ConfigAspects / ConfigRecipes / 翻译文件。
 * 3. TC4 停留在 1.7.10 最终版，meta 不会再变。
 * ============================================================
 */
public class TCItems {

    // ============================================================
    // 【Section 1】itemResource 神秘时代通用资源（meta 0~18）
    // ============================================================
    // 这个物品是 TC4 最核心的“通用资源”，大量 meta 都是它。
    // meta 对照表（来自翻译文件 item.ItemResource.X.name）：
    // 0 源动之焰 Alumentum
    // 1 闪耀之光 Nitor
    // 2 神秘锭 Thaumium Ingot
    // 3 水银 Quicksilver
    // 4 魔力油脂 Tallow
    // 5 僵尸之脑(死亡) —— 废弃/未使用，ConfigAspects 没注册源质
    // 6 琥珀 Amber
    // 7 魔力布匹 Enchanted Fabric
    // 8 魔力滤管 Filter
    // 9 知识碎片 Knowledge Fragment
    // 10 镜面玻璃 Mirror Glass
    // 11 腐化粘浆 Taint Slime
    // 12 腐化触手 Tainted Goo
    // 13 罐子标签 Jar Label（NBT 存要素）
    // 14 世界盐 Salis Mundus
    // 15 元始魔力 Primal Charm
    // 16 虚空锭 Void Metal Ingot
    // 17 虚空种子 Void Seed
    // 18 金粒 Gold Nugget（TC 自定义）
    // ============================================================

    /** 源动之焰（Alumentum），TC 燃料，meta 0 */
    public static ItemStack alumentum() {
        return new ItemStack(ConfigItems.itemResource, 1, 0);
    }

    /** 闪耀之光（Nitor），TC 光源，meta 1 */
    public static ItemStack nitor() {
        return new ItemStack(ConfigItems.itemResource, 1, 1);
    }

    /** 神秘锭（Thaumium Ingot），meta 2 */
    public static ItemStack thaumiumIngot() {
        return new ItemStack(ConfigItems.itemResource, 1, 2);
    }

    /** 水银（Quicksilver），meta 3 */
    public static ItemStack quicksilver() {
        return new ItemStack(ConfigItems.itemResource, 1, 3);
    }

    /** 魔力油脂（Tallow），meta 4 */
    public static ItemStack tallow() {
        return new ItemStack(ConfigItems.itemResource, 1, 4);
    }

    /** 僵尸之脑(死亡)，meta 5 —— 废弃，不建议使用 */
    public static ItemStack zombieBrainDead() {
        return new ItemStack(ConfigItems.itemResource, 1, 5);
    }

    /** 琥珀（Amber），meta 6 */
    public static ItemStack amber() {
        return new ItemStack(ConfigItems.itemResource, 1, 6);
    }

    /** 魔力布匹（Enchanted Fabric），meta 7 */
    public static ItemStack enchantedFabric() {
        return new ItemStack(ConfigItems.itemResource, 1, 7);
    }

    /** 魔力滤管（Filter），meta 8 */
    public static ItemStack filter() {
        return new ItemStack(ConfigItems.itemResource, 1, 8);
    }

    /** 知识碎片（Knowledge Fragment），meta 9 */
    public static ItemStack knowledgeFragment() {
        return new ItemStack(ConfigItems.itemResource, 1, 9);
    }

    /** 镜面玻璃（Mirror Glass），meta 10 */
    public static ItemStack mirrorGlass() {
        return new ItemStack(ConfigItems.itemResource, 1, 10);
    }

    /** 腐化粘浆（Taint Slime），meta 11 */
    public static ItemStack taintSlime() {
        return new ItemStack(ConfigItems.itemResource, 1, 11);
    }

    /** 腐化触手（Tainted Goo），meta 12 */
    public static ItemStack taintedGoo() {
        return new ItemStack(ConfigItems.itemResource, 1, 12);
    }

    /** 罐子标签（Jar Label），meta 13，NBT 存要素 */
    public static ItemStack jarLabel() {
        return new ItemStack(ConfigItems.itemResource, 1, 13);
    }

    /** 世界盐（Salis Mundus），meta 14 */
    public static ItemStack salisMundus() {
        return new ItemStack(ConfigItems.itemResource, 1, 14);
    }

    /** 元始魔力（Primal Charm），meta 15 */
    public static ItemStack primalCharm() {
        return new ItemStack(ConfigItems.itemResource, 1, 15);
    }

    /** 虚空锭（Void Metal Ingot），meta 16 */
    public static ItemStack voidMetalIngot() {
        return new ItemStack(ConfigItems.itemResource, 1, 16);
    }

    /** 虚空种子（Void Seed），meta 17 */
    public static ItemStack voidSeed() {
        return new ItemStack(ConfigItems.itemResource, 1, 17);
    }

    /** 金粒（Gold Nugget，TC 自定义），meta 18 */
    public static ItemStack goldNuggetTC() {
        return new ItemStack(ConfigItems.itemResource, 1, 18);
    }

    // ============================================================
    // 【Section 2】itemShard 六种原始碎片 + 平衡碎片（meta 0~6）
    // ============================================================

    /** 风之碎片（Air Shard），meta 0 */
    public static ItemStack shardAir() {
        return new ItemStack(ConfigItems.itemShard, 1, 0);
    }

    /** 火之碎片（Fire Shard），meta 1 */
    public static ItemStack shardFire() {
        return new ItemStack(ConfigItems.itemShard, 1, 1);
    }

    /** 水之碎片（Water Shard），meta 2 */
    public static ItemStack shardWater() {
        return new ItemStack(ConfigItems.itemShard, 1, 2);
    }

    /** 地之碎片（Earth Shard），meta 3 */
    public static ItemStack shardEarth() {
        return new ItemStack(ConfigItems.itemShard, 1, 3);
    }

    /** 秩序碎片（Order Shard），meta 4 */
    public static ItemStack shardOrder() {
        return new ItemStack(ConfigItems.itemShard, 1, 4);
    }

    /** 混沌碎片（Entropy Shard），meta 5 */
    public static ItemStack shardEntropy() {
        return new ItemStack(ConfigItems.itemShard, 1, 5);
    }

    /** 平衡碎片（Balanced Shard），meta 6 */
    public static ItemStack shardBalanced() {
        return new ItemStack(ConfigItems.itemShard, 1, 6);
    }

    // ============================================================
    // 【Section 3】itemNugget 金属粒 / 矿簇（meta 0~7, 16~21, 31）
    // ============================================================
    // 0~7 是金属粒，16~21 和 31 是矿簇（冶炼出双倍锭）。
    // ============================================================

    /** 铁粒，meta 0 */
    public static ItemStack nuggetIron() {
        return new ItemStack(ConfigItems.itemNugget, 1, 0);
    }

    /** 铜粒，meta 1 */
    public static ItemStack nuggetCopper() {
        return new ItemStack(ConfigItems.itemNugget, 1, 1);
    }

    /** 锡粒，meta 2 */
    public static ItemStack nuggetTin() {
        return new ItemStack(ConfigItems.itemNugget, 1, 2);
    }

    /** 银粒，meta 3 */
    public static ItemStack nuggetSilver() {
        return new ItemStack(ConfigItems.itemNugget, 1, 3);
    }

    /** 铅粒，meta 4 */
    public static ItemStack nuggetLead() {
        return new ItemStack(ConfigItems.itemNugget, 1, 4);
    }

    /** 水银液滴，meta 5 */
    public static ItemStack nuggetQuicksilver() {
        return new ItemStack(ConfigItems.itemNugget, 1, 5);
    }

    /** 神秘粒，meta 6 */
    public static ItemStack nuggetThaumium() {
        return new ItemStack(ConfigItems.itemNugget, 1, 6);
    }

    /** 虚空粒，meta 7 */
    public static ItemStack nuggetVoid() {
        return new ItemStack(ConfigItems.itemNugget, 1, 7);
    }

    /** 铁原矿簇，meta 16 */
    public static ItemStack clusterIron() {
        return new ItemStack(ConfigItems.itemNugget, 1, 16);
    }

    /** 铜原矿簇，meta 17 */
    public static ItemStack clusterCopper() {
        return new ItemStack(ConfigItems.itemNugget, 1, 17);
    }

    /** 锡原矿簇，meta 18 */
    public static ItemStack clusterTin() {
        return new ItemStack(ConfigItems.itemNugget, 1, 18);
    }

    /** 银原矿簇，meta 19 */
    public static ItemStack clusterSilver() {
        return new ItemStack(ConfigItems.itemNugget, 1, 19);
    }

    /** 铅原矿簇，meta 20 */
    public static ItemStack clusterLead() {
        return new ItemStack(ConfigItems.itemNugget, 1, 20);
    }

    /** 朱砂原矿簇，meta 21 */
    public static ItemStack clusterCinnabar() {
        return new ItemStack(ConfigItems.itemNugget, 1, 21);
    }

    /** 金原矿簇，meta 31 */
    public static ItemStack clusterGold() {
        return new ItemStack(ConfigItems.itemNugget, 1, 31);
    }

    // ============================================================
    // 【Section 4】itemEssence 源质瓶（meta 0~1）
    // ============================================================

    /** 玻璃安瓿（空瓶），meta 0 */
    public static ItemStack phialEmpty() {
        return new ItemStack(ConfigItems.itemEssence, 1, 0);
    }

    /** 要素安瓿（装满，NBT 存要素），meta 1 */
    public static ItemStack phialFilled() {
        return new ItemStack(ConfigItems.itemEssence, 1, 1);
    }

    // ============================================================
    // 【Section 5】itemPrimalArrow 元始之矢（meta 0~5）
    // ============================================================

    /** 风之矢，meta 0 */
    public static ItemStack primalArrowAir() {
        return new ItemStack(ConfigItems.itemPrimalArrow, 1, 0);
    }

    /** 火之矢，meta 1 */
    public static ItemStack primalArrowFire() {
        return new ItemStack(ConfigItems.itemPrimalArrow, 1, 1);
    }

    /** 水之矢，meta 2 */
    public static ItemStack primalArrowWater() {
        return new ItemStack(ConfigItems.itemPrimalArrow, 1, 2);
    }

    /** 地之矢，meta 3 */
    public static ItemStack primalArrowEarth() {
        return new ItemStack(ConfigItems.itemPrimalArrow, 1, 3);
    }

    /** 秩序矢，meta 4 */
    public static ItemStack primalArrowOrder() {
        return new ItemStack(ConfigItems.itemPrimalArrow, 1, 4);
    }

    /** 混沌矢，meta 5 */
    public static ItemStack primalArrowEntropy() {
        return new ItemStack(ConfigItems.itemPrimalArrow, 1, 5);
    }

    // ============================================================
    // 【Section 6】itemWandCap 法杖杖端（meta 0~8）
    // ============================================================

    /** 铁杖端，meta 0 */
    public static ItemStack wandCapIron() {
        return new ItemStack(ConfigItems.itemWandCap, 1, 0);
    }

    /** 金杖端，meta 1 */
    public static ItemStack wandCapGold() {
        return new ItemStack(ConfigItems.itemWandCap, 1, 1);
    }

    /** 充能神秘杖端，meta 2 */
    public static ItemStack wandCapThaumium() {
        return new ItemStack(ConfigItems.itemWandCap, 1, 2);
    }

    /** 铜杖端，meta 3 */
    public static ItemStack wandCapCopper() {
        return new ItemStack(ConfigItems.itemWandCap, 1, 3);
    }

    /** 充能银杖端，meta 4 */
    public static ItemStack wandCapSilverCharged() {
        return new ItemStack(ConfigItems.itemWandCap, 1, 4);
    }

    /** 惰性银杖端，meta 5 */
    public static ItemStack wandCapSilverInert() {
        return new ItemStack(ConfigItems.itemWandCap, 1, 5);
    }

    /** 惰性神秘杖端，meta 6 */
    public static ItemStack wandCapThaumiumInert() {
        return new ItemStack(ConfigItems.itemWandCap, 1, 6);
    }

    /** 充能虚空杖端，meta 7 */
    public static ItemStack wandCapVoidCharged() {
        return new ItemStack(ConfigItems.itemWandCap, 1, 7);
    }

    /** 惰性虚空杖端，meta 8 */
    public static ItemStack wandCapVoidInert() {
        return new ItemStack(ConfigItems.itemWandCap, 1, 8);
    }

    // ============================================================
    // 【Section 7】itemWandRod 法杖杖柄 / 手杖杖芯（meta 0~7, 50~57, 100）
    // ============================================================
    // 0~7 是法杖杖柄，50~57 是手杖杖芯，100 是元始杖芯。
    // ============================================================

    /** 宏伟之木杖柄，meta 0 */
    public static ItemStack wandRodGreatwood() {
        return new ItemStack(ConfigItems.itemWandRod, 1, 0);
    }

    /** 黑曜石杖柄，meta 1 */
    public static ItemStack wandRodObsidian() {
        return new ItemStack(ConfigItems.itemWandRod, 1, 1);
    }

    /** 银树杖柄，meta 2 */
    public static ItemStack wandRodSilverwood() {
        return new ItemStack(ConfigItems.itemWandRod, 1, 2);
    }

    /** 寒霜杖柄，meta 3 */
    public static ItemStack wandRodIce() {
        return new ItemStack(ConfigItems.itemWandRod, 1, 3);
    }

    /** 石英杖柄，meta 4 */
    public static ItemStack wandRodQuartz() {
        return new ItemStack(ConfigItems.itemWandRod, 1, 4);
    }

    /** 甘蔗杖柄，meta 5 */
    public static ItemStack wandRodReed() {
        return new ItemStack(ConfigItems.itemWandRod, 1, 5);
    }

    /** 烈焰杖柄，meta 6 */
    public static ItemStack wandRodBlaze() {
        return new ItemStack(ConfigItems.itemWandRod, 1, 6);
    }

    /** 白骨杖柄，meta 7 */
    public static ItemStack wandRodBone() {
        return new ItemStack(ConfigItems.itemWandRod, 1, 7);
    }

    /** 宏伟之木杖芯，meta 50 */
    public static ItemStack staffRodGreatwood() {
        return new ItemStack(ConfigItems.itemWandRod, 1, 50);
    }

    /** 黑曜石杖芯，meta 51 */
    public static ItemStack staffRodObsidian() {
        return new ItemStack(ConfigItems.itemWandRod, 1, 51);
    }

    /** 银树杖芯，meta 52 */
    public static ItemStack staffRodSilverwood() {
        return new ItemStack(ConfigItems.itemWandRod, 1, 52);
    }

    /** 寒霜杖芯，meta 53 */
    public static ItemStack staffRodIce() {
        return new ItemStack(ConfigItems.itemWandRod, 1, 53);
    }

    /** 石英杖芯，meta 54 */
    public static ItemStack staffRodQuartz() {
        return new ItemStack(ConfigItems.itemWandRod, 1, 54);
    }

    /** 甘蔗杖芯，meta 55 */
    public static ItemStack staffRodReed() {
        return new ItemStack(ConfigItems.itemWandRod, 1, 55);
    }

    /** 烈焰杖芯，meta 56 */
    public static ItemStack staffRodBlaze() {
        return new ItemStack(ConfigItems.itemWandRod, 1, 56);
    }

    /** 白骨杖芯，meta 57 */
    public static ItemStack staffRodBone() {
        return new ItemStack(ConfigItems.itemWandRod, 1, 57);
    }

    /** 元始杖芯，meta 100 */
    public static ItemStack staffRodPrimal() {
        return new ItemStack(ConfigItems.itemWandRod, 1, 100);
    }

    // ============================================================
    // 【Section 8】itemGolemCore 傀儡核心（meta 0~11, 100）
    // ============================================================

    /** 填充，meta 0 */
    public static ItemStack golemCoreFill() {
        return new ItemStack(ConfigItems.itemGolemCore, 1, 0);
    }

    /** 空置，meta 1 */
    public static ItemStack golemCoreEmpty() {
        return new ItemStack(ConfigItems.itemGolemCore, 1, 1);
    }

    /** 聚集，meta 2 */
    public static ItemStack golemCoreGather() {
        return new ItemStack(ConfigItems.itemGolemCore, 1, 2);
    }

    /** 收获，meta 3 */
    public static ItemStack golemCoreHarvest() {
        return new ItemStack(ConfigItems.itemGolemCore, 1, 3);
    }

    /** 守卫，meta 4 */
    public static ItemStack golemCoreGuard() {
        return new ItemStack(ConfigItems.itemGolemCore, 1, 4);
    }

    /** 移液，meta 5 */
    public static ItemStack golemCoreLiquid() {
        return new ItemStack(ConfigItems.itemGolemCore, 1, 5);
    }

    /** 炼金，meta 6 */
    public static ItemStack golemCoreAlchemy() {
        return new ItemStack(ConfigItems.itemGolemCore, 1, 6);
    }

    /** 砍伐，meta 7 */
    public static ItemStack golemCoreLumber() {
        return new ItemStack(ConfigItems.itemGolemCore, 1, 7);
    }

    /** 使用，meta 8 */
    public static ItemStack golemCoreUse() {
        return new ItemStack(ConfigItems.itemGolemCore, 1, 8);
    }

    /** 屠夫，meta 9 */
    public static ItemStack golemCoreButcher() {
        return new ItemStack(ConfigItems.itemGolemCore, 1, 9);
    }

    /** 排序，meta 10 */
    public static ItemStack golemCoreSorting() {
        return new ItemStack(ConfigItems.itemGolemCore, 1, 10);
    }

    /** 垂钓，meta 11 */
    public static ItemStack golemCoreFishing() {
        return new ItemStack(ConfigItems.itemGolemCore, 1, 11);
    }

    /** 空白（用于注魔合成基础核心），meta 100 */
    public static ItemStack golemCoreBlank() {
        return new ItemStack(ConfigItems.itemGolemCore, 1, 100);
    }

    // ============================================================
    // 【Section 9】itemGolemPlacer 傀儡放置物品（meta 0~7）
    // ============================================================

    /** 稻草傀儡，meta 0 */
    public static ItemStack golemStraw() {
        return new ItemStack(ConfigItems.itemGolemPlacer, 1, 0);
    }

    /** 木头傀儡，meta 1 */
    public static ItemStack golemWood() {
        return new ItemStack(ConfigItems.itemGolemPlacer, 1, 1);
    }

    /** 油脂傀儡，meta 2 */
    public static ItemStack golemTallow() {
        return new ItemStack(ConfigItems.itemGolemPlacer, 1, 2);
    }

    /** 粘土傀儡，meta 3 */
    public static ItemStack golemClay() {
        return new ItemStack(ConfigItems.itemGolemPlacer, 1, 3);
    }

    /** 血肉傀儡，meta 4 */
    public static ItemStack golemFlesh() {
        return new ItemStack(ConfigItems.itemGolemPlacer, 1, 4);
    }

    /** 石头傀儡，meta 5 */
    public static ItemStack golemStone() {
        return new ItemStack(ConfigItems.itemGolemPlacer, 1, 5);
    }

    /** 铁卫傀儡，meta 6 */
    public static ItemStack golemIron() {
        return new ItemStack(ConfigItems.itemGolemPlacer, 1, 6);
    }

    /** 神秘傀儡，meta 7 */
    public static ItemStack golemThaumium() {
        return new ItemStack(ConfigItems.itemGolemPlacer, 1, 7);
    }

    // ============================================================
    // 【Section 10】itemGolemUpgrade 傀儡升级（meta 0~5）
    // ============================================================

    /** 傀儡升级:风，meta 0 */
    public static ItemStack golemUpgradeAir() {
        return new ItemStack(ConfigItems.itemGolemUpgrade, 1, 0);
    }

    /** 傀儡升级:地，meta 1 */
    public static ItemStack golemUpgradeEarth() {
        return new ItemStack(ConfigItems.itemGolemUpgrade, 1, 1);
    }

    /** 傀儡升级:火，meta 2 */
    public static ItemStack golemUpgradeFire() {
        return new ItemStack(ConfigItems.itemGolemUpgrade, 1, 2);
    }

    /** 傀儡升级:水，meta 3 */
    public static ItemStack golemUpgradeWater() {
        return new ItemStack(ConfigItems.itemGolemUpgrade, 1, 3);
    }

    /** 傀儡升级:秩序，meta 4 */
    public static ItemStack golemUpgradeOrder() {
        return new ItemStack(ConfigItems.itemGolemUpgrade, 1, 4);
    }

    /** 傀儡升级:混沌，meta 5 */
    public static ItemStack golemUpgradeEntropy() {
        return new ItemStack(ConfigItems.itemGolemUpgrade, 1, 5);
    }

    // ============================================================
    // 【Section 11】itemGolemDecoration 傀儡饰件（meta 0~7）
    // ============================================================

    /** 礼帽，meta 0 */
    public static ItemStack golemDecoHat() {
        return new ItemStack(ConfigItems.itemGolemDecoration, 1, 0);
    }

    /** 眼镜，meta 1 */
    public static ItemStack golemDecoGlasses() {
        return new ItemStack(ConfigItems.itemGolemDecoration, 1, 1);
    }

    /** 蝴蝶结，meta 2 */
    public static ItemStack golemDecoBowtie() {
        return new ItemStack(ConfigItems.itemGolemDecoration, 1, 2);
    }

    /** 毡帽，meta 3 */
    public static ItemStack golemDecoFez() {
        return new ItemStack(ConfigItems.itemGolemDecoration, 1, 3);
    }

    /** 飞镖发射器，meta 4 */
    public static ItemStack golemDecoDart() {
        return new ItemStack(ConfigItems.itemGolemDecoration, 1, 4);
    }

    /** 面甲，meta 5 */
    public static ItemStack golemDecoVisor() {
        return new ItemStack(ConfigItems.itemGolemDecoration, 1, 5);
    }

    /** 铁甲，meta 6 */
    public static ItemStack golemDecoArmor() {
        return new ItemStack(ConfigItems.itemGolemDecoration, 1, 6);
    }

    /** 锏臂，meta 7 */
    public static ItemStack golemDecoHammer() {
        return new ItemStack(ConfigItems.itemGolemDecoration, 1, 7);
    }

    // ============================================================
    // 【Section 12】itemEldritchObject 邪术物品（meta 0~4）
    // ============================================================

    /** 邪术之眼，meta 0 */
    public static ItemStack eldritchEye() {
        return new ItemStack(ConfigItems.itemEldritchObject, 1, 0);
    }

    /** 血腥仪式（书），meta 1 */
    public static ItemStack crimsonRites() {
        return new ItemStack(ConfigItems.itemEldritchObject, 1, 1);
    }

    /** 符文石板，meta 2 */
    public static ItemStack runicTablet() {
        return new ItemStack(ConfigItems.itemEldritchObject, 1, 2);
    }

    /** 元始珍珠，meta 3 */
    public static ItemStack primalPearl() {
        return new ItemStack(ConfigItems.itemEldritchObject, 1, 3);
    }

    /** 邪术方尖碑敷设器，meta 4 */
    public static ItemStack eldritchObeliskPlacer() {
        return new ItemStack(ConfigItems.itemEldritchObject, 1, 4);
    }

    // ============================================================
    // 【Section 13】itemBaubleBlanks 饰品坯料（meta 0~3）
    // ============================================================

    /** 凡人护身符，meta 0 */
    public static ItemStack baubleAmulet() {
        return new ItemStack(ConfigItems.itemBaubleBlanks, 1, 0);
    }

    /** 凡人指环，meta 1 */
    public static ItemStack baubleRing() {
        return new ItemStack(ConfigItems.itemBaubleBlanks, 1, 1);
    }

    /** 凡人腰带，meta 2 */
    public static ItemStack baubleBelt() {
        return new ItemStack(ConfigItems.itemBaubleBlanks, 1, 2);
    }

    /** 学徒指环，meta 3 */
    public static ItemStack baubleApprenticeRing() {
        return new ItemStack(ConfigItems.itemBaubleBlanks, 1, 3);
    }

    // ============================================================
    // 【Section 14】itemLootbag 藏宝袋（meta 0~2）
    // ============================================================

    /** 寻常藏宝袋，meta 0 */
    public static ItemStack lootBagCommon() {
        return new ItemStack(ConfigItems.itemLootbag, 1, 0);
    }

    /** 非凡藏宝袋，meta 1 */
    public static ItemStack lootBagUncommon() {
        return new ItemStack(ConfigItems.itemLootbag, 1, 1);
    }

    /** 稀有藏宝袋，meta 2 */
    public static ItemStack lootBagRare() {
        return new ItemStack(ConfigItems.itemLootbag, 1, 2);
    }

    // ============================================================
    // 【Section 15】itemKey 钥匙（meta 0~1）
    // ============================================================

    /** 铁钥匙，meta 0 */
    public static ItemStack keyIron() {
        return new ItemStack(ConfigItems.itemKey, 1, 0);
    }

    /** 金钥匙，meta 1 */
    public static ItemStack keyGold() {
        return new ItemStack(ConfigItems.itemKey, 1, 1);
    }

    // ============================================================
    // 【Section 16】饰品带 meta（Runic / Vis / Hover）
    // ============================================================

    /** 符文护盾护身符，meta 0 */
    public static ItemStack amuletRunic() {
        return new ItemStack(ConfigItems.itemAmuletRunic, 1, 0);
    }

    /** 应急护盾护身符，meta 1 */
    public static ItemStack amuletRunicEmergency() {
        return new ItemStack(ConfigItems.itemAmuletRunic, 1, 1);
    }

    /** 防护指环，meta 0 */
    public static ItemStack ringRunicPlain() {
        return new ItemStack(ConfigItems.itemRingRunic, 1, 0);
    }

    /** 符文护盾指环，meta 1 */
    public static ItemStack ringRunic() {
        return new ItemStack(ConfigItems.itemRingRunic, 1, 1);
    }

    /** 充能符文护盾指环，meta 2 */
    public static ItemStack ringRunicCharged() {
        return new ItemStack(ConfigItems.itemRingRunic, 1, 2);
    }

    /** 再充能符文护盾指环，meta 3 */
    public static ItemStack ringRunicHealing() {
        return new ItemStack(ConfigItems.itemRingRunic, 1, 3);
    }

    /** 符文护盾腰带，meta 0 */
    public static ItemStack girdleRunic() {
        return new ItemStack(ConfigItems.itemGirdleRunic, 1, 0);
    }

    /** 冲击符文护盾腰带，meta 1 */
    public static ItemStack girdleRunicKinetic() {
        return new ItemStack(ConfigItems.itemGirdleRunic, 1, 1);
    }

    /** 魔力石，meta 0 */
    public static ItemStack amuletVisStone() {
        return new ItemStack(ConfigItems.itemAmuletVis, 1, 0);
    }

    /** 魔力存储护身符，meta 1 */
    public static ItemStack amuletVis() {
        return new ItemStack(ConfigItems.itemAmuletVis, 1, 1);
    }

    /** 奥法腰带 */
    public static ItemStack girdleHover() {
        return new ItemStack(ConfigItems.itemGirdleHover);
    }

    // ============================================================
    // 【Section 17】无 meta 的常用单品
    // ============================================================

    /** 僵尸之脑（真正可用的） */
    public static ItemStack zombieBrain() {
        return new ItemStack(ConfigItems.itemZombieBrain);
    }

    /** 天域之华（Wisp Essence，meta 0） */
    public static ItemStack wispEssence() {
        return new ItemStack(ConfigItems.itemWispEssence, 1, 0);
    }

    /** 晶化源质（Crystal Essence，meta 0） */
    public static ItemStack crystalEssence() {
        return new ItemStack(ConfigItems.itemCrystalEssence, 1, 0);
    }

    /** 笔与墨 */
    public static ItemStack inkwell() {
        return new ItemStack(ConfigItems.itemInkwell);
    }

    /** 魔导透镜 */
    public static ItemStack thaumometer() {
        return new ItemStack(ConfigItems.itemThaumometer);
    }

    /** 魔导手册 */
    public static ItemStack thaumonomicon() {
        return new ItemStack(ConfigItems.itemThaumonomicon);
    }

    /** 研究笔记 */
    public static ItemStack researchNotes() {
        return new ItemStack(ConfigItems.itemResearchNotes);
    }

    /** 揭示之护目镜 */
    public static ItemStack goggles() {
        return new ItemStack(ConfigItems.itemGoggles);
    }

    /** 旅行者之靴 */
    public static ItemStack bootsTraveller() {
        return new ItemStack(ConfigItems.itemBootsTraveller);
    }

    /** 奥能之扉 */
    public static ItemStack arcaneDoor() {
        return new ItemStack(ConfigItems.itemArcaneDoor);
    }

    /** 魔力手镜 */
    public static ItemStack handMirror() {
        return new ItemStack(ConfigItems.itemHandMirror);
    }

    /** 源质谐振器 */
    public static ItemStack resonator() {
        return new ItemStack(ConfigItems.itemResonator);
    }

    /** 箱子宝宝 */
    public static ItemStack trunkSpawner() {
        return new ItemStack(ConfigItems.itemTrunkSpawner);
    }

    /** 净化浴盐 */
    public static ItemStack bathSalts() {
        return new ItemStack(ConfigItems.itemBathSalts);
    }

    /** 桶装死亡之水 */
    public static ItemStack bucketDeath() {
        return new ItemStack(ConfigItems.itemBucketDeath);
    }

    /** 桶装净化之水 */
    public static ItemStack bucketPure() {
        return new ItemStack(ConfigItems.itemBucketPure);
    }

    /** 祛邪肥皂 */
    public static ItemStack sanitySoap() {
        return new ItemStack(ConfigItems.itemSanitySoap);
    }

    /** 神智检测仪 */
    public static ItemStack sanityChecker() {
        return new ItemStack(ConfigItems.itemSanityChecker);
    }

    /** 腐化瓶 */
    public static ItemStack bottleTaint() {
        return new ItemStack(ConfigItems.itemBottleTaint);
    }

    /** 凶险磁石 */
    public static ItemStack compassStone() {
        return new ItemStack(ConfigItems.itemCompassStone);
    }

    /** 元始杵 */
    public static ItemStack primalCrusher() {
        return new ItemStack(ConfigItems.itemPrimalCrusher);
    }

    /** 奥法缰绳 */
    public static ItemStack hoverHarness() {
        return new ItemStack(ConfigItems.itemHoverHarness);
    }

    /** 傀儡使铃铛 */
    public static ItemStack golemBell() {
        return new ItemStack(ConfigItems.itemGolemBell);
    }

    /** 三层肉饼 */
    public static ItemStack tripleMeatTreat() {
        return new ItemStack(ConfigItems.itemTripleMeatTreat);
    }

    /** 魔豆 */
    public static ItemStack manaBean() {
        return new ItemStack(ConfigItems.itemManaBean);
    }

    /** 鸡肉粒 */
    public static ItemStack nuggetChicken() {
        return new ItemStack(ConfigItems.itemNuggetChicken);
    }

    /** 牛肉粒 */
    public static ItemStack nuggetBeef() {
        return new ItemStack(ConfigItems.itemNuggetBeef);
    }

    /** 猪肉粒 */
    public static ItemStack nuggetPork() {
        return new ItemStack(ConfigItems.itemNuggetPork);
    }

    /** 鱼肉粒 */
    public static ItemStack nuggetFish() {
        return new ItemStack(ConfigItems.itemNuggetFish);
    }

    // ============================================================
    // 【Section 18】神秘锭系列工具/武器/盔甲（无 meta）
    // ============================================================

    public static ItemStack helmetThaumium() {
        return new ItemStack(ConfigItems.itemHelmetThaumium);
    }

    public static ItemStack chestThaumium() {
        return new ItemStack(ConfigItems.itemChestThaumium);
    }

    public static ItemStack legsThaumium() {
        return new ItemStack(ConfigItems.itemLegsThaumium);
    }

    public static ItemStack bootsThaumium() {
        return new ItemStack(ConfigItems.itemBootsThaumium);
    }

    public static ItemStack shovelThaumium() {
        return new ItemStack(ConfigItems.itemShovelThaumium);
    }

    public static ItemStack pickThaumium() {
        return new ItemStack(ConfigItems.itemPickThaumium);
    }

    public static ItemStack axeThaumium() {
        return new ItemStack(ConfigItems.itemAxeThaumium);
    }

    public static ItemStack swordThaumium() {
        return new ItemStack(ConfigItems.itemSwordThaumium);
    }

    public static ItemStack hoeThaumium() {
        return new ItemStack(ConfigItems.itemHoeThaumium);
    }

    // ============================================================
    // 【Section 19】元素工具（炽心镐、后土铲等，无 meta）
    // ============================================================

    public static ItemStack shovelElemental() {
        return new ItemStack(ConfigItems.itemShovelElemental);
    }

    public static ItemStack pickElemental() {
        return new ItemStack(ConfigItems.itemPickElemental);
    }

    public static ItemStack axeElemental() {
        return new ItemStack(ConfigItems.itemAxeElemental);
    }

    public static ItemStack swordElemental() {
        return new ItemStack(ConfigItems.itemSwordElemental);
    }

    public static ItemStack hoeElemental() {
        return new ItemStack(ConfigItems.itemHoeElemental);
    }

    // ============================================================
    // 【Section 20】虚空金属系列工具/武器/盔甲（无 meta）
    // ============================================================

    public static ItemStack helmetVoid() {
        return new ItemStack(ConfigItems.itemHelmetVoid);
    }

    public static ItemStack chestVoid() {
        return new ItemStack(ConfigItems.itemChestVoid);
    }

    public static ItemStack legsVoid() {
        return new ItemStack(ConfigItems.itemLegsVoid);
    }

    public static ItemStack bootsVoid() {
        return new ItemStack(ConfigItems.itemBootsVoid);
    }

    public static ItemStack shovelVoid() {
        return new ItemStack(ConfigItems.itemShovelVoid);
    }

    public static ItemStack pickVoid() {
        return new ItemStack(ConfigItems.itemPickVoid);
    }

    public static ItemStack axeVoid() {
        return new ItemStack(ConfigItems.itemAxeVoid);
    }

    public static ItemStack swordVoid() {
        return new ItemStack(ConfigItems.itemSwordVoid);
    }

    public static ItemStack hoeVoid() {
        return new ItemStack(ConfigItems.itemHoeVoid);
    }

    // ============================================================
    // 【Section 21】要塞盔甲 / 虚空法袍 / 法袍（无 meta）
    // ============================================================

    public static ItemStack helmetFortress() {
        return new ItemStack(ConfigItems.itemHelmetFortress);
    }

    public static ItemStack chestFortress() {
        return new ItemStack(ConfigItems.itemChestFortress);
    }

    public static ItemStack legsFortress() {
        return new ItemStack(ConfigItems.itemLegsFortress);
    }

    public static ItemStack helmetVoidRobe() {
        return new ItemStack(ConfigItems.itemHelmetVoidRobe);
    }

    public static ItemStack chestVoidRobe() {
        return new ItemStack(ConfigItems.itemChestVoidRobe);
    }

    public static ItemStack legsVoidRobe() {
        return new ItemStack(ConfigItems.itemLegsVoidRobe);
    }

    public static ItemStack chestRobe() {
        return new ItemStack(ConfigItems.itemChestRobe);
    }

    public static ItemStack legsRobe() {
        return new ItemStack(ConfigItems.itemLegsRobe);
    }

    public static ItemStack bootsRobe() {
        return new ItemStack(ConfigItems.itemBootsRobe);
    }

    // ============================================================
    // 【Section 22】血腥邪徒盔甲（无 meta，部分 meta 由 NBT 决定）
    // ============================================================

    public static ItemStack helmetCultistRobe() {
        return new ItemStack(ConfigItems.itemHelmetCultistRobe);
    }

    public static ItemStack chestCultistRobe() {
        return new ItemStack(ConfigItems.itemChestCultistRobe);
    }

    public static ItemStack legsCultistRobe() {
        return new ItemStack(ConfigItems.itemLegsCultistRobe);
    }

    public static ItemStack bootsCultist() {
        return new ItemStack(ConfigItems.itemBootsCultist);
    }

    public static ItemStack helmetCultistPlate() {
        return new ItemStack(ConfigItems.itemHelmetCultistPlate);
    }

    public static ItemStack chestCultistPlate() {
        return new ItemStack(ConfigItems.itemChestCultistPlate);
    }

    public static ItemStack legsCultistPlate() {
        return new ItemStack(ConfigItems.itemLegsCultistPlate);
    }

    public static ItemStack helmetCultistLeaderPlate() {
        return new ItemStack(ConfigItems.itemHelmetCultistLeaderPlate);
    }

    public static ItemStack chestCultistLeaderPlate() {
        return new ItemStack(ConfigItems.itemChestCultistLeaderPlate);
    }

    public static ItemStack legsCultistLeaderPlate() {
        return new ItemStack(ConfigItems.itemLegsCultistLeaderPlate);
    }

    // ============================================================
    // 【Section 23】其他武器
    // ============================================================

    /** 骨弓 */
    public static ItemStack bowBone() {
        return new ItemStack(ConfigItems.itemBowBone);
    }

    /** 血腥之刃 */
    public static ItemStack swordCrimson() {
        return new ItemStack(ConfigItems.itemSwordCrimson);
    }

    // ============================================================
    // 【Section 24】法杖核心
    // ============================================================

    public static ItemStack focusFire() {
        return new ItemStack(ConfigItems.itemFocusFire);
    }

    public static ItemStack focusFrost() {
        return new ItemStack(ConfigItems.itemFocusFrost);
    }

    public static ItemStack focusShock() {
        return new ItemStack(ConfigItems.itemFocusShock);
    }

    public static ItemStack focusTrade() {
        return new ItemStack(ConfigItems.itemFocusTrade);
    }

    public static ItemStack focusExcavation() {
        return new ItemStack(ConfigItems.itemFocusExcavation);
    }

    public static ItemStack focusHellbat() {
        return new ItemStack(ConfigItems.itemFocusHellbat);
    }

    public static ItemStack focusWarding() {
        return new ItemStack(ConfigItems.itemFocusWarding);
    }

    public static ItemStack focusPrimal() {
        return new ItemStack(ConfigItems.itemFocusPrimal);
    }

    public static ItemStack focusPortableHole() {
        return new ItemStack(ConfigItems.itemFocusPortableHole);
    }

    public static ItemStack focusPech() {
        return new ItemStack(ConfigItems.itemFocusPech);
    }

    public static ItemStack focusPouch() {
        return new ItemStack(ConfigItems.itemFocusPouch);
    }

    // ============================================================
    // 【Section 25】原版物品快捷方式（配方里常用的）
    // ============================================================
    // 这些不是 TC 的，但配方里经常和 TC 物品一起用，一并封装。
    // ============================================================

    public static ItemStack diamond() {
        return new ItemStack(Items.diamond);
    }

    public static ItemStack emerald() {
        return new ItemStack(Items.emerald);
    }

    public static ItemStack goldIngot() {
        return new ItemStack(Items.gold_ingot);
    }

    public static ItemStack ironIngot() {
        return new ItemStack(Items.iron_ingot);
    }

    public static ItemStack goldNugget() {
        return new ItemStack(Items.gold_nugget);
    }

    public static ItemStack ironBlock() {
        return new ItemStack(Blocks.iron_block);
    }

    public static ItemStack goldBlock() {
        return new ItemStack(Blocks.gold_block);
    }

    public static ItemStack obsidian() {
        return new ItemStack(Blocks.obsidian);
    }

    public static ItemStack glowstoneDust() {
        return new ItemStack(Items.glowstone_dust);
    }

    public static ItemStack blazeRod() {
        return new ItemStack(Items.blaze_rod);
    }

    public static ItemStack blazePowder() {
        return new ItemStack(Items.blaze_powder);
    }

    public static ItemStack enderPearl() {
        return new ItemStack(Items.ender_pearl);
    }

    public static ItemStack apple() {
        return new ItemStack(Items.apple);
    }

    public static ItemStack goldenApple() {
        return new ItemStack(Items.golden_apple, 1, 0);
    }

    public static ItemStack notchApple() {
        return new ItemStack(Items.golden_apple, 1, 1);
    }

    public static ItemStack paper() {
        return new ItemStack(Items.paper);
    }

    public static ItemStack leather() {
        return new ItemStack(Items.leather);
    }

    public static ItemStack string() {
        return new ItemStack(Items.string);
    }

    public static ItemStack stick() {
        return new ItemStack(Items.stick);
    }

    public static ItemStack feather() {
        return new ItemStack(Items.feather);
    }

    public static ItemStack clayBall() {
        return new ItemStack(Items.clay_ball);
    }

    /** 水桶 */
    public static ItemStack waterBucket() {
        return new ItemStack(Items.water_bucket);
    }

    /** 岩浆桶 */
    public static ItemStack lavaBucket() {
        return new ItemStack(Items.lava_bucket);
    }

}
