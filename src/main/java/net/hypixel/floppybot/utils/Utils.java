package net.hypixel.floppybot.utils;

import feign.Feign;
import feign.gson.GsonDecoder;
import feign.jackson.JacksonDecoder;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.hypixel.floppybot.service.MojangService;

import java.awt.*;
import java.util.HashMap;

public class Utils {

    public static final MojangService mojangService = Feign.builder()
            .decoder(new JacksonDecoder())
            .target(MojangService.class, "https://api.mojang.com");

    private static final EmbedBuilder defaultEmbedBuilder = new EmbedBuilder() {{
        setTitle("-SP3CTR3 BOT-");
        setDescription("This is in BETA - DM Yoursole1#7254 with issues");
        setThumbnail("https://cdn.discordapp.com/attachments/826929655851384922/852204578064564254/a_3596d9dbe4464e2d1cb9265d8ceb3ccf.png");
        setFooter("Created with \u2665 by Yoursole1#7254");
        setColor(new Color(2871056));
    }};

    public static MessageEmbed createEmbed(HashMap<String, String> content) {
        mojangService.getPlayerByName("MarvelousAny").getName();
        content.keySet()
                .forEach(field ->
                        defaultEmbedBuilder.addField(
                                field,
                                content.get(field),
                                false));
        return defaultEmbedBuilder.build();
    }

}
