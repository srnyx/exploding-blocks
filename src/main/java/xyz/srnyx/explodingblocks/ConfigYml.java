package xyz.srnyx.explodingblocks;

import org.jetbrains.annotations.NotNull;

import xyz.srnyx.annoyingapi.AnnoyingPlugin;
import xyz.srnyx.annoyingapi.file.AnnoyingResource;


public class ConfigYml {
    public final boolean griefing;
    public final int chance;
    public final int size;

    public ConfigYml(@NotNull AnnoyingPlugin plugin) {
        final AnnoyingResource config = new AnnoyingResource(plugin, "config.yml");
        griefing = config.getBoolean("griefing", false);
        chance = config.getInt("chance", 100);
        size = config.getInt("size", 2);
    }
}
