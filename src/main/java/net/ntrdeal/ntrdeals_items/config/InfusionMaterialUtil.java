package net.ntrdeal.ntrdeals_items.config;

import net.minecraft.item.ArmorMaterial;
import net.minecraft.registry.entry.RegistryEntry;
import net.ntrdeal.ntrdeals_items.NTRDealsItems;
import net.ntrdeal.ntrdeals_items.item.ModArmorMaterials;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class InfusionMaterialUtil {
    public static final File CONFIG = new File("config");
    public static final File NTRDEALS_CONFIG = new File("config/ntrdeals-config");
    public static final File INFUSION_MATERIAL_FILE = new File("config/ntrdeals-config/infusion-materials.json");
    public static final InfusionMaterialList INFUSION_MATERIALS = new InfusionMaterialList(INFUSION_MATERIAL_FILE);

    private static final List<RegistryEntry<ArmorMaterial>> MATERIALS = List.of(
            ModArmorMaterials.COSMOLITE,
            ModArmorMaterials.LUNARITE
    );

    public static void registerInfusionMaterialList(){
        try {
            if (CONFIG.mkdir()) NTRDealsItems.LOGGER.info("Config directory made!");
            if (NTRDEALS_CONFIG.mkdir()) NTRDealsItems.LOGGER.info("NTRDeals Config directory made!");
            if (INFUSION_MATERIAL_FILE.createNewFile()){
                for (RegistryEntry<ArmorMaterial> material : MATERIALS){
                    makeEntry(material);
                }
                NTRDealsItems.LOGGER.info("Infusion json made!");
            } load();
        } catch (IOException throwable){
            NTRDealsItems.LOGGER.warn(String.valueOf(throwable));
        }
    }

    public static boolean makeEntry(RegistryEntry<ArmorMaterial> material){
        if (getEntry(material) == null){
            INFUSION_MATERIALS.add(new InfusionMaterialEntry(material.getIdAsString()));
            load();
            return true;
        } else if (getEntry(material) instanceof InfusionMaterialEntry) {
            return false;
        } else return false;
    }

    public static boolean removeEntry(RegistryEntry<ArmorMaterial> material){
        if (getEntry(material) instanceof InfusionMaterialEntry entry){
            INFUSION_MATERIALS.remove(entry);
            load();
            return true;
        } return false;
    }

    public static boolean containsMaterial(RegistryEntry<ArmorMaterial> material){
        return getEntry(material) instanceof InfusionMaterialEntry;
    }

    public static InfusionMaterialEntry getEntry(RegistryEntry<ArmorMaterial> material){return INFUSION_MATERIALS.get(material.getIdAsString());}

    private static void load() {
        try {
            INFUSION_MATERIALS.load();
        } catch (IOException throwable) {
            NTRDealsItems.LOGGER.warn(String.valueOf(throwable));
        }
    }

    public static void reset() {
        try {
            if (INFUSION_MATERIAL_FILE.exists()){
                if (INFUSION_MATERIAL_FILE.delete()){
                    NTRDealsItems.LOGGER.info("Infusion json deleted!");
                    if (INFUSION_MATERIAL_FILE.createNewFile()){
                        load();
                        for (RegistryEntry<ArmorMaterial> material : MATERIALS){
                            makeEntry(material);
                        }
                        NTRDealsItems.LOGGER.info("Infusion json reset!");
                    }
                } load();
            }
        } catch (IOException throwable){
            NTRDealsItems.LOGGER.warn(String.valueOf(throwable));
        }
    }
}
