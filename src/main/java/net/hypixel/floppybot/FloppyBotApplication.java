package net.hypixel.floppybot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FloppyBotApplication {
//    public static JDABuilder builder;

    public static void main(String[] args) {
//        String token = System.getenv("TOKEN") == null ? args[0] : System.getenv("TOKEN");
//        builder = JDABuilder.createDefault(token);
//        builder.disableCache(CacheFlag.MEMBER_OVERRIDES, CacheFlag.VOICE_STATE);
//        builder.setBulkDeleteSplittingEnabled(false);
//        builder.setCompression(Compression.NONE);
//        builder.setActivity(Activity.playing("SP3CTR3 Games!"));
//        builder.enableIntents(GatewayIntent.GUILD_MEMBERS);
//        builder.addEventListeners(new ApplyChatListener());
//        builder.addEventListeners(new Commands());
//        builder.build();
        SpringApplication.run(FloppyBotApplication.class, args);
    }
}
