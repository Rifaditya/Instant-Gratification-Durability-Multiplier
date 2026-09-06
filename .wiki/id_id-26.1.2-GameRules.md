# Referensi GameRules (26.1.2)

Semua GameRule Durability Multiplier terdaftar di bawah kategori kustom **`durability-multiplier:durability_multiplier`** (`"Durability Multiplier"`).

---

## 📊 Tabel Referensi Lengkap GameRules

### 1. GameRules Persentase Daya Tahan
Aturan persentase mengontrol penskalaan ketahanan item.
* `200` = 200% (Ketahanan 2x)
* `100` = 100% (Garis dasar 1x Vanilla)
* `50` = 50% (Setengah ketahanan / 2x lebih cepat aus)
* `0` = Mewarisi dari kategori induk atau default global
* `-1` = Nilai sentinel **Sekali Pakai (Mode Kaca)** (hancur dalam 1 pukulan)

| # | Pengenal GameRule | Tipe | Bawaan | Min | Deskripsi & Perilaku |
| :-: | :--- | :---: | :---: | :---: | :--- |
| 1 | `ig:dm_percent_global` | `Integer` | `200` | `-1` | Persentase dasar global untuk semua item yang dapat rusak. |
| 2 | `ig:dm_percent_weapons` | `Integer` | `0` | `-1` | Penimpa global untuk semua senjata (Pedang, Tombak, Trisula, Gada, Busur, Panah Otomatis). |
| 3 | `ig:dm_percent_swords` | `Integer` | `0` | `-1` | Persentase khusus untuk Pedang (`#minecraft:swords`, `#c:swords`). |
| 4 | `ig:dm_percent_spears` | `Integer` | `0` | `-1` | Persentase khusus untuk Tombak (`#minecraft:spears`, `#c:spears`). |
| 5 | `ig:dm_percent_tridents` | `Integer` | `0` | `-1` | Persentase khusus untuk Trisula (`TridentItem`, `#c:tridents`). |
| 6 | `ig:dm_percent_maces` | `Integer` | `0` | `-1` | Persentase khusus untuk Gada (`MaceItem`, `#c:maces`). |
| 7 | `ig:dm_percent_bows` | `Integer` | `0` | `-1` | Persentase khusus untuk Busur (`BowItem`, `#c:bows`). |
| 8 | `ig:dm_percent_crossbows` | `Integer` | `0` | `-1` | Persentase khusus untuk Panah otomatis (`CrossbowItem`, `#c:crossbows`). |
| 9 | `ig:dm_percent_shields` | `Integer` | `0` | `-1` | Persentase khusus untuk Perisai (`ShieldItem`, `#c:shields`). |
| 10 | `ig:dm_percent_tools` | `Integer` | `0` | `-1` | Persentase kategori induk untuk semua alat (tools). |
| 11 | `ig:dm_percent_pickaxes` | `Integer` | `0` | `-1` | Persentase khusus untuk Beliung (`PickaxeItem`, `#c:pickaxes`). |
| 12 | `ig:dm_percent_axes` | `Integer` | `0` | `-1` | Persentase khusus untuk Kapak (`AxeItem`, `#c:axes`). |
| 13 | `ig:dm_percent_shovels` | `Integer` | `0` | `-1` | Persentase khusus untuk Sekop (`ShovelItem`, `#c:shovels`). |
| 14 | `ig:dm_percent_hoes` | `Integer` | `0` | `-1` | Persentase khusus untuk Cangkul (`HoeItem`, `#c:hoes`). |
| 15 | `ig:dm_percent_shears` | `Integer` | `0` | `-1` | Persentase khusus untuk Gunting (`ShearsItem`, `#c:shears`). |
| 16 | `ig:dm_percent_fishing_rods` | `Integer` | `0` | `-1` | Persentase khusus untuk Alat pancing (`FishingRodItem`). |
| 17 | `ig:dm_percent_brushes` | `Integer` | `0` | `-1` | Persentase khusus untuk Kuas (`BrushItem`). |
| 18 | `ig:dm_percent_flint_and_steel` | `Integer` | `0` | `-1` | Persentase khusus untuk Pemantik api (`FlintAndSteelItem`). |
| 19 | `ig:dm_percent_armor` | `Integer` | `0` | `-1` | Persentase kategori induk untuk semua bagian armor. |
| 20 | `ig:dm_percent_helmets` | `Integer` | `0` | `-1` | Persentase khusus untuk Helm (`#c:helmets`, slot kepala). |
| 21 | `ig:dm_percent_chestplates` | `Integer` | `0` | `-1` | Persentase khusus untuk Pelindung dada (`#c:chestplates`, slot dada). |
| 22 | `ig:dm_percent_leggings` | `Integer` | `0` | `-1` | Persentase khusus untuk Celana pelindung (`#c:leggings`, slot kaki). |
| 23 | `ig:dm_percent_boots` | `Integer` | `0` | `-1` | Persentase khusus untuk Sepatu bot (`#c:boots`, slot telapak kaki). |
| 24 | `ig:dm_percent_elytra` | `Integer` | `0` | `-1` | Persentase khusus untuk Sayap Elytra (`Items.ELYTRA`, `GLIDER`). |

