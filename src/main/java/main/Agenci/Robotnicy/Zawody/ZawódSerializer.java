package main.Agenci.Robotnicy.Zawody;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

public class ZawódSerializer implements JsonSerializer<Zawód> {
    @Override
    public JsonElement serialize(Zawód src, Type typeOfSrc, JsonSerializationContext context) {
        return new JsonPrimitive(src.dajNazwę());
    }
}
