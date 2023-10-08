package main.Giełda;

import main.Agenci.Robotnicy.Robotnik;
import main.Giełda.StrategieGiełdy.StrategiaGiełdy;
import main.Komparatory.KomparatorOfertKupnaSpekulantów;
import main.Komparatory.KomparatorOfertSprzedażySpekulantów;
import main.Komparatory.KomparatorProduktów;
import main.Oferty.OfertaRobotnika;
import main.Oferty.OfertaSpekulanta;
import main.Produkty.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

import static java.lang.Math.max;

public class Giełda {
    private final StrategiaGiełdy strategiaGiełdy;
    private final ArrayList<TransakcjeDnia> historiaTransakcji;
    private final ArrayList<OfertaRobotnika> sprzedażeRobotnika;
    private final ArrayList<OfertaRobotnika> kupnaRobotnika;
    private final ArrayList<OfertaSpekulanta> sprzedażeSpekulanta;
    private final ArrayList<OfertaSpekulanta> kupnaSpekulanta;
    private OfertaSpekulanta[] tablicaSprzedażySpekulanta;
    private OfertaSpekulanta[] tablicaKupnaSpekulanta;
    private TransakcjeDnia dziś;
    private final int karaZaBrakUbrań;
    private final int długość;

    public Giełda(StrategiaGiełdy strategiaGiełdy, int karaZaBrakUbrań, int długość, TransakcjeDnia dzieńZero){
        this.strategiaGiełdy = strategiaGiełdy;
        this.karaZaBrakUbrań = karaZaBrakUbrań;
        this.długość = długość;
        this.historiaTransakcji = new ArrayList<>();
        this.historiaTransakcji.add(dzieńZero);
        this.sprzedażeRobotnika = new ArrayList<>();
        this.sprzedażeSpekulanta = new ArrayList<>();
        this.kupnaRobotnika = new ArrayList<>();
        this.kupnaSpekulanta = new ArrayList<>();
        this.dziś = new TransakcjeDnia();
    }

    public int getKaraZaBrakUbrań() {
        return karaZaBrakUbrań;
    }

    public int getDługość() {
        return długość;
    }

    public TransakcjeDnia getTransakcjeDnia(int i) {
        return historiaTransakcji.get(max(0, getDzień()- i - 1));
    }

    public TransakcjeProduktu getTransakcjeProduktu(Produkt produkt, int i) {
        if (getTransakcjeDnia(i).getTransakcjeProduktu(produkt) == null ) {
            return getDzieńZero().getTransakcjeTypu(produkt.dajID());
        }
        return getTransakcjeDnia(i).getTransakcjeProduktu(produkt);
    }

    public double getŚredniaProduktuZDnia(Produkt produkt, int i) {
        if (getTransakcjeDnia(i).getTransakcjeProduktu(produkt) == null ||
                getTransakcjeDnia(i).getTransakcjeProduktu(produkt).getIleSprzedano() == 0) {
            return getDzieńZero().getTransakcjeTypu(produkt.dajID()).getSumaCen();
        }
        return getTransakcjeDnia(i).getTransakcjeProduktu(produkt).getSumaCen() /
                getTransakcjeDnia(i).getTransakcjeProduktu(produkt).getIleSprzedano();
    }

    public TransakcjeDnia getDziś() {
        return dziś;
    }

    public TransakcjeDnia getDzieńZero() {
        return getTransakcjeDnia(getDzień() - 1);
    }

    public void dodajSprzedażRobotnika(OfertaRobotnika oferta) {
        sprzedażeRobotnika.add(oferta);
        dziś.zarejstrujOfertę(oferta, true);
    }

    public void dodajKupnoRobotnika(OfertaRobotnika oferta) {
        kupnaRobotnika.add(oferta);
    }

    public void dodajSprzedażSpekulanta (OfertaSpekulanta oferta) {
        sprzedażeSpekulanta.add(oferta);
        dziś.zarejstrujOfertę(oferta, false);
    }

    public void dodajKupnoSpekulanta (OfertaSpekulanta oferta) {
        kupnaSpekulanta.add(oferta);
    }

    public int getDzień() {
        return historiaTransakcji.size();
    }

    private int porównajrobotników(Robotnik robotnik1, Robotnik robotnik2) {
        OfertaRobotnika oferta1 = new OfertaRobotnika(new Jedzenie(), 0, robotnik1);
        OfertaRobotnika oferta2 = new OfertaRobotnika(new Jedzenie(), 0, robotnik2);
        return strategiaGiełdy.dajKomparator(getDzień()).compare(oferta1, oferta2);
    }

