# Durability Multiplier 公式Wiki

🌐 **Languages**: [[🇺🇸 English|Home]] | [[🇨🇳 简体中文|zh_cn-Home]] | [[🇭🇰 繁體中文|zh_tw-Home]] | [[🇷🇺 Русский|ru_ru-Home]] | [[🇪🇸 Español|es_es-Home]] | [[🇩🇪 Deutsch|de_de-Home]] | [[🇫🇷 Français|fr_fr-Home]] | [[🇧🇷 Português|pt_br-Home]] | [[🇯🇵 日本語|ja_jp-Home]] | [[🇮🇩 Bahasa Indonesia|id_id-Home]] | [[🇰🇷 한국어|ko_kr-Home]]

**Dasik (Rifaditya)**によって設計された**Durability Multiplier**（Instant Gratification Collection）の公式技術・ゲームプレイWikiへようこそ。

> 📌 **ソースコードに関する免責事項**: このWikiのドキュメントは**リポジトリの最新ソースコード状態**を反映しており、CurseForgeやModrinthで公開されているリリースビルドに先立つ未リリースの最新機能が含まれている場合があります。

---

## 🧭 マルチバージョンスイッチボードポータル

Durability Multiplierは専用のMinecraftバージョンごとに開発されています。以下からプレイ中のバージョンを選択して専用Wikiへアクセスしてください：

| Minecraft バージョン | リリース世代 | 対応ビルド | Java バージョン | Loom ツールチェーン | Wiki 入口 |
| :--- | :--- | :--- | :---: | :---: | :--- |
| **Minecraft 26.2** | Modern Sovereign Era | `1.2.14+26.2` | Java 25 | Loom 1.15.2 | [[👉 MC 26.2 Wikiを開く\|ja_jp-26.2-Home]] |
| **Minecraft 26.1.2** | Modern Sovereign Era | `1.1.21+26.1.2` | Java 25 | Loom 1.15.2 | [[👉 MC 26.1.2 Wikiを開く\|ja_jp-26.1.2-Home]] |

---

## ⚡ 基本理念とアーキテクチャ

Durability Multiplierは**Instant Gratification (IG)**設計思想に属しています。その唯一の目的は、サバイバルにおける**「道具整備のストレス」**を排除することです：

* **プレイヤーの時間を尊重**: 面倒な修理ループ、採掘の中断、大切な装備のうっかり破損を排除します。
* **純粋な数学的ダメージ軽減**: 整数除算と確率的端数処理によって耐久度延長が計算され、バニラの属性を損なうことなく何百万回もの使用にわたって数学的正確性を保ちます。
* **細やかな制御**: 24個の個別カテゴリ（剣、槍、トライデント、メイス、弓、クロスボウ、盾、道具、ツルハシ、斧、シャベル、クワ、ハサミ、釣竿、ブラシ、火打石と打ち金、防具、ヘルメット、チェストプレート、レギンス、ブーツ、エリトラ、武器、全体）を73個の静的ゲームルールで設定できます。
* **ゴッドモード（無限化）**: 単一の真偽値ゲームルールで、任意のカテゴリを100%破壊不可に設定可能。
* **Modアイテムの自動検出**: レジストリ確定時にModの耐久度アイテムを自動探索し、専用ゲームルールやGUI操作を動的に提供。
* **同期ズレゼロ**: サーバーのゲームルールは専用Fabricネットワーク (`durability-multiplier:sync_rules`) 経由でクライアントに同期され、瞬時にツールチップに反映されます。

---

## 📚 グローバルナビゲーションとリソース

* [[バージョン互換性マトリクス|ja_jp-Version-Compatibility]]
* [[MC 26.2 ドキュメントハブ|ja_jp-26.2-Home]]
* [[MC 26.1.2 ドキュメントハブ|ja_jp-26.1.2-Home]]
* [CurseForge 配布ページ](https://curseforge.com/minecraft/mc-mods/durability-multiplier)
* [Modrinth 配布ページ](https://modrinth.com/mod/durability-multiplier)
* [GitHub ソースリポジトリ](https://github.com/Rifaditya/Instant-Gratification-Durability-Multiplier)
