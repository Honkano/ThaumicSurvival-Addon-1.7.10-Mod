package com.kongtai.thaumicsurvival.blocks;

import net.minecraft.block.Block;

import com.kongtai.thaumicsurvival.blocks.totem.BlockEnergizedTotem;

import cpw.mods.fml.common.registry.GameRegistry;

public class ModBlocks {

    public static Block energizedTotem;

    public static void registerBlocks() {
        energizedTotem = new BlockEnergizedTotem();

        // ★ 最标准的写法：直接传名字，Forge 会自动把它的 ItemBlock 也建好！
        GameRegistry.registerBlock(energizedTotem, "energized_totem");
    }
}