---

### 2. GameRules Mode Dewa (Tak Terbatas)
Saat diaktifkan (`true`), item dalam kategori tersebut menerima $0$ kerusakan dan tidak akan pernah hancur.

| # | Pengenal GameRule | Tipe | Bawaan | Deskripsi |
| :-: | :--- | :---: | :---: | :--- |
| 25 | `ig:dm_infinity_global` | `Boolean` | `false` | Mode Dewa global untuk semua item yang dapat rusak di dalam game. |
| 26 | `ig:dm_infinity_weapons` | `Boolean` | `false` | Mode Dewa untuk semua senjata. |
| 27 | `ig:dm_infinity_swords` | `Boolean` | `false` | Mode Dewa untuk Pedang. |
| 28 | `ig:dm_infinity_spears` | `Boolean` | `false` | Mode Dewa untuk Tombak. |
| 29 | `ig:dm_infinity_tridents` | `Boolean` | `false` | Mode Dewa untuk Trisula. |
| 30 | `ig:dm_infinity_maces` | `Boolean` | `false` | Mode Dewa untuk Gada. |
| 31 | `ig:dm_infinity_bows` | `Boolean` | `false` | Mode Dewa untuk Busur. |
| 32 | `ig:dm_infinity_crossbows` | `Boolean` | `false` | Mode Dewa untuk Panah otomatis. |
| 33 | `ig:dm_infinity_shields` | `Boolean` | `false` | Mode Dewa untuk Perisai. |
| 34 | `ig:dm_infinity_tools` | `Boolean` | `false` | Mode Dewa untuk semua alat. |
| 35 | `ig:dm_infinity_pickaxes` | `Boolean` | `false` | Mode Dewa untuk Beliung. |
| 36 | `ig:dm_infinity_axes` | `Boolean` | `false` | Mode Dewa untuk Kapak. |
| 37 | `ig:dm_infinity_shovels` | `Boolean` | `false` | Mode Dewa untuk Sekop. |
| 38 | `ig:dm_infinity_hoes` | `Boolean` | `false` | Mode Dewa untuk Cangkul. |
| 39 | `ig:dm_infinity_shears` | `Boolean` | `false` | Mode Dewa untuk Gunting. |
| 40 | `ig:dm_infinity_fishing_rods` | `Boolean` | `false` | Mode Dewa untuk Alat pancing. |
| 41 | `ig:dm_infinity_brushes` | `Boolean` | `false` | Mode Dewa untuk Kuas. |
| 42 | `ig:dm_infinity_flint_and_steel` | `Boolean` | `false` | Mode Dewa untuk Pemantik api. |
| 43 | `ig:dm_infinity_armor` | `Boolean` | `false` | Mode Dewa untuk semua armor. |
| 44 | `ig:dm_infinity_helmets` | `Boolean` | `false` | Mode Dewa untuk Helm. |
| 45 | `ig:dm_infinity_chestplates` | `Boolean` | `false` | Mode Dewa untuk Pelindung dada. |
| 46 | `ig:dm_infinity_leggings` | `Boolean` | `false` | Mode Dewa untuk Celana pelindung. |
| 47 | `ig:dm_infinity_boots` | `Boolean` | `false` | Mode Dewa untuk Sepatu bot. |
| 48 | `ig:dm_infinity_elytra` | `Boolean` | `false` | Mode Dewa untuk Sayap Elytra. |

---

### 3. GameRules Sekali Pakai (Mode Kaca)
Saat diaktifkan (`true`), item dalam kategori tersebut akan hancur seketika setelah 1 kali pakai.

