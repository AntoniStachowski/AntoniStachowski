package main.Agenci.Robotnicy;

import main.Agenci.Robotnicy.StrategieKupna.StrategiaKupna;
import main.Agenci.Robotnicy.StrategieNauki.StrategiaNauki;
import main.Agenci.Robotnicy.StrategieProdukcji.StrategiaProdukcji;
import main.Agenci.Robotnicy.StrategieZmianyŚcieżki.StrategiaZmianyŚcieżki;
import main.Agenci.Robotnicy.Zawody.Zawód;
import main.Produkty.Produkt;
import com.google.gson.*;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.TreeMap;

import static main.Agenci.ProduktyDeserializerHelp.wczytaj;
import static main.Main.mójGson;

public class RobotnikDeserializer implements JsonDeserializer<Robotnik> {

    @Override
    public Robotnik deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject json = jsonElement.getAsJsonObject();
        int id = json.get("id").getAsInt();
        int poziom = json.get("poziom").getAsInt();
        Zawód zawód = mójGson.fromJson(json.get("kariera"), Zawód.class);
        zawód.setPoziom(poziom);
        StrategiaKupna strategiaKupna = mójGson.fromJson(json.get("kupowanie"), StrategiaKupna.class);
        StrategiaProdukcji strategiaProdukcji = mójGson.fromJson(json.get("produkcja"), StrategiaProdukcji.class);
        StrategiaNauki strategiaNauki = mójGson.fromJson(json.get("uczenie"), StrategiaNauki.class);
        StrategiaZmianyŚcieżki strategiaZmianyŚcieżki =
                mójGson.fromJson(json.get("zmiana"), StrategiaZmianyŚcieżki.class);

        int[] produtywność = new int[5];
        JsonObject jProduktywność = json.get("produktywnosc").getAsJsonObject();
        produtywność[0] = jProduktywność.get("diamenty").getAsInt();
        produtywność[1] = jProduktywność.get("jedzenie").getAsInt();
        produtywność[2] = jProduktywność.get("ubrania").getAsInt();
        produtywność[3] = jProduktywność.get("narzedzia").getAsInt();
        produtywność[4] = jProduktywność.get("programy").getAsInt();

        JsonObject zasoby = json.get("zasoby").getAsJsonObject();
        double diamenty = zasoby.get("diamenty").getAsInt();

        ArrayList<TreeMap<Produkt, Integer>> produkty = wczytaj(zasoby);

        return new Robotnik(id, diamenty, zawód, produtywność, strategiaKupna, strategiaNauki, strategiaProdukcji,
                strategiaZmianyŚcieżki, produkty);
    }
}
