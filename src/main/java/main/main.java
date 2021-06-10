package main;

import commands.ApplyChatListener;
import commands.Commands;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.Compression;
import net.dv8tion.jda.api.utils.cache.CacheFlag;

import javax.security.auth.login.LoginException;

public class main {
    public static JDABuilder builder;
    public static void main(String[] args) throws LoginException {
        String token = "TOKEN HERE";
        builder = JDABuilder.createDefault(token);
        builder.disableCache(CacheFlag.MEMBER_OVERRIDES, CacheFlag.VOICE_STATE);
        builder.setBulkDeleteSplittingEnabled(false);
        builder.setCompression(Compression.NONE);
        builder.setActivity(Activity.playing("SP3CTR3 Games!"));
        builder.enableIntents(GatewayIntent.GUILD_MEMBERS);
        builder.addEventListeners(new ApplyChatListener());
        builder.addEventListeners(new Commands());
        builder.build();
    }
}