| # | Pengenal GameRule | Tipe | Bawaan | Deskripsi |
| :-: | :--- | :---: | :---: | :--- |
| 49 | `ig:dm_single_use_global` | `Boolean` | `false` | Mode Kaca global (sekali pakai) untuk semua item. |
| 50 | `ig:dm_single_use_weapons` | `Boolean` | `false` | Sekali pakai untuk semua senjata. |
| 51 | `ig:dm_single_use_swords` | `Boolean` | `false` | Sekali pakai untuk Pedang. |
| 52 | `ig:dm_single_use_spears` | `Boolean` | `false` | Sekali pakai untuk Tombak. |
| 53 | `ig:dm_single_use_tridents` | `Boolean` | `false` | Sekali pakai untuk Trisula. |
| 54 | `ig:dm_single_use_maces` | `Boolean` | `false` | Sekali pakai untuk Gada. |
| 55 | `ig:dm_single_use_bows` | `Boolean` | `false` | Sekali pakai untuk Busur. |
| 56 | `ig:dm_single_use_crossbows` | `Boolean` | `false` | Sekali pakai untuk Panah otomatis. |
| 57 | `ig:dm_single_use_shields` | `Boolean` | `false` | Sekali pakai untuk Perisai. |
| 58 | `ig:dm_single_use_tools` | `Boolean` | `false` | Sekali pakai untuk semua alat. |
| 59 | `ig:dm_single_use_pickaxes` | `Boolean` | `false` | Sekali pakai untuk Beliung. |
| 60 | `ig:dm_single_use_axes` | `Boolean` | `false` | Sekali pakai untuk Kapak. |
| 61 | `ig:dm_single_use_shovels` | `Boolean` | `false` | Sekali pakai untuk Sekop. |
| 62 | `ig:dm_single_use_hoes` | `Boolean` | `false` | Sekali pakai untuk Cangkul. |
| 63 | `ig:dm_single_use_shears` | `Boolean` | `false` | Sekali pakai untuk Gunting. |
| 64 | `ig:dm_single_use_fishing_rods` | `Boolean` | `false` | Sekali pakai untuk Alat pancing. |
| 65 | `ig:dm_single_use_brushes` | `Boolean` | `false` | Sekali pakai untuk Kuas. |
| 66 | `ig:dm_single_use_flint_and_steel` | `Boolean` | `false` | Sekali pakai untuk Pemantik api. |
| 67 | `ig:dm_single_use_armor` | `Boolean` | `false` | Sekali pakai untuk semua armor. |
| 68 | `ig:dm_single_use_helmets` | `Boolean` | `false` | Sekali pakai untuk Helm. |
| 69 | `ig:dm_single_use_chestplates` | `Boolean` | `false` | Sekali pakai untuk Pelindung dada. |
| 70 | `ig:dm_single_use_leggings` | `Boolean` | `false` | Sekali pakai untuk Celana pelindung. |
| 71 | `ig:dm_single_use_boots` | `Boolean` | `false` | Sekali pakai untuk Sepatu bot. |
| 72 | `ig:dm_single_use_elytra` | `Boolean` | `false` | Sekali pakai untuk Sayap Elytra. |

---

### 4. GameRules Tampilan & Mod Dinamis

| Pengenal GameRule | Tipe | Bawaan | Deskripsi |
| :--- | :---: | :---: | :--- |
| `ig:dm_show_tooltip` | `Boolean` | `true` | Menampilkan baris bonus ketahanan pada tooltip item. |
| `ig:percent_<mod>_<item>` | `Integer` | `0` | Penimpa persentase dinamis untuk item mod tertentu (Min `-1`). |
| `ig:infinity_<mod>_<item>` | `Boolean` | `false` | Penimpa Mode Kebal dinamis untuk item mod tertentu. |
| `ig:single_use_<mod>_<item>` | `Boolean` | `false` | Penimpa Sekali Pakai dinamis untuk item mod tertentu. |

---

## ⚡ Perintah Penyesuaian Dalam Game

```mcfunction
# Query current global percentage
/gamerule ig:dm_percent_global

# Set diamond/netherite pickaxes to 500% (5x) durability
/gamerule ig:dm_percent_pickaxes 500

# Make Elytra wings unbreakable
/gamerule ig:dm_infinity_elytra true

# Set a modded weapon to Single-Use using the -1 sentinel
/gamerule ig:percent_techmod_plasma_cutter -1

# Disable all multipliers (vanilla 100% baseline)
/gamerule ig:dm_percent_global 100
```

