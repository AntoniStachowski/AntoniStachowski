package main.Agenci.Robotnicy.StrategieZmianyŚcieżki;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

public class StrategiaZmianyŚcieżkiSerializer implements JsonSerializer<StrategiaZmianyŚcieżki> {
    @Override
    public JsonElement serialize(StrategiaZmianyŚcieżki src, Type typeOfSrc, JsonSerializationContext context) {
        return new JsonPrimitive(src.dajNazwę());
    }
}
