package com.kongtai.thaumicsurvival.api.thaumcraft.tcapiusetools;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.client.fx.ParticleEngine;
import thaumcraft.client.fx.beams.FXArc;
import thaumcraft.client.fx.beams.FXBeam;
import thaumcraft.client.fx.beams.FXBeamBore;
import thaumcraft.client.fx.beams.FXBeamGolemBoss;
import thaumcraft.client.fx.beams.FXBeamPower;
import thaumcraft.client.fx.beams.FXBeamWand;
import thaumcraft.client.fx.bolt.FXLightningBolt;
import thaumcraft.client.fx.other.FXBlockWard;
import thaumcraft.client.fx.other.FXShieldRunes;
import thaumcraft.client.fx.other.FXSonic;
import thaumcraft.client.fx.particles.FXBlockRunes;
import thaumcraft.client.fx.particles.FXBoreParticles;
import thaumcraft.client.fx.particles.FXBoreSparkle;
import thaumcraft.client.fx.particles.FXBreaking;
import thaumcraft.client.fx.particles.FXBubble;
import thaumcraft.client.fx.particles.FXBubbleAlt;
import thaumcraft.client.fx.particles.FXBurst;
import thaumcraft.client.fx.particles.FXDrop;
import thaumcraft.client.fx.particles.FXEssentiaTrail;
import thaumcraft.client.fx.particles.FXGeneric;
import thaumcraft.client.fx.particles.FXScorch;
import thaumcraft.client.fx.particles.FXSlimyBubble;
import thaumcraft.client.fx.particles.FXSmokeSpiral;
import thaumcraft.client.fx.particles.FXSmokeTrail;
import thaumcraft.client.fx.particles.FXSpark;
import thaumcraft.client.fx.particles.FXSparkle;
import thaumcraft.client.fx.particles.FXSparkleTrail;
import thaumcraft.client.fx.particles.FXSwarm;
import thaumcraft.client.fx.particles.FXVent;
import thaumcraft.client.fx.particles.FXVisSparkle;
import thaumcraft.client.fx.particles.FXWisp;
import thaumcraft.client.fx.particles.FXWispArcing;
import thaumcraft.client.fx.particles.FXWispEG;

/**
 * ============================================================
 * 【神秘时代粒子大词典】
 * ============================================================
 * 用途：把 client/fx 下所有 FXxxx 类，全部封装成有名字的静态方法。
 *
 * 【重要：只在客户端出粒子】
 * 1. 所有方法都标了 @SideOnly(Side.CLIENT)，
 * 服务端调用 = 空操作，不会崩。
 * 2. 所有方法开头还有 if (!world.isRemote) return;
 * 双保险。
 * 3. 所以：
 * 客户端调 = 出粒子
 * 服务端调 = 静默跳过
 *
 * 【服务端要出粒子怎么办】
 * 目前需要你自己加网络包：
 * 服务端判定 → 发包 → 客户端 onMessage 里调 TCParticles.xxx(...)
 * 参考 TC4 的 PacketFXZap / PacketFXBlockSparkle。
 *
 * 【所有事件都是 TC 那边播放】
 * 本类不实现任何粒子，只是 new TC 的 FXxxx 类。
 * 所以 TC 必须是前置，否则编译不过。
 *
 * 【分类】
 * Section 1 颜色 type 常量
 * Section 2 基础闪光（FXSparkle / FXSpark / FXBurst / FXGeneric / FXSparkleTrail）
 * Section 3 灵光/灵气（FXWisp / FXWispArcing / FXWispEG / FXVisSparkle）
 * Section 4 源质（FXEssentiaTrail）
 * Section 5 光束（FXBeam / FXBeamWand / FXBeamBore / FXBeamGolemBoss / FXBeamPower）
 * Section 6 闪电/电弧（FXLightningBolt / FXArc）
 * Section 7 气泡/液体（FXBubble / FXBubbleAlt / FXSlimyBubble / FXDrop）
 * Section 8 烟雾/轨迹（FXSmokeSpiral / FXSmokeTrail）
 * Section 9 灼烧（FXScorch）
 * Section 10 符文/护盾/音波（FXBlockRunes / FXBlockWard / FXShieldRunes / FXSonic）
 * Section 11 虫群/喷口（FXSwarm / FXVent）
 * Section 12 挖掘/破碎（FXBoreParticles / FXBoreSparkle / FXBreaking）
 * Section 13 组合场景（crit / attackHit / spellCast / fireHit / ...）
 * Section 14 颜色拆解辅助
 * ============================================================
 */
