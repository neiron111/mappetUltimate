package neiron.ultimate.mixins.theme;

import mchorse.mclib.config.ConfigBuilder;
import neiron.ultimate.utils.IFeatures;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ThemeFix implements IFeatures {

    public static ThemeFix instance;

    public static IFeatures getInstance() {
        if (ThemeFix.instance == null) {
            ThemeFix.instance = new ThemeFix();
        }
        return ThemeFix.instance;
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
        return "theme";
    }
}
