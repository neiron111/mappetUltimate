package neiron.ultimate;

import mchorse.mclib.McLib;
import mchorse.mclib.config.ConfigBuilder;
import mchorse.mclib.config.ConfigManager;
import mchorse.mclib.events.RegisterConfigEvent;
import neiron.ultimate.MixinDocumentation.DocumModule;
import neiron.ultimate.MixinScriptPanel.PanelModule;
import neiron.ultimate.network.proxy.CommonProxy;
import neiron.ultimate.utils.IModule;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import neiron.ultimate.scripting.ScriptingModule;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Mod(
        modid = Ultimate.MOD_ID,
        name = Ultimate.NAME,
        version = Ultimate.VERSION,
        dependencies = "required-after:mclib@[@MCLIB@,);" +
                "required-after:mappet@[@MAPPET@,);",
        updateJSON = ""
)
public class Ultimate {
    public Ultimate() {

    }
    public static final String MOD_ID = "mappetultimate";
    public static final String NAME = "MappetUltimate";

    public static final String VERSION = "@VERSION@";

    public static final Logger logger = LogManager.getLogger(MOD_ID);

    public static final int mainColor = 0x41376e;

    public ConfigManager configs;

    @Mod.Instance
    public static Ultimate instance;

    @SidedProxy(serverSide = "neiron.ultimate.network.proxy.CommonProxy", clientSide = "neiron.ultimate.network.proxy.ClientProxy")
    public static CommonProxy proxy;

    public static final List<IModule> modules = new ArrayList<>(Arrays.asList(
        ScriptingModule.getInstance(),
        DocumModule.getInstance(),
        PanelModule.getInstance()
    ));

    @SubscribeEvent
    public void onConfigRegister(RegisterConfigEvent event) {
        ConfigBuilder builder = event.createBuilder(MOD_ID);

        modules.forEach(module -> module.addConfigOptions(builder));
    }

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {

        McLib.EVENT_BUS.register(this);

        this.configs = new ConfigManager();

        File configFolder = Loader.instance().getConfigDir();
        configFolder.mkdir();

        this.configs.register(configFolder);

        proxy.preInit(event);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.init(event);
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        proxy.postInit(event);
    }
}
