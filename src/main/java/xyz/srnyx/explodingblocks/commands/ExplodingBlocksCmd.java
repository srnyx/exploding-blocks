package xyz.srnyx.explodingblocks.commands;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import xyz.srnyx.annoyingapi.command.AnnoyingCommand;
import xyz.srnyx.annoyingapi.command.AnnoyingSender;
import xyz.srnyx.annoyingapi.message.AnnoyingMessage;
import xyz.srnyx.annoyingapi.message.DefaultReplaceType;

import xyz.srnyx.explodingblocks.ExplodingBlocks;

import java.util.Arrays;
import java.util.List;


public class ExplodingBlocksCmd implements AnnoyingCommand {
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
        if (sender.args.length == 0) {
            plugin.setExplodingEnabled(!plugin.enabled);
            new AnnoyingMessage(plugin, "toggle")
                    .replace("%state%", plugin.enabled, DefaultReplaceType.BOOLEAN)
                    .send(sender);
            return;
        }

        // <on|...>
        final boolean toggle = sender.argEquals(0, "on");
        plugin.setExplodingEnabled(toggle);
        new AnnoyingMessage(plugin, "toggle")
                .replace("%state%", toggle, DefaultReplaceType.BOOLEAN)
                .send(sender);
    }

    @Override @Nullable
    public List<String> onTabComplete(@NotNull AnnoyingSender sender) {
        return sender.args.length == 1 ? Arrays.asList("on", "off") : null;
    }
}
