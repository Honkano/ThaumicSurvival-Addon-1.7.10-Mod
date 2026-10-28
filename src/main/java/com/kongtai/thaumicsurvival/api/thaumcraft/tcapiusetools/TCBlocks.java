package com.kongtai.thaumicsurvival.api.thaumcraft.tcapiusetools;

import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

import thaumcraft.common.config.ConfigBlocks;

/**
 * ============================================================
 * 【神秘时代方块大词典】
 * ============================================================
 * 用途：把 ConfigBlocks.blockXxx + meta 翻译成有名字的静态方法。
 *
 * 【重要说明】
 * 1. 每个方法返回 ItemStack（ItemBlock 形式），不是 Block。
 * 写配方用 ItemStack，放置方块直接 ConfigBlocks.blockXxx。
 *
 * 2. meta 来源：ConfigBlocks.registerBlocks() + 翻译文件 tile.xxx.X.name。
 *
 * 3. 以下方块没有“正常物品形式”，本类不封装：
 * blockFluidPure / blockFluidDeath → 用 TCItems.bucketPure() / bucketDeath()
 * blockDoubleSlabWood / Stone → 用 blockSlabWood() / blockSlabStone()
 * blockEldritchPortal / Nothing → 技术性方块
 *
 * 4. TC4 停留在 1.7.10 最终版，meta 不会再变。
 * ============================================================
 */
public class TCBlocks {

    // ============================================================
    // 【Section 1】blockCustomOre 自定义矿石（meta 0~7）
    // ============================================================

    /** 朱砂矿石，meta 0 */
    public static ItemStack oreCinnabar() {
        return new ItemStack(ConfigBlocks.blockCustomOre, 1, 0);
    }

    /** 风之蕴魔石，meta 1 */
    public static ItemStack oreInfusedAir() {
        return new ItemStack(ConfigBlocks.blockCustomOre, 1, 1);
    }

    /** 火之蕴魔石，meta 2 */
    public static ItemStack oreInfusedFire() {
        return new ItemStack(ConfigBlocks.blockCustomOre, 1, 2);
    }

    /** 水之蕴魔石，meta 3 */
    public static ItemStack oreInfusedWater() {
        return new ItemStack(ConfigBlocks.blockCustomOre, 1, 3);
    }

    /** 地之蕴魔石，meta 4 */
    public static ItemStack oreInfusedEarth() {
        return new ItemStack(ConfigBlocks.blockCustomOre, 1, 4);
    }

    /** 秩序蕴魔石，meta 5 */
    public static ItemStack oreInfusedOrder() {
        return new ItemStack(ConfigBlocks.blockCustomOre, 1, 5);
    }

    /** 混沌蕴魔石，meta 6 */
    public static ItemStack oreInfusedEntropy() {
        return new ItemStack(ConfigBlocks.blockCustomOre, 1, 6);
    }

    /** 琥珀矿石，meta 7 */
    public static ItemStack oreAmber() {
        return new ItemStack(ConfigBlocks.blockCustomOre, 1, 7);
    }

    // ============================================================
    // 【Section 2】blockMagicalLog 魔法原木（meta 0~1）
    // ============================================================

    /** 宏伟之木原木，meta 0 */
    public static ItemStack logGreatwood() {
        return new ItemStack(ConfigBlocks.blockMagicalLog, 1, 0);
    }

    /** 银树原木，meta 1 */
    public static ItemStack logSilverwood() {
        return new ItemStack(ConfigBlocks.blockMagicalLog, 1, 1);
    }

    // ============================================================
    // 【Section 3】blockMagicalLeaves 魔法树叶（meta 0~1）
    // ============================================================

    /** 宏伟之木树叶，meta 0 */
    public static ItemStack leavesGreatwood() {
        return new ItemStack(ConfigBlocks.blockMagicalLeaves, 1, 0);
    }

    /** 银树树叶，meta 1 */
    public static ItemStack leavesSilverwood() {
        return new ItemStack(ConfigBlocks.blockMagicalLeaves, 1, 1);
    }

    // ============================================================
    // 【Section 4】blockCustomPlant 自定义植物（meta 0~5）
    // ============================================================

    /** 宏伟之木树苗，meta 0 */
    public static ItemStack saplingGreatwood() {
        return new ItemStack(ConfigBlocks.blockCustomPlant, 1, 0);
    }

