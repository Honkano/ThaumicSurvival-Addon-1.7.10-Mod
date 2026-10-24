# ThaumicSurvival Addon (神秘生存学)

[![Minecraft](https://img.shields.io/badge/Minecraft-1.7.10-blue.svg)](https://www.minecraft.net/)
[![Thaumcraft](https://img.shields.io/badge/Thaumcraft-4.2.3.5-purple.svg)](https://www.curseforge.com/minecraft/mc-mods/thaumcraft)
[![License](https://img.shields.io/badge/License-MIT-green.svg)](LICENSE)
[![Author](https://img.shields.io/badge/Author-Kongtai%20(空太)-orange.svg)]()

> **[EN]** A 1.7.10 Thaumcraft 4 PVP-focused addon inspired by Survival Games. Features simplified crafts & artifact crafting. Includes a powerful, easy-to-use public API for developers.
> 
> **[ZH]** 一个偏 PVP 向的 1.7.10 神秘时代 4 附属。灵感源于极限生存玩法，包含大量简易合成与神器合成，并内置了一套强大且易用的公开 API。

---

## 👋 About the Author (关于作者)

**Author:** 空太 (Kongtai / Honkano)
**Personal Note:** 
While I have maintained over a dozen different Minecraft mods over the years, **ThaumicSurvival** is my very first true original creation from scratch. It is my "firstborn" in the modding world. I poured my heart into the API design and the gameplay mechanics to make it as smooth and enjoyable as possible.

*（个人寄语：虽然我这些年维护过十几个不同的 Minecraft 模组，但 **神秘生存学** 是我第一个真正意义上从零开始、完全属于自己的原创作品。它是我的“亲儿子”。我把关于代码架构和玩法设计的心血都倾注在了里面，希望它能给你带来极佳的游戏体验。）*

---

## ✨ Features (核心特性)

* **⚔️ PVP & Survival Games Focused (PVP 与极限生存导向)**
  * Fast-paced crafting recipes designed for PVP and mini-game environments.
  * 快节奏的合成配方，专为 PVP 和小游戏环境设计。
  
* **🛠️ Simplified Crafts (极限白嫖/简易合成)**
  * Includes time-saving recipes like crafting Anvils with only 1 Iron Block and 3 Ingots, or creating Obsidian with Water & Lava buckets in the Arcane Workbench!
  * 包含省时省力的配方，例如仅需 1 个铁块和 3 个铁锭即可合成铁砧，甚至可以在奥术工作台里用水桶和岩浆桶直接“物理反应”出黑曜石！

* **🧪 Advanced Artifacts (神器与进阶合成)**
  * Features multi-tiered "Greedy Golden Apples" infused with dangerous powers. The higher the tier, the more research points you get, but the higher the warp!
  * 包含多阶“魔铸金苹果”，灌注了危险的力量。等级越高，获得的研究点越多，但伴随的扭曲值也越致命！

* **🔧 Developer-Friendly API (极度友好的开发者 API)**
  * Built-in utility classes (`TCItems`, `TCBlocks`, `TCAspects`) for easy modding. No more guessing obscure meta values!
  * 内置 `TCItems`、`TCBlocks`、`TCAspects` 等词典工具类，写配方再也不用去翻源码查 meta 值了！
  * Custom `MirrorResearchItem` system to mirror vanilla research across categories without visual bugs.
  * 自研的“镜像研究”系统，完美解决跨分类画线的视觉 Bug，科技树排版强迫症福音。

---

## 📥 Installation (安装说明)

1. Install **Minecraft Forge 1.7.10** and **Thaumcraft 4**.
   *(安装 Minecraft Forge 1.7.10 和 神秘时代 4)*
2. Drop the `ThaumicSurvival-x.x.x.jar` into your `mods` folder.
   *(将本模组的 jar 包丢进 mods 文件夹)*
3. Enjoy! (启动游戏，开始你的极限生存之旅！)

---

## 👨‍💻 For Developers (开发者指南)

We have packaged a **standalone API (JAR)** for this mod. If you are building an addon, you **do not need to depend on the entire ThaumicSurvival mod**. You can simply import our API JAR to easily add your own items, blocks, recipes, and research!

*(我们为本模组打包了一个**独立的 API 本体 (JAR)**。如果你正在开发附属模组，你**完全不需要依赖整个神秘生存学模组**，只需直接引入我们的 API 包，即可轻松添加你自己的物品、方块、配方与研究！)*

**Why use our API? (为什么使用我们的 API？)**
We have heavily simplified the original Thaumcraft 4 API to make your life easier. No more digging through source code or guessing obscure meta values! Plus, we provide custom particle, sound, and animation effects that you can utilize in your own creations.

*(我们将神秘时代 4 原生的 API 进行了深度的“简洁化”封装，让你的开发如丝般顺滑。再也不用去翻源码猜 Meta 值了！此外，我们还内置了自定义的粒子、音效与动画效果，供你的附属模组调用！)*

**The Ultimate API Tools (终极 API 工具包):**
*   📦 **`TCItems` / `TCBlocks`**: Complete dictionaries for Thaumcraft items and blocks. Write `TCItems.thaumiumIngot()` instead of `new ItemStack(ConfigItems.itemResource, 1, 2)`!
    *(物品与方块大词典。用方法名直呼其名，告别反人类的 meta 值！)*
*   ✨ **`TCAspects`**: Aspect dictionary and smart constructors. `TCAspects.of(FIRE, 16, MAGIC, 8)` creates complex AspectLists in one line.
    *(要素大词典与智能构造器，一行代码生成复杂源质配方。)*
*   🧪 **`TCRecipes`**: Safe and clean recipe wrappers. Easily register Infusion, Crucible, and Arcane recipes without missing a parameter.
    *(配方注册封装。注魔、坩埚、奥术工作台，参数一目了然，安全防崩。)*
*   📖 **`TCResearchHelper`**: The ultimate research helper. Includes `textPage()` for UI texts, and the powerful `mirror()` function to mirror vanilla research across categories seamlessly!
    *(究极研究辅助工具。自带文本页生成，以及强大的“镜像研究”黑科技，完美解决跨分类画线问题！)*
*   🏷️ **`TCEntities` / `TCOreDict`**: Safe string constants for entity triggers and OreDictionary names. No more typos causing silent failures.
    *(实体触发与矿物词典常量表，彻底告别拼写错误导致的配方失效。)*
*   🔊 **`TCSounds`**: A complete dictionary of all 66 Thaumcraft sound events. Play custom sounds easily on both Client and Server.
    *(神秘时代音效大词典。收录全部 66 个音效事件，两端可用，轻松为你的武器和机器添加打击感！)*
*   🌌 **`TCParticles`**: A powerful particle engine wrapper. Summon sparks, beams, lightning, wisps, and custom animations with simple method calls.
    *(粒子特效大词典。封装了所有的光束、闪电、灵光与符文粒子，只需一行代码，为你的 PVP 神器注入灵魂！)*

**The codebase is strictly categorized:**
*   `api/`: The ultimate tool classes mentioned above.
*   `thaumcraftwork/recipes/`: All recipes, including dynamic multi-tier recipes.
*   `thaumcraftwork/research/`: Research nodes and dynamic page assembly.
*(代码结构经过严格分类，配合极其详尽的中文注释，对新手极度友好，欢迎一起来写神秘时代附属！)*

---

## 📜 Credits & License (致谢与开源协议)

* **Thaumcraft** by *Azanor*.
* **GTNH ExampleMod Template** by *GTNewHorizons*.
* **Mod Author:** **[空太 / Honkano](https://github.com/Honkano)**
* Licensed under the **MIT License**. See [LICENSE](LICENSE) for details.

---
*“魔法不是用来种田的，是用来在奥术工作台里打架的。” —— 空太*