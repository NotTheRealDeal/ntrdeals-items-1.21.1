package net.ntrdeal.ntrdeals_items.mixin;

import net.minecraft.entity.DamageUtil;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.AttributeContainer;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.tag.DamageTypeTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.ntrdeal.ntrdeals_items.attribute.ModAttributes;
import net.ntrdeal.ntrdeals_items.command.ModGameRules;
import net.ntrdeal.ntrdeals_items.compatability.EnchancementCompatibility;
import net.ntrdeal.ntrdeals_items.variables.Functions;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity {

    public LivingEntityMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @Shadow public abstract double getAttributeValue(RegistryEntry<EntityAttribute> attribute);

    @Shadow public abstract double getAttributeBaseValue(RegistryEntry<EntityAttribute> attribute);

    @Shadow public abstract void heal(float amount);

    @Shadow public abstract float getHealth();

    @Shadow public abstract float getMaxHealth();

    @Shadow public abstract boolean isBaby();

    @Unique
    private int lat = 0;

    @Inject(method = "createLivingAttributes", at = @At("RETURN"), cancellable = true)
    private static void Injected(CallbackInfoReturnable<DefaultAttributeContainer.Builder> cir){
        cir.setReturnValue(cir.getReturnValue()
                .add(ModAttributes.MOVEMENT_SCALE)
                .add(ModAttributes.ARMOR_PENETRATION)
                .add(ModAttributes.PASSIVE_REGENERATION)
                .add(ModAttributes.SCALE)
                .add(ModAttributes.RANGED_ATTACK_MULTIPLIER)
                .add(ModAttributes.BANE_OF_ADOLESCENCE)
                .add(ModAttributes.CHARGE_TIME));
    }

    @Inject(method = "getJumpVelocity()F", at = @At("RETURN"), cancellable = true)
    private void Injected2(CallbackInfoReturnable<Float> cir){
        cir.setReturnValue(cir.getReturnValue()*Functions.normalizeMovement((float) getAttributeValue(ModAttributes.MOVEMENT_SCALE)));
    }

    @Redirect(method = "fall", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;getAttributeValue(Lnet/minecraft/registry/entry/RegistryEntry;)D"))
    private double Redirected(LivingEntity instance, RegistryEntry<EntityAttribute> attribute){
        return getAttributeValue(attribute)*(getAttributeBaseValue(ModAttributes.MOVEMENT_SCALE));
    }

    @Redirect(method = "computeFallDamage", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;getAttributeValue(Lnet/minecraft/registry/entry/RegistryEntry;)D", ordinal = 0))
    private double Redirected2(LivingEntity instance, RegistryEntry<EntityAttribute> attribute){
        return getAttributeValue(attribute)*(getAttributeValue(ModAttributes.MOVEMENT_SCALE));
    }

    @Redirect(method = "applyArmorToDamage", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/DamageUtil;getDamageLeft(Lnet/minecraft/entity/LivingEntity;FLnet/minecraft/entity/damage/DamageSource;FF)F"))
    private float Redirected3(LivingEntity armorWearer, float damageAmount, DamageSource damageSource, float armor, float armorToughness){
        float modifiedArmor = damageSource.getSource() instanceof LivingEntity entity ? MathHelper.clamp((float) (armor - entity.getAttributeValue(ModAttributes.ARMOR_PENETRATION)), 0f, 30f) : armor;
        return DamageUtil.getDamageLeft(armorWearer, damageAmount, damageSource, modifiedArmor, (float)this.getAttributeValue(EntityAttributes.GENERIC_ARMOR_TOUGHNESS));
    }

    @Inject(method = "tick", at = @At("TAIL"))
    private void Injected3(CallbackInfo ci){
        if (getAttributeValue(ModAttributes.PASSIVE_REGENERATION)!=0.0d && getWorld() instanceof ServerWorld world){
            if (age-lat>world.getGameRules().getInt(ModGameRules.COMBAT_TICKS) && getHealth()<getMaxHealth()){
                heal((float) (getAttributeValue(ModAttributes.PASSIVE_REGENERATION)/world.getGameRules().getInt(ModGameRules.REGEN_TICKS)));
            }
        }
    }

    @Inject(method = "setAttacker", at = @At("TAIL"))
    private void Injected4(LivingEntity attacker, CallbackInfo ci){
        if (attacker!=null){
            lat = age;
        }
    }

    @Redirect(method = "getScale", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/attribute/AttributeContainer;getValue(Lnet/minecraft/registry/entry/RegistryEntry;)D"))
    private double Redirected4(AttributeContainer instance, RegistryEntry<EntityAttribute> attribute){
        return MathHelper.clamp(instance.getValue(EntityAttributes.GENERIC_SCALE)+instance.getValue(ModAttributes.SCALE), 0.0625, 16);
    }

    @ModifyArg(method = "applyMovementInput", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;updateVelocity(FLnet/minecraft/util/math/Vec3d;)V"))
    private float ModifiedArg(float value){
        return (value * (float) getAttributeValue(ModAttributes.MOVEMENT_SCALE));
    }

    @ModifyVariable(method = "damage", at = @At("HEAD"), argsOnly = true)
    private float modifyDamageAmount(float amount, DamageSource source) {
        if (source.getAttacker() instanceof LivingEntity entity){
            if (isBaby() || getCommandTags().contains("adolescence")){
                amount += (float) entity.getAttributeValue(ModAttributes.BANE_OF_ADOLESCENCE);
            }
            if (source.isIn(DamageTypeTags.IS_PROJECTILE)){
                if (!EnchancementCompatibility.isBrimstone(source)){
                    amount *= (float) entity.getAttributeValue(ModAttributes.RANGED_ATTACK_MULTIPLIER);
                }
            }
        }
        return amount;
    }

}