public final class TCParticles {

    private TCParticles() {}

    // ============================================================
    // 【Section 1】颜色 type 常量
    // ============================================================
    // 对应 FXSparkle / FXWisp 构造器的 int type。
    // 具体 RGB 硬编码在 FX 类内部。
    // ============================================================

    public static final int COLOR_PINK_PURPLE = 0; // 紫粉
    public static final int COLOR_YELLOW = 1; // 黄
    public static final int COLOR_BLUE = 2; // 蓝
    public static final int COLOR_GREEN = 3; // 绿
    public static final int COLOR_RED = 4; // 红
    public static final int COLOR_DARK = 5; // 近黑
    public static final int COLOR_WHITE = 6; // 白
    public static final int COLOR_CYAN = 7; // 青

    // ============================================================
    // 【Section 2】基础闪光
    // ============================================================

    /**
     * 一个闪光粒子（最百搭）。
     * FXSparkle 预设 type 版。
     *
     * @param colorType  Section 1 的 COLOR_xxx
     * @param multiplier 寿命倍率：2 短 / 4 中 / 6 长
     */
    @SideOnly(Side.CLIENT)
    public static void sparkle(World w, double x, double y, double z, int colorType, int multiplier) {
        if (w == null || !w.isRemote) return;
        ParticleEngine.instance.addEffect(w, new FXSparkle(w, x, y, z, 1.0F, colorType, multiplier));
    }

    /**
     * 自定义 RGB 闪光。
     */
    @SideOnly(Side.CLIENT)
    public static void sparkleRGB(World w, double x, double y, double z, float r, float g, float b, int multiplier) {
        if (w == null || !w.isRemote) return;
        ParticleEngine.instance.addEffect(w, new FXSparkle(w, x, y, z, 1.0F, r, g, b, multiplier));
    }

    /**
     * 从起点飞向终点的闪光。
     */
    @SideOnly(Side.CLIENT)
    public static void sparkleToTarget(World w, double x, double y, double z, double tx, double ty, double tz,
        int colorType, int multiplier) {
        if (w == null || !w.isRemote) return;
        ParticleEngine.instance.addEffect(w, new FXSparkle(w, x, y, z, tx, ty, tz, 1.0F, colorType, multiplier));
    }

    /**
     * 带拖尾的闪光，朝目标实体飞。
     */
    @SideOnly(Side.CLIENT)
    public static void sparkleTrail(World w, double x, double y, double z, Entity target, float r, float g, float b) {
        if (w == null || !w.isRemote || target == null) return;
        ParticleEngine.instance.addEffect(w, new FXSparkleTrail(w, x, y, z, target, r, g, b));
    }

    /**
     * 短促火花（5~10 tick）。
     */
    @SideOnly(Side.CLIENT)
    public static void spark(World w, double x, double y, double z, float scale) {
        if (w == null || !w.isRemote) return;
        ParticleEngine.instance.addEffect(w, new FXSpark(w, x, y, z, scale));
    }

    /**
     * 爆发环（31 tick，白色，节点纹理）。
     */
    @SideOnly(Side.CLIENT)
    public static void burst(World w, double x, double y, double z, float scale) {
        if (w == null || !w.isRemote) return;
        ParticleEngine.instance.addEffect(w, new FXBurst(w, x, y, z, scale));
    }

