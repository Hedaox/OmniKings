package callout.OmniKings.proxy;

import callout.OmniKings.core.OmniKingsCoreH;
import callout.OmniKings.event.AttackEventOmniKings;
import callout.OmniKings.gui.GuiManager;
import callout.OmniKings.lib.ModVars;
import callout.OmniKings.network.*;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;
import net.minecraftforge.common.MinecraftForge;

/**
 * Do Things on server and client side at the same time
 * 
 * @author Hedaox
 */
public class CommonProxy {

    AttackEventOmniKings EAHandler = new AttackEventOmniKings();

    OmniKingsCoreH OKCHHandler = new OmniKingsCoreH();

    GuiManager GMHandler = new GuiManager();

    public static SimpleNetworkWrapper network;
    
    public void preInit(FMLPreInitializationEvent $e) 
    {

        MinecraftForge.EVENT_BUS.register(EAHandler);

        MinecraftForge.EVENT_BUS.register(GMHandler);

        FMLCommonHandler.instance().bus().register(OKCHHandler);

        network = NetworkRegistry.INSTANCE.newSimpleChannel(ModVars.MOD_ID + " Channel 1");

        //Client to Server
        network.registerMessage(MessageSendStringState.MyMessageHandler.class, MessageSendStringState.class, 0, Side.SERVER);
        network.registerMessage(MessageSendStringRelease.MyMessageHandler.class, MessageSendStringRelease.class, 1, Side.SERVER);
        network.registerMessage(MessageSendStringSoundToPlay.MyMessageHandler.class, MessageSendStringSoundToPlay.class, 2, Side.SERVER);
        network.registerMessage(MessageAskServerSomething.MyMessageHandler.class, MessageAskServerSomething.class, 3, Side.SERVER);

        //Server to Client
        network.registerMessage(MessageSendMaxAttrC.MyMessageHandlerC.class, MessageSendMaxAttrC.class, 4, Side.CLIENT);
        network.registerMessage(MessageSendClientSomethingC.MyMessageHandlerC.class, MessageSendClientSomethingC.class, 5, Side.CLIENT);
        network.registerMessage(MessageSendStringStateC.MyMessageHandlerC.class, MessageSendStringStateC.class, 6, Side.CLIENT);
    }

    public void init(FMLInitializationEvent $e)
    {
        
    }

    public void postInit(FMLPostInitializationEvent $e)
    {

    }
}


