package main.Agenci;

import main.Komparatory.KomparatorProduktów;
import main.Produkty.*;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.TreeMap;

public class ProduktyDeserializerHelp {
    public static ArrayList<TreeMap<Produkt, Integer>> wczytaj(JsonElement jsonElement){
        JsonObject json = jsonElement.getAsJsonObject();
        ArrayList<TreeMap<Produkt, Integer>> produkty = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            produkty.add(new TreeMap<>(new KomparatorProduktów()));
        }
        produkty.get(0).put(new Jedzenie(), json.get("jedzenie").getAsInt());
        produkty.get(1).put(new Ubranie(1), json.get("ubrania").getAsInt());
        produkty.get(2).put(new Narzędzie(1), json.get("narzedzia").getAsInt());
        produkty.get(3).put(new ProgramKomputerowy(1), json.get("programy").getAsInt());
        return produkty;
    }
}
