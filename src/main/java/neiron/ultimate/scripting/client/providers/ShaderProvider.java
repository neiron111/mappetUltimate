package neiron.ultimate.scripting.client.providers;

import net.minecraft.nbt.NBTTagCompound;
import net.optifine.shaders.Shaders;
import neiron.ultimate.scripting.client.ClientData;

public class ShaderProvider implements IClientDataProvider {
    public NBTTagCompound getData() {

        NBTTagCompound nbtTagCompound = new NBTTagCompound();
        nbtTagCompound.setString(ClientData.SHADER.toString(), Shaders.getShaderPack().getName());

        return nbtTagCompound;
    }

    @Override
    public void setData(NBTTagCompound data) {}
}