package neiron.ultimate.documentation;


import mchorse.mclib.config.ConfigBuilder;
import mchorse.mclib.config.values.ValueBoolean;
import mchorse.mclib.config.values.ValueInt;
import neiron.ultimate.IModule;
import neiron.ultimate.Ultimate;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class DocumModule implements IModule {

    public static DocumModule instance;



    public static IModule getInstance() {
        if (DocumModule.instance == null) {
            DocumModule.instance = new DocumModule();
        }
        return DocumModule.instance;
    }

    @Override
    public void addConfigOptions(ConfigBuilder builder) {

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
        return "documentation";
    }
}
