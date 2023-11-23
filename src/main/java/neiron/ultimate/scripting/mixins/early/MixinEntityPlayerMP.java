package neiron.ultimate.scripting.mixins.early;

import neiron.ultimate.scripting.mixins.utils.EntityPlayerMPAccessor;
import net.minecraft.entity.player.EntityPlayerMP;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(value = EntityPlayerMP.class)
public class MixinEntityPlayerMP implements EntityPlayerMPAccessor {
    @Shadow(remap = true)
    private String language;

    @Shadow(remap = true)
    private int respawnInvulnerabilityTicks;

    @Override
    public String getLanguage() {
        return language;
    }

    @Override
    public int getRespawnInvulnerabilityTicks() {
        return respawnInvulnerabilityTicks;
    }
}
