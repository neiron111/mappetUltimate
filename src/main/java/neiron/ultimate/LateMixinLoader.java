package neiron.ultimate;

import zone.rong.mixinbooter.ILateMixinLoader;

import java.util.List;
import java.util.stream.Collectors;

public class LateMixinLoader implements ILateMixinLoader {
    @Override
    public List<String> getMixinConfigs() {
        return Ultimate.modules.stream()
                .map(IModule::getMixinConfigs)
                .flatMap(List::stream)
                .collect(Collectors.toList());
    }
}
