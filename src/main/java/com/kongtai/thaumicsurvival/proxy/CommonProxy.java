package com.kongtai.thaumicsurvival.proxy;

import net.minecraftforge.common.MinecraftForge;

import com.kongtai.thaumicsurvival.ThaumicSurvival;
import com.kongtai.thaumicsurvival.handlers.FoodEventHandler;
import com.kongtai.thaumicsurvival.thaumcraftwork.recipes.ArcaneShapedRecipes;
import com.kongtai.thaumicsurvival.thaumcraftwork.recipes.ArcaneShapelessRecipes;
import com.kongtai.thaumicsurvival.thaumcraftwork.recipes.CrucibleRecipes;
import com.kongtai.thaumicsurvival.thaumcraftwork.recipes.DynamicInfusionRecipes;
import com.kongtai.thaumicsurvival.thaumcraftwork.recipes.InfusionRecipes;
import com.kongtai.thaumicsurvival.thaumcraftwork.research.TSResearchRegistry;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;

public class CommonProxy {

    public void preInit(FMLPreInitializationEvent event) {
        ThaumicSurvival.LOG.info("[ThaumicSurvival]神秘生存学 正在预初始化...");
    }

    public void init(FMLInitializationEvent event) {
        ThaumicSurvival.LOG.info("[ThaumicSurvival]神秘生存学 正在初始化...");

        // 1. 注册配方
        ArcaneShapedRecipes.init(); // 奥术有序合成
        ArcaneShapelessRecipes.init(); // 奥术无序合成

        InfusionRecipes.init(); // 注魔合成
        DynamicInfusionRecipes.init(); // 注魔动态合成（只是动态页显示）

        CrucibleRecipes.init();// 坩埚合成

        // 2. 注册研究页面
        TSResearchRegistry.init();

        // 3. 注册事件（吃苹果的监听）
        MinecraftForge.EVENT_BUS.register(new FoodEventHandler());
    }

    public void postInit(FMLPostInitializationEvent event) {
        ThaumicSurvival.LOG.info("[ThaumicSurvival]神秘生存学 后初始化完成！");
        TSResearchRegistry.postInit(); // 开启镜像研究节点模式 详情请记观察 [MirrorResearchItem.java]
    }

    public void serverStarting(FMLServerStartingEvent event) {
        // 服务器指令在这里注册
    }
}
