# Configuración e integración de GUI (26.2)

| Parámetro del sistema | Valor |
| :--- | :--- |
| **Ruta del archivo de configuración** | `config/durability-multiplier.json` |
| **Versión de configuración** | `2` (Migración automática desde v1) |
| **Proveedores de GUI** | Cloth Config (`me.shedaniel.cloth:cloth-config-fabric`) y ModMenu |
| **Clase de configuración** | `net.instantgratification.durabilitymultiplier.config.DurabilityConfig` |
| **Ayudante de GUI** | `ClothConfigScreenHelper` y `ModMenuIntegration` |
| **Ley de precedencia** | El archivo define **SOLO VALORES PREDETERMINADOS PARA NUEVOS MUNDOS**; los mundos activos usan GameRules |

---

## ⚙️ Estructura del archivo de configuración (`config/durability-multiplier.json`)

El archivo de configuración define los ajustes base y predeterminados para todos los mundos y servidores creados. Admite porcentajes de durabilidad, Modo Dios (Infinito), Un solo uso (Modo Cristal), formato de descripciones emergentes y anulaciones para objetos de mods.

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

## 🔄 El sistema de autorrelleno

Durability Multiplier cuenta con un **Escáner universal de descubrimiento de 3 niveles** autónomo que cataloga automáticamente objetos de mods sin necesidad de entrada manual:

1. **Barrido de inicio**: Al iniciar el cliente/servidor, el motor escanea `BuiltInRegistries.ITEM`.
2. **Filtro de daño**: Los objetos de mods externos (excluyendo `minecraft` y etiquetas de convención `c`) se verifican para `DataComponents.MAX_DAMAGE > 0`.
3. **Autopoblado**: Los objetos con durabilidad descubiertos se añaden automáticamente a:
   * `"forcedItems"`: Added to the active item registry list.
   * `"forcedPercentages"`: Added with default value `0` (indicating the item dynamically inherits its category or global multiplier).
4. **Persistencia de configuración**: Las listas actualizadas se guardan en `config/durability-multiplier.json`, haciendo que todos los objetos de mods sean inmediatamente visibles y editables en la GUI y GameRules.

---

## 🛠️ Guía de configuración manual de objetos

Creadores de modpacks, administradores de servidores y jugadores pueden declarar manualmente reglas para objetos específicos en `config/durability-multiplier.json`:

### 1. `forcedItems` (Registro de objetos)
Declara la lista de identificadores de recursos reconocidos por el mod.
```json
"forcedItems": [
  "techmod:plasma_cutter",
  "magicmod:staff_of_fire",
  "customweapons:obsidian_blade"
]
```

### 2. `forcedPercentages` (Porcentajes de durabilidad por objeto)
Asigna multiplicadores de porcentaje de durabilidad explícitos a objetos específicos:
* `0`: Hereda de la categoría padre o multiplicador global.
* `100`: Base vanilla 100% (durabilidad 1x).
* `200`: Durabilidad 200% (2x vida útil).
* `50`: Durabilidad 50% (mitad de vida / 2x desgaste).
* `-1`: Un solo uso (Modo Cristal - se rompe en el primer golpe).
```json
"forcedPercentages": {
  "techmod:plasma_cutter": 300,
  "customweapons:obsidian_blade": -1,
  "magicmod:training_wand": 50
}
```

### 3. `forcedInfinities` (Modo Dios por objeto)
Otorga estado irrompible permanente a objetos específicos:
```json
"forcedInfinities": {
  "magicmod:creative_staff": true,
  "adminmod:ban_hammer": true
}
```

### 4. `forcedSingleUses` (Modo de cristal por objeto)
Obliga a que objetos específicos se rompan tras perder durabilidad una sola vez:
```json
"forcedSingleUses": {
  "techmod:disposable_cutter": true,
  "survivalplus:glass_dagger": true
}
```

---

## ⚡ Centinela de modo de cristal `-1` para usuarios avanzados

Durability Multiplier incluye un **Valor Centinela `-1`** para porcentajes de durabilidad:
* Establecer cualquier regla de porcentaje o campo de configuración en `-1` (o cualquier entero negativo) activa automáticamente **Un solo uso (Modo Cristal)** para ese objeto o categoría.
* Cuando está activo, el objeto recibe `maxDamage - damageValue` en su primer uso, reduciendo su durabilidad a 0 y rompiéndose en exactamente 1 uso.
* Esto permite a administradores de servidores y creadores de packs aplicar mecánicas de rotura en 1 golpe directamente a través de controles deslizantes de porcentaje o comandos `/gamerule`.

---

## 🎨 Formato de visualización de descripciones emergentes

La opción `tooltipFormat` configura cómo se muestran los aumentos de durabilidad en las descripciones emergentes:

| Ajuste de formato | Salida de ejemplo (200% / 2x) | Salida de ejemplo (150% / 1.5x) | Descripción |
| :--- | :--- | :--- | :--- |
| `"ADAPTIVE"` *(Predeterminado)* | `⟨2x Durabilidad de espadas⟩` | `⟨150% Durabilidad de espadas⟩` | Muestra multiplicadores enteros limpios para centenas exactas; porcentajes en caso contrario. |
| `"PERCENTAGE"` | `⟨200% Durabilidad de espadas⟩` | `⟨150% Durabilidad de espadas⟩` | Siempre muestra el valor de porcentaje exacto. |
| `"MULTIPLIER"` | `⟨2x Durabilidad de espadas⟩` | `⟨1.5x Durabilidad de espadas⟩` | Siempre muestra la cadena formateada del factor multiplicador. |

Establece `"showTooltip": false` para ocultar completamente los indicadores de durabilidad.

---

## ⚠️ Advertencia importante sobre la prioridad de configuración

> ⚠️ **Aviso**: Los cambios realizados en `durability-multiplier.json` o en la pantalla de ModMenu **solo definen valores base predeterminados para mundos recién creados**.
> 
> Para mundos activos existentes, cada mundo mantiene su propio estado de GameRule independiente guardado dentro de los datos del mundo (`level.dat`). Para cambiar los ajustes en un mundo activo, usa el comando `/gamerule` en el juego o la pantalla de edición nativa de GameRules.

