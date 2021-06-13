package net.hypixel.floppybot.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class PlayerNamesListDTO {
    private ArrayList<NameDTO> names;

    public String getCurrentName() {
        return names.get(names.size()-1).getName();
    }
}
