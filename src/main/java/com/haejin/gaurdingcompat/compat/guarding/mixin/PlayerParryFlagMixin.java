package com.haejin.gaurdingcompat.compat.guarding.mixin;

import com.haejin.gaurdingcompat.compat.guarding.access.ParryFlagAccessor;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(Player.class)
public class PlayerParryFlagMixin implements ParryFlagAccessor {

    @Unique
    private boolean didParry = false;

    @Override
    public boolean guarding_compat$didParry() {
        return this.didParry;
    }

    @Override
    public void guarding_compat$setDidParry(boolean value) {
        this.didParry = value;
    }
}
