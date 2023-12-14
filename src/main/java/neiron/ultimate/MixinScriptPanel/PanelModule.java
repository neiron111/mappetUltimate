package neiron.ultimate.MixinScriptPanel;

import mchorse.mclib.config.ConfigBuilder;
import mchorse.mclib.config.values.ValueBoolean;
import neiron.ultimate.utils.IModule;
import neiron.ultimate.Ultimate;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class PanelModule implements IModule {
    public ValueBoolean noSubject;


    public static PanelModule instance;



    public static IModule getInstance() {
        if (PanelModule.instance == null) {
            PanelModule.instance = new PanelModule();
        }
        return PanelModule.instance;
    }

    @Override
    public void addConfigOptions(ConfigBuilder builder) {
        builder.category("Mappet-Ultimate");

        this.noSubject = builder.getBoolean("no_subject", false);
        this.noSubject.clientSide();

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
    public String getModuleId() {
        return "scriptpanel";
    }
}
