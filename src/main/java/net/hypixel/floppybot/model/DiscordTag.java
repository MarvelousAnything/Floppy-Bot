package net.hypixel.floppybot.model;

import lombok.Data;

@Data
public class DiscordTag {
    private String username;
    private String discriminator;

    public DiscordTag(String tag) {
        String[] parts = tag.split("#");
        this.username = parts[0];
        this.discriminator = parts[1];
    }

    public String toString() {
        return username + "#" + discriminator;
    }
}
