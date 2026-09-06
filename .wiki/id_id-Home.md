# Wiki Resmi Durability Multiplier

🌐 **Languages**: [[🇺🇸 English|Home]] | [[🇨🇳 简体中文|zh_cn-Home]] | [[🇭🇰 繁體中文|zh_tw-Home]] | [[🇷🇺 Русский|ru_ru-Home]] | [[🇪🇸 Español|es_es-Home]] | [[🇩🇪 Deutsch|de_de-Home]] | [[🇫🇷 Français|fr_fr-Home]] | [[🇧🇷 Português|pt_br-Home]] | [[🇯🇵 日本語|ja_jp-Home]] | [[🇮🇩 Bahasa Indonesia|id_id-Home]] | [[🇰🇷 한국어|ko_kr-Home]]

Selamat datang di dokumentasi teknis dan gameplay resmi untuk **Durability Multiplier** (Instant Gratification Collection), yang dirancang oleh **Dasik (Rifaditya)**.

> 📌 **Pernyataan Sumber Repositori**: Dokumentasi di Wiki ini mencerminkan **status kode sumber saat ini di repositori**, yang mungkin mencakup komit terbaru yang belum dirilis sebelum build publik resmi di CurseForge dan Modrinth.

---

## 🧭 Portal Papan Hubung Multi-Versi

Durability Multiplier dirancang untuk berbagai versi Minecraft utama. Pilih versi Minecraft Anda di bawah ini untuk membuka dokumentasi khususnya:

| Versi Minecraft | Era Rilis | Build yang Didukung | Versi Java | Toolchain Loom | Masuk Wiki |
| :--- | :--- | :--- | :---: | :---: | :--- |
| **Minecraft 26.2** | Modern Sovereign Era | `1.2.14+26.2` | Java 25 | Loom 1.15.2 | [[👉 Buka Wiki MC 26.2\|id_id-26.2-Home]] |
| **Minecraft 26.1.2** | Modern Sovereign Era | `1.1.21+26.1.2` | Java 25 | Loom 1.15.2 | [[👉 Buka Wiki MC 26.1.2\|id_id-26.1.2-Home]] |

---

## ⚡ Filosofi Inti & Arsitektur

Durability Multiplier termasuk dalam jalur desain **Instant Gratification (IG)**. Mandat utamanya adalah menghilangkan **"Friksi Pemeliharaan"** dalam survival Minecraft:

* **Menghargai Waktu Pemain**: Menghilangkan siklus perbaikan alat yang melelahkan, penghentian penambangan, dan kerusakan perlengkapan yang tidak disengaja.
* **Pengurangan Kerusakan Matematis Murni**: Perpanjangan ketahanan dihitung melalui pembagian bilangan bulat dan pembulatan probabilistik pada kerusakan yang masuk, memastikan presisi matematis tanpa merusak atribut bawaan vanilla.
* **Kendali Terperinci**: Konfigurasikan 24 kategori item individu secara independen melalui 73 GameRules statis.
* **Mode Kebal (Tak Terbatas)**: Jadikan kategori apa pun atau semua kategori 100% tak bisa hancur dengan satu GameRule boolean.
* **Deteksi Item Mod Otomatis**: Menemukan item berkurangnya ketahanan dari mod luar dan menyediakan GameRules serta kontrol GUI secara dinamis.
* **Nol Desinkronisasi Sisi**: GameRules server disinkronkan ke klien yang terhubung melalui jaringan Fabric (`durability-multiplier:sync_rules`) untuk indikator tooltip langsung secara instan.

---

## 📚 Navigasi Global & Sumber Daya

* [[Matriks Kompatibilitas Versi|id_id-Version-Compatibility]]
* [[Pusat Dokumentasi MC 26.2|id_id-26.2-Home]]
* [[Pusat Dokumentasi MC 26.1.2|id_id-26.1.2-Home]]
* [Halaman Platform CurseForge](https://curseforge.com/minecraft/mc-mods/durability-multiplier)
* [Halaman Platform Modrinth](https://modrinth.com/mod/durability-multiplier)
* [Repositori Kode Sumber GitHub](https://github.com/Rifaditya/Instant-Gratification-Durability-Multiplier)
