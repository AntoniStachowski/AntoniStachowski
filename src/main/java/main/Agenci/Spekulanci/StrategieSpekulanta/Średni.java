package main.Agenci.Spekulanci.StrategieSpekulanta;

import main.Agenci.Spekulanci.Spekulant;
import main.Giełda.Giełda;
import main.Oferty.OfertaSpekulanta;
import main.Produkty.Produkt;
import com.google.gson.annotations.SerializedName;

import static java.lang.Math.min;

public class Średni extends StrategiaSpekulanta  {
    @SerializedName("historia_spekulanta_sredniego")
    int historia;

    public Średni(int historia){
        super();
        this.historia = historia;
    }

    @Override
    public String dajNazwę() {
        return "sredni";
    }

    @Override
    public void handluj() {
        Spekulant spekulant = getSpekulant();
        Giełda giełda = spekulant.getGiełda();
        int dzień = giełda.getDzień();

        for (int i = 0; i < 4; i++) {
            for (int j = 1; j <= dzień; j++) {
                Produkt produkt = Produkt.dajProdukt(i, j);
                double sumaŚrednich = 0;

                for (int k = 0; k < min(dzień, historia); k++) {
                    sumaŚrednich += giełda.getŚredniaProduktuZDnia(produkt, k);
                }

                double średnia = sumaŚrednich/min(dzień, historia);
                if (spekulant.getProdukty(produkt) == null) {
                    OfertaSpekulanta oferta = new OfertaSpekulanta(produkt, 100, średnia * 0.95, spekulant);
                    giełda.dodajKupnoSpekulanta(oferta);
                } else {
                    OfertaSpekulanta ofertaKupna = new OfertaSpekulanta
                            (produkt, 100, średnia * 0.9, spekulant);
                    OfertaSpekulanta ofertaSprzedaży = new OfertaSpekulanta
                            (produkt, spekulant.getProdukty(produkt), średnia * 1.1, spekulant);
                    giełda.dodajKupnoSpekulanta(ofertaKupna);
                    giełda.dodajSprzedażSpekulanta(ofertaSprzedaży);
                }
            }
        }

    }
}
