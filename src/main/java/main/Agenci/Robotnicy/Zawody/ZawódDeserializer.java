package main.Agenci.Robotnicy.Zawody;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

public class ZawódDeserializer implements JsonDeserializer<Zawód> {
    @Override
    public Zawód deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        String nazwaZawodu = jsonElement.getAsString();
        switch (nazwaZawodu) {
            case "rolnik":
                return new Rolnik();
            case "gornik":
                return new Górnik();
            case "inzynier":
                return new Inżynier();
            case "rzemieslnik":
                return new Rzemieślinik();
            case "programista":
                return new Programista();
            default :
                return null;
        }
    }
}
