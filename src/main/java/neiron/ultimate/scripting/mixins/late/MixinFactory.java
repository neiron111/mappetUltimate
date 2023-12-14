package neiron.ultimate.scripting.mixins.late;

import mchorse.mappet.Mappet;
import mchorse.mappet.api.misc.ServerSettings;
import mchorse.mappet.api.scripts.code.ScriptFactory;
import mchorse.mappet.api.triggers.Trigger;
import mchorse.mappet.network.Dispatcher;
import mchorse.mappet.network.common.content.PacketRequestServerSettings;
import mchorse.mappet.network.common.content.PacketRequestStates;
import mchorse.mappet.network.common.content.PacketServerSettings;
import neiron.ultimate.utils.MixinTargetName;
import org.spongepowered.asm.mixin.Mixin;
import mchorse.mappet.client.gui.panels.GuiServerSettingsPanel;

@Mixin(value = ScriptFactory.class, remap = false)
@MixinTargetName("mchorse.mappet.api.scripts.user.IScriptFactory")
public abstract class MixinFactory {

    private ServerSettings settings;

    /**
     * Return Global Trigger
     *
     * <pre>{@code
     *   function main(c)
     *  {
     *       var triggerFactory = mappet.getTriggerFactory();
     *       var trigger = triggerFactory.createTrigger(mappet.getGlobalTrigger("onServerTick"));
     *
     *       var block = triggerFactory.createCommandTriggerBlock();
     *       block.setString("/say 1");
     *       trigger.set(0, block);
     * }
     *  }</pre>
     *
     * @param event
     *
     * @return int
     */
    public Trigger getGlobalTrigger(String event)
    {
        if(event == "onServerTick")
        {
            return Mappet.settings.serverTick;
        }
        else if(event == "playerChat")
        {
            return Mappet.settings.playerChat;
        }
        else if(event == "blockBreak")
        {
            return Mappet.settings.blockBreak;
        }
        else if(event == "blockPlace")
        {
            return Mappet.settings.blockPlace;
        }
        else if(event == "entityDamaged")
        {
            return Mappet.settings.entityDamaged;
        }
        else if(event == "entityAttacked")
        {
            return Mappet.settings.entityAttacked;
        }
        else if(event == "playerItemInteract")
        {
            return Mappet.settings.playerItemInteract;
        }
        else if(event == "blockClick")
        {
            return Mappet.settings.blockClick;
        }
        else if(event == "blockInteract")
        {
            return Mappet.settings.blockInteract;
        }
        else if(event == "playerEntityInteract")
        {
            return Mappet.settings.playerEntityInteract;
        }
        else if(event == "playerLogOut")
        {
            return Mappet.settings.playerLogOut;
        }
        else if(event == "playerRespawn")
        {
            return Mappet.settings.playerRespawn;
        }
        else if(event == "playerItemPickup")
        {
            return Mappet.settings.playerItemPickup;
        }
        else if(event == "playerItemToss")
        {
            return Mappet.settings.playerItemToss;
        }
        else if(event == "playerEntityLeash")
        {
            return Mappet.settings.playerEntityLeash;
        }
        else if(event == "onPlayerTick")
        {
            return Mappet.settings.playerEntityLeash;
        }

        Dispatcher.sendToServer(new PacketServerSettings(this.settings.serializeNBT()));

        return null;
    }
}