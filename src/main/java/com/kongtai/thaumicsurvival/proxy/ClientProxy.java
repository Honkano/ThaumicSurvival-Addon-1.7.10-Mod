package com.kongtai.thaumicsurvival.proxy;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public class ClientProxy extends CommonProxy {

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        super.preInit(event);
        // 客户端专属代码：以后要渲染贴图、注册按键就写在这里

        // 注册本代理到 FML 事件总线，用于接收 ClientTickEvent
        // （/tsfx 指令执行后延迟一 tick 再开 GUI，避免被聊天栏关闭逻辑覆盖）
    }

    @Override
    public void init(FMLInitializationEvent event) {
        super.init(event);

        // 客户端专属代码：以后要绑定模型就写在这里
    }

    /**
     * 每 tick 检查一次：如果指令把 pendingOpen 置 true，就在这一 tick 打开 GUI。
     *
     * 为什么需要延迟：
     * GuiChat.keyTyped 按回车后的顺序是
     * 1. mc.handleClientCommand(s) ← 我们的 processCommand 在这里跑
     * 2. mc.displayGuiScreen(null) ← 紧接着聊天栏关掉，把 GUI 也覆盖成 null
     * 所以在 processCommand 里直接 displayGuiScreen(new TSFXTestGui()) 会被第二行覆盖。
     * 延迟到下一 tick，聊天栏已经关完了，再开 GUI 就不会被覆盖。
     */
}
