package main.Komparatory;

import main.Produkty.Produkt;

import java.util.Comparator;

public class KomparatorProduktów implements Comparator<Produkt> {
    @Override
    public int compare(Produkt o1, Produkt o2) {
        if(o1.dajID() == o2.dajID()) {
            if(o1.getWytrzymałość() == o2.getWytrzymałość()) {
                return o2.getPoziom() - o1.getPoziom();
            } else {
                return o2.getWytrzymałość() - o1.getWytrzymałość();
            }
        } else {
            return o1.dajID() - o2.dajID();
        }
    }
}
