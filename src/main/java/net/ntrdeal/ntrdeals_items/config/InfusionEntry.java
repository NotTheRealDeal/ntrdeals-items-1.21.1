package net.ntrdeal.ntrdeals_items.config;

import com.google.gson.JsonObject;
import net.minecraft.server.ServerConfigEntry;

import java.util.Map;

public class InfusionEntry extends ServerConfigEntry<String> {
    public final String key;
    public final Map<String, Double> map;

    public InfusionEntry(String key, Map<String, Double> map) {
        super(key);
        this.key = key;
        this.map = map;
    }

    public InfusionEntry(JsonObject json) {
        super(getItemFromJson(json));
        this.key = getItemFromJson(json);
        this.map = getMapFromJson(json.getAsJsonObject("attributes"));
    }

    private static String getItemFromJson(JsonObject json){
        return json.get("item").getAsString();
    }

    private static Map<String, Double> getMapFromJson(JsonObject json){
        Map<String, Double> mapFromJson = new java.util.HashMap<>(Map.of());
        for (String index : json.keySet()){
            mapFromJson.put(index, json.get(index).getAsDouble());
        }
        return mapFromJson;
    }

    @Override
    protected void write(JsonObject json) {
        json.addProperty("item", key);
        JsonObject attributes = new JsonObject();
        for (String attribute : map.keySet()){
            attributes.addProperty(attribute, map.get(attribute));
        }
        json.add("attributes", attributes);
    }
}
