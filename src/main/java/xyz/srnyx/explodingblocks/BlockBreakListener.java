package xyz.srnyx.explodingblocks;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Collection;


public class BlockBreakListener implements Listener {
    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        if (EbCommand.enabled) {
            final Block block = event.getBlock();
            final Collection<ItemStack> drops = block.getDrops();

            // Cancel event (don't drop the block)
            event.setCancelled(true);

            // Make sure the block is actually removed (in-case it's obsidian or something)
            block.setType(Material.AIR);

            // Summon Creeper to simulate explosion
            final Creeper creeper = (Creeper) block.getWorld().spawnEntity(block.getLocation(), EntityType.CREEPER);
            creeper.setExplosionRadius(2);
            creeper.explode();

            // Artificially drop the block
            if (event.getPlayer().getGameMode() == GameMode.SURVIVAL) for (final ItemStack item : drops) block.getWorld().dropItemNaturally(block.getLocation(), item);
        }
    }
}
