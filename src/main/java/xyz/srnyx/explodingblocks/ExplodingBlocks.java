package xyz.srnyx.explodingblocks;

import org.jetbrains.annotations.NotNull;
import xyz.srnyx.annoyingapi.AnnoyingPlugin;
import xyz.srnyx.annoyingapi.data.StringData;
import xyz.srnyx.annoyingapi.file.AnnoyingData;
import xyz.srnyx.annoyingapi.file.AnnoyingFile;
import xyz.srnyx.explodingblocks.messages.EBMessagesProvider;
import xyz.srnyx.explodingblocks.stats.FastStats;

import java.io.File;
import java.util.logging.Level;


public class ExplodingBlocks extends AnnoyingPlugin {
    @NotNull private static final String TABLE = "data";
    @NotNull public static final String COL_ENABLED = "enabled";

    public ConfigYml config;
    public StringData data;

    public ExplodingBlocks() {
        options
                .statsOptions(statsOptions -> statsOptions
                        .bStats(bStatsOptions -> bStatsOptions.id(18868))
                        .fastStats(fastStatsOptions -> fastStatsOptions.loader(FastStats.class)))
                .dataOptions(dataOptions -> dataOptions
                        .table(TABLE, COL_ENABLED))
                .registrationOptions.papiExpansionToRegister(() -> new ExplodingPlaceholders(this));
    }

    @Override @NotNull
    public EBMessagesProvider getMessages() {
        return (EBMessagesProvider) super.getMessages();
    }

    @Override
    public void load() {
        config = configLoader.build(builder -> builder
                .config(new ConfigYml()));
        data = new StringData(this, TABLE, "server");
        convertOldData();
    }

    @Override
    public void reload() {
        config.reload();
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
