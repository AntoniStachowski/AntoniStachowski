package main.Giełda;

import main.Oferty.Oferta;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class TransakcjeProduktu {
    private int ileWystawiliRobotnicy;
    private int ileWystawiono;
    private int ileSprzedano;
    private double sumaCen;
    private double minimalnaCena;
    private double maksymalnaCena;

    @Override
    public String toString() {
        return "TransakcjeProduktu{" +
                "ileWystawiliRobotnicy=" + ileWystawiliRobotnicy +
                ", ileWystawiono=" + ileWystawiono +
                ", ileSprzedano=" + ileSprzedano +
                ", sumaCen=" + sumaCen +
                ", minimalnaCena=" + minimalnaCena +
                '}';
    }

    public TransakcjeProduktu(){
        minimalnaCena = Double.MAX_VALUE;
        maksymalnaCena = -1;
    };

    public TransakcjeProduktu(int ileSprzedano, int ileWystawiono, int ileWystawiliRobotnicy, double sumaCen) {
        this.ileSprzedano = ileSprzedano;
        this.ileWystawiono = ileWystawiono;
        this.ileWystawiliRobotnicy = ileWystawiliRobotnicy;
        this.sumaCen = sumaCen;
        minimalnaCena = Double.MAX_VALUE;
        maksymalnaCena = -1;
    }

    public double getMaksymalnaCena() {
        return maksymalnaCena;
    }

    public double getMinimalnaCena() {
        return minimalnaCena;
    }

    public void dodajWystawienia(int x) {
        ileWystawiono += x;
    }

    public void dodajSprzedaże(int x) {
        ileSprzedano += x;
    }

    public void dodajSumęCeny(int x) {
        sumaCen += x;
    }

    public void dodjWystawieniaRobotników (int x) {
        ileWystawiliRobotnicy += x;
    }

    public int getIleWystawiono() {
        return ileWystawiono;
    }

    public double getSumaCen() {
        return sumaCen;
    }

    public int getIleSprzedano() {
        return ileSprzedano;
    }

    public int getIleWystawiliRobotnicy() {
        return ileWystawiliRobotnicy;
    }

    public void zarejstrujOfertę(Oferta oferta, boolean czyRobotnik) {
        if(czyRobotnik) {
            ileWystawiliRobotnicy += oferta.getLiczba();
        }
        ileWystawiono += oferta.getLiczba();
    }

    public void sprzedano(int ile, double cena) {
        ileSprzedano += ile;
        sumaCen += ile * cena;
        minimalnaCena = min(minimalnaCena, cena);
        maksymalnaCena = max(maksymalnaCena, cena);
    }
}
