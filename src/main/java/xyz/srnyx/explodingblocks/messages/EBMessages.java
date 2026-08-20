package xyz.srnyx.explodingblocks.messages;

import eu.okaeri.configs.annotation.Comment;
import eu.okaeri.configs.annotation.Include;
import eu.okaeri.configs.annotation.IncludePosition;
import eu.okaeri.validator.annotation.NotNull;
import xyz.srnyx.annoyingapi.message.AnnoyingMessages;
import xyz.srnyx.annoyingapi.message.json.message.JsonChatMessage;
import xyz.srnyx.explodingblocks.ExplodingBlocks;


@Include(value = AnnoyingMessages.class, position = IncludePosition.BEFORE)
public class EBMessages extends AnnoyingMessages {
    public EBMessages(@org.jetbrains.annotations.NotNull ExplodingBlocks plugin) {
        super(plugin);
    }

    @Comment
    @NotNull public JsonChatMessage reload = defaultMessage("%prefix%Plugin successfully reloaded!@@%p%%command%@@%command%");

    @Comment
    @Comment("Placeholders: %state==boolean%")
    @NotNull public JsonChatMessage toggle = defaultMessage("%prefix%Exploding Blocks has been %s%%state==enabled//disabled%@@%p%%command%@@%command%");
}
