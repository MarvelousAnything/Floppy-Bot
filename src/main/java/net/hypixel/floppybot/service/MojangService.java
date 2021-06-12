package net.hypixel.floppybot.service;

import feign.Param;
import feign.RequestLine;
import net.hypixel.floppybot.model.Player;

import java.util.List;

public interface MojangService {
    @RequestLine("GET /users/profiles/minecraft/{name}")
    Player getPlayerByName(@Param("name") String name);

    @RequestLine("GET /user/profiles/{uuid}/names")
    List<Player> getPlayerByUUID(@Param("uuid") String uuid);
}