    /** 银树树苗，meta 1 */
    public static ItemStack saplingSilverwood() {
        return new ItemStack(ConfigBlocks.blockCustomPlant, 1, 1);
    }

    /** 水银花（Shimmerleaf），meta 2 */
    public static ItemStack shimmerleaf() {
        return new ItemStack(ConfigBlocks.blockCustomPlant, 1, 2);
    }

    /** 火焰草（Cinderpearl），meta 3 */
    public static ItemStack cinderpearl() {
        return new ItemStack(ConfigBlocks.blockCustomPlant, 1, 3);
    }

    /** 天域花（Ethereal Bloom），meta 4 */
    public static ItemStack etherealBloom() {
        return new ItemStack(ConfigBlocks.blockCustomPlant, 1, 4);
    }

    /** 纤毛菇（Taint Fibres 植物形态），meta 5 */
    public static ItemStack taintFibresPlant() {
        return new ItemStack(ConfigBlocks.blockCustomPlant, 1, 5);
    }

    // ============================================================
    // 【Section 5】blockCosmeticSolid 装饰固体（meta 0~15）
    // ============================================================

    /** 黑曜石图腾，meta 0 */
    public static ItemStack obsidianTotem() {
        return new ItemStack(ConfigBlocks.blockCosmeticSolid, 1, 0);
    }

    /** 黑曜石瓦块，meta 1 */
    public static ItemStack obsidianTile() {
        return new ItemStack(ConfigBlocks.blockCosmeticSolid, 1, 1);
    }

    /** 旅行者铺路石，meta 2 */
    public static ItemStack paveTravel() {
        return new ItemStack(ConfigBlocks.blockCosmeticSolid, 1, 2);
    }

    /** 守卫者铺路石，meta 3 */
    public static ItemStack paveWard() {
        return new ItemStack(ConfigBlocks.blockCosmeticSolid, 1, 3);
    }

    /** 神秘方块，meta 4 */
    public static ItemStack blockThaumium() {
        return new ItemStack(ConfigBlocks.blockCosmeticSolid, 1, 4);
    }

    /** 油脂方块，meta 5 */
    public static ItemStack blockTallow() {
        return new ItemStack(ConfigBlocks.blockCosmeticSolid, 1, 5);
    }

    /** 奥术石块，meta 6 */
    public static ItemStack arcaneStone() {
        return new ItemStack(ConfigBlocks.blockCosmeticSolid, 1, 6);
    }

    /** 奥术石砖，meta 7 */
    public static ItemStack arcaneBrick() {
        return new ItemStack(ConfigBlocks.blockCosmeticSolid, 1, 7);
    }

    /** 蕴灵黑曜石图腾，meta 8 */
    public static ItemStack obsidianTotemCharged() {
        return new ItemStack(ConfigBlocks.blockCosmeticSolid, 1, 8);
    }

    /** 傀儡脚镣，meta 9 */
    public static ItemStack golemFetter() {
        return new ItemStack(ConfigBlocks.blockCosmeticSolid, 1, 9);
    }

    /** 激活傀儡脚镣，meta 10 */
    public static ItemStack golemFetterActive() {
        return new ItemStack(ConfigBlocks.blockCosmeticSolid, 1, 10);
    }

    /** 荒古石头，meta 11 */
    public static ItemStack eldritchStone() {
        return new ItemStack(ConfigBlocks.blockCosmeticSolid, 1, 11);
    }

    /** 荒古岩石，meta 12 */
    public static ItemStack eldritchRock() {
        return new ItemStack(ConfigBlocks.blockCosmeticSolid, 1, 12);
    }

    /** 荒古石头（变体），meta 13 */
    public static ItemStack eldritchStone2() {
        return new ItemStack(ConfigBlocks.blockCosmeticSolid, 1, 13);
    }

    /** 陈年石头，meta 14 */
    public static ItemStack ancientStone() {
        return new ItemStack(ConfigBlocks.blockCosmeticSolid, 1, 14);
    }

    /** 荒古石座，meta 15 */
    public static ItemStack eldritchPedestal() {
        return new ItemStack(ConfigBlocks.blockCosmeticSolid, 1, 15);
    }

