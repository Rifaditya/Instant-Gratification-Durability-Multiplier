# Pemecahan Masalah & FAQ (26.2)

| Topik Sistem | Ringkasan |
| :--- | :--- |
| **Perilaku Presedensi** | GameRules mengesampingkan konfigurasi di dunia aktif; konfigurasi menetapkan bawaan dunia baru |
| **Engine Perhitungan** | Pencegatan probabilistik (nol mutasi NBT, nol desinkronisasi simpanan) |
| **Toleransi Kasus Ekstrem** | 100% bebas crash saat mod dihapus, registri dilepas, atau komponen hilang |

---

## ❓ Pertanyaan yang Sering Diajukan (FAQ)

### Q1: Mengapa perubahan konfigurasi di ModMenu tidak memengaruhi dunia pemain tunggal aktif saya?
**Jawaban**: Sesuai dengan **Hukum Presedensi**, perubahan di `durability-multiplier.json` atau GUI ModMenu menetapkan nilai dasar **HANYA untuk dunia BARU**. Untuk mengubah pengaturan di dunia Anda saat ini, gunakan perintah `/gamerule` dalam game (misal `/gamerule ig:dm_percent_tools 500`) atau layar pengeditan GameRules.

### Q2: Mengapa tooltip item tidak menampilkan teks persentase atau pengali?
**Jawaban**:
1. Pastikan item merupakan item yang memiliki ketahanan (`DataComponents.MAX_DAMAGE > 0`).
2. Periksa apakah `ig:dm_show_tooltip` disetel ke `true`.
3. Jika pengaturan aktif adalah `100` (100% ketahanan vanilla), baris tooltip ekstra tidak ditampilkan agar tooltip tetap rapi.

### Q3: Mengapa alat 500% (5x) saya mengalami pengurangan daya tahan hanya setelah 2 kali pukulan?
**Jawaban**: Durability Multiplier menggunakan **pencegatan kerusakan probabilistik** (mekanik yang persis sama dengan sihir *Tak Terpatahkan / Unbreaking* vanilla) untuk menjamin **keamanan simpanan dunia 100%**. Pada 500% (5x ketahanan), setiap penghancuran blok memiliki peluang independen **20% (1 dari 5)** menghasilkan 1 kerusakan dan **80% peluang** menyerapnya. Karena setiap pukulan melempar peluang secara independen, kadang ketahanan berkurang setelah 2 kali pakai atau baru berkurang setelah 8 kali pakai, tetapi secara keseluruhan alat tersebut akan bertahan tepat 5x lebih lama (~7.805 penghancuran blok untuk beliung berlian).

### Q4: Haruskah saya memasukkan angka desimal seperti 0.5 atau 1.5 ke dalam GameRules?
**Jawaban**: **Tidak**. GameRules Minecraft hanya menerima bilangan bulat (`int`). Selalu masukkan angka persentase utuh:
* `50` untuk 50% (setengah ketahanan / 2x lebih aus)
* `100` untuk 100% (standar vanilla 1x)
* `150` untuk 150% (peningkatan ketahanan 1.5x)
* `200` untuk 200% (ketahanan ganda 2x)
* `-1` untuk Sekali Pakai (Mode Kaca / hancur dalam 1 pukulan)

### Q5: Apakah Durability Multiplier berfungsi bersama sihir Unbreaking (Tak Terpatahkan)?
**Jawaban**: Ya! Durability Multiplier menyesuaikan kerusakan masuk **sebelum** pemrosesan sihir vanilla. Beliung dengan Tak Terpatahkan III dan setelan 200% (2x) akan bertahan sekitar $4 \times 2 = 8\times$ lebih lama dibanding beliung vanilla tanpa sihir.

### Q6: Bagaimana cara mengaktifkan Mode Kaca 1-Pukulan (Sekali Pakai) untuk suatu item?
**Jawaban**: Anda dapat melakukan salah satu dari langkah berikut:
1. Menyetel GameRule sekali pakai ke true: `/gamerule ig:dm_single_use_swords true` (atau `/gamerule ig:single_use_<mod>_<item> true`).
2. Gunakan **Sentinel `-1` Pengguna Mahir**: setel aturan persentase ke `-1`, misal `/gamerule ig:dm_percent_swords -1` atau `/gamerule ig:percent_<mod>_<item> -1`.

---

## 🔍 Kasus Ekstrem Mendalam & Perilaku Siklus Hidup