    /**
     * 通用粒子（多帧纹理 + 延迟 + 循环）。
     *
     * @param startParticle 起始帧
     * @param numParticles  帧总数
     * @param particleInc   帧间隔
     */
    @SideOnly(Side.CLIENT)
    public static void generic(World w, double x, double y, double z, double mx, double my, double mz, float scale,
        int maxAge, int startParticle, int numParticles, int particleInc) {
        if (w == null || !w.isRemote) return;
        FXGeneric fx = new FXGeneric(w, x, y, z, mx, my, mz);
        fx.setScale(scale);
        fx.setMaxAge(maxAge, 0);
        fx.setParticles(startParticle, numParticles, particleInc);
        ParticleEngine.instance.addEffect(w, fx);
    }

    // ============================================================
    // 【Section 3】灵光 / 灵气
    // ============================================================

    /**
     * 灵光粒子（胖瘦循环）。
     */
    @SideOnly(Side.CLIENT)
    public static void wisp(World w, double x, double y, double z, int colorType) {
        if (w == null || !w.isRemote) return;
        ParticleEngine.instance.addEffect(w, new FXWisp(w, x, y, z, 1.0F, colorType));
    }

    /**
     * 自定义 RGB 灵光。
     */
    @SideOnly(Side.CLIENT)
    public static void wispRGB(World w, double x, double y, double z, float r, float g, float b) {
        if (w == null || !w.isRemote) return;
        ParticleEngine.instance.addEffect(w, new FXWisp(w, x, y, z, 1.0F, r, g, b));
    }

    /**
     * 从起点飞向终点的灵光。
     */
    @SideOnly(Side.CLIENT)
    public static void wispToTarget(World w, double x, double y, double z, double tx, double ty, double tz,
        int colorType) {
        if (w == null || !w.isRemote) return;
        ParticleEngine.instance.addEffect(w, new FXWisp(w, x, y, z, tx, ty, tz, 1.0F, colorType));
    }

    /**
     * 追踪实体的灵光。
     */
    @SideOnly(Side.CLIENT)
    public static void wispAtEntity(World w, double x, double y, double z, Entity target, int colorType) {
        if (w == null || !w.isRemote || target == null) return;
        ParticleEngine.instance.addEffect(w, new FXWisp(w, x, y, z, target, colorType));
    }

    /**
     * 灵光电弧（弧形轨迹）。
     */
    @SideOnly(Side.CLIENT)
    public static void wispArcing(World w, double x, double y, double z, double tx, double ty, double tz, float r,
        float g, float b) {
        if (w == null || !w.isRemote) return;
        ParticleEngine.instance.addEffect(w, new FXWispArcing(w, x, y, z, tx, ty, tz, 1.0F, r, g, b));
    }

    /**
     * 外域灵光（近黑 + 距离衰减）。
     */
    @SideOnly(Side.CLIENT)
    public static void wispEG(World w, double x, double y, double z, Entity target) {
        if (w == null || !w.isRemote) return;
        ParticleEngine.instance.addEffect(w, new FXWispEG(w, x, y, z, target));
    }

    /**
     * 灵气粒子（会朝目标飞，颜色可被 Aspect 覆盖）。
     */
    @SideOnly(Side.CLIENT)
    public static void visSparkle(World w, double x, double y, double z, double tx, double ty, double tz,
        Aspect aspect) {
        if (w == null || !w.isRemote || aspect == null) return;
        FXVisSparkle fx = new FXVisSparkle(w, x, y, z, tx, ty, tz);
        int c = aspect.getColor();
        fx.setRBGColorF(red(c), green(c), blue(c));
        ParticleEngine.instance.addEffect(w, fx);
    }

    // ============================================================
    // 【Section 4】源质
    // ============================================================

    /**
     * 源质流（颜色跟 Aspect 走，正弦呼吸缩放）。
     */
    @SideOnly(Side.CLIENT)
    public static void essentiaTrail(World w, double x, double y, double z, double tx, double ty, double tz,
        Aspect aspect, float scale) {
        if (w == null || !w.isRemote || aspect == null) return;
        ParticleEngine.instance.addEffect(w, new FXEssentiaTrail(w, x, y, z, tx, ty, tz, 0, aspect.getColor(), scale));
    }