    // ============================================================
    // 【Section 6】blockCosmeticOpaque 装饰不透明（meta 0~2）
    // ============================================================

    /** 琥珀方块，meta 0 */
    public static ItemStack blockAmber() {
        return new ItemStack(ConfigBlocks.blockCosmeticOpaque, 1, 0);
    }

    /** 琥珀砖块，meta 1 */
    public static ItemStack brickAmber() {
        return new ItemStack(ConfigBlocks.blockCosmeticOpaque, 1, 1);
    }

    /** 守卫者玻璃，meta 2 */
    public static ItemStack wardedGlass() {
        return new ItemStack(ConfigBlocks.blockCosmeticOpaque, 1, 2);
    }

    // ============================================================
    // 【Section 7】blockWoodenDevice 木制设备（meta 0~8）
    // ============================================================

    /** 奥术风箱，meta 0 */
    public static ItemStack bellows() {
        return new ItemStack(ConfigBlocks.blockWoodenDevice, 1, 0);
    }

    /** 奥术之耳，meta 1 */
    public static ItemStack arcaneEar() {
        return new ItemStack(ConfigBlocks.blockWoodenDevice, 1, 1);
    }

    /** 奥术压力盘，meta 2 */
    public static ItemStack arcanePressurePlate() {
        return new ItemStack(ConfigBlocks.blockWoodenDevice, 1, 2);
    }

    /** 奥术钻探机基座，meta 4 */
    public static ItemStack arcaneBoreBase() {
        return new ItemStack(ConfigBlocks.blockWoodenDevice, 1, 4);
    }

    /** 奥术钻探机，meta 5 */
    public static ItemStack arcaneBore() {
        return new ItemStack(ConfigBlocks.blockWoodenDevice, 1, 5);
    }

    /** 宏伟之树木板，meta 6 */
    public static ItemStack plankGreatwood() {
        return new ItemStack(ConfigBlocks.blockWoodenDevice, 1, 6);
    }

    /** 银树木板，meta 7 */
    public static ItemStack plankSilverwood() {
        return new ItemStack(ConfigBlocks.blockWoodenDevice, 1, 7);
    }

    /** 旗帜（空），meta 8，颜色由 NBT "color" 决定 */
    public static ItemStack banner() {
        return new ItemStack(ConfigBlocks.blockWoodenDevice, 1, 8);
    }

    // ============================================================
    // 【Section 8】blockMetalDevice 金属设备（meta 0~14）
    // ============================================================

    /** 坩埚，meta 0 */
    public static ItemStack crucible() {
        return new ItemStack(ConfigBlocks.blockMetalDevice, 1, 0);
    }

    /** 奥术蒸馏器，meta 1 */
    public static ItemStack alembic() {
        return new ItemStack(ConfigBlocks.blockMetalDevice, 1, 1);
    }

    /** 魔力充能中继器，meta 2 */
    public static ItemStack visChargeRelay() {
        return new ItemStack(ConfigBlocks.blockMetalDevice, 1, 2);
    }

    /** 高级炼金构材，meta 3 */
    public static ItemStack advAlchemyConstruct() {
        return new ItemStack(ConfigBlocks.blockMetalDevice, 1, 3);
    }

    /** 物品格栅，meta 5 */
    public static ItemStack grate() {
        return new ItemStack(ConfigBlocks.blockMetalDevice, 1, 5);
    }

    /** 物品格栅（关闭），meta 6 */
    public static ItemStack grateClosed() {
        return new ItemStack(ConfigBlocks.blockMetalDevice, 1, 6);
    }

    /** 奥术灯，meta 7 */
    public static ItemStack arcaneLamp() {
        return new ItemStack(ConfigBlocks.blockMetalDevice, 1, 7);
    }

    /** 催生灯，meta 8 */
    public static ItemStack lampGrowth() {
        return new ItemStack(ConfigBlocks.blockMetalDevice, 1, 8);
    }

    /** 炼金构材，meta 9 */
    public static ItemStack alchemyConstruct() {
        return new ItemStack(ConfigBlocks.blockMetalDevice, 1, 9);
    }

    /** 神秘炼金塔（下），meta 10 */
    public static ItemStack thaumatorium() {
        return new ItemStack(ConfigBlocks.blockMetalDevice, 1, 10);
    }

