package com.kongtai.thaumicsurvival.thaumcraftwork.research;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import net.minecraft.item.ItemStack;

import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.research.ResearchCategories;
import thaumcraft.api.research.ResearchItem;
import thaumcraft.api.research.ResearchPage;

/**
 * 【镜像研究黑科技类】
 * 作用：把一个已经存在的原版/其他模组的研究节点，映射到你的分类里。
 * 特点：不用抄文本，不用抄配方，原版更新它自动更新。
 */
public class MirrorResearchItem extends ResearchItem {

    private ResearchItem original;

    public MirrorResearchItem(String newKey, String newCategory, String originalCategory, String originalKey, int col,
        int row, ItemStack icon) {
        super(newKey, newCategory, new AspectList(), col, row, 1, icon);

        // 👇 加上安全判断，如果分类不存在或者研究不存在，就不硬摸，直接返回
        if (ResearchCategories.researchCategories.containsKey(originalCategory)) {
            this.original = ResearchCategories.researchCategories.get(originalCategory).research.get(originalKey);
        }

        if (this.original != null) {
            bindToOriginal();
            this.setStub();
            this.setHidden();
        } else {
            // 没找到，打印个警告，并把镜子设为不可见，绝不崩溃
            System.out.println("[ThaumicSurvival镜像] 找不到原版研究：" + originalKey);
            this.setHidden();
            this.setStub(); // 防止玩家意外解锁空节点
        }
    }

    private void bindToOriginal() {
        // 1.7.10 里 siblings 是公开字段，直接摸！
        String[] sib = original.siblings;

        if (sib == null) {
            // 如果原版没兄弟，直接把自己塞进去
            original.siblings = new String[] { this.key };
        } else {
            // 如果有兄弟，转成 List，把自己加进去，再转回数组
            List<String> list = new ArrayList<String>(Arrays.asList(sib));
            list.add(this.key);
            original.siblings = list.toArray(new String[0]);
        }
    }

    @Override
    public ResearchPage[] getPages() {
        return original != null ? original.getPages() : super.getPages();
    }

    @Override
    public String getName() {
        return original != null ? original.getName() : super.getName();
    }

    @Override
    public String getText() {
        return original != null ? original.getText() : super.getText();
    }
}
