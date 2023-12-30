package neiron.ultimate.scripting;

import mchorse.mclib.config.ConfigBuilder;
import neiron.ultimate.Ultimate;
import neiron.ultimate.utils.IFeatures;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class Scripting implements IFeatures {
    private static Scripting instance;

    public static IFeatures getInstance() {
        if (Scripting.instance == null) {
            Scripting.instance = new Scripting();
        }
        return Scripting.instance;
    }

    @Override
    public void addConfigOptions(ConfigBuilder builder) {

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
        return "script";
    }
}
