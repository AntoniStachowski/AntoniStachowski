package main.Agenci.Robotnicy.StrategieKupna;

import com.google.gson.*;

import java.lang.reflect.Type;

public class StrategiaKupnaSerializer implements JsonSerializer<StrategiaKupna> {
    @Override
    public JsonElement serialize(StrategiaKupna strategiaKupna, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject strategia = JsonParser.parseString(new Gson().toJson(strategiaKupna)).getAsJsonObject();
        strategia.addProperty("typ", strategiaKupna.dajNazwę());

        return strategia;
    }
}
