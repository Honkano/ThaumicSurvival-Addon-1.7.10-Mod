package com.kongtai.thaumicsurvival.thaumcraftwork.research;

import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.research.ResearchCategories;
import thaumcraft.api.research.ResearchItem;
import thaumcraft.api.research.ResearchPage;

/**
 * 【镜像研究】
 *
 * 运行时引用委托：镜像不复制原研究内容，只持有原研究对象引用。
 * getName / getText / getPages 全部实时转发给原研究。
 *
 * 图标：自动构造函数从原研究借 icon_resource（纹理图标）。
 * 如果原研究是 ItemStack 图标，请用带 ItemStack 的构造函数手动指定，
 * 或者接受兜底纹理。
 */
public class MirrorResearchItem extends ResearchItem {

    /** 原研究没有纹理图标时使用的兜底纹理 */
    private static final ResourceLocation FALLBACK_ICON = new ResourceLocation(
        "thaumcraft",
        "textures/misc/r_eldritchmajor.png");

    /** 被镜像的原研究对象（运行时引用，不是快照） */
    private ResearchItem original;

    /**
     * 自动借图标构造：从原研究借 icon_resource。
     * 原研究是 ItemStack 图标时使用兜底纹理。
     */
    public MirrorResearchItem(String newKey, String newCategory, String originalCategory, String originalKey, int col,
        int row) {
        super(newKey, newCategory, new AspectList(), col, row, 1, resolveIcon(originalCategory, originalKey));
        initMirror(originalCategory, originalKey);
    }

    /**
     * 手动指定 ItemStack 图标构造。
     */
    public MirrorResearchItem(String newKey, String newCategory, String originalCategory, String originalKey, int col,
        int row, ItemStack icon) {
        super(newKey, newCategory, new AspectList(), col, row, 1, icon);
        initMirror(originalCategory, originalKey);
    }

    // ================== 静态辅助 ==================

    /**
     * 在 super(...) 之前解析原研究的纹理图标。
     * 因为 icon_resource 是 final，只能在 super 时赋值，所以必须静态解析。
     */
    private static ResourceLocation resolveIcon(String originalCategory, String originalKey) {
        ResearchItem orig = findOriginal(originalCategory, originalKey);
        if (orig != null && orig.icon_resource != null) {
            return orig.icon_resource;
        }
        return FALLBACK_ICON;
    }

    private static ResearchItem findOriginal(String originalCategory, String originalKey) {
        if (ResearchCategories.researchCategories == null) {
            return null;
        }
        if (!ResearchCategories.researchCategories.containsKey(originalCategory)) {
            return null;
        }
        return ResearchCategories.researchCategories.get(originalCategory).research.get(originalKey);
    }

    // ================== 初始化 ==================

    private void initMirror(String originalCategory, String originalKey) {
        this.original = findOriginal(originalCategory, originalKey);

        if (this.original == null) {
            System.out.println("[ThaumicSurvival镜像] 找不到原研究：" + originalCategory + "/" + originalKey + "，镜像自动隐藏。");
            this.setHidden();
            this.setStub();
            return;
        }

        bindToOriginal();
        this.setStub();
        this.setHidden();
    }

    /**
     * 把镜像挂到原研究的 siblings 数组里。
     */
    private void bindToOriginal() {
        String[] sib = this.original.siblings;

        if (sib == null || sib.length == 0) {
            this.original.siblings = new String[] { this.key };
            return;
        }

        for (String s : sib) {
            if (this.key.equals(s)) {
                return;
            }
        }

        String[] ns = new String[sib.length + 1];
        System.arraycopy(sib, 0, ns, 0, sib.length);
        ns[sib.length] = this.key;
        this.original.siblings = ns;
    }

    // ================== 内容折射 ==================

    @Override
    public ResearchPage[] getPages() {
        return this.original != null ? this.original.getPages() : super.getPages();
    }

    @Override
    public String getName() {
        return this.original != null ? this.original.getName() : super.getName();
    }

    @Override
    public String getText() {
        return this.original != null ? this.original.getText() : super.getText();
    }

    // 顺从原版：带刺（特殊节点）
    @Override
    public boolean isSpecial() {
        return this.original != null ? this.original.isSpecial() : super.isSpecial();
    }

    // 顺从原版：圆形边框
    @Override
    public boolean isRound() {
        return this.original != null ? this.original.isRound() : super.isRound();
    }

    // 顺从原版：次要节点（把之前的 if 判断删掉，直接用这个更彻底）
    @Override
    public boolean isSecondary() {
        return this.original != null ? this.original.isSecondary() : super.isSecondary();
    }

}
