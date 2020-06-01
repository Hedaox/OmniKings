package callout.DBCOmniverse;

import callout.DBCOmniverse.lib.ModVars;
import callout.DBCOmniverse.proxy.CommonProxy;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
/*
 * Main code
 */

@Mod(modid = ModVars.MOD_ID, name = ModVars.MOD_name)
public class Main {

    @Mod.Instance
    public static Main instance = new Main();

    @SidedProxy(clientSide = "callout.DBCOmniverse.proxy.ClientProxy", serverSide = "callout.DBCOmniverse.proxy.ServerProxy")
    public static CommonProxy proxy;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent $e) {
        proxy.preInit($e);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent $e) {
        proxy.init($e);

    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent $e) {
        proxy.postInit($e);

    }
}