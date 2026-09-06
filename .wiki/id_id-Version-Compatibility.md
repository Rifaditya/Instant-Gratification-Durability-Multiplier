# Matriks Kompatibilitas Versi & Siklus Hidup

| Spesifikasi | Jangkar Minecraft 26.2 | Jangkar Minecraft 26.1.2 |
| :--- | :--- | :--- |
| **Target Versi Minecraft** | `26.2` (`>=26.2-`) | `26.1.2` (`*`) |
| **Versi Mod (SemVer)** | `1.2.14+26.2` | `1.1.21+26.1.2` |
| **Toolchain Java** | Java 25 (`release = 25`) | Java 25 (`release = 25`) |
| **Fabric Loader** | `>=0.16.9` (Dibangun di `0.19.1`) | `>=0.16.9` (Dibangun di `0.19.1`) |
| **Fabric API** | `0.150.1+26.2` | `0.145.4+26.1.2` |
| **Fabric Loom** | `1.15.2` | `1.15.2` |
| **Ketergantungan DasikLibrary** | `1.8.28` | `1.8.28` |
| **Ketergantungan Cloth Config** | `26.1.154` (Opsional) | `26.1.154` (Opsional) |
| **Ketergantungan ModMenu** | `18.0.0-beta.1` (Opsional) | `18.0.0-beta.1` (Opsional) |
| **Perlindungan ModVersionGuard** | ✅ Aktif (Pemeriksaan Kelas `EntityTypes`) | Runtime Standar |
| **Pusat Dokumentasi Khusus** | [[👉 Buka Hub MC 26.2\|id_id-26.2-Home]] | [[👉 Buka Hub MC 26.1.2\|id_id-26.1.2-Home]] |

---

## 🏛️ Arsitektur Era & Hukum "1 Jar 1 Version"

Durability Multiplier mengikuti mandat desain **1 Jar 1 Versi**:
1. Setiap rilis utama Minecraft memiliki direktori subproyek khusus sendiri (`Durability Multiplier v26.1/`, `Durability Multiplier v26.2/`).
2. Artefak rilis dikompilasi secara independen ke dalam JAR berlabel (`durability-multiplier-1.1.21+26.1.2.jar`, `durability-multiplier-1.2.14+26.2.jar`) dan dikatalogkan secara terpusat di `Archive Jar of all versions/`.
3. MC 26.2 menggabungkan verifikasi runtime `ModVersionGuard` tanpa dependensi di `onInitialize()` untuk menghentikan eksekusi dengan aman jika dimuat di lingkungan yang tidak kompatibel.

---

> 📌 **Pernyataan Sumber Repositori**: Dokumentasi di Wiki ini mencerminkan **status kode sumber saat ini di repositori**, yang mungkin mencakup komit terbaru yang belum dirilis sebelum build publik resmi di CurseForge dan Modrinth.
