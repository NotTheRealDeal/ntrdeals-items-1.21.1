package net.ntrdeal.ntrdeals_items.config;

import com.google.gson.JsonObject;
import net.minecraft.server.ServerConfigEntry;
import net.minecraft.server.ServerConfigList;

import java.io.File;

public class InfusionList extends ServerConfigList<String, InfusionEntry> {

    public InfusionList(File file) {super(file);}

    @Override
    protected ServerConfigEntry<String> fromJson(JsonObject json) {return new InfusionEntry(json);}
}