### Kasus Ekstrem 1: Pencopotan Pemasangan Mod & Penghapusan Item
Saat pemain menghapus mod yang item kustomnya telah terdaftar di Durability Multiplier:
1. **Keamanan Berkas Konfigurasi**: ID item yang dihapus tetap tersimpan dengan aman di dalam `forcedItems` dan `forcedPercentages` di `config/durability-multiplier.json`.
2. **Status Dunia Tidur**: Setiap GameRule dinamis yang tersimpan di `level.dat` dunia akan berada dalam keadaan tidur (dormant) di memori.
3. **Nol Crash & Nol Kerusakan Data**: Karena pencarian item dilindungi oleh `BuiltInRegistries.ITEM.getKey(stack.getItem())`, game tidak akan pernah mencari kelas yang hilang atau ID yang tak terpetakan. Tidak ada `NullPointerException` atau kerusakan chunk yang bisa terjadi.
4. **Pemulihan Otomatis Saat Dipasang Ulang**: Jika mod tersebut dipasang kembali di kemudian hari, semua konfigurasi persentase, Mode Kebal, dan Sekali Pakai sebelumnya akan **seketika terhubung kembali** tanpa perlu dikonfigurasi ulang!
5. **Pembersihan Konfigurasi Manual (Opsional)**: Jika Anda ingin menghapus entri mod yang sudah dihapus dari konfigurasi:
   * Open `config/durability-multiplier.json` in a text editor.
   * Remove the deleted item ID string from the `"forcedItems"` list.
   * Remove the corresponding keys from `"forcedPercentages"`, `"forcedInfinities"`, and `"forcedSingleUses"`.
   * Save the file.

---

### Kasus Ekstrem 2: Penyaringan Ketat Daya Tahan (`MAX_DAMAGE > 0`)
Mengapa mod furnitur/mebel (misal kursi/lemari Macaw's Furniture), blok bangunan, makanan, atau bahan kerajinan tidak muncul di GameRules atau `durability-multiplier.json`?
* Durability Multiplier secara ketat memverifikasi `DataComponents.MAX_DAMAGE > 0` sebelum mendaftarkan item apa pun.
* Item tanpa komponen ketahanan (blok, makanan, batangan, benih) ditolak dalam $0.0001\mu\text{s}$ selama penyisiran booting.
* Ini mencegah polusi namespace dan memastikan saran Tab GameRules tetap bersih dan responsif.

---

### Kasus Ekstrem 3: Hierarki Evaluasi & Prioritas Lengkap
Saat suatu item menerima kerusakan ketahanan, hasilnya ditentukan oleh hierarki evaluasi ketat berikut:

$$\text{God Mode (Infinity)} \longrightarrow \text{Single-Use (Glass Mode)} \longrightarrow \text{Per-Item Override} \longrightarrow \text{Subcategory} \longrightarrow \text{Parent Category} \longrightarrow \text{Global} \longrightarrow \text{100\% Vanilla}$$

1. **Pemeriksaan Mode Kebal (Tak Terbatas)**:
   * Per-Item God Mode (`ig:infinity_<mod>_<item>` / `forcedInfinities`)
   * Subcategory God Mode (`ig:dm_infinity_pickaxes`, `ig:dm_infinity_swords`, etc.)
   * Parent Category God Mode (`ig:dm_infinity_tools`, `ig:dm_infinity_weapons`, `ig:dm_infinity_armor`)
   * Global God Mode (`ig:dm_infinity_global`)
   * *If any is `true` $\rightarrow$ Damage = $0$ (Unbreakable).*
2. **Pemeriksaan Sekali Pakai (Mode Kaca)**:
   * Effective percentage $\le -1$ (`-1` Glass Mode Sentinel)
   * Per-Item Single-Use (`ig:single_use_<mod>_<item>` / `forcedSingleUses`)
   * Subcategory Single-Use (`ig:dm_single_use_pickaxes`, `ig:dm_single_use_swords`, etc.)
   * Parent Category Single-Use (`ig:dm_single_use_tools`, `ig:dm_single_use_weapons`, `ig:dm_single_use_armor`)
   * Global Single-Use (`ig:dm_single_use_global`)
   * *If active $\rightarrow$ Item loses all remaining durability in 1 hit.*
3. **Penyelesaian Skala Persentase**:
   * Per-Item Override (`ig:percent_<mod>_<item>` / `forcedPercentages`) if $\neq 0$
   * Subcategory Percentage (`ig:dm_percent_pickaxes`, `ig:dm_percent_swords`, etc.) if $\neq 0$
   * Parent Category Percentage (`ig:dm_percent_tools`, `ig:dm_percent_weapons`, `ig:dm_percent_armor`) if $\neq 0$
   * Global Percentage (`ig:dm_percent_global`) if $\neq 0$
   * Vanilla Fallback: $100\%$ baseline.

