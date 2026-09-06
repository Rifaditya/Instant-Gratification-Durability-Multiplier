# Durability Multiplier — Pusat Dokumentasi Minecraft 26.2

Selamat datang di dokumentasi resmi **Durability Multiplier** untuk **Minecraft 26.2** (`1.2.14+26.2`).

> 📌 **Pernyataan Sumber Repositori**: Dokumentasi di Wiki ini mencerminkan **status kode sumber saat ini di repositori**, yang mungkin mencakup komit terbaru yang belum dirilis sebelum build publik resmi di CurseForge dan Modrinth.

---

## 📋 Cuplikan Teknis (26.2)

| Parameter | Nilai | Deskripsi |
| :--- | :--- | :--- |
| **Pengenal Mod** | `durability-multiplier` | ID mod ber-namespace di Fabric Loader |
| **Versi Mod** | `1.2.14+26.2` | Tag rilis SemVer |
| **Target Minecraft** | `26.2` (`>=26.2-`) | Jangkar versi asli |
| **Rilis Java** | Java 25 | Dikompilasi dengan `release = 25` |
| **Fabric Loader** | `>=0.16.9` | Persyaratan loader minimum |
| **Fabric API** | `0.150.1+26.2` | Persyaratan runtime Fabric API |
| **DasikLibrary** | `1.8.28` | Inti arsitektur bersama |
| **GameRules Terdaftar** | **73 Aturan Statis** + Aturan Mod Dinamis | 24 Persentase, 24 Kebal, 24 Sekali Pakai, 1 Tooltip |
| **Titik Injeksi Mixin** | 3 Kelas Target | `ItemStack`, `GameRules` |
| **Penulis & Lisensi** | **Dasik (Rifaditya)** / GPL-3.0-or-later | Modifikasi sumber terbuka |

---

## 🧭 Matriks Navigasi (26.2)

### 🎮 Panduan Pemain & Gameplay
* [[Pengali Ketahanan & Kategori|id_id-26.2-Durability-Multipliers]] — Sistem persentase terperinci 24 kategori dan hierarki penimpaan.
* [[Mode Kebal & Tak Terbatas|id_id-26.2-God-Mode-and-Infinity]] — Pengaturan kekebalan nol-kerusakan di 24 kategori.
* [[Matematika Pengurangan Kerusakan & Peluang|id_id-26.2-Damage-Reduction-and-Probability-Math]] — Formula matematis dan pembulatan probabilistik.
* [[Klasifikasi Item & Kompatibilitas Mod|id_id-26.2-Item-Classification-and-Mod-Compatibility]] — Cara item vanilla dan mod diklasifikasikan.
* [[Pemindaian Item Mod Dinamis|id_id-26.2-Dynamic-Modded-Item-Registration]] — Pemindai penemuan universal 3 tingkat dan pengisian otomatis.
* [[Indikator Tooltip & HUD|id_id-26.2-Tooltip-Indicators-and-HUD]] — Penayangan tooltip item di sisi klien.
* [[Tabel Referensi GameRules|id_id-26.2-GameRules]] — Tabel referensi lengkap untuk seluruh 73 GameRule statis.
* [[Perintah & Administrasi Dalam Game|id_id-26.2-Commands-and-Administration]] — Mengelola pengaturan lewat `/gamerule`.
* [[Kemajuan & Pencapaian|id_id-26.2-Advancements]] — Kebijakan ketiadaan dan integrasi vanilla.
* [[GUI Konfigurasi & Bawaan Dunia|id_id-26.2-Configuration]] — Integrasi ModMenu & Cloth Config.
* [[Perlindungan Runtime ModVersionGuard|id_id-26.2-ModVersionGuard-and-Safety]] — Pelindung versi tanpa ketergantungan.
* [[Pemecahan Masalah & FAQ|id_id-26.2-Troubleshooting-and-FAQ]] — Prosedur diagnostik dan pertanyaan umum.

### 💻 Referensi Pengembang & Teknis
* [[Arsitektur & Deskriptor Mixin|id_id-26.2-Architecture-and-Mixins]] — Hierarki paket, hook injeksi, dan keamanan re-entry.
* [[Sinkronisasi Jaringan & Protokol Payload|id_id-26.2-Network-Sync-and-Payload-Protocol]] — Protokol sinkronisasi S2C (`DurabilityPayload`).
* [[Pengaturan Pengembang & Kompilasi|id_id-26.2-Developer-Setup-and-Building]] — Perintah Gradle, toolchain Loom, dan JDK.
* [[Integrasi API & Addon|id_id-26.2-API-and-Addon-Integration]] — Memperluas mod, `DurabilityHelper`, dan aturan kustom.
