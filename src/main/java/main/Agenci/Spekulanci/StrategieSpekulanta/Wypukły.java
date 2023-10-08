package main.Agenci.Spekulanci.StrategieSpekulanta;

import main.Agenci.Spekulanci.Spekulant;
import main.Giełda.Giełda;
import main.Oferty.OfertaSpekulanta;
import main.Produkty.Produkt;

public class Wypukły extends StrategiaSpekulanta{

    @Override
    public String dajNazwę() {
        return "wypukly";
    }

    @Override
    public void handluj() {
        Spekulant spekulant = getSpekulant();
        Giełda giełda = spekulant.getGiełda();
        int dzień = giełda.getDzień();
        double[] średnie = new double[3];

        if(dzień == 1) {
            return;
        }

        for (int i = 0; i < 4; i++) {
            for (int j = 1; j <= dzień; j++) {
                Produkt produkt = Produkt.dajProdukt(i,j);
                for (int k = 0; k < 3; k++) {
                    średnie[k] = giełda.getŚredniaProduktuZDnia(produkt,k);
                }
                if (średnie[0] + średnie[2] > 2 * średnie[1]) {
                    Integer ile = spekulant.getProdukty(produkt);
                    if (ile != null) {
                        OfertaSpekulanta oferta = new OfertaSpekulanta
                                (produkt, ile, 1.1 * średnie[0], spekulant);
                        giełda.dodajSprzedażSpekulanta(oferta);
                    }
                }
                if (średnie[0] + średnie[2] < 2 * średnie[1]) {
                    OfertaSpekulanta oferta = new OfertaSpekulanta
                            (produkt, 100, 0.9 * średnie[0], spekulant);
                    giełda.dodajKupnoSpekulanta(oferta);
                }
            }
        }
    }
}
