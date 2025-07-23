package net.ntrdeal.ntrdeals_items.mixin;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.ntrdeal.ntrdeals_items.attribute.ModAttributes;
import org.apache.commons.lang3.mutable.MutableFloat;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(EnchantmentHelper.class)
public class EnchantmentHelperMixin {
    @Redirect(method = "getCrossbowChargeTime", at = @At(value = "INVOKE", target = "Lorg/apache/commons/lang3/mutable/MutableFloat;floatValue()F"))
    private static float Redirected(MutableFloat instance, ItemStack stack, LivingEntity user){
        return (float) (instance.doubleValue()*user.getAttributeValue(ModAttributes.CHARGE_TIME));
    }
}
