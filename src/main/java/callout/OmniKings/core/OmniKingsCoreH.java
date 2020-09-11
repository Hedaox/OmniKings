package callout.OmniKings.core;

import JinRyuu.DragonBC.common.DBCClient;
import JinRyuu.DragonBC.common.DBCKiTech;
import JinRyuu.JRMCore.*;
import callout.OmniKings.config.OmniKingsConfig;
import callout.OmniKings.event.RenderPlayerEventOmniKings;
import callout.OmniKings.network.*;
import callout.OmniKings.proxy.ClientProxy;
import callout.OmniKings.proxy.ServerProxy;
import callout.OmniKings.utils.Utils;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.common.config.Configuration;

import java.util.List;

import static JinRyuu.JRMCore.JRMCoreH.*;
import static JinRyuu.JRMCore.JRMCoreHDBC.Dscndng;

public class OmniKingsCoreH {

    public static Configuration config = null;
    public static float[][] TransBaStBnP = new float[10][];
    public static float[][] TransBaStBnPO = new float[10][];
    public static int[][] TransBaStBnF = { { 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0 }, { 150, 150, 0, 150, 0, 0 } };
    public static float[] TransBaRgnO = { 1.0F, 1.0F, 1.0F, 1.0F, -0.5F};
    public static float[] TransBaRgn = { 1.0F, 1.0F, 1.0F, 1.0F, -0.5F};
    private static int bioAndroidState = 0;
    private final int slowTickMax = 1000;
    private int slowTick = 0;

