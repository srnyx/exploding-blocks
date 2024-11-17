package xyz.srnyx.explodingblocks;

import org.jetbrains.annotations.NotNull;

import xyz.srnyx.annoyingapi.AnnoyingPlugin;
import xyz.srnyx.annoyingapi.PluginPlatform;
import xyz.srnyx.annoyingapi.data.StringData;
import xyz.srnyx.annoyingapi.file.AnnoyingData;
import xyz.srnyx.annoyingapi.file.AnnoyingFile;

import java.io.File;
import java.util.logging.Level;


public class ExplodingBlocks extends AnnoyingPlugin {
    @NotNull private static final String TABLE = "data";
    @NotNull public static final String COL_ENABLED = "enabled";

    @NotNull public ConfigYml config = new ConfigYml(this);
    public StringData data;

    public ExplodingBlocks() {
        options
                .pluginOptions(pluginOptions -> pluginOptions.updatePlatforms(
                        PluginPlatform.modrinth("kKXUJTU9"),
                        PluginPlatform.hangar(this),
                        PluginPlatform.spigot("104482")))
                .bStatsOptions(bStatsOptions -> bStatsOptions.id(18868))
                .dataOptions(dataOptions -> dataOptions
                        .enabled(true)
                        .table(TABLE, COL_ENABLED))
                .registrationOptions
                .toRegister(new BlockBreakListener(this), new ExplodingBlocksCmd(this))
                .papiExpansionToRegister(() -> new ExplodingPlaceholders(this));
    }

    @Override
    public void load() {
        data = new StringData(this, TABLE, "server");
        convertOldData();
    }

    @Override
    public void reload() {
        config = new ConfigYml(this);
    }

    private void convertOldData() {
        final AnnoyingData oldData = new AnnoyingData(this, "data.yml", new AnnoyingFile.Options<>().canBeEmpty(false));
        if (!oldData.file.exists()) return;
        if (!oldData.contains("converted_now-stored-elsewhere") && oldData.getBoolean("enabled")) data.set(COL_ENABLED, true);
        oldData.setSave("converted_now-stored-elsewhere", true);
        // Rename data file to old-data.yml
        if (!oldData.file.renameTo(new File(oldData.file.getParent(), "data-old.yml"))) log(Level.WARNING, "&cFailed to rename old data file: &4" + oldData.file.getPath());
    }
}
