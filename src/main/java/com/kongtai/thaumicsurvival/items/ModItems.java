package com.kongtai.thaumicsurvival.items;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;

public class ModItems {

    // 这里其实不需要注册原版苹果，因为原版苹果已经有了，我们只是给它挂上 NBT
    public static void registerItems() {
        // 如果你以后有自己的新物品，在这里注册
    }

    // 专门生成带 NBT 的金苹果
    public static ItemStack createGoldenApple(int meta, String name, int tier, String... loreLines) {
        ItemStack stack = new ItemStack(Items.golden_apple, 1, meta);
        NBTTagCompound display = new NBTTagCompound();
        display.setString("Name", name);
        NBTTagList lore = new NBTTagList();
        for (String line : loreLines) {
            lore.appendTag(new NBTTagString(line));
        }
        display.setTag("Lore", lore);
        NBTTagCompound nbt = new NBTTagCompound();
        nbt.setTag("display", display);
        nbt.setString("thaumicsurvival:apple_type", meta == 0 ? "simple" : "enchanted");
        nbt.setInteger("thaumicsurvival:tier", tier);
        stack.setTagCompound(nbt);
        return stack;
    }
}
