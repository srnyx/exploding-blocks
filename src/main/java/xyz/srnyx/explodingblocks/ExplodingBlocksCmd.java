package xyz.srnyx.explodingblocks;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import xyz.srnyx.annoyingapi.command.AnnoyingCommand;
import xyz.srnyx.annoyingapi.command.AnnoyingSender;
import xyz.srnyx.annoyingapi.message.AnnoyingMessage;
import xyz.srnyx.annoyingapi.message.DefaultReplaceType;

import java.util.Arrays;
import java.util.List;


public class ExplodingBlocksCmd extends AnnoyingCommand {
    @NotNull private final ExplodingBlocks plugin;

    public ExplodingBlocksCmd(@NotNull ExplodingBlocks plugin) {
        this.plugin = plugin;
    }

    @Override @NotNull
    public ExplodingBlocks getAnnoyingPlugin() {
        return plugin;
    }

    @Override @NotNull
    public String getName() {
        return "eb";
    }

    @Override @NotNull
    public String getPermission() {
        return "eb.toggle";
    }

    @Override
    public void onCommand(@NotNull AnnoyingSender sender) {
        // No arguments
        if (sender.args.length != 1) {
            sender.invalidArguments();
            return;
        }

        // reload
        if (sender.argEquals(0, "reload")) {
            plugin.reload();
            new AnnoyingMessage(plugin, "reload").send(sender);
            return;
        }

        // <on|off>
        final boolean toggle = sender.argEquals(0, "on");
        if (!toggle && !sender.argEquals(0, "off")) {
            sender.invalidArgumentByIndex(0);
            return;
        }
        plugin.data.set(ExplodingBlocks.COL_ENABLED, toggle);
        new AnnoyingMessage(plugin, "toggle")
                .replace("%state%", toggle, DefaultReplaceType.BOOLEAN)
                .send(sender);
    }

    @NotNull private static final List<String> TAB_COMPLETE = Arrays.asList("reload", "on", "off");

    @Override @Nullable
    public List<String> onTabComplete(@NotNull AnnoyingSender sender) {
        return sender.args.length == 1 ? TAB_COMPLETE : null;
    }
}
