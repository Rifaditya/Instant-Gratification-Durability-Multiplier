# 指令與管理 (26.2)

| 管理系统屬性 | 詳情 |
| :--- | :--- |
| **指令引擎** | 原版 Minecraft `/gamerule` Brigadier 指令系统 |
| **命名空间** | 所有規則均以 `ig:` 為前缀 |
| **权限等級** | 2 級 (OP / 單人游戏作弊已開啟) |
| **图形化管理** | 支持通过游戏規則界面与 ModMenu 配置界面管理 |
| **缺省政策** | 設計上有意保持**零自定义 Brigadier 指令子樹** |

---

## ⚡ 遊戲內管理工作流程

Durability Multiplier 完全依赖原版 `/gamerule` 指令。未添加任何自定义指令（如 `/durability set` 或 `/durability reload`），確保与原版命令方块、函數、权限插件和數據封包保持 100% 原生兼容。

### 常用管理任務

#### 1. 設定標準生存增益
```mcfunction
# Double all items globally (200% durability - Default)
/gamerule ig:dm_percent_global 200

# Give mining tools a 5x (500%) lifespan
/gamerule ig:dm_percent_tools 500

# Give weapons a 3x (300%) lifespan
/gamerule ig:dm_percent_weapons 300
```

#### 2. 設定戰鬥與 PvP 伺服器設定
```mcfunction
# Keep armor at vanilla durability (100%) to prevent overly tanky players
/gamerule ig:dm_percent_armor 100

# Give weapons 2x (200%) durability
/gamerule ig:dm_percent_swords 200
/gamerule ig:dm_percent_bows 200
```

#### 3. 啟用創造模式風格生存（無限鞘翅與工具）
```mcfunction
# Unbreakable Elytra for limitless exploration
/gamerule ig:dm_infinity_elytra true

# Unbreakable Tools for building mega-projects
/gamerule ig:dm_infinity_tools true
```

#### 4. 設定動態模組物品
```mcfunction
# Give a custom modded drill 400% durability
/gamerule ig:percent_techmod_titanium_drill 400

# Make a modded staff unbreakable
/gamerule ig:infinity_magicmod_staff_of_fire true

# Set a modded dagger to Single-Use using the -1 sentinel
/gamerule ig:percent_customweapons_glass_dagger -1
```

#### 5. 隱藏提示框文字
```mcfunction
# Hide durability multiplier info from item tooltips
/gamerule ig:dm_show_tooltip false
```