    // ============================================================
    // 【Section 5】光束
    // ============================================================

    /**
     * 基础光束（点到点）。
     *
     * @param type 0 beam.png / 1 beam1 / 2 beam2 / 3 beam3
     */
    @SideOnly(Side.CLIENT)
    public static void beam(World w, double x, double y, double z, double tx, double ty, double tz, float r, float g,
        float b, int age, int type, float width) {
        if (w == null || !w.isRemote) return;
        FXBeam fx = new FXBeam(w, x, y, z, tx, ty, tz, r, g, b, age);
        fx.setType(type);
        fx.width = width;
        fx.setPulse(true);
        ParticleEngine.instance.addEffect(w, fx);
    }

    /**
     * 光束——跟随实体（终点跟着实体走）。
     */
    @SideOnly(Side.CLIENT)
    public static void beamToEntity(World w, double x, double y, double z, Entity target, float r, float g, float b,
        int age, int type, float width) {
        if (w == null || !w.isRemote || target == null) return;
        FXBeam fx = new FXBeam(w, x, y, z, target, r, g, b, age);
        fx.setType(type);
        fx.width = width;
        fx.setPulse(true);
        ParticleEngine.instance.addEffect(w, fx);
    }

    /**
     * 法杖光束（从玩家手部射出）。
     */
    @SideOnly(Side.CLIENT)
    public static void beamWand(World w, EntityPlayer player, double tx, double ty, double tz, float r, float g,
        float b, int age, int type, float width) {
        if (w == null || !w.isRemote || player == null) return;
        FXBeamWand fx = new FXBeamWand(w, player, tx, ty, tz, r, g, b, age);
        fx.setType(type);
        fx.width = width;
        fx.setPulse(true);
        ParticleEngine.instance.addEffect(w, fx);
    }

    /**
     * 奥术钻头光束（末端带冲击环）。
     *
     * @param impact 冲击环强度（0 关闭，4~6 强烈）
     */
    @SideOnly(Side.CLIENT)
    public static void beamBore(World w, double px, double py, double pz, double tx, double ty, double tz, float r,
        float g, float b, int age, int type, int impact) {
        if (w == null || !w.isRemote) return;
        FXBeamBore fx = new FXBeamBore(w, px, py, pz, tx, ty, tz, r, g, b, age);
        fx.setType(type);
        fx.impact = impact;
        fx.setPulse(true);
        ParticleEngine.instance.addEffect(w, fx);
    }

    /**
     * 傀儡 Boss 光束（从 Boss 眼睛射出，跟随目标）。
     */
    @SideOnly(Side.CLIENT)
    public static void beamGolemBoss(World w, EntityLivingBase boss, Entity target, float r, float g, float b, int age,
        int type, float width) {
        if (w == null || !w.isRemote || boss == null || target == null) return;
        FXBeamGolemBoss fx = new FXBeamGolemBoss(w, boss, target, r, g, b, age);
        fx.setType(type);
        fx.width = width;
        fx.setPulse(true);
        ParticleEngine.instance.addEffect(w, fx);
    }

    /**
     * 能量束（灰色低透明度，戴揭示护目镜会亮 10 倍）。
     */
    @SideOnly(Side.CLIENT)
    public static void beamPower(World w, double px, double py, double pz, double tx, double ty, double tz, int age) {
        if (w == null || !w.isRemote) return;
        ParticleEngine.instance.addEffect(w, new FXBeamPower(w, px, py, pz, tx, ty, tz, 0.5F, 0.5F, 0.5F, age));
    }

    // ============================================================
    // 【Section 6】闪电 / 电弧
    // ============================================================

