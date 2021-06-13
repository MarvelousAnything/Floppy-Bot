package net.hypixel.floppybot;

import net.hypixel.floppybot.dto.PlayerNamesListDTO;
import net.hypixel.floppybot.mapper.PlayerMapper;
import net.hypixel.floppybot.model.Player;
import net.hypixel.floppybot.service.HypixelService;
import net.hypixel.floppybot.service.MojangService;
import org.mapstruct.factory.Mappers;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ApplicationContext;

import java.util.List;
import java.util.concurrent.ExecutionException;

@EnableFeignClients
@SpringBootApplication
public class FloppyBotApplication {
    public static void main(String[] args) {
        SpringApplication.run(FloppyBotApplication.class, args);
    }
}
