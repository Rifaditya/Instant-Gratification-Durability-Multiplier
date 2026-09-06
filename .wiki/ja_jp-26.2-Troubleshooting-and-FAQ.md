# トラブルシューティングとFAQ (26.2)

| トピック | 要約 |
| :--- | :--- |
| **優先順位の挙動** | 既存ワールドではGameRulesが優先。設定ファイルは新規ワールドの初期値を設定 |
| **計算エンジン** | 確率的傍受（NBT改変ゼロ、セーブデータの不整合ゼロ） |
| **例外耐性** | Mod削除、レジストリ解除、コンポーネント欠損時も100%クラッシュフリー |

---

## ❓ よくある質問 (FAQ)

### Q1: ModMenuで変更した設定がアクティブなシングルプレイワールドに反映されないのはなぜですか？
**回答**: **優先順位の法則**により、設定ファイルやModMenuでの変更は**新規ワールドの初期値のみ**に適用されます。現在プレイ中のワールドの設定を変更するには、ゲーム内の`/gamerule`コマンド（例：`/gamerule ig:dm_percent_tools 500`）またはゲームルール編集画面を使用してください。

### Q2: アイテムのツールチップにパーセンテージや倍率テキストが表示されないのはなぜですか？
**回答**:
1. アイテムが耐久度ゲージを持つアイテム（`DataComponents.MAX_DAMAGE > 0`）か確認してください。
2. `ig:dm_show_tooltip`が`true`に設定されているか確認してください。
3. 現在の設定が`100`（バニラ耐久度100%）の場合、ツールチップをすっきり保つため追加行は描画されません。

### Q3: 500% (5倍) のツールがわずか2回の使用で耐久値を消費したのはなぜですか？
**回答**: Durability Multiplierは、**セーブデータの安全性を100%保つ**ために**確率的ダメージ傍受**（バニラの*耐久力（Unbreaking）*エンチャントと全く同じ方式）を採用しています。500%（耐久度5倍）の場合、ブロックを1個壊すごとに**20%（5回に1回）の確率**で1ダメージを受け、**80%の確率**で無効化されます。各使用ごとに独立して確率判定が行われるため、2回で減ることもあれば8回減らないこともありますが、ツールの全寿命を通じて見れば正確に5倍長持ちします（ダイヤのツルハシなら約7,805回採掘可能）。

### Q4: GameRulesに 0.5 や 1.5 などの小数を入力すべきですか？
**回答**: **いいえ**。MinecraftのGameRulesは整数（`int`）のみ受け付けます。常に整数のパーセント値を入力してください：
* `50` = 50%（耐久度半分 / 消耗速度2倍）
* `100` = 100%（バニラ標準の1倍）
* `150` = 150%（耐久度1.5倍）
* `200` = 200%（耐久度2倍）
* `-1` = 使い捨て（ガラスモード / 1回で破損）

### Q5: Durability Multiplierは耐久力（Unbreaking）エンチャントと共存できますか？
**回答**: はい！Durability Multiplierはエンチャント判定の**前**にダメージをスケーリングします。耐久力III付きのツルハシに200%（2倍）を設定した場合、エンチャントなしのバニラツルハシと比べて約 $4 \times 2 = 8$倍 長持ちします。

### Q6: アイテムに対して1回ヒットで壊れるグラスモード（使い切り）を有効にするにはどうすればよいですか？
**回答**: 以下のいずれかの方法で行えます：
1. 使い捨てゲームルールをtrueにする: `/gamerule ig:dm_single_use_swords true` (または `/gamerule ig:single_use_<mod>_<item> true`)。
2. **上級者向け`-1`番兵値**を使う: 倍率ルールを`-1`にする（例：`/gamerule ig:dm_percent_swords -1` や `/gamerule ig:percent_<mod>_<item> -1`）。

---

## 🔍 エッジケースとライフサイクルの詳細解説

