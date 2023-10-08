package main.Agenci.Robotnicy;

import main.Agenci.Agent;
import main.Agenci.Robotnicy.StrategieKupna.StrategiaKupna;
import main.Agenci.Robotnicy.StrategieNauki.StrategiaNauki;
import main.Agenci.Robotnicy.StrategieProdukcji.StrategiaProdukcji;
import main.Agenci.Robotnicy.StrategieZmianyŚcieżki.StrategiaZmianyŚcieżki;
import main.Agenci.Robotnicy.Zawody.Zawód;
import main.Agenci.Robotnicy.Zawody.ZbiórZawodów;
import main.Komparatory.KomparatorProduktów;
import main.Oferty.OfertaRobotnika;
import main.Produkty.*;

import java.util.ArrayList;
import java.util.TreeMap;

import static java.lang.Math.min;

public class Robotnik extends Agent {
    private final ZbiórZawodów zawody;
    private Zawód obecnyZawód;
    private final int[] produktywność;
    private int dniBezJedzenia;
    private final StrategiaKupna strategiaKupna;
    private final StrategiaNauki strategiaNauki;
    private final StrategiaProdukcji strategiaProdukcji;
    private final StrategiaZmianyŚcieżki strategiaZmianyŚcieżki;
    private int dzisiejszaProdukcja;
    private boolean czyNauka;

    public Robotnik(int id, double diamenty, Zawód obecnyZawód,
                    int[] produktywność, StrategiaKupna strategiaKupna, StrategiaNauki strategiaNauki,
                    StrategiaProdukcji strategiaProdukcji, StrategiaZmianyŚcieżki strategiaZmianyŚcieżki,
                    ArrayList<TreeMap<Produkt, Integer>> produkty) {

        super(id, diamenty, produkty);

        this.obecnyZawód = obecnyZawód;
        this.produktywność = produktywność;
        this.strategiaKupna = strategiaKupna;
        this.strategiaNauki = strategiaNauki;
        this.strategiaProdukcji = strategiaProdukcji;
        this.strategiaZmianyŚcieżki = strategiaZmianyŚcieżki;
        this.zawody = new ZbiórZawodów(obecnyZawód, this);
        strategiaKupna.setRobotnik(this);
        strategiaNauki.setRobotnik(this);
        strategiaProdukcji.setRobotnik(this);
        strategiaZmianyŚcieżki.setRobotnik(this);
    }

    private double bonusNarzędzi() {
        int sumaPoziomów = 0;
        TreeMap<Produkt, Integer> narzędzia = getProdukty(2);
        for(Produkt narzędzie: narzędzia.keySet()) {
            sumaPoziomów += narzędzie.getPoziom() * narzędzia.get(narzędzie);
        }
        return (double) sumaPoziomów / 100;
    }

    private double karaZaGłód() {
        switch (dniBezJedzenia) {
            case 0:
                return 0;
            case 1:
                return -1;
            case 2:
                return -3;
            default:
                return -10000;
        }
    }

    private double karaZaUbrania() {
        int liczbaUbrań = 0;
        TreeMap<Produkt, Integer> ubrania = getProdukty(1);
        for (Integer ile: ubrania.values()) {
            liczbaUbrań += ile;
            if(liczbaUbrań >= 100) {
                return 0;
            }
        }
        return (double) getGiełda().getKaraZaBrakUbrań() / 100;
    }

    public int getDzisiejszaProdukcja() {
        return dzisiejszaProdukcja;
    }

    public int getPodstawowaProduktywność(int id){
        return produktywność[id+1];
    }

    public int getProduktywność(int id) {
        return (int) (produktywność[id + 1] *
                (obecnyZawód.premiaTypu(id) + bonusNarzędzi() + karaZaGłód() - karaZaUbrania()));
    }

    public Zawód getObecnyZawód() {
        return obecnyZawód;
    }

    public boolean isCzyNauka() {
        return czyNauka;
    }

    public StrategiaKupna getStrategiaKupna() {
        return strategiaKupna;
    }

    public StrategiaNauki getStrategiaNauki() {
        return strategiaNauki;
    }

    public StrategiaProdukcji getStrategiaProdukcji() {
        return strategiaProdukcji;
    }

