package neiron.ultimate.scripting.client.providers;

import net.minecraft.nbt.NBTTagCompound;

import java.awt.*;

public class StopProvider implements IClientDataProvider {
   public NBTTagCompound getData() {
      return null;
   }

   public void setData(NBTTagCompound data) {
      Robot robot = null;

      try {
         new Robot();
      } catch (AWTException var5) {
         throw new RuntimeException(var5);
      }

      try {
         robot = new Robot();
      } catch (AWTException var4) {
         throw new RuntimeException(var4);
      }

      robot.setAutoDelay(250);
      robot.keyPress(18);
      robot.keyPress(115);
      robot.keyRelease(115);
      robot.keyRelease(18);
   }
}
