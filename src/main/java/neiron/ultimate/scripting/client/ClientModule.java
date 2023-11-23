package neiron.ultimate.scripting.client;

import mchorse.mclib.config.ConfigBuilder;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import neiron.ultimate.IModule;
import neiron.ultimate.Ultimate;

public class ClientModule implements IModule {
    private static ClientModule instance;

    public static IModule getInstance() {
        if (ClientModule.instance == null) {
            ClientModule.instance = new ClientModule();
        }
        return ClientModule.instance;
    }

    @Override
    public void addConfigOptions(ConfigBuilder builder) {
    }

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        Ultimate.logger.info("Mappet extras client module preInit!");
    }

    @Override
    public void init(FMLInitializationEvent event) {
        Ultimate.logger.info("Mappet extras client module init!");
    }

    @Override
    public void postInit(FMLPostInitializationEvent event) {
        Ultimate.logger.info("Mappet extras client module postInit!");
    }

    @Override
    public String getModuleId() {
        return "client";
    }
}
