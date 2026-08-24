<p align="center">
    <a href="https://www.curseforge.com/minecraft/mc-mods/fabric-api"><img src="https://img.shields.io/badge/Requires-Fabric_API-blue?style=for-the-badge&amp;logo=fabric" alt="Requires Fabric API"></a>
    <a href="https://www.curseforge.com/minecraft/mc-mods/dasik-libary"><img src="https://img.shields.io/badge/Requires-Dasik_Library-orange?style=for-the-badge&amp;logo=curseforge" alt="CurseForge: Dasik Library"></a>
    <img src="https://img.shields.io/badge/Language-Java_25-orange?style=for-the-badge&amp;logo=java" alt="Java 25">
    <img src="https://img.shields.io/badge/License-GPLv3-green?style=for-the-badge" alt="License">
    <img src="https://img.shields.io/badge/Minecraft-26.2+-brightgreen?style=for-the-badge" alt="Minecraft 26.2+">
</p>

<h2>⚒️ Durability Multiplier</h2>

<blockquote><p><strong>"Stop babysitting your tools. Focus on the adventure."</strong></p></blockquote>

<p>Tired of having your favorite diamond pickaxes, maces, and enchanted netherite armor shatter in the middle of a dangerous Nether fortress raid or deep mining expedition? <strong>Durability Multiplier</strong> gives you complete control over item longevity. Whether you want double durability, 10x durability, or complete invincibility (God Mode), it's just a GameRule away.</p>

<p>Part of the <strong>Instant Gratification Collection</strong> &mdash; mods that respect the player's time.</p>

<hr>

<h2>✨ Features</h2>

<h3>🔧 Granular Multiplier System</h3>
<p>Make items last 2x, 10x, or even 2,147,483,647x longer without altering underlying item NBT or save data.</p>

<ul>
  <li><strong>Global Multiplier:</strong> One central rule (<code>dm_multiplier_global</code>) to scale all damageable items in the world.</li>
  <li><strong>Granular Category Overrides:</strong> Independent multipliers for Swords, Spears, Tridents, Maces, Bows, Crossbows, Tools, Armor, and Elytra.</li>
  <li><strong>64-Bit Overflow Protection:</strong> Built with 64-bit <code>long</code> arithmetic to prevent integer wrap-around &mdash; items cleanly cap at maximum durability instead of breaking unexpectedly.</li>
</ul>

<blockquote><p>💡 <strong>Pro-Tip:</strong> Multipliers dynamically stack with vanilla Unbreaking enchantments, giving high-tier enchanted gear extreme longevity!</p></blockquote>

<h3>🛡️ God Mode (Infinity Mode)</h3>
<p>Toggle complete invincibility for individual item categories or all items simultaneously.</p>

<ul>
  <li><strong>Per-Category Control:</strong> Make only your armor unbreakable while keeping weapon durability normal, or make everything invincible.</li>
  <li><strong>Priority Hierarchy:</strong> Category-specific settings override global rules, and Infinity mode always takes precedence over numerical multipliers.</li>
</ul>

<h3>💬 Real-Time Tooltip Display</h3>
<p>Hover over any item in your inventory to see its durability status updated in real time:</p>

<p align="center">
  <img src="https://raw.githubusercontent.com/Rifaditya/Instant-Gratification-Durability-Multiplier/refs/heads/main/Images/Unbreak.png" alt="Unbreakable Tooltip" width="85%">
  <br><br>
  <img src="https://raw.githubusercontent.com/Rifaditya/Instant-Gratification-Durability-Multiplier/refs/heads/main/Images/4x%20tools.png" alt="4x Durability Tooltip" width="85%">
</p>

<ul>
  <li><strong><code>&lang;4x Durability&rang;</code></strong> &mdash; Subtle gray indicator displaying the active category multiplier.</li>
  <li><strong><code>✦ UNBREAKABLE</code></strong> &mdash; Bold golden indicator when God Mode is active.</li>
  <li>Can be toggled on or off anytime via <code>dm_show_tooltip</code>.</li>
</ul>

<h3>🔍 Compatibility &amp; HUD Integration</h3>
<ul>
  <li><strong>ModMenu &amp; Cloth Config / YACL Integration:</strong> Configure default multipliers and toggles directly in singleplayer using the graphical configuration screen.</li>
  <li><strong>Server-Side Dedicated Support:</strong> 100% functional on dedicated servers &mdash; vanilla clients can connect seamlessly without needing the mod installed on their client!</li>
  <li><strong>Zero NBT Modification:</strong> Uses non-destructive event interception powered by <strong>DasikLibrary</strong>, guaranteeing your world saves remain 100% vanilla safe.</li>
</ul>

<hr>

<h2>📊 Quick Reference &amp; Mechanics Matrix</h2>

