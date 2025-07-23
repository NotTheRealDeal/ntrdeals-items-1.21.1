package net.ntrdeal.ntrdeals_items.mixin;

import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.player.HungerManager;
import net.minecraft.entity.player.ItemCooldownManager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.ntrdeal.ntrdeals_items.attribute.ModAttributes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerEntity.class)
public class PlayerEntityMixin {

    @Inject(method = "createPlayerAttributes", at = @At("RETURN"), cancellable = true)
    private static void Injected(CallbackInfoReturnable<DefaultAttributeContainer.Builder> cir){
        cir.setReturnValue(cir.getReturnValue()
                .add(ModAttributes.APPETITE)
                .add(ModAttributes.SHIELD_FRAGILITY));
    }

    @Redirect(method = "addExhaustion", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/player/HungerManager;addExhaustion(F)V"))
    private void Redirected(HungerManager instance, float exhaustion){
        if ((PlayerEntity)(Object)this instanceof PlayerEntity entity) {
            instance.addExhaustion((float) (exhaustion*entity.getAttributeValue(ModAttributes.APPETITE)));
        }
    }

    @Redirect(method = "disableShield", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/player/ItemCooldownManager;set(Lnet/minecraft/item/Item;I)V"))
    private void Redirected(ItemCooldownManager instance, Item item, int duration){
        if ((PlayerEntity)(Object)this instanceof PlayerEntity entity){
            instance.set(item, Math.round(duration*(float)entity.getAttributeValue(ModAttributes.SHIELD_FRAGILITY)));
        }
    }
}
