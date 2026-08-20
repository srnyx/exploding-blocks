package xyz.srnyx.explodingblocks;

import eu.okaeri.configs.annotation.Comment;
import xyz.srnyx.annoyingapi.file.okaeri.RootConfig;
import xyz.srnyx.annoyingapi.stats.Stat;


public class ConfigYml extends RootConfig {
    @Comment("Whether the explosion should destroy blocks")
    @Stat
    public boolean griefing = false;

    @Comment
    @Comment("The percent chance that a block will explode")
    @Comment("0 = 0% chance, 100 = 100% chance")
    @Stat
    public int chance = 100;

    @Comment
    @Comment("The size of the explosion (default: 2")
    @Stat
    public int size = 2;
}
