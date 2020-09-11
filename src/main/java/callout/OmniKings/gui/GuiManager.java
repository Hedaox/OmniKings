package callout.OmniKings.gui;

import JinRyuu.JRMCore.JRMCoreGuiScreen;
import JinRyuu.JRMCore.JRMCoreH;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraftforge.client.event.GuiOpenEvent;

public class GuiManager {

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public void openGui(GuiOpenEvent event) {
        if(event.gui instanceof JRMCoreGuiScreen)
        {
            if(JRMCoreH.Races.length == 8 && JRMCoreH.vlblRSklsMR.length == 8 && JRMCoreH.vlblRSklsLvl.length == 8)
            {
                event.gui = new OmniKingsGuiScreen(0);
            }
            else
            {
                event.setCanceled(true);
            }
        }
    }
}