    public StrategiaZmianyŚcieżki getStrategiaZmianyŚcieżki() {
        return strategiaZmianyŚcieżki;
    }

    public void zróbDzień() {
        if (strategiaNauki.czyNauka()) {
            czyNauka = true;
            uczSię();
        } else {
            czyNauka = false;
            pracuj();
            strategiaKupna.kupuj();
        }
    }

    private void uczSię() {
        int nowyZawód = strategiaZmianyŚcieżki.zmianaŚcieżki();
        if (nowyZawód == -1 || nowyZawód == obecnyZawód.dajID()) {
            obecnyZawód.zwiększPoziom();
        } else {
            obecnyZawód = zawody.getZawód(nowyZawód);
        }
    }

    public void produkujZProgramami (int id, int ile) {
        TreeMap<Produkt, Integer> programy = getProdukty(3);
        for(Produkt program: programy.keySet()){
            int ileProgramów = programy.get(program);
            int ileProdukujemy = min(ileProgramów, ile);
            OfertaRobotnika oferta = new OfertaRobotnika
                    (Produkt.dajProdukt(id,program.getPoziom()), ileProdukujemy, this);
            getGiełda().dodajSprzedażRobotnika(oferta);
            ile -= ileProdukujemy;
            dodajProdukty(program, -ileProdukujemy, false);
            if (ile == 0) {
                return;
            }
        }
        OfertaRobotnika oferta = new OfertaRobotnika
                (Produkt.dajProdukt(id, obecnyZawód.dajPoziomProduktu(id)), ile, this);
        getGiełda().dodajSprzedażRobotnika(oferta);
    }

    public void pracuj() {
        int idProdukcji = strategiaProdukcji.typProdukcji();
        int liczbaProdukcji = getProduktywność(idProdukcji);
        dzisiejszaProdukcja = liczbaProdukcji;
        if (idProdukcji == -1) {
            dodajDiamenty(liczbaProdukcji);
        } else if (!Produkt.czyProgramy(idProdukcji)) {
            OfertaRobotnika oferta = new OfertaRobotnika
                    (Produkt.dajProdukt(idProdukcji, obecnyZawód.dajPoziomProduktu(idProdukcji)),
                            liczbaProdukcji, this);
            getGiełda().dodajSprzedażRobotnika(oferta);
        } else {
            produkujZProgramami(idProdukcji, liczbaProdukcji);
        }
        getProdukty(2).clear();
    }

    public boolean jedz() {
        int ileJedzenia = getProdukty(new Jedzenie());
        if (ileJedzenia >= 100) {
            dniBezJedzenia = 0;
            dodajProdukty(new Jedzenie(), -100, false);
            return true;
        } else {
            dniBezJedzenia++;
            dodajProdukty(new Jedzenie(), -ileJedzenia, false);
            return dniBezJedzenia < 3;
        }
    }

    public void ubierzSię() {
        int doUbrania = 100;
        TreeMap<Produkt, Integer> ubrania = getProdukty(1);
        ArrayList<Produkt> doUsunięcia = new ArrayList<>();
        TreeMap<Produkt, Integer> doDodania = new TreeMap<>(new KomparatorProduktów());
        for (Produkt ubranie: ubrania.keySet()) {
            int ileUbrań = ubrania.get(ubranie);
            int ileUbiorę = min(ileUbrań, doUbrania);
            doUbrania -= ileUbiorę;
            dodajProdukty(ubranie, -ileUbiorę, false);
            if (ubrania.get(ubranie) == 0) {
                doUsunięcia.add(ubranie);
            }
            int nowaWytrzymałość = ubranie.getWytrzymałość() - 1;
            if(nowaWytrzymałość > 0) {
                doDodania.put(new Ubranie(ubranie.getPoziom(), nowaWytrzymałość), ileUbiorę);
            }
            if (doUbrania == 0) {
                break;
            }
        }
        for (Produkt ubranie: doUsunięcia) {
            ubrania.remove(ubranie);
        }
        for (Produkt ubranie: doDodania.keySet()) {
            dodajProdukty(ubranie, doDodania.get(ubranie), true);
        }
    }

}
