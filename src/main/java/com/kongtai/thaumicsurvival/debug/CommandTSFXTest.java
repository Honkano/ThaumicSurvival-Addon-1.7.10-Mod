package com.kongtai.thaumicsurvival.debug;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * 【调试指令 /tsfx】
 * 只在客户端运行，直接打开粒子/音效测试 GUI。
 * 不发给服务端，不联网，不注册任何东西。
 */
@SideOnly(Side.CLIENT)
public class CommandTSFXTest extends CommandBase {

    @Override
    public String getCommandName() {
        return "tsfx";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "/tsfx";
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) {
        // 不直接开 GUI，只打标记，下一 tick 在 ClientTickEvent 里开
        TSFXTestGui.pendingOpen = true;
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 0;
    }

    @Override
    public boolean canCommandSenderUseCommand(ICommandSender sender) {
        return true;
    }
}