    /** 神秘炼金塔（上），meta 11 */
    public static ItemStack thaumatoriumTop() {
        return new ItemStack(ConfigBlocks.blockMetalDevice, 1, 11);
    }

    /** 记忆矩阵，meta 12 */
    public static ItemStack mnemonicMatrix() {
        return new ItemStack(ConfigBlocks.blockMetalDevice, 1, 12);
    }

    /** 育种灯，meta 13 */
    public static ItemStack lampFertility() {
        return new ItemStack(ConfigBlocks.blockMetalDevice, 1, 13);
    }

    /** 魔力中继器，meta 14 */
    public static ItemStack visRelay() {
        return new ItemStack(ConfigBlocks.blockMetalDevice, 1, 14);
    }

    // ============================================================
    // 【Section 9】blockStoneDevice 石头设备（meta 0~14）
    // ============================================================

    /** 炼金炉，meta 0 */
    public static ItemStack alchemyFurnace() {
        return new ItemStack(ConfigBlocks.blockStoneDevice, 1, 0);
    }

    /** 奥术基座，meta 1 */
    public static ItemStack arcanePedestal() {
        return new ItemStack(ConfigBlocks.blockStoneDevice, 1, 1);
    }

    /** 符文矩阵，meta 2 */
    public static ItemStack infusionMatrix() {
        return new ItemStack(ConfigBlocks.blockStoneDevice, 1, 2);
    }

    /** 注魔桩，meta 3 */
    public static ItemStack infusionPillar() {
        return new ItemStack(ConfigBlocks.blockStoneDevice, 1, 3);
    }

    /** 注魔桩，meta 4 */
    public static ItemStack infusionPillar2() {
        return new ItemStack(ConfigBlocks.blockStoneDevice, 1, 4);
    }

    /** 法杖充能基座，meta 5 */
    public static ItemStack wandPedestal() {
        return new ItemStack(ConfigBlocks.blockStoneDevice, 1, 5);
    }

    /** 注魔桩，meta 6 */
    public static ItemStack infusionPillar3() {
        return new ItemStack(ConfigBlocks.blockStoneDevice, 1, 6);
    }

    /** 注魔桩，meta 7 */
    public static ItemStack infusionPillar4() {
        return new ItemStack(ConfigBlocks.blockStoneDevice, 1, 7);
    }

    /** 复合充能核心，meta 8 */
    public static ItemStack wandPedestalFocus() {
        return new ItemStack(ConfigBlocks.blockStoneDevice, 1, 8);
    }

    /** 节点稳定器，meta 9 */
    public static ItemStack nodeStabilizer() {
        return new ItemStack(ConfigBlocks.blockStoneDevice, 1, 9);
    }

    /** 高级节点稳定器，meta 10 */
    public static ItemStack nodeStabilizerAdv() {
        return new ItemStack(ConfigBlocks.blockStoneDevice, 1, 10);
    }

    /** 节点换能器，meta 11 */
    public static ItemStack nodeTransducer() {
        return new ItemStack(ConfigBlocks.blockStoneDevice, 1, 11);
    }

    /** 奥术浴场，meta 12 */
    public static ItemStack arcaneSpa() {
        return new ItemStack(ConfigBlocks.blockStoneDevice, 1, 12);
    }

    /** 核心镶饰台，meta 13 */
    public static ItemStack focalManipulator() {
        return new ItemStack(ConfigBlocks.blockStoneDevice, 1, 13);
    }

    /** 咒波洗涤器，meta 14 */
    public static ItemStack fluxScrubber() {
        return new ItemStack(ConfigBlocks.blockStoneDevice, 1, 14);
    }

    // ============================================================
    // 【Section 10】blockTable 桌子 / 工作台（meta 0~15）
    // ============================================================

    /** 台桌，meta 0 */
    public static ItemStack table() {
        return new ItemStack(ConfigBlocks.blockTable, 1, 0);
    }

    /** 研究台，meta 1（放置时由两个台桌 + 笔与墨组合，物品形式 meta 1） */
    public static ItemStack researchTable() {
        return new ItemStack(ConfigBlocks.blockTable, 1, 1);
    }

    /** 解构工作台，meta 14 */
    public static ItemStack deconstructionTable() {
        return new ItemStack(ConfigBlocks.blockTable, 1, 14);
    }

