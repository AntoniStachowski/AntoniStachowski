package main.Agenci.Spekulanci;

import main.Agenci.Spekulanci.StrategieSpekulanta.StrategiaSpekulanta;
import main.Produkty.Produkt;
import com.google.gson.*;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.TreeMap;

import static main.Agenci.ProduktyDeserializerHelp.wczytaj;
import static main.Main.mójGson;

public class SpekulantDeserializer implements JsonDeserializer<Spekulant> {

    @Override
    public Spekulant deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject json = jsonElement.getAsJsonObject();
        int id = json.get("id").getAsInt();

        StrategiaSpekulanta strategiaSpekulanta = mójGson.fromJson(json.get("kariera"), StrategiaSpekulanta.class);

        JsonObject zasoby = json.get("zasoby").getAsJsonObject();

        ArrayList<TreeMap<Produkt, Integer>> produkty = wczytaj(zasoby);
        double diamenty = zasoby.get("diamenty").getAsInt();

        return new Spekulant(id, diamenty, produkty, strategiaSpekulanta);
    }
}