    /**
     * 闪电（默认多段折线 + 分叉）。
     *
     * @param type  0 紫 / 1 黄 / 2 蓝 / 3 绿 / 4 红 / 5 黑 / 6 淡青
     * @param width 线宽，普通 0.06F~0.125F，粗闪电 0.2F
     */
    @SideOnly(Side.CLIENT)
    public static void lightning(World w, double x1, double y1, double z1, double x2, double y2, double z2, int type,
        float width) {
        if (w == null || !w.isRemote) return;
        FXLightningBolt bolt = new FXLightningBolt(w, x1, y1, z1, x2, y2, z2, w.rand.nextLong(), 6, 0.5F, 8);
        bolt.defaultFractal();
        bolt.setType(type);
        bolt.setWidth(width);
        bolt.finalizeBolt();
    }

    /**
     * 电弧（沿抛物线折线，十字带状，瞬时）。
     *
     * @param color ARGB int，可传 Aspect.getColor()
     */
    @SideOnly(Side.CLIENT)
    public static void arc(World w, double x1, double y1, double z1, double x2, double y2, double z2, int color) {
        if (w == null || !w.isRemote) return;
        ParticleEngine.instance
            .addEffect(w, new FXArc(w, x1, y1, z1, x2, y2, z2, red(color), green(color), blue(color), 0.115));
    }

    // ============================================================
    // 【Section 7】气泡 / 液体
    // ============================================================

    /**
     * 气泡（坩埚冒泡）。
     *
     * @param color ARGB int，可传 Aspect.getColor() 或任意颜色
     * @param age   基础寿命
     */
    @SideOnly(Side.CLIENT)
    public static void bubble(World w, double x, double y, double z, double mx, double my, double mz, int color,
        int age) {
        if (w == null || !w.isRemote) return;
        FXBubble fx = new FXBubble(w, x, y, z, mx, my, mz, age);
        fx.setRGB(red(color), green(color), blue(color));
        ParticleEngine.instance.addEffect(w, fx);
    }

    /**
     * 气泡变体（有"破裂"帧，从小变大）。
     */
    @SideOnly(Side.CLIENT)
    public static void bubbleAlt(World w, double x, double y, double z, double mx, double my, double mz, int color,
        int age) {
        if (w == null || !w.isRemote) return;
        FXBubbleAlt fx = new FXBubbleAlt(w, x, y, z, mx, my, mz, age);
        fx.setRGB(red(color), green(color), blue(color));
        ParticleEngine.instance.addEffect(w, fx);
    }

    /**
     * 黏液气泡（7 帧弹跳）。
     */
    @SideOnly(Side.CLIENT)
    public static void slimyBubble(World w, double x, double y, double z, float scale) {
        if (w == null || !w.isRemote) return;
        ParticleEngine.instance.addEffect(w, new FXSlimyBubble(w, x, y, z, scale));
    }

    /**
     * 液滴（完整物理：重力、碰撞、水中消失）。
     */
    @SideOnly(Side.CLIENT)
    public static void drop(World w, double x, double y, double z, float r, float g, float b) {
        if (w == null || !w.isRemote) return;
        ParticleEngine.instance.addEffect(w, new FXDrop(w, x, y, z, r, g, b));
    }

    // ============================================================
    // 【Section 8】烟雾 / 轨迹
    // ============================================================

    /**
     * 螺旋烟雾（螺旋上升 + 线性淡出）。
     *
     * @param radius 螺旋半径
     * @param start  起始角度（度）
     * @param miny   最低 y
     */
    @SideOnly(Side.CLIENT)
    public static void smokeSpiral(World w, double x, double y, double z, float radius, int start, int miny) {
        if (w == null || !w.isRemote) return;
        ParticleEngine.instance.addEffect(w, new FXSmokeSpiral(w, x, y, z, radius, start, miny));
    }

    /**
     * 烟雾轨迹（朝目标飞）。
     */
    @SideOnly(Side.CLIENT)
    public static void smokeTrail(World w, double x, double y, double z, Entity target, float r, float g, float b) {
        if (w == null || !w.isRemote || target == null) return;
        ParticleEngine.instance.addEffect(w, new FXSmokeTrail(w, x, y, z, target, r, g, b));
    }

