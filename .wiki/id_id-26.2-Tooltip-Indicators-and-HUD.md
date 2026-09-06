# Indikator Tooltip & HUD (26.2)

| Parameter Sistem | Nilai |
| :--- | :--- |
| **GameRule Pengalih** | `ig:dm_show_tooltip` |
| **Status Bawaan** | `true` (Aktif) |
| **Target Mixin** | `ItemStack.addDetailsToTooltip` (`ItemStackTooltipMixin`) |
| **Titik Injeksi** | `@At("TAIL")` |
| **Gaya Mode Kebal** | `✦ UNBREAKABLE` (Emas, Tebal — `ChatFormatting.GOLD`, `BOLD`) |
| **Gaya Pengali** | `⟨Ketahanan Kategori Nx⟩` (Abu-abu — `ChatFormatting.GRAY`) |

---

## ⚡ Ringkasan & Presentasi Visual

Durability Multiplier memberikan umpan balik langsung dan jelas pada tooltip item setiap kali usia pakai item diubah.

### Gaya Visual Tooltip

| Status | Teks yang Ditampilkan | Tampilan Visual | Kode Warna |
| :--- | :--- | :--- | :--- |
| **Mode Kebal Aktif** | `✦ UNBREAKABLE` | **✦ UNBREAKABLE** | Emas, Tebal (`ChatFormatting.GOLD`, `BOLD`) |
| **Sekali Pakai (Mode Kaca)** | `⟨SINGLE-USE⟩` | ⟨SINGLE-USE⟩ | Abu-abu (`ChatFormatting.GRAY`) |
| **Pengali 200% / 2x** | `⟨Ketahanan Pedang 2x⟩` | ⟨Ketahanan Pedang 2x⟩ | Abu-abu (`ChatFormatting.GRAY`) |
| **Ketahanan 150%** | `⟨Ketahanan Baju Zirah 150%⟩` | ⟨Ketahanan Baju Zirah 150%⟩ | Abu-abu (`ChatFormatting.GRAY`) |
| **Ketahanan 50% (Setengah)** | `⟨Ketahanan Pedang 50%⟩` | ⟨Ketahanan Pedang 50%⟩ | Abu-abu (`ChatFormatting.GRAY`) |
| **Pengali 500% / 5x** | `⟨Ketahanan Beliung 5x⟩` | ⟨Ketahanan Beliung 5x⟩ | Abu-abu (`ChatFormatting.GRAY`) |
| **Penimpa Item Mod** | `⟨Ketahanan Plasma Cutter 300%⟩` | ⟨Ketahanan Plasma Cutter 300%⟩ | Abu-abu (`ChatFormatting.GRAY`) |
| **Garis Dasar Vanilla (100%)** | *(Tidak ada)* | *(Tidak ada baris tooltip ekstra yang dirender)* | — |

---

## 🎨 Mode Format Tooltip (`tooltipFormat`)

Mod ini mendukung 3 format tampilan yang dapat dikonfigurasi melalui `config/durability-multiplier.json` dan GUI ModMenu:
1. **`ADAPTIVE` (Default)**: Otomatis menampilkan pengali bulat rapi (`2x`, `5x`) untuk kelipatan ratusan, dan persentase (`50%`, `150%`) untuk lainnya.
2. **`PERCENTAGE`**: Selalu menampilkan persentase eksplisit (misal `Ketahanan Pedang 200%`).
3. **`MULTIPLIER`**: Selalu menampilkan pengali desimal (misal `Ketahanan Pedang 2x`, `Ketahanan Pedang 0.5x`).

---

## 🖥️ Eksekusi Sisi Klien & Server

```
                       [Item Tooltip Render]
                                 │
                                 ▼
                     [Is Player on Integrated Server?]
                     ├── YES ──► Read GameRules from ServerLevel
                     │           (DurabilityHelper.getTooltipLabel)
                     │
                     └── NO (Remote Server) ──► Read Synced Client Cache
                                                (DurabilityClientState)
```

1. **Server Terintegrasi (Singleplayer / Host LAN)**: Tooltip meminta data GameRules aktif `ServerLevel` langsung secara real time.
2. **Klien Khusus (Terhubung Multipemain)**: Tooltip membaca dari `DurabilityClientState` yang diperbarui lewat paket jaringan `DurabilityPayload` setiap kali GameRules server berubah.
