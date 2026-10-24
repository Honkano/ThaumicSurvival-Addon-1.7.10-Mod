package com.kongtai.thaumicsurvival.api.thaumcraft.tcapiusetools;

import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;

/**
 * ============================================================
 * 【神秘时代要素大词典】
 * ============================================================
 * 用途：把 Aspect.XXX 和 getTag() 的拉丁文，统一成有名字的常量，
 * 写配方、NBT、网络包时不用手拼字符串。
 *
 * 【重要说明】
 * 1. TC4 的 Aspect 字段名本来就是英文（FIRE、WATER、AIR...），
 * 和 TC6 的调用方式完全一致。所谓“中二拉丁文”指的是
 * Aspect.FIRE.getTag() 返回的 "ignis"。
 *
 * 2. getTag() 的拉丁文用于：
 * - NBT 存储（AspectList.writeToNBT）
 * - 网络包（PacketAspectPool）
 * - 部分矿辞/研究 key
 * 这三个地方改不了，改了会破坏存档和协议。
 *
 * 3. 本类提供两层封装：
 * - Aspect 别名：TCAspects.FIRE = Aspect.FIRE（英文调用）
 * - Tag 常量：TCAspects.TAG_FIRE = "ignis"（拉丁文封装）
 *
 * 4. TC4 停留在 1.7.10 最终版，要素不会再变。
 * ============================================================
 */
public class TCAspects {

    // ============================================================
    // 【Section 1】六种元始要素（Primal Aspects）
    // ============================================================
    // 字段名英文，tag 拉丁文。写配方用字段名，写 NBT 用 TAG_XXX。
    // ============================================================

    /** 风（Air），tag = "aer" */
    public static final Aspect AIR = Aspect.AIR;
    public static final String TAG_AIR = "aer";

    /** 地（Earth），tag = "terra" */
    public static final Aspect EARTH = Aspect.EARTH;
    public static final String TAG_EARTH = "terra";

    /** 火（Fire），tag = "ignis" */
    public static final Aspect FIRE = Aspect.FIRE;
    public static final String TAG_FIRE = "ignis";

    /** 水（Water），tag = "aqua" */
    public static final Aspect WATER = Aspect.WATER;
    public static final String TAG_WATER = "aqua";

    /** 秩序（Order），tag = "ordo" */
    public static final Aspect ORDER = Aspect.ORDER;
    public static final String TAG_ORDER = "ordo";

    /** 混沌（Entropy），tag = "perditio" */
    public static final Aspect ENTROPY = Aspect.ENTROPY;
    public static final String TAG_ENTROPY = "perditio";

    // ============================================================
    // 【Section 2】复合要素（Compound Aspects）
    // ============================================================
    // 按中文名首字母排序，方便查。每个都有字段名 + tag 两行。
    // ============================================================

    /** 虚空（Void），tag = "vacuos" */
    public static final Aspect VOID = Aspect.VOID;
    public static final String TAG_VOID = "vacuos";

    /** 魔力（Magic），tag = "praecantatio" */
    public static final Aspect MAGIC = Aspect.MAGIC;
    public static final String TAG_MAGIC = "praecantatio";

    /** 灵气（Aura），tag = "auram" */
    public static final Aspect AURA = Aspect.AURA;
    public static final String TAG_AURA = "auram";

    /** 污染（Taint），tag = "vitium" */
    public static final Aspect TAINT = Aspect.TAINT;
    public static final String TAG_TAINT = "vitium";

    /** 光明（Light），tag = "lux" */
    public static final Aspect LIGHT = Aspect.LIGHT;
    public static final String TAG_LIGHT = "lux";

    /** 能量（Energy），tag = "potentia" */
    public static final Aspect ENERGY = Aspect.ENERGY;
    public static final String TAG_ENERGY = "potentia";

    /** 移动（Motion），tag = "motus" */
    public static final Aspect MOTION = Aspect.MOTION;
    public static final String TAG_MOTION = "motus";

    /** 气候（Weather），tag = "tempestas" */
    public static final Aspect WEATHER = Aspect.WEATHER;
    public static final String TAG_WEATHER = "tempestas";

    /** 生命（Life），tag = "victus" */
    public static final Aspect LIFE = Aspect.LIFE;
    public static final String TAG_LIFE = "victus";

    /** 死亡（Death），tag = "mortuus" */
    public static final Aspect DEATH = Aspect.DEATH;
    public static final String TAG_DEATH = "mortuus";

