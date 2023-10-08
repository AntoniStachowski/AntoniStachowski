package main.Agenci.Robotnicy.Zawody;

import main.Agenci.Robotnicy.Robotnik;

public class ZbiórZawodów {
    Zawód[] zawody;

    public ZbiórZawodów(Zawód zawódPoczątkowy, Robotnik robotnik) {
        zawody = new Zawód[]{new Górnik(),
                new Rolnik(),
                new Inżynier(),
                new Programista(),
                new Rzemieślinik()};
        zawody[zawódPoczątkowy.dajID() + 1] = zawódPoczątkowy;
    }

    public Zawód getZawód (int id) {
        return zawody[id+1];
    }
}
