# Kemajuan & Pencapaian (26.2)

| Parameter Sistem | Status |
| :--- | :--- |
| **JSON Kemajuan Kustom** | **Tidak Ada** (Sengaja Ditiadakan Sesuai Desain) |
| **Cakupan** | Pengubah Gameplay Murni / Utilitas Instant Gratification |
| **Kemajuan Vanilla** | 100% Kompatibel dengan semua kemajuan Cerita & Peternakan vanilla |

---

## 📌 Deklarasi Kebijakan Ketiadaan (Absence Policy)

Sesuai dengan filosofi **Instant Gratification (IG)** dan arsitektur jejak minimal proyek:
* Durability Multiplier **tidak memiliki pohon JSON kemajuan kustom sama sekali**.
* Mod ini **tidak** mendaftarkan pemicu kustom, kriteria, atau notifikasi toast.

---

## 🌾 Kompatibilitas Kemajuan Vanilla

Semua kemajuan vanilla tetap terpicu secara alami:
* Menambang bijih berlian memicu **"Berlian!"** (`minecraft:story/mine_diamond`).
* Meluncur dengan Elitra memicu **"Bukan Batas Langit"** (`minecraft:end/elytra`).
* Menangkis serangan dengan perisai memicu **"Tidak Hari Ini, Terima Kasih"** (`minecraft:story/deflect_arrow`).

Karena pengurangan ketahanan item terjadi secara transparan di dalam `ItemStack.hurtAndBreak`, kemajuan yang melacak penggunaan perkakas, pembunuhan dengan senjata, atau kerusakan zirah tetap dievaluasi tanpa hambatan apa pun.