    // ============================================================
    // 【Section 9】灼烧
    // ============================================================

    /**
     * 灼烧粒子（颜色从蓝到白动态变）。
     *
     * @param direction 方向（玩家视线/攻击方向）
     * @param spread    散布半径（lance=false 时有效）
     * @param lance     true = 笔直，false = 散开
     */
    @SideOnly(Side.CLIENT)
    public static void scorch(World w, double x, double y, double z, Vec3 direction, float spread, boolean lance) {
        if (w == null || !w.isRemote || direction == null) return;
        ParticleEngine.instance.addEffect(w, new FXScorch(w, x, y, z, direction, spread, lance));
    }

    /**
     * 火焰喷射（连发 3 个 scorch，散开）。
     */
    @SideOnly(Side.CLIENT)
    public static void fireSpray(World w, double x, double y, double z, Vec3 direction) {
        if (w == null || !w.isRemote || direction == null) return;
        for (int i = 0; i < 3; i++) {
            ParticleEngine.instance.addEffect(w, new FXScorch(w, x, y, z, direction, 2.0F, false));
        }
    }

    // ============================================================
    // 【Section 10】符文 / 护盾 / 音波
    // ============================================================

    /**
     * 方块符文粒子（16 种符文 + 4 方向随机）。
     */
    @SideOnly(Side.CLIENT)
    public static void blockRunes(World w, double x, double y, double z, float r, float g, float b, int multiplier) {
        if (w == null || !w.isRemote) return;
        ParticleEngine.instance.addEffect(w, new FXBlockRunes(w, x, y, z, r, g, b, multiplier));
    }

    /**
     * 方块守护符文（16 帧半球纹理，随机旋转）。
     */
    @SideOnly(Side.CLIENT)
    public static void blockWard(World w, double x, double y, double z, ForgeDirection side, float fx, float fy,
        float fz) {
        if (w == null || !w.isRemote) return;
        ParticleEngine.instance.addEffect(w, new FXBlockWard(w, x, y, z, side, fx, fy, fz));
    }

    /**
     * 护盾符文（3D 半球模型，跟随实体）。
     *
     * @param yaw   朝向（target < 0 时用 0）
     * @param pitch 朝向（target = -1 时用 90/270）
     */
    @SideOnly(Side.CLIENT)
    public static void shieldRunes(World w, double x, double y, double z, Entity target, float yaw, float pitch) {
        if (w == null || !w.isRemote || target == null) return;
        ParticleEngine.instance.addEffect(w, new FXShieldRunes(w, x, y, z, target, 8, yaw, pitch));
    }

    /**
     * 声波（3D 半球模型，沿视线拉伸）。
     */
    @SideOnly(Side.CLIENT)
    public static void sonic(World w, double x, double y, double z, Entity target) {
        if (w == null || !w.isRemote || target == null) return;
        ParticleEngine.instance.addEffect(w, new FXSonic(w, x, y, z, target, 10));
    }

    // ============================================================
    // 【Section 11】虫群 / 喷口
    // ============================================================

    /**
     * 虫群（追踪目标，目标受伤时颜色变暗）。
     */
    @SideOnly(Side.CLIENT)
    public static void swarm(World w, double x, double y, double z, Entity target, float r, float g, float b) {
        if (w == null || !w.isRemote || target == null) return;
        ParticleEngine.instance.addEffect(w, new FXSwarm(w, x, y, z, target, r, g, b));
    }

    /**
     * 虫群（自定义速度 / 转向 / 重力）。
     */
    @SideOnly(Side.CLIENT)
    public static void swarmCustom(World w, double x, double y, double z, Entity target, float r, float g, float b,
        float speed, float turnSpeed, float gravity) {
        if (w == null || !w.isRemote || target == null) return;
        ParticleEngine.instance.addEffect(w, new FXSwarm(w, x, y, z, target, r, g, b, speed, turnSpeed, gravity));
    }

