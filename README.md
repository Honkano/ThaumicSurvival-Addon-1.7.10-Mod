# ThaumicSurvival Addon (神秘生存学) 1.7.10 Forge

---
<p align="center">
  <img src="ThaumicSurvivalMODLOGO.png?raw=true" alt="Thaumic Survival Logo" width="1000">
</p>

---
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

---
A Lost Chapter from the Thaumonomicon
《魔导手册》失落的一页

EN:
Long before the first block was ever placed, an ancient Thaumaturge known only as the "First Scholar" foresaw the end. Not of the world, but of a world—a final, brutal arena where the only law was survival. He recorded his findings in a forbidden chapter of the Thaumonomicon, a chapter so dangerous that it was torn from the book and scattered across the void.

ZH:
在第一个方块被放置之前，一位只被称作“初代学者”的古代神秘使预见到了终局。那不是世界的终结，而是一个世界的终结——一个最终极、最残酷的竞技场，在那里，唯一的法则就是生存。他将发现记录在《魔导手册》的禁忌章节中，这章内容太过危险，以至于被从书中撕下，散落在了虚空之中。

EN:
Those who found the fragments spoke of a ritual. The "Champions' Trial." When the ancient runes were activated, the sky would darken, and the earth itself would become a cage. Resources would be scarce. Health would no longer regenerate naturally; the only salvation lay in the golden apples, infused with raw, unstable magical energy—the Greedy Golden Apples, each tier offering greater power at a far more terrible cost in warp and instability. To consume one was to gamble with your own mind.

ZH:
找到碎片的人提到了一个仪式：“冠军试炼”。当古老的符文被激活，天空会变暗，大地本身会变成牢笼。资源会变得稀缺。生命不再自然恢复；唯一的救赎，寄托在那些灌注了原始、不稳定魔法能量的金苹果上——魔铸金苹果，每一阶都提供更强大的力量，但也伴随着更可怕的不稳定与扭曲代价。吃下它，就是拿自己的心智做赌注。

EN:
But the true prize was never survival. It was the Artifacts—weapons of myth, reforged in the fires of the Infusion Altar. The Dragon's Cleaver, a blade that could sever the bond between a soul and its armor. Caliburn, the golden sword of kings, said to choose its wielder not by bloodline, but by the weight of their sins. And the Thunderfall Axe, whose every strike summoned a storm that answered only to its master. These were not weapons. They were the keys to the Trial's end.

ZH:
但真正的奖赏从来不是生存。而是那些神器——在注魔祭坛的烈火中重铸的神话武器。巨龙之斧，一柄能斩断灵魂与盔甲之间联系的重刃。咖喱棒，王者之金剑，据说它选择主人不看血统，而看其罪孽的重量。还有雷霆之斧，每一次挥击都会召唤一场只回应其主人的风暴。这些不是武器，它们是终结试炼的钥匙。

EN:
The Trial has been played a thousand times across a thousand worlds. Champions have risen, and Champions have fallen, their heads consumed by the victor in a final, desperate act of survival. The arena always resets. The Artifacts always return to the Altar, waiting to be reforged. The cycle is eternal, and the Thaumonomicon chapter remains lost—until now.

ZH:
试炼已在千百个世界中上演了千百次。冠军崛起，冠军陨落，他们的头颅被胜者在最后绝望的求生之举中吞下。竞技场总会重置。神器总会回到祭坛，等待被再次重铸。这个循环是永恒的，而《魔导手册》的那一章也一直失落——直到现在。

EN:
A lone player, known only as Steve, was mining in the deep dark of a world long abandoned by its Champions. He found no diamonds, no ancient debris. Instead, his pickaxe struck something that hummed with a faint, lingering magic. It was a book. Not the Thaumonomicon itself, but a single, leather-bound chapter, its pages stained with the residue of a thousand desperate battles. The runes on the cover were barely legible, but one word pulsed with a dull, angry light: “CHAMPIONS.”

ZH:
一位只被称作史蒂夫的孤独玩家，在一个早已被冠军们遗弃的世界的幽暗深处挖矿。他没有找到钻石，也没有找到远古残骸。他的镐子敲到了某个东西，正散发着微弱、萦绕不散的魔法嗡鸣。那是一本书。不是《魔导手册》本身，而是单独的一章，皮革装订，书页上沾满了上千场绝望战斗的残留物。封面上的如尼文字几乎无法辨认，但有一个词正随着暗淡、愤怒的光芒脉动：“冠军”。

EN:
Steve opened the book. The first page contained a map—not of his world, but of an arena. A countdown had already begun. He could hear the distant, metallic sound of an Infusion Altar powering up, and the faint, hungry whispers of an ancient, waiting crowd.

He looked at the first recipe. It was for a simple iron anvil. The materials were listed: one iron block, three iron ingots.

But there was a footnote, written in a different hand, a hand that had won before.

“The Trial has already begun, Steve. The first artifact is waiting. And this time, the Champions are real.”

ZH:
史蒂夫翻开了书。第一页是一张地图——不是他这个世界的地图，而是一个竞技场的地图。倒计时已经开始了。他能听到远处注魔祭坛启动时金属般的嗡鸣，以及一个古老、等待已久的群体发出的微弱而饥渴的低语。

他看向第一个配方。那是一个简易铁砧。材料清单上写着：一个铁块，三个铁锭。

但旁边有一行脚注，是用另一种笔迹写的——一种曾经赢过的笔迹。

“试炼已经开始了，史蒂夫。第一件神器正在等待。而这一次，冠军们是真的。”
--- 