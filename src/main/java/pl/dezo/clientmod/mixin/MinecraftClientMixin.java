package pl.dezo.clientmod.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.Icons;
import net.minecraft.client.util.Window;
import net.minecraft.resource.InputSupplier;
import net.minecraft.resource.ResourcePack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.io.InputStream;

@Mixin(MinecraftClient.class)
public class MinecraftClientMixin {

    // Przechwytujemy wywołanie ustawiania ikony okna podczas inicjalizacji MinecraftClient
    @Redirect(method = "<init>", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/util/Window;setIcon(Lnet/minecraft/resource/ResourcePack;Lnet/minecraft/client/util/Icons;)V"))
    private void dezoClientIcon(Window window, ResourcePack resourcePack, Icons icons) {
        try {
            // Bezpieczne wstrzyknięcie własnej ikony - ścieżka do Twojej ikony w folderze resources
            InputSupplier<InputStream> icon16 = () -> MinecraftClientMixin.class.getResourceAsStream("/assets/dezoclient/icon16.png");
            InputSupplier<InputStream> icon32 = () -> MinecraftClientMixin.class.getResourceAsStream("/assets/dezoclient/icon32.png");
            
            // Jeśli ikony istnieją, ustawiamy je, w przeciwnym razie puszczamy domyślne
            if (icon16 != null && icon16.get() != null && icon32 != null && icon32.get() != null) {
                window.setIcon(icon16, icon32);
            } else {
                window.setIcon(resourcePack, icons);
            }
        } catch (Exception e) {
            // Fallback w razie błędu - zapobiega crashowi GLFW
            try {
                window.setIcon(resourcePack, icons);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
