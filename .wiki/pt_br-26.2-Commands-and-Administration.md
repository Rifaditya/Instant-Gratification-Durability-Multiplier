# Comandos e administração (26.2)

| Sistema Administrativo | Detalhe |
| :--- | :--- |
| **Motor de Comandos** | Sistema de Comandos Brigadier `/gamerule` do Minecraft Vanilla |
| **Namespace** | Prefixo `ig:` para todas as regras |
| **Nível de Permissão** | Nível 2 (OP / Cheats Habilitados em Um Jogador) |
| **Administração por GUI** | Suportada pela tela de GameRules e configuração do ModMenu |
| **Política de Ausência** | **Zero subárvores de comandos Brigadier personalizados** por design |

---

## ⚡ Fluxo de trabalho de administração no jogo

Durability Multiplier depende inteiramente de comandos `/gamerule` do vanilla. Nenhum comando personalizado (como `/durability set` ou `/durability reload`) é adicionado, garantindo 100% de compatibilidade nativa com blocos de comando, funções, permissões e datapacks.

### Tarefas administrativas comuns

#### 1. Configurar melhorias padrão de sobrevivência
```mcfunction
# Double all items globally (200% durability - Default)
/gamerule ig:dm_percent_global 200

# Give mining tools a 5x (500%) lifespan
/gamerule ig:dm_percent_tools 500

# Give weapons a 3x (300%) lifespan
/gamerule ig:dm_percent_weapons 300
```

#### 2. Configurar opções de combate e servidores PvP
```mcfunction
# Keep armor at vanilla durability (100%) to prevent overly tanky players
/gamerule ig:dm_percent_armor 100

# Give weapons 2x (200%) durability
/gamerule ig:dm_percent_swords 200
/gamerule ig:dm_percent_bows 200
```

#### 3. Ativar sobrevivência estilo criativo (élitros e ferramentas inquebráveis)
```mcfunction
# Unbreakable Elytra for limitless exploration
/gamerule ig:dm_infinity_elytra true

# Unbreakable Tools for building mega-projects
/gamerule ig:dm_infinity_tools true
```

#### 4. Configurar itens dinâmicos de mods
```mcfunction
# Give a custom modded drill 400% durability
/gamerule ig:percent_techmod_titanium_drill 400

# Make a modded staff unbreakable
/gamerule ig:infinity_magicmod_staff_of_fire true

# Set a modded dagger to Single-Use using the -1 sentinel
/gamerule ig:percent_customweapons_glass_dagger -1
```

#### 5. Ocultar texto de dicas de ferramentas
```mcfunction
# Hide durability multiplier info from item tooltips
/gamerule ig:dm_show_tooltip false
```

