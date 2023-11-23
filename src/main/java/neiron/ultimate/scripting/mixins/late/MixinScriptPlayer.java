package neiron.ultimate.scripting.mixins.late;

import mchorse.mappet.api.scripts.code.entities.ScriptPlayer;
import mchorse.mappet.api.scripts.user.entities.IScriptEntity;
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
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;

import java.util.function.Consumer;

@Mixin(value = ScriptPlayer.class, remap = false)
@MixinTargetName("mchorse.mappet.api.scripts.user.entities.IScriptPlayer")
public abstract class MixinScriptPlayer {
    @Shadow
    public abstract EntityPlayerMP getMinecraftPlayer();


    public void getShaderPack(Consumer<Object> callback) {
        NBTTagCompound nbtTagCompound = new NBTTagCompound();
        nbtTagCompound.setString(ClientData.SHADER.toString(), Shaders.getShaderPack().getName());

        PacketClientData.сallBack.put(this.getMinecraftPlayer().getUniqueID(), callback);
        Dispatcher.sendTo(new PacketClientData(ClientData.SHADER, AccessType.GET, nbtTagCompound), this.getMinecraftPlayer());

    }
    public void getDate(String pattern, Consumer<Object> callback) {
        NBTTagCompound nbtTagCompound = new NBTTagCompound();

        NBTTagCompound data = new NBTTagCompound();

        data.setString("format", pattern);

        nbtTagCompound.setString(ClientData.GETDATE.toString(), new GetdateProvider().getData(data).getString(ClientData.GETDATE.toString()));

        PacketClientData.сallBack.put(this.getMinecraftPlayer().getUniqueID(), callback);
        Dispatcher.sendTo(new PacketClientData(ClientData.GETDATE, AccessType.GET_WITH_DATA, nbtTagCompound, data), this.getMinecraftPlayer());

    }
    public void saveScreenshot(String path, String name){
        NBTTagCompound nbtTagCompound = new NBTTagCompound();
        nbtTagCompound.setString(ClientData.SCREEN.toString(), path);
        nbtTagCompound.setString("name", name);

        Dispatcher.sendTo(new PacketClientData(ClientData.SCREEN, AccessType.SET, nbtTagCompound), this.getMinecraftPlayer());
    }

    public void gameStop() {
        NBTTagCompound nbtTagCompound = new NBTTagCompound();
        Dispatcher.sendTo(new PacketClientData(ClientData.STOP, AccessType.SET, nbtTagCompound), this.getMinecraftPlayer());
    }

    public void setLocalMorph(AbstractMorph morph, EntityPlayerMP player)
    {
        mchorse.metamorph.network.Dispatcher.sendTo(new PacketMorphPlayer(this.getMinecraftPlayer().getEntityId(), morph), player);
    }

    public void setShaderPack(String par1name)
    {
        Shaders.setShaderPack(par1name);
        Shaders.uninit();
    }

}