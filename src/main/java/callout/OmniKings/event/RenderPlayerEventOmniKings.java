package callout.OmniKings.event;

import JinRyuu.JRMCore.JRMCoreConfig;
import JinRyuu.JRMCore.JRMCoreH;
import callout.OmniKings.render.RenderPlayerOmniKings;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.player.EntityPlayer;

import java.util.HashMap;
import java.util.UUID;

public class RenderPlayerEventOmniKings {

    public static int maxAttr = 500;
    public static HashMap<UUID, Byte> states = new HashMap();
    public static HashMap<UUID, Byte> releases = new HashMap();

    public RenderPlayerEventOmniKings(){
    }

    @SubscribeEvent
    public void preRenderPlayer(net.minecraftforge.client.event.RenderPlayerEvent.Pre event) {
        //is Client side ?
        if(event.entity.worldObj.isRemote) {
            if ((JRMCoreH.plyrs != null) && (JRMCoreH.plyrs.length > 0) && (!event.entityPlayer.isInvisible()) && (JRMCoreH.dnn(1))) {
                for (int pl = 0; pl < JRMCoreH.plyrs.length; pl++) {
                    if ((JRMCoreH.plyrs[pl].equals(event.entityPlayer.getCommandSenderName())) && (JRMCoreH.data1.length >= JRMCoreH.plyrs.length)) {

                        //get dns
                        String[] s = JRMCoreH.data1[pl].split(";");
                        String dns = s[1];
                        //get race
                        int race = JRMCoreH.dnsRace(dns);
                        //Is Race from the mod ?
                        if (race > 4) {
                            //get state
                            int state = 0;
                            int omniState = 0;
                            if(RenderPlayerEventOmniKings.states.get(event.entityPlayer.getUniqueID()) != null)
                            {
                                state = RenderPlayerEventOmniKings.states.get(event.entityPlayer.getUniqueID());
                                omniState = RenderPlayerEventOmniKings.states.get(event.entityPlayer.getUniqueID());
                                if(omniState > 3)
                                {
                                    omniState = 3;
                                }
                            }

                            //the client player
                            EntityPlayer clientPlayer = Minecraft.getMinecraft().thePlayer;

                            //the render player
                            EntityPlayer renderPlayer = event.entityPlayer;

                            //Cancel Player Render
                            event.setCanceled(true);

                            //Get Player Height and width depending on stats
                            int[] PlyrAttrbts = JRMCoreH.PlyrAttrbts(event.entityPlayer);
                            int playerConst = PlyrAttrbts[2];
                            float heightBonus = 0;
                            float widthBonus = -0.05f;
                            if (Minecraft.getMinecraft().isSingleplayer()) {
                                heightBonus = playerConst * (0.25f / JRMCoreConfig.ctmx);
                                heightBonus += 0.10f * JRMCoreH.curRelease*0.01f;
                            } else {
                                heightBonus = playerConst * (0.25f / maxAttr);
                                if(releases.get(event.entityPlayer.getUniqueID()) != null)
                                {
                                    heightBonus += 0.10f * releases.get(event.entityPlayer.getUniqueID())*0.01f;
                                }
                            }
                            if(omniState == 1)
                            {
                                heightBonus += 0.05;
                                widthBonus += 0.10f;
                            }
                            else if(omniState == 2 || state == 4)
                            {
                                heightBonus -= 0.10;
                                widthBonus += 0.05f;
                            }
                            else if(omniState == 3 )
                            {
                                heightBonus -= 0.09f;
                                widthBonus += 0.0555f;
                            }

                            //Get players position
                            double d0 = event.entityPlayer.lastTickPosX + (event.entityPlayer.posX - event.entityPlayer.lastTickPosX);
                            double d1 = event.entityPlayer.lastTickPosY + (event.entityPlayer.posY - event.entityPlayer.lastTickPosY);
                            double d2 = event.entityPlayer.lastTickPosZ + (event.entityPlayer.posZ - event.entityPlayer.lastTickPosZ);

                            //Player Renderer
                            RenderPlayerOmniKings playerRenderer = new RenderPlayerOmniKings(0.90F + heightBonus + widthBonus, 0.90F + heightBonus, 0.90F + heightBonus + widthBonus, state);

                            //Render Bio Android
                            if (renderPlayer.getUniqueID().equals(clientPlayer.getUniqueID())) {
                                playerRenderer.doRender((AbstractClientPlayer) event.entityPlayer, 0, 0, 0, 0, 1.0F);
                            } else {
                                playerRenderer.doRender((AbstractClientPlayer) event.entityPlayer, d0 - RenderManager.renderPosX, d1 - RenderManager.renderPosY, d2 - RenderManager.renderPosZ, 0, 1.0F);
                            }
                        }
                    }
                }
            }
        }
    }
}