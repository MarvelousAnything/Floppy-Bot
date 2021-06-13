package net.hypixel.floppybot.service;

import com.google.gson.JsonObject;
import lombok.AllArgsConstructor;
import net.hypixel.api.HypixelAPI;
import net.hypixel.floppybot.config.HypixelProperties;
import net.hypixel.floppybot.model.DiscordTag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
@AllArgsConstructor
public class HypixelService {

    private final HypixelAPI hypixelAPI;

    public DiscordTag getDiscordTag(String uuid) throws ExecutionException, InterruptedException {
        JsonObject player = hypixelAPI.getPlayerByUuid(uuid).get().getPlayer();
        return new DiscordTag(player.get("socialMedia").getAsJsonObject().get("links").getAsJsonObject().get("DISCORD").getAsString());
    }
}