    /** 奥术工作台，meta 15 */
    public static ItemStack arcaneWorkbench() {
        return new ItemStack(ConfigBlocks.blockTable, 1, 15);
    }

    // ============================================================
    // 【Section 11】blockJar 罐子（meta 0~3）
    // ============================================================

    /** 源质罐子（空），meta 0 */
    public static ItemStack jar() {
        return new ItemStack(ConfigBlocks.blockJar, 1, 0);
    }

    /** 缸中之脑，meta 1 */
    public static ItemStack jarBrain() {
        return new ItemStack(ConfigBlocks.blockJar, 1, 1);
    }

    /** 缸中节点，meta 2 */
    public static ItemStack jarNode() {
        return new ItemStack(ConfigBlocks.blockJar, 1, 2);
    }

    /** 虚空罐子，meta 3 */
    public static ItemStack jarVoid() {
        return new ItemStack(ConfigBlocks.blockJar, 1, 3);
    }

    // ============================================================
    // 【Section 12】blockCandle 蜡烛（meta 0~15，16 种颜色）
    // ============================================================

    public static ItemStack candleWhite() {
        return new ItemStack(ConfigBlocks.blockCandle, 1, 0);
    }

    public static ItemStack candleOrange() {
        return new ItemStack(ConfigBlocks.blockCandle, 1, 1);
    }

    public static ItemStack candleMagenta() {
        return new ItemStack(ConfigBlocks.blockCandle, 1, 2);
    }

    public static ItemStack candleLightBlue() {
        return new ItemStack(ConfigBlocks.blockCandle, 1, 3);
    }

    public static ItemStack candleYellow() {
        return new ItemStack(ConfigBlocks.blockCandle, 1, 4);
    }

    public static ItemStack candleLime() {
        return new ItemStack(ConfigBlocks.blockCandle, 1, 5);
    }

    public static ItemStack candlePink() {
        return new ItemStack(ConfigBlocks.blockCandle, 1, 6);
    }

    public static ItemStack candleGray() {
        return new ItemStack(ConfigBlocks.blockCandle, 1, 7);
    }

    public static ItemStack candleLightGray() {
        return new ItemStack(ConfigBlocks.blockCandle, 1, 8);
    }

    public static ItemStack candleCyan() {
        return new ItemStack(ConfigBlocks.blockCandle, 1, 9);
    }

    public static ItemStack candlePurple() {
        return new ItemStack(ConfigBlocks.blockCandle, 1, 10);
    }

    public static ItemStack candleBlue() {
        return new ItemStack(ConfigBlocks.blockCandle, 1, 11);
    }

    public static ItemStack candleBrown() {
        return new ItemStack(ConfigBlocks.blockCandle, 1, 12);
    }

    public static ItemStack candleGreen() {
        return new ItemStack(ConfigBlocks.blockCandle, 1, 13);
    }

    public static ItemStack candleRed() {
        return new ItemStack(ConfigBlocks.blockCandle, 1, 14);
    }

    public static ItemStack candleBlack() {
        return new ItemStack(ConfigBlocks.blockCandle, 1, 15);
    }

    // ============================================================
    // 【Section 13】blockAiry 灵气方块（meta 0~5, 10~12）
    // ============================================================

    /** 灵气节点，meta 0 */
    public static ItemStack node() {
        return new ItemStack(ConfigBlocks.blockAiry, 1, 0);
    }

    /** 闪耀之光，meta 1 */
    public static ItemStack nitorGlow() {
        return new ItemStack(ConfigBlocks.blockAiry, 1, 1);
    }

    /** 闪烁之光，meta 2 */
    public static ItemStack flickerGlow() {
        return new ItemStack(ConfigBlocks.blockAiry, 1, 2);
    }

    /** 闪烁之光（变体），meta 3 */
    public static ItemStack flickerGlow2() {
        return new ItemStack(ConfigBlocks.blockAiry, 1, 3);
    }

    /** 守护之光，meta 4 */
    public static ItemStack wardingGlow() {
        return new ItemStack(ConfigBlocks.blockAiry, 1, 4);
    }

    /** 充能灵气节点，meta 5 */
    public static ItemStack energizedNode() {
        return new ItemStack(ConfigBlocks.blockAiry, 1, 5);
    }

