package com.kongtai.thaumicsurvival.api.thaumcraft.tcapiusetools;

import net.minecraft.entity.Entity;
import net.minecraft.world.World;

/**
 * ============================================================
 * 【神秘时代音效大词典】
 * ============================================================
 * 用途：把 assets/thaumcraft/sounds.json 里 66 个音效事件，
 * 全部封装成常量和静态播放方法。
 *
 * 【重要：两端都能播】
 * 1. 音效和粒子不同，不需要 @SideOnly。
 * 2. 服务端调 playSound → 原版网络自动同步给附近玩家
 * 客户端调 playSound → 本地立即播放
 * 3. 所以：
 * 服务端判定里调 = 附近所有玩家都能听到
 * 客户端调 = 只有自己听到
 * 4. 本类所有方法都直接用 TC 的 sounds.json 事件名。
 * TC 作为前置加载后，事件名全局注册，任何 mod 都能用。
 *
 * 【前缀不能省】
 * 事件名必须带 "thaumcraft:" 前缀。
 * 例如 "gore" ❌ "thaumcraft:gore" ✅
 *
 * 【音量 / 音高参考】
 * volume：普通攻击 0.5F / 暴击 0.8F / 环境 0.3F~0.5F / 剧情 1.0F
 * pitch：1.0F 原速 / 1.2F 紧张 / 0.9F 低沉 / 0.8~1.2 加随机
 *
 * 【分类】
 * Section 1 音效事件常量（全 66 个）
 * Section 2 基础播放
 * Section 3 武器 / 战斗
 * Section 4 法术 / 施法
 * Section 5 合成 / 升级
 * Section 6 炼金 / 机器
 * Section 7 研究 / 书写
 * Section 8 生物 / 怪物
 * Section 9 环境 / 氛围
 * Section 10 物品 / 交互
 * Section 11 组合场景（和 TCParticles 配对）
 * ============================================================
 */
public final class TCSounds {

    private TCSounds() {}

    // ============================================================
    // 【Section 1】音效事件常量
    // ============================================================
    // 全部来自 assets/thaumcraft/sounds.json 的 key。
    // 命名规则：SOUND_ + 大写事件名。
    // 变体数由 sounds.json 定义，播放时随机挑一个。
    // ============================================================

    // ---- 战斗 / 武器（5）----
    public static final String SOUND_SWING = "thaumcraft:swing"; // 2 变体，挥击
    public static final String SOUND_GORE = "thaumcraft:gore"; // 2 变体，血肉命中，暴击首选
    public static final String SOUND_TOOL = "thaumcraft:tool"; // 2 变体，工具使用
    public static final String SOUND_WIND = "thaumcraft:wind"; // 2 变体，风声/挥空
    public static final String SOUND_GOLEM_IRON_SHOOT = "thaumcraft:golemironshoot"; // 1 变体，傀儡铁炮

    // ---- 电 / 冲击（4）----
    public static final String SOUND_SHOCK = "thaumcraft:shock"; // 2 变体，冲击
    public static final String SOUND_ZAP = "thaumcraft:zap"; // 2 变体，短促电火花
    public static final String SOUND_JACOBS = "thaumcraft:jacobs"; // 1 变体，高压电弧
    public static final String SOUND_RUMBLE = "thaumcraft:rumble"; // 1 变体，隆隆震动

    // ---- 元素（2）----
    public static final String SOUND_FIRE_LOOP = "thaumcraft:fireloop"; // 1 变体，循环火焰
    public static final String SOUND_ICE = "thaumcraft:ice"; // 3 变体，冰霜

    // ---- 法术（5）----
    public static final String SOUND_WAND = "thaumcraft:wand"; // 3 变体，法杖施法
    public static final String SOUND_WAND_FAIL = "thaumcraft:wandfail"; // 1 变体，施法失败
    public static final String SOUND_CRYSTAL = "thaumcraft:crystal"; // 1 变体，水晶共鸣
    public static final String SOUND_RUNIC_SHIELD_EFFECT = "thaumcraft:runicShieldEffect"; // 1 变体，护盾触发
    public static final String SOUND_RUNIC_SHIELD_CHARGE = "thaumcraft:runicShieldCharge"; // 1 变体，护盾充能

