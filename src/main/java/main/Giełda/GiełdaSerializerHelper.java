package main.Giełda;

import com.google.gson.JsonObject;

public class GiełdaSerializerHelper {
    public static JsonObject minimalneCeny(Giełda giełda){
        JsonObject ceny = new JsonObject();
        ceny.addProperty("jedzenie", giełda.getMinimalnaCena(0));
        ceny.addProperty("ubrania", giełda.getMinimalnaCena(1));
        ceny.addProperty("narzedzia", giełda.getMinimalnaCena(2));
        ceny.addProperty("programy", giełda.getMinimalnaCena(3));
        return ceny;
    }

    public static JsonObject maksymalneCeny(Giełda giełda){
        JsonObject ceny = new JsonObject();
        ceny.addProperty("jedzenie", giełda.getMaksymalnaCena(0));
        ceny.addProperty("ubrania", giełda.getMaksymalnaCena(1));
        ceny.addProperty("narzedzia", giełda.getMaksymalnaCena(2));
        ceny.addProperty("programy", giełda.getMaksymalnaCena(3));
        return ceny;
    }

    public static JsonObject średnieCeny(Giełda giełda){
        JsonObject ceny = new JsonObject();
        ceny.addProperty("jedzenie", giełda.getŚredniaCena(0));
        ceny.addProperty("ubrania", giełda.getŚredniaCena(1));
        ceny.addProperty("narzedzia", giełda.getŚredniaCena(2));
        ceny.addProperty("programy", giełda.getŚredniaCena(3));
        return ceny;
    }
}
