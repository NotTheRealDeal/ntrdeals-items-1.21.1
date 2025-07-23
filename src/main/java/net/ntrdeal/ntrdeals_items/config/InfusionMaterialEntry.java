package net.ntrdeal.ntrdeals_items.config;

import com.google.gson.JsonObject;
import net.minecraft.server.ServerConfigEntry;

public class InfusionMaterialEntry extends ServerConfigEntry<String> {
    public final String key;


    public InfusionMaterialEntry(String key) {
        super(key);
        this.key = key;
    }

    public InfusionMaterialEntry(JsonObject json){
        super(getMaterialFromJson(json));
        this.key = getMaterialFromJson(json);
    }

    private static String getMaterialFromJson(JsonObject json){
        return json.get("material").getAsString();
    }

    @Override
    protected void write(JsonObject json) {
        json.addProperty("material", key);
    }
}
