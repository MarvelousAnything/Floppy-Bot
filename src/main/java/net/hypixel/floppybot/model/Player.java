package net.hypixel.floppybot.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import net.hypixel.floppybot.utils.Utils;

@Data
public class Player {
    String name;
    private String id;

    Player(@JsonProperty String name) {
        this(Utils.mojangService.getPlayerByName(name));
    }

    Player(Player player) {
        this.name = player.getName();
        this.id = player.getId();
    }
}
