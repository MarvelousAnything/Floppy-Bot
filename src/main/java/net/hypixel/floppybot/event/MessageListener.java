package net.hypixel.floppybot.event;

import discord4j.core.object.entity.Message;
import discord4j.core.spec.EmbedCreateSpec;
import discord4j.discordjson.json.ImmutableEmbedData;
import net.hypixel.floppybot.embed.EmbedBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.core.publisher.Mono;

import java.util.function.Consumer;

public abstract class MessageListener {

    @Autowired
    private EmbedBuilder embedBuilder;

    public Mono<Void> processCommand(Message eventMessage) {
        return Mono.just(eventMessage)
                .filter(message -> message.getAuthor().map(user -> !user.isBot()).orElse(false))
                .filter(message -> message.getContent().equalsIgnoreCase("!todo"))
                .flatMap(Message::getChannel)
                .flatMap(channel ->
                        channel.createEmbed(embedBuilder
                        .addField(
                                "Application",
                                "test",
                                true)
                                .build()
                        )
                )
                .then();
    }
}
