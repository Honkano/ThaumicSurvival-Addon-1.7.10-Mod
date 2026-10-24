package com.kongtai.thaumicsurvival;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.kongtai.thaumicsurvival.proxy.CommonProxy;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;

/**
 * 【神秘生存学】的主类！
 * 这个类就像是模组的心脏，Forge 启动时会首先找到这里。
 */
@Mod(
    modid = ThaumicSurvival.MODID, // 模组唯一ID，必须全小写
    version = Tags.VERSION, // 版本号（GTNH RFG 构建系统自动生成的）
    name = ThaumicSurvival.NAME, // 模组的展示名字
    dependencies = "required-after:Thaumcraft", // 👈 加上这一句！
    acceptedMinecraftVersions = "[1.7.10]" // 限制只能在 1.7.10 运行

)
public class ThaumicSurvival {

    // ==================== 核心常量 ====================
    // 定义模组 ID，其他地方（比如注册物品、网络包）都要用到它
    public static final String MODID = "thaumicsurvival"; // 👈 注意：MODID 最好全小写，避免有些系统不认大写
    public static final String NAME = "神秘生存学";

    // 全局日志工具，你可以在代码里用 ThaumicSurvival.LOG.info("xxx") 来打印控制台信息
    public static final Logger LOG = LogManager.getLogger(MODID);

    // ==================== 双端代理 ====================
    // 这是 Forge 的“左右互搏术”。
    // 如果运行在客户端，proxy 会自动变成 ClientProxy；
    // 如果运行在服务器，proxy 会自动变成 CommonProxy。
    // 注意：路径一定要和你新建的文件夹一模一样！
    @SidedProxy(
        clientSide = "com.kongtai.thaumicsurvival.proxy.ClientProxy",
        serverSide = "com.kongtai.thaumicsurvival.proxy.CommonProxy")
    public static CommonProxy proxy;

    // ==================== 生命周期阶段 ====================

    /**
     * 第一阶段：预初始化 (Pre-Init)
     * 这个阶段主要用来注册物品、方块、配置文件和网络通道。
     * （此时原版和 Forge 的核心还没加载完）
     */
    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        // 把任务扔给代理类去处理
        proxy.preInit(event);
    }

    /**
     * 第二阶段：初始化 (Init)
     * 这个阶段原版的数据都加载完了。主要用来注册合成表（Workbench, 奥术工作台）、
     * 注册事件监听器（比如吃苹果加研究点）。
     */
    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.init(event);
    }

    /**
     * 第三阶段：后初始化 (Post-Init)
     * 所有模组都已经加载完毕。主要用来跨模组联动（比如检查有没有装 IC2），
     * 或者注册神秘时代的注魔配方/研究节点（因为必须等神秘时代加载完）。
     */
    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        proxy.postInit(event);
    }

    /**
     * 第四阶段：服务器启动前 (ServerStarting)
     * 如果是开服，客户端连进来之前会触发。这里通常用来注册指令（比如 /home）。
     */
    @Mod.EventHandler
    public void serverStarting(FMLServerStartingEvent event) {
        proxy.serverStarting(event);
    }
}
