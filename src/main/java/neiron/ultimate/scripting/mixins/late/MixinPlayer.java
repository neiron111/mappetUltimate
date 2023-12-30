package neiron.ultimate.scripting.mixins.late;

import com.mojang.authlib.GameProfile;
import mchorse.metamorph.api.morphs.AbstractMorph;
import mchorse.metamorph.network.common.survival.PacketMorphPlayer;
import neiron.ultimate.utils.MixinTargetName;
import neiron.ultimate.client.AccessType;
import neiron.ultimate.client.ClientData;
import neiron.ultimate.client.network.PacketClientData;
import neiron.ultimate.client.providers.DateProvider;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.optifine.shaders.Shaders;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import neiron.ultimate.network.Dispatcher;

import java.util.UUID;
import java.util.function.Consumer;

@Mixin(value = mchorse.mappet.api.scripts.code.entities.ScriptPlayer.class, remap = false)
@MixinTargetName("mchorse.mappet.api.scripts.user.entities.IScriptPlayer")
public abstract class MixinPlayer {

    @Shadow
    public abstract EntityPlayerMP getMinecraftPlayer();



    /**
     * Return player used shaderpack
     *
     * <pre>{@code
     *    function main(c)
     *    {
     *      c.subject.getShaderPack(function(shader)
     *      {
     *          this.shader = shader.replace(".zip", "")
     *
     *          mappet.set("shader", this.shader)
     *      })
     *
     *      var shader = mappet.get("shader")
     *
     *      c.send(shader)
     *    }
     *  }</pre>
     */
    public void getShaderPack(Consumer<Object> callback) {
        NBTTagCompound nbtTagCompound = new NBTTagCompound();
        nbtTagCompound.setString(ClientData.SHADER.toString(), Shaders.getShaderPack().getName());

        PacketClientData.сallBack.put(this.getMinecraftPlayer().getUniqueID(), callback);
        Dispatcher.sendTo(new PacketClientData(ClientData.SHADER, AccessType.GET, nbtTagCompound), this.getMinecraftPlayer());

    }

    /**
     * Return player date by format param
     *
     * <pre>{@code
     *     function main(c) {
     *      c.subject.getDate("y-d-m-s", function(date)
     *      {
     *      mappet.set("date", date)
     *      })
     *
     *      var date = mappet.get("date")
     *
     *      c.send(date)
     *      }
     *  }</pre>
     */
    public void getDate(String pattern, Consumer<Object> callback) {
        NBTTagCompound nbtTagCompound = new NBTTagCompound();

        NBTTagCompound data = new NBTTagCompound();

        data.setString("format", pattern);

        nbtTagCompound.setString(ClientData.DATE.toString(), new DateProvider().getData(data).getString(ClientData.DATE.toString()));

        PacketClientData.сallBack.put(this.getMinecraftPlayer().getUniqueID(), callback);
        Dispatcher.sendTo(new PacketClientData(ClientData.DATE, AccessType.GET_WITH_DATA, nbtTagCompound, data), this.getMinecraftPlayer());

    }

    /**
     * Saves screenshots to the specified path with the specified name.
     *
     *<pre>{@code
     * function main(c)
     * {
     *     c.subject.saveScreenshot("\\config", "name")
     * }
     * }</pre>
     *
     * @param path
     * @param name
     *
     * @return void
     */
    public void saveScreenshot(String path, String name){
        NBTTagCompound nbtTagCompound = new NBTTagCompound();
        nbtTagCompound.setString(ClientData.SCREEN.toString(), path);
        nbtTagCompound.setString("name", name);

        Dispatcher.sendTo(new PacketClientData(ClientData.SCREEN, AccessType.SET, nbtTagCompound), this.getMinecraftPlayer());
    }
    /**
     * Sets the player's morph for the player.
     *
     * <pre>{@code
     *  function main(c)
     *  {
     *      var player = c.server.getPlayer("Player434").getMinecraftPlayer()
     *
     *      c.subject.setLocalMorph(mappet.createMorph("{DisplayName:\"McHorse\",Skin:\"blockbuster:textures/entity/mchorse/skin.png\",BodyParts:[{Limb:\"head\",Morph:{Settings:{Hands:1b},Name:\"blockbuster.mchorse/head\"}}],Settings:{Hands:1b},Name:\"blockbuster.fred_3d\"}"), player)
     *  }
     * }</pre>
     *
     * @param morph, player
     *
     * @return void
     */
    public void setLocalMorph(AbstractMorph morph, EntityPlayerMP player)
    {
        mchorse.metamorph.network.Dispatcher.sendTo(new PacketMorphPlayer(this.getMinecraftPlayer().getEntityId(), morph), player);
    }

    /**
     * Simulation of a keyboard button clamp
     *
     * <pre>{@code
     *   function main(c)
     * {
     *    c.subject.pressKey(16)
     *
     *    c.scheduleScript(70, function (c)
     *     {
     *         c.subject.RealiseKey(16)
     *     });
     * }
     * }</pre>
     *
     * @param key
     *
     * @return void
     */
    public void pressKey(int key)
    {
        NBTTagCompound nbtTagCompound = new NBTTagCompound();
        nbtTagCompound.setInteger(ClientData.KEY.toString(), key);

        Dispatcher.sendTo(new PacketClientData(ClientData.KEY, AccessType.SET, nbtTagCompound), this.getMinecraftPlayer());
    }

    /**
     * Simulation of pressing the keyboard button
     *
     * @param key
     *
     * @return void
     */
    public void realiseKey(int key)
    {
        NBTTagCompound nbtTagCompound = new NBTTagCompound();
        nbtTagCompound.setInteger(ClientData.RKEY.toString(), key);

        Dispatcher.sendTo(new PacketClientData(ClientData.RKEY, AccessType.SET, nbtTagCompound), this.getMinecraftPlayer());
    }

    /**
     * HUI
     *
     <pre>{@code
     *   function main(c)
     * {
     *    MINSHTORM GAY
     * }
     * }</pre>
     *
     * @return GameProfile
     */
    public GameProfile getGameProfile()
    {
        return getMinecraftPlayer().getGameProfile();
    }

    /**
     * HUI
     *
     <pre>{@code
     *   function main(c)
     * {
     *    MINSHTORM GAY
     * }
     * }</pre>
     *
     * @return GameProfile
     */
    public UUID getOfflineUUID(String name)
    {
        return EntityPlayerMP.getOfflineUUID(name);
    }

}
