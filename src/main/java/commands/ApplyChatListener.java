package commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.GuildChannel;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import utils.PlayerUtils;
import utils.embedBuilder;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.concurrent.ExecutionException;

public class ApplyChatListener extends ListenerAdapter {
    private LinkedHashMap<String, String> applicationData = new LinkedHashMap<>();
    private int applicationPhase = 0;

    public void onGuildMessageReceived(GuildMessageReceivedEvent e){
        if(Apply.applicants.get(e.getAuthor())!=null&&Apply.applicants.get(e.getAuthor()).equals(e.getChannel())){
            switch (applicationPhase){
                case 0:
                    e.getMessage().delete().queue();
                    try {
                        if(PlayerUtils.getUUID(e.getMessage().getContentRaw())!=null){
                            if(e.getAuthor().getAsTag().equalsIgnoreCase(PlayerUtils.getDiscord(PlayerUtils.getUUID(e.getMessage().getContentRaw())))){
                                Message botmsg = e.getChannel().retrieveMessageById(Apply.messageId).complete();
                                botmsg.delete().queue();
                                applicationData.put("Applications","Please enter your age (-1 if you decline to answer)");
                                applicationData.put("**IGN**",PlayerUtils.fixUsernameCaps(e.getMessage().getContentRaw()));
                                e.getChannel().sendMessage(embedBuilder.createEmbed(applicationData)).queue((message) -> Apply.messageId = message.getIdLong());
                                applicationPhase++;
                            }else{
                                e.getChannel().sendMessage(PlayerUtils.fixUsernameCaps(e.getMessage().getContentRaw())+" is not linked to your discord").queue();
                            }
                        }else{
                            e.getChannel().sendMessage(e.getMessage().getContentRaw()+" is not a valid IGN").queue();
                        }

                    } catch (IOException | InterruptedException | ExecutionException exception) {
                        exception.printStackTrace();
                    }
                    break;

                case 1:
                    e.getMessage().delete().queue();
                    try{
                        if(Integer.parseInt(e.getMessage().getContentRaw())<=100 && Integer.parseInt(e.getMessage().getContentRaw())>= 0){
                            applicationData.replace("Applications","Why would you like to join floppy? (Long Answer)");
                            applicationData.put("**Age**", e.getMessage().getContentRaw());
                            Message botmsg = e.getChannel().retrieveMessageById(Apply.messageId).complete();
                            botmsg.delete().queue();
                            e.getChannel().sendMessage(embedBuilder.createEmbed(applicationData)).queue((message) -> Apply.messageId = message.getIdLong());
                            applicationPhase++;
                        }else if(Integer.parseInt(e.getMessage().getContentRaw())==-1){
                            applicationData.replace("Applications","Why would you like to join SP3CTR3? (Long Answer)");
                            applicationData.put("**Age**", "Declined to Answer");
                            Message botmsg = e.getChannel().retrieveMessageById(Apply.messageId).complete();
                            botmsg.delete().queue();
                            e.getChannel().sendMessage(embedBuilder.createEmbed(applicationData)).queue((message) -> Apply.messageId = message.getIdLong());
                            applicationPhase++;
                        }else{
                            e.getChannel().sendMessage("Enter an age between 0 and 100 (-1 to decline to answer)").queue();
                        }
                    }catch(NumberFormatException exception){
                        e.getChannel().sendMessage("Please enter a number!").queue();
                    }

                    break;

                case 2:
                    applicationData.replace("Applications","Do you know anyone in the guild? If so, who?");
                    applicationData.put("**Why they want to join**", e.getMessage().getContentRaw());
                    Message botmsg = e.getChannel().retrieveMessageById(Apply.messageId).complete();
                    botmsg.delete().queue();
                    e.getChannel().sendMessage(embedBuilder.createEmbed(applicationData)).queue((message) -> Apply.messageId = message.getIdLong());
                    applicationPhase++;
                    e.getMessage().delete().queue();
                    break;

                case 3:

                    applicationData.put("**Who they know**", e.getMessage().getContentRaw());
                    Message botmsgA = e.getChannel().retrieveMessageById(Apply.messageId).complete();
                    botmsgA.delete().queue();
                    e.getChannel().sendMessage(embedBuilder.createEmbed(new HashMap<>(){{
                        put("Applications","Great! You're all set.  Your application was sent to the staff team");
                    }})).queue();
                    Apply.messageId=0;
                    applicationPhase = 0;
                    e.getMessage().delete().queue();
                    applicationData.remove("Applications");
                    applicationData.put("**Discord ID**", e.getAuthor().getAsTag());

                    Apply.applicants.remove(e.getAuthor());
                    for(GuildChannel channel : e.getGuild().getChannels()) {
                        if (channel.getName().equalsIgnoreCase("applications")) {
                            ((TextChannel) channel).sendMessage(embedBuilder.createEmbed(applicationData)).queue();
                            applicationData = new LinkedHashMap<>();
                            break;
                        }
                    }
                    break;
            }
        }
    }

}
