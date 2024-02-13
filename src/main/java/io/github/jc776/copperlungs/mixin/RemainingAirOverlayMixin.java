package io.github.jc776.copperlungs.mixin;

import com.simibubi.create.content.equipment.armor.RemainingAirOverlay;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;


import io.github.jc776.copperlungs.CopperLungs;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.tags.FluidTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.material.Fluid;

@Mixin(RemainingAirOverlay.class)
public class RemainingAirOverlayMixin {
	/**
	 * See DivingHelmetItemMixin
	 */
	@Redirect(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/player/LocalPlayer;isEyeInFluid(Lnet/minecraft/tags/TagKey;)Z"))
	private boolean redirectIsEyeInFluid(LocalPlayer entity, TagKey<Fluid> tagKey) {
		final var res = entity.isEyeInFluid(tagKey);
		if (tagKey == FluidTags.WATER) {
			return res || CopperLungs.airQualityActivatesHelmet(entity);
		}
		return res;
	}
}
