package main;

import com.google.gson.JsonArray;
import main.Agenci.Robotnicy.Robotnik;
import main.Agenci.Robotnicy.RobotnikDeserializer;
import main.Agenci.Robotnicy.RobotnikSerializer;
import main.Agenci.Robotnicy.StrategieKupna.StrategiaKupna;
import main.Agenci.Robotnicy.StrategieKupna.StrategiaKupnaDeserializer;
import main.Agenci.Robotnicy.StrategieKupna.StrategiaKupnaSerializer;
import main.Agenci.Robotnicy.StrategieNauki.StrategiaNauki;
import main.Agenci.Robotnicy.StrategieNauki.StrategiaNaukiDeserializer;
import main.Agenci.Robotnicy.StrategieNauki.StrategiaNaukiSerializer;
import main.Agenci.Robotnicy.StrategieProdukcji.StrategiaProdukcji;
import main.Agenci.Robotnicy.StrategieProdukcji.StrategiaProdukcjiDeserializer;
import main.Agenci.Robotnicy.StrategieProdukcji.StrategiaProdukcjiSerializer;
import main.Agenci.Robotnicy.StrategieZmianyŚcieżki.StrategiaZmianyŚcieżki;
import main.Agenci.Robotnicy.StrategieZmianyŚcieżki.StrategiaZmianyŚcieżkiDeserializer;
import main.Agenci.Robotnicy.StrategieZmianyŚcieżki.StrategiaZmianyŚcieżkiSerializer;
import main.Agenci.Robotnicy.Zawody.Zawód;
import main.Agenci.Robotnicy.Zawody.ZawódDeserializer;
import main.Agenci.Robotnicy.Zawody.ZawódSerializer;
import main.Agenci.Spekulanci.Spekulant;
import main.Agenci.Spekulanci.SpekulantDeserializer;
import main.Agenci.Spekulanci.SpekulantSerializer;
import main.Agenci.Spekulanci.StrategieSpekulanta.StrategiaSpekulanta;
import main.Agenci.Spekulanci.StrategieSpekulanta.StrategiaSpekulantaDeserializer;
import main.Agenci.Spekulanci.StrategieSpekulanta.StrategiaSpekulantaSerializer;
import main.Giełda.*;
import main.Giełda.StrategieGiełdy.StrategiaGiełdy;
import main.Giełda.StrategieGiełdy.StrategiaGiełdyDeserializer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class Main {
    public static Gson mójGson;

    private static void inicjalizujGson() {
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        builder.registerTypeHierarchyAdapter(Giełda.class, new GiełdaDeserializer());
        builder.registerTypeHierarchyAdapter(Giełda.class, new GiełdaSerializer());

        builder.registerTypeHierarchyAdapter(Robotnik.class, new RobotnikDeserializer());
        builder.registerTypeHierarchyAdapter(Robotnik.class, new RobotnikSerializer());

        builder.registerTypeHierarchyAdapter(Spekulant.class, new SpekulantDeserializer());
        builder.registerTypeHierarchyAdapter(Spekulant.class, new SpekulantSerializer());

        builder.registerTypeHierarchyAdapter(TransakcjeDnia.class, new TransakcjeDniaDeserializer());

        builder.registerTypeHierarchyAdapter(StrategiaGiełdy.class, new StrategiaGiełdyDeserializer());

        builder.registerTypeHierarchyAdapter(StrategiaSpekulanta.class, new StrategiaSpekulantaDeserializer());
        builder.registerTypeHierarchyAdapter(StrategiaSpekulanta.class, new StrategiaSpekulantaSerializer());

        builder.registerTypeHierarchyAdapter(StrategiaKupna.class, new StrategiaKupnaDeserializer());
        builder.registerTypeHierarchyAdapter(StrategiaKupna.class, new StrategiaKupnaSerializer());

        builder.registerTypeHierarchyAdapter(StrategiaNauki.class, new StrategiaNaukiDeserializer());
        builder.registerTypeHierarchyAdapter(StrategiaNauki.class, new StrategiaNaukiSerializer());

        builder.registerTypeHierarchyAdapter(StrategiaProdukcji.class, new StrategiaProdukcjiDeserializer());
        builder.registerTypeHierarchyAdapter(StrategiaProdukcji.class, new StrategiaProdukcjiSerializer());

        builder.registerTypeHierarchyAdapter(StrategiaZmianyŚcieżki.class, new StrategiaZmianyŚcieżkiDeserializer());
        builder.registerTypeHierarchyAdapter(StrategiaZmianyŚcieżki.class, new StrategiaZmianyŚcieżkiSerializer());

        builder.registerTypeHierarchyAdapter(Zawód.class, new ZawódDeserializer());
        builder.registerTypeHierarchyAdapter(Zawód.class, new ZawódSerializer());

        //builder.enableComplexMapKeySerialization();

        mójGson = builder.create();
    }

    public static void main(String[] args) throws FileNotFoundException {

        JsonArray tablicaOutputów = new JsonArray();
        File input = new File(args[0]);
        inicjalizujGson();
        JsonReader reader = new JsonReader(new FileReader(input));
        Symulacja symulacja = mójGson.fromJson(reader, Symulacja.class);
        symulacja.symuluj(tablicaOutputów);

        try(PrintWriter printWriter = new PrintWriter(args[1], StandardCharsets.UTF_8)) {
            printWriter.println(mójGson.toJson(tablicaOutputów));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
