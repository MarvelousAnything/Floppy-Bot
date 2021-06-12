//package net.hypixel.floppybot.utils;
//
//import net.dv8tion.jda.api.EmbedBuilder;
//import net.dv8tion.jda.api.entities.MessageEmbed;
//
//import java.awt.*;
//import java.util.HashMap;
//
//public class embedBuilder {
//
//    public static MessageEmbed createEmbed(HashMap<String,String> content){
//
//
//        EmbedBuilder embedBuilder = new EmbedBuilder();
//        embedBuilder.setTitle("-SP3CTR3 BOT-");
//        embedBuilder.setDescription("This is in BETA - DM Yoursole1#7254 with issues");
//        embedBuilder.setThumbnail("https://cdn.discordapp.com/attachments/826929655851384922/852204578064564254/a_3596d9dbe4464e2d1cb9265d8ceb3ccf.png");
//
//        for(String field : content.keySet()){
//            embedBuilder.addField(field,content.get(field),false);
//        }
//
//        embedBuilder.setFooter("Created with \u2665 by Yoursole1#7254");
//        embedBuilder.setColor(new Color(2871056));
//        return embedBuilder.build();
//    }
//
//}
