package callout.OmniKings.proxy;

import callout.OmniKings.entities.EntitiesOmni;
import callout.OmniKings.event.RenderPlayerEventOmniKings;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.common.MinecraftForge;

/**
 * Do Things on client side
 * 
 * @author Hedaox
 */

public class ClientProxy extends CommonProxy{


    RenderPlayerEventOmniKings RPEOKHandler = new RenderPlayerEventOmniKings();

    public void preInit(FMLPreInitializationEvent $e) 
    {
        MinecraftForge.EVENT_BUS.register(RPEOKHandler);

        super.preInit($e);
    }

    public void init(FMLInitializationEvent $e)
    {
    	super.init($e);

        EntitiesOmni.client();
    }

	public void postInit(FMLPostInitializationEvent $e)
    {
    	super.postInit($e);
    }

}
