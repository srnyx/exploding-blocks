package xyz.srnyx.explodingblocks;

import org.jetbrains.annotations.NotNull;

import xyz.srnyx.annoyingapi.AnnoyingPlugin;
import xyz.srnyx.annoyingapi.PluginPlatform;
import xyz.srnyx.annoyingapi.file.AnnoyingData;
import xyz.srnyx.annoyingapi.file.AnnoyingResource;


public class ExplodingBlocks extends AnnoyingPlugin {
    // Config
    public boolean griefing;
    public int chance;
    public int size;
    // Data
    @NotNull public final AnnoyingData data = new AnnoyingData(this, "data.yml");
    public boolean enabled;

    public ExplodingBlocks() {
        options
                .pluginOptions(pluginOptions -> pluginOptions.updatePlatforms(
                        PluginPlatform.modrinth("exploding-blocks"),
                        PluginPlatform.hangar(this, "srnyx"),
                        PluginPlatform.spigot("104482")))
                .bStatsOptions(bStatsOptions -> bStatsOptions.id(18868))
                .registrationOptions
                .toRegister(this, BlockBreakListener.class, ExplodingBlocksCmd.class)
                .papiExpansionToRegister(() -> new ExplodingPlaceholders(this));

        reload();
    }

    @Override
    public void reload() {
        final AnnoyingResource config = new AnnoyingResource(this, "config.yml");
        griefing = config.getBoolean("griefing");
        chance = config.getInt("chance");
        size = config.getInt("size");
        enabled = data.getBoolean("enabled");
    }
}