    /** 飞行（Flight），tag = "volatus" */
    public static final Aspect FLIGHT = Aspect.FLIGHT;
    public static final String TAG_FLIGHT = "volatus";

    /** 黑暗（Darkness），tag = "tenebrae" */
    public static final Aspect DARKNESS = Aspect.DARKNESS;
    public static final String TAG_DARKNESS = "tenebrae";

    /** 史莱姆/粘液（Slime），tag = "limus" */
    public static final Aspect SLIME = Aspect.SLIME;
    public static final String TAG_SLIME = "limus";

    /** 植物（Plant），tag = "herba" */
    public static final Aspect PLANT = Aspect.PLANT;
    public static final String TAG_PLANT = "herba";

    /** 树木/木材（Tree），tag = "arbor" */
    public static final Aspect TREE = Aspect.TREE;
    public static final String TAG_TREE = "arbor";

    /** 灵魂（Soul），tag = "spiritus" */
    public static final Aspect SOUL = Aspect.SOUL;
    public static final String TAG_SOUL = "spiritus";

    /** 人类（Man），tag = "humanus" */
    public static final Aspect MAN = Aspect.MAN;
    public static final String TAG_MAN = "humanus";

    /** 野兽（Beast），tag = "bestia" */
    public static final Aspect BEAST = Aspect.BEAST;
    public static final String TAG_BEAST = "bestia";

    /** 不死/亡灵（Undead），tag = "exanimis" */
    public static final Aspect UNDEAD = Aspect.UNDEAD;
    public static final String TAG_UNDEAD = "exanimis";

    /** 思维/记忆/认知（Mind），tag = "cognitio" */
    public static final Aspect MIND = Aspect.MIND;
    public static final String TAG_MIND = "cognitio";

    /** 感官（Senses），tag = "sensus" */
    public static final Aspect SENSES = Aspect.SENSES;
    public static final String TAG_SENSES = "sensus";

    /** 作物（Crop），tag = "messis" */
    public static final Aspect CROP = Aspect.CROP;
    public static final String TAG_CROP = "messis";

    /** 收获（Harvest），tag = "meto" */
    public static final Aspect HARVEST = Aspect.HARVEST;
    public static final String TAG_HARVEST = "meto";

    /** 金属（Metal），tag = "metallum" */
    public static final Aspect METAL = Aspect.METAL;
    public static final String TAG_METAL = "metallum";

    /** 矿藏（Mine），tag = "perfodio" */
    public static final Aspect MINE = Aspect.MINE;
    public static final String TAG_MINE = "perfodio";

    /** 工具/仪器（Tool），tag = "instrumentum" */
    public static final Aspect TOOL = Aspect.TOOL;
    public static final String TAG_TOOL = "instrumentum";

    /** 武器/攻击/伤害（Weapon），tag = "telum" */
    public static final Aspect WEAPON = Aspect.WEAPON;
    public static final String TAG_WEAPON = "telum";

    /** 寒冰/霜冻/寒冷（Cold），tag = "gelum" */
    public static final Aspect COLD = Aspect.COLD;
    public static final String TAG_COLD = "gelum";

    /** 水晶/玻璃/澄澈（Crystal），tag = "vitreus" */
    public static final Aspect CRYSTAL = Aspect.CRYSTAL;
    public static final String TAG_CRYSTAL = "vitreus";

    /** 治疗/痊愈（Heal），tag = "sano" */
    public static final Aspect HEAL = Aspect.HEAL;
    public static final String TAG_HEAL = "sano";

    /** 旅行/行程（Travel），tag = "iter" */
    public static final Aspect TRAVEL = Aspect.TRAVEL;
    public static final String TAG_TRAVEL = "iter";

    /** 毒药（Poison），tag = "venenum" */
    public static final Aspect POISON = Aspect.POISON;
    public static final String TAG_POISON = "venenum";

    /** 异域/怪异/诡谲（Eldritch），tag = "alienis" */
    public static final Aspect ELDRITCH = Aspect.ELDRITCH;
    public static final String TAG_ELDRITCH = "alienis";

    /** 装备/防护（Armor），tag = "tutamen" */
    public static final Aspect ARMOR = Aspect.ARMOR;
    public static final String TAG_ARMOR = "tutamen";

    /** 饥饿（Hunger），tag = "fames" */
    public static final Aspect HUNGER = Aspect.HUNGER;
    public static final String TAG_HUNGER = "fames";

