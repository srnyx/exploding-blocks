package xyz.srnyx.explodingblocks.listeners;

import org.bukkit.GameMode;
import org.bukkit.GameRule;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import org.jetbrains.annotations.NotNull;

import xyz.srnyx.annoyingapi.AnnoyingListener;

import xyz.srnyx.explodingblocks.ExplodingBlocks;

import java.util.Collection;
import java.util.Random;


public class BlockBreakListener implements AnnoyingListener {
    @NotNull private static final Random RANDOM = new Random();

    @NotNull private final ExplodingBlocks plugin;

    public BlockBreakListener(@NotNull ExplodingBlocks plugin) {
        this.plugin = plugin;
    }

    @Override @NotNull
    public ExplodingBlocks getAnnoyingPlugin() {
        return plugin;
    }

    @EventHandler
    public void onBlockBreak(@NotNull BlockBreakEvent event) {
        if (!plugin.enabled || RANDOM.nextInt(100) >= plugin.chance) return;
        final Player player = event.getPlayer();
        final Block block = event.getBlock();
        final World world = block.getWorld();
        final Collection<ItemStack> drops = block.getDrops(player.getInventory().getItemInMainHand());

        // Prevent block from dropping naturally
        event.setCancelled(true);

        // Make sure the block is actually removed (in-case it's obsidian or something)
        block.setType(Material.AIR);

        // Summon Creeper to simulate explosion
        final Creeper creeper = (Creeper) world.spawnEntity(block.getLocation(), EntityType.CREEPER);
        final boolean changeMobGriefing = !plugin.griefing && Boolean.TRUE.equals(world.getGameRuleValue(GameRule.MOB_GRIEFING));
        creeper.setExplosionRadius(plugin.size);
        if (changeMobGriefing) world.setGameRule(GameRule.MOB_GRIEFING, false);
        creeper.explode();
        if (changeMobGriefing) world.setGameRule(GameRule.MOB_GRIEFING, true);

        // Artificially drop the block
        if (player.getGameMode().equals(GameMode.SURVIVAL)) drops.forEach(drop -> world.dropItemNaturally(block.getLocation(), drop));
    }
}
