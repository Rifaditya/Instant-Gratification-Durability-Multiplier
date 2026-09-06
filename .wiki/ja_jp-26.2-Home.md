# Durability Multiplier — Minecraft 26.2 ドキュメントハブ

**Minecraft 26.2** (`1.2.14+26.2`) 向け **Durability Multiplier** 公式ドキュメントハブへようこそ。

> 📌 **ソースコードに関する免責事項**: このWikiのドキュメントは**リポジトリの最新ソースコード状態**を反映しており、CurseForgeやModrinthで公開されているリリースビルドに先立つ未リリースの最新機能が含まれている場合があります。

---

## 📋 技術スナップショット (26.2)

| パラメータ | 設定値 | 説明 |
| :--- | :--- | :--- |
| **Mod識別子** | `durability-multiplier` | Fabric Loader内のMod ID |
| **Modバージョン** | `1.2.14+26.2` | SemVerリリースタグ |
| **対象Minecraft** | `26.2` (`>=26.2-`) | ネイティブバージョン |
| **Javaリリース** | Java 25 | `release = 25`でコンパイル |
| **Fabric Loader** | `>=0.16.9` | 必須ローダーバージョン |
| **Fabric API** | `0.150.1+26.2` | 必須Fabric APIバージョン |
| **DasikLibrary** | `1.8.28` | 共通コアライブラリ |
| **登録ゲームルール** | **73個の静的ルール** + 動的Modルール | 24個の倍率、24個の無限、24個の使い捨て、1個のツールチップ |
| **Mixin注入ポイント** | 3つの対象クラス | `ItemStack`, `GameRules` |
| **作者 & ライセンス** | **Dasik (Rifaditya)** / GPL-3.0-or-later | オープンソースMod |

---

## 🧭 ナビゲーションマトリクス (26.2)

### 🎮 プレイヤー＆ゲームプレイガイド
* [[耐久度倍率とカテゴリ|ja_jp-26.2-Durability-Multipliers]] — 24カテゴリの詳細な倍率体系と上書き階層。
* [[ゴッドモードと無限化|ja_jp-26.2-God-Mode-and-Infinity]] — 24カテゴリに対応したダメージゼロの無敵化設定。
* [[ダメージ軽減の計算式と確率|ja_jp-26.2-Damage-Reduction-and-Probability-Math]] — 数学的な計算式と確率的端数処理。
* [[アイテム分類とMod互換性|ja_jp-26.2-Item-Classification-and-Mod-Compatibility]] — バニラおよびModアイテムの判定方法。
* [[動的Modアイテム探索・登録|ja_jp-26.2-Dynamic-Modded-Item-Registration]] — ユニバーサル3段階スキャナーと自動入力機能。
* [[ツールチップ表示とHUD|ja_jp-26.2-Tooltip-Indicators-and-HUD]] — クライアント側でのツールチップ描画。
* [[ゲームルール一覧表|ja_jp-26.2-GameRules]] — 全73個の静的ゲームルールの網羅的リファレンス。
* [[コマンドとゲーム内管理|ja_jp-26.2-Commands-and-Administration]] — `/gamerule`によるゲーム内設定管理。
* [[進捗と実績|ja_jp-26.2-Advancements]] — 不使用ポリシーとバニラ進捗との統合。
* [[設定GUIとワールド初期値|ja_jp-26.2-Configuration]] — ModMenuおよびCloth Configとの連携。
* [[ModVersionGuard実行時保護|ja_jp-26.2-ModVersionGuard-and-Safety]] — 依存関係ゼロのバージョン保護ガード。
* [[トラブルシューティングとよくある質問|ja_jp-26.2-Troubleshooting-and-FAQ]] — 診断手順とFAQ。

### 💻 開発者向け技術リファレンス
* [[アーキテクチャとMixin設計|ja_jp-26.2-Architecture-and-Mixins]] — パッケージ構成、注入フック、再突入防止設計。
* [[ネットワーク同期とプロトコル|ja_jp-26.2-Network-Sync-and-Payload-Protocol]] — S2C同期プロトコル (`DurabilityPayload`)。
* [[開発環境構築とビルド手順|ja_jp-26.2-Developer-Setup-and-Building]] — Gradleコマンド、Loomツールチェーン、JDK要件。
* [[APIとアドオン開発|ja_jp-26.2-API-and-Addon-Integration]] — Modの拡張、`DurabilityHelper`、カスタムルール追加。
