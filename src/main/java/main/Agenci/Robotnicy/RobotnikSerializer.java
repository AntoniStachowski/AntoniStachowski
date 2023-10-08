package main.Agenci.Robotnicy;

import com.google.gson.*;

import java.lang.reflect.Type;

import static main.Agenci.ProduktySerializerHelp.ogarnij;
import static main.Main.mójGson;

public class RobotnikSerializer implements JsonSerializer<Robotnik> {

    @Override
    public JsonElement serialize(Robotnik src, Type typeOfSrc, JsonSerializationContext context) {

        JsonObject robotnik = new JsonObject();
        robotnik.addProperty("id", src.getId());
        robotnik.addProperty("poziom", src.getObecnyZawód().getPoziom());

        robotnik.add("kariera", JsonParser.parseString(mójGson.toJson(src.getObecnyZawód())));
        robotnik.add("kupowanie", JsonParser.parseString(mójGson.toJson(src.getStrategiaKupna())));
        robotnik.add("produkcja", JsonParser.parseString(mójGson.toJson(src.getStrategiaProdukcji())));
        robotnik.add("uczenie", JsonParser.parseString(mójGson.toJson(src.getStrategiaNauki())));
        robotnik.add("zmiana", JsonParser.parseString(mójGson.toJson(src.getStrategiaZmianyŚcieżki())));

        JsonObject produktywność = new JsonObject();
        produktywność.addProperty("diamenty", src.getPodstawowaProduktywność(-1));
        produktywność.addProperty("jedzenie", src.getPodstawowaProduktywność(0));
        produktywność.addProperty("ubrania", src.getPodstawowaProduktywność(1));
        produktywność.addProperty("narzedzia", src.getPodstawowaProduktywność(2));
        produktywność.addProperty("programy", src.getPodstawowaProduktywność(3));

        robotnik.add("produktywnosc", produktywność);
        JsonObject zasoby = ogarnij(src.getProdukty());
        zasoby.addProperty("diamenty", src.getDiamenty());

        robotnik.add("zasoby", zasoby);

        return robotnik;
    }
}
