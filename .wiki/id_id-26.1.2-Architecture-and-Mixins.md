# Arsitektur & Deskriptor Mixin (26.1.2)

| Properti Arsitektur | Nilai |
| :--- | :--- |
| **Paket Utama** | `net.instantgratification.durabilitymultiplier` |
| **Tingkat Kompatibilitas** | `JAVA_25` |
| **Konfigurasi Mixin** | `durability-multiplier.mixins.json` |
| **Syarat Injektor Default** | `1` |
| **Pelindung Re-entry** | `ThreadLocal<Boolean> dm$processing` |

---

## 🌳 Hierarki Paket ASCII

```
net.instantgratification.durabilitymultiplier/
├── DurabilityHelper.java               # Stateless damage reduction & classification engine
├── DurabilityMultiplier.java           # Common mod initializer & logger
├── DurabilityMultiplierFabric.java     # Fabric main entrypoint
├── DurabilityMultiplierFabricClient.java # Client entrypoint & network receiver
├── config/
│   ├── ClothConfigScreenHelper.java    # Optional Cloth Config GUI builder
│   ├── DurabilityConfig.java           # JSON configuration loader & save handler
│   └── ModMenuIntegration.java         # ModMenu entrypoint hook
├── mixin/
│   ├── GameRulesMixin.java             # Intercepts GameRules.set() for network sync
│   ├── ItemStackDurabilityMixin.java   # Intercepts hurtAndBreak() for damage reduction
│   └── ItemStackTooltipMixin.java      # Intercepts addDetailsToTooltip() for status lines
├── network/
│   ├── DurabilityClientState.java      # Client-side cache of 73 GameRules & dynamic mod rules
│   ├── DurabilityNetworking.java       # S2C packet registration & player sync logic
│   └── DurabilityPayload.java          # CustomPacketPayload record definition
└── registry/
    └── DurabilityRules.java            # GameRuleCategory & 73 GameRules definitions
```

---

## 💉 Rincian Lengkap Target Mixin

### 1. `ItemStackDurabilityMixin`
* **Kelas Target**: `net.minecraft.world.item.ItemStack`
* **Metode Target**: `hurtAndBreak(ILnet/minecraft/server/level/ServerLevel;Lnet/minecraft/server/level/ServerPlayer;Ljava/util/function/Consumer;)V`
* **Titik Injeksi**: `@At("HEAD")`, `cancellable = true`
* **Metode Penangan**: `dm$hurtAndBreak(int amount, ServerLevel level, @Nullable ServerPlayer player, Consumer<Item> onBreak, CallbackInfo ci)`
* **Rasional Desain**: Titik corong tunggal untuk semua kerusakan item di Minecraft. Dilindungi dari loop tak terbatas dengan `ThreadLocal<Boolean> dm$processing` saat memanggil ulang `hurtAndBreak` dengan nilai kerusakan yang disesuaikan.

### 2. `ItemStackTooltipMixin`
* **Kelas Target**: `net.minecraft.world.item.ItemStack`
* **Metode Target**: `addDetailsToTooltip(Item.TooltipContext, TooltipDisplay, Player, TooltipFlag, Consumer<Component>)V`
* **Titik Injeksi**: `@At("TAIL")`
* **Metode Penangan**: `dm$addDurabilityTooltip(...)`
* **Rasional Desain**: Menambahkan teks tebal emas `✦ UNBREAKABLE`, abu-abu `⟨SINGLE-USE⟩`, atau abu-abu `⟨Ketahanan Kategori Nx⟩` / `⟨Ketahanan Kategori P%⟩` pada tooltip item.

### 3. `GameRulesMixin`
* **Kelas Target**: `net.minecraft.world.level.gamerules.GameRules`
* **Metode Target**: `set(GameRule<T>, T, MinecraftServer)V`
* **Titik Injeksi**: `@At("TAIL")`
* **Metode Penangan**: `onSet(GameRule<T> key, T value, @Nullable MinecraftServer server, CallbackInfo ci)`
* **Rasional Desain**: Saat aturan dalam kategori `DURABILITY_MULTIPLIER` berubah, memicu `DurabilityNetworking.syncToAll(server)` untuk menyinkronkan nilai terbaru ke semua klien secara real time.

---

## 🔄 Pemindaian Registri Item Mod Dinamis

Pendaftaran item dinamis ditenagai oleh `DynamicRegistryScanner` dari **`DasikLibrary`**:
* **Metode Hook**: `DynamicRegistryScanner.subscribe(BuiltInRegistries.ITEM, DurabilityRules::isItemDamageable, (id, item) -> { ... })`
* **Siklus Hidup**: Pemindai penemuan universal 3 tingkat (Penyisiran saat booting, callback langsung selama mod luar dimuat, dan penyisiran pengaman saat server mulai).
* **Nol Mixin Registri Kustom**: Menggantikan mixin registri manual dengan callback event standar yang aman dari crash classloader.
