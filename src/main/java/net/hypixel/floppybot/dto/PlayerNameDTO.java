package net.hypixel.floppybot.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class PlayerNameDTO {
    private String name;
    private String id;
}
