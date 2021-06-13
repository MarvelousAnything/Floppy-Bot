package net.hypixel.floppybot.command;

import discord4j.core.object.entity.Message;
import discord4j.core.object.entity.channel.MessageChannel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import net.hypixel.floppybot.event.MessageCreateListener;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@Getter
@AllArgsConstructor
public class CommandListener extends MessageCreateListener {
    private final String prefix;
    private final Function<Message, Mono<Void>> function;
}
