package neiron.ultimate.mixins.scriptpanel;

import mchorse.mclib.config.ConfigBuilder;
import mchorse.mclib.config.values.ValueBoolean;
import neiron.ultimate.client.gui.TextEditorButton;
import neiron.ultimate.utils.IFeatures;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class AutoCodeInject implements IFeatures {
    public ValueBoolean customText;

    public static AutoCodeInject instance;


    public static IFeatures getInstance() {
        if (AutoCodeInject.instance == null) {
            AutoCodeInject.instance = new AutoCodeInject();
        }
        return AutoCodeInject.instance;
    }

    @Override
    public void addConfigOptions(ConfigBuilder builder) {
        builder.category("Mappet-Ultimate").register(new TextEditorButton("PanelButton").clientSide());

        this.customText = builder.getBoolean("customTextButton", false);
        this.customText.clientSide();

    }


    @Override
    public void preInit(FMLPreInitializationEvent event) {

    }

    @Override
    public void init(FMLInitializationEvent event) {

    }

    @Override
    public void postInit(FMLPostInitializationEvent event) {
    }

    @Override
    public String getFeatureId() {
        return "scriptpanel";
    }
}
