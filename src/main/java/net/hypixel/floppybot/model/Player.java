package net.hypixel.floppybot.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import net.hypixel.floppybot.dto.NameDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Data
public class Player {

    private String id;
    private String name;
    private List<String> names;
    private DiscordTag discordTag;

    public void setNames(List<NameDTO> names) {
        this.names = names.stream().map(NameDTO::getName).collect(Collectors.toList());
    }

}