    // ---- 合成 / 升级（4）----
    public static final String SOUND_CRAFT_START = "thaumcraft:craftstart"; // 1 变体，合成开始
    public static final String SOUND_CRAFT_FAIL = "thaumcraft:craftfail"; // 1 变体，合成失败
    public static final String SOUND_UPGRADE = "thaumcraft:upgrade"; // 1 变体，升级完成
    public static final String SOUND_ERASE = "thaumcraft:erase"; // 1 变体，擦除

    // ---- 炼金 / 机器（6）----
    public static final String SOUND_BUBBLE = "thaumcraft:bubble"; // 4 变体，冒泡
    public static final String SOUND_SPILL = "thaumcraft:spill"; // 1 变体，溢出
    public static final String SOUND_ALEMBIC_KNOCK = "thaumcraft:alembicknock"; // 1 变体，蒸馏器敲击
    public static final String SOUND_PUMP = "thaumcraft:pump"; // 3 变体，泵
    public static final String SOUND_JAR = "thaumcraft:jar"; // 4 变体，罐子
    public static final String SOUND_URN_BREAK = "thaumcraft:urnbreak"; // 1 变体，陶罐破碎

    // ---- 注魔（2）----
    public static final String SOUND_INFUSER = "thaumcraft:infuser"; // 1 变体，注魔进行中（循环）
    public static final String SOUND_INFUSER_START = "thaumcraft:infuserstart"; // 1 变体，注魔启动

    // ---- 研究 / 书写（5）----
    public static final String SOUND_PAGE = "thaumcraft:page"; // 2 变体，翻书页
    public static final String SOUND_WRITE = "thaumcraft:write"; // 2 变体，书写
    public static final String SOUND_LEARN = "thaumcraft:learn"; // 1 变体，学习/发现
    public static final String SOUND_KEY = "thaumcraft:key"; // 1 变体，钥匙
    public static final String SOUND_DOOR_FAIL = "thaumcraft:doorfail"; // 1 变体，门打不开

    // ---- Pech（6）----
    public static final String SOUND_PECH_IDLE = "thaumcraft:pech_idle"; // 3 变体，闲置
    public static final String SOUND_PECH_TRADE = "thaumcraft:pech_trade"; // 1 变体，交易
    public static final String SOUND_PECH_DICE = "thaumcraft:pech_dice"; // 1 变体，掷骰
    public static final String SOUND_PECH_HIT = "thaumcraft:pech_hit"; // 2 变体，受击
    public static final String SOUND_PECH_DEATH = "thaumcraft:pech_death"; // 1 变体，死亡
    public static final String SOUND_PECH_CHARGE = "thaumcraft:pech_charge"; // 2 变体，冲锋

    // ---- 灵光 / 虫群（5）----
    public static final String SOUND_WISP_LIVE = "thaumcraft:wisplive"; // 3 变体，灵光存活
    public static final String SOUND_WISP_DEAD = "thaumcraft:wispdead"; // 1 变体，灵光死亡
    public static final String SOUND_FLY = "thaumcraft:fly"; // 2 变体，飞行/虫鸣
    public static final String SOUND_SWARM = "thaumcraft:swarm"; // 3 变体，虫群
    public static final String SOUND_SWARM_ATTACK = "thaumcraft:swarmattack"; // 1 变体，虫群攻击

    // ---- 外域守卫（4）----
    public static final String SOUND_EG_IDLE = "thaumcraft:egidle"; // 2 变体，闲置
    public static final String SOUND_EG_ATTACK = "thaumcraft:egattack"; // 1 变体，攻击
    public static final String SOUND_EG_DEATH = "thaumcraft:egdeath"; // 1 变体，死亡
    public static final String SOUND_EG_SCREECH = "thaumcraft:egscreech"; // 1 变体，尖啸

    // ---- 外域螃蟹（3）----
    public static final String SOUND_CRAB_CLAW = "thaumcraft:crabclaw"; // 1 变体，蟹钳
    public static final String SOUND_CRAB_DEATH = "thaumcraft:crabdeath"; // 1 变体，死亡
    public static final String SOUND_CRAB_TALK = "thaumcraft:crabtalk"; // 3 变体，说话