<table>
  <thead>
    <tr>
      <th>Category</th>
      <th>Multiplier Rule</th>
      <th>God Mode (Infinity) Rule</th>
      <th>Default Value</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td><strong>Global</strong></td>
      <td><code>dm_multiplier_global</code></td>
      <td><code>dm_infinity_global</code></td>
      <td>Multiplier: <code>2</code> (2x) / Infinity: <code>false</code></td>
    </tr>
    <tr>
      <td><strong>Weapons</strong></td>
      <td><code>dm_multiplier_weapons</code></td>
      <td><code>dm_infinity_weapons</code></td>
      <td>Multiplier: <code>0</code> (Inherit Global)</td>
    </tr>
    <tr>
      <td><strong>Swords</strong></td>
      <td><code>dm_multiplier_swords</code></td>
      <td><code>dm_infinity_swords</code></td>
      <td>Multiplier: <code>0</code> (Inherit Global)</td>
    </tr>
    <tr>
      <td><strong>Spears</strong></td>
      <td><code>dm_multiplier_spears</code></td>
      <td><code>dm_infinity_spears</code></td>
      <td>Multiplier: <code>0</code> (Inherit Global)</td>
    </tr>
    <tr>
      <td><strong>Tridents</strong></td>
      <td><code>dm_multiplier_tridents</code></td>
      <td><code>dm_infinity_tridents</code></td>
      <td>Multiplier: <code>0</code> (Inherit Global)</td>
    </tr>
    <tr>
      <td><strong>Maces</strong></td>
      <td><code>dm_multiplier_maces</code></td>
      <td><code>dm_infinity_maces</code></td>
      <td>Multiplier: <code>0</code> (Inherit Global)</td>
    </tr>
    <tr>
      <td><strong>Bows &amp; Crossbows</strong></td>
      <td><code>dm_multiplier_bows</code> / <code>crossbows</code></td>
      <td><code>dm_infinity_bows</code> / <code>crossbows</code></td>
      <td>Multiplier: <code>0</code> (Inherit Global)</td>
    </tr>
    <tr>
      <td><strong>Tools (Pick/Axe/Shovel/Hoe)</strong></td>
      <td><code>dm_multiplier_tools</code></td>
      <td><code>dm_infinity_tools</code></td>
      <td>Multiplier: <code>0</code> (Inherit Global)</td>
    </tr>
    <tr>
      <td><strong>Armor (All Pieces)</strong></td>
      <td><code>dm_multiplier_armor</code></td>
      <td><code>dm_infinity_armor</code></td>
      <td>Multiplier: <code>0</code> (Inherit Global)</td>
    </tr>
    <tr>
      <td><strong>Elytra</strong></td>
      <td><code>dm_multiplier_elytra</code></td>
      <td><code>dm_infinity_elytra</code></td>
      <td>Multiplier: <code>0</code> (Inherit Global)</td>
    </tr>
  </tbody>
</table>

<hr>

<h2>📋 Quick Start Commands</h2>

<pre><code>/gamerule dm_multiplier_global 4      &rarr; 4x durability for all items
/gamerule dm_infinity_swords true     &rarr; Swords never break
/gamerule dm_multiplier_armor 10      &rarr; 10x armor durability
/gamerule dm_infinity_global true     &rarr; Everything is unbreakable
/gamerule dm_show_tooltip false       &rarr; Hide tooltip indicator</code></pre>

<hr>

<h2>⚙️ Configuration (Native Game Rules)</h2>

<blockquote><p><strong>💡 Config vs. In-Game GameRules:</strong> The global configuration file only defines default values for newly created worlds. In existing worlds, change settings in-game via the <strong>Edit Game Rules</strong> UI screen or the <code>/gamerule</code> command.</p></blockquote>

<p>All settings live directly inside the <strong>"Durability Multiplier"</strong> GameRules category with zero external config files to manage.</p>

<p align="center">
  <img src="https://raw.githubusercontent.com/Rifaditya/Instant-Gratification-Durability-Multiplier/refs/heads/main/Images/2026-02-15_11.24.45.png" alt="Edit Game Rules UI" width="85%">
</p>

<hr>

<h2>📖 How-To &amp; Operational Playbook</h2>

<p>Here is how <strong>Durability Multiplier</strong> operates and how to configure it to your playstyle:</p>

