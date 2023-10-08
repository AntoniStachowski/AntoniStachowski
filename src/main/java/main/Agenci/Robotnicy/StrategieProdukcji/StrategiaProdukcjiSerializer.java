package main.Agenci.Robotnicy.StrategieProdukcji;

import com.google.gson.*;

import java.lang.reflect.Type;

public class StrategiaProdukcjiSerializer implements JsonSerializer<StrategiaProdukcji> {
    @Override
    public JsonElement serialize(StrategiaProdukcji src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject strategia = JsonParser.parseString(new Gson().toJson(src)).getAsJsonObject();
        strategia.addProperty("typ", src.dajNazwę());
        return strategia;
    }
}