    // ---- 腐化生物（1）----
    public static final String SOUND_BRAIN = "thaumcraft:brain"; // 4 变体，大脑

    // ---- 触手 / 根须（2）----
    public static final String SOUND_TENTACLE = "thaumcraft:tentacle"; // 3 变体，触手
    public static final String SOUND_ROOTS = "thaumcraft:roots"; // 3 变体，根须

    // ---- 诡异 / 氛围（4）----
    public static final String SOUND_HEARTBEAT = "thaumcraft:heartbeat"; // 1 变体，心跳
    public static final String SOUND_WHISPERS = "thaumcraft:whispers"; // 1 变体，低语
    public static final String SOUND_MONOLITH = "thaumcraft:monolith"; // 1 变体，方尖碑
    public static final String SOUND_EVIL_PORTAL = "thaumcraft:evilportal"; // 1 变体，邪恶传送门

    // ---- 吟唱 / 交易（2）----
    public static final String SOUND_CHANT = "thaumcraft:chant"; // 3 变体，吟唱
    public static final String SOUND_COINS = "thaumcraft:coins"; // 1 变体，硬币

    // ---- 机械 / 结构（4）----
    public static final String SOUND_CREAK = "thaumcraft:creak"; // 2 变体，吱呀
    public static final String SOUND_SQUEEK = "thaumcraft:squeek"; // 2 变体，吱吱
    public static final String SOUND_CAMERA_TICKS = "thaumcraft:cameraticks"; // 1 变体，机械滴答
    public static final String SOUND_CAMERA_CLACK = "thaumcraft:cameraclack"; // 3 变体，机械咔嗒

    // ---- 悬浮装备（2）----
    public static final String SOUND_HH_ON = "thaumcraft:hhon"; // 1 变体，悬浮开启
    public static final String SOUND_HH_OFF = "thaumcraft:hhoff"; // 1 变体，悬浮关闭

    // 一共 66 个事件。
    // 想加新事件，照抄格式，名字必须和 sounds.json 的 key 一致。

    // ============================================================
    // 【Section 2】基础播放
    // ============================================================
    // 两端都能调：
    // - 服务端：通过原版网络同步给附近玩家
    // - 客户端：本地立即播放
    // ============================================================

    /**
     * 在世界坐标播一个 TC 音效。
     *
     * @param sound  Section 1 的 SOUND_xxx 常量
     * @param volume 音量，0.0~1.0 常见，>1.0 会更远
     * @param pitch  音高，1.0 原速，0.8~1.2 加随机
     */
    public static void play(World w, double x, double y, double z, String sound, float volume, float pitch) {
        if (w == null || sound == null) return;
        w.playSound(x, y, z, sound, volume, pitch, false);
    }

    /**
     * 在世界坐标播一个带随机音高的 TC 音效。
     * 适合大量重复音（挥击、脚步）。
     */
    public static void playRandomPitch(World w, double x, double y, double z, String sound, float volume,
        float pitchMin, float pitchMax) {
        if (w == null || sound == null) return;
        float pitch = pitchMin + w.rand.nextFloat() * (pitchMax - pitchMin);
        w.playSound(x, y, z, sound, volume, pitch, false);
    }

    /**
     * 在实体位置播一个 TC 音效。
     */
    public static void playAtEntity(Entity e, String sound, float volume, float pitch) {
        if (e == null || e.worldObj == null || sound == null) return;
        e.worldObj.playSoundAtEntity(e, sound, volume, pitch);
    }

    /**
     * 在实体位置播一个带随机音高的 TC 音效。
     */
    public static void playAtEntityRandomPitch(Entity e, String sound, float volume, float pitchMin, float pitchMax) {
        if (e == null || e.worldObj == null || sound == null) return;
        float pitch = pitchMin + e.worldObj.rand.nextFloat() * (pitchMax - pitchMin);
        e.worldObj.playSoundAtEntity(e, sound, volume, pitch);
    }

    // ============================================================
    // 【Section 3】武器 / 战斗
    // ============================================================
    // 通用参数：
    // 普通攻击 volume 0.5 / pitch 1.0
    // 暴击 volume 0.8 / pitch 1.2
    // ============================================================

