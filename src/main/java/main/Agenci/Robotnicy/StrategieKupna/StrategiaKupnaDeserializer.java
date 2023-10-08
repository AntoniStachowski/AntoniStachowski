package main.Agenci.Robotnicy.StrategieKupna;

import com.google.gson.*;

import java.lang.reflect.Type;

public class StrategiaKupnaDeserializer implements JsonDeserializer<StrategiaKupna> {
    @Override
    public StrategiaKupna deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        String typ = jsonObject.get("typ").getAsString();
        switch (typ) {
            case "technofob":
                return new Technofob();
            case "czyscioszek":
                return new Czyścioszek();
            case "zmechanizowany":
                return new Zmechanizowany(jsonObject.get("liczba_narzedzi").getAsInt());
            case "gadzeciarz":
                return new Gadżeciarz(jsonObject.get("liczba_narzedzi").getAsInt());
            default:
                return null;
        }
    }
}
