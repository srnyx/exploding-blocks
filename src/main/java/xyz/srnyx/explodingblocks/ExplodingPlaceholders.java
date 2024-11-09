package xyz.srnyx.explodingblocks;

import org.bukkit.entity.Player;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import xyz.srnyx.annoyingapi.AnnoyingPAPIExpansion;


public class ExplodingPlaceholders extends AnnoyingPAPIExpansion {
    @NotNull private final ExplodingBlocks plugin;

    public ExplodingPlaceholders(@NotNull ExplodingBlocks plugin) {
        this.plugin = plugin;
    }

    @Override @NotNull
    public ExplodingBlocks getAnnoyingPlugin() {
        return plugin;
    }

    @Override @NotNull
    public String getIdentifier() {
        return "exploding";
    }

    @Override @Nullable
    public String onPlaceholderRequest(@Nullable Player player, @NotNull String params) {
        return params.equals("status") ? String.valueOf(plugin.data.has(ExplodingBlocks.COL_ENABLED)) : null;
    }
}
