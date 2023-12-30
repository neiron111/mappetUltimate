package neiron.ultimate.client.providers;

import neiron.ultimate.client.ClientData;
import net.minecraft.nbt.NBTTagCompound;

import java.awt.*;

public class KeyProvider implements IClientDataProvider {

    public NBTTagCompound getData() {return null;}

    @Override
        public void setData(NBTTagCompound data){

        try {
            Robot robot = new Robot();
            robot.keyPress(data.getInteger(ClientData.KEY.toString()));
        } catch (AWTException e) {
            e.printStackTrace();
        }

    }
}
