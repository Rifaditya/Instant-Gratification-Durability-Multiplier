# 提示框指示器与 HUD (26.2)

| 系统参数 | 取值 |
| :--- | :--- |
| **开关游戏规则** | `ig:dm_show_tooltip` |
| **默认状态** | `true` (已启用) |
| **Mixin 目标** | `ItemStack.addDetailsToTooltip` (`ItemStackTooltipMixin`) |
| **注入点** | `@At("TAIL")` |
| **上帝模式样式** | `✦ UNBREAKABLE` (金色粗体 — `ChatFormatting.GOLD`, `ChatFormatting.BOLD`) |
| **倍率显示样式** | `⟨Nx Category Durability⟩` (灰色 — `ChatFormatting.GRAY`) |

---

## ⚡ 概述与视觉呈现

每当物品的寿命被调整时，Durability Multiplier 都会在物品提示框上直接提供即时且明确的视觉反馈。

### 提示框视觉样式

| 状态 | 渲染文本 | 视觉外观 | 颜色代码 |
| :--- | :--- | :--- | :--- |
| **上帝模式已激活** | `✦ UNBREAKABLE` | **✦ UNBREAKABLE** | 金色粗体 (`ChatFormatting.GOLD`, `BOLD`) |
| **单次使用（玻璃模式）** | `⟨SINGLE-USE⟩` | ⟨SINGLE-USE⟩ | 灰色 (`ChatFormatting.GRAY`) |
| **200% / 2x 倍率** | `⟨2x Swords Durability⟩` | ⟨2x Swords Durability⟩ | 灰色 (`ChatFormatting.GRAY`) |
| **150% 耐久度** | `⟨150% Chestplates Durability⟩` | ⟨150% Chestplates Durability⟩ | 灰色 (`ChatFormatting.GRAY`) |
| **50% (半数耐久)** | `⟨50% Swords Durability⟩` | ⟨50% Swords Durability⟩ | 灰色 (`ChatFormatting.GRAY`) |
| **500% / 5x 倍率** | `⟨5x Pickaxes Durability⟩` | ⟨5x Pickaxes Durability⟩ | 灰色 (`ChatFormatting.GRAY`) |
| **模组物品特定覆盖** | `⟨300% Plasma Cutter Durability⟩` | ⟨300% Plasma Cutter Durability⟩ | 灰色 (`ChatFormatting.GRAY`) |
| **原版基准 (100%)** | *(无)* | *(不渲染额外提示框文本行)* | — |

---

## 🎨 提示框格式化模式 (`tooltipFormat`)

模组通过 `config/durability-multiplier.json` 和 ModMenu 界面支持 3 种可配置的显示格式：
1. **`ADAPTIVE` (默认)**：整百倍率自动显示简洁的整数倍率 (`2x`, `5x`)，其余情况显示明确的百分比 (`50%`, `150%`)。
2. **`PERCENTAGE`**：始终显示明确的百分比（例如 `200% Swords Durability`, `50% Pickaxes Durability`）。
3. **`MULTIPLIER`**：始终显示格式化的倍率（例如 `2x Swords Durability`, `0.5x Swords Durability`, `1.5x Chestplates Durability`）。

---

## 🖥️ 客户端与服务端端侧执行机制

```
                       [Item Tooltip Render]
                                 │
                                 ▼
                     [Is Player on Integrated Server?]
                     ├── YES ──► Read GameRules from ServerLevel
                     │           (DurabilityHelper.getTooltipLabel)
                     │
                     └── NO (Remote Server) ──► Read Synced Client Cache
                                                (DurabilityClientState)
```

1. **集成服务端（单人游戏 / 局域网主机）**：提示框直接实时查询活跃的 `ServerLevel` 游戏规则。
2. **独立客户端（连接多人服务器）**：提示框读取 `DurabilityClientState` 缓存，每当服务端游戏规则变化时通过 `DurabilityPayload` 封包自动同步更新。
