package pl.dezo.clientmod.mixin;

import net.minecraft.SharedConstants;
import net.minecraft.client.MinecraftClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(MinecraftClient.class)
public abstract class MinecraftClientMixin {

    @Inject(method = "getWindowTitle", at = @At("HEAD"), cancellable = true, require = 0)
    private void dezo$windowTitle(CallbackInfoReturnable<String> cir) {
        cir.setReturnValue("Dezo Client " + SharedConstants.getGameVersion().getName());
    }
}
