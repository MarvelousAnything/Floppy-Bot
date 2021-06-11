package net.hypixel.floppybot.utils;

import feign.Response;
import feign.gson.GsonDecoder;

import java.io.IOException;
import java.lang.reflect.Type;

public class IdDecoder extends GsonDecoder {
    @Override
    public Object decode(Response response, Type type) throws IOException {
        super.decode(response, type);
    }
}
