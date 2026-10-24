package com.kongtai.thaumicsurvival.thaumcraftwork.recipes;

/**
 * ============================================================
 * 【普通注魔配方管理类】
 * ============================================================
 * 这个类专门放“普通注魔配方”（单级、静态）。
 * 包括：
 * InfusionRecipe 普通注魔
 * InfusionEnchantmentRecipe 注魔附魔
 * CrucibleRecipe 坩埚
 * IArcaneRecipe 奥术（有序/无序通用）
 * ThaumcraftApi 总 API
 * Aspect / AspectList 源质
 * ConfigItems 神秘时代原版物品
 * Blocks / Items / ItemStack 原版物品
 * Enchantment 原版附魔
 * ============================================================
 */
public class InfusionRecipes {

    // ============================================================
    // 【静态字段区】
    // 普通注魔配方的静态字段写在这里。
    // 目前为空。
    // ============================================================
    // public static InfusionRecipe recipeXxx;

    // ============================================================
    // 【总入口】
    // 在 CommonProxy.init() 里调用 InfusionRecipes.init()
    // ============================================================
    public static void init() {
        // ==========================================
        // 普通注魔配方（单级、静态）写在这里
        // 目前没有，留空
        // ==========================================
    }

    // ============================================================
    // 【下面以后加配方时照抄，改参数】
    // ============================================================
    //
    // 例：一个普通注魔配方
    //
    // public static InfusionRecipe recipeXxx;
    //
    // 在 init() 里：
    // AspectList cost = TCAspects.of(TCAspects.MAGIC, 16, TCAspects.AURA, 8);
    //
    // recipeXxx = TCRecipes.infusion(
    // "你的研究KEY",
    // TCItems.diamond(), // 输出
    // 2, // 不稳定度
    // cost, // 源质
    // TCItems.ironIngot(), // 中心物品
    // new ItemStack[] {
    // TCItems.salisMundus(),
    // TCItems.salisMundus(),
    // TCItems.diamond(),
    // TCItems.diamond()
    // });
    //
    // ============================================================

    // ============================================================
    // 【普通注魔配方 · 最详细模板】
    // ============================================================
    // 下面每一条都是“以后写普通注魔配方时要注意的坑”，
    // 不是现在要跑的逻辑。写新配方时翻到这里照抄。
    // ============================================================

    // ------------------------------------------------------------
    // 【知识点 1】输出可以是 ItemStack，也可以是 Object[]（给输出打 NBT）
    // ------------------------------------------------------------
    //
    // TCRecipes.infusion / infusionWithNBT 的 output 参数类型是 Object。
    // 源码内部检查：result 必须是 ItemStack 或 Object[]，否则返回 null。
    //
    // 写法 A：普通 ItemStack
    // TCItems.diamond()
    //
    // 写法 B：Object[]，给输出物品打 NBT
    // new Object[] {
    // "thaumicsurvival:anvil_tier", // NBT key（字符串）
    // new NBTTagByte((byte) 1) // NBT 值（NBTBase 子类）
    // }
    //
    // 写法 B 的用途：
    // 你想让注魔输出带自定义 NBT，但不想写自定义 InfusionRecipe 子类时，
    // 直接用 Object[] 就行。祭坛合成时会把这段 NBT 写到输出物品上。
    //
    // 注意：
    // Object[] 只能写“一组 key-value”，多个 NBT 要嵌套在 NBTTagCompound 里，
    // 不能直接写多个 key-value 对。复杂 NBT 还是建议写自定义子类。

    // ------------------------------------------------------------
    // 【知识点 2】默认 matches() 做四件事，缺一不可
    // ------------------------------------------------------------
    //
    // 祭坛判断“这张配方匹不匹配”时，走的是 InfusionRecipe.matches()，
    // 默认实现依次检查：
    //
    // ① 研究检查：
    // if (research.length() > 0 &&
    // !isResearchComplete(player, research)) return false;
    // → 玩家没解锁研究，配方不认。
    //
    // ② 中心物品检查：
    // areItemStacksEqual(central, recipeInput, true)
    // → 中心物品必须和构造器里的 recipeInput 相等（fuzzy=true，矿辞兼容）。
    //
    // ③ 材料检查：
    // 每个 comp 都要在输入列表里找到，用 areItemStacksEqual(slot, comp, true)。
    // → 少一个材料，不匹配。
    //
    // ④ 多余材料检查：
    // ii.size() == 0
    // → 输入里多放一个东西，不匹配。
    //
    // 第 ④ 条是玩家最容易踩的坑：
    // 基座上多放一个东西，配方就不认。研究书文本里最好提一句
    // “基座上只能放对应材料，不能多放”。

