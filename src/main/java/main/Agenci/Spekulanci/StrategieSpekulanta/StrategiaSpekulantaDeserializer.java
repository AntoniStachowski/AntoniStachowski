package main.Agenci.Spekulanci.StrategieSpekulanta;

import com.google.gson.*;

import java.lang.reflect.Type;

public class StrategiaSpekulantaDeserializer implements JsonDeserializer<StrategiaSpekulanta> {
    @Override
    public StrategiaSpekulanta deserialize(JsonElement jsonElement, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject json = jsonElement.getAsJsonObject();
        switch (json.get("typ").getAsString()){
            case "sredni":
                return new Średni(json.get("historia_spekulanta_sredniego").getAsInt());
            case "wypukly":
                return new Wypukły();
            case "regulujacy_rynek":
                return new RegulującyRynek();
            default:
                return null;
        }
    }
}
