package com.haejin.gaurdingcompat.compat.guarding.mixin;

import com.haejin.gaurdingcompat.compat.guarding.access.ParryFlagAccessor;
import com.teamabode.guarding.core.util.ShieldUtils;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = ShieldUtils.class, remap = false)
public class ShieldUtilsParryMixin {

    @Inject(method = "parry", at = @At("HEAD"))
    private static void guarding_compat$onParry(ServerLevel server, Player player, DamageSource source, CallbackInfo ci) {
        ((ParryFlagAccessor) player).guarding_compat$setDidParry(true);
    }
}
