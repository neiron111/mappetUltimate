package neiron.ultimate.scripting.mixins.late;

import mchorse.mappet.api.scripts.user.entities.IScriptEntity;
import mchorse.metamorph.api.morphs.AbstractMorph;
import mchorse.metamorph.network.common.survival.PacketMorphPlayer;
import neiron.ultimate.MixinTargetName;
import neiron.ultimate.scripting.client.AccessType;
import neiron.ultimate.scripting.client.ClientData;
import neiron.ultimate.scripting.client.network.PacketClientData;
import neiron.ultimate.scripting.client.providers.DateProvider;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.optifine.shaders.Shaders;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import neiron.ultimate.network.Dispatcher;
import net.minecraft.network.play.server.SPacketEntityMetadata;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.entity.Entity;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.DataSerializers;
import java.lang.Byte;

import java.util.function.Consumer;

@Mixin(value = mchorse.mappet.api.scripts.code.entities.ScriptPlayer.class, remap = false)
@MixinTargetName("mchorse.mappet.api.scripts.user.entities.IScriptPlayer")
public abstract class IUltimatePlayer {
    @Shadow
    public abstract EntityPlayerMP getMinecraftPlayer();

    /**
     * Return player used shaderpack
     *
     *<pre>{@code
     * function main(c) {
     * c.subject.getShaderPack(function(shader)
     *  {
     *     this.shader = shader.replace(".zip", "")
     *
     *     mappet.set("shader", this.shader)
     *  })
     *
     * var shader = mappet.get("shader")
     *
     * c.send(shader) }
     * </pre>
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
     * <pre>{@code
     * function main(c) {
     * c.subject.getDate("y-d-m-s", function(date)
     *  {
     *     mappet.set("date", date)
     *  })
     *
     * var date = mappet.get("date")
     *
     * c.send(date) }
     * </pre>
     *
     * @param pattern, callback
     *
     *  @return String
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
     *     c.subject.saveScreenshot("\\config", "name") }
     * </pre>
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
     *    function main(c)
     * {
     *     var player = c.server.getPlayer("Player434").getMinecraftPlayer()
     *
     *     c.subject.setLocalMorph(mappet.createMorph("{DisplayName:\"McHorse\",Skin:\"blockbuster:textures/entity/mchorse/skin.png\",BodyParts:[{Limb:\"head\",Morph:{Settings:{Hands:1b},Name:\"blockbuster.mchorse/head\"}}],Settings:{Hands:1b},Name:\"blockbuster.fred_3d\"}"), player) }
     * </pre>
     *
     * @param morph, player
     *
     * @return void
     */
    public void setLocalMorph(AbstractMorph morph, EntityPlayerMP player)
    {
        mchorse.metamorph.network.Dispatcher.sendTo(new PacketMorphPlayer(this.getMinecraftPlayer().getEntityId(), morph), player);
    }

    public void setLocalGlowing(IScriptEntity entity, Byte bytes)
    {
        entity.getMinecraftEntity().getDataManager().set(new DataParameter(0, DataSerializers.BYTE), bytes);

         getMinecraftPlayer().connection.sendPacket(new SPacketEntityMetadata(entity.getMinecraftEntity().getEntityId(), entity.getMinecraftEntity().getDataManager(), true));
    }
}
