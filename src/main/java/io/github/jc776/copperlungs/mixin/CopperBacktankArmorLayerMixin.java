package io.github.jc776.copperlungs.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import com.simibubi.create.content.curiosities.armor.CopperBacktankArmorLayer;

import io.github.jc776.copperlungs.CopperLungs;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.tags.FluidTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.material.Fluid;

@Mixin(CopperBacktankArmorLayer.class)
public class CopperBacktankArmorLayerMixin {
	/**
	 * See DivingHelmetItemMixin
	 */
	@Redirect(method = "renderRemainingAirOverlay(Lnet/minecraftforge/client/gui/ForgeIngameGui;Lcom/mojang/blaze3d/vertex/PoseStack;FII)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/player/LocalPlayer;isEyeInFluid(Lnet/minecraft/tags/TagKey;)Z"))
	private static boolean redirectIsEyeInFluid(LocalPlayer entity, TagKey<Fluid> tagKey) {
		final var res = entity.isEyeInFluid(tagKey);
		if (tagKey == FluidTags.WATER) {
			return res || CopperLungs.airQualityActivatesHelmet(entity);
		}
		return res;
	}
}
