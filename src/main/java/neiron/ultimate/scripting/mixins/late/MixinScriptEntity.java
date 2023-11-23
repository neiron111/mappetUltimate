package neiron.ultimate.scripting.mixins.late;

import mchorse.mappet.api.scripts.code.ScriptEvent;
import mchorse.mappet.api.scripts.code.entities.ScriptEntity;
import mchorse.mappet.api.scripts.user.entities.IScriptEntity;
import mchorse.mappet.api.utils.DataContext;
import mchorse.metamorph.api.morphs.AbstractMorph;
import mchorse.metamorph.network.common.survival.PacketMorphPlayer;
import neiron.ultimate.MixinTargetName;
import neiron.ultimate.network.Dispatcher;
import neiron.ultimate.scripting.scripts.code.ScriptUltimate;
import neiron.ultimate.scripting.scripts.user.IScriptUltimate;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(value = ScriptEntity.class, remap = false)
@MixinTargetName("mchorse.mappet.api.scripts.user.entities.IScriptEntity")
public abstract class MixinScriptEntity
{

}