    /** 合成/工艺（Craft），tag = "fabrico" */
    public static final Aspect CRAFT = Aspect.CRAFT;
    public static final String TAG_CRAFT = "fabrico";

    /** 贪婪/贪财（Greed），tag = "lucrum" */
    public static final Aspect GREED = Aspect.GREED;
    public static final String TAG_GREED = "lucrum";

    /** 布匹/材料/覆盖（Cloth），tag = "pannus" */
    public static final Aspect CLOTH = Aspect.CLOTH;
    public static final String TAG_CLOTH = "pannus";

    /** 机械/机构（Mechanism），tag = "machina" */
    public static final Aspect MECHANISM = Aspect.MECHANISM;
    public static final String TAG_MECHANISM = "machina";

    /** 陷阱/监禁（Trap），tag = "vinculum" */
    public static final Aspect TRAP = Aspect.TRAP;
    public static final String TAG_TRAP = "vinculum";

    /** 贸易/交换（Exchange），tag = "permutatio" */
    public static final Aspect EXCHANGE = Aspect.EXCHANGE;
    public static final String TAG_EXCHANGE = "permutatio";

    /** 肉体（Flesh），tag = "corpus" */
    public static final Aspect FLESH = Aspect.FLESH;
    public static final String TAG_FLESH = "corpus";

    // ============================================================
    // 【Section 3】便捷构造方法
    // ============================================================

    /**
     * 六原质各 n 点。
     * 例：primal(8) → AIR 8 + EARTH 8 + FIRE 8 + WATER 8 + ORDER 8 + ENTROPY 8
     */
    public static AspectList primal(int n) {
        return new AspectList().add(AIR, n)
            .add(EARTH, n)
            .add(FIRE, n)
            .add(WATER, n)
            .add(ORDER, n)
            .add(ENTROPY, n);
    }

    /**
     * 六原质，每个不同倍率。
     * 顺序：AIR, EARTH, FIRE, WATER, ORDER, ENTROPY
     * 例：primal(10, 1, 2, 3, 4, 5, 6)
     * → AIR 10, EARTH 20, FIRE 30, WATER 40, ORDER 50, ENTROPY 60
     */
    public static AspectList primal(int base, int... multipliers) {
        Aspect[] primals = { AIR, EARTH, FIRE, WATER, ORDER, ENTROPY };
        AspectList list = new AspectList();
        for (int i = 0; i < primals.length && i < multipliers.length; i++) {
            list.add(primals[i], base * multipliers[i]);
        }
        return list;
    }

    /**
     * 可变参数快速构造：
     * of(FIRE, 16, MAGIC, 8) → FIRE 16 + MAGIC 8
     * 参数必须成对：Aspect, Integer, Aspect, Integer...
     */
    public static AspectList of(Object... pairs) {
        AspectList list = new AspectList();
        for (int i = 0; i + 1 < pairs.length; i += 2) {
            Aspect a = (Aspect) pairs[i];
            int n = (Integer) pairs[i + 1];
            list.add(a, n);
        }
        return list;
    }

    /**
     * 每个要素各 1 点。
     * each(FIRE, WATER) → FIRE 1 + WATER 1
     */
    public static AspectList each(Aspect... aspects) {
        AspectList list = new AspectList();
        for (Aspect a : aspects) {
            list.add(a, 1);
        }
        return list;
    }

    /**
     * 单个要素 n 点。
     * single(FIRE, 100) → FIRE 100
     */
    public static AspectList single(Aspect aspect, int n) {
        return new AspectList().add(aspect, n);
    }

    // ============================================================
    // 【Section 4】常用配方源质模板
    // ============================================================

    /** 注魔铁砧基础源质：金属 8 / 工具 4 / 工艺 4 / 地 4 / 火 2 */
    public static AspectList infusionAnvilBase() {
        return new AspectList().add(METAL, 8)
            .add(TOOL, 4)
            .add(CRAFT, 4)
            .add(EARTH, 4)
            .add(FIRE, 2);
    }

    /** 魔铸金苹果基础源质：火 32 / 生命 48 / 魔力 64 / 贪婪 64 / 饥饿 32 */
    public static AspectList greedyAppleBase() {
        return new AspectList().add(FIRE, 32)
            .add(LIFE, 48)
            .add(MAGIC, 64)
            .add(GREED, 64)
            .add(HUNGER, 32);
    }
}
