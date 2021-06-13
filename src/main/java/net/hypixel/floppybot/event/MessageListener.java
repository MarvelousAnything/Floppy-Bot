package net.hypixel.floppybot.event;

import discord4j.core.object.entity.Message;
import lombok.AllArgsConstructor;
import net.hypixel.floppybot.embed.EmbedBuilder;
import reactor.core.publisher.Mono;

import java.util.function.Function;

public abstract class MessageListener {

    public abstract String getPrefix();
    public abstract Function<Message, Mono<Void>> getFunction();

    public String parseCommand(String command) {
        return command.replaceAll(getPrefix() + " ", "");
    }

    public Mono<Void> processCommand(Message eventMessage) {
        return Mono.just(eventMessage)
                .filter(message -> message.getAuthor().map(user -> !user.isBot()).orElse(false))
                .filter(message -> message.getContent().startsWith(getPrefix()))
                .flatMap(Message::getChannel)
                .flatMap(channel -> getFunction().apply(eventMessage));
    }
}
