package main.Giełda;

import main.Komparatory.KomparatorProduktów;
import main.Oferty.Oferta;
import main.Produkty.Produkt;

import java.util.ArrayList;
import java.util.TreeMap;

public class TransakcjeDnia {
    ArrayList<TreeMap<Produkt, TransakcjeProduktu>> transakcjeProduktu;
    ArrayList<TransakcjeProduktu> transakcjeTypu;

    public TransakcjeDnia() {
        transakcjeProduktu = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            transakcjeProduktu.add(new TreeMap<Produkt, TransakcjeProduktu>(new KomparatorProduktów()));
        }
        transakcjeTypu = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            transakcjeTypu.add(new TransakcjeProduktu());
        }
    }

    public TransakcjeProduktu getTransakcjeProduktu(Produkt produkt) {
        return transakcjeProduktu.get(produkt.dajID()).get(produkt);
    }

    public int getIleWystawionoTypu(int id) {
        return transakcjeTypu.get(id).getIleWystawiono();
    }

    public int getIleWystawiliRobotnicy(Produkt produkt) {
        if (getTransakcjeProduktu(produkt)!=null) {
            return getTransakcjeProduktu(produkt).getIleWystawiliRobotnicy();
        }
        return 0;
    }

    public TransakcjeProduktu getTransakcjeTypu(int id) {
        return transakcjeTypu.get(id);
    }

    public void zarejstrujOfertę(Oferta oferta, boolean czyRobotnik) {
        Produkt produkt = oferta.getProdukt();
        transakcjeProduktu.get(produkt.dajID()).putIfAbsent(produkt, new TransakcjeProduktu());
        transakcjeProduktu.get(produkt.dajID()).get(produkt).zarejstrujOfertę(oferta, czyRobotnik);
        transakcjeTypu.get(produkt.dajID()).zarejstrujOfertę(oferta, czyRobotnik);
    }

    public void sprzedano (Produkt produkt, int ile, double cena) {
        transakcjeProduktu.get(produkt.dajID()).putIfAbsent(produkt, new TransakcjeProduktu());
        transakcjeProduktu.get(produkt.dajID()).get(produkt).sprzedano(ile, cena);
        transakcjeTypu.get(produkt.dajID()).sprzedano(ile, cena);
    }
}
