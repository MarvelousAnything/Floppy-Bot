package net.hypixel.floppybot.service;

import net.hypixel.floppybot.dto.NameDTO;
import net.hypixel.floppybot.dto.PlayerNameDTO;
import net.hypixel.floppybot.dto.PlayerNamesListDTO;
import net.hypixel.floppybot.model.Player;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.UUID;

@Service
@FeignClient(name = "MOJANG-SERVICE", url = "https://api.mojang.com")
public interface MojangService {

    @GetMapping("/users/profiles/minecraft/{name}")
    PlayerNameDTO getPlayerByName(@PathVariable("name") String name);

    @GetMapping("/user/profiles/{uuid}/names")
    List<NameDTO> getPlayerByUUID(@PathVariable("uuid") String uuid);
}
