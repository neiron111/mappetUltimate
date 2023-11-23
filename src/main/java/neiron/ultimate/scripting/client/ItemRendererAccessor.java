package neiron.ultimate.scripting.client;

import net.minecraft.util.EnumHandSide;

public interface ItemRendererAccessor {
    void invokeRenderArmFirstPerson(float a, float b, EnumHandSide handSide);
    float getEquippedProgressMainHand();
    float getPrevEquippedProgressMainHand();
}