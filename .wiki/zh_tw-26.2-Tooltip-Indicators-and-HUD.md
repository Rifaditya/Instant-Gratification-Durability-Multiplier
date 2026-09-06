# 提示框指示器與 HUD (26.2)

| 系统參數 | 取值 |
| :--- | :--- |
| **開關游戏規則** | `ig:dm_show_tooltip` |
| **默认状態** | `true` (已啟用) |
| **Mixin 目標** | `ItemStack.addDetailsToTooltip` (`ItemStackTooltipMixin`) |
| **注入點** | `@At("TAIL")` |
| **上帝模式樣式** | `✦ UNBREAKABLE` (金色粗體 — `ChatFormatting.GOLD`, `ChatFormatting.BOLD`) |
| **倍率顯示樣式** | `⟨Nx Category Durability⟩` (灰色 — `ChatFormatting.GRAY`) |

---

## ⚡ 概述與視覺呈現

每当物品的寿命被調整時，Durability Multiplier 都会在物品提示框上直接提供即時且明確的视觉反馈。

### 提示框視覺樣式

| 状態 | 渲染文本 | 视觉外观 | 颜色代碼 |
| :--- | :--- | :--- | :--- |
| **上帝模式已激活** | `✦ UNBREAKABLE` | **✦ UNBREAKABLE** | 金色粗體 (`ChatFormatting.GOLD`, `BOLD`) |
| **單次使用（玻璃模式）** | `⟨SINGLE-USE⟩` | ⟨SINGLE-USE⟩ | 灰色 (`ChatFormatting.GRAY`) |
| **200% / 2x 倍率** | `⟨2x Swords Durability⟩` | ⟨2x Swords Durability⟩ | 灰色 (`ChatFormatting.GRAY`) |
| **150% 耐久度** | `⟨150% Chestplates Durability⟩` | ⟨150% Chestplates Durability⟩ | 灰色 (`ChatFormatting.GRAY`) |
| **50% (半數耐久)** | `⟨50% Swords Durability⟩` | ⟨50% Swords Durability⟩ | 灰色 (`ChatFormatting.GRAY`) |
| **500% / 5x 倍率** | `⟨5x Pickaxes Durability⟩` | ⟨5x Pickaxes Durability⟩ | 灰色 (`ChatFormatting.GRAY`) |
| **模組物品特定覆蓋** | `⟨300% Plasma Cutter Durability⟩` | ⟨300% Plasma Cutter Durability⟩ | 灰色 (`ChatFormatting.GRAY`) |
| **原版基准 (100%)** | *(無)* | *(不渲染额外提示框文本行)* | — |

---

## 🎨 提示框格式化模式 (`tooltipFormat`)

模組通过 `config/durability-multiplier.json` 和 ModMenu 界面支持 3 种可配置的顯示格式：
1. **`ADAPTIVE` (默认)**：整百倍率自動顯示简洁的整數倍率 (`2x`, `5x`)，其餘情况顯示明確的百分比 (`50%`, `150%`)。
2. **`PERCENTAGE`**：始终顯示明確的百分比（例如 `200% Swords Durability`, `50% Pickaxes Durability`）。
3. **`MULTIPLIER`**：始终顯示格式化的倍率（例如 `2x Swords Durability`, `0.5x Swords Durability`, `1.5x Chestplates Durability`）。

---

## 🖥️ 客戶端與伺服端端側執行機制

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

1. **集成服务端（單人游戏 / 局域網主機）**：提示框直接實時查詢活跃的 `ServerLevel` 游戏規則。
2. **獨立客戶端（連接多人服务器）**：提示框讀取 `DurabilityClientState` 缓存，每当服务端游戏規則變化時通过 `DurabilityPayload` 封封包自動同步更新。
