package main.Agenci.Robotnicy.StrategieNauki;

import com.google.gson.*;

import java.lang.reflect.Type;

public class StrategiaNaukiDeserializer implements JsonDeserializer<StrategiaNauki> {
    @Override
    public StrategiaNauki deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject json = jsonElement.getAsJsonObject();

        switch (json.get("typ").getAsString()) {
            case "pracus":
                return new Pracuś();
            case "rozkladowy":
                return new Rozkładowy();
            case "okresowy":
                return new Okresowy(json.get("okresowosc_nauki").getAsInt());
            case "oszczedny":
                return new Oszczędny(json.get("limit_diamentow").getAsDouble());
            case "student":
                return new Student(json.get("zapas").getAsInt(), json.get("okres").getAsInt());
            default:
                return null;
        }
    }
}
