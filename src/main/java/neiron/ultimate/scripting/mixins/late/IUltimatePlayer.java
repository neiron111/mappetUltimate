package neiron.ultimate.scripting.mixins.late;

import mchorse.metamorph.api.morphs.AbstractMorph;
import mchorse.metamorph.network.common.survival.PacketMorphPlayer;
import neiron.ultimate.MixinTargetName;
import neiron.ultimate.scripting.client.AccessType;
import neiron.ultimate.scripting.client.ClientData;
import neiron.ultimate.scripting.client.network.PacketClientData;
import neiron.ultimate.scripting.client.providers.GetdateProvider;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.optifine.shaders.Shaders;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import neiron.ultimate.network.Dispatcher;

import java.util.function.Consumer;

@Mixin(value = mchorse.mappet.api.scripts.code.entities.ScriptPlayer.class, remap = false)
@MixinTargetName("mchorse.mappet.api.scripts.user.entities.IScriptPlayer")
public abstract class IUltimatePlayer {
    @Shadow
    public abstract EntityPlayerMP getMinecraftPlayer();

    /**
     * Return player used shaderpack
     *
     *
     *
     *
     * @param callback
     *
     * @return shaderName
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
     *
     *
     *
     * @param pattern, callback
     *
     *  @return String
     */
    public void getDate(String pattern, Consumer<Object> callback) {
        NBTTagCompound nbtTagCompound = new NBTTagCompound();

        NBTTagCompound data = new NBTTagCompound();

        data.setString("format", pattern);

        nbtTagCompound.setString(ClientData.GETDATE.toString(), new GetdateProvider().getData(data).getString(ClientData.GETDATE.toString()));

        PacketClientData.сallBack.put(this.getMinecraftPlayer().getUniqueID(), callback);
        Dispatcher.sendTo(new PacketClientData(ClientData.GETDATE, AccessType.GET_WITH_DATA, nbtTagCompound, data), this.getMinecraftPlayer());

    }

    /**
     * Saves screenshots to the specified path with the specified name.
     *
     *
     *
     * @param path, name
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
     * Crashes the game.
     *
     *
     * @return void
     */
    public void gameStop() {
        NBTTagCompound nbtTagCompound = new NBTTagCompound();
        Dispatcher.sendTo(new PacketClientData(ClientData.STOP, AccessType.SET, nbtTagCompound), this.getMinecraftPlayer());
    }
    /**
     * Sets the player's morph for the player.
     *
     *
     * @param morph, player
     *
     * @return void
     */
    public void setLocalMorph(AbstractMorph morph, EntityPlayerMP player)
    {
        mchorse.metamorph.network.Dispatcher.sendTo(new PacketMorphPlayer(this.getMinecraftPlayer().getEntityId(), morph), player);
    }
}