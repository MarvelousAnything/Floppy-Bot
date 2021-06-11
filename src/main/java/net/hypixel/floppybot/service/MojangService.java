package net.hypixel.floppybot.service;

import feign.Param;
import feign.RequestLine;
import net.hypixel.floppybot.model.Player;

import java.util.List;

public interface MojangService {
    @RequestLine("GET /users/profiles/minecraft/{name}")
    public Player getPlayerByName(@Param("name") String name);

    @RequestLine("GET /user/profiles/{uuid}/names")
    public List<Player> getPlayerByUUID(@Param("uuid") String uuid);
}
