# Konfigurasi & Integrasi GUI (26.1.2)

| Parameter Sistem | Nilai |
| :--- | :--- |
| **Jalur Berkas Konfigurasi** | `config/durability-multiplier.json` |
| **Versi Konfigurasi** | `2` (Dimigrasi otomatis dari v1) |
| **Penyedia GUI** | Cloth Config (`me.shedaniel.cloth:cloth-config-fabric`) & ModMenu |
| **Kelas Konfigurasi** | `net.instantgratification.durabilitymultiplier.config.DurabilityConfig` |
| **Helper GUI** | `ClothConfigScreenHelper` & `ModMenuIntegration` |
| **Hukum Presedensi** | Berkas konfigurasi **HANYA menetapkan bawaan dunia baru**; dunia aktif memakai GameRules |

---

## ⚙️ Struktur Berkas Konfigurasi (`config/durability-multiplier.json`)

Berkas konfigurasi menetapkan pengaturan dasar dan nilai bawaan untuk semua dunia singleplayer baru serta server multipemain. Berkas ini mendukung persentase ketahanan, Mode Kebal (Tak Terbatas), Sekali Pakai (Mode Kaca), pemformatan tooltip, dan penimpaan item mod dinamis.

```json
{
  "configVersion": 2,
  
  "percentGlobal": 200,
  "percentWeapons": 0,
  "percentSwords": 0,
  "percentSpears": 0,
  "percentTridents": 0,
  "percentMaces": 0,
  "percentBows": 0,
  "percentCrossbows": 0,
  "percentTools": 0,
  "percentPickaxes": 0,
  "percentAxes": 0,
  "percentShovels": 0,
  "percentHoes": 0,
  "percentShears": 0,
  "percentFishingRods": 0,
  "percentBrushes": 0,
  "percentFlintAndSteel": 0,
  "percentArmor": 0,
  "percentHelmets": 0,
  "percentChestplates": 0,
  "percentLeggings": 0,
  "percentBoots": 0,
  "percentElytra": 0,
  "percentShields": 0,
  
  "infinityGlobal": false,
  "infinityWeapons": false,
  "infinitySwords": false,
  "infinitySpears": false,
  "infinityTridents": false,
  "infinityMaces": false,
  "infinityBows": false,
  "infinityCrossbows": false,
  "infinityTools": false,
  "infinityPickaxes": false,
  "infinityAxes": false,
  "infinityShovels": false,
  "infinityHoes": false,
  "infinityShears": false,
  "infinityFishingRods": false,
  "infinityBrushes": false,
  "infinityFlintAndSteel": false,
  "infinityArmor": false,
  "infinityHelmets": false,
  "infinityChestplates": false,
  "infinityLeggings": false,
  "infinityBoots": false,
  "infinityElytra": false,
  "infinityShields": false,
  
  "singleUseGlobal": false,
  "singleUseWeapons": false,
  "singleUseSwords": false,
  "singleUseSpears": false,
  "singleUseTridents": false,
  "singleUseMaces": false,
  "singleUseBows": false,
  "singleUseCrossbows": false,
  "singleUseTools": false,
  "singleUsePickaxes": false,
  "singleUseAxes": false,
  "singleUseShovels": false,
  "singleUseHoes": false,
  "singleUseShears": false,
  "singleUseFishingRods": false,
  "singleUseBrushes": false,
  "singleUseFlintAndSteel": false,
  "singleUseArmor": false,
  "singleUseHelmets": false,
  "singleUseChestplates": false,
  "singleUseLeggings": false,
  "singleUseBoots": false,
  "singleUseElytra": false,
  "singleUseShields": false,
  
  "showTooltip": true,
  "tooltipFormat": "ADAPTIVE",
  
  "forcedItems": [],
  "forcedPercentages": {},
  "forcedInfinities": {},
  "forcedSingleUses": {}
}
```

---

## 🔄 Sistem Pengisian Otomatis (Auto-Populate)

Durability Multiplier dilengkapi **Pemindai Penemuan Universal 3 Tingkat** otonom yang secara otomatis mengkatalogkan item mod tanpa perlu entri data manual:

1. **Penyisiran Booting**: Saat klien/server dijalankan, engine memindai `BuiltInRegistries.ITEM`.
2. **Filter Ketahanan**: Item dari namespace mod luar (kecuali `minecraft` dan tag konvensi umum `c`) diperiksa apakah memiliki `DataComponents.MAX_DAMAGE > 0`.
3. **Pengisian Otomatis**: Item berkurangnya ketahanan yang ditemukan otomatis ditambahkan ke:
   * `"forcedItems"`: Added to the active item registry list.
   * `"forcedPercentages"`: Added with default value `0` (indicating the item dynamically inherits its category or global multiplier).
