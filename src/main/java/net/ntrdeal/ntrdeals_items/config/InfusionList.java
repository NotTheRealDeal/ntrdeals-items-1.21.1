package net.ntrdeal.ntrdeals_items.config;

import com.google.gson.JsonObject;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.item.Item;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.ServerConfigEntry;
import net.minecraft.server.ServerConfigList;

import java.io.File;
import java.util.Map;
import java.util.Objects;

public class InfusionList extends ServerConfigList<String, InfusionEntry> {

    public InfusionList(File file) {super(file);}

    public Boolean contains(Item item){
        return this.contains(item.toString());
    }

    public Boolean containsString(String string){
        return this.contains(string);
    }

    public Map<RegistryEntry<EntityAttribute>, Double> getItemMap(Item item){
        return InfusionUtil.getMap(Objects.requireNonNull(this.get(item.toString())).map);
    }

    @Override
    protected ServerConfigEntry<String> fromJson(JsonObject json) {return new InfusionEntry(json);}
}
