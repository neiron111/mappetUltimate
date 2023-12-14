package neiron.ultimate.MixinScriptPanel;

import mchorse.mappet.api.scripts.Script;
import mchorse.mappet.client.gui.panels.GuiMappetDashboardPanel;
import mchorse.mappet.client.gui.panels.GuiScriptPanel;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import mchorse.mappet.client.gui.GuiMappetDashboard;
import net.minecraft.client.Minecraft;

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
        if (PanelModule.instance.noSubject.get() == true) {
            super.fillDefaultData(data);
            data.code = "function main(c)\n{\n\n}";
        }

    }
}