    /** 挥击（出手） */
    public static void swing(World w, double x, double y, double z) {
        playRandomPitch(w, x, y, z, SOUND_SWING, 0.5F, 0.9F, 1.1F);
    }

    /** 普通命中 */
    public static void hit(World w, double x, double y, double z) {
        play(w, x, y, z, SOUND_TOOL, 0.5F, 1.0F);
    }

    /** 暴击命中 */
    public static void crit(World w, double x, double y, double z) {
        play(w, x, y, z, SOUND_GORE, 0.8F, 1.2F);
    }

    /** 挥空 */
    public static void miss(World w, double x, double y, double z) {
        play(w, x, y, z, SOUND_WIND, 0.4F, 1.0F);
    }

    /** 雷击武器命中 */
    public static void zapHit(World w, double x, double y, double z) {
        playRandomPitch(w, x, y, z, SOUND_ZAP, 0.8F, 0.9F, 1.1F);
    }

    /** 高压电弧（链式闪电） */
    public static void jacobs(World w, double x, double y, double z) {
        play(w, x, y, z, SOUND_JACOBS, 0.7F, 1.0F);
    }

    /** 冲击（近战重击） */
    public static void shock(World w, double x, double y, double z) {
        playRandomPitch(w, x, y, z, SOUND_SHOCK, 0.7F, 0.9F, 1.1F);
    }

    /** 火焰持续（循环） */
    public static void fireLoop(World w, double x, double y, double z) {
        play(w, x, y, z, SOUND_FIRE_LOOP, 0.4F, 1.0F);
    }

    /** 冰霜命中 */
    public static void ice(World w, double x, double y, double z) {
        playRandomPitch(w, x, y, z, SOUND_ICE, 0.6F, 0.95F, 1.05F);
    }

    /** 铁炮射击（傀儡） */
    public static void golemIronShoot(World w, double x, double y, double z) {
        play(w, x, y, z, SOUND_GOLEM_IRON_SHOOT, 0.6F, 1.0F);
    }

    // ============================================================
    // 【Section 4】法术 / 施法
    // ============================================================

    /** 施法成功 */
    public static void spellCast(World w, double x, double y, double z) {
        playRandomPitch(w, x, y, z, SOUND_WAND, 0.6F, 0.95F, 1.05F);
    }

    /** 施法失败 */
    public static void spellFail(World w, double x, double y, double z) {
        play(w, x, y, z, SOUND_WAND_FAIL, 0.5F, 1.0F);
    }

    /** 水晶共鸣（法术蓄力/水晶武器） */
    public static void crystal(World w, double x, double y, double z) {
        play(w, x, y, z, SOUND_CRYSTAL, 0.5F, 1.0F);
    }

    /** 护盾充能 */
    public static void shieldCharge(World w, double x, double y, double z) {
        play(w, x, y, z, SOUND_RUNIC_SHIELD_CHARGE, 0.6F, 1.0F);
    }

    /** 护盾触发（格挡） */
    public static void shieldBlock(World w, double x, double y, double z) {
        play(w, x, y, z, SOUND_RUNIC_SHIELD_EFFECT, 0.8F, 1.0F);
    }

    // ============================================================
    // 【Section 5】合成 / 升级
    // ============================================================

    /** 合成开始 */
    public static void craftStart(World w, double x, double y, double z) {
        play(w, x, y, z, SOUND_CRAFT_START, 0.6F, 1.0F);
    }

    /** 合成失败 */
    public static void craftFail(World w, double x, double y, double z) {
        play(w, x, y, z, SOUND_CRAFT_FAIL, 0.5F, 1.0F);
    }

    /** 升级/强化完成 */
    public static void upgrade(World w, double x, double y, double z) {
        play(w, x, y, z, SOUND_UPGRADE, 1.0F, 1.0F);
    }

    /** 擦除（研究笔记） */
    public static void erase(World w, double x, double y, double z) {
        play(w, x, y, z, SOUND_ERASE, 0.4F, 1.0F);
    }

    // ============================================================
    // 【Section 6】炼金 / 机器
    // ============================================================