    //Server
    @SubscribeEvent
    public void updateEventOnServerTick(TickEvent.ServerTickEvent event) throws Exception {
        //is Server ?
        if(!MinecraftServer.getServer().getEntityWorld().isRemote) {
            if (slowTick > slowTickMax) {
                ServerProxy.network.sendToAll(new MessageSendMaxAttrC(JRMCoreConfig.ctmx));
                for (int i = 0; i < MinecraftServer.getServer().getConfigurationManager().playerEntityList.size(); i++) {
                    EntityPlayer player = (EntityPlayer) MinecraftServer.getServer().getConfigurationManager().playerEntityList.get(i);
                    ServerProxy.network.sendToAll(new MessageSendStringRelease(player.getUniqueID().toString() + "#" + JRMCoreH.getByte(player, "jrmcRelease")));
                }
                slowTick = 0;
            } else {
                slowTick++;
            }

            if (JRMCoreH.Races.length < 8 || JRMCoreH.vlblRSklsMR.length < 8 || JRMCoreH.vlblRSklsLvl.length < 8 || JRMCoreH.trans.length < 8) {
                Utils.setPrivateFinalStatic(JRMCoreH.class.getDeclaredField("Races"), new String[]{"Human", "Saiyan", "Half-Saiyan", "Namekian", "Arcosian", "Bio-Android", "Buu race", "Zen-oh race"});
                Utils.setPrivateFinalStatic(JRMCoreH.class.getDeclaredField("RaceAllow"), new String[]{"All", "DBC", "DBC", "DBC", "DBC", "DBC", "DBC", "DBC"});
                Utils.setPrivateFinalStatic(JRMCoreH.class.getDeclaredField("RaceCanHaveHair"), new String[]{"H", "H", "H", "A", "R", "R", "A", "A"});
                Utils.setPrivateFinalStatic(JRMCoreH.class.getDeclaredField("RaceCanHavePwr"), new String[]{"0123", "012", "012", "012", "012", "012", "012", "012", "012"});
                Utils.setPrivateFinalStatic(JRMCoreH.class.getDeclaredField("RaceCustomSkin"), new int[]{2, 2, 2, 1, 1, 1, 1, 1});
                Utils.setPrivateFinalStatic(JRMCoreH.class.getDeclaredField("RaceHairColor"), new int[]{-1, 0, -1, -1, -1, -1, -1, -1});
                Utils.setPrivateFinalStatic(JRMCoreH.class.getDeclaredField("RaceGenders"), new int[]{2, 2, 2, 1, 2, 1, 1, 1});
                Utils.setPrivateFinalStatic(JRMCoreH.class.getDeclaredField("customSknLimits"), new int[][]{{1, 1, 5, 5, 6, 2}, {1, 1, 5, 5, 6, 0}, {1, 2, 5, 5, 6, 2}, {3, 3, 5, 5, 3, 2}, {3, 4, 5, 6, 2, 2}, {1, 4, 1, 1, 2, 2}, {1, 1, 1, 1, 1, 1}, {1, 1, 1, 1, 1, 1}});
                Utils.setPrivateFinalStatic(JRMCoreH.class.getDeclaredField("customSknLimitsBCP"), new int[]{7, 7, 7, 3, 3, 3, 3, 3});
                Utils.setPrivateFinalStatic(JRMCoreH.class.getDeclaredField("defbodycols"), new int[][][]{
                        {{16297621, 6498048}, {16297621, 6498048}, {16297621, 6498048}, {5095183, 13796998, 12854822}, {15460342, 16111595, 8533141, 16550015}, {756738, 11915265, 12854822, 69956}, {0}, {0}},
                        {{15979704, 6498048}, {15979704, 6498048}, {15979704, 6498048}, {4566029, 14191242, 14363435}, {15460342, 15188457, 287340, 16550015}, {1348855, 14676490, 16471299, 75575}, {0}, {0}},
                        {{13014656, 6498048}, {13014656, 6498048}, {13014656, 6498048}, {4896782, 12875121, 12920870}, {15460342, 10442657, 3625381, 13125463}, {13375236, 8047641, 14237907, 4457574}, {0}, {0}},
                        {{12622942, 6498048}, {12622942, 6498048}, {12622942, 6498048}, {0}, {0}, {0}, {0}, {0}},
                        {{10112303, 6498048}, {10112303, 6498048}, {10112303, 6498048}, {0}, {0}, {0}, {0}, {0}},
                        {{7225375, 6498048}, {7225375, 6498048}, {7225375, 6498048}, {0}, {0}, {0}, {0}, {0}},
                        {{3677711, 6498048}, {3677711, 6498048}, {3677711, 6498048}, {0}, {0}, {0}, {0}, {0}}
                });
                Utils.setPrivateFinalStatic(JRMCoreH.class.getDeclaredField("defeyecols"), new int[][]{{1, 1, 1, 1, 14617612, 1, 1, 1}, {4896782, 1, 4896782, 4896782, 1, 1, 1, 1}, {14617612, 1, 14617612, 14617612, 4896782, 1, 1, 1}});
                Utils.setPrivateFinalStatic(JRMCoreH.class.getDeclaredField("TransNms"), new String[][]{{"Base", "HForm1", "HForm2", "HFormG"}, {"Base", "Super", "SuperG2", "SuperG3", "SuperFP", "Super2", "Super3", "Oozaru", "OozaruS", "SuperG", "SuperB", "SuperGR", "SuperL", "SuperLB", "Super4", "SuperBS"}, {"Base", "Super", "SuperG2", "SuperG3", "SuperFP", "Super2", "Super3", "Oozaru", "OozaruS", "SuperG", "SuperB", "SuperGR", "SuperL", "SuperLB", "Super4", "SuperBS"}, {"Base", "NForm1", "NForm2", "NFormG"}, {"Form0", "Form1", "Form2", "Form3", "Base", "Form5", "Form6", "FormG"}, {"Base", "Semi-Perfect", "Perfect", "Full-Power", "God"}, {"Base", "Super", "God"}, {"Base", "Omni"}});
                Utils.setPrivateFinalStatic(JRMCoreH.class.getDeclaredField("vlblRSklsNms"), new String[]{"HiddenPotential", "SuperForm", "SuperForm", "PowerBoost", "Transformations", "Perfectionism", "Buu", "Zen-oh"});
                Utils.setPrivateFinalStatic(JRMCoreH.class.getDeclaredField("attrStart"), new int[][][]{{{10}, {10}, {10}}, {{10, 15, 10, 5, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10}, {10, 10, 10, 5, 5, 10, 10, 10, 10, 10, 10, 10, 10, 10}, {10, 10, 10, 7, 15, 10, 10, 10, 10, 10, 10, 10, 10, 10}, {10, 15, 15, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10}, {10, 5, 10, 15, 5, 10, 10, 10, 10, 10, 10, 10, 10, 10}, {10, 5, 5, 18, 15, 10, 10, 10, 10, 10, 10, 10, 10, 10}}, {{10}, {10}, {10}, {10}, {10}, {10}}, {{1}, {1}, {1}}});
                Utils.setPrivateFinalStatic(JRMCoreH.class.getDeclaredField("trans"), new String[][]{{"Base", "Full", "Buffed", "God"}, {"Base", "SS", "SSG2", "SSG3", "SSFullPow", "SS2", "SS3", "Oozaru", "Golden", "SSGod", "SSB", "SSGodR", "LSS", "LSS2", "SS4", "SSBE"}, {"Base", "SS", "SSG2", "SSG3", "SSFullPow", "SS2", "SS3", "Oozaru", "Golden", "SSGod", "SSB", "SSGodR", "LSS", "LSS2", "SS4", "SSBE"}, {"Base", "Full", "Giant", "God"}, {"Form0", "Form1", "Form2", "Form3", "Base", "Form5", "Ultimate", "God"}, {"Base", "Semi-Perfect", "Perfect", "Full-Power", "God"}, {"Base", "Super", "God"}, {"Base", "Omni"}});
                Utils.setPrivateFinalStatic(JRMCoreH.class.getDeclaredField("statIncBonusRaceDBC"), new int[][]{{0, 30, 15, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 20, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 10, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {30, 0, 15, 0, 10, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {10, 20, 15, 30, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {10, 0, 5, 20, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {10, 0, 5, 0, 30, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 10, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}});

                int[][] vlblRSklsLvlOmni = new int[8][];
                int[][] vlblRSklsLvlOOmni = new int[8][];
                double[][] vlblRSklsMROmni = new double[8][];
                double[][] vlblRSklsMROOmni = new double[8][];

                for (int i = 0; i < JRMCoreH.vlblRSklsLvl.length; i++) {
                    vlblRSklsLvlOmni[i] = JRMCoreH.vlblRSklsLvl[i];
                }

                for (int i = 0; i < JRMCoreH.vlblRSklsLvlO.length; i++) {
                    vlblRSklsLvlOOmni[i] = JRMCoreH.vlblRSklsLvlO[i];
                }

                for (int i = 0; i < JRMCoreH.vlblRSklsMR.length; i++) {
                    vlblRSklsMROmni[i] = JRMCoreH.vlblRSklsMR[i];
                }

                for (int i = 0; i < JRMCoreH.vlblRSklsMRO.length; i++) {
                    vlblRSklsMROOmni[i] = JRMCoreH.vlblRSklsMRO[i];
                }

                JRMCoreH.vlblRSklsLvl = vlblRSklsLvlOmni;
                JRMCoreH.vlblRSklsLvlO = vlblRSklsLvlOOmni;
                JRMCoreH.vlblRSklsMR = vlblRSklsMROmni;
                JRMCoreH.vlblRSklsMRO = vlblRSklsMROOmni;
                JRMCoreH.TransHmRgnO = new float[]{1.0F, -0.5F, -0.25F, -0.5F, 0.0F};
                JRMCoreH.TransHmRgn = new float[]{1.0F, -0.5F, -0.25F, -0.5F, 0.0F};

                OmniKingsConfig.init(config);
            }

            TransBaStBnP = TransBaStBnPO;

            for (Object player:MinecraftServer.getServer().getConfigurationManager().playerEntityList) {
                if(JRMCoreH.getByte((EntityPlayer)player, "jrmcRace") > 4)
                {
                    ManageEnergy((EntityPlayer)player);
                }
            }
        }
    }

    private void ManageEnergy(EntityPlayer player) {
        int rls = JRMCoreH.getByte(player, "jrmcRelease");
        int pwr = JRMCoreH.getByte(player, "jrmcPwrtyp");
        int rc = JRMCoreH.getByte(player, "jrmcRace");
        int st = JRMCoreH.getByte(player, "jrmcState");
        int cl = JRMCoreH.getByte(player, "jrmcClass");
        float RREnergy =  JRMCoreH.stat(pwr, 10, 100, rc, cl, 0.0F);
        int resrv = JRMCoreH.getInt(player, "jrmcArcRsrv");
        if (curEnergy >= 0 && player.ticksExisted%100 == 1)
        {
            //Bio Android
            if(rc == 5)
            {
                int currentEnergy = JRMCoreH.getInt(player, "jrmcEnrgy");
                int energyToSet = (int) (currentEnergy + ((maxEnergy/10)*TransBaRgn[st])*curRelease/100);
                if(energyToSet > 0 || energyToSet < maxEnergy)
                {
                    JRMCoreH.setInt(energyToSet, player, "jrmcEnrgy");
                }
                else if(energyToSet <= 0)
                {
                    JRMCoreH.setInt(0, player, "jrmcEnrgy");
                }
                else if(energyToSet >= maxEnergy)
                {
                    JRMCoreH.setInt(maxEnergy, player, "jrmcEnrgy");
                }
            }
        }
    }

    public static double TransBaRegen(int[] curAtr, double r, int st, String SklX, int cr, int resrv)
    {
        double c = 0.0D;
        if (st > 0)
        {
            int might = (int)((TransPwrModAtr(curAtr, 0, st, 0, 1, SklX, cr, resrv, false, false, false, false, 1, null, false) - curAtr[0]) * 0.4F + (TransPwrModAtr(curAtr, 1, st, 0, 1, SklX, cr, resrv, false, false, false, false, 1, null, false) - curAtr[1]) * 0.25F + (TransPwrModAtr(curAtr, 3, st, 0, 1, SklX, cr, resrv, false, false, false, false, 1, null, false) - curAtr[3]) * 0.35F);

            c = might * TransBaRgn[st];
        }
        else
        {
            c = r * TransBaRgn[st];
        }
        return c;
    }

    //Client
    @SubscribeEvent
    public void updateEventOnPlayerTick(TickEvent.PlayerTickEvent event) throws Exception {
        //is Client ?
        if(event.player.worldObj.isRemote)
        {
            if(!Minecraft.getMinecraft().isSingleplayer() && TransBaStBnPO[0] == null)
            {
                ClientProxy.network.sendToServer(new MessageAskServerSomething("TransBAStBnP"));
            }
            else if(TransBaStBnPO[0] != null)
            {
                TransBaStBnP = TransBaStBnPO;
            }

            if (JRMCoreH.Races.length < 8 || JRMCoreH.vlblRSklsMR.length < 8 || JRMCoreH.vlblRSklsLvl.length < 8) {
                Utils.setPrivateFinalStatic(JRMCoreH.class.getDeclaredField("Races"), new String[]{"Human", "Saiyan", "Half-Saiyan", "Namekian", "Arcosian", "Bio-Android", "Buu race", "Zen-oh race"});
                Utils.setPrivateFinalStatic(JRMCoreH.class.getDeclaredField("RaceAllow"), new String[]{"All", "DBC", "DBC", "DBC", "DBC", "DBC", "DBC", "DBC"});
                Utils.setPrivateFinalStatic(JRMCoreH.class.getDeclaredField("RaceCanHaveHair"), new String[]{"H", "H", "H", "A", "R", "R", "A", "A"});
                Utils.setPrivateFinalStatic(JRMCoreH.class.getDeclaredField("RaceCanHavePwr"), new String[]{"0123", "012", "012", "012", "012", "012", "012", "012", "012"});
                Utils.setPrivateFinalStatic(JRMCoreH.class.getDeclaredField("RaceCustomSkin"), new int[]{2, 2, 2, 1, 1, 1, 1, 1});
                Utils.setPrivateFinalStatic(JRMCoreH.class.getDeclaredField("RaceHairColor"), new int[]{-1, 0, -1, -1, -1, -1, -1, -1});
                Utils.setPrivateFinalStatic(JRMCoreH.class.getDeclaredField("RaceGenders"), new int[]{2, 2, 2, 1, 2, 1, 1, 1});
                Utils.setPrivateFinalStatic(JRMCoreH.class.getDeclaredField("customSknLimits"), new int[][]{{1, 1, 5, 5, 6, 2}, {1, 1, 5, 5, 6, 0}, {1, 2, 5, 5, 6, 2}, {3, 3, 5, 5, 3, 2}, {3, 4, 5, 6, 2, 2}, {1, 4, 1, 1, 2, 2}, {1, 1, 1, 1, 1, 1}, {1, 1, 1, 1, 1, 1}});
                Utils.setPrivateFinalStatic(JRMCoreH.class.getDeclaredField("customSknLimitsBCP"), new int[]{7, 7, 7, 3, 3, 3, 3, 3});
                Utils.setPrivateFinalStatic(JRMCoreH.class.getDeclaredField("defbodycols"), new int[][][]{
                        {{16297621, 6498048}, {16297621, 6498048}, {16297621, 6498048}, {5095183, 13796998, 12854822}, {15460342, 16111595, 8533141, 16550015}, {756738, 11915265, 12854822, 69956}, {0}, {0}},
                        {{15979704, 6498048}, {15979704, 6498048}, {15979704, 6498048}, {4566029, 14191242, 14363435}, {15460342, 15188457, 287340, 16550015}, {1348855, 14676490, 16471299, 75575}, {0}, {0}},
                        {{13014656, 6498048}, {13014656, 6498048}, {13014656, 6498048}, {4896782, 12875121, 12920870}, {15460342, 10442657, 3625381, 13125463}, {13375236, 8047641, 14237907, 4457574}, {0}, {0}},
                        {{12622942, 6498048}, {12622942, 6498048}, {12622942, 6498048}, {0}, {0}, {0}, {0}, {0}},
                        {{10112303, 6498048}, {10112303, 6498048}, {10112303, 6498048}, {0}, {0}, {0}, {0}, {0}},
                        {{7225375, 6498048}, {7225375, 6498048}, {7225375, 6498048}, {0}, {0}, {0}, {0}, {0}},
                        {{3677711, 6498048}, {3677711, 6498048}, {3677711, 6498048}, {0}, {0}, {0}, {0}, {0}}
                });
                Utils.setPrivateFinalStatic(JRMCoreH.class.getDeclaredField("defeyecols"), new int[][]{{1, 1, 1, 1, 14617612, 1, 1, 1}, {4896782, 1, 4896782, 4896782, 1, 1, 1, 1}, {14617612, 1, 14617612, 14617612, 4896782, 1, 1, 1}});
                Utils.setPrivateFinalStatic(JRMCoreH.class.getDeclaredField("TransNms"), new String[][]{{"Base", "HForm1", "HForm2", "HFormG"}, {"Base", "Super", "SuperG2", "SuperG3", "SuperFP", "Super2", "Super3", "Oozaru", "OozaruS", "SuperG", "SuperB", "SuperGR", "SuperL", "SuperLB", "Super4", "SuperBS"}, {"Base", "Super", "SuperG2", "SuperG3", "SuperFP", "Super2", "Super3", "Oozaru", "OozaruS", "SuperG", "SuperB", "SuperGR", "SuperL", "SuperLB", "Super4", "SuperBS"}, {"Base", "NForm1", "NForm2", "NFormG"}, {"Form0", "Form1", "Form2", "Form3", "Base", "Form5", "Form6", "FormG"}, {"Base", "Semi-Perfect", "Perfect", "Full-Power", "God"}, {"Base", "Super", "God"}, {"Base", "Omni"}});
                Utils.setPrivateFinalStatic(JRMCoreH.class.getDeclaredField("vlblRSklsNms"), new String[]{"HiddenPotential", "SuperForm", "SuperForm", "PowerBoost", "Transformations", "Perfectionism", "Buu", "Zen-oh"});
                Utils.setPrivateFinalStatic(JRMCoreH.class.getDeclaredField("attrStart"), new int[][][]{{{10}, {10}, {10}}, {{10, 15, 10, 5, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10}, {10, 10, 10, 5, 5, 10, 10, 10, 10, 10, 10, 10, 10, 10}, {10, 10, 10, 7, 15, 10, 10, 10, 10, 10, 10, 10, 10, 10}, {10, 15, 15, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10}, {10, 5, 10, 15, 5, 10, 10, 10, 10, 10, 10, 10, 10, 10}, {10, 5, 5, 18, 15, 10, 10, 10, 10, 10, 10, 10, 10, 10}}, {{10}, {10}, {10}, {10}, {10}, {10}}, {{1}, {1}, {1}}});
                Utils.setPrivateFinalStatic(JRMCoreH.class.getDeclaredField("trans"), new String[][]{{"Base", "Full", "Buffed", "God"}, {"Base", "SS", "SSG2", "SSG3", "SSFullPow", "SS2", "SS3", "Oozaru", "Golden", "SSGod", "SSB", "SSGodR", "LSS", "LSS2", "SS4", "SSBE"}, {"Base", "SS", "SSG2", "SSG3", "SSFullPow", "SS2", "SS3", "Oozaru", "Golden", "SSGod", "SSB", "SSGodR", "LSS", "LSS2", "SS4", "SSBE"}, {"Base", "Full", "Giant", "God"}, {"Form0", "Form1", "Form2", "Form3", "Base", "Form5", "Ultimate", "God"}, {"Base", "Semi-Perfect", "Perfect", "Full-Power", "God"}, {"Base", "Super", "God"}, {"Base", "Omni"}});
                Utils.setPrivateFinalStatic(JRMCoreH.class.getDeclaredField("statIncBonusRaceDBC"), new int[][]{{0, 30, 15, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 20, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 10, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {30, 0, 15, 0, 10, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {10, 20, 15, 30, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {10, 0, 5, 20, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {10, 0, 5, 0, 30, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 10, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}});

                int[][] vlblRSklsLvlOmni = new int[8][];
                int[][] vlblRSklsLvlOOmni = new int[8][];
                double[][] vlblRSklsMROmni = new double[8][];
                double[][] vlblRSklsMROOmni = new double[8][];

                for (int i = 0; i < JRMCoreH.vlblRSklsLvl.length; i++) {
                    vlblRSklsLvlOmni[i] = JRMCoreH.vlblRSklsLvl[i];
                }

                for (int i = 0; i < JRMCoreH.vlblRSklsLvlO.length; i++) {
                    vlblRSklsLvlOOmni[i] = JRMCoreH.vlblRSklsLvlO[i];
                }

                for (int i = 0; i < JRMCoreH.vlblRSklsMR.length; i++) {
                    vlblRSklsMROmni[i] = JRMCoreH.vlblRSklsMR[i];
                }

                for (int i = 0; i < JRMCoreH.vlblRSklsMRO.length; i++) {
                    vlblRSklsMROOmni[i] = JRMCoreH.vlblRSklsMRO[i];
                }

                JRMCoreH.vlblRSklsLvl = vlblRSklsLvlOmni;
                JRMCoreH.vlblRSklsLvlO = vlblRSklsLvlOOmni;
                JRMCoreH.vlblRSklsMR = vlblRSklsMROmni;
                JRMCoreH.vlblRSklsMRO = vlblRSklsMROOmni;

            }

            transformClient();
        }
    }

    @SideOnly(Side.CLIENT)
    private void transformClient()
    {
        //Player transform regen
        if(JRMCoreH.Race == 5 && (JRMCoreH.TransHmRgn == null || JRMCoreH.TransHmRgn.length < 5))
        {
            JRMCoreH.TransHmRgnO = new float[]{2.0F, 1.5F, 1.0F, -0.5F, -1.0F, -0.5F};
            JRMCoreH.TransHmRgn = new float[]{2.0F, 1.5F, 1.0F, -0.5F, -1.0F, -0.5F};
        }
        else if (JRMCoreH.TransHmRgn == null)
        {
            JRMCoreH.TransHmRgnO = new float[]{1.0F, -0.5F, -0.25F, -0.5F};
            JRMCoreH.TransHmRgn = new float[]{1.0F, -0.5F, -0.25F, -0.5F};
        }

        //Player transformation
        EntityPlayer player = Minecraft.getMinecraft().thePlayer;
        if(JRMCoreH.Race == 5)
        {
            //Player Transformation boost stats
            boolean c = (JRMCoreH.StusEfctsMe(10)) || (JRMCoreH.StusEfctsMe(11));
            if(TransBaStBnP[0] != null)
            {
                int STR = OmniKingsCoreH.TransPwrModAtr(JRMCoreH.PlyrAttrbts, 0, JRMCoreH.State, JRMCoreH.State2, JRMCoreH.Race, JRMCoreH.PlyrSkillX, JRMCoreH.curRelease, JRMCoreH.getArcRsrv(), JRMCoreH.StusEfctsMe(14), JRMCoreH.StusEfctsMe(12), JRMCoreH.StusEfctsMe(13), JRMCoreH.StusEfctsMe(19), JRMCoreH.Pwrtyp, JRMCoreH.PlyrSkills, c);
                int DEX = OmniKingsCoreH.TransPwrModAtr(JRMCoreH.PlyrAttrbts, 1, JRMCoreH.State, JRMCoreH.State2, JRMCoreH.Race, JRMCoreH.PlyrSkillX, JRMCoreH.curRelease, JRMCoreH.getArcRsrv(), JRMCoreH.StusEfctsMe(14), JRMCoreH.StusEfctsMe(12), JRMCoreH.StusEfctsMe(13), JRMCoreH.StusEfctsMe(19), JRMCoreH.Pwrtyp, JRMCoreH.PlyrSkills, c);
                int WIL = OmniKingsCoreH.TransPwrModAtr(JRMCoreH.PlyrAttrbts, 3, JRMCoreH.State, JRMCoreH.State2, JRMCoreH.Race, JRMCoreH.PlyrSkillX, JRMCoreH.curRelease, JRMCoreH.getArcRsrv(), JRMCoreH.StusEfctsMe(14), JRMCoreH.StusEfctsMe(12), JRMCoreH.StusEfctsMe(13), JRMCoreH.StusEfctsMe(19), JRMCoreH.Pwrtyp, JRMCoreH.PlyrSkills, c);

                JRMCoreH.setInt( STR, player, JRMCoreH.AttrbtNbtI[0]);
                JRMCoreH.setInt( DEX, player, JRMCoreH.AttrbtNbtI[1]);
                JRMCoreH.setInt( WIL, player, JRMCoreH.AttrbtNbtI[3]);
            }

            //Give player new state
            if ((JRMCoreH.PlyrSkillX != null) && (!JRMCoreH.PlyrSkillX.contains("pty")) && (JRMCoreH.PlyrSkillX.length() > 1)) {
                //First three state for Bio Android
                int stateSkillLvl = Integer.parseInt(JRMCoreH.PlyrSkillX.substring(2));
                if (stateSkillLvl > 3) {
                    stateSkillLvl = 3;
                }
                if (stateSkillLvl != bioAndroidState || (JRMCoreH.State != bioAndroidState && JRMCoreH.State != 4))
                {
                    if (bioAndroidState < Integer.parseInt(JRMCoreH.PlyrSkillX.substring(2)) && Integer.parseInt(JRMCoreH.PlyrSkillX.substring(2)) < 4) {
                        ClientProxy.network.sendToServer(new MessageSendStringSoundToPlay("jinryuudragonbc:1610.sss"));
                    }
                    ClientProxy.network.sendToServer(new MessageSendStringState(Minecraft.getMinecraft().thePlayer.getUniqueID().toString() + "#" + stateSkillLvl));
                    bioAndroidState = stateSkillLvl;
                }

                //God State
                int godSkillLvl = -1;
                for (String skillStr: PlyrSkills) {
                    if(skillStr.startsWith("GF"))
                    {
                        godSkillLvl = Integer.parseInt(skillStr.substring(2));
                    }
                }
                if (Integer.parseInt(JRMCoreH.PlyrSkillX.substring(2)) == 4 && godSkillLvl >= 0 && JRMCoreH.State == 3) {
                    //Transforming into God state
                    if (JRMCoreKeyHandler.KiAscend.getIsKeyPressed()) {
                        JRMCoreH.TransSaiCurRg++;
                        DBCKiTech.ascending = true;
                        // Transform
                        if (JRMCoreH.TransSaiCurRg >= 100) {
                            ClientProxy.network.sendToServer(new MessageSendStringSoundToPlay("jinryuudragonbc:1610.sss"));
                            DBCKiTech.ascending = false;
                            JRMCoreH.TransSaiCurRg = 0;

                            ClientProxy.network.sendToServer(new MessageSendStringState(Minecraft.getMinecraft().thePlayer.getUniqueID().toString() + "#" + 4));
                        }
                    }
                    //Stop transforming
                    else {
                        if (JRMCoreH.TransSaiCurRg > 0) {
                            JRMCoreH.TransSaiCurRg--;
                        }
                        if (OmniKingAura.aura != null && !OmniKingAura.aura.isDead) {
                            OmniKingAura.aura.setDead();
                        }
                        DBCKiTech.ascending = false;
                    }
                }
                //Bio-android Descend
                if (JRMCoreKeyHandler.KiDescend.getIsKeyPressed()) {
                    //God
                    if (JRMCoreH.State == 4) {
                        ClientProxy.network.sendToServer(new MessageSendStringSoundToPlay("jinryuudragonbc:DBC.descend"));

                        ClientProxy.network.sendToServer(new MessageSendStringState(Minecraft.getMinecraft().thePlayer.getUniqueID().toString() + "#" + 3));
                    }
                    //others
                    else
                    {
                        if(JRMCoreH.curRelease > 0)
                        {
                            if ((!JRMCoreH.StusEfctsMe(13)) && (!JRMCoreH.StusEfctsMe(19)) && (((JRMCoreH.State2 == 0) && (JRMCoreH.State == 0)) || ((JRMCoreH.State2 == 0) && (JRMCoreH.Race == 4) && (JRMCoreH.State <= 4)))) {
                                JRMCoreH.Rls((byte)0);
                            }
                            JRMCoreH.kiInSuper = 0;
                            DBCKiTech.ascend = 0;
                            DBCKiTech.ptime = 0;
                            DBCKiTech.pup = 0;
                            ClientProxy.network.sendToServer(new MessageSendStringSoundToPlay("jinryuudragonbc:DBC.descend"));
                            DBCKiTech.DashKi(false);
                            DBCKiTech.Dscndng(3);
                        }
                    }
                }
            }
            //Meditation charge
            int meditationSkillLvl = -1;
            for (String skillStr : PlyrSkills) {
                if(skillStr.startsWith("MD"))
                {
                    meditationSkillLvl = Integer.parseInt(skillStr.substring(2));
                }
            }
            if (meditationSkillLvl >= 0 && DBCKiTech.releasing && player.ticksExisted%20 == 1 && curStamina > 0)
            {
                int energyToAdd = (int) (curEnergy + ((maxEnergy/100))*(meditationSkillLvl+1));
                int staminaToLoose = (int) (curStamina - ((maxStamina/100))*((meditationSkillLvl+1)*2));
                if(curStamina < energyToAdd)
                {
                    energyToAdd = curStamina;
                }
                if(energyToAdd < maxEnergy)
                {
                    ClientProxy.network.sendToServer(new MessageAskServerSomething("SetInteger#" + player.getUniqueID().toString() + "#jrmcEnrgy#" + energyToAdd));
                }
                else
                {
                    ClientProxy.network.sendToServer(new MessageAskServerSomething("SetInteger#" + player.getUniqueID().toString() + "#jrmcEnrgy#" + maxEnergy));
                }

                if(staminaToLoose > 0)
                {
                    ClientProxy.network.sendToServer(new MessageAskServerSomething("SetInteger#" + player.getUniqueID().toString() + "#jrmcStamina#" + staminaToLoose));
                }
                else
                {
                    ClientProxy.network.sendToServer(new MessageAskServerSomething("SetInteger#" + player.getUniqueID().toString() + "#jrmcStamina#" + 0));
                }
            }
            //OmniKingAura.auras
            switch (JRMCoreH.State)
            {
                case 3:
                    if(DBCKiTech.releasing || DBCKiTech.turbo || (DBCKiTech.ascending && JRMCoreH.TransSaiCurRg > 0)) {
                        OmniKingAura.setAuraLightning(player);
                        if(DBCKiTech.ascending && JRMCoreH.TransSaiCurRg > 0)
                        {
                            OmniKingAura.setAura(player, -1);
                            OmniKingAura.setAuraRing(player, -1);
                        }
                    }
                    else {
                        if (OmniKingAura.auraLightning != null && !OmniKingAura.auraLightning.isDead) {
                            OmniKingAura.auraLightning.setDead();
                        }
                    }
                    break;
                case 4:
                    OmniKingAura.removeAura(player);
                    if(DBCKiTech.releasing || DBCKiTech.turbo || DBCKiTech.dodge_forwHold) {
                        OmniKingAura.setAuraGod(player);
                        if(DBCKiTech.releasing)
                        {
                            OmniKingAura.setAuraRing(player, 16646144);
                        }
                    }
                    else {
                        if (OmniKingAura.auraGod != null && !OmniKingAura.auraGod.isDead) {
                            OmniKingAura.auraGod.setDead();
                        }
                    }
                    break;
            }
        }
    }
    public static int TransBaPwr(int[] curAtr, int atr, int st, int rg, int e, int skl, boolean y, int skls, boolean v)
    {
        double sbp = ev_oob(TransBaStBnP, st, atr);
        double sbf = ev_oob(TransBaStBnF, st, atr);

        int m = rtXq4V(v);m = m > 1000 ? 1000 : m;
        int per = (int)(sbp * curAtr[atr]);
        per = (y) && (skls > 0) ? (int)(TransBaStBnP[3][atr] * curAtr[atr] * (1.0D + skl * 0.02D)) : per;
        int flt = (int)(curAtr[atr] + sbf * m * 0.0010000000474974513D);
        flt = (y) && (skls > 0) ? (int)(curAtr[atr] + TransBaStBnF[3][atr] * (1.0D + skl * 0.02D) * m * 0.0010000000474974513D) : flt;
        flt = flt > rtXq4V(v) ? rtXq4V(v) : flt;
        per = (per > flt) || (sbf == 0.0D) || (atr == 4) ? per : flt;
        per = ArcRsrvPowBst(per, st, rg, e);
        return per;
    }

    public static int TransPwrModAtr(int[] curAtr, int atr, int st, int st2, int r, String SklX, int cr, int e, boolean y, boolean j, boolean m, boolean n, int w, String[] Skls, boolean v)
    {
        int skl = w == 1 ? SklLvlX(1, SklX) - 1 : 0;
        int skls = w == 1 ? SklLvl(10, 1, Skls) : 0;
        int rslt = 0;
        switch (r)
        {
            case 5:
                rslt = TransBaPwr(curAtr, atr, st, cr, e, skl, m, skls, v); break;
            default:
                rslt = curAtr[atr];
        }
        if ((w == 1) && (n)) {
            rslt = (int)(rslt * (TransMngDmg[0] * 1.0D));
        } else if ((w == 1) && (st2 < TransKaiDmg.length)) {
            rslt = (int)(rslt * (TransKaiDmg[st2] * 1.0D) + (j ? rslt * JRMCoreConfig.mjn * 0.01F : 0.0F) + ((y) && ((lgndb(r, st)) || (m)) ? rslt * JRMCoreConfig.lgnd * 0.01F : 0.0F));
        }
        if ((w == 2) && (st2 < TransGtsDmg.length)) {
            rslt = (int)(rslt * (TransGtsDmg[st2] * 1.0D));
        }
        if (!JRMCoreConfig.OverAtrLimit) {
            rslt = rslt > rtXq4V(v) ? rtXq4V(v) : rslt;
        }
        rslt = (int)(rslt > Double.MAX_VALUE ? Double.MAX_VALUE : rslt);
        return rslt;
    }
}
