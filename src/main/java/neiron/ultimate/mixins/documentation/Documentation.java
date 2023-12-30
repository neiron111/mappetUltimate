package neiron.ultimate.mixins.documentation;


import mchorse.mclib.config.ConfigBuilder;
import neiron.ultimate.utils.IFeatures;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class Documentation implements IFeatures {

    public static Documentation instance;



    public static IFeatures getInstance() {
        if (Documentation.instance == null) {
            Documentation.instance = new Documentation();
        }
        return Documentation.instance;
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
        return "documentation";
    }
}
