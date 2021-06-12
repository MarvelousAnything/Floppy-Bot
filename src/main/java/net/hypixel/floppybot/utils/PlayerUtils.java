//package net.hypixel.floppybot.utils;
//
//import com.google.gson.JsonArray;
//import com.google.gson.JsonObject;
//import com.google.gson.JsonParser;
//import net.hypixel.api.HypixelAPI;
//
//import java.io.IOException;
//import java.net.URL;
//import java.nio.charset.StandardCharsets;
//import java.util.NoSuchElementException;
//import java.util.Scanner;
//import java.util.UUID;
//import java.util.concurrent.ExecutionException;
//
//public class PlayerUtils {
//
//    /**
//     * Takes in the player's UUID and returns their discord tag (getUUID(MyExampleMcAccount) -> Example#1234)
//     * This is best used with getUUID()
//     * @param uuid
//     * @return
//     * @throws ExecutionException
//     * @throws InterruptedException
//     */
//
//    public static String getDiscord(String uuid) throws ExecutionException, InterruptedException {
//        final UUID API_UUID = UUID.fromString("cf5c2051-35d6-4d1a-88f9-57924b6ed9a4");
//        HypixelAPI api = new HypixelAPI(API_UUID);
//        JsonObject json = null;
//        try{
//            json = new JsonParser().parse(api.getPlayerByUuid(uuid).get().getPlayer().toString()).getAsJsonObject();
//            if(json.get("socialMedia")!=null){
//                JsonObject socialMedia = json.getAsJsonObject("socialMedia");
//                if(socialMedia.get("links")!=null){
//                    JsonObject links = socialMedia.getAsJsonObject("links");
//                    if(links.get("DISCORD")!=null){
//                        return links.get("DISCORD").toString().replace("\"","");
//                    }
//                }
//            }
//            return null;
//        }catch(ExecutionException e){
//            return null;
//        }
//    }
//
//    /**
//     * Takes in the IGN of a minecraft account and returns its UUID
//     * @param name
//     * @return
//     * @throws IOException
//     */
//
//    public static String getUUID(String name) throws IOException {
//        String url = "https://api.mojang.com/users/profiles/minecraft/"+name;
//        String UUIDJson = null;
//        try{
//            UUIDJson = new Scanner(new URL(url).openStream(), StandardCharsets.UTF_8).useDelimiter("\\A").next();
//            JsonObject JsonFile = (JsonObject) new JsonParser().parse(UUIDJson);
//            String uuid = JsonFile.get("id").toString();
//            return uuid.replace("\"","");
//        }catch(NoSuchElementException e){
//            return null;
//        }
//    }
//
//    /**
//     * Takes in the IGN of a minecraft account and returns it with the capital letter formatting fixed
//     * (example: yoursole -> Yoursole)
//     * @param ign
//     * @return
//     * @throws IOException
//     */
//    public static String fixUsernameCaps(String ign) throws IOException {
//        String name = null;
//        String url = "https://api.mojang.com/user/profiles/"+getUUID(ign)+"/names";
//        String UUIDJson = null;
//        UUIDJson = new Scanner(new URL(url).openStream(), StandardCharsets.UTF_8).useDelimiter("\\A").next();
//        JsonArray JsonFile = (JsonArray) new JsonParser().parse(UUIDJson);
//        name =JsonFile.get(JsonFile.size()-1).getAsJsonObject().get("name").toString();
//        name = name.replaceAll("\"","");
//        return name;
//    }
//
//}
