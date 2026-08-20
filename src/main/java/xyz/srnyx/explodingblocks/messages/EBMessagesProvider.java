package xyz.srnyx.explodingblocks.messages;

import org.jetbrains.annotations.NotNull;
import xyz.srnyx.annoyingapi.message.AnnoyingMessages;
import xyz.srnyx.annoyingapi.message.MessagesProvider;
import xyz.srnyx.explodingblocks.ExplodingBlocks;


public class EBMessagesProvider extends MessagesProvider {
    @NotNull private final ExplodingBlocks plugin;
    private EBMessages messages;

    public EBMessagesProvider(@NotNull ExplodingBlocks plugin) {
        this.plugin = plugin;

        builder(b -> b.config(new EBMessages(plugin)));
        defaults
                .prefix("&6&lEB &8&l| &e")
                .p("&e")
                .s("&6");
    }

    @Override @NotNull
    public ExplodingBlocks getAnnoyingPlugin() {
        return plugin;
    }

    @Override
    public void accept(@NotNull AnnoyingMessages annoyingMessages) {
        messages = (EBMessages) annoyingMessages;
    }

    @Override @NotNull
    public EBMessages get() {
        return messages;
    }
}
