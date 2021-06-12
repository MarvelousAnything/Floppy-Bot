package net.hypixel.floppybot;

import net.hypixel.floppybot.service.MojangService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ApplicationContext;

@EnableFeignClients
@SpringBootApplication
public class FloppyBotApplication {
    public static void main(String[] args) {
        SpringApplication.run(FloppyBotApplication.class, args);
    }
}
