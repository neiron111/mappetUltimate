package neiron.ultimate.mixins.scriptpanel.mixin;

import mchorse.mappet.api.scripts.Script;
import mchorse.mappet.client.gui.panels.GuiMappetDashboardPanel;
import mchorse.mappet.client.gui.panels.GuiScriptPanel;
import neiron.ultimate.mixins.scriptpanel.AutoCodeInject;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import mchorse.mappet.client.gui.GuiMappetDashboard;
import net.minecraft.client.Minecraft;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

@Mixin(value = GuiScriptPanel.class, remap = false)


public abstract class ScriptPanel extends GuiMappetDashboardPanel<Script> {


    public ScriptPanel(Minecraft mc, GuiMappetDashboard dashboard) {
        super(mc, dashboard);
    }

    @Inject(
            method = "fillDefaultData",
            at = @At(
                    value = "TAIL"
            )
    )
    public void mixin(Script data, CallbackInfo ci) {
        if (AutoCodeInject.instance.customText.get() == true) {
            super.fillDefaultData(data);
            try {
                data.code = String.join("\n", Files.readAllLines(Paths.get(".\\config\\mappetultimate").resolve("script.json"), StandardCharsets.UTF_8));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }
}
