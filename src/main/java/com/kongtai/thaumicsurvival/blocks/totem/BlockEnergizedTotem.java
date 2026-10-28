package com.kongtai.thaumicsurvival.blocks.totem;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import com.kongtai.thaumicsurvival.creativetab.TSTabs;

import thaumcraft.common.tiles.TileNode;

public class BlockEnergizedTotem extends Block implements ITileEntityProvider {

    public BlockEnergizedTotem() {
        super(Material.rock);
        this.setBlockName("thaumicsurvival.energized_totem");
        this.setBlockTextureName("thaumcraft:obsidiantotembase");
        this.setHardness(30.0F);
        this.setResistance(999.0F);
        this.setStepSound(soundTypeStone);

        // ★ 给方块挂上创造栏，它的物品形态也会自动跟过来
        this.setCreativeTab(TSTabs.TAB_THAUMICSURVIVAL);
    }

    private net.minecraft.util.IIcon[] icons = new net.minecraft.util.IIcon[7];

    // 注册贴图（要把所有面的图都拿过来）
    @cpw.mods.fml.relauncher.SideOnly(cpw.mods.fml.relauncher.Side.CLIENT)
    @Override
    public void registerBlockIcons(net.minecraft.client.renderer.texture.IIconRegister register) {
        this.icons[0] = register.registerIcon("thaumcraft:obsidiantile");
        this.icons[1] = register.registerIcon("thaumcraft:obsidiantotembase");
        this.icons[2] = register.registerIcon("thaumcraft:obsidiantotem1");
        this.icons[3] = register.registerIcon("thaumcraft:obsidiantotem2");
        this.icons[4] = register.registerIcon("thaumcraft:obsidiantotem3");
        this.icons[5] = register.registerIcon("thaumcraft:obsidiantotem4");
        this.icons[6] = register.registerIcon("thaumcraft:obsidiantotembaseshaded");
    }

    // 抄 A 哥的“长脸”逻辑
    @cpw.mods.fml.relauncher.SideOnly(cpw.mods.fml.relauncher.Side.CLIENT)
    @Override
    public net.minecraft.util.IIcon getIcon(net.minecraft.world.IBlockAccess ba, int x, int y, int z, int side) {
        if (side > 1) { // 侧面才长脸
            boolean hasAbove = ba.getBlock(x, y + 1, z) == this;
            boolean hasBelow = ba.getBlock(x, y - 1, z) == this;

            if (hasAbove) {
                return this.icons[6]; // 顶上被压着，用阴影
            } else if (hasBelow) {
                return this.icons[2 + Math.abs((side + x % 4 + z % 4 + y % 4) % 4)]; // 柱身长脸
            }
        }
        return this.icons[1]; // 普通底座
    }

    // ==========================================
    // ★ 修复物品栏/手持时的 3D 渲染贴图
    // ==========================================
    @cpw.mods.fml.relauncher.SideOnly(cpw.mods.fml.relauncher.Side.CLIENT)
    @Override
    public net.minecraft.util.IIcon getIcon(int side, int meta) {
        // 物品栏里不需要长脸，直接拿底座（或者柱身）当默认图标
        // 咱们刚才注册的 icons[1] 是底座，icons[2] 是柱身，你挑一个好看的
        return this.icons[1];
    }

    @Override
    public boolean hasTileEntity(int metadata) {
        return true;
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta) {
        return new TileNode();
    }
}
