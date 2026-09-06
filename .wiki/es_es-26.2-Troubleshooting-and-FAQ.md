# Solución de problemas y preguntas frecuentes (26.2)

| Tema del sistema | Resumen |
| :--- | :--- |
| **Comportamiento de precedencia** | Las GameRules anulan la configuración en mundos activos; el config define valores de nuevos mundos |
| **Motor de cálculo** | Intercepción probabilística (cero modificación de NBT, cero desincronización) |
| **Tolerancia a casos límite** | 100% libre de fallos al eliminar mods, desvincular registros y componentes ausentes |

---

## ❓ Preguntas frecuentes (FAQ)

### Q1: ¿Por qué los cambios de configuración en ModMenu no afectan a mi mundo de un jugador activo?
**Respuesta**: Según lo diseñado por la **Ley de precedencia**, los cambios realizados en `durability-multiplier.json` o en la GUI de ModMenu definen valores base predeterminados **SOLO PARA NUEVOS MUNDOS**. Para cambiar los ajustes en tu mundo actual, usa el comando `/gamerule` (ej. `/gamerule ig:dm_percent_tools 500`) o la pantalla de edición de GameRules.

### Q2: ¿Por qué la descripción de un objeto no muestra el texto de porcentaje o multiplicador?
**Respuesta**:
1. Verifica que el objeto sea dañable y tenga una barra de durabilidad (`DataComponents.MAX_DAMAGE > 0`).
2. Comprueba si `ig:dm_show_tooltip` está establecido en `true`.
3. Si el valor activo es `100` (100% de durabilidad vanilla), no se muestra ninguna línea adicional para mantener limpias las descripciones.

### Q3: ¿Por qué mi herramienta al 500% (5x) perdió durabilidad después de solo 2 usos?
**Respuesta**: Durability Multiplier utiliza **intercepción probabilística de daño** (el mismo mecanismo que usa el encantamiento *Irrompibilidad* de vanilla) para garantizar **100% de seguridad en los mundos**. Al 500% (5x durabilidad), cada rotura de bloque tiene una **probabilidad independiente del 20% (1 en 5)** de causar 1 daño y un **80% de probabilidad** de absorberlo. Debido a que cada golpe se calcula independientemente, una herramienta podría recibir daño tras 2 u 8 usos, pero a lo largo de su vida útil durará exactamente 5 veces más (~7,805 bloques rotos para un pico de diamante).

### Q4: ¿Debo introducir decimales como 0.5 o 1.5 en las GameRules?
**Respuesta**: **No**. Las GameRules de Minecraft solo aceptan números enteros (`int`). Ingresa siempre números de porcentaje enteros:
* `50` para 50% (mitad de durabilidad / desgaste 2x)
* `100` para 100% (estándar vanilla 1x)
* `150` para 150% (aumento de 1.5x durabilidad)
* `200` para 200% (durabilidad doble 2x)
* `-1` para Un solo uso (Modo Cristal / se rompe en 1 golpe)

### Q5: ¿Funciona Durability Multiplier con los encantamientos de Irrompibilidad (Unbreaking)?
**Respuesta**: ¡Sí! Durability Multiplier escala el daño entrante **antes** del procesamiento del encantamiento vanilla. Un pico con Irrompibilidad III con un ajuste de 200% (2x) durará aproximadamente $4 \times 2 = 8\times$ más que un pico vanilla sin encantar.

### Q6: ¿Cómo activo el Modo de cristal (un solo uso tras 1 golpe) para un objeto?
**Respuesta**: Puedes:
1. Establecer la GameRule de un solo uso en true: `/gamerule ig:dm_single_use_swords true` (o `/gamerule ig:single_use_<mod>_<item> true`).
2. Usar el **Centinela de usuario avanzado `-1`**: establece la regla de porcentaje en `-1`, ej. `/gamerule ig:dm_percent_swords -1` o `/gamerule ig:percent_<mod>_<item> -1`.

---

## 🔍 Casos límite en profundidad y comportamiento del ciclo de vida

