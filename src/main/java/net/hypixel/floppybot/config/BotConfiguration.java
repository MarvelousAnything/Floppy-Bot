package net.hypixel.floppybot.config;

import discord4j.core.DiscordClientBuilder;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.Event;
import discord4j.core.object.entity.Message;
import discord4j.core.spec.EmbedCreateSpec;
import discord4j.discordjson.json.EmbedFooterData;
import discord4j.discordjson.json.EmbedThumbnailData;
import discord4j.discordjson.json.ImmutableEmbedData;
import discord4j.rest.util.Color;
import net.hypixel.floppybot.event.EventListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.function.Consumer;

@Configuration
public class BotConfiguration {

    @Autowired
    private BotProperties properties;

    @Bean
    public GatewayDiscordClient gatewayDiscordClient() {
        return DiscordClientBuilder.create(properties.getToken())
                .build()
                .login()
                .block();
    }

    @Bean
    public <T extends Event> GatewayDiscordClient gatewayDiscordClient(List<EventListener<T>> eventListeners) {
        GatewayDiscordClient client = DiscordClientBuilder.create(properties.getToken())
                .build()
                .login()
                .block();

        for(EventListener<T> listener : eventListeners) {
            client.on(listener.getEventType())
                    .flatMap(listener::execute)
                    .onErrorResume(listener::handleError)
                    .subscribe();
        }

        return client;
    }

//    return EmbedCreateSpec.builder()
//            .title(properties.getEmbed().getTitle())
//            .description(properties.getEmbed().getDescription())
//            .thumbnail(EmbedThumbnailData.builder().url(properties.getEmbed().getThumbnail().getUrl()).build())
//            .footer(EmbedFooterData.builder().text(properties.getEmbed().getFooter().getText()).build())
//            .color(properties.getEmbed().getColor());
    @Bean
    public Consumer<EmbedCreateSpec> embedCreateSpecConsumer() {
        return (embedCreateSpec) -> embedCreateSpec
            .setTitle(properties.getEmbed().getTitle())
            .setDescription(properties.getEmbed().getDescription())
            .setThumbnail(properties.getEmbed().getThumbnail().getUrl())
            .setFooter(properties.getEmbed().getFooter().getText(), null)
            .setColor(Color.GREEN);
    }

}
