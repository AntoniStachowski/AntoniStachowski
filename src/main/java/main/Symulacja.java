package main;

import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import main.Agenci.Robotnicy.Robotnik;
import main.Agenci.Spekulanci.Spekulant;
import main.Giełda.Giełda;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Random;

import static main.Main.mójGson;

public class Symulacja {
    public static Random losowanie = new Random();

    @SerializedName("info")
    private final Giełda giełda;
    private final ArrayList<Robotnik> robotnicy;
    private final ArrayList<Spekulant> spekulanci;

    public Symulacja(Giełda giełda, ArrayList<Robotnik> robotnicy, ArrayList<Spekulant> spekulanci) {
        this.giełda = giełda;
        this.robotnicy = robotnicy;
        this.spekulanci = spekulanci;
    }

    public void przekażGiełdę() {
        for(Robotnik robotnik: robotnicy) {
            robotnik.setGiełda(giełda);
        }
        for(Spekulant spekulant: spekulanci) {
            spekulant.setGiełda(giełda);
        }
    }

    public Giełda getGiełda() {
        return giełda;
    }

    public void symuluj(JsonArray tablicaOutputów) {
        przekażGiełdę();
        ArrayList<Robotnik> martwi = new ArrayList<>();
        for(int i = 0; i < giełda.getDługość(); i++) {
            for (Robotnik robotnik: robotnicy) {
                robotnik.zróbDzień();
            }

            for (Spekulant spekulant: spekulanci) {
                spekulant.handluj();
            }

            giełda.rozpatrzDzień();

            for (Robotnik robotnik: robotnicy) {
                if (!robotnik.isCzyNauka()) {
                    robotnik.ubierzSię();

                    if (!robotnik.jedz()) {
                        martwi.add(robotnik);
                    }
                }
            }

            for (Robotnik martwy: martwi) {
                robotnicy.remove(martwy);
            }
            martwi.clear();

            tablicaOutputów.add(JsonParser.parseString(mójGson.toJson(this)));
        }
    }
}
