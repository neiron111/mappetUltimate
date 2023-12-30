package neiron.ultimate.mixins.theme.mixin;

import mchorse.mappet.client.gui.scripts.themes.GuiThemeEditorOverlayPanel;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

@Mixin(value = GuiThemeEditorOverlayPanel.class, remap = false)
public abstract class MixinGuiTheme {

    @Inject(method = "onClose", at = @At(value = "TAIL", ordinal = 5), locals = LocalCapture.CAPTURE_FAILHARD, remap = false)
    public void onClose(CallbackInfo ci, GuiThemeEditorOverlayPanel.SyntaxStyleEntry item)
    {
        try {
            Files.write(Paths.get(".\\config\\mappetultimate").resolve("theme.json"), item.file.getName().getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