    /** 静电力场，meta 10 */
    public static ItemStack staticField() {
        return new ItemStack(ConfigBlocks.blockAiry, 1, 10);
    }

    /** 弱化力场，meta 11 */
    public static ItemStack weakenField() {
        return new ItemStack(ConfigBlocks.blockAiry, 1, 11);
    }

    /** 封绝力场，meta 12 */
    public static ItemStack sealField() {
        return new ItemStack(ConfigBlocks.blockAiry, 1, 12);
    }

    // ============================================================
    // 【Section 14】blockEldritch 邪术方块（meta 0~10）
    // ============================================================

    /** 邪术祭坛，meta 0 */
    public static ItemStack eldritchAltar() {
        return new ItemStack(ConfigBlocks.blockEldritch, 1, 0);
    }

    /** 邪术方尖碑，meta 1 */
    public static ItemStack eldritchObelisk() {
        return new ItemStack(ConfigBlocks.blockEldritch, 1, 1);
    }

    /** 邪术方尖碑，meta 2 */
    public static ItemStack eldritchObelisk2() {
        return new ItemStack(ConfigBlocks.blockEldritch, 1, 2);
    }

    /** 邪术拱顶石，meta 3 */
    public static ItemStack eldritchCap() {
        return new ItemStack(ConfigBlocks.blockEldritch, 1, 3);
    }

    /** 发光荒古石头，meta 4 */
    public static ItemStack eldritchStoneGlow() {
        return new ItemStack(ConfigBlocks.blockEldritch, 1, 4);
    }

    /** 雕文石头，meta 5 */
    public static ItemStack eldritchStoneGlyph() {
        return new ItemStack(ConfigBlocks.blockEldritch, 1, 5);
    }

    /** 荒古门廊，meta 7 */
    public static ItemStack eldritchDoorway() {
        return new ItemStack(ConfigBlocks.blockEldritch, 1, 7);
    }

    /** 荒古锁具，meta 8 */
    public static ItemStack eldritchLock() {
        return new ItemStack(ConfigBlocks.blockEldritch, 1, 8);
    }

    /** 荒古锁孔，meta 9 */
    public static ItemStack eldritchKeyhole() {
        return new ItemStack(ConfigBlocks.blockEldritch, 1, 9);
    }

    /** 符文石头，meta 10 */
    public static ItemStack eldritchStoneRune() {
        return new ItemStack(ConfigBlocks.blockEldritch, 1, 10);
    }

    // ============================================================
    // 【Section 15】blockCrystal 水晶方块（meta 0~7）
    // ============================================================

    /** 风之魔晶，meta 0 */
    public static ItemStack crystalAir() {
        return new ItemStack(ConfigBlocks.blockCrystal, 1, 0);
    }

    /** 火之魔晶，meta 1 */
    public static ItemStack crystalFire() {
        return new ItemStack(ConfigBlocks.blockCrystal, 1, 1);
    }

    /** 水之魔晶，meta 2 */
    public static ItemStack crystalWater() {
        return new ItemStack(ConfigBlocks.blockCrystal, 1, 2);
    }

    /** 地之魔晶，meta 3 */
    public static ItemStack crystalEarth() {
        return new ItemStack(ConfigBlocks.blockCrystal, 1, 3);
    }

    /** 秩序魔晶，meta 4 */
    public static ItemStack crystalOrder() {
        return new ItemStack(ConfigBlocks.blockCrystal, 1, 4);
    }

    /** 混沌魔晶，meta 5 */
    public static ItemStack crystalEntropy() {
        return new ItemStack(ConfigBlocks.blockCrystal, 1, 5);
    }

    /** 复相魔晶，meta 6 */
    public static ItemStack crystalBalanced() {
        return new ItemStack(ConfigBlocks.blockCrystal, 1, 6);
    }

    /** 诡异魔晶，meta 7 */
    public static ItemStack crystalEldritch() {
        return new ItemStack(ConfigBlocks.blockCrystal, 1, 7);
    }

    // ============================================================
    // 【Section 16】blockTube 源质管道（meta 0~7）
    // ============================================================

    /** 源质管道，meta 0 */
    public static ItemStack tube() {
        return new ItemStack(ConfigBlocks.blockTube, 1, 0);
    }