### エッジケース1: Modのアンインストールとアイテムの削除
Durability Multiplierに登録されていたアイテムを持つModを削除した場合：
1. **設定ファイルの安全性**: 削除されたアイテムIDは`config/durability-multiplier.json`内に安全に保持されます。
2. **ワールドデータの休眠**: `level.dat`内に保存された動的ルールはメモリ内で完全に休眠状態となります。
3. **クラッシュ＆破損ゼロ**: アイテムの検索は`BuiltInRegistries.ITEM.getKey(stack.getItem())`を経由するため、存在しないクラスや未マッピングのIDを探索することはなく、`NullPointerException`やチャンク破損は一切発生しません。
4. **再導入時の自動復帰**: 将来そのModを再導入した場合、以前の倍率・ゴッドモード・使い捨て設定が**即座に再適用**されます！
5. **手動クリーンアップ（任意）**: 設定ファイルから削除済みModの記述を消去したい場合：
   * Open `config/durability-multiplier.json` in a text editor.
   * Remove the deleted item ID string from the `"forcedItems"` list.
   * Remove the corresponding keys from `"forcedPercentages"`, `"forcedInfinities"`, and `"forcedSingleUses"`.
   * Save the file.

---

### エッジケース2: 厳格な耐久値フィルタリング (`MAX_DAMAGE > 0`)
家具Mod（Macaw's Furnitureの椅子やタンスなど）、ブロック、食料、素材がGameRulesや設定ファイルに表示されないのはなぜですか？
* Durability Multiplierは、アイテムを登録する前に`DataComponents.MAX_DAMAGE > 0`を厳格に確認します。
* 耐久度を持たないアイテム（ブロック、食料、インゴット、種など）は起動時スキャンで即座に（$0.0001\mu\text{s}$）除外されます。
* これにより名前空間の汚染を防ぎ、Tab補完を常に快適で使いやすい状態に保ちます。

---

### エッジケース3: 完全な評価と優先順位の階層
アイテムが耐久度ダメージを受ける際、以下の厳格な優先階層に従って結果が決定されます：

$$\text{God Mode (Infinity)} \longrightarrow \text{Single-Use (Glass Mode)} \longrightarrow \text{Per-Item Override} \longrightarrow \text{Subcategory} \longrightarrow \text{Parent Category} \longrightarrow \text{Global} \longrightarrow \text{100\% Vanilla}$$

1. **ゴッドモード判定（無敵化）**:
   * Per-Item God Mode (`ig:infinity_<mod>_<item>` / `forcedInfinities`)
   * Subcategory God Mode (`ig:dm_infinity_pickaxes`, `ig:dm_infinity_swords`, etc.)
   * Parent Category God Mode (`ig:dm_infinity_tools`, `ig:dm_infinity_weapons`, `ig:dm_infinity_armor`)
   * Global God Mode (`ig:dm_infinity_global`)
   * *If any is `true` $\rightarrow$ Damage = $0$ (Unbreakable).*
2. **使い捨て判定（ガラスモード）**:
   * Effective percentage $\le -1$ (`-1` Glass Mode Sentinel)
   * Per-Item Single-Use (`ig:single_use_<mod>_<item>` / `forcedSingleUses`)
   * Subcategory Single-Use (`ig:dm_single_use_pickaxes`, `ig:dm_single_use_swords`, etc.)
   * Parent Category Single-Use (`ig:dm_single_use_tools`, `ig:dm_single_use_weapons`, `ig:dm_single_use_armor`)
   * Global Single-Use (`ig:dm_single_use_global`)
   * *If active $\rightarrow$ Item loses all remaining durability in 1 hit.*
3. **パーセントスケーリングの解決**:
   * Per-Item Override (`ig:percent_<mod>_<item>` / `forcedPercentages`) if $\neq 0$
   * Subcategory Percentage (`ig:dm_percent_pickaxes`, `ig:dm_percent_swords`, etc.) if $\neq 0$
   * Parent Category Percentage (`ig:dm_percent_tools`, `ig:dm_percent_weapons`, `ig:dm_percent_armor`) if $\neq 0$
   * Global Percentage (`ig:dm_percent_global`) if $\neq 0$
   * Vanilla Fallback: $100\%$ baseline.