    /**
     * 喷口粒子（从大变小，向上飘）。
     */
    @SideOnly(Side.CLIENT)
    public static void vent(World w, double x, double y, double z, double mx, double my, double mz, int color) {
        if (w == null || !w.isRemote) return;
        ParticleEngine.instance.addEffect(w, new FXVent(w, x, y, z, mx, my, mz, color));
    }

    // ============================================================
    // 【Section 12】挖掘 / 破碎
    // ============================================================

    /**
     * 挖掘碎屑（方块版，颜色取方块纹理）。
     */
    @SideOnly(Side.CLIENT)
    public static void boreParticlesBlock(World w, double x, double y, double z, double tx, double ty, double tz,
        Block block, int side, int meta) {
        if (w == null || !w.isRemote || block == null) return;
        FXBoreParticles fx = new FXBoreParticles(w, x, y, z, tx, ty, tz, block, side, meta);
        fx.func_70596_a((int) tx, (int) ty, (int) tz);
        ParticleEngine.instance.addEffect(w, fx);
    }

    /**
     * 挖掘碎屑（物品版）。
     */
    @SideOnly(Side.CLIENT)
    public static void boreParticlesItem(World w, double x, double y, double z, double tx, double ty, double tz,
        Item item, int side, int meta) {
        if (w == null || !w.isRemote || item == null) return;
        FXBoreParticles fx = new FXBoreParticles(w, x, y, z, tx, ty, tz, item, side, meta);
        fx.func_70596_a((int) tx, (int) ty, (int) tz);
        ParticleEngine.instance.addEffect(w, fx);
    }

    /**
     * 钻头火花（绿色，朝目标飞）。
     */
    @SideOnly(Side.CLIENT)
    public static void boreSparkle(World w, double x, double y, double z, double tx, double ty, double tz) {
        if (w == null || !w.isRemote) return;
        ParticleEngine.instance.addEffect(w, new FXBoreSparkle(w, x, y, z, tx, ty, tz));
    }

    /**
     * 物品破碎粒子。
     */
    @SideOnly(Side.CLIENT)
    public static void breaking(World w, double x, double y, double z, Item item) {
        if (w == null || !w.isRemote || item == null) return;
        ParticleEngine.instance.addEffect(w, new FXBreaking(w, x, y, z, item));
    }

    // ============================================================
    // 【Section 13】组合场景（一次出多个粒子）
    // ============================================================

    /**
     * 普通攻击命中：白闪 + 少量火花。
     */
    @SideOnly(Side.CLIENT)
    public static void attackHit(World w, double x, double y, double z) {
        sparkle(w, x, y, z, COLOR_WHITE, 2);
        spark(w, x, y, z, 0.6F);
    }

    /**
     * 暴击命中：红色闪光 + 爆发环 + 少量粉紫灵光。
     */
    @SideOnly(Side.CLIENT)
    public static void crit(World w, double x, double y, double z) {
        if (w == null || !w.isRemote) return;
        FXSparkle s = new FXSparkle(w, x, y, z, 1.0F, COLOR_RED, 4);
        s.tinkle = true;
        ParticleEngine.instance.addEffect(w, s);
        ParticleEngine.instance.addEffect(w, new FXBurst(w, x, y, z, 0.6F));
        ParticleEngine.instance.addEffect(w, new FXWisp(w, x, y, z, 1.0F, COLOR_PINK_PURPLE));
    }

    /**
     * 暴击——直接传目标实体。
     */
    @SideOnly(Side.CLIENT)
    public static void critAtEntity(Entity target) {
        if (target == null || target.worldObj == null) return;
        crit(target.worldObj, target.posX, target.posY + target.height * 0.5, target.posZ);
    }

    /**
     * 法术施放成功：白闪 + 紫粉灵光 + 爆发环。
     */
    @SideOnly(Side.CLIENT)
    public static void spellCast(World w, double x, double y, double z) {
        sparkle(w, x, y, z, COLOR_WHITE, 3);
        wisp(w, x, y, z, COLOR_PINK_PURPLE);
        burst(w, x, y, z, 0.5F);
    }

