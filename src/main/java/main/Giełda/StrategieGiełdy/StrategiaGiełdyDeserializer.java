package main.Giełda.StrategieGiełdy;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

public class StrategiaGiełdyDeserializer implements JsonDeserializer<StrategiaGiełdy> {
    @Override
    public StrategiaGiełdy deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        switch (json.getAsString()) {
            case "kapitalistyczna":
                return new Kapitalistyczna();
            case "socjalistyczna":
                return new Socjalistyczna();
            case "zrownowazona":
                return new Zrównoważona();
            default:
                return null;
        }
    }
}
