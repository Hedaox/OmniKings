package callout.OmniKings;

import callout.OmniKings.core.OmniKingsCoreH;
import callout.OmniKings.lib.ModVars;
import callout.OmniKings.proxy.CommonProxy;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.common.config.Configuration;
/*
 * Main code
 */

@Mod(modid = ModVars.MOD_ID, name = ModVars.MOD_name)
public class Main {

    @Mod.Instance
    public static Main instance = new Main();

    @SidedProxy(clientSide = "callout.OmniKings.proxy.ClientProxy", serverSide = "callout.OmniKings.proxy.ServerProxy")
    public static CommonProxy proxy;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent $e) {
        proxy.preInit($e);

        OmniKingsCoreH.config = new Configuration($e.getSuggestedConfigurationFile());
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