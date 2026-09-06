# ツールチップ表示とHUD (26.2)

| システムパラメータ | 設定値 |
| :--- | :--- |
| **表示切替ゲームルール** | `ig:dm_show_tooltip` |
| **初期状態** | `true`（有効） |
| **Mixin対象** | `ItemStack.addDetailsToTooltip` (`ItemStackTooltipMixin`) |
| **注入ポイント** | `@At("TAIL")` |
| **ゴッドモード表示スタイル** | `✦ UNBREAKABLE` (金色・太字 — `ChatFormatting.GOLD`, `BOLD`) |
| **倍率表示スタイル** | `⟨Nx カテゴリ耐久度⟩` (灰色 — `ChatFormatting.GRAY`) |

---

## ⚡ 概要と視覚的表示

Durability Multiplierは、アイテムの寿命が変更された際にツールチップ上で直感的かつ明確な視覚フィードバックを提供します。

### ツールチップのビジュアルスタイル

| 状態 | 描画テキスト | 見た目 | カラーコード |
| :--- | :--- | :--- | :--- |
| **ゴッドモード有効** | `✦ UNBREAKABLE` | **✦ UNBREAKABLE** | 金色、太字 (`ChatFormatting.GOLD`, `BOLD`) |
| **使い捨て（ガラスモード）** | `⟨SINGLE-USE⟩` | ⟨SINGLE-USE⟩ | 灰色 (`ChatFormatting.GRAY`) |
| **200% / 2倍 耐久度** | `⟨2x 剣の耐久度⟩` | ⟨2x 剣の耐久度⟩ | 灰色 (`ChatFormatting.GRAY`) |
| **150% 耐久度** | `⟨150% 鎧の耐久度⟩` | ⟨150% 鎧の耐久度⟩ | 灰色 (`ChatFormatting.GRAY`) |
| **50% 耐久度（半分）** | `⟨50% 剣の耐久度⟩` | ⟨50% 剣の耐久度⟩ | 灰色 (`ChatFormatting.GRAY`) |
| **500% / 5倍 耐久度** | `⟨5x ツルハシの耐久度⟩` | ⟨5x ツルハシの耐久度⟩ | 灰色 (`ChatFormatting.GRAY`) |
| **Modアイテム上書き** | `⟨300% プラズマカッター耐久度⟩` | ⟨300% プラズマカッター耐久度⟩ | 灰色 (`ChatFormatting.GRAY`) |
| **バニラ基準値（100%）** | *(なし)* | *(追加の表示行は描画されません)* | — |

---

## 🎨 ツールチップフォーマットモード (`tooltipFormat`)

設定ファイルおよびModMenu GUIから、3種類の表示形式を選択できます：
1. **`ADAPTIVE` (デフォルト)**: 100単位は整数の倍率（`2x`, `5x`）で、それ以外はパーセント（`50%`, `150%`）で自動表示します。
2. **`PERCENTAGE`**: 常に明示的なパーセントで表示します（例：`200% 剣の耐久度`）。
3. **`MULTIPLIER`**: 常に小数の倍率で表示します（例：`2x 剣の耐久度`, `0.5x 剣の耐久度`）。

---

## 🖥️ クライアント・サーバー側の実行

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

1. **統合サーバー（シングルプレイ / LANホスト）**: ツールチップは`ServerLevel`の現在のルールをリアルタイムで直接参照します。
2. **専用クライアント（マルチプレイ接続時）**: ルール変更時に配信される`DurabilityPayload`パケットによって更新される`DurabilityClientState`を参照します。
