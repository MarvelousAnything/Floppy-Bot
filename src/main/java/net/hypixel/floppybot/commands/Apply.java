package net.hypixel.floppybot.commands;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;
import net.hypixel.floppybot.utils.embedBuilder;

import java.util.HashMap;
import java.util.Objects;

public class Apply {
    public static long messageId = 0;
    public static HashMap<User,TextChannel> applicants = new HashMap<>();

    /**
     * This adds the user and channel they are trying to apply in to the "applicants" hashmap.
     * This is then used in the ApplyChatListener.java class, to track which users are in the application
     * process and where they are doing it.  If the user is not verified, it will not let them apply
     * @param content
     * @param member
     * @param user
     * @param guild
     * @param channel
     */
    public static void apply(String content, Member member, User user, Guild guild, TextChannel channel){
        if(Objects.requireNonNull(member).getRoles().contains(guild.getRolesByName("Verified",true).get(0))){
            channel.sendMessage(embedBuilder.createEmbed(new HashMap<>(){{
                put("Applications","What is your ign?");
            }})).queue((message) -> messageId = message.getIdLong());

            applicants.put(user,channel);
        }else{
            channel.sendMessage(embedBuilder.createEmbed(new HashMap<>(){{
                put("Applications","Please Verify First!  (?verify \"IGN\")");
            }})).queue();
        }


    }
}


