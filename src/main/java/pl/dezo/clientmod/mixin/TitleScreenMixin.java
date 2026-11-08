package pl.dezo.clientmod.mixin;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.TitleScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(TitleScreen.class)
public class TitleScreenMixin {

    @Inject(method = "render", at = @At("RETURN"))
    private void onRender(DrawContext context, int mouseX, int mouseY, float delta, CallbackInfo ci) {
        // Nowy system DrawContext w 1.21.11 do rysowania tekstu DezoClient w prawym dolnym rogu
        String text = "DezoClient 3.7";
        int width = context.getScaledWindowWidth();
        int height = context.getScaledWindowHeight();
        
        // Rysowanie z cieniem (textRenderer, tekst, x, y, kolor)
        context.drawTextWithShadow(
            net.minecraft.client.MinecraftClient.getInstance().textRenderer,
            text,
            width - net.minecraft.client.MinecraftClient.getInstance().textRenderer.getWidth(text) - 5,
            height - 15,
            0xFFFFFF
        );
    }
}
