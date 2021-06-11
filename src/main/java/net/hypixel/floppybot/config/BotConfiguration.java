package net.hypixel.floppybot.config;

import discord4j.core.DiscordClientBuilder;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.Event;
import discord4j.discordjson.json.EmbedFooterData;
import discord4j.discordjson.json.EmbedThumbnailData;
import discord4j.discordjson.json.ImmutableEmbedData;
import net.dv8tion.jda.api.JDABuilder;
import net.hypixel.floppybot.event.EventListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.awt.*;
import java.util.List;

@Configuration
public class BotConfiguration {
    @Value("${token}")
    private String token;

    @Value("${thumbnail}")
    private String thumbnailUrl = "https://cdn.discordapp.com/attachments/826929655851384922/852204578064564254/a_3596d9dbe4464e2d1cb9265d8ceb3ccf.png"

    @Bean
    public GatewayDiscordClient gatewayDiscordClient() {
        return DiscordClientBuilder.create(token)
                .build()
                .login()
                .block();
    }

    @Bean
    public <T extends Event> GatewayDiscordClient gatewayDiscordClient(List<EventListener<T>> eventListeners) {
        GatewayDiscordClient client = DiscordClientBuilder.create(token)
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

    @Bean
    public ImmutableEmbedData.Builder embedBuilder() {
        return ImmutableEmbedData.builder()
            .title("-SP3CTR3 BOT-")
            .description("This is in BETA - DM Yoursole1#7254 with issues")
            .thumbnail(EmbedThumbnailData.builder().url(thumbnailUrl).build())
            .footer(EmbedFooterData.builder().text("Created with \u2665 by Yoursole1#7254").build())
            .color(2871056);
    }
}
