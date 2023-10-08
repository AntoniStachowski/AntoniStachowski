package main.Giełda;

import main.Giełda.StrategieGiełdy.StrategiaGiełdy;
import com.google.gson.*;

import java.lang.reflect.Type;

import static main.Main.mójGson;

public class GiełdaDeserializer implements JsonDeserializer<Giełda> {
    @Override
    public Giełda deserialize(JsonElement jsonElement, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject json = jsonElement.getAsJsonObject();
        int karaZaBrakUbrań = json.get("kara_za_brak_ubran").getAsInt();
        int długość = json.get("dlugosc").getAsInt();
        StrategiaGiełdy strategiaGiełdy = mójGson.fromJson(json.get("gielda"), StrategiaGiełdy.class);
        TransakcjeDnia dzieńZero = mójGson.fromJson(json.get("ceny"), TransakcjeDnia.class);

        return new Giełda(strategiaGiełdy, karaZaBrakUbrań, długość, dzieńZero);
    }
}
