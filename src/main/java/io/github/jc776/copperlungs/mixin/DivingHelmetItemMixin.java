package io.github.jc776.copperlungs.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import com.simibubi.create.content.curiosities.armor.DivingHelmetItem;

import io.github.jc776.copperlungs.CopperLungs;
import net.minecraft.tags.FluidTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.material.Fluid;

@Mixin(DivingHelmetItem.class)
public abstract class DivingHelmetItemMixin {
	/** 
	 * Activate helmet "if in water or lava" -> "if in water or bad air or lava" 
	 * "lava" is used for an advancement, so don't modify that one
	 */
	@Redirect(method = "breatheUnderwater(Lnet/minecraftforge/event/entity/living/LivingEvent$LivingUpdateEvent;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/LivingEntity;isEyeInFluid(Lnet/minecraft/tags/TagKey;)Z"))
	private static boolean redirectIsEyeInFluid(LivingEntity entity, TagKey<Fluid> tagKey) {
		final var res = entity.isEyeInFluid(tagKey);
		if (tagKey == FluidTags.WATER) {
			return res || CopperLungs.airQualityActivatesHelmet(entity);
		}
		return res;
	}
	

}