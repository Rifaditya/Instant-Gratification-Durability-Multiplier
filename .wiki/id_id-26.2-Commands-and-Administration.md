# Perintah & Administrasi (26.2)

| Sistem Administrasi | Rincian |
| :--- | :--- |
| **Engine Perintah** | Sistem Perintah Brigadier `/gamerule` Minecraft Vanilla |
| **Namespace** | Awalan `ig:` untuk semua aturan |
| **Tingkat Izin** | Level 2 (OP / Cheat Singleplayer Aktif) |
| **Administrasi GUI** | Didukung melalui layar GameRules & konfigurasi ModMenu |
| **Kebijakan Peniadaan** | Sengaja **tidak mendaftarkan sub-pohon perintah Brigadier kustom** |

---

## ⚡ Alur Kerja Administrasi Dalam Game

Durability Multiplier sepenuhnya mengandalkan perintah bawaan `/gamerule`. Tidak ada perintah kustom (seperti `/durability set` atau `/durability reload`) yang ditambahkan, memastikan kompatibilitas asli 100% dengan command block, function, sistem izin, dan datapack.

### Tugas Administratif Umum

#### 1. Mengonfigurasi Buff Kelangsungan Hidup Standar
```mcfunction
# Double all items globally (200% durability - Default)
/gamerule ig:dm_percent_global 200

# Give mining tools a 5x (500%) lifespan
/gamerule ig:dm_percent_tools 500

# Give weapons a 3x (300%) lifespan
/gamerule ig:dm_percent_weapons 300
```

#### 2. Mengonfigurasi Pengaturan Pertempuran & Server PvP
```mcfunction
# Keep armor at vanilla durability (100%) to prevent overly tanky players
/gamerule ig:dm_percent_armor 100

# Give weapons 2x (200%) durability
/gamerule ig:dm_percent_swords 200
/gamerule ig:dm_percent_bows 200
```

#### 3. Mengaktifkan Kelangsungan Hidup Gaya Kreatif (Elytra & Alat Tak Terpatahkan)
```mcfunction
# Unbreakable Elytra for limitless exploration
/gamerule ig:dm_infinity_elytra true

# Unbreakable Tools for building mega-projects
/gamerule ig:dm_infinity_tools true
```

#### 4. Mengonfigurasi Item Mod Dinamis
```mcfunction
# Give a custom modded drill 400% durability
/gamerule ig:percent_techmod_titanium_drill 400

# Make a modded staff unbreakable
/gamerule ig:infinity_magicmod_staff_of_fire true

# Set a modded dagger to Single-Use using the -1 sentinel
/gamerule ig:percent_customweapons_glass_dagger -1
```

#### 5. Menyembunyikan Teks Tooltip
```mcfunction
# Hide durability multiplier info from item tooltips
/gamerule ig:dm_show_tooltip false
```

