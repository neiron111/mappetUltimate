package neiron.ultimate.scripting.client.providers;

import neiron.ultimate.scripting.client.ClientData;
import net.minecraft.nbt.NBTTagCompound;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

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
