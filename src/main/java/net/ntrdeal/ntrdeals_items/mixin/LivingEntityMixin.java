package net.ntrdeal.ntrdeals_items.mixin;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.ntrdeal.ntrdeals_items.variables.Functions;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Map;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {
    @Shadow public abstract ItemStack getEquippedStack(EquipmentSlot slot);

    @Shadow public abstract void kill();

    @Inject(method = "onEquipStack", at = @At("HEAD"))
    private void Injected(EquipmentSlot slot, ItemStack oldStack, ItemStack newStack, CallbackInfo ci){
        if (Functions.containsLapis(newStack)){
            Map<EquipmentSlot, ItemStack> map = new java.util.HashMap<>(Map.of(
                    EquipmentSlot.HEAD, getEquippedStack(EquipmentSlot.HEAD),
                    EquipmentSlot.CHEST, getEquippedStack(EquipmentSlot.CHEST),
                    EquipmentSlot.LEGS, getEquippedStack(EquipmentSlot.LEGS),
                    EquipmentSlot.FEET, getEquippedStack(EquipmentSlot.FEET)));
            map.replace(slot, newStack);
            if(Functions.is4Lapis(map.values().stream().toList())) kill();
        }
    }
}
