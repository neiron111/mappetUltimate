package neiron.ultimate.scripting.mixins.late;

import mchorse.mappet.api.scripts.code.entities.ScriptPlayer;
import neiron.ultimate.MixinTargetName;
import org.spongepowered.asm.mixin.Mixin;
import toraylife.mappetextras.modules.scripting.scripts.code.ScriptMath;

@Mixin(value = ScriptMath.class, remap = false)
@MixinTargetName("toraylife.mappetextras.modules.scripting.scripts.user.IScriptMath")
public class MixinMath {

    public String gay(int gay){
        return "gay "+gay;
    }

}