    /** 源质阀门，meta 1 */
    public static ItemStack tubeValve() {
        return new ItemStack(ConfigBlocks.blockTube, 1, 1);
    }

    /** 奥法离心机，meta 2 */
    public static ItemStack centrifuge() {
        return new ItemStack(ConfigBlocks.blockTube, 1, 2);
    }

    /** 过滤源质管道，meta 3 */
    public static ItemStack tubeFilter() {
        return new ItemStack(ConfigBlocks.blockTube, 1, 3);
    }

    /** 源质缓存器，meta 4 */
    public static ItemStack tubeBuffer() {
        return new ItemStack(ConfigBlocks.blockTube, 1, 4);
    }

    /** 限定源质管道，meta 5 */
    public static ItemStack tubeRestrict() {
        return new ItemStack(ConfigBlocks.blockTube, 1, 5);
    }

    /** 定向源质管道，meta 6 */
    public static ItemStack tubeOneway() {
        return new ItemStack(ConfigBlocks.blockTube, 1, 6);
    }

    /** 源质结晶器，meta 7 */
    public static ItemStack essentiaCrystalizer() {
        return new ItemStack(ConfigBlocks.blockTube, 1, 7);
    }

    // ============================================================
    // 【Section 17】blockMirror 魔镜（meta 0, 6）
    // ============================================================

    /** 魔镜（物品传输），meta 0 */
    public static ItemStack mirror() {
        return new ItemStack(ConfigBlocks.blockMirror, 1, 0);
    }

    /** 源质之镜，meta 6 */
    public static ItemStack mirrorEssentia() {
        return new ItemStack(ConfigBlocks.blockMirror, 1, 6);
    }

    // ============================================================
    // 【Section 18】blockTaint 污染方块（meta 0~2）
    // ============================================================

    /** 陈年污点，meta 0 */
    public static ItemStack taintCrust() {
        return new ItemStack(ConfigBlocks.blockTaint, 1, 0);
    }

    /** 腐化泥土，meta 1 */
    public static ItemStack taintSoil() {
        return new ItemStack(ConfigBlocks.blockTaint, 1, 1);
    }

    /** 血肉方块，meta 2 */
    public static ItemStack taintFlesh() {
        return new ItemStack(ConfigBlocks.blockTaint, 1, 2);
    }

    // ============================================================
    // 【Section 19】blockTaintFibres 污染纤维（meta 0~4）
    // ============================================================

    /** 腐化草丛，meta 0 */
    public static ItemStack taintFibres0() {
        return new ItemStack(ConfigBlocks.blockTaintFibres, 1, 0);
    }

    /** 腐化草地，meta 1 */
    public static ItemStack taintFibres1() {
        return new ItemStack(ConfigBlocks.blockTaintFibres, 1, 1);
    }

    /** 腐化植物，meta 2 */
    public static ItemStack taintFibres2() {
        return new ItemStack(ConfigBlocks.blockTaintFibres, 1, 2);
    }

    /** 孢子梗，meta 3 */
    public static ItemStack taintFibres3() {
        return new ItemStack(ConfigBlocks.blockTaintFibres, 1, 3);
    }

    /** 成熟孢子梗，meta 4 */
    public static ItemStack taintFibres4() {
        return new ItemStack(ConfigBlocks.blockTaintFibres, 1, 4);
    }

    // ============================================================
    // 【Section 20】无 meta 的常用方块
    // ============================================================

    /** 隙间（多方块结构专用占位符，带虚线框） */
    public static ItemStack hole() {
        return new ItemStack(ConfigBlocks.blockHole, 1, 15); // 👈 必须是 15！
    }

    /** 饕餮箱子 */
    public static ItemStack hungryChest() {
        return new ItemStack(ConfigBlocks.blockChestHungry);
    }

    /** 奥能之扉（方块形式） */
    public static ItemStack arcaneDoorBlock() {
        return new ItemStack(ConfigBlocks.blockArcaneDoor);
    }

    /** 奥术悬浮台 */
    public static ItemStack levitator() {
        return new ItemStack(ConfigBlocks.blockLifter);
    }

    /** 奥术木料 */
    public static ItemStack arcaneWood() {
        return new ItemStack(ConfigBlocks.blockMagicBox);
    }

