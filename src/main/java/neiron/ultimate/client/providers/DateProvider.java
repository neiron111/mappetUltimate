package neiron.ultimate.client.providers;

import net.minecraft.nbt.NBTTagCompound;
import neiron.ultimate.client.ClientData;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateProvider implements IClientDataProvider{
    public NBTTagCompound getData(NBTTagCompound data) {
        NBTTagCompound nbtTagCompound = new NBTTagCompound();
        DateFormat dateFormat = new SimpleDateFormat(data.getString("format"));
        Date date = new Date();
        nbtTagCompound.setString(ClientData.DATE.toString(), dateFormat.format(date));

        return nbtTagCompound;
    }

    @Override
    public void setData(NBTTagCompound data) {
    }
}