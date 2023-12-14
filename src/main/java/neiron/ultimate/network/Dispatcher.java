package neiron.ultimate.network;

import java.util.Iterator;

import mchorse.mappet.network.common.content.PacketServerSettings;
import mchorse.mappet.network.common.scripts.PacketClick;
import mchorse.mappet.network.server.scripts.ServerHandlerClick;
import mchorse.mclib.network.AbstractDispatcher;
import neiron.ultimate.Ultimate;
import neiron.ultimate.scripting.client.network.PacketClientData;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityTracker;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.relauncher.Side;

public class Dispatcher {
    public static final AbstractDispatcher DISPATCHER = new AbstractDispatcher(Ultimate.MOD_ID) {
        public void register() {
            this.register(PacketClientData.class, PacketClientData.ClientHandler.class, Side.CLIENT);
            this.register(PacketClientData.class, PacketClientData.ServerHandler.class, Side.SERVER);
        }
    };

    public static void sendToTracked(Entity entity, IMessage message) {
        EntityTracker tracker = ((WorldServer)entity.world).getEntityTracker();
        Iterator var3 = tracker.getTrackingPlayers(entity).iterator();

        while(var3.hasNext()) {
            EntityPlayer player = (EntityPlayer)var3.next();
            sendTo(message, (EntityPlayerMP)player);
        }

    }

    public static void sendTo(IMessage message, EntityPlayerMP player) {
        DISPATCHER.sendTo(message, player);
    }

    public static void sendToServer(IMessage message) {
        DISPATCHER.sendToServer(message);
    }

    public static void register() {
        DISPATCHER.register();
    }
}
