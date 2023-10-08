package main.Agenci;

import main.Giełda.Giełda;
import main.Produkty.*;

import java.util.ArrayList;
import java.util.TreeMap;

public abstract class Agent {
    private int id;
    private double diamenty;
    private ArrayList<TreeMap<Produkt, Integer>> produkty;
    private Giełda giełda;

    protected Agent (int id, double diamenty, ArrayList<TreeMap<Produkt, Integer>> produkty ) {
        this.id = id;
        this.diamenty = diamenty;
        this.produkty = produkty;
    }

    public int getId() {
        return id;
    }

    public Giełda getGiełda() {
        return giełda;
    }

    public void setGiełda(Giełda giełda) {
        this.giełda = giełda;
    }

    public TreeMap<Produkt, Integer> getProdukty(int id) {
        return produkty.get(id);
    }

    public ArrayList<TreeMap<Produkt, Integer>> getProdukty() {
        return produkty;
    }

    public Integer getProdukty(Produkt produkt) {
        return produkty.get(produkt.dajID()).get(produkt);
    }

    public double getDiamenty() {
        return diamenty;
    }

    public void dodajDiamenty(double x) {
        diamenty += x;
    }

    public void dodajProdukty(Produkt produkt, int liczba, boolean czyUsuwać) {
        TreeMap<Produkt, Integer> produkty = getProdukty(produkt.dajID());
        if (produkty.get(produkt) == null) {
            produkty.put(produkt, liczba);
        } else {
            int nowaLiczba = produkty.get(produkt) + liczba;
            if (nowaLiczba <= 0 && czyUsuwać) {
                produkty.remove(produkt);
            } else {
                produkty.put(produkt, nowaLiczba);
            }
        }
    }
}
