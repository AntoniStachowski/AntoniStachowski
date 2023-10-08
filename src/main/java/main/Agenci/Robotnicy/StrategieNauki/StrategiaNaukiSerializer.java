package main.Agenci.Robotnicy.StrategieNauki;

import com.google.gson.*;

import java.lang.reflect.Type;

public class StrategiaNaukiSerializer implements JsonSerializer<StrategiaNauki> {
    @Override
    public JsonElement serialize(StrategiaNauki src, Type typeOfSrc, JsonSerializationContext context) {
        String args = new Gson().toJson(src);
        JsonObject strategia = JsonParser.parseString(args).getAsJsonObject();
        strategia.addProperty("typ", src.dajNazwę());
        return strategia;
    }
}
