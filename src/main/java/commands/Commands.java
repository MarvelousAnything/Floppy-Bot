package commands;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import utils.PlayerUtils;
import utils.embedBuilder;

import java.io.IOException;
import java.util.HashMap;
import java.util.Objects;
import java.util.concurrent.ExecutionException;

public class Commands extends ListenerAdapter {

    public void onGuildMessageReceived(GuildMessageReceivedEvent e){
        String content = e.getMessage().getContentRaw();

        //switch of the first word in the message
        switch(content.split(" ",-1)[0].toLowerCase()){


            case "?verify":
                Verify.verify(content,e.getMember(),e.getAuthor(),e.getGuild(),e.getChannel());
                break;


            case "?help":
                e.getChannel().sendMessage(embedBuilder.createEmbed(new HashMap<>(){{
                    put("Help Section","**?help**\nThis Page\n**?verify \"ign\"**\nVerify yourself\n" +
                            "**?apply**\nApply for the guild.  It will walk you through the process");
                }})).queue();
                break;


            case "?apply":
                Apply.apply(content,e.getMember(),e.getAuthor(),e.getGuild(),e.getChannel());
                break;

        }
    }
}
