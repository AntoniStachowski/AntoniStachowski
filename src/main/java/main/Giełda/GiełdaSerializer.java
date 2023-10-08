package main.Giełda;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

import static main.Giełda.GiełdaSerializerHelper.*;

public class GiełdaSerializer implements JsonSerializer<Giełda> {
    @Override
    public JsonElement serialize(Giełda src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject giełda = new JsonObject();
        giełda.addProperty("dzien", src.getDzień() - 1);
        giełda.add("ceny_max", maksymalneCeny(src));
        giełda.add("ceny_min", minimalneCeny(src));
        giełda.add("ceny_srednie", średnieCeny(src));
        return giełda;
    }
}
