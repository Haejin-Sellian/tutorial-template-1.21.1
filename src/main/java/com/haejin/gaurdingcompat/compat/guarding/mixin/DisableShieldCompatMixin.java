package com.haejin.gaurdingcompat.compat.guarding.mixin;

import com.haejin.gaurdingcompat.compat.guarding.access.ParryFlagAccessor;
import com.teamabode.guarding.core.registry.GuardingItems;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Player.class)
public class DisableShieldCompatMixin {

    @Inject(method = "disableShield", at = @At("HEAD"), cancellable = true)
    private void guarding_compat$cancelCooldownOnParry(CallbackInfo ci) {
        Player self = (Player)(Object)this;
        ParryFlagAccessor accessor = (ParryFlagAccessor) self;

        if (accessor.guarding_compat$didParry()) {
            accessor.guarding_compat$setDidParry(false);

            // Cancel vanilla disableShield — suppresses vanilla shield cooldown
            ci.cancel();

            // Remove the netherite shield cooldown that Guarding's NetheriteShieldMixin
            // already applied before our cancel could stop it
            self.getCooldowns().removeCooldown(GuardingItems.NETHERITE_SHIELD);
        }
    }
}
