package main.Agenci.Spekulanci.StrategieSpekulanta;

import main.Agenci.Spekulanci.Spekulant;
import main.Giełda.Giełda;
import main.Giełda.TransakcjeProduktu;
import main.Oferty.OfertaSpekulanta;
import main.Produkty.Produkt;

public class RegulującyRynek extends StrategiaSpekulanta {

    @Override
    public String dajNazwę() {
        return "regulujacy_rynek";
    }

    @Override
    public void handluj() {
        Spekulant spekulant = getSpekulant();
        Giełda giełda = spekulant.getGiełda();
        int dzień = giełda.getDzień();

        if(dzień == 1) {
            return;
        }

        for (int i = 0; i < 4; i++) {
            for (int j = 1; j <= dzień; j++) {
                Produkt produkt = Produkt.dajProdukt(i,j);
                TransakcjeProduktu transakcjeWczoraj = giełda.getTransakcjeProduktu(produkt,0);
                double średnia;
                if(! (transakcjeWczoraj.getIleSprzedano()==0)) {
                    średnia = transakcjeWczoraj.getSumaCen() / transakcjeWczoraj.getIleSprzedano();
                } else {
                    średnia = giełda.getDzieńZero().getTransakcjeTypu(i).getSumaCen();
                }
                double mnożnik = (double) giełda.getDziś().getIleWystawiliRobotnicy(produkt) /
                        Math.max(1, giełda.getTransakcjeDnia(0).getIleWystawiliRobotnicy(produkt));
                double cena = średnia * mnożnik;
                OfertaSpekulanta ofertaKupna = new OfertaSpekulanta(produkt, 100, 0.9 * cena, spekulant);
                giełda.dodajKupnoSpekulanta(ofertaKupna);
                Integer ile = spekulant.getProdukty(produkt);
                if (ile != null) {
                    OfertaSpekulanta ofertaSprzeaży = new OfertaSpekulanta(produkt, ile, 1.1 * cena, spekulant);
                    giełda.dodajSprzedażSpekulanta(ofertaSprzeaży);
                }
            }
        }

    }
}
