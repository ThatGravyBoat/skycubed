package tech.thatgravyboat.skycubed.mixin.fixes;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.mojang.authlib.yggdrasil.YggdrasilMinecraftSessionService;
import org.slf4j.Logger;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(YggdrasilMinecraftSessionService.class)
public class YggdrasilMinecraftSessionServiceMixin {

    @WrapOperation(
            method = "unpackTextures",
            at = @At(
                    value = "INVOKE",
                    target = "Lorg/slf4j/Logger;error(Ljava/lang/String;Ljava/lang/Throwable;)V",
                    ordinal = 0
            ),
            remap = false
    )
    private void unpackTextures(Logger instance, String s, Throwable throwable, Operation<Void> original) {
        // Do nothing
    }
}