    /** 冒泡（坩埚） */
    public static void bubble(World w, double x, double y, double z) {
        playRandomPitch(w, x, y, z, SOUND_BUBBLE, 0.3F, 0.8F, 1.2F);
    }

    /** 液体溢出 */
    public static void spill(World w, double x, double y, double z) {
        play(w, x, y, z, SOUND_SPILL, 0.5F, 1.0F);
    }

    /** 蒸馏器敲击 */
    public static void alembicKnock(World w, double x, double y, double z) {
        playRandomPitch(w, x, y, z, SOUND_ALEMBIC_KNOCK, 0.4F, 0.9F, 1.1F);
    }

    /** 泵 */
    public static void pump(World w, double x, double y, double z) {
        playRandomPitch(w, x, y, z, SOUND_PUMP, 0.4F, 0.9F, 1.1F);
    }

    /** 罐子操作 */
    public static void jar(World w, double x, double y, double z) {
        playRandomPitch(w, x, y, z, SOUND_JAR, 0.4F, 0.9F, 1.1F);
    }

    /** 陶罐破碎 */
    public static void urnBreak(World w, double x, double y, double z) {
        play(w, x, y, z, SOUND_URN_BREAK, 0.6F, 1.0F);
    }

    /** 注魔启动 */
    public static void infuserStart(World w, double x, double y, double z) {
        play(w, x, y, z, SOUND_INFUSER_START, 1.0F, 1.0F);
    }

    /** 注魔进行中（循环） */
    public static void infuser(World w, double x, double y, double z) {
        play(w, x, y, z, SOUND_INFUSER, 0.5F, 1.0F);
    }

    // ============================================================
    // 【Section 7】研究 / 书写
    // ============================================================

    /** 翻书页 */
    public static void page(World w, double x, double y, double z) {
        playRandomPitch(w, x, y, z, SOUND_PAGE, 0.3F, 0.9F, 1.1F);
    }

    /** 书写 */
    public static void write(World w, double x, double y, double z) {
        playRandomPitch(w, x, y, z, SOUND_WRITE, 0.3F, 0.9F, 1.1F);
    }

    /** 学习/发现 */
    public static void learn(World w, double x, double y, double z) {
        play(w, x, y, z, SOUND_LEARN, 0.7F, 1.0F);
    }

    /** 钥匙 */
    public static void key(World w, double x, double y, double z) {
        play(w, x, y, z, SOUND_KEY, 0.5F, 1.0F);
    }

    /** 门打不开 */
    public static void doorFail(World w, double x, double y, double z) {
        play(w, x, y, z, SOUND_DOOR_FAIL, 0.4F, 1.0F);
    }

    // ============================================================
    // 【Section 8】生物 / 怪物
    // ============================================================

    /** Pech 闲置 */
    public static void pechIdle(World w, double x, double y, double z) {
        playRandomPitch(w, x, y, z, SOUND_PECH_IDLE, 0.4F, 0.85F, 1.15F);
    }

    /** Pech 交易 */
    public static void pechTrade(World w, double x, double y, double z) {
        play(w, x, y, z, SOUND_PECH_TRADE, 0.6F, 1.0F);
    }

    /** Pech 掷骰 */
    public static void pechDice(World w, double x, double y, double z) {
        play(w, x, y, z, SOUND_PECH_DICE, 0.5F, 1.0F);
    }

    /** Pech 受击 */
    public static void pechHit(World w, double x, double y, double z) {
        playRandomPitch(w, x, y, z, SOUND_PECH_HIT, 0.6F, 0.9F, 1.1F);
    }

    /** Pech 死亡 */
    public static void pechDeath(World w, double x, double y, double z) {
        play(w, x, y, z, SOUND_PECH_DEATH, 0.7F, 1.0F);
    }

    /** Pech 冲锋 */
    public static void pechCharge(World w, double x, double y, double z) {
        playRandomPitch(w, x, y, z, SOUND_PECH_CHARGE, 0.6F, 0.9F, 1.1F);
    }

    /** 灵光存活 */
    public static void wispLive(World w, double x, double y, double z) {
        playRandomPitch(w, x, y, z, SOUND_WISP_LIVE, 0.3F, 0.9F, 1.1F);
    }

