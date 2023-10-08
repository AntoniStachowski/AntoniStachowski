package main.Agenci.Spekulanci;

import com.google.gson.*;

import java.lang.reflect.Type;

import static main.Agenci.ProduktySerializerHelp.ogarnij;
import static main.Main.mójGson;

public class SpekulantSerializer implements JsonSerializer<Spekulant> {
    @Override
    public JsonElement serialize(Spekulant src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject spekulant = new JsonObject();
        spekulant.addProperty("id", src.getId());
        spekulant.add("kariera", JsonParser.parseString(mójGson.toJson(src.getStrategiaSpekulanta())));
        JsonObject zasoby = ogarnij(src.getProdukty());
        zasoby.addProperty("diamenty", src.getDiamenty());
        spekulant.add("zasoby", zasoby);
        return spekulant;
    }
}
