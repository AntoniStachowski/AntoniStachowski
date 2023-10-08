package main.Agenci.Robotnicy.StrategieProdukcji;

import com.google.gson.*;

import java.lang.reflect.Type;

public class StrategiaProdukcjiDeserializer implements JsonDeserializer<StrategiaProdukcji> {
    @Override
    public StrategiaProdukcji deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject json = jsonElement.getAsJsonObject();
        String nazwaStrategii = json.get("typ").getAsString();
        switch (nazwaStrategii) {
            case "chciwy":
                return new Chciwy();
            case "losowy":
                return new Losowy();
            case "krotkowzroczny":
                return new Krótkowzroczny();
            case "perspektywiczny":
                return new Perspektywiczny(json.get("historia_perspektywy").getAsInt());
            case "sredniak":
                return new Średniak(json.get("historia_sredniej_produkcji").getAsInt());
            default:
                return null;
        }
    }
}
