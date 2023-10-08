package main.Agenci.Spekulanci.StrategieSpekulanta;

import com.google.gson.*;

import java.lang.reflect.Type;

public class StrategiaSpekulantaSerializer implements JsonSerializer<StrategiaSpekulanta> {
    @Override
    public JsonElement serialize(StrategiaSpekulanta src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject strategia = JsonParser.parseString(new Gson().toJson(src)).getAsJsonObject();
        strategia.addProperty("typ", src.dajNazwę());

        return strategia;
    }
}
