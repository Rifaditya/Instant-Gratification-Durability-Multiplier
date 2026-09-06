# Pengali Daya Tahan & Persentase (26.2)

Durability Multiplier menggantikan mekanisme keausan tetap bawaan vanilla dengan **Engine Skala Persentase** dinamis yang mendukung peningkatan ketahanan (misal 200% = 2x, 500% = 5x) maupun penalti keausan (misal 50% = 0.5x, 25% = 0.25x).

---

## ⚙️ GameRules Persentase Inti

| # | Pengenal GameRule | Bawaan | Kategori Target / Deskripsi |
| :-: | :--- | :---: | :--- |
| 1 | `ig:dm_percent_global` | `200` | Persentase global yang diterapkan pada semua item yang dapat rusak. |
| 2 | `ig:dm_percent_weapons` | `0` | Penimpa induk untuk semua senjata (Pedang, Tombak, Trisula, Gada, Busur, Panah Otomatis). |
| 3 | `ig:dm_percent_swords` | `0` | Persentase khusus untuk Pedang (`#minecraft:swords`, `#c:swords`). |
| 4 | `ig:dm_percent_spears` | `0` | Persentase khusus untuk Tombak (`#minecraft:spears`, `#c:spears`). |
| 5 | `ig:dm_percent_tridents` | `0` | Persentase khusus untuk Trisula (`Items.TRIDENT`, `TridentItem`, `#c:tridents`). |
| 6 | `ig:dm_percent_maces` | `0` | Persentase khusus untuk Gada (`Items.MACE`, `MaceItem`, `#c:maces`). |
| 7 | `ig:dm_percent_bows` | `0` | Persentase khusus untuk Busur (`Items.BOW`, `BowItem`, `#c:bows`). |
| 8 | `ig:dm_percent_crossbows` | `0` | Persentase khusus untuk Panah otomatis (`Items.CROSSBOW`, `CrossbowItem`, `#c:crossbows`). |
| 9 | `ig:dm_percent_shields` | `0` | Persentase khusus untuk Perisai (`Items.SHIELD`, `ShieldItem`, `#c:shields`). |
| 10 | `ig:dm_percent_tools` | `0` | Persentase kategori induk untuk semua alat (tools). |
| 11 | `ig:dm_percent_pickaxes` | `0` | Persentase khusus untuk Beliung (`PickaxeItem`, `#c:pickaxes`). |
| 12 | `ig:dm_percent_axes` | `0` | Persentase khusus untuk Kapak (`AxeItem`, `#c:axes`). |
| 13 | `ig:dm_percent_shovels` | `0` | Persentase khusus untuk Sekop (`ShovelItem`, `#c:shovels`). |
| 14 | `ig:dm_percent_hoes` | `0` | Persentase khusus untuk Cangkul (`HoeItem`, `#c:hoes`). |
| 15 | `ig:dm_percent_shears` | `0` | Persentase khusus untuk Gunting (`ShearsItem`, `#c:shears`). |
| 16 | `ig:dm_percent_fishing_rods` | `0` | Persentase khusus untuk Alat pancing (`FishingRodItem`). |
| 17 | `ig:dm_percent_brushes` | `0` | Persentase khusus untuk Kuas (`BrushItem`). |
| 18 | `ig:dm_percent_flint_and_steel` | `0` | Persentase khusus untuk Pemantik api (`FlintAndSteelItem`). |
| 19 | `ig:dm_percent_armor` | `0` | Persentase kategori induk untuk semua bagian armor. |
| 20 | `ig:dm_percent_helmets` | `0` | Persentase khusus untuk Helm (`#minecraft:head_armor`, `#c:helmets`). |
| 21 | `ig:dm_percent_chestplates` | `0` | Persentase khusus untuk Pelindung dada (`#minecraft:chest_armor`, `#c:chestplates`). |
| 22 | `ig:dm_percent_leggings` | `0` | Persentase khusus untuk Celana pelindung (`#minecraft:leg_armor`, `#c:leggings`). |
| 23 | `ig:dm_percent_boots` | `0` | Persentase khusus untuk Sepatu bot (`#minecraft:foot_armor`, `#c:boots`). |
| 24 | `ig:dm_percent_elytra` | `0` | Persentase khusus untuk Sayap Elytra (`Items.ELYTRA`, `DataComponents.GLIDER`). |

> [!NOTE]
> Aturan penimpaan yang disetel ke `0` otomatis kembali ke kategori induknya atau bawaan Global. Menyetel `-1` mengaktifkan **Sekali Pakai (Mode Kaca)**.

---

## 🔒 Keamanan 100% untuk Penyimpanan Dunia
Durability Multiplier **tidak** mengubah NBT item atau `DataComponents.MAX_DAMAGE` pada simpanan dunia Anda. Semua penyesuaian ketahanan dihitung secara dinamis saat kerusakan terjadi, menjamin nol kerusakan dunia atau sisa data termodifikasi jika mod dicopot.
