package net.hypixel.floppybot.event;

import discord4j.core.event.domain.message.MessageCreateEvent;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.hypixel.floppybot.embed.EmbedBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.function.Function;
import java.util.function.Supplier;

@Slf4j
public abstract class MessageCreateListener extends MessageListener implements EventListener<MessageCreateEvent> {
    @Override
    public Class<MessageCreateEvent> getEventType() {
        return MessageCreateEvent.class;
    }

    @Override
    public Mono<Void> execute(MessageCreateEvent event) {
        log.info("Message Created!");
        return processCommand(event.getMessage());
    }
}