4. **Penyimpanan Konfigurasi**: Daftar yang diperbarui disimpan kembali ke `config/durability-multiplier.json`, membuat semua item mod langsung terlihat dan dapat diedit di GUI Cloth Config / ModMenu serta GameRules dalam game.

---

## 🛠️ Panduan Konfigurasi Item Manual

Pembuat modpack, administrator server, dan pemain dapat secara manual menentukan aturan kustom untuk item tertentu langsung di `config/durability-multiplier.json`:

### 1. `forcedItems` (Pendaftaran Item)
Mendeklarasikan daftar pengenal sumber daya item yang dikenali oleh mod.
```json
"forcedItems": [
  "techmod:plasma_cutter",
  "magicmod:staff_of_fire",
  "customweapons:obsidian_blade"
]
```

### 2. `forcedPercentages` (Persentase Daya Tahan per Item)
Menetapkan pengali persentase ketahanan eksplisit ke item tertentu:
* `0`: Mewarisi dari kategori induk atau pengali global.
* `100`: Garis dasar 100% vanilla (ketahanan 1x).
* `200`: Ketahanan 200% (usia pakai 2x).
* `50`: Ketahanan 50% (usia pakai setengah / 2x lebih cepat aus).
* `-1`: Sekali Pakai (Mode Kaca - hancur pada pukulan pertama).
```json
"forcedPercentages": {
  "techmod:plasma_cutter": 300,
  "customweapons:obsidian_blade": -1,
  "magicmod:training_wand": 50
}
```

### 3. `forcedInfinities` (Mode Dewa per Item)
Memberikan status tak bisa hancur permanen pada item tertentu:
```json
"forcedInfinities": {
  "magicmod:creative_staff": true,
  "adminmod:ban_hammer": true
}
```

### 4. `forcedSingleUses` (Mode Kaca per Item)
Memaksa item tertentu hancur seketika setelah kehilangan ketahanan satu kali:
```json
"forcedSingleUses": {
  "techmod:disposable_cutter": true,
  "survivalplus:glass_dagger": true
}
```

---

## ⚡ Nilai Sentinel `-1` Mode Kaca untuk Pengguna Mahir

Durability Multiplier menyertakan **Nilai Sentinel `-1`** untuk persentase ketahanan:
* Menyetel aturan persentase atau bidang konfigurasi ke `-1` (atau bilangan bulat negatif apa pun) otomatis memicu **Sekali Pakai (Mode Kaca)** untuk item atau kategori tersebut.
* Saat aktif, item menerima kerusakan `maxDamage - damageValue` pada pukulan pertamanya, mengurangi ketahanannya menjadi 0 dan hancur tepat dalam 1 kali pakai.
* Ini memudahkan admin server dan pembuat paket untuk memberlakukan mekanik hancur 1 kali pakai langsung lewat slider atau perintah `/gamerule`.

---

## 🎨 Format Tampilan Tooltip

Opsi `tooltipFormat` mengonfigurasi bagaimana bonus ketahanan ditampilkan pada tooltip item:

| Pengaturan Format | Contoh Tampilan (200% / 2x) | Contoh Tampilan (150% / 1.5x) | Deskripsi |
| :--- | :--- | :--- | :--- |
| `"ADAPTIVE"` *(Default)* | `⟨Ketahanan Pedang 2x⟩` | `⟨Ketahanan Pedang 150%⟩` | Menampilkan pengali bilangan bulat rapi untuk kelipatan ratusan; selain itu persentase. |
| `"PERCENTAGE"` | `⟨Ketahanan Pedang 200%⟩` | `⟨Ketahanan Pedang 150%⟩` | Selalu menampilkan nilai persentase yang pasti. |
| `"MULTIPLIER"` | `⟨Ketahanan Pedang 2x⟩` | `⟨Ketahanan Pedang 1.5x⟩` | Selalu menampilkan teks faktor pengali berformat. |

Setel `"showTooltip": false` untuk menyembunyikan indikator ketahanan sepenuhnya.

---

## ⚠️ Peringatan Penting Prioritas Konfigurasi

> ⚠️ **Pemberitahuan**: Perubahan di `durability-multiplier.json` atau GUI ModMenu **hanya menetapkan nilai dasar untuk dunia yang baru dibuat**.
> 
> Untuk dunia yang sudah ada, setiap dunia menyimpan status GameRule-nya sendiri di dalam data dunia (`level.dat`). Untuk mengubah pengaturan di dunia aktif, gunakan perintah `/gamerule` di dalam game atau layar edit GameRules bawaan.

