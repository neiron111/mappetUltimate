package neiron.ultimate;

import neiron.ultimate.utils.IModule;
import zone.rong.mixinbooter.IEarlyMixinLoader;

import java.util.List;
import java.util.stream.Collectors;


public class EarlyMixinLoader implements IEarlyMixinLoader {
    @Override
    public List<String> getMixinConfigs() {
        return Ultimate.modules.stream()
                .map(IModule::getEarlyMixinConfigs)
                .flatMap(List::stream)
                .collect(Collectors.toList());
    }
}
