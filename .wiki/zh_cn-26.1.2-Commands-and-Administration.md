# 指令与管理 (26.1.2)

| 管理系统属性 | 详情 |
| :--- | :--- |
| **指令引擎** | 原版 Minecraft `/gamerule` Brigadier 指令系统 |
| **命名空间** | 所有规则均以 `ig:` 为前缀 |
| **权限等级** | 2 级 (OP / 单人游戏作弊已开启) |
| **图形化管理** | 支持通过游戏规则界面与 ModMenu 配置界面管理 |
| **缺省政策** | 设计上有意保持**零自定义 Brigadier 指令子树** |

---

## ⚡ 游戏内管理工作流

Durability Multiplier 完全依赖原版 `/gamerule` 指令。未添加任何自定义指令（如 `/durability set` 或 `/durability reload`），确保与原版命令方块、函数、权限插件和数据包保持 100% 原生兼容。

### 常用管理任务

#### 1. 配置标准生存增益
```mcfunction
# Double all items globally (200% durability - Default)
/gamerule ig:dm_percent_global 200

# Give mining tools a 5x (500%) lifespan
/gamerule ig:dm_percent_tools 500

# Give weapons a 3x (300%) lifespan
/gamerule ig:dm_percent_weapons 300
```

#### 2. 配置战斗与 PvP 服务器设置
```mcfunction
# Keep armor at vanilla durability (100%) to prevent overly tanky players
/gamerule ig:dm_percent_armor 100

# Give weapons 2x (200%) durability
/gamerule ig:dm_percent_swords 200
/gamerule ig:dm_percent_bows 200
```

#### 3. 启用创造模式风格生存（无限鞘翅与工具）
```mcfunction
# Unbreakable Elytra for limitless exploration
/gamerule ig:dm_infinity_elytra true

# Unbreakable Tools for building mega-projects
/gamerule ig:dm_infinity_tools true
```

#### 4. 配置动态模组物品
```mcfunction
# Give a custom modded drill 400% durability
/gamerule ig:percent_techmod_titanium_drill 400

# Make a modded staff unbreakable
/gamerule ig:infinity_magicmod_staff_of_fire true

# Set a modded dagger to Single-Use using the -1 sentinel
/gamerule ig:percent_customweapons_glass_dagger -1
```

#### 5. 隐藏提示框文本
```mcfunction
# Hide durability multiplier info from item tooltips
/gamerule ig:dm_show_tooltip false
```