    /** 炼狱熔炉 */
    public static ItemStack infernalFurnace() {
        return new ItemStack(ConfigBlocks.blockArcaneFurnace);
    }

    /** 守卫者方块 */
    public static ItemStack wardedBlock() {
        return new ItemStack(ConfigBlocks.blockWarded);
    }

    /** 源质库 */
    public static ItemStack essentiaReservoir() {
        return new ItemStack(ConfigBlocks.blockEssentiaReservoir);
    }

    /** 荒古炼金炉 */
    public static ItemStack advAlchemyFurnace() {
        return new ItemStack(ConfigBlocks.blockAlchemyFurnace);
    }

    // ============================================================
    // 【Section 21】楼梯 / 台阶
    // ============================================================

    /** 奥术楼梯 */
    public static ItemStack stairsArcaneStone() {
        return new ItemStack(ConfigBlocks.blockStairsArcaneStone);
    }

    /** 宏伟之木楼梯 */
    public static ItemStack stairsGreatwood() {
        return new ItemStack(ConfigBlocks.blockStairsGreatwood);
    }

    /** 银树楼梯 */
    public static ItemStack stairsSilverwood() {
        return new ItemStack(ConfigBlocks.blockStairsSilverwood);
    }

    /** 荒古楼梯 */
    public static ItemStack stairsEldritch() {
        return new ItemStack(ConfigBlocks.blockStairsEldritch);
    }

    /** 宏伟之木台阶，meta 0 */
    public static ItemStack slabGreatwood() {
        return new ItemStack(ConfigBlocks.blockSlabWood, 1, 0);
    }

    /** 银树台阶，meta 1 */
    public static ItemStack slabSilverwood() {
        return new ItemStack(ConfigBlocks.blockSlabWood, 1, 1);
    }

    /** 奥术台阶，meta 0 */
    public static ItemStack slabArcane() {
        return new ItemStack(ConfigBlocks.blockSlabStone, 1, 0);
    }

    /** 荒古台阶，meta 1 */
    public static ItemStack slabEldritch() {
        return new ItemStack(ConfigBlocks.blockSlabStone, 1, 1);
    }

    // ============================================================
    // 【Section 22】战利品方块
    // ============================================================

    /** 古旧之瓮 */
    public static ItemStack lootUrn() {
        return new ItemStack(ConfigBlocks.blockLootUrn);
    }

    /** 废弃箱子 */
    public static ItemStack lootCrate() {
        return new ItemStack(ConfigBlocks.blockLootCrate);
    }

    // ============================================================
    // 【Section 23】原版方块快捷方式
    // ============================================================

    public static ItemStack stone() {
        return new ItemStack(Blocks.stone);
    }

    public static ItemStack cobblestone() {
        return new ItemStack(Blocks.cobblestone);
    }

    public static ItemStack ironBlock() {
        return new ItemStack(Blocks.iron_block);
    }

    public static ItemStack goldBlock() {
        return new ItemStack(Blocks.gold_block);
    }

    public static ItemStack diamondBlock() {
        return new ItemStack(Blocks.diamond_block);
    }

    public static ItemStack obsidian() {
        return new ItemStack(Blocks.obsidian);
    }

    public static ItemStack glass() {
        return new ItemStack(Blocks.glass);
    }

    public static ItemStack netherBrick() {
        return new ItemStack(Blocks.nether_brick);
    }

    public static ItemStack quartzBlock() {
        return new ItemStack(Blocks.quartz_block);
    }

    public static ItemStack bookshelf() {
        return new ItemStack(Blocks.bookshelf);
    }

    public static ItemStack cauldron() {
        return new ItemStack(Blocks.cauldron);
    }

    public static ItemStack anvil() {
        return new ItemStack(Blocks.anvil, 1, 0);
    }

    public static ItemStack dispenser() {
        return new ItemStack(Blocks.dispenser);
    }

    public static ItemStack hopper() {
        return new ItemStack(Blocks.hopper);
    }

    public static ItemStack piston() {
        return new ItemStack(Blocks.piston);
    }

    public static ItemStack lever() {
        return new ItemStack(Blocks.lever);
    }

    /** 附魔台 */
    public static ItemStack enchantingTable() {
        return new ItemStack(Blocks.enchanting_table);
    }
}
