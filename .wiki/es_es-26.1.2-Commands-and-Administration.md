# Comandos y administración (26.1.2)

| Sistema administrativo | Detalle |
| :--- | :--- |
| **Motor de comandos** | Sistema de comandos Brigadier `/gamerule` de Minecraft Vanilla |
| **Espacio de nombres** | Prefijo `ig:` para todas las reglas |
| **Nivel de permiso** | Nivel 2 (OP / Trucos activados en un jugador) |
| **Administración por GUI** | Compatible mediante la pantalla de GameRules y la configuración de ModMenu |
| **Política de ausencia** | **Cero subárboles de comandos Brigadier personalizados** por diseño |

---

## ⚡ Flujo de trabajo de administración en el juego

Durability Multiplier se basa completamente en los comandos vanilla de `/gamerule`. No se añaden comandos personalizados (como `/durability set` o `/durability reload`), garantizando un 100% de compatibilidad nativa con bloques de comandos, funciones, permisos y datapacks.

### Tareas administrativas comunes

#### 1. Configurar mejoras de supervivencia estándar
```mcfunction
# Double all items globally (200% durability - Default)
/gamerule ig:dm_percent_global 200

# Give mining tools a 5x (500%) lifespan
/gamerule ig:dm_percent_tools 500

# Give weapons a 3x (300%) lifespan
/gamerule ig:dm_percent_weapons 300
```

#### 2. Configurar ajustes de combate y servidores PvP
```mcfunction
# Keep armor at vanilla durability (100%) to prevent overly tanky players
/gamerule ig:dm_percent_armor 100

# Give weapons 2x (200%) durability
/gamerule ig:dm_percent_swords 200
/gamerule ig:dm_percent_bows 200
```

#### 3. Habilitar supervivencia estilo creativo (élitros y herramientas irrompibles)
```mcfunction
# Unbreakable Elytra for limitless exploration
/gamerule ig:dm_infinity_elytra true

# Unbreakable Tools for building mega-projects
/gamerule ig:dm_infinity_tools true
```

#### 4. Configurar objetos dinámicos de mods
```mcfunction
# Give a custom modded drill 400% durability
/gamerule ig:percent_techmod_titanium_drill 400

# Make a modded staff unbreakable
/gamerule ig:infinity_magicmod_staff_of_fire true

# Set a modded dagger to Single-Use using the -1 sentinel
/gamerule ig:percent_customweapons_glass_dagger -1
```

#### 5. Ocultar texto de descripciones emergentes
```mcfunction
# Hide durability multiplier info from item tooltips
/gamerule ig:dm_show_tooltip false
```

