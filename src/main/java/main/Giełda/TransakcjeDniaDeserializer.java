package main.Giełda;

import main.Produkty.Jedzenie;
import main.Produkty.Narzędzie;
import main.Produkty.ProgramKomputerowy;
import main.Produkty.Ubranie;
import com.google.gson.*;

import java.lang.reflect.Type;

public class TransakcjeDniaDeserializer implements JsonDeserializer<TransakcjeDnia> {
    @Override
    public TransakcjeDnia deserialize(JsonElement jsonElement, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject json = jsonElement.getAsJsonObject();
        TransakcjeDnia transakcje = new TransakcjeDnia();

        double cenaJedzenia = json.get("jedzenie").getAsDouble();
        transakcje.sprzedano(new Jedzenie(), 1, cenaJedzenia);

        double cenaUbrań = json.get("ubrania").getAsDouble();
        transakcje.sprzedano(new Ubranie(0), 1, cenaUbrań);

        double cenaNarzędzi = json.get("narzedzia").getAsDouble();
        transakcje.sprzedano(new Narzędzie(0), 1, cenaNarzędzi);

        double cenaProgramów = json.get("programy").getAsDouble();
        transakcje.sprzedano(new ProgramKomputerowy(0), 1,cenaProgramów);

        return transakcje;
    }
}
