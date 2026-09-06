# Advancements & Achievements (26.2)

| System Parameter | Status |
| :--- | :--- |
| **Custom Advancement JSONs** | **None** (Intentionally Omitted by Design) |
| **Scope** | Pure Gameplay Modifier / Instant Gratification Utility |
| **Vanilla Advancements** | 100% Compatible with all vanilla Story & Husbandry advancements |

---

## 📌 Absence Policy Declaration

In strict compliance with the project's **Instant Gratification (IG)** philosophy and minimal footprint architecture:
* Durability Multiplier contains **zero custom advancement JSON trees**.
* The mod does **not** register custom triggers, criteria, or toast notifications.

---

## 🌾 Vanilla Advancement Compatibility

All vanilla advancements continue to trigger naturally:
* Mining diamond ore triggers **"Diamonds!"** (`minecraft:story/mine_diamond`).
* Flying through an Elytra ring triggers **"Sky's the Limit"** (`minecraft:end/elytra`).
* Blocking damage with a shield triggers **"Not Today, Thank You"** (`minecraft:story/deflect_arrow`).

Because item durability reduction occurs transparently within `ItemStack.hurtAndBreak`, advancements that track tool usage, weapon kills, or armor damage evaluate without interference.
