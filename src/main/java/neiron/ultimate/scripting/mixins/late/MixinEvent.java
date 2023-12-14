package neiron.ultimate.scripting.mixins.late;

import mchorse.mappet.Mappet;
import mchorse.mappet.api.scripts.code.ScriptEvent;
import mchorse.mappet.api.triggers.Trigger;
import mchorse.mappet.api.utils.DataContext;
import neiron.ultimate.utils.MixinTargetName;
import neiron.ultimate.scripting.scripts.code.ScriptUltimate;
import neiron.ultimate.scripting.scripts.user.IScriptUltimate;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(value = ScriptEvent.class, remap = false)
@MixinTargetName("mchorse.mappet.api.scripts.user.IScriptEvent")
public abstract class MixinEvent {
    public IScriptUltimate getUltimate() {
        return new ScriptUltimate();
    }
    private DataContext context;
    public int shaudy(String command) {
        return this.context.execute(command);
    }

}