    // ------------------------------------------------------------
    // 【知识点 3】研究 key 必须和研究节点 key 完全一致
    // ------------------------------------------------------------
    //
    // 配方第一个参数 "你的研究KEY" 必须和研究节点里
    // new ResearchItem("你的研究KEY", ...) 的 key 一字不差。
    //
    // 不一致的后果：
    // 玩家研究解锁了，配方也不认（matches 第一步就 return false），
    // 祭坛一直显示“无匹配配方”，玩家以为配方坏了。
    //
    // 例外：
    // 传 ""（空字符串）可以跳过研究检查，但这样配方对所有人可用，
    // 一般不建议，除非你真的想让所有人都能合。

    // ------------------------------------------------------------
    // 【知识点 4】材料不能传矿辞字符串
    // ------------------------------------------------------------
    //
    // TCRecipes.infusion 的 components 参数类型是 ItemStack[]，
    // 不是 Object[]。所以：
    //
    // TCItems.ironIngot() ✅ 合法
    // TCItems.nuggetIron() ✅ 合法
    // "ingotIron" ❌ 编译不过
    //
    // 但运行时 matches 用的是 areItemStacksEqual(..., true)，
    // fuzzy=true 会检查矿辞 ID，所以：
    // TCItems.ironIngot() 和
    // new ItemStack(某个模组的铁锭)（共享 "ingotIron" 矿辞）
    // 会互相匹配。
    //
    // 结论：
    // 写法上只能传 ItemStack，矿辞兼容是运行时 fuzzy 自动干的。

    // ------------------------------------------------------------
    // 【知识点 5】instability 的实际效果和推荐范围
    // ------------------------------------------------------------
    //
    // instability 不是“显示数字”，它影响注魔过程中的事件：
    //
    // 0~5 基本稳定，偶尔小事件
    // 5~15 偶尔咒波、掉落物
    // 15~40 频繁咒波、可能爆炸
    // 40+ 几乎必炸
    //
    // 原版配方一般 0~10，高难度 20~50。
    // 你铁砧 2~10、苹果 1~25，都在合理范围。
    //
    // 调 instability 时问自己：
    // 这个配方是“日常用品”还是“危险实验”？
    // 日常 → 0~10；危险 → 20~50。

    // ------------------------------------------------------------
    // 【知识点 6】components 建议 4 的倍数
    // ------------------------------------------------------------
    //
    // components 是 ItemStack[]，长度任意，但祭坛实际只有 4 个基座位置。
    //
    // 4 个材料：每个基座放 1 个，最直观
    // 8 个材料：每个基座放 2 个，也能摆
    // 6、12：对称摆法，原版常用
    // 奇数个（如 3、5）：玩家摆起来别扭，不建议
    //
    // 建议：写 4、6、8、12，方便玩家对称摆放。

    // ------------------------------------------------------------
    // 【完整示例】一个普通注魔配方，所有知识点都用上
    // ------------------------------------------------------------
    //
    // 1. 类顶部加字段：
    //
    // public static InfusionRecipe recipeMagicDiamond;
    //
    // 2. init() 里写：
    //
    // // 源质“菜单”：16 魔法 + 8 灵气 + 8 感知
    // AspectList cost = TCAspects.of(
    // TCAspects.MAGIC, 16,
    // TCAspects.AURA, 8,
    // TCAspects.SENSES, 8);
    //
    // // 基座材料：4 个，方便对称摆
    // ItemStack[] components = new ItemStack[] {
    // TCItems.diamond(),
    // TCItems.diamond(),
    // TCItems.salisMundus(), // 世界盐
    // TCItems.salisMundus()
    // };
    //
    // // 注册配方
    // recipeMagicDiamond = TCRecipes.infusion(
    // "THAUMICSURVIVAL_MAGIC_DIAMOND", // ① 研究 key（和节点一致）
    // TCItems.diamond(), // ② 输出（ItemStack 或 Object[]）
    // 5, // ③ 不稳定度（0~10 日常，20~50 危险）
    // cost, // ④ 源质菜单（不是消耗，是清单）
    // TCItems.ironIngot(), // ⑤ 中心物品（放祭坛中央）
    // components); // ⑥ 基座材料（建议 4/6/8/12）
    //
    // 3. 研究页里引用：
    //
    // new ResearchPage(InfusionRecipes.recipeMagicDiamond)
    //
    // 4. 检查清单：
    // □ 研究 key 和节点 key 一致
    // □ 输出是 ItemStack 或 Object[]
    // □ 中心物品是 ItemStack
    // □ 材料是 ItemStack[]（不能传字符串）
    // □ 材料数量是 4/6/8/12
    // □ instability 在合理范围
    // □ 玩家不能多放材料（matches 第 ④ 条）
    //
    // ------------------------------------------------------------

}
