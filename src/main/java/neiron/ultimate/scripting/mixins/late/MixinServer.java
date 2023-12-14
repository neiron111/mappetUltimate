package neiron.ultimate.scripting.mixins.late;

import mchorse.mappet.api.scripts.code.ScriptEvent;
import mchorse.mappet.api.scripts.code.ScriptServer;
import mchorse.mappet.api.utils.DataContext;
import neiron.ultimate.scripting.scripts.code.ScriptUltimate;
import neiron.ultimate.scripting.scripts.user.IScriptUltimate;
import neiron.ultimate.utils.MixinTargetName;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.GameType;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(value = ScriptServer.class, remap = false)
@MixinTargetName("mchorse.mappet.api.scripts.user.IScriptServer")
public abstract class MixinServer {

    private MinecraftServer server;

    /**
     * Return the maximum number of players on the server
     *
     * <pre>{@code
     *    function main(c)
     *   {
     *      c.send(c.server.getMaxPlayers())
     *   }
     *  }</pre>
     *
     * @return int
     */
    public int getMaxPlayers()
    {
       return this.server.getMaxPlayers();
    }
}