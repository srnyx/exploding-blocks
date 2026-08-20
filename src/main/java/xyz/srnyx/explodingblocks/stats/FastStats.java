package xyz.srnyx.explodingblocks.stats;

import dev.faststats.Metrics;
import org.jetbrains.annotations.NotNull;
import xyz.srnyx.annoyingapi.stats.loader.FastStatsLoader;
import xyz.srnyx.explodingblocks.ExplodingBlocks;


public class FastStats extends FastStatsLoader {
    @NotNull private final ExplodingBlocks plugin;

    public FastStats(@NotNull ExplodingBlocks plugin) {
        this.plugin = plugin;
    }

    @Override @NotNull
    public ExplodingBlocks getAnnoyingPlugin() {
        return plugin;
    }

    @Override @NotNull
    public String getId() {
        return "0dcd81640de3accb90dfded2a5f49d34";
    }

    @Override
    public void mutateMetricsFactory(@NotNull Metrics.Factory factory) {
        factory.addMetric(config("config", () -> plugin.config));
    }
}