    /**
     * 火焰命中：火焰喷射 + 红色闪光。
     */
    @SideOnly(Side.CLIENT)
    public static void fireHit(World w, double x, double y, double z, Vec3 direction) {
        fireSpray(w, x, y, z, direction);
        sparkle(w, x, y, z, COLOR_RED, 2);
    }

    /**
     * 冰霜命中：青色闪光 + 少量蓝闪。
     */
    @SideOnly(Side.CLIENT)
    public static void frostHit(World w, double x, double y, double z) {
        sparkle(w, x, y, z, COLOR_CYAN, 3);
        sparkleRGB(w, x, y + 0.3, z, 0.5F, 0.7F, 1.0F, 2);
    }

    /**
     * 雷击：闪电 + 蓝色闪光 + 爆发。
     */
    @SideOnly(Side.CLIENT)
    public static void lightningAttack(World w, double x1, double y1, double z1, double x2, double y2, double z2) {
        lightning(w, x1, y1, z1, x2, y2, z2, 2, 0.08F);
        sparkle(w, x2, y2, z2, COLOR_BLUE, 3);
        burst(w, x2, y2, z2, 0.5F);
    }

    /**
     * 电弧攻击（点到点）+ 蓝闪。
     */
    @SideOnly(Side.CLIENT)
    public static void arcAttack(World w, double x1, double y1, double z1, double x2, double y2, double z2, int color) {
        arc(w, x1, y1, z1, x2, y2, z2, color);
        sparkleRGB(w, x2, y2, z2, red(color), green(color), blue(color), 2);
    }

    /**
     * 原质爆发（吃 Aspect 颜色，四周散 4 个闪光）。
     */
    @SideOnly(Side.CLIENT)
    public static void aspectBurst(World w, double x, double y, double z, Aspect aspect) {
        if (aspect == null || w == null || !w.isRemote) return;
        int c = aspect.getColor();
        for (int i = 0; i < 4; i++) {
            double dx = (w.rand.nextDouble() - 0.5) * 0.5;
            double dy = w.rand.nextDouble() * 0.5;
            double dz = (w.rand.nextDouble() - 0.5) * 0.5;
            ParticleEngine.instance
                .addEffect(w, new FXSparkle(w, x + dx, y + dy, z + dz, 1.2F, red(c), green(c), blue(c), 3));
        }
    }

    /**
     * 击杀反馈：暗紫闪 + 粉紫灵光。
     */
    @SideOnly(Side.CLIENT)
    public static void killFeedback(World w, double x, double y, double z) {
        sparkle(w, x, y, z, COLOR_PINK_PURPLE, 4);
        sparkleRGB(w, x, y + 0.3, z, 0.4F, 0.1F, 0.6F, 3);
        wisp(w, x, y, z, COLOR_DARK);
    }

    /**
     * 护盾格挡：青色闪 + 爆发。
     */
    @SideOnly(Side.CLIENT)
    public static void shieldBlock(World w, double x, double y, double z) {
        sparkle(w, x, y, z, COLOR_CYAN, 2);
        burst(w, x, y, z, 0.4F);
    }

    /**
     * 武器强化完成：白闪 + 爆发 + 灵光。
     */
    @SideOnly(Side.CLIENT)
    public static void upgrade(World w, double x, double y, double z) {
        burst(w, x, y, z, 0.8F);
        sparkle(w, x, y, z, COLOR_WHITE, 6);
        wisp(w, x, y, z, COLOR_PINK_PURPLE);
    }

    // ============================================================
    // 【Section 14】颜色拆解辅助
    // ============================================================

    private static float red(int color) {
        return (color >> 16 & 255) / 255F;
    }

    private static float green(int color) {
        return (color >> 8 & 255) / 255F;
    }

    private static float blue(int color) {
        return (color & 255) / 255F;
    }
}