    /** 灵光死亡 */
    public static void wispDead(World w, double x, double y, double z) {
        play(w, x, y, z, SOUND_WISP_DEAD, 0.6F, 1.0F);
    }

    /** 虫群 */
    public static void swarm(World w, double x, double y, double z) {
        playRandomPitch(w, x, y, z, SOUND_SWARM, 0.3F, 0.9F, 1.1F);
    }

    /** 虫群攻击 */
    public static void swarmAttack(World w, double x, double y, double z) {
        play(w, x, y, z, SOUND_SWARM_ATTACK, 0.5F, 1.0F);
    }

    /** 飞行/虫鸣 */
    public static void fly(World w, double x, double y, double z) {
        playRandomPitch(w, x, y, z, SOUND_FLY, 0.2F, 0.8F, 1.2F);
    }

    /** 外域守卫闲置 */
    public static void egIdle(World w, double x, double y, double z) {
        playRandomPitch(w, x, y, z, SOUND_EG_IDLE, 0.4F, 0.85F, 1.15F);
    }

    /** 外域守卫攻击 */
    public static void egAttack(World w, double x, double y, double z) {
        play(w, x, y, z, SOUND_EG_ATTACK, 0.7F, 1.0F);
    }

    /** 外域守卫死亡 */
    public static void egDeath(World w, double x, double y, double z) {
        play(w, x, y, z, SOUND_EG_DEATH, 0.8F, 1.0F);
    }

    /** 外域守卫尖啸 */
    public static void egScreech(World w, double x, double y, double z) {
        play(w, x, y, z, SOUND_EG_SCREECH, 0.8F, 1.0F);
    }

    /** 外域螃蟹蟹钳 */
    public static void crabClaw(World w, double x, double y, double z) {
        play(w, x, y, z, SOUND_CRAB_CLAW, 0.5F, 1.0F);
    }

    /** 外域螃蟹死亡 */
    public static void crabDeath(World w, double x, double y, double z) {
        play(w, x, y, z, SOUND_CRAB_DEATH, 0.6F, 1.0F);
    }

    /** 外域螃蟹说话 */
    public static void crabTalk(World w, double x, double y, double z) {
        playRandomPitch(w, x, y, z, SOUND_CRAB_TALK, 0.4F, 0.9F, 1.1F);
    }

    /** 大脑 */
    public static void brain(World w, double x, double y, double z) {
        playRandomPitch(w, x, y, z, SOUND_BRAIN, 0.5F, 0.9F, 1.1F);
    }

    /** 触手 */
    public static void tentacle(World w, double x, double y, double z) {
        playRandomPitch(w, x, y, z, SOUND_TENTACLE, 0.5F, 0.9F, 1.1F);
    }

    /** 根须 */
    public static void roots(World w, double x, double y, double z) {
        playRandomPitch(w, x, y, z, SOUND_ROOTS, 0.5F, 0.9F, 1.1F);
    }

    // ============================================================
    // 【Section 9】环境 / 氛围
    // ============================================================

    /** 心跳（紧张/低血量/扭曲） */
    public static void heartbeat(World w, double x, double y, double z) {
        play(w, x, y, z, SOUND_HEARTBEAT, 1.0F, 1.0F);
    }

    /** 低语（扭曲氛围） */
    public static void whispers(World w, double x, double y, double z) {
        play(w, x, y, z, SOUND_WHISPERS, 0.6F, 1.0F);
    }

    /** 方尖碑 */
    public static void monolith(World w, double x, double y, double z) {
        play(w, x, y, z, SOUND_MONOLITH, 0.7F, 1.0F);
    }

    /** 邪恶传送门 */
    public static void evilPortal(World w, double x, double y, double z) {
        play(w, x, y, z, SOUND_EVIL_PORTAL, 0.8F, 1.0F);
    }

    /** 吟唱（仪式） */
    public static void chant(World w, double x, double y, double z) {
        playRandomPitch(w, x, y, z, SOUND_CHANT, 0.8F, 0.9F, 1.1F);
    }

    /** 硬币 */
    public static void coins(World w, double x, double y, double z) {
        play(w, x, y, z, SOUND_COINS, 0.5F, 1.0F);
    }

