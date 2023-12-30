package neiron.ultimate.client.gui;

import mchorse.mappet.client.gui.utils.overlays.GuiOverlay;
import mchorse.mclib.client.gui.framework.GuiBase;
import mchorse.mclib.client.gui.framework.elements.GuiElement;
import mchorse.mclib.client.gui.framework.elements.buttons.GuiButtonElement;
import mchorse.mclib.client.gui.utils.keys.IKey;
import mchorse.mclib.config.gui.GuiConfigPanel;
import mchorse.mclib.config.values.ValueGUI;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Arrays;
import java.util.List;

public class TextEditorButton extends ValueGUI {

    public TextEditorButton(String id) {
        super(id);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public List<GuiElement> getFields(Minecraft mc, GuiConfigPanel config) {
        GuiButtonElement button = new GuiButtonElement(mc, IKey.lang("mappetultimate.config.Mappet-Ultimate.editor_button"), (t) ->
        {
            GuiOverlay.addOverlay(GuiBase.getCurrent(), new ValueScriptEditor(mc), 0.6F, 0.95F);
        });

        return Arrays.asList(button.tooltip(IKey.lang(this.getCommentKey())));
    }
}
