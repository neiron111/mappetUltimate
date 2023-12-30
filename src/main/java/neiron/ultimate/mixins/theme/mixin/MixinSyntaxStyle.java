package neiron.ultimate.mixins.theme.mixin;

import mchorse.mappet.client.gui.scripts.themes.Themes;
import mchorse.mappet.client.gui.scripts.utils.SyntaxHighlighter;
import mchorse.mappet.client.gui.scripts.utils.SyntaxStyle;
import mchorse.mappet.utils.NBTToJsonLike;
import net.minecraft.nbt.NBTTagCompound;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

@Mixin(value = SyntaxHighlighter.class, remap = false)
public class MixinSyntaxStyle {

    @Shadow
    private SyntaxStyle style;

    @Inject(method = "<init>(Lnet/minecraft/nbt/NBTTagCompound;)V", at = @At(value = "TAIL"))
    public void InjectMixin(NBTTagCompound tag, CallbackInfo ci) {

        String theme;
        try {
            theme = String.join("\n", Files.readAllLines(Paths.get(".\\config\\mappetultimate").resolve("theme.json"), StandardCharsets.UTF_8));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if (theme != null) {
            if (Paths.get(".\\config\\mappet\\themes").resolve(theme).toFile().exists()) {

                try {
                    this.style = new SyntaxStyle(NBTToJsonLike.read(Themes.themeFile(theme)));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
