package main.Agenci.Spekulanci;

import main.Agenci.Agent;
import main.Agenci.Spekulanci.StrategieSpekulanta.StrategiaSpekulanta;
import main.Produkty.Produkt;

import java.util.ArrayList;
import java.util.TreeMap;

public class Spekulant extends Agent {
    private StrategiaSpekulanta strategiaSpekulanta;

    public Spekulant(int id, double diamenty, ArrayList<TreeMap<Produkt, Integer>> produkty,
                     StrategiaSpekulanta strategiaSpekulanta ) {
        super(id, diamenty, produkty);
        this.strategiaSpekulanta = strategiaSpekulanta;
        strategiaSpekulanta.setSpekulant(this);
    }

    public StrategiaSpekulanta getStrategiaSpekulanta() {
        return strategiaSpekulanta;
    }

    public void handluj() {
        strategiaSpekulanta.handluj();
    }
}
