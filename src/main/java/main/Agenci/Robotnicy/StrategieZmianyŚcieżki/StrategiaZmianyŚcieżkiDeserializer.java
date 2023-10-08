package main.Agenci.Robotnicy.StrategieZmianyŚcieżki;

import com.google.gson.*;

import java.lang.reflect.Type;

public class StrategiaZmianyŚcieżkiDeserializer implements JsonDeserializer<StrategiaZmianyŚcieżki> {
    @Override
    public StrategiaZmianyŚcieżki deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        switch (jsonElement.getAsString()){
            case "konserwatysta":
                return new Konserwatysta();
            case "rewolucjonista":
                return new Rewolucjonista();
            default:
                return null;
        }
    }
}
