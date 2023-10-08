package main.Komparatory;

import main.Oferty.OfertaSpekulanta;

import java.util.Comparator;

public class KomparatorOfertKupnaSpekulantów implements Comparator<OfertaSpekulanta> {
    @Override
    public int compare(OfertaSpekulanta o1, OfertaSpekulanta o2) {
        int porównanieProduktów = new KomparatorProduktów().compare(o1.getProdukt(), o2.getProdukt());
        if(porównanieProduktów == 0) {
            return (int) Math.signum(o1.getCena() - o2.getCena());
        }
        return porównanieProduktów;
    }
}
