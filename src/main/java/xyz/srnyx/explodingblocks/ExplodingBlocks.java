package xyz.srnyx.explodingblocks;

import xyz.srnyx.annoyingapi.AnnoyingPlugin;
import xyz.srnyx.annoyingapi.PluginPlatform;
import xyz.srnyx.annoyingapi.file.AnnoyingData;
import xyz.srnyx.annoyingapi.file.AnnoyingResource;


public class ExplodingBlocks extends AnnoyingPlugin {
    public final boolean griefing = new AnnoyingResource(this, "config.yml").getBoolean("griefing");
    public final AnnoyingData data = new AnnoyingData(this, "data.yml");
    public boolean enabled;

    public ExplodingBlocks() {
        options
                .pluginOptions(pluginOptions -> pluginOptions.updatePlatforms(
                        PluginPlatform.modrinth("exploding-blocks"),
                        PluginPlatform.hangar(this, "srnyx"),
                        PluginPlatform.spigot("104482")))
                .bStatsOptions(bStatsOptions -> bStatsOptions.id(18868))
                .registrationOptions
                .automaticRegistration(automaticRegistration -> automaticRegistration.packages(
                        "xyz.srnyx.explodingblocks.commands",
                        "xyz.srnyx.explodingblocks.listeners"))
                .papiExpansionToRegister(() -> new ExplodingPlaceholders(this));

        enabled = data.getBoolean("enabled");
    }

    public void setExplodingEnabled(boolean enabled) {
        this.enabled = enabled;
        data.setSave("enabled", enabled);
    }
}
