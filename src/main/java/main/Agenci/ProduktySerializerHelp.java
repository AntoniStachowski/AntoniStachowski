package main.Agenci;

import main.Produkty.Jedzenie;
import main.Produkty.Produkt;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.TreeMap;

public class ProduktySerializerHelp {
    public static JsonObject ogarnij(ArrayList<TreeMap<Produkt, Integer>> src) {
        JsonObject produkty = new JsonObject();
        produkty.addProperty("jedzenie", src.get(0).get(new Jedzenie()));
        produkty.add("ubrania", ogarnijSingle(src.get(1)));
        produkty.add("narzedzia", ogarnijSingle(src.get(2)));
        produkty.add("programy", ogarnijSingle(src.get(3)));
        return produkty;
    }

    private static JsonArray ogarnijSingle(TreeMap<Produkt, Integer> src){
        if(src.isEmpty()){
            return new JsonArray();
        }
        int size = src.firstKey().getPoziom();
        int[] poziomy = new int[size];

        for(Produkt produkt: src.keySet()) {
            poziomy[produkt.getPoziom() - 1] += src.get(produkt);
        }

        JsonArray produkty = new JsonArray();
        for(int i = 0; i < size; i++) {
            produkty.add(poziomy[i]);
        }

        return produkty;
    }
}
