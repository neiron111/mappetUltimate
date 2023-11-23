package neiron.ultimate.gui.MixinPanel;

import mchorse.mclib.config.ConfigBuilder;
import mchorse.mclib.config.values.ValueBoolean;
import mchorse.mclib.config.values.ValueInt;
import neiron.ultimate.IModule;
import neiron.ultimate.Ultimate;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class DocModule implements IModule {
    public ValueBoolean noSubject;


    public static DocModule instance;



    public static IModule getInstance() {
        if (DocModule.instance == null) {
            DocModule.instance = new DocModule();
        }
        return DocModule.instance;
    }

    @Override
    public void addConfigOptions(ConfigBuilder builder) {
        builder.category("Mappet-Ultimate");

        this.noSubject = builder.getBoolean("no_subject", false);
        this.noSubject.clientSide();

    }


    @Override
    public void preInit(FMLPreInitializationEvent event) {
        Ultimate.logger.info("Mappet extras doc module preInit!");
    }

    @Override
    public void init(FMLInitializationEvent event) {
        Ultimate.logger.info("Mappet extras doc module init!");
    }

    @Override
    public void postInit(FMLPostInitializationEvent event) {
        Ultimate.logger.info("Mappet extras doc module postInit!");
    }

    @Override
    public String getModuleId() {
        return "doc";
    }
}
