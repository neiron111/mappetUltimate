package neiron.ultimate;

import neiron.ultimate.utils.IFeatures;
import zone.rong.mixinbooter.ILateMixinLoader;

import java.util.List;
import java.util.stream.Collectors;

public class LateMixinLoader implements ILateMixinLoader {
    @Override
    public List<String> getMixinConfigs() {
        return Ultimate.features.stream()
                .map(IFeatures::getMixinConfigs)
                .flatMap(List::stream)
                .collect(Collectors.toList());
    }
}
