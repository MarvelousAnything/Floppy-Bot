package commands;

import net.dv8tion.jda.api.entities.*;
import utils.PlayerUtils;
import utils.embedBuilder;

import java.io.IOException;
import java.util.HashMap;
import java.util.Objects;
import java.util.concurrent.ExecutionException;



public class Verify {

    /**
     * This takes in the required information to check if a user can verify to a minecraft account,
     * and if they can, it adds the verified role and sends a message.
     * @param content
     * @param member
     * @param user
     * @param guild
     * @param channel
     */
    public static void verify(String content, Member member, User user, Guild guild, TextChannel channel){
        if(!Objects.requireNonNull(member).getRoles().contains(guild.getRolesByName("Verified",true).get(0))){
            if(content.split(" ",-1).length>1){
                String discord = null;
                //get the user's discord
                try {
                    discord = PlayerUtils.getDiscord(PlayerUtils.getUUID(content.split(" ",-1)[1]));
                } catch (ExecutionException | InterruptedException | IOException executionException) {
                    executionException.printStackTrace();
                }

                //if discord is null that means that no such Object was found in the API
                // (this account hasnt set up discord or the account doesn't exist)
                //if discord equals the sender's discord then it is their account...so verify
                //otherwise, that means that the discord was found but it doesn't match, therefor its not their account
                if(discord==null){

                    channel.sendMessage(embedBuilder.createEmbed(new HashMap<>(){{
                        put("Verification","Error - Something went wrong");
                    }})).queue();

                }else if(discord.equalsIgnoreCase(user.getAsTag())){
                    guild.addRoleToMember(Objects.requireNonNull(member), guild.getRolesByName("Verified",true).get(0)).queue();


                    channel.sendMessage(embedBuilder.createEmbed(new HashMap<>(){{
                        try {
                            put("Verification",PlayerUtils.fixUsernameCaps(content.split(" ",-1)[1])+", you were verified");
                        } catch (IOException exception) {
                            exception.printStackTrace();
                        }
                    }})).queue();

                }else{

                    channel.sendMessage(embedBuilder.createEmbed(new HashMap<>(){{
                        put("Verification","Error - This is not your account to verify");
                    }})).queue();

                }
            }else{
                channel.sendMessage(embedBuilder.createEmbed(new HashMap<>(){{
                    put("Verification","Error - Please specify an IGN");
                }})).queue();
            }
        }else{
            channel.sendMessage(embedBuilder.createEmbed(new HashMap<>(){{
                put("Verification","Error - You are already verified");
            }})).queue();
        }
    }
}
