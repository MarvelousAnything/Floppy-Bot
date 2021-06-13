package net.hypixel.floppybot.config;

import net.hypixel.api.HypixelAPI;
import net.hypixel.floppybot.mapper.PlayerMapper;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HypixelConfiguration {
    @Autowired
    private HypixelProperties properties;

    @Bean
    public HypixelAPI hypixelAPI() {
        return new HypixelAPI(properties.getToken());
    }
}
