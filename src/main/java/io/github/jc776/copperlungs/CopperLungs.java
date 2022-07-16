package io.github.jc776.copperlungs;

import at.petrak.minerslung.common.breath.AirHelper;
import at.petrak.minerslung.common.breath.AirQualityLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.fml.common.Mod;

@Mod(CopperLungs.MOD_ID)
public class CopperLungs {
	public static final String MOD_ID = "copperlungs";
	
	public static boolean airQualityActivatesHelmet(LivingEntity entity) {
		final var air = AirHelper.getO2LevelFromLocation(entity.getEyePosition(), entity.getLevel()).getFirst();
		return air == AirQualityLevel.RED || air == AirQualityLevel.YELLOW;
	}
}