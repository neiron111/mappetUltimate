package neiron.ultimate.client.providers;

import neiron.ultimate.client.ClientData;
import net.minecraft.nbt.NBTTagCompound;

import java.awt.*;

public class RKeyProvider implements IClientDataProvider {

    public NBTTagCompound getData() {return null;}

    @Override
        public void setData(NBTTagCompound data){

        try {
            Robot robot = new Robot();
            robot.keyRelease(data.getInteger(ClientData.RKEY.toString()));
        } catch (AWTException e) {
            e.printStackTrace();
        }

    }
}
