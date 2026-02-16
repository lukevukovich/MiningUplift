# <img src="src/main/resources/assets/uplift/textures/items/gem_dust.png" width="40" style="image-rendering: pixelated;"> Mining Uplift - Complete Mod Wiki

> **Mod ID:** `uplift`

Mining Uplift is a Minecraft mod that adds **13 new ores and minerals**, full tool and armor sets with unique set bonus effects, a custom **Smelter** furnace, a **Gem Crusher** block, throwable **Mine Bombs**, a new **Cave Dimension** accessed through a **Cave Portal**, and a hostile **Stoneman** entity. This wiki documents every feature, item, block, recipe, and mechanic added by the mod.

---

## Table of Contents

- [Installation](#installation)
- [Development Setup](#development-setup)
- [Backlog](#backlog)
- [New Ores & Materials](#new-ores--materials)
  - [Ruby](#ruby)
  - [Sapphire](#sapphire)
  - [Amethyst](#amethyst)
  - [Chromium](#chromium)
  - [Obsidian (Shards)](#obsidian-shards)
  - [Burning Diamond](#burning-diamond)
  - [Platinum](#platinum)
  - [Uranium](#uranium)
  - [Tasmanite](#tasmanite)
  - [Bloodstone](#bloodstone)
  - [Ender](#ender)
  - [Opal](#opal)
  - [Rose Gold](#rose-gold)
- [Special Items](#special-items)
  - [Gem Dust & Gemstone](#gem-dust--gemstone)
  - [Igniter](#igniter)
  - [Mine Bomb](#mine-bomb)
  - [Uranium Bomb](#uranium-bomb)
  - [Burning Diamond Dust](#burning-diamond-dust)
  - [Stone Meal & ](#stone-meal--stone-soup)
  - [Rose Golden Apple](#rose-golden-apple)
  - [Stoneman Spawn Egg](#stoneman-spawn-egg)
- [Tool & Weapon Stats](#tool--weapon-stats)
- [Armor Stats & Set Bonuses](#armor-stats--set-bonuses)
- [Armor Effect Toggle](#armor-effect-toggle)
- [Special Blocks](#special-blocks)
  - [Gem Crusher](#gem-crusher)
  - [Smelter](#smelter)
  - [Igniter Fire](#igniter-fire)
  - [Gem Torches](#gem-torches)
- [The Cave Dimension](#the-cave-dimension)
  - [Cave Portal](#cave-portal)
- [Ore Generation](#ore-generation)
  - [Overworld Ores](#overworld-ores)
  - [Nether Ores](#nether-ores)
  - [End Ores](#end-ores)
  - [Cave Dimension Ores](#cave-dimension-ores)
- [Entities](#entities)
  - [Stoneman](#stoneman)
- [Effects](#effects)
  - [Flight](#flight)
  - [Luminous](#luminous)
- [Crafting](#crafting)
  - [Utility & Special Item Recipes](#utility--special-item-recipes)
  - [Ruby Recipes](#ruby-recipes)
  - [Sapphire Recipes](#sapphire-recipes)
  - [Amethyst Recipes](#amethyst-recipes)
  - [Chromium Recipes](#chromium-recipes)
  - [Obsidian Recipes](#obsidian-recipes)
  - [Burning Diamond Recipes](#burning-diamond-recipes)
  - [Platinum Recipes](#platinum-recipes)
  - [Uranium Recipes](#uranium-recipes)
  - [Bloodstone Recipes](#bloodstone-recipes)
  - [Ender Recipes](#ender-recipes)
  - [Tasmanite Recipes](#tasmanite-recipes)
  - [Opal Recipes](#opal-recipes)
  - [Rose Gold Recipes](#rose-gold-recipes)
- [Smelting](#smelting)
  - [Furnace Recipes](#furnace-recipes)
  - [Smelter Recipes](#smelter-recipes)
  - [Fuel Values](#fuel-values)

---

## Installation

### Prerequisites:
  - Java JRE 8
  - Minecraft 1.15.2
  - Forge Mod Loader for Minecraft 1.15.2

### To install the mod for normal use (not development):
  1. Ensure Minecraft version 1.15.2 exists for Forge Mod Loader to use.
  1. Install Forge Mod Loader for Minecraft 1.15.2 and launch Minecraft once with that profile so the `mods/` folder is created.
  2. Build the mod JAR (or download a release) and place the JAR file into the `mods/` folder of your Forge-enabled Minecraft installation.
  3. Start Minecraft with the Forge profile - the mod should load automatically.

> Note: This is a regular Forge mod distribution — you do not need the JDK to run the mod in a normal Minecraft client; only the Java runtime + Forge + Minecraft are required for players.

---

## Development Setup

### Dev Prerequisites

- Java JRE 8
- Java JDK 8
- Repository cloned locally
- Global Gradle is optional; the repository includes a Gradle wrapper

### Build & Run Locally

Use the Gradle wrapper from the project root on Windows (PowerShell or CMD):

#### Build JAR
```
.\gradlew.bat clean reobfJar
```

The assembled mod JAR will appear in `build/libs/` after `build`.

#### Run Client
```
.\gradlew.bat runClient
```

The `runClient` task starts a Minecraft client with the mod loaded for development and testing.

---

## Backlog

## `MU-1` Calibrate Mob Spawning in Cave Dimension

**Type:** Improvement  
**Priority:** High

### Description

Mob spawning in the Cave Dimension appears unbalanced. Excessive spawning of **Stoneman** mobs and gemstone lighting may be affecting spawn logic.

### Tasks

- Review gemstone lighting impact (ore generation)
- Investigate ignoring light levels for mob spawning
- Evaluate Stoneman spawn rates (possibly too high)

## `MU-2` Replicate Cave Dimension Teleportation Crash

**Type:** Bug  
**Priority:** High

### Description

Crash occurs when teleporting from Overworld to Cave Dimension under specific conditions.

### Reproduction Notes

- Low health
- Teleporter used multiple times
- Overworld → Cave Dimension transition

### Tasks

- Attempt to reliably reproduce crash
- Add logging around teleport handler
- Validate player state before dimension transfer

## `MU-3` Fix Portal Linking

**Type:** Bug  
**Priority:** Medium

### Description

Portal linking is delayed by one step. New portals created in the Cave Dimension link correctly, but the system appears one portal behind.

### Expected Behavior

Creating a new portal in the Cave Dimension should link immediately and correctly to the Overworld counterpart.

### Tasks

- Trace portal registry mapping
- Inspect stored portal coordinates
- Verify dimension pairing logic

## `MU-4` Fix Fire Block Border Rendering

**Type:** Bug  
**Priority:** Low

### Description

Fire block border rendering appears visually incorrect.

### Tasks

- Review block model JSON
- Check render layer configuration
- Test in multiple lighting conditions

## `MU-5` Refill Smelter Fuel Bar with Igniter

**Type:** Feature  
**Priority:** Medium

### Description

Right-clicking the smelter with an Igniter item should refill the fuel bar.

### Acceptance Criteria

- Fuel refills only up to maximum capacity
- Fuel does not exceed remaining igniter fuel
- Proper sync between client and server
- Visual fuel bar updates correctly

## `MU-6` Playtesting and Balancing

**Type:** Task  
**Priority:** Medium

### Description

Perform play-testing sessions to evaluate the new features and balance adjustments.

### Tasks

- Stress armor toggle functionality
- Review armor and tool balance
- Review ore distribution and rarity
- Evalulate fuel efficiency and smelter mechanics

---

## New Ores & Materials

### Ruby

<img src="src/main/resources/assets/uplift/textures/blocks/ruby_ore.png" width="40" style="image-rendering: pixelated;"> <img src="src/main/resources/assets/uplift/textures/blocks/ruby_block.png" width="40" style="image-rendering: pixelated;"> <img src="src/main/resources/assets/uplift/textures/items/ruby.png" width="40" style="image-rendering: pixelated;">

A common red gemstone found in the Overworld and Cave Dimension. Ruby is the most accessible new gem and serves as a good early-game material upgrade.

| Property            | Value                      |
| ------------------- | -------------------------- |
| Ore Hardness        | 3.0                        |
| Ore Harvest Level   | 1 (Stone)                  |
| Drops XP            | Yes                        |
| Block Hardness      | 5.0 / Blast Resistance 6.0 |
| Block Harvest Level | 2 (Iron)                   |

---

### Sapphire

<img src="src/main/resources/assets/uplift/textures/blocks/sapphire_ore.png" width="40" style="image-rendering: pixelated;"> <img src="src/main/resources/assets/uplift/textures/blocks/sapphire_block.png" width="40" style="image-rendering: pixelated;"> <img src="src/main/resources/assets/uplift/textures/items/sapphire.png" width="40" style="image-rendering: pixelated;">

A blue gemstone found deeper underground. Sapphire tools and armor are a step up from Ruby.

| Property            | Value                      |
| ------------------- | -------------------------- |
| Ore Hardness        | 3.0                        |
| Ore Harvest Level   | 2 (Iron)                   |
| Drops XP            | Yes                        |
| Block Hardness      | 5.0 / Blast Resistance 6.0 |
| Block Harvest Level | 2 (Iron)                   |

---

### Amethyst

<img src="src/main/resources/assets/uplift/textures/blocks/amethyst_ore.png" width="40" style="image-rendering: pixelated;"> <img src="src/main/resources/assets/uplift/textures/blocks/amethyst_block.png" width="40" style="image-rendering: pixelated;"> <img src="src/main/resources/assets/uplift/textures/items/amethyst.png" width="40" style="image-rendering: pixelated;">

A vibrant gemstone with moderate stats. Amethyst armor grants a Speed effect when worn as a full set.

| Property            | Value                      |
| ------------------- | -------------------------- |
| Ore Hardness        | 3.0                        |
| Ore Harvest Level   | 2 (Iron)                   |
| Drops XP            | Yes                        |
| Block Hardness      | 5.0 / Blast Resistance 6.0 |
| Block Harvest Level | 2 (Iron)                   |

---

### Chromium

<img src="src/main/resources/assets/uplift/textures/blocks/chrome_ore.png" width="40" style="image-rendering: pixelated;"> <img src="src/main/resources/assets/uplift/textures/blocks/chrome_block.png" width="40" style="image-rendering: pixelated;"> <img src="src/main/resources/assets/uplift/textures/items/chrome_ingot.png" width="40" style="image-rendering: pixelated;"> <img src="src/main/resources/assets/uplift/textures/items/chrome_nugget.png" width="40" style="image-rendering: pixelated;">

A metallic ore smelted into ingots. Chromium has stats similar to Sapphire and grants **Luminous** (a custom light-emitting effect) when worn as a full armor set.

| Property          | Value                      |
| ----------------- | -------------------------- |
| Ore Hardness      | 3.0                        |
| Ore Harvest Level | 2 (Iron)                   |
| Block Hardness    | 5.0 / Blast Resistance 6.0 |
| Block Light Level | 10                         |

---

### Obsidian (Shards)

<img src="wiki/wiki_textures/obsidian.png" width="40" style="image-rendering: pixelated;"> <img src="src/main/resources/assets/uplift/textures/blocks/obsidian_block.png" width="40" style="image-rendering: pixelated;"> <img src="src/main/resources/assets/uplift/textures/items/obsidian_shard.png" width="40" style="image-rendering: pixelated;">

Obsidian Shards are obtained by processing vanilla Obsidian in the **Smelter**. They can be crafted into extremely durable tools and armor. The Block of Obsidian is incredibly blast-resistant (1200).

| Property            | Value                          |
| ------------------- | ------------------------------ |
| Block Hardness      | 50.0 / Blast Resistance 1200.0 |
| Block Harvest Level | 2 (Iron)                       |

---

### Burning Diamond

<img src="src/main/resources/assets/uplift/textures/blocks/burning_diamond_ore.png" width="40" style="image-rendering: pixelated;"> <img src="src/main/resources/assets/uplift/textures/blocks/burning_diamond_block.png" width="40" style="image-rendering: pixelated;"> <img src="src/main/resources/assets/uplift/textures/items/burning_diamond.png" width="40" style="image-rendering: pixelated;"> <img src="src/main/resources/assets/uplift/textures/items/burning_dust.png" width="40" style="image-rendering: pixelated;">

An extremely rare, fiery gem. Burning Diamond ore emits light (level 5), and the block emits even more (level 10). Burning Diamond is also an incredibly powerful **furnace fuel** that smelts **200 items** per unit (25x coal). Burning Diamond Dust is created by processing a vanilla Diamond in the Smelter.

| Property          | Value                    |
| ----------------- | ------------------------ |
| Ore Hardness      | 3.0                      |
| Ore Harvest Level | 3 (Diamond)              |
| Ore Light Level   | 5                        |
| Drops XP          | Yes                      |
| Block Light Level | 10                       |
| Fuel Value        | 40,000 ticks (200 items) |

---

### Platinum

<img src="src/main/resources/assets/uplift/textures/blocks/platinum_ore.png" width="40" style="image-rendering: pixelated;"> <img src="src/main/resources/assets/uplift/textures/blocks/platinum_block.png" width="40" style="image-rendering: pixelated;"> <img src="src/main/resources/assets/uplift/textures/items/platinum_ingot.png" width="40" style="image-rendering: pixelated;"> <img src="src/main/resources/assets/uplift/textures/items/platinum_nugget.png" width="40" style="image-rendering: pixelated;">

The pinnacle of Overworld metals. Platinum is extraordinarily rare and yields the strongest non-specialty tools and armor. Requires a harvest level of 4 (Burning Diamond-tier or higher pickaxe) to mine its ore.

| Property          | Value                      |
| ----------------- | -------------------------- |
| Ore Hardness      | 3.0                        |
| Ore Harvest Level | 4 (Burning Diamond)        |
| Block Hardness    | 5.0 / Blast Resistance 6.0 |

---

### Uranium

<img src="src/main/resources/assets/uplift/textures/blocks/uranium_ore.png" width="40" style="image-rendering: pixelated;"> <img src="src/main/resources/assets/uplift/textures/blocks/uranium_block.png" width="40" style="image-rendering: pixelated;"> <img src="src/main/resources/assets/uplift/textures/items/uranium_ingot.png" width="40" style="image-rendering: pixelated;"> <img src="src/main/resources/assets/uplift/textures/items/uranium_nugget.png" width="40" style="image-rendering: pixelated;">

A radioactive metal found high in the world (Y 46-256). Uranium tools and the Uranium Bomb make it a powerful mid-game material.

| Property          | Value                      |
| ----------------- | -------------------------- |
| Ore Hardness      | 3.0                        |
| Ore Harvest Level | 3 (Diamond)                |
| Block Hardness    | 5.0 / Blast Resistance 6.0 |

---

### Tasmanite

<img src="src/main/resources/assets/uplift/textures/blocks/tasmanite_ore.png" width="40" style="image-rendering: pixelated;"> <img src="src/main/resources/assets/uplift/textures/blocks/tasmanite_block.png" width="40" style="image-rendering: pixelated;"> <img src="src/main/resources/assets/uplift/textures/items/tasmanite.png" width="40" style="image-rendering: pixelated;">

A luminous mineral found throughout the Overworld. Tasmanite is primarily a fuel source (smelts **12 items**, 1.5x coal) and a crafting component. The Block of Tasmanite emits maximum light (level 15).

| Property          | Value                  |
| ----------------- | ---------------------- |
| Ore Hardness      | 3.0                    |
| Ore Harvest Level | 2 (Iron)               |
| Drops XP          | Yes                    |
| Block Light Level | 15                     |
| Fuel Value        | 2,400 ticks (12 items) |

---

### Bloodstone

<img src="src/main/resources/assets/uplift/textures/blocks/bloodstone_ore.png" width="40" style="image-rendering: pixelated;"> <img src="src/main/resources/assets/uplift/textures/blocks/bloodstone_block.png" width="40" style="image-rendering: pixelated;"> <img src="src/main/resources/assets/uplift/textures/items/bloodstone.png" width="40" style="image-rendering: pixelated;">

A dark gem found only in the **Nether**. Bloodstone armor provides constant Instant Health but comes with severe debuffs - a balanced risk/reward armor set.

| Property          | Value               |
| ----------------- | ------------------- |
| Ore Hardness      | 3.0                 |
| Ore Harvest Level | 2 (Iron)            |
| Drops XP          | Yes                 |
| Found In          | Nether (Netherrack) |

---

### Ender

<img src="src/main/resources/assets/uplift/textures/blocks/ender_ore.png" width="40" style="image-rendering: pixelated;"> <img src="src/main/resources/assets/uplift/textures/blocks/ender_block.png" width="40" style="image-rendering: pixelated;"> <img src="src/main/resources/assets/uplift/textures/items/ender_gem.png" width="40" style="image-rendering: pixelated;">

A powerful gem found only in **The End**. Ender tools have the highest durability in the mod (2,450 uses), and the full armor set grants **Flight**.
Ender gems can be obtained by mining **Ender Ore** in The End or as a rare drop from **Endermen**.

| Property          | Value               |
| ----------------- | ------------------- |
| Ore Hardness      | 3.0                 |
| Ore Harvest Level | 3 (Diamond)         |
| Drops XP          | Yes                 |
| Found In          | The End (End Stone) |

---

### Opal

<img src="src/main/resources/assets/uplift/textures/blocks/opal_ore.png" width="40" style="image-rendering: pixelated;"> <img src="src/main/resources/assets/uplift/textures/blocks/opal_block.png" width="40" style="image-rendering: pixelated;"> <img src="src/main/resources/assets/uplift/textures/items/opal.png" width="40" style="image-rendering: pixelated;">

A rare gem found only underground and in the Cave Dimension. Opal currently has no tool or armor set but is highly valuable as **Gem Crusher** input (9 Gem Dust per Opal).

| Property          | Value       |
| ----------------- | ----------- |
| Ore Hardness      | 3.0         |
| Ore Harvest Level | 3 (Diamond) |
| Drops XP          | Yes         |

---

### Rose Gold

<img src="src/main/resources/assets/uplift/textures/blocks/rose_gold_ore.png" width="40" style="image-rendering: pixelated;"> <img src="src/main/resources/assets/uplift/textures/blocks/rose_gold_block.png" width="40" style="image-rendering: pixelated;"> <img src="src/main/resources/assets/uplift/textures/items/rose_gold_ingot.png" width="40" style="image-rendering: pixelated;"> <img src="src/main/resources/assets/uplift/textures/items/rose_gold_nugget.png" width="40" style="image-rendering: pixelated;">

A lustrous metal found in the Cave Dimension. Rose Gold is used to craft two unique legendary weapons and the powerful Rose Golden Apple. Its weapons have very low durability (100) but extremely high damage.

| Property          | Value                      |
| ----------------- | -------------------------- |
| Ore Hardness      | 3.0                        |
| Ore Harvest Level | 2 (Iron)                   |
| Block Hardness    | 5.0 / Blast Resistance 6.0 |

---

## Special Items

### Gem Dust & Gemstone

<img src="src/main/resources/assets/uplift/textures/items/gem_dust.png" width="32" style="image-rendering: pixelated;"> **Gem Dust** - A fine powder obtained by crushing gems in the **Gem Crusher**. 9 Gem Dust can be crafted into a **Gemstone** block, which is used to build **Cave Portal** frames.

<img src="src/main/resources/assets/uplift/textures/blocks/gemstone.png" width="32" style="image-rendering: pixelated;"> **Gemstone** - A glowing block (similar to Glowstone) crafted from 9 Gem Dust. Acts as the portal frame material for the Cave Portal.

---

### Igniter

<img src="src/main/resources/assets/uplift/textures/items/igniter.png" width="40" style="image-rendering: pixelated;">

A special tool crafted from a Burning Diamond and Flint & Steel. It has **200 durability** and serves three special purposes:

| Action                 | Durability Cost |
| ---------------------- | --------------- |
| Place Igniter Fire     | 1               |
| Light Campfire         | 1               |
| Light an Unlit Smelter | 50              |
| Activate a Cave Portal | 100             |

---

### Mine Bomb

<img src="src/main/resources/assets/uplift/textures/items/mine_bomb.png" width="40" style="image-rendering: pixelated;">

A throwable explosive (max stack: 4). On impact, it creates a column of **power-4** explosions from the impact point all the way down to **Y=0 (bedrock)**. Ideal for strip-mining entire columns.

---

### Uranium Bomb

<img src="src/main/resources/assets/uplift/textures/items/uranium_bomb.png" width="40" style="image-rendering: pixelated;">

A devastating throwable explosive (max stack: 4). On impact, it creates **power-20** explosions in a 5-block deep column. Far more destructive than the Mine Bomb but limited in vertical range.

---

### Burning Diamond Dust

<img src="src/main/resources/assets/uplift/textures/items/burning_dust.png" width="40" style="image-rendering: pixelated;">

Obtained by processing a vanilla Diamond in the Smelter. Four Burning Diamond Dust can be crafted into a Burning Diamond.

---

### Stone Meal & Stone Soup

<img src="src/main/resources/assets/uplift/textures/items/stone_meal.png" width="32" style="image-rendering: pixelated;"> **Stone Meal** - Dropped by the Stoneman entity (0-1 per kill). Used to craft Stone Soup.

<img src="src/main/resources/assets/uplift/textures/items/stone_soup.png" width="32" style="image-rendering: pixelated;"> **Stone Soup** - A food item restoring **6 hunger** and **0.8 saturation**. Returns a Bowl when consumed in Survival mode. Max stack: 1.

---

### Rose Golden Apple

<img src="src/main/resources/assets/uplift/textures/items/rose_golden_apple.png" width="40" style="image-rendering: pixelated;">

An incredibly powerful food with the following effects:

| Effect          | Duration | Amplifier |
| --------------- | -------- | --------- |
| Absorption      | 4 min    | 0         |
| Regeneration    | 20 sec   | 2         |
| Fire Resistance | 2 min    | 0         |
| Resistance      | 2 min    | 0         |

Hunger: 12 | Saturation: 10.0 | Always edible

---

### Stoneman Spawn Egg

**Stoneman Spawn Egg**

A spawn egg for the Stoneman entity. Max stack: 16.

> **Note:** The spawn egg uses Minecraft's built-in spawn egg template (tinted colors), so it does not have a dedicated standalone item texture file in this repo.

---

## Tool & Weapon Stats

All custom tools follow standard Minecraft tool types (Sword, Pickaxe, Axe, Shovel, Hoe) with the following material stats:

| Material        | Harvest Level | Durability | Efficiency | Enchantability | Repair Item     |
| --------------- | ------------- | ---------- | ---------- | -------------- | --------------- |
| Ruby            | 1             | 200        | 5.0        | 20             | Ruby            |
| Sapphire        | 2             | 750        | 6.0        | 10             | Sapphire        |
| Chromium        | 2             | 700        | 6.0        | 12             | Chromium Ingot  |
| Amethyst        | 2             | 600        | 7.0        | 15             | Amethyst        |
| Obsidian        | 3             | 1,800      | 7.0        | 10             | Obsidian Shard  |
| Burning Diamond | 4             | 1,650      | 9.0        | 15             | Burning Diamond |
| Platinum        | 4             | 2,150      | 10.0       | 10             | Platinum Ingot  |
| Uranium         | 3             | 1,100      | 8.0        | 15             | Uranium Ingot   |
| Ender           | 3             | 2,450      | 9.0        | 20             | Ender Gem       |
| Rose Gold       | 4             | 100        | 10.0       | 0              | Rose Gold Ingot |

### Weapon Attack Damage

| Weapon          | Sword | Pickaxe | Axe | Shovel |
| --------------- | ----- | ------- | --- | ------ |
| Ruby            | 6     | 3       | 9   | 3.5    |
| Sapphire        | 6     | 4       | 9   | 4.5    |
| Chromium        | 6     | 4       | 9   | 4.5    |
| Amethyst        | 6     | 5       | 9   | 5.5    |
| Obsidian        | 7     | 5       | 9   | 4.5    |
| Burning Diamond | 8     | 5       | 9   | 6.5    |
| Platinum        | 10    | 6       | 11  | 7.0    |
| Uranium         | 7     | 5       | 9   | 5.5    |
| Ender           | 8     | 5       | 9   | 5.5    |

### Rose Gold Weapons (Special)

Rose Gold only has a **Sword** and **Axe**, each with unique effects when held:

| Weapon            | Attack Damage | Special Effect                         |
| ----------------- | ------------- | -------------------------------------- |
| Rose Golden Sword | 12            | Grants **Speed III** while held        |
| Rose Golden Axe   | 12            | Grants **Invisibility III** while held |

Both have only **100 durability** and **0 enchantability** - extremely powerful but fragile and unenchantable.

---

## Armor Stats & Set Bonuses

Armor values shown as: Boots / Leggings / Chestplate / Helmet

| Material        | Durability Factor | Protection (B/L/C/H) | Toughness | Enchantability | Full Set Bonus                                                   |
| --------------- | ----------------- | -------------------- | --------- | -------------- | ---------------------------------------------------------------- |
| Ruby            | 5                 | 2/4/5/2              | 0.0       | 20             | **Resistance**                                                   |
| Sapphire        | 9                 | 2/5/6/2              | 1.0       | 10             | **Water Breathing**                                              |
| Chromium        | 8                 | 2/5/6/2              | 1.0       | 12             | **Luminous** (custom)                                            |
| Amethyst        | 6                 | 3/5/6/3              | 0.0       | 15             | **Speed**                                                        |
| Obsidian        | 18                | 3/5/5/3              | 2.5       | 10             | **Night Vision**                                                 |
| Burning Diamond | 17                | 4/7/9/4              | 2.0       | 15             | **Fire Resistance**                                              |
| Platinum        | 21                | 5/8/9/5              | 3.0       | 10             | **Strength**                                                     |
| Uranium         | 12                | 3/6/8/3              | 1.0       | 15             | **Haste II**                                                     |
| Bloodstone      | 3                 | 6/6/6/6              | 4.0       | 5              | **Instant Health** + Blindness + Slowness V + Mining Fatigue III |
| Ender           | 24                | 3/6/8/3              | 2.0       | 20             | **Flight** (custom)                                              |

> **Note:** Set bonuses activate only when all 4 armor pieces are worn. The effect persists with infinite duration and is instantly removed when any piece is unequipped. Each set displays colored particles when active. Effects can be toggled on and off — see [Armor Effect Toggle](#armor-effect-toggle).

### Armor Durability Reference

Durability = Durability Factor x Base (Boots: 27, Leggings: 30, Chestplate: 35, Helmet: 25)

| Material        | Boots | Leggings | Chestplate | Helmet |
| --------------- | ----- | -------- | ---------- | ------ |
| Ruby            | 135   | 150      | 175        | 125    |
| Sapphire        | 243   | 270      | 315        | 225    |
| Chromium        | 216   | 240      | 280        | 200    |
| Amethyst        | 162   | 180      | 210        | 150    |
| Obsidian        | 486   | 540      | 630        | 450    |
| Burning Diamond | 459   | 510      | 595        | 425    |
| Platinum        | 567   | 630      | 735        | 525    |
| Uranium         | 324   | 360      | 420        | 300    |
| Bloodstone      | 81    | 90       | 105        | 75     |
| Ender           | 648   | 720      | 840        | 600    |

---

## Armor Effect Toggle

All armor set bonus effects can be **toggled on and off** per player. When toggled off, the armor still provides its normal protection and durability but grants no potion effects or particles. This is useful when you want the armor's defense without the effect (for example, disabling Speed to build precisely, or disabling Luminous while exploring).

**How to toggle:**

1. Equip a full matching armor set (all 4 pieces of the same material)
2. **Double-sneak** (press sneak twice quickly, within 0.5 seconds)
3. An actionbar message confirms the new state: **"Armor Effects: ON"** (green) or **"Armor Effects: OFF"** (red)

| Property    | Value                                                                     |
| ----------- | ------------------------------------------------------------------------- |
| Activation  | Double-sneak while wearing a full matching armor set                      |
| Window      | 10 ticks (0.5 seconds) between sneaks                                     |
| Scope       | Per-player — each player has their own toggle state                       |
| Persistence | Saved to the world — survives game restarts and world reloads             |
| Default     | ON (effects are active)                                                   |
| Applies To  | All armor set bonus effects (Ruby, Sapphire, Amethyst, Chromium, etc.)    |
| Limitation  | Cannot toggle while flying; will display a red error message if attempted |

> **Note:** Toggling affects all armor sets globally for that player. If you toggle effects off with Amethyst armor, they remain off when you switch to Ruby armor until you toggle them back on.

---

## Special Blocks

### Gem Crusher

<img src="wiki/wiki_textures/gem_crusher.png" width="40" style="image-rendering: pixelated;">

A functional block that converts gems and materials into **Gem Dust** when right-clicked with a valid item in your main hand. No fuel is required - simply hold the item and interact.

| Input Item        | Gem Dust Output |
| ----------------- | --------------- |
| Ruby              | 1               |
| Sapphire          | 3               |
| Amethyst          | 3               |
| Diamond (vanilla) | 6               |
| Emerald (vanilla) | 6               |
| Obsidian Shard    | 6               |
| Burning Diamond   | 9               |
| Opal              | 9               |
| Ender Gem         | 9               |

| Property      | Value                      |
| ------------- | -------------------------- |
| Hardness      | 3.5 / Blast Resistance 3.0 |
| Harvest Level | 1 (Stone Pickaxe)          |

---

### Smelter

<img src="src/main/resources/assets/uplift/textures/blocks/smelter_front.png" width="40" style="image-rendering: pixelated;">

A specialized furnace made from **Obsidian**. The Smelter must be **lit with an Igniter** (costs 50 durability) to function. It processes ores and materials using the custom `uplift:uplift` recipe type - recipes not available in normal furnaces.

| Property     | Value                                                 |
| ------------ | ----------------------------------------------------- |
| Hardness     | 50.0 / Blast Resistance 1200.0                        |
| Light Level  | 12 (when lit)                                         |
| Lit By       | Igniter (50 durability cost)                          |
| Smelt Time   | 150 ticks per item (7.5 sec)                          |
| Lit Duration | 200 smelts (fuel drains only while actively smelting) |

> See [Smelter Recipes](#smelter-recipes) for the full Furnace and Smelter recipe list.

---

### Igniter Fire

A special vibrant purple fire placed by the Igniter. Deals different damage depending on weather:

| Condition   | Players               | Mobs                   |
| ----------- | --------------------- | ---------------------- |
| Not Raining | Set on fire for 1 sec | 6 damage + 30 sec fire |
| Raining     | 1 fire damage         | 4 fire damage          |

---

### Gem Torches

Custom colored torches crafted with the resource on top of Coal on top of a Stick. Each torch emits **light level 14**. Available variants:

- <img src="src/main/resources/assets/uplift/textures/items/ruby_torch.png" width="28" style="image-rendering: pixelated;"> Ruby Torch
- <img src="src/main/resources/assets/uplift/textures/items/burning_diamond_torch.png" width="28" style="image-rendering: pixelated;"> Burning Diamond Torch
- <img src="src/main/resources/assets/uplift/textures/items/sapphire_torch.png" width="28" style="image-rendering: pixelated;"> Sapphire Torch
- <img src="src/main/resources/assets/uplift/textures/items/amethyst_torch.png" width="28" style="image-rendering: pixelated;"> Amethyst Torch
- <img src="src/main/resources/assets/uplift/textures/items/obsidian_torch.png" width="28" style="image-rendering: pixelated;"> Obsidian Torch
- <img src="src/main/resources/assets/uplift/textures/items/ender_torch.png" width="28" style="image-rendering: pixelated;"> Ender Torch
- <img src="src/main/resources/assets/uplift/textures/items/opal_torch.png" width="28" style="image-rendering: pixelated;"> Opal Torch
- <img src="src/main/resources/assets/uplift/textures/items/tasmanite_torch.png" width="28" style="image-rendering: pixelated;"> Tasmanite Torch (crafted differently - see [Crafting](#tasmanite-torch))

All torches can be placed on walls (wall torch variant included).

---

## The Cave Dimension

The Cave Dimension ("The Cave") is a custom underground dimension consisting entirely of stone, ores, and caves.

| Property        | Value                                  |
| --------------- | -------------------------------------- |
| Max Height      | 70 blocks                              |
| Sea Level       | 24                                     |
| Bedrock Floor   | Y = 0                                  |
| Bedrock Ceiling | Y = 65                                 |
| Surface         | Stone (no grass, dirt, or sky)         |
| Weather         | None (no rain)                         |
| Daylight Cycle  | Fixed (always dim)                     |
| Can Respawn     | No (deaths send you back to Overworld) |
| Beds            | Explode (like the Nether)              |
| Fog Color       | Black                                  |

**Biome:** Ore Biome - A custom biome with cave carvers and an extremely rich ore distribution. All mod ores spawn here at significantly higher rates than in the Overworld (see [Ore Generation](#ore-generation)).

**Mob Spawns in Cave Dimension:**

| Entity        | Spawn Weight | Group Size |
| ------------- | ------------ | ---------- |
| Stoneman      | 12           | 2-4        |
| Zombie        | 8            | 1-3        |
| Skeleton      | 8            | 1-3        |
| Creeper       | 6            | 1-2        |
| Spider        | 6            | 1-2        |
| Bat (ambient) | 10           | 2-4        |

### Cave Portal

A portal block built from a **Gemstone** frame (just like a Nether Portal frame is made of Obsidian). The portal supports variable sizes from 2x3 to 21x21 and is activated with the **Igniter** (costs 100 durability).

<img src="wiki/wiki_textures/cave_portal.png" width="250" style="image-rendering: auto;">

**How to build:**

1. Build a portal frame out of **Gemstone** blocks (rectangular, minimum 2x3 interior)
2. Use the **Igniter** on the inside of the frame
3. Step into the portal to teleport

**Teleport behavior:**

- Teleports between the **Overworld** and the **Cave Dimension** (1:1 coordinate scale)
- Applies: Nausea (6 sec), Blindness (2 sec), Slow Falling (3 sec) on teleportation
- Portal cooldown: 5 seconds (100 ticks)
- Bi-directional portal linking is persisted across sessions

> **Crafting (Shaped):** The Cave Portal can be crafted from Gemstone blocks (see the recipe in [Crafting](#gemstone-block)). The portal texture is animated, so the recipe display uses a placeholder icon.

---

## Ore Generation

### Overworld Ores

|                                                                                                                                     | Ore                 | Veins/Chunk | Vein Size | Y Range | Harvest Level |
| ----------------------------------------------------------------------------------------------------------------------------------- | ------------------- | ----------- | --------- | ------- | ------------- |
| <img src="src/main/resources/assets/uplift/textures/blocks/ruby_ore.png" width="28" style="image-rendering: pixelated;">            | Ruby Ore            | 22          | 10        | 0-64    | 1 (Stone)     |
| <img src="src/main/resources/assets/uplift/textures/blocks/sapphire_ore.png" width="28" style="image-rendering: pixelated;">        | Sapphire Ore        | 6           | 8         | 0-32    | 2 (Iron)      |
| <img src="src/main/resources/assets/uplift/textures/blocks/chrome_ore.png" width="28" style="image-rendering: pixelated;">          | Chromium Ore        | 6           | 8         | 0-32    | 2 (Iron)      |
| <img src="src/main/resources/assets/uplift/textures/blocks/amethyst_ore.png" width="28" style="image-rendering: pixelated;">        | Amethyst Ore        | 6           | 8         | 0-32    | 2 (Iron)      |
| <img src="src/main/resources/assets/uplift/textures/blocks/platinum_ore.png" width="28" style="image-rendering: pixelated;">        | Platinum Ore        | 1           | 4         | 0-8     | 4 (Platinum)  |
| <img src="src/main/resources/assets/uplift/textures/blocks/burning_diamond_ore.png" width="28" style="image-rendering: pixelated;"> | Burning Diamond Ore | 1           | 4         | 0-12    | 3 (Diamond)   |
| <img src="src/main/resources/assets/uplift/textures/blocks/tasmanite_ore.png" width="28" style="image-rendering: pixelated;">       | Tasmanite Ore       | 12          | 8         | 0-128   | 2 (Iron)      |
| <img src="src/main/resources/assets/uplift/textures/blocks/opal_ore.png" width="28" style="image-rendering: pixelated;">            | Opal Ore            | 1           | 6         | 0-24    | 3 (Diamond)   |
| <img src="src/main/resources/assets/uplift/textures/blocks/uranium_ore.png" width="28" style="image-rendering: pixelated;">         | Uranium Ore         | 4           | 6         | 46-256  | 3 (Diamond)   |

### Nether Ores

|                                                                                                                                | Ore            | Veins/Chunk | Vein Size | Y Range | Found In   |
| ------------------------------------------------------------------------------------------------------------------------------ | -------------- | ----------- | --------- | ------- | ---------- |
| <img src="src/main/resources/assets/uplift/textures/blocks/bloodstone_ore.png" width="28" style="image-rendering: pixelated;"> | Bloodstone Ore | 8           | 8         | 0-128   | Netherrack |

### End Ores

|                                                                                                                           | Ore       | Veins/Chunk | Vein Size | Y Range | Found In  |
| ------------------------------------------------------------------------------------------------------------------------- | --------- | ----------- | --------- | ------- | --------- |
| <img src="src/main/resources/assets/uplift/textures/blocks/ender_ore.png" width="28" style="image-rendering: pixelated;"> | Ender Ore | 32          | 12        | 0-256   | End Stone |

### Cave Dimension Ores

The Cave Dimension has dramatically increased ore rates. All ores generate between Y 0-70.

|                                                                                                                                     | Ore                    | Veins/Chunk | Vein Size |
| ----------------------------------------------------------------------------------------------------------------------------------- | ---------------------- | ----------- | --------- |
| <img src="src/main/resources/assets/uplift/textures/blocks/ruby_ore.png" width="28" style="image-rendering: pixelated;">            | Ruby Ore               | 30          | 10        |
| <img src="src/main/resources/assets/uplift/textures/blocks/sapphire_ore.png" width="28" style="image-rendering: pixelated;">        | Sapphire Ore           | 14          | 8         |
| <img src="src/main/resources/assets/uplift/textures/blocks/chrome_ore.png" width="28" style="image-rendering: pixelated;">          | Chromium Ore           | 14          | 8         |
| <img src="src/main/resources/assets/uplift/textures/blocks/amethyst_ore.png" width="28" style="image-rendering: pixelated;">        | Amethyst Ore           | 14          | 8         |
| <img src="src/main/resources/assets/uplift/textures/blocks/platinum_ore.png" width="28" style="image-rendering: pixelated;">        | Platinum Ore           | 3           | 4         |
| <img src="src/main/resources/assets/uplift/textures/blocks/burning_diamond_ore.png" width="28" style="image-rendering: pixelated;"> | Burning Diamond Ore    | 2           | 4         |
| <img src="src/main/resources/assets/uplift/textures/blocks/tasmanite_ore.png" width="28" style="image-rendering: pixelated;">       | Tasmanite Ore          | 20          | 10        |
| <img src="src/main/resources/assets/uplift/textures/blocks/opal_ore.png" width="28" style="image-rendering: pixelated;">            | Opal Ore               | 6           | 6         |
| <img src="src/main/resources/assets/uplift/textures/blocks/rose_gold_ore.png" width="28" style="image-rendering: pixelated;">       | Rose Gold Ore          | 14          | 6         |
| <img src="src/main/resources/assets/uplift/textures/blocks/uranium_ore.png" width="28" style="image-rendering: pixelated;">         | Uranium Ore            | 6           | 8         |
| <img src="src/main/resources/assets/uplift/textures/blocks/gemstone.png" width="28" style="image-rendering: pixelated;">            | Gemstone               | 10          | 8         |
| <img src="wiki/wiki_textures/coal_ore.png" width="28" style="image-rendering: pixelated;">                                          | Coal Ore (vanilla)     | 50          | 14        |
| <img src="wiki/wiki_textures/iron_ore.png" width="28" style="image-rendering: pixelated;">                                          | Iron Ore (vanilla)     | 26          | 10        |
| <img src="wiki/wiki_textures/gold_ore.png" width="28" style="image-rendering: pixelated;">                                          | Gold Ore (vanilla)     | 12          | 8         |
| <img src="wiki/wiki_textures/diamond_ore.png" width="28" style="image-rendering: pixelated;">                                       | Diamond Ore (vanilla)  | 3           | 8         |
| <img src="wiki/wiki_textures/emerald_ore.png" width="28" style="image-rendering: pixelated;">                                       | Emerald Ore (vanilla)  | 4           | 6         |
| <img src="wiki/wiki_textures/lapis_ore.png" width="28" style="image-rendering: pixelated;">                                         | Lapis Ore (vanilla)    | 12          | 8         |
| <img src="wiki/wiki_textures/redstone_ore.png" width="28" style="image-rendering: pixelated;">                                      | Redstone Ore (vanilla) | 12          | 10        |
| <img src="wiki/wiki_textures/obsidian.png" width="28" style="image-rendering: pixelated;">                                          | Obsidian (vanilla)     | 10          | 8         |

> **Note:** Rose Gold Ore spawns **only** in the Cave Dimension.

---

## Entities

### Stoneman

A hostile mob that spawns in the **Cave Dimension**. Resembles a zombie-like creature made of stone.

| Stat            | Value              |
| --------------- | ------------------ |
| Health          | 10 HP (5 hearts)   |
| Base Attack     | 2.0                |
| Attack (Easy)   | 1.0                |
| Attack (Normal) | 2.0                |
| Attack (Hard)   | 3.0                |
| Movement Speed  | 0.25               |
| Classification  | Monster            |
| Spawn Weight    | 12 (groups of 2-4) |

**Special Behaviors:**

- **Immune to fall damage**
- **Cannot see invisible players** - Invisibility potion completely hides you (use the Rose Golden Axe!)
- Uses Villager sounds (ambient, hurt, death)
- Does not spawn on Peaceful difficulty

**Drops (on player kill):**

| Drop       | Amount |
| ---------- | ------ |
| Ruby       | 0-1    |
| Sapphire   | 0-1    |
| Amethyst   | 0-1    |
| Stone Meal | 0-1    |

---

## Effects

### Flight

<img src="src/main/resources/assets/uplift/textures/mob_effect/flight.png" width="40" style="image-rendering: pixelated;">

Granted by wearing a full set of **Ender armor**. Allows creative-mode flight.

| Property | Value                                                          |
| -------- | -------------------------------------------------------------- |
| Type     | Beneficial                                                     |
| Source   | Full Ender armor set bonus                                     |
| Notes    | Effect is removed immediately if any armor piece is unequipped |

---

### Luminous

<img src="src/main/resources/assets/uplift/textures/mob_effect/luminous.png" width="40" style="image-rendering: pixelated;">

Granted by wearing a full set of **Chromium armor**. Places an invisible light source block at the player's feet that follows them as they move, illuminating the area around them. The light repositions every second and old light blocks are cleaned up automatically. The light source is visible to other players.

| Property        | Value                                                          |
| --------------- | -------------------------------------------------------------- |
| Type            | Beneficial                                                     |
| Source          | Full Chromium armor set bonus                                  |
| Light Level     | Equivalent to a placed light source at the player's position   |
| Update Interval | Every 20 ticks (1 second)                                      |
| Notes           | Effect is removed immediately if any armor piece is unequipped |

---

## Crafting

### Utility & Special Item Recipes

#### Gem Crusher

<table style="border-collapse: collapse;">
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/stone.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/platinum_ingot.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/stone.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td rowspan="3" align="center" width="44" style="vertical-align: middle;"><span style="font-size: 32px;">➞</span></td>
<td rowspan="3" align="center" style="vertical-align: middle; white-space: normal;"><img src="wiki/wiki_textures/gem_crusher.png" width="48" style="image-rendering: pixelated;"></td>
</tr>
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/stone.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/platinum_ingot.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/stone.png" width="32" height="32" style="image-rendering: pixelated;"></td>
</tr>
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/stone.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/stone.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/stone.png" width="32" height="32" style="image-rendering: pixelated;"></td>
</tr>
</table>

> **Crafting (Shaped):** 6 Stone + 2 Platinum Ingots ➞ 1 Gem Crusher

---

#### Smelter (Unlit)

<table style="border-collapse: collapse;">
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/obsidian.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/obsidian.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/obsidian.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td rowspan="3" align="center" width="44" style="vertical-align: middle;"><span style="font-size: 32px;">➞</span></td>
<td rowspan="3" align="center" style="vertical-align: middle; white-space: normal;"><img src="src/main/resources/assets/uplift/textures/blocks/smelter_front_unlit.png" width="48" height="48" style="image-rendering: pixelated;"></td>
</tr>
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/obsidian.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/obsidian.png" width="32" height="32" style="image-rendering: pixelated;"></td>
</tr>
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/obsidian.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/obsidian.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/obsidian.png" width="32" height="32" style="image-rendering: pixelated;"></td>
</tr>
</table>

> **Crafting (Shaped):** 8 Obsidian ➞ 1 Smelter (Unlit). Light it with the Igniter.

---

#### Gemstone Block

<table style="border-collapse: collapse;">
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/gem_dust.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/gem_dust.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/gem_dust.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td rowspan="3" align="center" width="44" style="vertical-align: middle;"><span style="font-size: 32px;">➞</span></td>
<td rowspan="3" align="center" style="vertical-align: middle; white-space: normal;"><img src="src/main/resources/assets/uplift/textures/blocks/gemstone.png" width="48" height="48" style="image-rendering: pixelated;"></td>
</tr>
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/gem_dust.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/gem_dust.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/gem_dust.png" width="32" height="32" style="image-rendering: pixelated;"></td>
</tr>
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/gem_dust.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/gem_dust.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/gem_dust.png" width="32" height="32" style="image-rendering: pixelated;"></td>
</tr>
</table>

> **Crafting (Shaped):** 9 Gem Dust ➞ 1 Gemstone

#### Gem Dust from Gemstone

<table style="border-collapse: collapse;">
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/blocks/gemstone.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" width="44" style="vertical-align: middle;"><span style="font-size: 32px;">➞</span></td>
<td align="center" style="vertical-align: middle; white-space: normal;"><img src="src/main/resources/assets/uplift/textures/items/gem_dust.png" width="48" height="48" style="image-rendering: pixelated;"></td>
</tr>
</table>

> **Crafting (Shapeless):** 1 Gemstone ➞ 3 Gem Dust

---

#### Igniter

<table style="border-collapse: collapse;">
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/burning_diamond.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/flint_and_steel.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" width="44" style="vertical-align: middle;"><span style="font-size: 32px;">➞</span></td>
<td align="center" style="vertical-align: middle; white-space: normal;"><img src="src/main/resources/assets/uplift/textures/items/igniter.png" width="48" height="48" style="image-rendering: pixelated;"></td>
</tr>
</table>

> **Crafting (Shapeless):** 1 Burning Diamond + 1 Flint & Steel ➞ 1 Igniter

---

#### Mine Bomb

<table style="border-collapse: collapse;">
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/gem_dust.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td rowspan="3" align="center" width="44" style="vertical-align: middle;"><span style="font-size: 32px;">➞</span></td>
<td rowspan="3" align="center" style="vertical-align: middle; white-space: normal;"><img src="src/main/resources/assets/uplift/textures/items/mine_bomb.png" width="48" height="48" style="image-rendering: pixelated;"></td>
</tr>
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/gem_dust.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/gunpowder.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/gem_dust.png" width="32" height="32" style="image-rendering: pixelated;"></td>
</tr>
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/gem_dust.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
</tr>
</table>

> **Crafting (Shaped):** 4 Gem Dust + 1 Gunpowder ➞ 1 Mine Bomb

---

#### Uranium Bomb

<table style="border-collapse: collapse;">
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/uranium_ingot.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td rowspan="3" align="center" width="44" style="vertical-align: middle;"><span style="font-size: 32px;">➞</span></td>
<td rowspan="3" align="center" style="vertical-align: middle; white-space: normal;"><img src="src/main/resources/assets/uplift/textures/items/uranium_bomb.png" width="48" height="48" style="image-rendering: pixelated;"></td>
</tr>
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/uranium_ingot.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/gunpowder.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/uranium_ingot.png" width="32" height="32" style="image-rendering: pixelated;"></td>
</tr>
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/uranium_ingot.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
</tr>
</table>

> **Crafting (Shaped):** 4 Uranium Ingots + 1 Gunpowder ➞ 1 Uranium Bomb

---

#### Stone Soup

<table style="border-collapse: collapse;">
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/stone_meal.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/bowl.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" width="44" style="vertical-align: middle;"><span style="font-size: 32px;">➞</span></td>
<td align="center" style="vertical-align: middle; white-space: normal;"><img src="src/main/resources/assets/uplift/textures/items/stone_soup.png" width="48" height="48" style="image-rendering: pixelated;"></td>
</tr>
</table>

> **Crafting (Shapeless):** 1 Stone Meal + 1 Bowl ➞ 1 Stone Soup

---

#### Rose Golden Apple

<table style="border-collapse: collapse;">
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/rose_gold_ingot.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/rose_gold_ingot.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/rose_gold_ingot.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td rowspan="3" align="center" width="44" style="vertical-align: middle;"><span style="font-size: 32px;">➞</span></td>
<td rowspan="3" align="center" style="vertical-align: middle; white-space: normal;"><img src="src/main/resources/assets/uplift/textures/items/rose_golden_apple.png" width="48" height="48" style="image-rendering: pixelated;"></td>
</tr>
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/rose_gold_ingot.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/golden_apple.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/rose_gold_ingot.png" width="32" height="32" style="image-rendering: pixelated;"></td>
</tr>
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/rose_gold_ingot.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/rose_gold_ingot.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/rose_gold_ingot.png" width="32" height="32" style="image-rendering: pixelated;"></td>
</tr>
</table>

> **Crafting (Shaped):** 8 Rose Gold Ingots + 1 Golden Apple ➞ 1 Rose Golden Apple

---

#### Burning Diamond from Dust

<table style="border-collapse: collapse;">
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/burning_dust.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td rowspan="3" align="center" width="44" style="vertical-align: middle;"><span style="font-size: 32px;">➞</span></td>
<td rowspan="3" align="center" style="vertical-align: middle; white-space: normal;"><img src="src/main/resources/assets/uplift/textures/items/burning_diamond.png" width="48" height="48" style="image-rendering: pixelated;"></td>
</tr>
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/burning_dust.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/burning_dust.png" width="32" height="32" style="image-rendering: pixelated;"></td>
</tr>
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/burning_dust.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
</tr>
</table>

> **Crafting (Shaped):** 4 Burning Diamond Dust (diamond pattern) ➞ 1 Burning Diamond

---

### Ruby Recipes

#### Ruby Sword

<table style="border-collapse: collapse;">
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/ruby.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td rowspan="3" align="center" width="44" style="vertical-align: middle;"><span style="font-size: 32px;">➞</span></td>
<td rowspan="3" align="center" style="vertical-align: middle; white-space: normal;"><img src="src/main/resources/assets/uplift/textures/items/ruby_sword.png" width="48" height="48" style="image-rendering: pixelated;"></td>
</tr>
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/ruby.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
</tr>
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/stick.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
</tr>
</table>

> **Crafting (Shaped):** 2 Ruby + 1 Stick ➞ 1 Ruby Sword

#### Ruby Pickaxe

<table style="border-collapse: collapse;">
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/ruby.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/ruby.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/ruby.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td rowspan="3" align="center" width="44" style="vertical-align: middle;"><span style="font-size: 32px;">➞</span></td>
<td rowspan="3" align="center" style="vertical-align: middle; white-space: normal;"><img src="src/main/resources/assets/uplift/textures/items/ruby_pickaxe.png" width="48" height="48" style="image-rendering: pixelated;"></td>
</tr>
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/stick.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
</tr>
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/stick.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
</tr>
</table>

> **Crafting (Shaped):** 3 Ruby + 2 Sticks ➞ 1 Ruby Pickaxe

#### Ruby Axe

<table style="border-collapse: collapse;">
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/ruby.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/ruby.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td rowspan="3" align="center" width="44" style="vertical-align: middle;"><span style="font-size: 32px;">➞</span></td>
<td rowspan="3" align="center" style="vertical-align: middle; white-space: normal;"><img src="src/main/resources/assets/uplift/textures/items/ruby_axe.png" width="48" height="48" style="image-rendering: pixelated;"></td>
</tr>
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/ruby.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/stick.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
</tr>
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/stick.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
</tr>
</table>

> **Crafting (Shaped):** 3 Ruby + 2 Sticks ➞ 1 Ruby Axe

#### Ruby Shovel

<table style="border-collapse: collapse;">
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/ruby.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td rowspan="3" align="center" width="44" style="vertical-align: middle;"><span style="font-size: 32px;">➞</span></td>
<td rowspan="3" align="center" style="vertical-align: middle; white-space: normal;"><img src="src/main/resources/assets/uplift/textures/items/ruby_shovel.png" width="48" height="48" style="image-rendering: pixelated;"></td>
</tr>
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/stick.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
</tr>
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/stick.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
</tr>
</table>

> **Crafting (Shaped):** 1 Ruby + 2 Sticks ➞ 1 Ruby Shovel

#### Ruby Hoe

<table style="border-collapse: collapse;">
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/ruby.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/ruby.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td rowspan="3" align="center" width="44" style="vertical-align: middle;"><span style="font-size: 32px;">➞</span></td>
<td rowspan="3" align="center" style="vertical-align: middle; white-space: normal;"><img src="src/main/resources/assets/uplift/textures/items/ruby_hoe.png" width="48" height="48" style="image-rendering: pixelated;"></td>
</tr>
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/stick.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
</tr>
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/stick.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
</tr>
</table>

> **Crafting (Shaped):** 2 Ruby + 2 Sticks ➞ 1 Ruby Hoe

#### Ruby Helmet

<table style="border-collapse: collapse;">
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/ruby.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/ruby.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/ruby.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td rowspan="3" align="center" width="44" style="vertical-align: middle;"><span style="font-size: 32px;">➞</span></td>
<td rowspan="3" align="center" style="vertical-align: middle; white-space: normal;"><img src="src/main/resources/assets/uplift/textures/items/ruby_helmet.png" width="48" height="48" style="image-rendering: pixelated;"></td>
</tr>
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/ruby.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/ruby.png" width="32" height="32" style="image-rendering: pixelated;"></td>
</tr>
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
</tr>
</table>

> **Crafting (Shaped):** 5 Ruby ➞ 1 Ruby Helmet

#### Ruby Chestplate

<table style="border-collapse: collapse;">
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/ruby.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/ruby.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td rowspan="3" align="center" width="44" style="vertical-align: middle;"><span style="font-size: 32px;">➞</span></td>
<td rowspan="3" align="center" style="vertical-align: middle; white-space: normal;"><img src="src/main/resources/assets/uplift/textures/items/ruby_chestplate.png" width="48" height="48" style="image-rendering: pixelated;"></td>
</tr>
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/ruby.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/ruby.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/ruby.png" width="32" height="32" style="image-rendering: pixelated;"></td>
</tr>
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/ruby.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/ruby.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/ruby.png" width="32" height="32" style="image-rendering: pixelated;"></td>
</tr>
</table>

> **Crafting (Shaped):** 8 Ruby ➞ 1 Ruby Chestplate

#### Ruby Leggings

<table style="border-collapse: collapse;">
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/ruby.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/ruby.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/ruby.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td rowspan="3" align="center" width="44" style="vertical-align: middle;"><span style="font-size: 32px;">➞</span></td>
<td rowspan="3" align="center" style="vertical-align: middle; white-space: normal;"><img src="src/main/resources/assets/uplift/textures/items/ruby_leggings.png" width="48" height="48" style="image-rendering: pixelated;"></td>
</tr>
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/ruby.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/ruby.png" width="32" height="32" style="image-rendering: pixelated;"></td>
</tr>
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/ruby.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/ruby.png" width="32" height="32" style="image-rendering: pixelated;"></td>
</tr>
</table>

> **Crafting (Shaped):** 7 Ruby ➞ 1 Ruby Leggings

#### Ruby Boots

<table style="border-collapse: collapse;">
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/ruby.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/ruby.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td rowspan="3" align="center" width="44" style="vertical-align: middle;"><span style="font-size: 32px;">➞</span></td>
<td rowspan="3" align="center" style="vertical-align: middle; white-space: normal;"><img src="src/main/resources/assets/uplift/textures/items/ruby_boots.png" width="48" height="48" style="image-rendering: pixelated;"></td>
</tr>
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/ruby.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/ruby.png" width="32" height="32" style="image-rendering: pixelated;"></td>
</tr>
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
</tr>
</table>

> **Crafting (Shaped):** 4 Ruby ➞ 1 Ruby Boots

#### Block of Ruby

<table style="border-collapse: collapse;">
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/ruby.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/ruby.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/ruby.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td rowspan="3" align="center" width="44" style="vertical-align: middle;"><span style="font-size: 32px;">➞</span></td>
<td rowspan="3" align="center" style="vertical-align: middle; white-space: normal;"><img src="src/main/resources/assets/uplift/textures/blocks/ruby_block.png" width="48" height="48" style="image-rendering: pixelated;"></td>
</tr>
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/ruby.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/ruby.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/ruby.png" width="32" height="32" style="image-rendering: pixelated;"></td>
</tr>
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/ruby.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/ruby.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/ruby.png" width="32" height="32" style="image-rendering: pixelated;"></td>
</tr>
</table>

> **Crafting (Shaped):** 9 Ruby ➞ 1 Block of Ruby

#### Ruby from Block

<table style="border-collapse: collapse;">
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/blocks/ruby_block.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" width="44" style="vertical-align: middle;"><span style="font-size: 32px;">➞</span></td>
<td align="center" style="vertical-align: middle; white-space: normal;"><img src="src/main/resources/assets/uplift/textures/items/ruby.png" width="48" height="48" style="image-rendering: pixelated;"></td>
</tr>
</table>

> **Crafting (Shapeless):** 1 Block of Ruby ➞ 9 Ruby

#### Ruby Torch

<table style="border-collapse: collapse;">
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/ruby.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td rowspan="3" align="center" width="44" style="vertical-align: middle;"><span style="font-size: 32px;">➞</span></td>
<td rowspan="3" align="center" style="vertical-align: middle; white-space: normal;"><img src="src/main/resources/assets/uplift/textures/items/ruby_torch.png" width="48" height="48" style="image-rendering: pixelated;"></td>
</tr>
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/coal.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
</tr>
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/stick.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
</tr>
</table>

> **Crafting (Shaped):** Ruby above Coal above Stick ➞ 8 Ruby Torches

---

### Sapphire Recipes

#### Sapphire Sword

<table style="border-collapse: collapse;">
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/sapphire.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td rowspan="3" align="center" width="44" style="vertical-align: middle;"><span style="font-size: 32px;">➞</span></td>
<td rowspan="3" align="center" style="vertical-align: middle; white-space: normal;"><img src="src/main/resources/assets/uplift/textures/items/sapphire_sword.png" width="48" height="48" style="image-rendering: pixelated;"></td>
</tr>
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/sapphire.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
</tr>
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/stick.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
</tr>
</table>

> **Crafting (Shaped):** 2 Sapphire + 1 Stick ➞ 1 Sapphire Sword

#### Sapphire Pickaxe

<table style="border-collapse: collapse;">
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/sapphire.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/sapphire.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/sapphire.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td rowspan="3" align="center" width="44" style="vertical-align: middle;"><span style="font-size: 32px;">➞</span></td>
<td rowspan="3" align="center" style="vertical-align: middle; white-space: normal;"><img src="src/main/resources/assets/uplift/textures/items/sapphire_pickaxe.png" width="48" height="48" style="image-rendering: pixelated;"></td>
</tr>
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/stick.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
</tr>
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/stick.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
</tr>
</table>

> **Crafting (Shaped):** 3 Sapphire + 2 Sticks ➞ 1 Sapphire Pickaxe

#### Sapphire Axe

<table style="border-collapse: collapse;">
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/sapphire.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/sapphire.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td rowspan="3" align="center" width="44" style="vertical-align: middle;"><span style="font-size: 32px;">➞</span></td>
<td rowspan="3" align="center" style="vertical-align: middle; white-space: normal;"><img src="src/main/resources/assets/uplift/textures/items/sapphire_axe.png" width="48" height="48" style="image-rendering: pixelated;"></td>
</tr>
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/sapphire.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/stick.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
</tr>
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/stick.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
</tr>
</table>

> **Crafting (Shaped):** 3 Sapphire + 2 Sticks ➞ 1 Sapphire Axe

#### Sapphire Shovel

<table style="border-collapse: collapse;">
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/sapphire.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td rowspan="3" align="center" width="44" style="vertical-align: middle;"><span style="font-size: 32px;">➞</span></td>
<td rowspan="3" align="center" style="vertical-align: middle; white-space: normal;"><img src="src/main/resources/assets/uplift/textures/items/sapphire_shovel.png" width="48" height="48" style="image-rendering: pixelated;"></td>
</tr>
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/stick.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
</tr>
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/stick.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
</tr>
</table>

> **Crafting (Shaped):** 1 Sapphire + 2 Sticks ➞ 1 Sapphire Shovel

#### Sapphire Hoe

<table style="border-collapse: collapse;">
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/sapphire.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/sapphire.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td rowspan="3" align="center" width="44" style="vertical-align: middle;"><span style="font-size: 32px;">➞</span></td>
<td rowspan="3" align="center" style="vertical-align: middle; white-space: normal;"><img src="src/main/resources/assets/uplift/textures/items/sapphire_hoe.png" width="48" height="48" style="image-rendering: pixelated;"></td>
</tr>
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/stick.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
</tr>
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/stick.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
</tr>
</table>

> **Crafting (Shaped):** 2 Sapphire + 2 Sticks ➞ 1 Sapphire Hoe

#### Sapphire Helmet

<table style="border-collapse: collapse;">
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/sapphire.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/sapphire.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/sapphire.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td rowspan="3" align="center" width="44" style="vertical-align: middle;"><span style="font-size: 32px;">➞</span></td>
<td rowspan="3" align="center" style="vertical-align: middle; white-space: normal;"><img src="src/main/resources/assets/uplift/textures/items/sapphire_helmet.png" width="48" height="48" style="image-rendering: pixelated;"></td>
</tr>
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/sapphire.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/sapphire.png" width="32" height="32" style="image-rendering: pixelated;"></td>
</tr>
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
</tr>
</table>

> **Crafting (Shaped):** 5 Sapphire ➞ 1 Sapphire Helmet

#### Sapphire Chestplate

<table style="border-collapse: collapse;">
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/sapphire.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/sapphire.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td rowspan="3" align="center" width="44" style="vertical-align: middle;"><span style="font-size: 32px;">➞</span></td>
<td rowspan="3" align="center" style="vertical-align: middle; white-space: normal;"><img src="src/main/resources/assets/uplift/textures/items/sapphire_chestplate.png" width="48" height="48" style="image-rendering: pixelated;"></td>
</tr>
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/sapphire.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/sapphire.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/sapphire.png" width="32" height="32" style="image-rendering: pixelated;"></td>
</tr>
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/sapphire.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/sapphire.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/sapphire.png" width="32" height="32" style="image-rendering: pixelated;"></td>
</tr>
</table>

> **Crafting (Shaped):** 8 Sapphire ➞ 1 Sapphire Chestplate

#### Sapphire Leggings

<table style="border-collapse: collapse;">
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/sapphire.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/sapphire.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/sapphire.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td rowspan="3" align="center" width="44" style="vertical-align: middle;"><span style="font-size: 32px;">➞</span></td>
<td rowspan="3" align="center" style="vertical-align: middle; white-space: normal;"><img src="src/main/resources/assets/uplift/textures/items/sapphire_leggings.png" width="48" height="48" style="image-rendering: pixelated;"></td>
</tr>
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/sapphire.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/sapphire.png" width="32" height="32" style="image-rendering: pixelated;"></td>
</tr>
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/sapphire.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/sapphire.png" width="32" height="32" style="image-rendering: pixelated;"></td>
</tr>
</table>

> **Crafting (Shaped):** 7 Sapphire ➞ 1 Sapphire Leggings

#### Sapphire Boots

<table style="border-collapse: collapse;">
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/sapphire.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/sapphire.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td rowspan="3" align="center" width="44" style="vertical-align: middle;"><span style="font-size: 32px;">➞</span></td>
<td rowspan="3" align="center" style="vertical-align: middle; white-space: normal;"><img src="src/main/resources/assets/uplift/textures/items/sapphire_boots.png" width="48" height="48" style="image-rendering: pixelated;"></td>
</tr>
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/sapphire.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/sapphire.png" width="32" height="32" style="image-rendering: pixelated;"></td>
</tr>
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
</tr>
</table>

> **Crafting (Shaped):** 4 Sapphire ➞ 1 Sapphire Boots

#### Block of Sapphire

<table style="border-collapse: collapse;">
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/sapphire.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/sapphire.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/sapphire.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td rowspan="3" align="center" width="44" style="vertical-align: middle;"><span style="font-size: 32px;">➞</span></td>
<td rowspan="3" align="center" style="vertical-align: middle; white-space: normal;"><img src="src/main/resources/assets/uplift/textures/blocks/sapphire_block.png" width="48" height="48" style="image-rendering: pixelated;"></td>
</tr>
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/sapphire.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/sapphire.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/sapphire.png" width="32" height="32" style="image-rendering: pixelated;"></td>
</tr>
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/sapphire.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/sapphire.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/sapphire.png" width="32" height="32" style="image-rendering: pixelated;"></td>
</tr>
</table>

> **Crafting (Shaped):** 9 Sapphire ➞ 1 Block of Sapphire

#### Sapphire from Block

<table style="border-collapse: collapse;">
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/blocks/sapphire_block.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" width="44" style="vertical-align: middle;"><span style="font-size: 32px;">➞</span></td>
<td align="center" style="vertical-align: middle; white-space: normal;"><img src="src/main/resources/assets/uplift/textures/items/sapphire.png" width="48" height="48" style="image-rendering: pixelated;"></td>
</tr>
</table>

> **Crafting (Shapeless):** 1 Block of Sapphire ➞ 9 Sapphire

#### Sapphire Torch

<table style="border-collapse: collapse;">
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/sapphire.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td rowspan="3" align="center" width="44" style="vertical-align: middle;"><span style="font-size: 32px;">➞</span></td>
<td rowspan="3" align="center" style="vertical-align: middle; white-space: normal;"><img src="src/main/resources/assets/uplift/textures/items/sapphire_torch.png" width="48" height="48" style="image-rendering: pixelated;"></td>
</tr>
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/coal.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
</tr>
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/stick.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
</tr>
</table>

> **Crafting (Shaped):** Sapphire above Coal above Stick ➞ 8 Sapphire Torches

---

### Amethyst Recipes

#### Amethyst Sword

<table style="border-collapse: collapse;">
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/amethyst.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td rowspan="3" align="center" width="44" style="vertical-align: middle;"><span style="font-size: 32px;">➞</span></td>
<td rowspan="3" align="center" style="vertical-align: middle; white-space: normal;"><img src="src/main/resources/assets/uplift/textures/items/amethyst_sword.png" width="48" height="48" style="image-rendering: pixelated;"></td>
</tr>
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/amethyst.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
</tr>
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/stick.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
</tr>
</table>

> **Crafting (Shaped):** 2 Amethyst + 1 Stick ➞ 1 Amethyst Sword

#### Amethyst Pickaxe

<table style="border-collapse: collapse;">
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/amethyst.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/amethyst.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/amethyst.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td rowspan="3" align="center" width="44" style="vertical-align: middle;"><span style="font-size: 32px;">➞</span></td>
<td rowspan="3" align="center" style="vertical-align: middle; white-space: normal;"><img src="src/main/resources/assets/uplift/textures/items/amethyst_pickaxe.png" width="48" height="48" style="image-rendering: pixelated;"></td>
</tr>
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/stick.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
</tr>
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/stick.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
</tr>
</table>

> **Crafting (Shaped):** 3 Amethyst + 2 Sticks ➞ 1 Amethyst Pickaxe

#### Amethyst Axe

<table style="border-collapse: collapse;">
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/amethyst.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/amethyst.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td rowspan="3" align="center" width="44" style="vertical-align: middle;"><span style="font-size: 32px;">➞</span></td>
<td rowspan="3" align="center" style="vertical-align: middle; white-space: normal;"><img src="src/main/resources/assets/uplift/textures/items/amethyst_axe.png" width="48" height="48" style="image-rendering: pixelated;"></td>
</tr>
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/amethyst.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/stick.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
</tr>
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/stick.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
</tr>
</table>

> **Crafting (Shaped):** 3 Amethyst + 2 Sticks ➞ 1 Amethyst Axe

#### Amethyst Shovel

<table style="border-collapse: collapse;">
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/amethyst.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td rowspan="3" align="center" width="44" style="vertical-align: middle;"><span style="font-size: 32px;">➞</span></td>
<td rowspan="3" align="center" style="vertical-align: middle; white-space: normal;"><img src="src/main/resources/assets/uplift/textures/items/amethyst_shovel.png" width="48" height="48" style="image-rendering: pixelated;"></td>
</tr>
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/stick.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
</tr>
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/stick.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
</tr>
</table>

> **Crafting (Shaped):** 1 Amethyst + 2 Sticks ➞ 1 Amethyst Shovel

#### Amethyst Hoe

<table style="border-collapse: collapse;">
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/amethyst.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/amethyst.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td rowspan="3" align="center" width="44" style="vertical-align: middle;"><span style="font-size: 32px;">➞</span></td>
<td rowspan="3" align="center" style="vertical-align: middle; white-space: normal;"><img src="src/main/resources/assets/uplift/textures/items/amethyst_hoe.png" width="48" height="48" style="image-rendering: pixelated;"></td>
</tr>
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/stick.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
</tr>
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/stick.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
</tr>
</table>

> **Crafting (Shaped):** 2 Amethyst + 2 Sticks ➞ 1 Amethyst Hoe

#### Amethyst Helmet

<table style="border-collapse: collapse;">
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/amethyst.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/amethyst.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/amethyst.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td rowspan="3" align="center" width="44" style="vertical-align: middle;"><span style="font-size: 32px;">➞</span></td>
<td rowspan="3" align="center" style="vertical-align: middle; white-space: normal;"><img src="src/main/resources/assets/uplift/textures/items/amethyst_helmet.png" width="48" height="48" style="image-rendering: pixelated;"></td>
</tr>
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/amethyst.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/amethyst.png" width="32" height="32" style="image-rendering: pixelated;"></td>
</tr>
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
</tr>
</table>

> **Crafting (Shaped):** 5 Amethyst ➞ 1 Amethyst Helmet

#### Amethyst Chestplate

<table style="border-collapse: collapse;">
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/amethyst.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/amethyst.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td rowspan="3" align="center" width="44" style="vertical-align: middle;"><span style="font-size: 32px;">➞</span></td>
<td rowspan="3" align="center" style="vertical-align: middle; white-space: normal;"><img src="src/main/resources/assets/uplift/textures/items/amethyst_chestplate.png" width="48" height="48" style="image-rendering: pixelated;"></td>
</tr>
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/amethyst.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/amethyst.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/amethyst.png" width="32" height="32" style="image-rendering: pixelated;"></td>
</tr>
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/amethyst.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/amethyst.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/amethyst.png" width="32" height="32" style="image-rendering: pixelated;"></td>
</tr>
</table>

> **Crafting (Shaped):** 8 Amethyst ➞ 1 Amethyst Chestplate

#### Amethyst Leggings

<table style="border-collapse: collapse;">
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/amethyst.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/amethyst.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/amethyst.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td rowspan="3" align="center" width="44" style="vertical-align: middle;"><span style="font-size: 32px;">➞</span></td>
<td rowspan="3" align="center" style="vertical-align: middle; white-space: normal;"><img src="src/main/resources/assets/uplift/textures/items/amethyst_leggings.png" width="48" height="48" style="image-rendering: pixelated;"></td>
</tr>
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/amethyst.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/amethyst.png" width="32" height="32" style="image-rendering: pixelated;"></td>
</tr>
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/amethyst.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/amethyst.png" width="32" height="32" style="image-rendering: pixelated;"></td>
</tr>
</table>

> **Crafting (Shaped):** 7 Amethyst ➞ 1 Amethyst Leggings

#### Amethyst Boots

<table style="border-collapse: collapse;">
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/amethyst.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/amethyst.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td rowspan="3" align="center" width="44" style="vertical-align: middle;"><span style="font-size: 32px;">➞</span></td>
<td rowspan="3" align="center" style="vertical-align: middle; white-space: normal;"><img src="src/main/resources/assets/uplift/textures/items/amethyst_boots.png" width="48" height="48" style="image-rendering: pixelated;"></td>
</tr>
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/amethyst.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/amethyst.png" width="32" height="32" style="image-rendering: pixelated;"></td>
</tr>
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
</tr>
</table>

> **Crafting (Shaped):** 4 Amethyst ➞ 1 Amethyst Boots

#### Block of Amethyst

<table style="border-collapse: collapse;">
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/amethyst.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/amethyst.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/amethyst.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td rowspan="3" align="center" width="44" style="vertical-align: middle;"><span style="font-size: 32px;">➞</span></td>
<td rowspan="3" align="center" style="vertical-align: middle; white-space: normal;"><img src="src/main/resources/assets/uplift/textures/blocks/amethyst_block.png" width="48" height="48" style="image-rendering: pixelated;"></td>
</tr>
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/amethyst.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/amethyst.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/amethyst.png" width="32" height="32" style="image-rendering: pixelated;"></td>
</tr>
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/amethyst.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/amethyst.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/amethyst.png" width="32" height="32" style="image-rendering: pixelated;"></td>
</tr>
</table>

> **Crafting (Shaped):** 9 Amethyst ➞ 1 Block of Amethyst

#### Amethyst from Block

<table style="border-collapse: collapse;">
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/blocks/amethyst_block.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" width="44" style="vertical-align: middle;"><span style="font-size: 32px;">➞</span></td>
<td align="center" style="vertical-align: middle; white-space: normal;"><img src="src/main/resources/assets/uplift/textures/items/amethyst.png" width="48" height="48" style="image-rendering: pixelated;"></td>
</tr>
</table>

> **Crafting (Shapeless):** 1 Block of Amethyst ➞ 9 Amethyst

#### Amethyst Torch

<table style="border-collapse: collapse;">
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/amethyst.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td rowspan="3" align="center" width="44" style="vertical-align: middle;"><span style="font-size: 32px;">➞</span></td>
<td rowspan="3" align="center" style="vertical-align: middle; white-space: normal;"><img src="src/main/resources/assets/uplift/textures/items/amethyst_torch.png" width="48" height="48" style="image-rendering: pixelated;"></td>
</tr>
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/coal.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
</tr>
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/stick.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
</tr>
</table>

> **Crafting (Shaped):** Amethyst above Coal above Stick ➞ 8 Amethyst Torches

---

### Chromium Recipes

#### Chromium Sword

<table style="border-collapse: collapse;">
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/chrome_ingot.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td rowspan="3" align="center" width="44" style="vertical-align: middle;"><span style="font-size: 32px;">➞</span></td>
<td rowspan="3" align="center" style="vertical-align: middle; white-space: normal;"><img src="src/main/resources/assets/uplift/textures/items/chrome_sword.png" width="48" height="48" style="image-rendering: pixelated;"></td>
</tr>
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/chrome_ingot.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
</tr>
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/stick.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
</tr>
</table>

> **Crafting (Shaped):** 2 Chromium Ingot + 1 Stick ➞ 1 Chromium Sword

#### Chromium Pickaxe

<table style="border-collapse: collapse;">
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/chrome_ingot.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/chrome_ingot.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/chrome_ingot.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td rowspan="3" align="center" width="44" style="vertical-align: middle;"><span style="font-size: 32px;">➞</span></td>
<td rowspan="3" align="center" style="vertical-align: middle; white-space: normal;"><img src="src/main/resources/assets/uplift/textures/items/chrome_pickaxe.png" width="48" height="48" style="image-rendering: pixelated;"></td>
</tr>
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/stick.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
</tr>
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/stick.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
</tr>
</table>

> **Crafting (Shaped):** 3 Chromium Ingot + 2 Sticks ➞ 1 Chromium Pickaxe

#### Chromium Axe

<table style="border-collapse: collapse;">
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/chrome_ingot.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/chrome_ingot.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td rowspan="3" align="center" width="44" style="vertical-align: middle;"><span style="font-size: 32px;">➞</span></td>
<td rowspan="3" align="center" style="vertical-align: middle; white-space: normal;"><img src="src/main/resources/assets/uplift/textures/items/chrome_axe.png" width="48" height="48" style="image-rendering: pixelated;"></td>
</tr>
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/chrome_ingot.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/stick.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
</tr>
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/stick.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
</tr>
</table>

> **Crafting (Shaped):** 3 Chromium Ingot + 2 Sticks ➞ 1 Chromium Axe

#### Chromium Shovel

<table style="border-collapse: collapse;">
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/chrome_ingot.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td rowspan="3" align="center" width="44" style="vertical-align: middle;"><span style="font-size: 32px;">➞</span></td>
<td rowspan="3" align="center" style="vertical-align: middle; white-space: normal;"><img src="src/main/resources/assets/uplift/textures/items/chrome_shovel.png" width="48" height="48" style="image-rendering: pixelated;"></td>
</tr>
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/stick.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
</tr>
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/stick.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
</tr>
</table>

> **Crafting (Shaped):** 1 Chromium Ingot + 2 Sticks ➞ 1 Chromium Shovel

#### Chromium Hoe

<table style="border-collapse: collapse;">
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/chrome_ingot.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/chrome_ingot.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td rowspan="3" align="center" width="44" style="vertical-align: middle;"><span style="font-size: 32px;">➞</span></td>
<td rowspan="3" align="center" style="vertical-align: middle; white-space: normal;"><img src="src/main/resources/assets/uplift/textures/items/chrome_hoe.png" width="48" height="48" style="image-rendering: pixelated;"></td>
</tr>
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/stick.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
</tr>
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/stick.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
</tr>
</table>

> **Crafting (Shaped):** 2 Chromium Ingot + 2 Sticks ➞ 1 Chromium Hoe

#### Chromium Helmet

<table style="border-collapse: collapse;">
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/chrome_ingot.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/chrome_ingot.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/chrome_ingot.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td rowspan="3" align="center" width="44" style="vertical-align: middle;"><span style="font-size: 32px;">➞</span></td>
<td rowspan="3" align="center" style="vertical-align: middle; white-space: normal;"><img src="src/main/resources/assets/uplift/textures/items/chrome_helmet.png" width="48" height="48" style="image-rendering: pixelated;"></td>
</tr>
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/chrome_ingot.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/chrome_ingot.png" width="32" height="32" style="image-rendering: pixelated;"></td>
</tr>
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
</tr>
</table>

> **Crafting (Shaped):** 5 Chromium Ingot ➞ 1 Chromium Helmet

#### Chromium Chestplate

<table style="border-collapse: collapse;">
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/chrome_ingot.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/chrome_ingot.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td rowspan="3" align="center" width="44" style="vertical-align: middle;"><span style="font-size: 32px;">➞</span></td>
<td rowspan="3" align="center" style="vertical-align: middle; white-space: normal;"><img src="src/main/resources/assets/uplift/textures/items/chrome_chestplate.png" width="48" height="48" style="image-rendering: pixelated;"></td>
</tr>
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/chrome_ingot.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/chrome_ingot.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/chrome_ingot.png" width="32" height="32" style="image-rendering: pixelated;"></td>
</tr>
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/chrome_ingot.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/chrome_ingot.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/chrome_ingot.png" width="32" height="32" style="image-rendering: pixelated;"></td>
</tr>
</table>

> **Crafting (Shaped):** 8 Chromium Ingot ➞ 1 Chromium Chestplate

#### Chromium Leggings

<table style="border-collapse: collapse;">
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/chrome_ingot.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/chrome_ingot.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/chrome_ingot.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td rowspan="3" align="center" width="44" style="vertical-align: middle;"><span style="font-size: 32px;">➞</span></td>
<td rowspan="3" align="center" style="vertical-align: middle; white-space: normal;"><img src="src/main/resources/assets/uplift/textures/items/chrome_leggings.png" width="48" height="48" style="image-rendering: pixelated;"></td>
</tr>
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/chrome_ingot.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/chrome_ingot.png" width="32" height="32" style="image-rendering: pixelated;"></td>
</tr>
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/chrome_ingot.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/chrome_ingot.png" width="32" height="32" style="image-rendering: pixelated;"></td>
</tr>
</table>

> **Crafting (Shaped):** 7 Chromium Ingot ➞ 1 Chromium Leggings

#### Chromium Boots

<table style="border-collapse: collapse;">
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/chrome_ingot.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/chrome_ingot.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td rowspan="3" align="center" width="44" style="vertical-align: middle;"><span style="font-size: 32px;">➞</span></td>
<td rowspan="3" align="center" style="vertical-align: middle; white-space: normal;"><img src="src/main/resources/assets/uplift/textures/items/chrome_boots.png" width="48" height="48" style="image-rendering: pixelated;"></td>
</tr>
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/chrome_ingot.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/chrome_ingot.png" width="32" height="32" style="image-rendering: pixelated;"></td>
</tr>
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
</tr>
</table>

> **Crafting (Shaped):** 4 Chromium Ingot ➞ 1 Chromium Boots

#### Block of Chromium

<table style="border-collapse: collapse;">
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/chrome_ingot.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/chrome_ingot.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/chrome_ingot.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td rowspan="3" align="center" width="44" style="vertical-align: middle;"><span style="font-size: 32px;">➞</span></td>
<td rowspan="3" align="center" style="vertical-align: middle; white-space: normal;"><img src="src/main/resources/assets/uplift/textures/blocks/chrome_block.png" width="48" height="48" style="image-rendering: pixelated;"></td>
</tr>
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/chrome_ingot.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/chrome_ingot.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/chrome_ingot.png" width="32" height="32" style="image-rendering: pixelated;"></td>
</tr>
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/chrome_ingot.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/chrome_ingot.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/chrome_ingot.png" width="32" height="32" style="image-rendering: pixelated;"></td>
</tr>
</table>

> **Crafting (Shaped):** 9 Chromium Ingot ➞ 1 Block of Chromium

#### Chromium Ingot from Block

<table style="border-collapse: collapse;">
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/blocks/chrome_block.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" width="44" style="vertical-align: middle;"><span style="font-size: 32px;">➞</span></td>
<td align="center" style="vertical-align: middle; white-space: normal;"><img src="src/main/resources/assets/uplift/textures/items/chrome_ingot.png" width="48" height="48" style="image-rendering: pixelated;"></td>
</tr>
</table>

> **Crafting (Shapeless):** 1 Block of Chromium ➞ 9 Chromium Ingot

#### Chromium Ingot from Nuggets

<table style="border-collapse: collapse;">
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/chrome_nugget.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/chrome_nugget.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/chrome_nugget.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td rowspan="3" align="center" width="44" style="vertical-align: middle;"><span style="font-size: 32px;">➞</span></td>
<td rowspan="3" align="center" style="vertical-align: middle; white-space: normal;"><img src="src/main/resources/assets/uplift/textures/items/chrome_ingot.png" width="48" height="48" style="image-rendering: pixelated;"></td>
</tr>
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/chrome_nugget.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/chrome_nugget.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/chrome_nugget.png" width="32" height="32" style="image-rendering: pixelated;"></td>
</tr>
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/chrome_nugget.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/chrome_nugget.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/chrome_nugget.png" width="32" height="32" style="image-rendering: pixelated;"></td>
</tr>
</table>

> **Crafting (Shaped):** 9 Chromium Nuggets ➞ 1 Chromium Ingot

#### Chromium Nuggets from Ingot

<table style="border-collapse: collapse;">
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/chrome_ingot.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" width="44" style="vertical-align: middle;"><span style="font-size: 32px;">➞</span></td>
<td align="center" style="vertical-align: middle; white-space: normal;"><img src="src/main/resources/assets/uplift/textures/items/chrome_nugget.png" width="48" height="48" style="image-rendering: pixelated;"></td>
</tr>
</table>

> **Crafting (Shapeless):** 1 Chromium Ingot ➞ 9 Chromium Nuggets

---

### Obsidian Recipes

#### Obsidian Sword

<table style="border-collapse: collapse;">
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/obsidian_shard.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td rowspan="3" align="center" width="44" style="vertical-align: middle;"><span style="font-size: 32px;">➞</span></td>
<td rowspan="3" align="center" style="vertical-align: middle; white-space: normal;"><img src="src/main/resources/assets/uplift/textures/items/obsidian_sword.png" width="48" height="48" style="image-rendering: pixelated;"></td>
</tr>
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/obsidian_shard.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
</tr>
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/stick.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
</tr>
</table>

> **Crafting (Shaped):** 2 Obsidian Shard + 1 Stick ➞ 1 Obsidian Sword

#### Obsidian Pickaxe

<table style="border-collapse: collapse;">
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/obsidian_shard.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/obsidian_shard.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/obsidian_shard.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td rowspan="3" align="center" width="44" style="vertical-align: middle;"><span style="font-size: 32px;">➞</span></td>
<td rowspan="3" align="center" style="vertical-align: middle; white-space: normal;"><img src="src/main/resources/assets/uplift/textures/items/obsidian_pickaxe.png" width="48" height="48" style="image-rendering: pixelated;"></td>
</tr>
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/stick.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
</tr>
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/stick.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
</tr>
</table>

> **Crafting (Shaped):** 3 Obsidian Shard + 2 Sticks ➞ 1 Obsidian Pickaxe

#### Obsidian Axe

<table style="border-collapse: collapse;">
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/obsidian_shard.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/obsidian_shard.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td rowspan="3" align="center" width="44" style="vertical-align: middle;"><span style="font-size: 32px;">➞</span></td>
<td rowspan="3" align="center" style="vertical-align: middle; white-space: normal;"><img src="src/main/resources/assets/uplift/textures/items/obsidian_axe.png" width="48" height="48" style="image-rendering: pixelated;"></td>
</tr>
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/obsidian_shard.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/stick.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
</tr>
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/stick.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
</tr>
</table>

> **Crafting (Shaped):** 3 Obsidian Shard + 2 Sticks ➞ 1 Obsidian Axe

#### Obsidian Shovel

<table style="border-collapse: collapse;">
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/obsidian_shard.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td rowspan="3" align="center" width="44" style="vertical-align: middle;"><span style="font-size: 32px;">➞</span></td>
<td rowspan="3" align="center" style="vertical-align: middle; white-space: normal;"><img src="src/main/resources/assets/uplift/textures/items/obsidian_shovel.png" width="48" height="48" style="image-rendering: pixelated;"></td>
</tr>
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/stick.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
</tr>
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/stick.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
</tr>
</table>

> **Crafting (Shaped):** 1 Obsidian Shard + 2 Sticks ➞ 1 Obsidian Shovel

#### Obsidian Hoe

<table style="border-collapse: collapse;">
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/obsidian_shard.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/obsidian_shard.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td rowspan="3" align="center" width="44" style="vertical-align: middle;"><span style="font-size: 32px;">➞</span></td>
<td rowspan="3" align="center" style="vertical-align: middle; white-space: normal;"><img src="src/main/resources/assets/uplift/textures/items/obsidian_hoe.png" width="48" height="48" style="image-rendering: pixelated;"></td>
</tr>
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/stick.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
</tr>
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/stick.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
</tr>
</table>

> **Crafting (Shaped):** 2 Obsidian Shard + 2 Sticks ➞ 1 Obsidian Hoe

#### Obsidian Helmet

<table style="border-collapse: collapse;">
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/obsidian_shard.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/obsidian_shard.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/obsidian_shard.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td rowspan="3" align="center" width="44" style="vertical-align: middle;"><span style="font-size: 32px;">➞</span></td>
<td rowspan="3" align="center" style="vertical-align: middle; white-space: normal;"><img src="src/main/resources/assets/uplift/textures/items/obsidian_helmet.png" width="48" height="48" style="image-rendering: pixelated;"></td>
</tr>
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/obsidian_shard.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/obsidian_shard.png" width="32" height="32" style="image-rendering: pixelated;"></td>
</tr>
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
</tr>
</table>

> **Crafting (Shaped):** 5 Obsidian Shard ➞ 1 Obsidian Helmet

#### Obsidian Chestplate

<table style="border-collapse: collapse;">
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/obsidian_shard.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/obsidian_shard.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td rowspan="3" align="center" width="44" style="vertical-align: middle;"><span style="font-size: 32px;">➞</span></td>
<td rowspan="3" align="center" style="vertical-align: middle; white-space: normal;"><img src="src/main/resources/assets/uplift/textures/items/obsidian_chestplate.png" width="48" height="48" style="image-rendering: pixelated;"></td>
</tr>
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/obsidian_shard.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/obsidian_shard.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/obsidian_shard.png" width="32" height="32" style="image-rendering: pixelated;"></td>
</tr>
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/obsidian_shard.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/obsidian_shard.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/obsidian_shard.png" width="32" height="32" style="image-rendering: pixelated;"></td>
</tr>
</table>

> **Crafting (Shaped):** 8 Obsidian Shard ➞ 1 Obsidian Chestplate

#### Obsidian Leggings

<table style="border-collapse: collapse;">
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/obsidian_shard.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/obsidian_shard.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/obsidian_shard.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td rowspan="3" align="center" width="44" style="vertical-align: middle;"><span style="font-size: 32px;">➞</span></td>
<td rowspan="3" align="center" style="vertical-align: middle; white-space: normal;"><img src="src/main/resources/assets/uplift/textures/items/obsidian_leggings.png" width="48" height="48" style="image-rendering: pixelated;"></td>
</tr>
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/obsidian_shard.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/obsidian_shard.png" width="32" height="32" style="image-rendering: pixelated;"></td>
</tr>
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/obsidian_shard.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/obsidian_shard.png" width="32" height="32" style="image-rendering: pixelated;"></td>
</tr>
</table>

> **Crafting (Shaped):** 7 Obsidian Shard ➞ 1 Obsidian Leggings

#### Obsidian Boots

<table style="border-collapse: collapse;">
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/obsidian_shard.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/obsidian_shard.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td rowspan="3" align="center" width="44" style="vertical-align: middle;"><span style="font-size: 32px;">➞</span></td>
<td rowspan="3" align="center" style="vertical-align: middle; white-space: normal;"><img src="src/main/resources/assets/uplift/textures/items/obsidian_boots.png" width="48" height="48" style="image-rendering: pixelated;"></td>
</tr>
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/obsidian_shard.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/obsidian_shard.png" width="32" height="32" style="image-rendering: pixelated;"></td>
</tr>
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
</tr>
</table>

> **Crafting (Shaped):** 4 Obsidian Shard ➞ 1 Obsidian Boots

#### Block of Obsidian

<table style="border-collapse: collapse;">
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/obsidian_shard.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/obsidian_shard.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/obsidian_shard.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td rowspan="3" align="center" width="44" style="vertical-align: middle;"><span style="font-size: 32px;">➞</span></td>
<td rowspan="3" align="center" style="vertical-align: middle; white-space: normal;"><img src="src/main/resources/assets/uplift/textures/blocks/obsidian_block.png" width="48" height="48" style="image-rendering: pixelated;"></td>
</tr>
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/obsidian_shard.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/obsidian_shard.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/obsidian_shard.png" width="32" height="32" style="image-rendering: pixelated;"></td>
</tr>
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/obsidian_shard.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/obsidian_shard.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/obsidian_shard.png" width="32" height="32" style="image-rendering: pixelated;"></td>
</tr>
</table>

> **Crafting (Shaped):** 9 Obsidian Shard ➞ 1 Block of Obsidian

#### Obsidian Shard from Block

<table style="border-collapse: collapse;">
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/blocks/obsidian_block.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" width="44" style="vertical-align: middle;"><span style="font-size: 32px;">➞</span></td>
<td align="center" style="vertical-align: middle; white-space: normal;"><img src="src/main/resources/assets/uplift/textures/items/obsidian_shard.png" width="48" height="48" style="image-rendering: pixelated;"></td>
</tr>
</table>

> **Crafting (Shapeless):** 1 Block of Obsidian ➞ 9 Obsidian Shard

#### Obsidian Torch

<table style="border-collapse: collapse;">
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/obsidian_shard.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td rowspan="3" align="center" width="44" style="vertical-align: middle;"><span style="font-size: 32px;">➞</span></td>
<td rowspan="3" align="center" style="vertical-align: middle; white-space: normal;"><img src="src/main/resources/assets/uplift/textures/items/obsidian_torch.png" width="48" height="48" style="image-rendering: pixelated;"></td>
</tr>
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/coal.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
</tr>
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/stick.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
</tr>
</table>

> **Crafting (Shaped):** Obsidian Shard above Coal above Stick ➞ 8 Obsidian Torches

---

### Burning Diamond Recipes

#### Burning Diamond Sword

<table style="border-collapse: collapse;">
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/burning_diamond.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td rowspan="3" align="center" width="44" style="vertical-align: middle;"><span style="font-size: 32px;">➞</span></td>
<td rowspan="3" align="center" style="vertical-align: middle; white-space: normal;"><img src="src/main/resources/assets/uplift/textures/items/burning_diamond_sword.png" width="48" height="48" style="image-rendering: pixelated;"></td>
</tr>
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/burning_diamond.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
</tr>
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/stick.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
</tr>
</table>

> **Crafting (Shaped):** 2 Burning Diamond + 1 Stick ➞ 1 Burning Diamond Sword

#### Burning Diamond Pickaxe

<table style="border-collapse: collapse;">
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/burning_diamond.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/burning_diamond.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/burning_diamond.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td rowspan="3" align="center" width="44" style="vertical-align: middle;"><span style="font-size: 32px;">➞</span></td>
<td rowspan="3" align="center" style="vertical-align: middle; white-space: normal;"><img src="src/main/resources/assets/uplift/textures/items/burning_diamond_pickaxe.png" width="48" height="48" style="image-rendering: pixelated;"></td>
</tr>
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/stick.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
</tr>
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/stick.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
</tr>
</table>

> **Crafting (Shaped):** 3 Burning Diamond + 2 Sticks ➞ 1 Burning Diamond Pickaxe

#### Burning Diamond Axe

<table style="border-collapse: collapse;">
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/burning_diamond.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/burning_diamond.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td rowspan="3" align="center" width="44" style="vertical-align: middle;"><span style="font-size: 32px;">➞</span></td>
<td rowspan="3" align="center" style="vertical-align: middle; white-space: normal;"><img src="src/main/resources/assets/uplift/textures/items/burning_diamond_axe.png" width="48" height="48" style="image-rendering: pixelated;"></td>
</tr>
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/burning_diamond.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/stick.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
</tr>
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/stick.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
</tr>
</table>

> **Crafting (Shaped):** 3 Burning Diamond + 2 Sticks ➞ 1 Burning Diamond Axe

#### Burning Diamond Shovel

<table style="border-collapse: collapse;">
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/burning_diamond.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td rowspan="3" align="center" width="44" style="vertical-align: middle;"><span style="font-size: 32px;">➞</span></td>
<td rowspan="3" align="center" style="vertical-align: middle; white-space: normal;"><img src="src/main/resources/assets/uplift/textures/items/burning_diamond_shovel.png" width="48" height="48" style="image-rendering: pixelated;"></td>
</tr>
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/stick.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
</tr>
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/stick.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
</tr>
</table>

> **Crafting (Shaped):** 1 Burning Diamond + 2 Sticks ➞ 1 Burning Diamond Shovel

#### Burning Diamond Hoe

<table style="border-collapse: collapse;">
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/burning_diamond.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/burning_diamond.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td rowspan="3" align="center" width="44" style="vertical-align: middle;"><span style="font-size: 32px;">➞</span></td>
<td rowspan="3" align="center" style="vertical-align: middle; white-space: normal;"><img src="src/main/resources/assets/uplift/textures/items/burning_diamond_hoe.png" width="48" height="48" style="image-rendering: pixelated;"></td>
</tr>
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/stick.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
</tr>
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/stick.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
</tr>
</table>

> **Crafting (Shaped):** 2 Burning Diamond + 2 Sticks ➞ 1 Burning Diamond Hoe

#### Burning Diamond Helmet

<table style="border-collapse: collapse;">
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/burning_diamond.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/burning_diamond.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/burning_diamond.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td rowspan="3" align="center" width="44" style="vertical-align: middle;"><span style="font-size: 32px;">➞</span></td>
<td rowspan="3" align="center" style="vertical-align: middle; white-space: normal;"><img src="src/main/resources/assets/uplift/textures/items/burning_diamond_helmet.png" width="48" height="48" style="image-rendering: pixelated;"></td>
</tr>
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/burning_diamond.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/burning_diamond.png" width="32" height="32" style="image-rendering: pixelated;"></td>
</tr>
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
</tr>
</table>

> **Crafting (Shaped):** 5 Burning Diamond ➞ 1 Burning Diamond Helmet

#### Burning Diamond Chestplate

<table style="border-collapse: collapse;">
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/burning_diamond.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/burning_diamond.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td rowspan="3" align="center" width="44" style="vertical-align: middle;"><span style="font-size: 32px;">➞</span></td>
<td rowspan="3" align="center" style="vertical-align: middle; white-space: normal;"><img src="src/main/resources/assets/uplift/textures/items/burning_diamond_chestplate.png" width="48" height="48" style="image-rendering: pixelated;"></td>
</tr>
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/burning_diamond.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/burning_diamond.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/burning_diamond.png" width="32" height="32" style="image-rendering: pixelated;"></td>
</tr>
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/burning_diamond.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/burning_diamond.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/burning_diamond.png" width="32" height="32" style="image-rendering: pixelated;"></td>
</tr>
</table>

> **Crafting (Shaped):** 8 Burning Diamond ➞ 1 Burning Diamond Chestplate

#### Burning Diamond Leggings

<table style="border-collapse: collapse;">
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/burning_diamond.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/burning_diamond.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/burning_diamond.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td rowspan="3" align="center" width="44" style="vertical-align: middle;"><span style="font-size: 32px;">➞</span></td>
<td rowspan="3" align="center" style="vertical-align: middle; white-space: normal;"><img src="src/main/resources/assets/uplift/textures/items/burning_diamond_leggings.png" width="48" height="48" style="image-rendering: pixelated;"></td>
</tr>
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/burning_diamond.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/burning_diamond.png" width="32" height="32" style="image-rendering: pixelated;"></td>
</tr>
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/burning_diamond.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/burning_diamond.png" width="32" height="32" style="image-rendering: pixelated;"></td>
</tr>
</table>

> **Crafting (Shaped):** 7 Burning Diamond ➞ 1 Burning Diamond Leggings

#### Burning Diamond Boots

<table style="border-collapse: collapse;">
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/burning_diamond.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/burning_diamond.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td rowspan="3" align="center" width="44" style="vertical-align: middle;"><span style="font-size: 32px;">➞</span></td>
<td rowspan="3" align="center" style="vertical-align: middle; white-space: normal;"><img src="src/main/resources/assets/uplift/textures/items/burning_diamond_boots.png" width="48" height="48" style="image-rendering: pixelated;"></td>
</tr>
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/burning_diamond.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/burning_diamond.png" width="32" height="32" style="image-rendering: pixelated;"></td>
</tr>
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
</tr>
</table>

> **Crafting (Shaped):** 4 Burning Diamond ➞ 1 Burning Diamond Boots

#### Block of Burning Diamond

<table style="border-collapse: collapse;">
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/burning_diamond.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/burning_diamond.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/burning_diamond.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td rowspan="3" align="center" width="44" style="vertical-align: middle;"><span style="font-size: 32px;">➞</span></td>
<td rowspan="3" align="center" style="vertical-align: middle; white-space: normal;"><img src="src/main/resources/assets/uplift/textures/blocks/burning_diamond_block.png" width="48" height="48" style="image-rendering: pixelated;"></td>
</tr>
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/burning_diamond.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/burning_diamond.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/burning_diamond.png" width="32" height="32" style="image-rendering: pixelated;"></td>
</tr>
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/burning_diamond.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/burning_diamond.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/burning_diamond.png" width="32" height="32" style="image-rendering: pixelated;"></td>
</tr>
</table>

> **Crafting (Shaped):** 9 Burning Diamond ➞ 1 Block of Burning Diamond

#### Burning Diamond from Block

<table style="border-collapse: collapse;">
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/blocks/burning_diamond_block.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" width="44" style="vertical-align: middle;"><span style="font-size: 32px;">➞</span></td>
<td align="center" style="vertical-align: middle; white-space: normal;"><img src="src/main/resources/assets/uplift/textures/items/burning_diamond.png" width="48" height="48" style="image-rendering: pixelated;"></td>
</tr>
</table>

> **Crafting (Shapeless):** 1 Block of Burning Diamond ➞ 9 Burning Diamond

#### Burning Diamond Torch

<table style="border-collapse: collapse;">
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/burning_diamond.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td rowspan="3" align="center" width="44" style="vertical-align: middle;"><span style="font-size: 32px;">➞</span></td>
<td rowspan="3" align="center" style="vertical-align: middle; white-space: normal;"><img src="src/main/resources/assets/uplift/textures/items/burning_diamond_torch.png" width="48" height="48" style="image-rendering: pixelated;"></td>
</tr>
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/coal.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
</tr>
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/stick.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
</tr>
</table>

> **Crafting (Shaped):** Burning Diamond above Coal above Stick ➞ 8 Burning Diamond Torches

---

### Platinum Recipes

#### Platinum Sword

<table style="border-collapse: collapse;">
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/platinum_ingot.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td rowspan="3" align="center" width="44" style="vertical-align: middle;"><span style="font-size: 32px;">➞</span></td>
<td rowspan="3" align="center" style="vertical-align: middle; white-space: normal;"><img src="src/main/resources/assets/uplift/textures/items/platinum_sword.png" width="48" height="48" style="image-rendering: pixelated;"></td>
</tr>
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/platinum_ingot.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
</tr>
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/stick.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
</tr>
</table>

> **Crafting (Shaped):** 2 Platinum Ingot + 1 Stick ➞ 1 Platinum Sword

#### Platinum Pickaxe

<table style="border-collapse: collapse;">
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/platinum_ingot.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/platinum_ingot.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/platinum_ingot.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td rowspan="3" align="center" width="44" style="vertical-align: middle;"><span style="font-size: 32px;">➞</span></td>
<td rowspan="3" align="center" style="vertical-align: middle; white-space: normal;"><img src="src/main/resources/assets/uplift/textures/items/platinum_pickaxe.png" width="48" height="48" style="image-rendering: pixelated;"></td>
</tr>
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/stick.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
</tr>
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/stick.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
</tr>
</table>

> **Crafting (Shaped):** 3 Platinum Ingot + 2 Sticks ➞ 1 Platinum Pickaxe

#### Platinum Axe

<table style="border-collapse: collapse;">
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/platinum_ingot.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/platinum_ingot.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td rowspan="3" align="center" width="44" style="vertical-align: middle;"><span style="font-size: 32px;">➞</span></td>
<td rowspan="3" align="center" style="vertical-align: middle; white-space: normal;"><img src="src/main/resources/assets/uplift/textures/items/platinum_axe.png" width="48" height="48" style="image-rendering: pixelated;"></td>
</tr>
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/platinum_ingot.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/stick.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
</tr>
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/stick.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
</tr>
</table>

> **Crafting (Shaped):** 3 Platinum Ingot + 2 Sticks ➞ 1 Platinum Axe

#### Platinum Shovel

<table style="border-collapse: collapse;">
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/platinum_ingot.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td rowspan="3" align="center" width="44" style="vertical-align: middle;"><span style="font-size: 32px;">➞</span></td>
<td rowspan="3" align="center" style="vertical-align: middle; white-space: normal;"><img src="src/main/resources/assets/uplift/textures/items/platinum_shovel.png" width="48" height="48" style="image-rendering: pixelated;"></td>
</tr>
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/stick.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
</tr>
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/stick.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
</tr>
</table>

> **Crafting (Shaped):** 1 Platinum Ingot + 2 Sticks ➞ 1 Platinum Shovel

#### Platinum Hoe

<table style="border-collapse: collapse;">
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/platinum_ingot.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/platinum_ingot.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td rowspan="3" align="center" width="44" style="vertical-align: middle;"><span style="font-size: 32px;">➞</span></td>
<td rowspan="3" align="center" style="vertical-align: middle; white-space: normal;"><img src="src/main/resources/assets/uplift/textures/items/platinum_hoe.png" width="48" height="48" style="image-rendering: pixelated;"></td>
</tr>
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/stick.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
</tr>
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/stick.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
</tr>
</table>

> **Crafting (Shaped):** 2 Platinum Ingot + 2 Sticks ➞ 1 Platinum Hoe

#### Platinum Helmet

<table style="border-collapse: collapse;">
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/platinum_ingot.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/platinum_ingot.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/platinum_ingot.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td rowspan="3" align="center" width="44" style="vertical-align: middle;"><span style="font-size: 32px;">➞</span></td>
<td rowspan="3" align="center" style="vertical-align: middle; white-space: normal;"><img src="src/main/resources/assets/uplift/textures/items/platinum_helmet.png" width="48" height="48" style="image-rendering: pixelated;"></td>
</tr>
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/platinum_ingot.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/platinum_ingot.png" width="32" height="32" style="image-rendering: pixelated;"></td>
</tr>
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
</tr>
</table>

> **Crafting (Shaped):** 5 Platinum Ingot ➞ 1 Platinum Helmet

#### Platinum Chestplate

<table style="border-collapse: collapse;">
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/platinum_ingot.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/platinum_ingot.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td rowspan="3" align="center" width="44" style="vertical-align: middle;"><span style="font-size: 32px;">➞</span></td>
<td rowspan="3" align="center" style="vertical-align: middle; white-space: normal;"><img src="src/main/resources/assets/uplift/textures/items/platinum_chestplate.png" width="48" height="48" style="image-rendering: pixelated;"></td>
</tr>
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/platinum_ingot.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/platinum_ingot.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/platinum_ingot.png" width="32" height="32" style="image-rendering: pixelated;"></td>
</tr>
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/platinum_ingot.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/platinum_ingot.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/platinum_ingot.png" width="32" height="32" style="image-rendering: pixelated;"></td>
</tr>
</table>

> **Crafting (Shaped):** 8 Platinum Ingot ➞ 1 Platinum Chestplate

#### Platinum Leggings

<table style="border-collapse: collapse;">
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/platinum_ingot.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/platinum_ingot.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/platinum_ingot.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td rowspan="3" align="center" width="44" style="vertical-align: middle;"><span style="font-size: 32px;">➞</span></td>
<td rowspan="3" align="center" style="vertical-align: middle; white-space: normal;"><img src="src/main/resources/assets/uplift/textures/items/platinum_leggings.png" width="48" height="48" style="image-rendering: pixelated;"></td>
</tr>
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/platinum_ingot.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/platinum_ingot.png" width="32" height="32" style="image-rendering: pixelated;"></td>
</tr>
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/platinum_ingot.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/platinum_ingot.png" width="32" height="32" style="image-rendering: pixelated;"></td>
</tr>
</table>

> **Crafting (Shaped):** 7 Platinum Ingot ➞ 1 Platinum Leggings

#### Platinum Boots

<table style="border-collapse: collapse;">
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/platinum_ingot.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/platinum_ingot.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td rowspan="3" align="center" width="44" style="vertical-align: middle;"><span style="font-size: 32px;">➞</span></td>
<td rowspan="3" align="center" style="vertical-align: middle; white-space: normal;"><img src="src/main/resources/assets/uplift/textures/items/platinum_boots.png" width="48" height="48" style="image-rendering: pixelated;"></td>
</tr>
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/platinum_ingot.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/platinum_ingot.png" width="32" height="32" style="image-rendering: pixelated;"></td>
</tr>
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
</tr>
</table>

> **Crafting (Shaped):** 4 Platinum Ingot ➞ 1 Platinum Boots

#### Block of Platinum

<table style="border-collapse: collapse;">
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/platinum_ingot.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/platinum_ingot.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/platinum_ingot.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td rowspan="3" align="center" width="44" style="vertical-align: middle;"><span style="font-size: 32px;">➞</span></td>
<td rowspan="3" align="center" style="vertical-align: middle; white-space: normal;"><img src="src/main/resources/assets/uplift/textures/blocks/platinum_block.png" width="48" height="48" style="image-rendering: pixelated;"></td>
</tr>
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/platinum_ingot.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/platinum_ingot.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/platinum_ingot.png" width="32" height="32" style="image-rendering: pixelated;"></td>
</tr>
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/platinum_ingot.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/platinum_ingot.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/platinum_ingot.png" width="32" height="32" style="image-rendering: pixelated;"></td>
</tr>
</table>

> **Crafting (Shaped):** 9 Platinum Ingot ➞ 1 Block of Platinum

#### Platinum Ingot from Block

<table style="border-collapse: collapse;">
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/blocks/platinum_block.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" width="44" style="vertical-align: middle;"><span style="font-size: 32px;">➞</span></td>
<td align="center" style="vertical-align: middle; white-space: normal;"><img src="src/main/resources/assets/uplift/textures/items/platinum_ingot.png" width="48" height="48" style="image-rendering: pixelated;"></td>
</tr>
</table>

> **Crafting (Shapeless):** 1 Block of Platinum ➞ 9 Platinum Ingot

#### Platinum Ingot from Nuggets

<table style="border-collapse: collapse;">
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/platinum_nugget.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/platinum_nugget.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/platinum_nugget.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td rowspan="3" align="center" width="44" style="vertical-align: middle;"><span style="font-size: 32px;">➞</span></td>
<td rowspan="3" align="center" style="vertical-align: middle; white-space: normal;"><img src="src/main/resources/assets/uplift/textures/items/platinum_ingot.png" width="48" height="48" style="image-rendering: pixelated;"></td>
</tr>
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/platinum_nugget.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/platinum_nugget.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/platinum_nugget.png" width="32" height="32" style="image-rendering: pixelated;"></td>
</tr>
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/platinum_nugget.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/platinum_nugget.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/platinum_nugget.png" width="32" height="32" style="image-rendering: pixelated;"></td>
</tr>
</table>

> **Crafting (Shaped):** 9 Platinum Nuggets ➞ 1 Platinum Ingot

#### Platinum Nuggets from Ingot

<table style="border-collapse: collapse;">
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/platinum_ingot.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" width="44" style="vertical-align: middle;"><span style="font-size: 32px;">➞</span></td>
<td align="center" style="vertical-align: middle; white-space: normal;"><img src="src/main/resources/assets/uplift/textures/items/platinum_nugget.png" width="48" height="48" style="image-rendering: pixelated;"></td>
</tr>
</table>

> **Crafting (Shapeless):** 1 Platinum Ingot ➞ 9 Platinum Nuggets

---

### Uranium Recipes

#### Uranium Sword

<table style="border-collapse: collapse;">
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/uranium_ingot.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td rowspan="3" align="center" width="44" style="vertical-align: middle;"><span style="font-size: 32px;">➞</span></td>
<td rowspan="3" align="center" style="vertical-align: middle; white-space: normal;"><img src="src/main/resources/assets/uplift/textures/items/uranium_sword.png" width="48" height="48" style="image-rendering: pixelated;"></td>
</tr>
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/uranium_ingot.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
</tr>
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/stick.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
</tr>
</table>

> **Crafting (Shaped):** 2 Uranium Ingot + 1 Stick ➞ 1 Uranium Sword

#### Uranium Pickaxe

<table style="border-collapse: collapse;">
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/uranium_ingot.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/uranium_ingot.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/uranium_ingot.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td rowspan="3" align="center" width="44" style="vertical-align: middle;"><span style="font-size: 32px;">➞</span></td>
<td rowspan="3" align="center" style="vertical-align: middle; white-space: normal;"><img src="src/main/resources/assets/uplift/textures/items/uranium_pickaxe.png" width="48" height="48" style="image-rendering: pixelated;"></td>
</tr>
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/stick.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
</tr>
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/stick.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
</tr>
</table>

> **Crafting (Shaped):** 3 Uranium Ingot + 2 Sticks ➞ 1 Uranium Pickaxe

#### Uranium Axe

<table style="border-collapse: collapse;">
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/uranium_ingot.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/uranium_ingot.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td rowspan="3" align="center" width="44" style="vertical-align: middle;"><span style="font-size: 32px;">➞</span></td>
<td rowspan="3" align="center" style="vertical-align: middle; white-space: normal;"><img src="src/main/resources/assets/uplift/textures/items/uranium_axe.png" width="48" height="48" style="image-rendering: pixelated;"></td>
</tr>
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/uranium_ingot.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/stick.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
</tr>
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/stick.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
</tr>
</table>

> **Crafting (Shaped):** 3 Uranium Ingot + 2 Sticks ➞ 1 Uranium Axe

#### Uranium Shovel

<table style="border-collapse: collapse;">
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/uranium_ingot.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td rowspan="3" align="center" width="44" style="vertical-align: middle;"><span style="font-size: 32px;">➞</span></td>
<td rowspan="3" align="center" style="vertical-align: middle; white-space: normal;"><img src="src/main/resources/assets/uplift/textures/items/uranium_shovel.png" width="48" height="48" style="image-rendering: pixelated;"></td>
</tr>
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/stick.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
</tr>
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/stick.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
</tr>
</table>

> **Crafting (Shaped):** 1 Uranium Ingot + 2 Sticks ➞ 1 Uranium Shovel

#### Uranium Hoe

<table style="border-collapse: collapse;">
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/uranium_ingot.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/uranium_ingot.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td rowspan="3" align="center" width="44" style="vertical-align: middle;"><span style="font-size: 32px;">➞</span></td>
<td rowspan="3" align="center" style="vertical-align: middle; white-space: normal;"><img src="src/main/resources/assets/uplift/textures/items/uranium_hoe.png" width="48" height="48" style="image-rendering: pixelated;"></td>
</tr>
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/stick.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
</tr>
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/stick.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
</tr>
</table>

> **Crafting (Shaped):** 2 Uranium Ingot + 2 Sticks ➞ 1 Uranium Hoe

#### Uranium Helmet

<table style="border-collapse: collapse;">
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/uranium_ingot.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/uranium_ingot.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/uranium_ingot.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td rowspan="3" align="center" width="44" style="vertical-align: middle;"><span style="font-size: 32px;">➞</span></td>
<td rowspan="3" align="center" style="vertical-align: middle; white-space: normal;"><img src="src/main/resources/assets/uplift/textures/items/uranium_helmet.png" width="48" height="48" style="image-rendering: pixelated;"></td>
</tr>
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/uranium_ingot.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/uranium_ingot.png" width="32" height="32" style="image-rendering: pixelated;"></td>
</tr>
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
</tr>
</table>

> **Crafting (Shaped):** 5 Uranium Ingot ➞ 1 Uranium Helmet

#### Uranium Chestplate

<table style="border-collapse: collapse;">
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/uranium_ingot.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/uranium_ingot.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td rowspan="3" align="center" width="44" style="vertical-align: middle;"><span style="font-size: 32px;">➞</span></td>
<td rowspan="3" align="center" style="vertical-align: middle; white-space: normal;"><img src="src/main/resources/assets/uplift/textures/items/uranium_chestplate.png" width="48" height="48" style="image-rendering: pixelated;"></td>
</tr>
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/uranium_ingot.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/uranium_ingot.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/uranium_ingot.png" width="32" height="32" style="image-rendering: pixelated;"></td>
</tr>
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/uranium_ingot.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/uranium_ingot.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/uranium_ingot.png" width="32" height="32" style="image-rendering: pixelated;"></td>
</tr>
</table>

> **Crafting (Shaped):** 8 Uranium Ingot ➞ 1 Uranium Chestplate

#### Uranium Leggings

<table style="border-collapse: collapse;">
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/uranium_ingot.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/uranium_ingot.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/uranium_ingot.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td rowspan="3" align="center" width="44" style="vertical-align: middle;"><span style="font-size: 32px;">➞</span></td>
<td rowspan="3" align="center" style="vertical-align: middle; white-space: normal;"><img src="src/main/resources/assets/uplift/textures/items/uranium_leggings.png" width="48" height="48" style="image-rendering: pixelated;"></td>
</tr>
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/uranium_ingot.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/uranium_ingot.png" width="32" height="32" style="image-rendering: pixelated;"></td>
</tr>
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/uranium_ingot.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/uranium_ingot.png" width="32" height="32" style="image-rendering: pixelated;"></td>
</tr>
</table>

> **Crafting (Shaped):** 7 Uranium Ingot ➞ 1 Uranium Leggings

#### Uranium Boots

<table style="border-collapse: collapse;">
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/uranium_ingot.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/uranium_ingot.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td rowspan="3" align="center" width="44" style="vertical-align: middle;"><span style="font-size: 32px;">➞</span></td>
<td rowspan="3" align="center" style="vertical-align: middle; white-space: normal;"><img src="src/main/resources/assets/uplift/textures/items/uranium_boots.png" width="48" height="48" style="image-rendering: pixelated;"></td>
</tr>
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/uranium_ingot.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/uranium_ingot.png" width="32" height="32" style="image-rendering: pixelated;"></td>
</tr>
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
</tr>
</table>

> **Crafting (Shaped):** 4 Uranium Ingot ➞ 1 Uranium Boots

#### Block of Uranium

<table style="border-collapse: collapse;">
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/uranium_ingot.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/uranium_ingot.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/uranium_ingot.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td rowspan="3" align="center" width="44" style="vertical-align: middle;"><span style="font-size: 32px;">➞</span></td>
<td rowspan="3" align="center" style="vertical-align: middle; white-space: normal;"><img src="src/main/resources/assets/uplift/textures/blocks/uranium_block.png" width="48" height="48" style="image-rendering: pixelated;"></td>
</tr>
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/uranium_ingot.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/uranium_ingot.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/uranium_ingot.png" width="32" height="32" style="image-rendering: pixelated;"></td>
</tr>
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/uranium_ingot.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/uranium_ingot.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/uranium_ingot.png" width="32" height="32" style="image-rendering: pixelated;"></td>
</tr>
</table>

> **Crafting (Shaped):** 9 Uranium Ingot ➞ 1 Block of Uranium

#### Uranium Ingot from Block

<table style="border-collapse: collapse;">
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/blocks/uranium_block.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" width="44" style="vertical-align: middle;"><span style="font-size: 32px;">➞</span></td>
<td align="center" style="vertical-align: middle; white-space: normal;"><img src="src/main/resources/assets/uplift/textures/items/uranium_ingot.png" width="48" height="48" style="image-rendering: pixelated;"></td>
</tr>
</table>

> **Crafting (Shapeless):** 1 Block of Uranium ➞ 9 Uranium Ingot

#### Uranium Ingot from Nuggets

<table style="border-collapse: collapse;">
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/uranium_nugget.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/uranium_nugget.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/uranium_nugget.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td rowspan="3" align="center" width="44" style="vertical-align: middle;"><span style="font-size: 32px;">➞</span></td>
<td rowspan="3" align="center" style="vertical-align: middle; white-space: normal;"><img src="src/main/resources/assets/uplift/textures/items/uranium_ingot.png" width="48" height="48" style="image-rendering: pixelated;"></td>
</tr>
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/uranium_nugget.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/uranium_nugget.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/uranium_nugget.png" width="32" height="32" style="image-rendering: pixelated;"></td>
</tr>
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/uranium_nugget.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/uranium_nugget.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/uranium_nugget.png" width="32" height="32" style="image-rendering: pixelated;"></td>
</tr>
</table>

> **Crafting (Shaped):** 9 Uranium Nuggets ➞ 1 Uranium Ingot

#### Uranium Nuggets from Ingot

<table style="border-collapse: collapse;">
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/uranium_ingot.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" width="44" style="vertical-align: middle;"><span style="font-size: 32px;">➞</span></td>
<td align="center" style="vertical-align: middle; white-space: normal;"><img src="src/main/resources/assets/uplift/textures/items/uranium_nugget.png" width="48" height="48" style="image-rendering: pixelated;"></td>
</tr>
</table>

> **Crafting (Shapeless):** 1 Uranium Ingot ➞ 9 Uranium Nuggets

---

### Bloodstone Recipes

#### Bloodstone Helmet

<table style="border-collapse: collapse;">
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/bloodstone.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/bloodstone.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/bloodstone.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td rowspan="3" align="center" width="44" style="vertical-align: middle;"><span style="font-size: 32px;">➞</span></td>
<td rowspan="3" align="center" style="vertical-align: middle; white-space: normal;"><img src="src/main/resources/assets/uplift/textures/items/bloodstone_helmet.png" width="48" height="48" style="image-rendering: pixelated;"></td>
</tr>
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/bloodstone.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/bloodstone.png" width="32" height="32" style="image-rendering: pixelated;"></td>
</tr>
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
</tr>
</table>

> **Crafting (Shaped):** 5 Bloodstone ➞ 1 Bloodstone Helmet

#### Bloodstone Chestplate

<table style="border-collapse: collapse;">
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/bloodstone.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/bloodstone.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td rowspan="3" align="center" width="44" style="vertical-align: middle;"><span style="font-size: 32px;">➞</span></td>
<td rowspan="3" align="center" style="vertical-align: middle; white-space: normal;"><img src="src/main/resources/assets/uplift/textures/items/bloodstone_chestplate.png" width="48" height="48" style="image-rendering: pixelated;"></td>
</tr>
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/bloodstone.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/bloodstone.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/bloodstone.png" width="32" height="32" style="image-rendering: pixelated;"></td>
</tr>
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/bloodstone.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/bloodstone.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/bloodstone.png" width="32" height="32" style="image-rendering: pixelated;"></td>
</tr>
</table>

> **Crafting (Shaped):** 8 Bloodstone ➞ 1 Bloodstone Chestplate

#### Bloodstone Leggings

<table style="border-collapse: collapse;">
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/bloodstone.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/bloodstone.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/bloodstone.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td rowspan="3" align="center" width="44" style="vertical-align: middle;"><span style="font-size: 32px;">➞</span></td>
<td rowspan="3" align="center" style="vertical-align: middle; white-space: normal;"><img src="src/main/resources/assets/uplift/textures/items/bloodstone_leggings.png" width="48" height="48" style="image-rendering: pixelated;"></td>
</tr>
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/bloodstone.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/bloodstone.png" width="32" height="32" style="image-rendering: pixelated;"></td>
</tr>
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/bloodstone.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/bloodstone.png" width="32" height="32" style="image-rendering: pixelated;"></td>
</tr>
</table>

> **Crafting (Shaped):** 7 Bloodstone ➞ 1 Bloodstone Leggings

#### Bloodstone Boots

<table style="border-collapse: collapse;">
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/bloodstone.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/bloodstone.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td rowspan="3" align="center" width="44" style="vertical-align: middle;"><span style="font-size: 32px;">➞</span></td>
<td rowspan="3" align="center" style="vertical-align: middle; white-space: normal;"><img src="src/main/resources/assets/uplift/textures/items/bloodstone_boots.png" width="48" height="48" style="image-rendering: pixelated;"></td>
</tr>
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/bloodstone.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/bloodstone.png" width="32" height="32" style="image-rendering: pixelated;"></td>
</tr>
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
</tr>
</table>

> **Crafting (Shaped):** 4 Bloodstone ➞ 1 Bloodstone Boots

#### Block of Bloodstone

<table style="border-collapse: collapse;">
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/bloodstone.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/bloodstone.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/bloodstone.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td rowspan="3" align="center" width="44" style="vertical-align: middle;"><span style="font-size: 32px;">➞</span></td>
<td rowspan="3" align="center" style="vertical-align: middle; white-space: normal;"><img src="src/main/resources/assets/uplift/textures/blocks/bloodstone_block.png" width="48" height="48" style="image-rendering: pixelated;"></td>
</tr>
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/bloodstone.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/bloodstone.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/bloodstone.png" width="32" height="32" style="image-rendering: pixelated;"></td>
</tr>
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/bloodstone.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/bloodstone.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/bloodstone.png" width="32" height="32" style="image-rendering: pixelated;"></td>
</tr>
</table>

> **Crafting (Shaped):** 9 Bloodstone ➞ 1 Block of Bloodstone

#### Bloodstone from Block

<table style="border-collapse: collapse;">
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/blocks/bloodstone_block.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" width="44" style="vertical-align: middle;"><span style="font-size: 32px;">➞</span></td>
<td align="center" style="vertical-align: middle; white-space: normal;"><img src="src/main/resources/assets/uplift/textures/items/bloodstone.png" width="48" height="48" style="image-rendering: pixelated;"></td>
</tr>
</table>

> **Crafting (Shapeless):** 1 Block of Bloodstone ➞ 9 Bloodstone

---

### Ender Recipes

#### Ender Sword

<table style="border-collapse: collapse;">
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/ender_gem.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td rowspan="3" align="center" width="44" style="vertical-align: middle;"><span style="font-size: 32px;">➞</span></td>
<td rowspan="3" align="center" style="vertical-align: middle; white-space: normal;"><img src="src/main/resources/assets/uplift/textures/items/ender_sword.png" width="48" height="48" style="image-rendering: pixelated;"></td>
</tr>
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/ender_gem.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
</tr>
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/stick.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
</tr>
</table>

> **Crafting (Shaped):** 2 Ender Gem + 1 Stick ➞ 1 Ender Sword

#### Ender Pickaxe

<table style="border-collapse: collapse;">
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/ender_gem.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/ender_gem.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/ender_gem.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td rowspan="3" align="center" width="44" style="vertical-align: middle;"><span style="font-size: 32px;">➞</span></td>
<td rowspan="3" align="center" style="vertical-align: middle; white-space: normal;"><img src="src/main/resources/assets/uplift/textures/items/ender_pickaxe.png" width="48" height="48" style="image-rendering: pixelated;"></td>
</tr>
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/stick.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
</tr>
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/stick.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
</tr>
</table>

> **Crafting (Shaped):** 3 Ender Gem + 2 Sticks ➞ 1 Ender Pickaxe

#### Ender Axe

<table style="border-collapse: collapse;">
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/ender_gem.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/ender_gem.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td rowspan="3" align="center" width="44" style="vertical-align: middle;"><span style="font-size: 32px;">➞</span></td>
<td rowspan="3" align="center" style="vertical-align: middle; white-space: normal;"><img src="src/main/resources/assets/uplift/textures/items/ender_axe.png" width="48" height="48" style="image-rendering: pixelated;"></td>
</tr>
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/ender_gem.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/stick.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
</tr>
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/stick.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
</tr>
</table>

> **Crafting (Shaped):** 3 Ender Gem + 2 Sticks ➞ 1 Ender Axe

#### Ender Shovel

<table style="border-collapse: collapse;">
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/ender_gem.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td rowspan="3" align="center" width="44" style="vertical-align: middle;"><span style="font-size: 32px;">➞</span></td>
<td rowspan="3" align="center" style="vertical-align: middle; white-space: normal;"><img src="src/main/resources/assets/uplift/textures/items/ender_shovel.png" width="48" height="48" style="image-rendering: pixelated;"></td>
</tr>
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/stick.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
</tr>
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/stick.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
</tr>
</table>

> **Crafting (Shaped):** 1 Ender Gem + 2 Sticks ➞ 1 Ender Shovel

#### Ender Hoe

<table style="border-collapse: collapse;">
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/ender_gem.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/ender_gem.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td rowspan="3" align="center" width="44" style="vertical-align: middle;"><span style="font-size: 32px;">➞</span></td>
<td rowspan="3" align="center" style="vertical-align: middle; white-space: normal;"><img src="src/main/resources/assets/uplift/textures/items/ender_hoe.png" width="48" height="48" style="image-rendering: pixelated;"></td>
</tr>
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/stick.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
</tr>
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/stick.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
</tr>
</table>

> **Crafting (Shaped):** 2 Ender Gem + 2 Sticks ➞ 1 Ender Hoe

#### Ender Helmet

<table style="border-collapse: collapse;">
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/ender_gem.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/ender_gem.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/ender_gem.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td rowspan="3" align="center" width="44" style="vertical-align: middle;"><span style="font-size: 32px;">➞</span></td>
<td rowspan="3" align="center" style="vertical-align: middle; white-space: normal;"><img src="src/main/resources/assets/uplift/textures/items/ender_helmet.png" width="48" height="48" style="image-rendering: pixelated;"></td>
</tr>
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/ender_gem.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/ender_gem.png" width="32" height="32" style="image-rendering: pixelated;"></td>
</tr>
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
</tr>
</table>

> **Crafting (Shaped):** 5 Ender Gem ➞ 1 Ender Helmet

#### Ender Chestplate

<table style="border-collapse: collapse;">
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/ender_gem.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/ender_gem.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td rowspan="3" align="center" width="44" style="vertical-align: middle;"><span style="font-size: 32px;">➞</span></td>
<td rowspan="3" align="center" style="vertical-align: middle; white-space: normal;"><img src="src/main/resources/assets/uplift/textures/items/ender_chestplate.png" width="48" height="48" style="image-rendering: pixelated;"></td>
</tr>
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/ender_gem.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/ender_gem.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/ender_gem.png" width="32" height="32" style="image-rendering: pixelated;"></td>
</tr>
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/ender_gem.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/ender_gem.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/ender_gem.png" width="32" height="32" style="image-rendering: pixelated;"></td>
</tr>
</table>

> **Crafting (Shaped):** 8 Ender Gem ➞ 1 Ender Chestplate

#### Ender Leggings

<table style="border-collapse: collapse;">
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/ender_gem.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/ender_gem.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/ender_gem.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td rowspan="3" align="center" width="44" style="vertical-align: middle;"><span style="font-size: 32px;">➞</span></td>
<td rowspan="3" align="center" style="vertical-align: middle; white-space: normal;"><img src="src/main/resources/assets/uplift/textures/items/ender_leggings.png" width="48" height="48" style="image-rendering: pixelated;"></td>
</tr>
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/ender_gem.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/ender_gem.png" width="32" height="32" style="image-rendering: pixelated;"></td>
</tr>
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/ender_gem.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/ender_gem.png" width="32" height="32" style="image-rendering: pixelated;"></td>
</tr>
</table>

> **Crafting (Shaped):** 7 Ender Gem ➞ 1 Ender Leggings

#### Ender Boots

<table style="border-collapse: collapse;">
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/ender_gem.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/ender_gem.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td rowspan="3" align="center" width="44" style="vertical-align: middle;"><span style="font-size: 32px;">➞</span></td>
<td rowspan="3" align="center" style="vertical-align: middle; white-space: normal;"><img src="src/main/resources/assets/uplift/textures/items/ender_boots.png" width="48" height="48" style="image-rendering: pixelated;"></td>
</tr>
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/ender_gem.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/ender_gem.png" width="32" height="32" style="image-rendering: pixelated;"></td>
</tr>
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
</tr>
</table>

> **Crafting (Shaped):** 4 Ender Gem ➞ 1 Ender Boots

#### Block of Ender

<table style="border-collapse: collapse;">
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/ender_gem.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/ender_gem.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/ender_gem.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td rowspan="3" align="center" width="44" style="vertical-align: middle;"><span style="font-size: 32px;">➞</span></td>
<td rowspan="3" align="center" style="vertical-align: middle; white-space: normal;"><img src="src/main/resources/assets/uplift/textures/blocks/ender_block.png" width="48" height="48" style="image-rendering: pixelated;"></td>
</tr>
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/ender_gem.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/ender_gem.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/ender_gem.png" width="32" height="32" style="image-rendering: pixelated;"></td>
</tr>
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/ender_gem.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/ender_gem.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/ender_gem.png" width="32" height="32" style="image-rendering: pixelated;"></td>
</tr>
</table>

> **Crafting (Shaped):** 9 Ender Gem ➞ 1 Block of Ender

#### Ender Gem from Block

<table style="border-collapse: collapse;">
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/blocks/ender_block.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" width="44" style="vertical-align: middle;"><span style="font-size: 32px;">➞</span></td>
<td align="center" style="vertical-align: middle; white-space: normal;"><img src="src/main/resources/assets/uplift/textures/items/ender_gem.png" width="48" height="48" style="image-rendering: pixelated;"></td>
</tr>
</table>

> **Crafting (Shapeless):** 1 Block of Ender ➞ 9 Ender Gem

#### Ender Torch

<table style="border-collapse: collapse;">
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/ender_gem.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td rowspan="3" align="center" width="44" style="vertical-align: middle;"><span style="font-size: 32px;">➞</span></td>
<td rowspan="3" align="center" style="vertical-align: middle; white-space: normal;"><img src="src/main/resources/assets/uplift/textures/items/ender_torch.png" width="48" height="48" style="image-rendering: pixelated;"></td>
</tr>
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/coal.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
</tr>
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/stick.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
</tr>
</table>

> **Crafting (Shaped):** Ender Gem above Coal above Stick ➞ 8 Ender Torches

---

### Tasmanite Recipes

#### Block of Tasmanite

<table style="border-collapse: collapse;">
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/tasmanite.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/tasmanite.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/tasmanite.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td rowspan="3" align="center" width="44" style="vertical-align: middle;"><span style="font-size: 32px;">➞</span></td>
<td rowspan="3" align="center" style="vertical-align: middle; white-space: normal;"><img src="src/main/resources/assets/uplift/textures/blocks/tasmanite_block.png" width="48" height="48" style="image-rendering: pixelated;"></td>
</tr>
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/tasmanite.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/tasmanite.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/tasmanite.png" width="32" height="32" style="image-rendering: pixelated;"></td>
</tr>
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/tasmanite.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/tasmanite.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/tasmanite.png" width="32" height="32" style="image-rendering: pixelated;"></td>
</tr>
</table>

> **Crafting (Shaped):** 9 Tasmanite ➞ 1 Block of Tasmanite

#### Tasmanite from Block

<table style="border-collapse: collapse;">
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/blocks/tasmanite_block.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" width="44" style="vertical-align: middle;"><span style="font-size: 32px;">➞</span></td>
<td align="center" style="vertical-align: middle; white-space: normal;"><img src="src/main/resources/assets/uplift/textures/items/tasmanite.png" width="48" height="48" style="image-rendering: pixelated;"></td>
</tr>
</table>

> **Crafting (Shapeless):** 1 Block of Tasmanite ➞ 9 Tasmanite

#### Tasmanite Torch

<table style="border-collapse: collapse;">
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/tasmanite.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td rowspan="3" align="center" width="44" style="vertical-align: middle;"><span style="font-size: 32px;">➞</span></td>
<td rowspan="3" align="center" style="vertical-align: middle; white-space: normal;"><img src="src/main/resources/assets/uplift/textures/items/tasmanite_torch.png" width="48" height="48" style="image-rendering: pixelated;"></td>
</tr>
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/stick.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
</tr>
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
</tr>
</table>

> **Crafting (Shaped):** 1 Tasmanite + 1 Stick ➞ 4 Tasmanite Torches

---

### Opal Recipes

#### Block of Opal

<table style="border-collapse: collapse;">
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/opal.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/opal.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/opal.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td rowspan="3" align="center" width="44" style="vertical-align: middle;"><span style="font-size: 32px;">➞</span></td>
<td rowspan="3" align="center" style="vertical-align: middle; white-space: normal;"><img src="src/main/resources/assets/uplift/textures/blocks/opal_block.png" width="48" height="48" style="image-rendering: pixelated;"></td>
</tr>
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/opal.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/opal.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/opal.png" width="32" height="32" style="image-rendering: pixelated;"></td>
</tr>
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/opal.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/opal.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/opal.png" width="32" height="32" style="image-rendering: pixelated;"></td>
</tr>
</table>

> **Crafting (Shaped):** 9 Opal ➞ 1 Block of Opal

#### Opal from Block

<table style="border-collapse: collapse;">
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/blocks/opal_block.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" width="44" style="vertical-align: middle;"><span style="font-size: 32px;">➞</span></td>
<td align="center" style="vertical-align: middle; white-space: normal;"><img src="src/main/resources/assets/uplift/textures/items/opal.png" width="48" height="48" style="image-rendering: pixelated;"></td>
</tr>
</table>

> **Crafting (Shapeless):** 1 Block of Opal ➞ 9 Opal

#### Opal Torch

<table style="border-collapse: collapse;">
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/opal.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td rowspan="3" align="center" width="44" style="vertical-align: middle;"><span style="font-size: 32px;">➞</span></td>
<td rowspan="3" align="center" style="vertical-align: middle; white-space: normal;"><img src="src/main/resources/assets/uplift/textures/items/opal_torch.png" width="48" height="48" style="image-rendering: pixelated;"></td>
</tr>
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/coal.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
</tr>
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/stick.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
</tr>
</table>

> **Crafting (Shaped):** Opal above Coal above Stick ➞ 8 Opal Torches

---

### Rose Gold Recipes

#### Rose Golden Sword

<table style="border-collapse: collapse;">
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/rose_gold_ingot.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td rowspan="3" align="center" width="44" style="vertical-align: middle;"><span style="font-size: 32px;">➞</span></td>
<td rowspan="3" align="center" style="vertical-align: middle; white-space: normal;"><img src="src/main/resources/assets/uplift/textures/items/rose_gold_sword.png" width="48" height="48" style="image-rendering: pixelated;"></td>
</tr>
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/rose_gold_ingot.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
</tr>
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/stick.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
</tr>
</table>

> **Crafting (Shaped):** 2 Rose Gold Ingots + 1 Stick ➞ 1 Rose Golden Sword

#### Rose Golden Axe

<table style="border-collapse: collapse;">
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/rose_gold_ingot.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/rose_gold_ingot.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td rowspan="3" align="center" width="44" style="vertical-align: middle;"><span style="font-size: 32px;">➞</span></td>
<td rowspan="3" align="center" style="vertical-align: middle; white-space: normal;"><img src="src/main/resources/assets/uplift/textures/items/rose_gold_axe.png" width="48" height="48" style="image-rendering: pixelated;"></td>
</tr>
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/rose_gold_ingot.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/stick.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
</tr>
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/stick.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="wiki/wiki_textures/blank.png" width="32" height="32" alt="" style="image-rendering: pixelated;"></td>
</tr>
</table>

> **Crafting (Shaped):** 3 Rose Gold Ingots + 2 Sticks ➞ 1 Rose Golden Axe

#### Block of Rose Gold

<table style="border-collapse: collapse;">
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/rose_gold_ingot.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/rose_gold_ingot.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/rose_gold_ingot.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td rowspan="3" align="center" width="44" style="vertical-align: middle;"><span style="font-size: 32px;">➞</span></td>
<td rowspan="3" align="center" style="vertical-align: middle; white-space: normal;"><img src="src/main/resources/assets/uplift/textures/blocks/rose_gold_block.png" width="48" height="48" style="image-rendering: pixelated;"></td>
</tr>
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/rose_gold_ingot.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/rose_gold_ingot.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/rose_gold_ingot.png" width="32" height="32" style="image-rendering: pixelated;"></td>
</tr>
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/rose_gold_ingot.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/rose_gold_ingot.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/rose_gold_ingot.png" width="32" height="32" style="image-rendering: pixelated;"></td>
</tr>
</table>

> **Crafting (Shaped):** 9 Rose Gold Ingots ➞ 1 Block of Rose Gold

#### Rose Gold Ingots from Block

<table style="border-collapse: collapse;">
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/blocks/rose_gold_block.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" width="44" style="vertical-align: middle;"><span style="font-size: 32px;">➞</span></td>
<td align="center" style="vertical-align: middle; white-space: normal;"><img src="src/main/resources/assets/uplift/textures/items/rose_gold_ingot.png" width="48" height="48" style="image-rendering: pixelated;"></td>
</tr>
</table>

> **Crafting (Shapeless):** 1 Block of Rose Gold ➞ 9 Rose Gold Ingots

#### Rose Gold Ingot from Nuggets

<table style="border-collapse: collapse;">
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/rose_gold_nugget.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/rose_gold_nugget.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/rose_gold_nugget.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td rowspan="3" align="center" width="44" style="vertical-align: middle;"><span style="font-size: 32px;">➞</span></td>
<td rowspan="3" align="center" style="vertical-align: middle; white-space: normal;"><img src="src/main/resources/assets/uplift/textures/items/rose_gold_ingot.png" width="48" height="48" style="image-rendering: pixelated;"></td>
</tr>
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/rose_gold_nugget.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/rose_gold_nugget.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/rose_gold_nugget.png" width="32" height="32" style="image-rendering: pixelated;"></td>
</tr>
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/rose_gold_nugget.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/rose_gold_nugget.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/rose_gold_nugget.png" width="32" height="32" style="image-rendering: pixelated;"></td>
</tr>
</table>

> **Crafting (Shaped):** 9 Rose Gold Nuggets ➞ 1 Rose Gold Ingot

#### Rose Gold Nuggets from Ingot

<table style="border-collapse: collapse;">
<tr>
<td align="center" style="border: 1px solid #555; width: 40px; height: 40px; padding: 4px;"><img src="src/main/resources/assets/uplift/textures/items/rose_gold_ingot.png" width="32" height="32" style="image-rendering: pixelated;"></td>
<td align="center" width="44" style="vertical-align: middle;"><span style="font-size: 32px;">➞</span></td>
<td align="center" style="vertical-align: middle; white-space: normal;"><img src="src/main/resources/assets/uplift/textures/items/rose_gold_nugget.png" width="48" height="48" style="image-rendering: pixelated;"></td>
</tr>
</table>

> **Crafting (Shapeless):** 1 Rose Gold Ingot ➞ 9 Rose Gold Nuggets

---

## Smelting

### Furnace Recipes

These recipes use a standard **Furnace** (vanilla mechanics — use coal/charcoal/etc. as fuel):

| Input                                                                                                                                   | Output                                                                                                                                           | XP  | Cook Time |
| --------------------------------------------------------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------------------------------------------------------------ | --- | --------- |
| <img src="src/main/resources/assets/uplift/textures/blocks/chrome_ore.png" width="28" style="image-rendering: pixelated;"> Chromium Ore | <img src="src/main/resources/assets/uplift/textures/items/chrome_ingot.png" width="28" style="image-rendering: pixelated;"> Chromium Ingot       | 0.7 | 200 ticks |
| Platinum Tools/Armor                                                                                                                    | <img src="src/main/resources/assets/uplift/textures/items/platinum_nugget.png" width="28" style="image-rendering: pixelated;"> Platinum Nugget   | 1.0 | 400 ticks |
| Chromium Tools/Armor                                                                                                                    | <img src="src/main/resources/assets/uplift/textures/items/chrome_nugget.png" width="28" style="image-rendering: pixelated;"> Chromium Nugget     | 0.7 | 200 ticks |
| Rose Gold Tools/Armor                                                                                                                   | <img src="src/main/resources/assets/uplift/textures/items/rose_gold_nugget.png" width="28" style="image-rendering: pixelated;"> Rose Gold Nugget | 0.8 | 200 ticks |
| Uranium Tools/Armor                                                                                                                     | <img src="src/main/resources/assets/uplift/textures/items/uranium_nugget.png" width="28" style="image-rendering: pixelated;"> Uranium Nugget     | 0.7 | 200 ticks |

---

### Smelter Recipes

These recipes use the custom <img src="src/main/resources/assets/uplift/textures/blocks/smelter_front.png" width="28" style="image-rendering: pixelated;"> **Smelter** block (must be lit with an Igniter). The Smelter stays lit for **200 smelts**.

| Input                                                                                                                                       | Output                                                                                                                                           | XP  | Cook Time |
| ------------------------------------------------------------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------------------------------------------------------------ | --- | --------- |
| <img src="wiki/wiki_textures/diamond.png" width="28" style="image-rendering: pixelated;"> Diamond                                           | <img src="src/main/resources/assets/uplift/textures/items/burning_dust.png" width="28" style="image-rendering: pixelated;"> Burning Diamond Dust | 3   | 150 ticks |
| <img src="src/main/resources/assets/uplift/textures/blocks/chrome_ore.png" width="28" style="image-rendering: pixelated;"> Chromium Ore     | <img src="src/main/resources/assets/uplift/textures/items/chrome_ingot.png" width="28" style="image-rendering: pixelated;"> Chromium Ingot       | 1   | 150 ticks |
| <img src="wiki/wiki_textures/gold_ore.png" width="28" style="image-rendering: pixelated;"> Gold Ore                                         | <img src="wiki/wiki_textures/gold_ingot.png" width="28" style="image-rendering: pixelated;"> Gold Ingot                                          | 1   | 150 ticks |
| <img src="wiki/wiki_textures/iron_ore.png" width="28" style="image-rendering: pixelated;"> Iron Ore                                         | <img src="wiki/wiki_textures/iron_ingot.png" width="28" style="image-rendering: pixelated;"> Iron Ingot                                          | 1   | 150 ticks |
| <img src="wiki/wiki_textures/obsidian.png" width="28" style="image-rendering: pixelated;"> Obsidian                                         | <img src="src/main/resources/assets/uplift/textures/items/obsidian_shard.png" width="28" style="image-rendering: pixelated;"> Obsidian Shard     | 2   | 150 ticks |
| <img src="src/main/resources/assets/uplift/textures/blocks/platinum_ore.png" width="28" style="image-rendering: pixelated;"> Platinum Ore   | <img src="src/main/resources/assets/uplift/textures/items/platinum_ingot.png" width="28" style="image-rendering: pixelated;"> Platinum Ingot     | 3   | 150 ticks |
| <img src="src/main/resources/assets/uplift/textures/blocks/rose_gold_ore.png" width="28" style="image-rendering: pixelated;"> Rose Gold Ore | <img src="src/main/resources/assets/uplift/textures/items/rose_gold_ingot.png" width="28" style="image-rendering: pixelated;"> Rose Gold Ingot   | 2   | 150 ticks |
| <img src="src/main/resources/assets/uplift/textures/blocks/uranium_ore.png" width="28" style="image-rendering: pixelated;"> Uranium Ore     | <img src="src/main/resources/assets/uplift/textures/items/uranium_ingot.png" width="28" style="image-rendering: pixelated;"> Uranium Ingot       | 2   | 150 ticks |

---

### Fuel Values

| Fuel Item       | Burn Time (ticks) | Items Smelted | Comparison |
| --------------- | ----------------- | ------------- | ---------- |
| Coal (vanilla)  | 1,600             | 8             | Baseline   |
| Tasmanite       | 2,400             | 12            | 1.5× Coal  |
| Burning Diamond | 40,000            | 200           | 25× Coal   |

> Burning Diamond Block and Tasmanite Block can also be used as fuel (block item variants).

---

_Mining Uplift — A complete overhaul of underground mining, crafting, and exploration._