    private void dopasujSprzedażRobotnika(OfertaRobotnika oferta) {
        OfertaSpekulanta idealnaOferta = new OfertaSpekulanta(oferta.getProdukt(), 0, Double.MAX_VALUE, null);
        Comparator<Produkt> comp = new KomparatorProduktów();
        int index = Arrays.binarySearch(tablicaKupnaSpekulanta, idealnaOferta, new KomparatorOfertKupnaSpekulantów());
        if (index < 0) {
            index = -index - 2;
        }
        for(int i = index; i >= 0 &&
                comp.compare(tablicaKupnaSpekulanta[i].getProdukt(), idealnaOferta.getProdukt()) == 0 &&
                oferta.getLiczba() > 0; i--) {
            OfertaSpekulanta ofertaS = tablicaKupnaSpekulanta[i];
            int naIleStać = (int) (ofertaS.getSpekulant().getDiamenty() / ofertaS.getCena());
            int ileKupiono = Math.min(Math.min(naIleStać, oferta.getLiczba()), ofertaS.getLiczba());
            oferta.odejmij(ileKupiono);
            ofertaS.odejmij(ileKupiono);
            ofertaS.getSpekulant().dodajProdukty(oferta.getProdukt(), ileKupiono, true);
            ofertaS.getSpekulant().dodajDiamenty(- ileKupiono * ofertaS.getCena());
            oferta.getRobotnik().dodajDiamenty(ileKupiono * ofertaS.getCena());
            if (ileKupiono != 0) {
                dziś.sprzedano(oferta.getProdukt(), ileKupiono, ofertaS.getCena());
            }
        }
    }

    private void dopasujKupnoRobotnika(OfertaRobotnika oferta) {
        Produkt idealnyProdukt = Produkt.dajProdukt(oferta.getProdukt().dajID(), Integer.MAX_VALUE);
        OfertaSpekulanta idealnaOferta = new OfertaSpekulanta(idealnyProdukt, 0, 0, null);
        int index = Arrays.binarySearch(tablicaSprzedażySpekulanta,
                idealnaOferta, new KomparatorOfertSprzedażySpekulantów());
        if (index < 0) {
            index = -index - 2;
        }
        for(int i = index; i >= 0 &&
                oferta.getProdukt().dajID() == tablicaSprzedażySpekulanta[i].getProdukt().dajID() &&
                oferta.getLiczba() > 0; i--) {
            OfertaSpekulanta ofertaS = tablicaSprzedażySpekulanta[i];
            int naIleStać = (int) (oferta.getRobotnik().getDiamenty() / ofertaS.getCena());
            int ileKupiono = Math.min(Math.min(naIleStać, oferta.getLiczba()), ofertaS.getLiczba());
            oferta.odejmij(ileKupiono);
            ofertaS.odejmij(ileKupiono);
            ofertaS.getSpekulant().dodajProdukty(ofertaS.getProdukt(), -ileKupiono, true);
            oferta.getRobotnik().dodajProdukty(ofertaS.getProdukt(), ileKupiono, false);
            ofertaS.getSpekulant().dodajDiamenty(ileKupiono * ofertaS.getCena());
            oferta.getRobotnik().dodajDiamenty(- ileKupiono * ofertaS.getCena());
            if(ileKupiono != 0) {
                dziś.sprzedano(ofertaS.getProdukt(), ileKupiono, ofertaS.getCena());
            }
        }
    }

    public void skupResztę() {
        for(OfertaRobotnika oferta: sprzedażeRobotnika) {
            TransakcjeProduktu transacje = getTransakcjeProduktu(oferta.getProdukt(), 0);
            oferta.getRobotnik().dodajDiamenty(oferta.getLiczba() * transacje.getMinimalnaCena());
        }
    }

    public void rozpatrzDzień() {
        Comparator<OfertaRobotnika> comp = strategiaGiełdy.dajKomparator(getDzień());
        sprzedażeRobotnika.sort(comp);
        kupnaRobotnika.sort(comp);
        sprzedażeSpekulanta.sort(new KomparatorOfertSprzedażySpekulantów());
        kupnaSpekulanta.sort(new KomparatorOfertKupnaSpekulantów());
        tablicaSprzedażySpekulanta = sprzedażeSpekulanta.toArray(new OfertaSpekulanta[0]);
        tablicaKupnaSpekulanta = kupnaSpekulanta.toArray(new OfertaSpekulanta[0]);
        int i = 0;

        for(OfertaRobotnika oferta: kupnaRobotnika) {
            while(i < sprzedażeRobotnika.size() && comp.compare(sprzedażeRobotnika.get(i), oferta) <= 0) {
                dopasujSprzedażRobotnika(sprzedażeRobotnika.get(i));
                i++;
            }
            dopasujKupnoRobotnika(oferta);
        }

        skupResztę();

        historiaTransakcji.add(dziś);
        sprzedażeRobotnika.clear();
        sprzedażeSpekulanta.clear();
        kupnaSpekulanta.clear();
        kupnaRobotnika.clear();
        dziś = new TransakcjeDnia();

    }

    public double getMinimalnaCena(int id) {
        double cena = getTransakcjeDnia(0).getTransakcjeTypu(id).getMinimalnaCena();
        if (cena == Double.MAX_VALUE) {
            return getDzieńZero().getTransakcjeTypu(id).getSumaCen();
        }
        return cena;
    }

    public double getMaksymalnaCena(int id) {
        double cena = getTransakcjeDnia(0).getTransakcjeTypu(id).getMaksymalnaCena();
        if (cena == -1) {
            return getDzieńZero().getTransakcjeTypu(id).getSumaCen();
        }
        return cena;
    }

    public double getŚredniaCena(int id) {
        if (getTransakcjeDnia(0).getTransakcjeTypu(id).getIleSprzedano() == 0) {
            return getDzieńZero().getTransakcjeTypu(id).getSumaCen();
        }
        return getTransakcjeDnia(0).getTransakcjeTypu(id).getSumaCen()
                / getTransakcjeDnia(0).getTransakcjeTypu(id).getIleSprzedano();
    }

}