    /** 隆隆震动 */
    public static void rumble(World w, double x, double y, double z) {
        play(w, x, y, z, SOUND_RUMBLE, 1.0F, 1.0F);
    }

    // ============================================================
    // 【Section 10】物品 / 交互
    // ============================================================

    /** 吱呀（木结构） */
    public static void creak(World w, double x, double y, double z) {
        playRandomPitch(w, x, y, z, SOUND_CREAK, 0.4F, 0.9F, 1.1F);
    }

    /** 吱吱（小机械） */
    public static void squeek(World w, double x, double y, double z) {
        playRandomPitch(w, x, y, z, SOUND_SQUEEK, 0.3F, 0.9F, 1.1F);
    }

    /** 机械滴答 */
    public static void cameraTicks(World w, double x, double y, double z) {
        play(w, x, y, z, SOUND_CAMERA_TICKS, 0.3F, 1.0F);
    }

    /** 机械咔嗒 */
    public static void cameraClack(World w, double x, double y, double z) {
        playRandomPitch(w, x, y, z, SOUND_CAMERA_CLACK, 0.4F, 0.9F, 1.1F);
    }

    /** 悬浮装备开启 */
    public static void hoverOn(World w, double x, double y, double z) {
        play(w, x, y, z, SOUND_HH_ON, 0.5F, 1.0F);
    }

    /** 悬浮装备关闭 */
    public static void hoverOff(World w, double x, double y, double z) {
        play(w, x, y, z, SOUND_HH_OFF, 0.5F, 1.0F);
    }

    // ============================================================
    // 【Section 11】组合场景（和 TCParticles 配对）
    // ============================================================
    // 这些方法只播声音。
    // 完整的"声音+粒子"组合，在你的武器/事件类里同时调
    // TCSounds.xxx(...) 和 TCParticles.xxx(...) 即可。
    //
    // 为什么不在 TCSounds 里同时调粒子：
    // 1. 保持两个类职责单一
    // 2. TCSounds 两端都能调；TCParticles 只在客户端出粒子
    // 3. 用户自己决定什么时候要粒子、什么时候只要声音
    // ============================================================

    /**
     * 攻击命中（声音部分）。
     * 配 TCParticles.attackHit(...) 使用。
     */
    public static void combatAttackHit(World w, double x, double y, double z) {
        swing(w, x, y, z);
        hit(w, x, y, z);
    }

    /**
     * 暴击（声音部分）。
     * 配 TCParticles.critAtEntity(target) 使用。
     */
    public static void combatCrit(World w, double x, double y, double z) {
        crit(w, x, y, z);
    }

    /**
     * 击杀反馈（声音部分）。
     * 配 TCParticles.killFeedback(...) 使用。
     */
    public static void combatKill(World w, double x, double y, double z) {
        wispDead(w, x, y, z);
    }

    /**
     * 火焰命中（声音部分）。
     * 配 TCParticles.fireHit(...) 使用。
     */
    public static void combatFireHit(World w, double x, double y, double z) {
        fireLoop(w, x, y, z);
        shock(w, x, y, z);
    }

    /**
     * 冰霜命中（声音部分）。
     * 配 TCParticles.frostHit(...) 使用。
     */
    public static void combatFrostHit(World w, double x, double y, double z) {
        ice(w, x, y, z);
    }

    /**
     * 雷击（声音部分）。
     * 配 TCParticles.lightningAttack(...) 使用。
     */
    public static void combatLightning(World w, double x, double y, double z) {
        zapHit(w, x, y, z);
        shock(w, x, y, z);
    }

    /**
     * 法术施放（声音部分）。
     * 配 TCParticles.spellCast(...) 使用。
     */
    public static void combatSpellCast(World w, double x, double y, double z) {
        spellCast(w, x, y, z);
    }

    /**
     * 护盾格挡（声音部分）。
     * 配 TCParticles.shieldBlock(...) 使用。
     */
    public static void combatShieldBlock(World w, double x, double y, double z) {
        shieldBlock(w, x, y, z);
    }

    /**
     * 武器强化完成（声音部分）。
     * 配 TCParticles.upgrade(...) 使用。
     */
    public static void combatUpgrade(World w, double x, double y, double z) {
        upgrade(w, x, y, z);
    }
}
