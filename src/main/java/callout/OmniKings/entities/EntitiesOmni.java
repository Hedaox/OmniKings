package callout.OmniKings.entities;

import callout.OmniKings.render.RenderAuraLightning;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public final class EntitiesOmni {
    @SideOnly(Side.CLIENT)
    public static void client()
    {
        RenderingRegistry.registerEntityRenderingHandler(EntityAuraLightning.class, new RenderAuraLightning());
    }
}