<ol>
  <li><strong>First Launch &amp; Auto-Population:</strong>
    <ul>
      <li>Place <code>durability-multiplier-*.jar</code> into your <code>mods/</code> directory alongside Fabric API.</li>
      <li>On the very first launch, the mod <strong>automatically generates and populates</strong> its default configuration file (<code>config/durability-multiplier.json</code>) and registers all <code>dm_*</code> GameRules into Minecraft's native registry with default <strong>2x durability</strong>.</li>
    </ul>
  </li>
  <li><strong>How to Configure:</strong>
    <ul>
      <li><strong>In-Game (Live / Instant Update):</strong> Press <code>Esc</code> &rarr; <strong>Edit Game Rules</strong> &rarr; scroll down to the <strong>"Durability Multiplier"</strong> section (or run <code>/gamerule dm_multiplier_global 4</code>). Changes take effect immediately in your active world with <strong>no restart required</strong>!</li>
      <li><strong>Config File (Global Defaults):</strong> If you manually edit <code>config/durability-multiplier.json</code> with a text editor to set new world defaults, <strong>restart Minecraft</strong> (or your server) for the new configuration file to load.</li>
    </ul>
  </li>
  <li><strong>How It Works In-Game:</strong>
    <ul>
      <li>The mod intercepts item durability damage events in real time. It uses standard vanilla item tags (<code>#minecraft:swords</code>, <code>#minecraft:axes</code>, <code>#minecraft:chest_armor</code>) to identify items dynamically, scaling durability loss using safe 64-bit integer arithmetic without modifying underlying item NBT or save data.</li>
    </ul>
  </li>
  <li><strong>Instant In-Game Verification:</strong>
    <ul>
      <li>Hover over any tool, weapon, or armor piece in your inventory to see the live durability status (<code>&lang;4x Durability&rang;</code> or <strong>✦ UNBREAKABLE</strong>) in the item tooltip.</li>
    </ul>
  </li>
</ol>

<h3>💎 Why Using Durability Multiplier is Better (Mod vs. Vanilla Workarounds)</h3>
<p><em>You might wonder: &ldquo;Can't I just use vanilla <code>/give</code> commands or custom datapacks?&rdquo;</em> Here is why <strong>Durability Multiplier</strong> is the vastly superior, headache-free solution for your world or server:</p>

<table>
  <thead>
    <tr>
      <th>Feature &amp; Advantage</th>
      <th>Vanilla Commands / Datapacks</th>
      <th>⚒️ Durability Multiplier</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td><strong>Universal Coverage</strong></td>
      <td>❌ Only affects specific <code>/give</code> items; newly crafted tools or loot chest gear are untouched.</td>
      <td>✅ <strong>100% Universal</strong> &mdash; Instantly applies to every item crafted, traded, looted, or spawned.</td>
    </tr>
    <tr>
      <td><strong>World Save Safety</strong></td>
      <td>⚠️ Modifies item NBT tags; risk of permanent item corruption if tags break.</td>
      <td>✅ <strong>100% Non-Destructive</strong> &mdash; Pure in-memory damage interception; saves stay 100% vanilla safe.</td>
    </tr>
    <tr>
      <td><strong>Live Real-Time Tuning</strong></td>
      <td>❌ Requires writing JSON, reloading datapacks (<code>/reload</code>), or re-issuing items.</td>
      <td>✅ <strong>Live GameRules</strong> &mdash; Change rules on the fly (<code>/gamerule dm_multiplier_global 4</code>) with zero downtime.</td>
    </tr>
    <tr>
      <td><strong>Granular Categories</strong></td>
      <td>❌ Manual tag management across hundreds of separate item IDs.</td>
      <td>✅ <strong>12+ Built-In Categories</strong> (Swords, Armor, Elytra, Tools, Maces) with dedicated sliders.</td>
    </tr>
    <tr>
      <td><strong>In-Game Tooltip Feedback</strong></td>
      <td>❌ No visual indicator unless you manually write custom item lore.</td>
      <td>✅ <strong>Native HUD Tooltips</strong> (<code>&lang;4x Durability&rang;</code> &amp; <strong>✦ UNBREAKABLE</strong>) updating in real time.</td>
    </tr>
    <tr>
      <td><strong>Dedicated Server Friendly</strong></td>
      <td>❌ Requires complex server scripts or custom permission plugins.</td>
      <td>✅ <strong>Server-Side Native</strong> &mdash; Vanilla clients connect seamlessly with zero required downloads.</td>
    </tr>
  </tbody>
</table>

<h3>🛡️ How to Make Items Unbreakable (God Mode)</h3>
<p>Want complete invincibility so you never have to repair or replace your gear ever again? You have <strong>3 easy ways</strong>:</p>

<ul>
  <li><strong>Way 1: Via In-Game GameRules (Instant &amp; World-Wide &mdash; Recommended)</strong>
    <p>Make <strong>everything</strong> unbreakable:</p>
    <pre><code>/gamerule dm_infinity_global true</code></pre>
    <p>Or make only a <strong>specific category</strong> unbreakable (e.g. Armor or Swords):</p>
    <pre><code>/gamerule dm_infinity_armor true
/gamerule dm_infinity_swords true</code></pre>
    <p><em>Takes effect immediately across all players in your active world with zero restarts or item re-crafting!</em></p>
  </li>
  <li><strong>Way 2: Via Native Game Rules Screen</strong>
    <p>Press <code>Esc</code> &rarr; click <strong>Edit Game Rules</strong> &rarr; scroll down to the <strong>"Durability Multiplier"</strong> category &rarr; toggle <strong>"Infinity Global"</strong> (or individual category switches) to <code>ON</code>.</p>
  </li>
  <li><strong>Way 3: Via Vanilla Item Data Components (Per-Item)</strong>
    <p>Give a specific player a permanently unbreakable item using Minecraft's native data component:</p>
    <pre><code>/give @p netherite_chestplate[unbreakable={}] 1</code></pre>
  </li>
</ul>

<blockquote><p>💡 <strong>Why Way 1 (The Mod) is Better:</strong> Unlike vanilla <code>/give</code> which only fixes <em>one specific item</em>, toggling the mod's GameRule instantly protects <strong>all existing and future items</strong> in your world without touching commands or player inventories!</p></blockquote>

<h3>💥 How to Make Items Single-Use (2 Ways)</h3>
<p>Want to create high-stakes "Glass Cannon" weapons or one-time consumable tools that break after a single use? There are <strong>2 ways</strong> to do it:</p>

<ul>
  <li><strong>Way 1: Via Item Data Components (Per-Item)</strong>
    <p>Give yourself an item with <code>max_damage=1</code>:</p>
    <pre><code>/give @p diamond_sword[max_damage=1] 1</code></pre>
    <p>This sets the item's total durability to exactly 1. The moment you strike a mob or mine a block, it shatters in a single hit!</p>
  </li>
  <li><strong>Way 2: Via GameRules &amp; Multipliers (Category-Wide &mdash; Recommended)</strong>
    <p>Ensure God Mode is disabled: <code>/gamerule dm_infinity_swords false</code>.</p>
    <p>Set the category multiplier to <code>1</code>: <code>/gamerule dm_multiplier_swords 1</code>.</p>
    <p>At a <code>1x</code> multiplier, items take unmitigated 1:1 damage, allowing any low-durability or 1-HP item (from custom datapacks or mods) to break immediately on use without multiplier extension.</p>
  </li>
</ul>

<blockquote><p>💡 <strong>Why Way 2 (The Mod) is Better:</strong> You can fine-tune durability scaling globally or by category with a single command, ensuring custom survival challenges and balanced minigames work effortlessly across entire multiplayer servers.</p></blockquote>

<hr>

<h2>☕ Support</h2>

<p>If you enjoy the <strong>Instant Gratification</strong> collection, consider fueling future updates!</p>

<p align="center">
  <a href="https://ko-fi.com/dasikigaijin/tip"><img src="https://img.shields.io/badge/Ko--fi-Support%20Me-FF5E5B?style=for-the-badge&amp;logo=ko-fi&amp;logoColor=white" alt="Ko-fi"></a>
  <a href="https://sociabuzz.com/dasikigaijin/tribe"><img src="https://img.shields.io/badge/SocioBuzz-Local_Support-7BB32E?style=for-the-badge" alt="SocioBuzz"></a>
  <a href="https://saweria.co/DasikIgaijinn"><img src="https://img.shields.io/badge/Saweria-Local_Support-FFA500?style=for-the-badge" alt="Saweria"></a>
</p>

<blockquote><p><strong>🇮🇩 Indonesian Users:</strong> SocioBuzz and Saweria support local payment methods (Gopay, OVO, Dana, etc.) if you want to support me without using PayPal/Ko-fi!</p></blockquote>

<hr>

<h2>📜 Credits</h2>

<table>
  <thead>
    <tr>
      <th>Role</th>
      <th>Author</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td><strong>Creator</strong></td>
      <td><strong>Dasik</strong> (Rifaditya)</td>
    </tr>
    <tr>
      <td><strong>Collection</strong></td>
      <td>Instant Gratification</td>
    </tr>
    <tr>
      <td><strong>License</strong></td>
      <td>GPLv3</td>
    </tr>
  </tbody>
</table>

<blockquote>
  <p><strong>📦 Modpack Permissions &amp; Distribution:</strong><br>
  You are free to include this mod in any modpack on any platform. However, the mod itself must be downloaded from its official distribution pages on <strong>Modrinth</strong> or <strong>CurseForge</strong>. Re-uploading or redistributing the mod jar file to third-party sites is strictly prohibited unless explicitly permitted by the creator.</p>
  <p><strong>License &amp; Forks:</strong><br>
  Since the source code is licensed under <strong>GNU GPLv3</strong>, you are fully permitted to fork the repository, make modifications, build your own versions, and distribute them under the terms of the GPLv3.</p>
</blockquote>

<hr>

<div align="center">
  <p><strong>Made with ❤️ for the Minecraft community</strong></p>
  <p><em>Part of the Instant Gratification Collection</em></p>
</div>
