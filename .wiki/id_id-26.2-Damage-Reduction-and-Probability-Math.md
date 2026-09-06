# Pengurangan Kerusakan & Matematika Probabilitas (26.2)

| Properti Matematika | Nilai |
| :--- | :--- |
| **Total Unit Kerusakan** | $\text{amount} \times 100$ |
| **Pembagian Bilangan Bulat Dasar** | $\lfloor (\text{amount} \times 100) / \text{percent} \rfloor$ |
| **Sisa Pembagian (Modulo)** | $(\text{amount} \times 100) \pmod{\text{percent}}$ |
| **Evaluasi Acak** | `random.nextInt(percent) < remainder` |
| **Batas Bilangan Bulat** | $\ge 0$ (Default global: 200, Timpaan: 0 = mewarisi) |
| **Jaminan Kerusakan Nol** | Dijamin $0$ saat Mode Kebal atau saat lemparan probabilitas penyerapan berhasil |

---

## 🛡️ Mengapa Pencegahan Kerusakan (Keamanan Simpanan 100%)?

Durability Multiplier is engineered to **never mutate item NBT or `DataComponents`** stored in world save files. 

### Mengapa Tidak Mengubah `DataComponents.MAX_DAMAGE` Secara Langsung?
1. **Nol Kontaminasi Dunia**: Mengubah batas ketahanan maksimum item akan menanamkan nilai modifikasi secara permanen ke inventaris, peti, dan berkas simpanan dunia. Jika mod dihapus, item akan tetap rusak atau terdistorsi.
2. **Penerapan Seketika**: Mengubah `/gamerule ig:dm_percent_global` langsung berlaku pada semua item di dunia tanpa perlu memindai inventaris atau membuat ulang item.
3. **Keseimbangan Perbaikan Paron & Mending**: Biaya paron dan penyerapan XP sihir Perbaikan dihitung berdasarkan ketahanan standar vanilla tanpa luapan bilangan bulat.

Sebaliknya, mod ini mencegat peristiwa kerusakan secara dinamis saat runtime melalui `ItemStackDurabilityMixin` dan menerapkan **Skala Kerusakan Probabilistik** (arsitektur yang persis sama dengan sihir bawaan Minecraft **Tak Terpatahkan / Unbreaking**).

---

## 📐 Algoritma Penskalaan yang Tepat

Saat item digunakan (menghasilkan kerusakan `originalAmount`, biasanya 1 untuk menghancurkan blok biasa atau mengayunkan alat):

```java
public static int calculateScaledDamage(int originalAmount, int percent, RandomSource random) {
    if (originalAmount <= 0)
        return 0;
    if (percent <= 0 || percent == 100)
        return originalAmount;

    int totalDamageUnits = originalAmount * 100;
    int baseDamage = totalDamageUnits / percent;
    int remainder = totalDamageUnits % percent;
    if (remainder > 0 && random.nextInt(percent) < remainder) {
        baseDamage++;
    }
    return baseDamage;
}
```

---

## 🎲 Matriks Distribusi Probabilitas

### Untuk Peristiwa 1 Kerusakan (`originalAmount = 1`)

| Persentase | Pengali Efektif | Dasar (`100 / P`) | Sisa (`100 % P`) | Peluang Kerusakan per Pukulan | Ekspektasi Kerusakan per Pukulan | Ketahanan Relatif |
| :-: | :-: | :-: | :-: | :-: | :-: | :-: |
| **25%** | $0.25\times$ | `4` | `0` | $100\%$ (4 kerusakan) | $4.00$ | $0.25\times$ (4x lebih aus) |
| **50%** | $0.50\times$ | `2` | `0` | $100\%$ (2 kerusakan) | $2.00$ | $0.50\times$ (2x lebih aus) |
| **75%** | $0.75\times$ | `1` | `25` | $100\%$ (1) + $33.3\%$ (+1) | $1.33$ | $0.75\times$ (1.33x lebih aus) |
| **100%** | $1.00\times$ (Vanilla) | `1` | `0` | $100\%$ (1 kerusakan) | $1.00$ | $1.00\times$ (Standar) |
| **150%** | $1.50\times$ | `0` | `100` | $\frac{100}{150} \approx 66.67\%$ (1 kerusakan) | $0.67$ | $1.50\times$ |
| **200%** | $2.00\times$ | `0` | `100` | $\frac{100}{200} = 50.00\%$ (1 kerusakan) | $0.50$ | $2.00\times$ |
| **300%** | $3.00\times$ | `0` | `100` | $\frac{100}{300} \approx 33.33\%$ (1 kerusakan) | $0.33$ | $3.00\times$ |
| **500%** | $5.00\times$ | `0` | `100` | $\frac{100}{500} = 20.00\%$ (1 kerusakan) | $0.20$ | $5.00\times$ |
| **1000%** | $10.00\times$ | `0` | `100` | $\frac{100}{1000} = 10.00\%$ (1 kerusakan) | $0.10$ | $10.00\times$ |

---

## 📈 Lemparan Acak Independen & Hukum Bilangan Besar

Karena penyerapan kerusakan dievaluasi secara independen pada setiap pukulan (seperti Tak Terpatahkan Vanilla):
* Pada **500% (5x)**, setiap pukulan melempar peluang independen $20\%$ menerima 1 kerusakan dan $80\%$ menerima 0.
* Dalam pengujian singkat, sebuah alat mungkin berkurang setelah 2 kali pakai atau baru setelah 8 kali pakai.
* Sepanjang masa pakai total alat (misalnya 1.561 penggunaan pada Kapak Berlian), total blok yang dihancurkan secara matematis mendekati **$\approx 7.805$ penggunaan** (tepat $5\times$).

Misalkan $N$ adalah ketahanan vanilla suatu item, dan $P$ adalah persentase aktif ($P \ge 100$). Jumlah penggunaan $U$ hingga item hancur mengikuti distribusi binomial negatif dengan rata-rata:

$$\mathbb{E}[U] = N \times \frac{P}{100}$$

Melalui ribuan kali penggunaan, hukum bilangan besar menjamin bahwa total ketahanan akan konvergen menjadi **tepat $\frac{P}{100}$ kali ketahanan vanilla**.
