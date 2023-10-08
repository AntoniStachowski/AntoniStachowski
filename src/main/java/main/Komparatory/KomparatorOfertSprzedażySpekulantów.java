package main.Komparatory;

import main.Oferty.OfertaSpekulanta;

import java.util.Comparator;

public class KomparatorOfertSprzedażySpekulantów implements Comparator<OfertaSpekulanta> {
    @Override
    public int compare(OfertaSpekulanta o1, OfertaSpekulanta o2) {
        int porównanieProduktów = new KomparatorProduktów().compare(o1.getProdukt(), o2.getProdukt());
        if(porównanieProduktów == 0) {
            return (int) (o2.getCena() - o1.getCena());
        }
        return - porównanieProduktów;
    }
}