### Caso límite 1: Desinstalación del mod y eliminación de objetos
Cuando un jugador elimina un mod que tenía objetos registrados en Durability Multiplier:
1. **Seguridad del archivo de configuración**: Los IDs de los objetos eliminados permanecen guardados en `forcedItems` y `forcedPercentages` en `config/durability-multiplier.json`.
2. **Estado inactivo en el mundo**: Las GameRules dinámicas (`ig:percent_<mod>_<item>`, `ig:infinity_<mod>_<item>`) en `level.dat` permanecen completamente inactivas.
3. **Cero fallos y cero corrupción**: Como la búsqueda se realiza mediante `BuiltInRegistries.ITEM.getKey(stack.getItem())`, el juego nunca intentará buscar clases ausentes ni IDs sin mapear. Se evitan `NullPointerException` o corrupción.
4. **Restauración automática al reinstalar**: Si el mod se reinstala en el futuro, ¡todas las configuraciones anteriores se **volverán a vincular al instante** sin necesidad de reconfigurar!
5. **Limpieza manual de configuración (Opcional)**: Si deseas depurar entradas de mods eliminados de tu configuración:
   * Open `config/durability-multiplier.json` in a text editor.
   * Remove the deleted item ID string from the `"forcedItems"` list.
   * Remove the corresponding keys from `"forcedPercentages"`, `"forcedInfinities"`, and `"forcedSingleUses"`.
   * Save the file.

---

### Caso límite 2: Filtrado estricto de durabilidad (`MAX_DAMAGE > 0`)
¿Por qué los muebles de mods (ej. sillas/armarios de Macaw's Furniture), bloques, comida o materiales no aparecen en GameRules ni en `durability-multiplier.json`?
* Durability Multiplier verifica estrictamente `DataComponents.MAX_DAMAGE > 0` antes de registrar cualquier objeto.
* Los objetos sin componentes de durabilidad (bloques, comida, lingotes, semillas) se descartan en $0.0001\mu\text{s}$ durante el inicio.
* Esto evita la saturación de nombres y garantiza que el autocompletado de GameRules se mantenga limpio y fluido.

---

### Caso límite 3: Jerarquía completa de evaluación y prioridad
Cuando un objeto recibe daño de durabilidad, el resultado se determina según la siguiente jerarquía estricta:

$$\text{God Mode (Infinity)} \longrightarrow \text{Single-Use (Glass Mode)} \longrightarrow \text{Per-Item Override} \longrightarrow \text{Subcategory} \longrightarrow \text{Parent Category} \longrightarrow \text{Global} \longrightarrow \text{100\% Vanilla}$$

1. **Comprobación de Modo Dios Irrompible**:
   * Per-Item God Mode (`ig:infinity_<mod>_<item>` / `forcedInfinities`)
   * Subcategory God Mode (`ig:dm_infinity_pickaxes`, `ig:dm_infinity_swords`, etc.)
   * Parent Category God Mode (`ig:dm_infinity_tools`, `ig:dm_infinity_weapons`, `ig:dm_infinity_armor`)
   * Global God Mode (`ig:dm_infinity_global`)
   * *If any is `true` $\rightarrow$ Damage = $0$ (Unbreakable).*
2. **Comprobación de Un solo uso (Modo Cristal)**:
   * Effective percentage $\le -1$ (`-1` Glass Mode Sentinel)
   * Per-Item Single-Use (`ig:single_use_<mod>_<item>` / `forcedSingleUses`)
   * Subcategory Single-Use (`ig:dm_single_use_pickaxes`, `ig:dm_single_use_swords`, etc.)
   * Parent Category Single-Use (`ig:dm_single_use_tools`, `ig:dm_single_use_weapons`, `ig:dm_single_use_armor`)
   * Global Single-Use (`ig:dm_single_use_global`)
   * *If active $\rightarrow$ Item loses all remaining durability in 1 hit.*
3. **Resolución de escalado de porcentaje**:
   * Per-Item Override (`ig:percent_<mod>_<item>` / `forcedPercentages`) if $\neq 0$
   * Subcategory Percentage (`ig:dm_percent_pickaxes`, `ig:dm_percent_swords`, etc.) if $\neq 0$
   * Parent Category Percentage (`ig:dm_percent_tools`, `ig:dm_percent_weapons`, `ig:dm_percent_armor`) if $\neq 0$
   * Global Percentage (`ig:dm_percent_global`) if $\neq 0$
   * Vanilla Fallback: $100\%$ baseline.

