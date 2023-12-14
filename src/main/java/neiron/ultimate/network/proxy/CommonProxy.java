package neiron.ultimate.network.proxy;

import neiron.ultimate.Ultimate;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import neiron.ultimate.network.Dispatcher;

public class CommonProxy {

    public CommonProxy() {
    }
    public void preInit(FMLPreInitializationEvent event) {
        Dispatcher.register();
        Ultimate.modules.forEach(module -> module.preInit(event));
    }

    public void init(FMLInitializationEvent event) {
        Ultimate.modules.forEach(module -> module.init(event));
    }

    public void postInit(FMLPostInitializationEvent event) {
        Ultimate.modules.forEach(module -> module.postInit(event));
    }
}
