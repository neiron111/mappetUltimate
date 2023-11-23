package neiron.ultimate.scripting.client.providers;

import net.minecraft.nbt.NBTTagCompound;
import neiron.ultimate.scripting.client.ClientData;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GetdateProvider implements IClientDataProvider{
    public NBTTagCompound getData(NBTTagCompound data) {
        NBTTagCompound nbtTagCompound = new NBTTagCompound();
        DateFormat dateFormat = new SimpleDateFormat(data.getString("format"));
        Date date = new Date();
        nbtTagCompound.setString(ClientData.GETDATE.toString(), dateFormat.format(date));

        return nbtTagCompound;
    }

    @Override
    public void setData(NBTTagCompound data) {
    }
}