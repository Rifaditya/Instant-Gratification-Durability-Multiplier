# コマンドと管理 (26.2)

| 管理システム | 詳細 |
| :--- | :--- |
| **コマンドエンジン** | バニラMinecraftの`/gamerule` Brigadierコマンドシステム |
| **名前空間** | すべてのルールに`ig:`プレフィックス |
| **権限レベル** | レベル2（OP権限 / シングルプレイチート有効） |
| **GUI管理** | ゲームルール設定画面およびModMenuコンフィグから操作可能 |
| **不使用ポリシー** | **カスタムBrigadierコマンドツリーは意図的に一切登録しない**方針 |

---

## ⚡ ゲーム内管理ワークフロー

Durability Multiplierは完全にバニラの`/gamerule`コマンドに依存しています。独自コマンド（`/durability set`や`/durability reload`など）は追加しないため、コマンドブロック、ファンクション、権限管理、データパックと100%ネイティブに互換します。

### 一般的な管理タスク

#### 1. 標準的なサバイバルバフの設定
```mcfunction
# Double all items globally (200% durability - Default)
/gamerule ig:dm_percent_global 200

# Give mining tools a 5x (500%) lifespan
/gamerule ig:dm_percent_tools 500

# Give weapons a 3x (300%) lifespan
/gamerule ig:dm_percent_weapons 300
```

#### 2. 戦闘およびPvPサーバー設定の構成
```mcfunction
# Keep armor at vanilla durability (100%) to prevent overly tanky players
/gamerule ig:dm_percent_armor 100

# Give weapons 2x (200%) durability
/gamerule ig:dm_percent_swords 200
/gamerule ig:dm_percent_bows 200
```

#### 3. クリエイティブ風サバイバルの有効化 (破壊不可エリトラとツール)
```mcfunction
# Unbreakable Elytra for limitless exploration
/gamerule ig:dm_infinity_elytra true

# Unbreakable Tools for building mega-projects
/gamerule ig:dm_infinity_tools true
```

#### 4. 動的Modアイテムの構成
```mcfunction
# Give a custom modded drill 400% durability
/gamerule ig:percent_techmod_titanium_drill 400

# Make a modded staff unbreakable
/gamerule ig:infinity_magicmod_staff_of_fire true

# Set a modded dagger to Single-Use using the -1 sentinel
/gamerule ig:percent_customweapons_glass_dagger -1
```

#### 5. ツールチップテキストの非表示
```mcfunction
# Hide durability multiplier info from item tooltips
/gamerule ig:dm_show_tooltip false
```

