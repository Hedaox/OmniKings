package callout.OmniKings.gui;

import JinRyuu.JRMCore.*;
import JinRyuu.JRMCore.items.ItemBarberSnC;

import callout.OmniKings.core.OmniKingsCoreH;
import callout.OmniKings.event.RenderPlayerEventOmniKings;
import callout.OmniKings.network.MessageSendStringState;
import callout.OmniKings.proxy.ClientProxy;
import callout.OmniKings.utils.Utils;
import com.google.common.collect.Multimap;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.*;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.item.ItemSword;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

import java.awt.*;
import java.lang.reflect.Field;
import java.util.*;
import java.util.List;

public class OmniKingsGuiScreen extends JRMCoreGuiScreen {

    private static int tickOmni = 0;
    private int kdfOmni;
    private boolean mousePressedOmni;
    private static float scrollSideOmni = 0.0F;
    private int scrollMouseJumpOmni = 1;
    private int scrollOmni;
    private static String dnsOmni = JRMCoreH.dns;
    private static String dnsSentOmni = "";
    private static String dnsHOmni = JRMCoreH.dnsH;
    private static String dnsHSentOmni = "";
    private static String dnsauOmni = JRMCoreH.dnsau;
    private int yOmni = 0;
    private int xOmni = 0;
    private byte pwrOmni;
    private static boolean[] CanUpgradeOmni = new boolean[]{true, true, true, true, true, true};
    private int mcuOmni;
    private static float ptchOmni = 0.0F;
    private boolean confirmationWindowOmni;
    private int IDtoProcessConfirmForOmni = -1;
    protected static List detailListOmni;
    public static Minecraft mc = Minecraft.getMinecraft();
    public static String icons;

    public OmniKingsGuiScreen(int w) {
        super(w);
    }

    public static void setchangerace() {
        GenderSlcted = JRMCoreH.RaceGenders[RaceSlcted] == 1 ? 0 : GenderSlcted;
        if (RaceSlcted == 5){
            StateSlcted = 0;
        }
        else {
            StateSlcted = 0;
        }

        int bt = JRMCoreH.customSknLimits[RaceSlcted][0];
        BodyTypeSlcted = BodyTypeSlcted > bt - 1 ? bt - 1 : BodyTypeSlcted;
        setchangebodycol();
        bt = JRMCoreH.customSknLimits[RaceSlcted][2];
        FaceNoseSlcted = FaceNoseSlcted > bt - 1 ? bt - 1 : FaceNoseSlcted;
        bt = JRMCoreH.customSknLimits[RaceSlcted][3];
        FaceMouthSlcted = FaceMouthSlcted > bt - 1 ? bt - 1 : FaceMouthSlcted;
        bt = JRMCoreH.customSknLimits[RaceSlcted][4];
        EyesSlcted = EyesSlcted > bt - 1 ? bt - 1 : EyesSlcted;
        setchangeeyecol();
        setdns();
    }

    public static void StateViewF() {
        if (RaceSlcted == 5) {
            ++StateSlcted;
            StateSlcted = StateSlcted > 3 ? 0 : StateSlcted;
        }
        else {
            StateSlcted = 0;
        }
    }

    public static void StateViewB() {
        if (RaceSlcted == 5) {
            --StateSlcted;
            StateSlcted = StateSlcted < 0 ? 3 : StateSlcted;
        }
        else {
            StateSlcted = 0;
        }

    }

    @Override
    public void actionPerformed(GuiButton button)
    {
        if(RaceSlcted == 4 && guiID == 0)
        {
            if(button.id == 1)
            {
                StateSlcted = 0;
                if(!Minecraft.getMinecraft().isSingleplayer())
                {
                    ClientProxy.network.sendToServer(new MessageSendStringState(Minecraft.getMinecraft().thePlayer.getUniqueID().toString() + "#" + StateSlcted));
                }
                else
                {
                    RenderPlayerEventOmniKings.states.put(Minecraft.getMinecraft().thePlayer.getUniqueID(), (byte) StateSlcted);
                    JRMCoreH.setByte( StateSlcted, Minecraft.getMinecraft().thePlayer, "JRMCState");
                    JRMCoreH.State = (byte) StateSlcted;
                }
            }
        }
        if((RaceSlcted > 4 && guiID == 0) || (JRMCoreH.Race > 4 && (guiID == 10 || guiID == 11)))
        {
            if (button.id == 4999)
            {
                StateViewF();
                if(!Minecraft.getMinecraft().isSingleplayer())
                {
                    ClientProxy.network.sendToServer(new MessageSendStringState(Minecraft.getMinecraft().thePlayer.getUniqueID().toString() + "#" + StateSlcted));
                }
                else
                {
                    RenderPlayerEventOmniKings.states.put(Minecraft.getMinecraft().thePlayer.getUniqueID(), (byte) StateSlcted);
                    JRMCoreH.setByte( StateSlcted, Minecraft.getMinecraft().thePlayer, "JRMCState");
                    JRMCoreH.State = (byte) StateSlcted;
                }
                setdns();
            }
            else if (button.id == 60537)
            {
                StateViewB();
                if(!Minecraft.getMinecraft().isSingleplayer())
                {
                    ClientProxy.network.sendToServer(new MessageSendStringState(Minecraft.getMinecraft().thePlayer.getUniqueID().toString() + "#" + StateSlcted));
                }
                else
                {
                    RenderPlayerEventOmniKings.states.put(Minecraft.getMinecraft().thePlayer.getUniqueID(), (byte) StateSlcted);
                    JRMCoreH.setByte( StateSlcted, Minecraft.getMinecraft().thePlayer, "JRMCState");
                    JRMCoreH.State = (byte) StateSlcted;
                }
                setdns();
            }
            else if (button.id == 5008)
            {
                EyesSlcted = SlctF(EyesSlcted, JRMCoreH.customSknLimits[RaceSlcted][4]);setdns();
            }
            else if (button.id == 60528)
            {
                EyesSlcted = SlctB(EyesSlcted, JRMCoreH.customSknLimits[RaceSlcted][4]);setdns();
            }
            else if (button.id == 5012)
            {
                BodyColPresetSlcted = SlctF(BodyColPresetSlcted, JRMCoreH.customSknLimitsBCP[RaceSlcted]);setchangebodycol();setdns();
            }
            else if (button.id == 60524)
            {
                BodyColPresetSlcted = SlctB(BodyColPresetSlcted, JRMCoreH.customSknLimitsBCP[RaceSlcted]);setchangebodycol();setdns();
            }
            else if (button.id == -10) {
                if (this.mcuOmni >= 3) {
                    this.mcuOmni = 0;
                } else {
                    this.mcuOmni += 1;
                }
            }
            else if(button.id >= 110 && button.id <= 110 + JRMCoreH.PlyrAttrbts.length)
            {
                for(byte i = 0; i < JRMCoreH.PlyrAttrbts.length; ++i) {
                    if (button.id == 110 + i) {
                        JRMCoreH.Upg((byte)(i + this.mcuOmni * 6));
                    }
                }
            }
            else if(button.id == -4)
            {
                int col = ((JRMCoreGuiButtonC)button).getBgCol();
                switch (colorType)
                {
                    case 4:
                        ColorSlcted = col; break;
                    case 5015:
                        KiColorSlcted = col; break;
                    case 5003:
                        BodyColMainSlcted = col; break;
                    case 5004:
                        BodyColSub1Slcted = col; break;
                    case 5005:
                        BodyColSub2Slcted = col; break;
                    case 5014:
                        BodyColSub3Slcted = col; break;
                    case 5009:
                        EyeCol1Slcted = col; break;
                    case 5010:
                        EyeCol2Slcted = col; break;
                    case 5016:
                        BodyauColMainSlcted = col;setdnsau();
                        JRMCoreH.jrmcDataFC(3, dnsauOmni); break;
                    case 5017:
                        BodyauColSub1Slcted = col;setdnsau();JRMCoreH.jrmcDataFC(3, dnsauOmni); break;
                    case 5018:
                        BodyauColSub2Slcted = col;setdnsau();JRMCoreH.jrmcDataFC(3, dnsauOmni); break;
                    case 5019:
                        BodyauColSub3Slcted = col;setdnsau();JRMCoreH.jrmcDataFC(3, dnsauOmni);
                }
                if ((this.guiIDprev == 8) || (this.guiIDprev == 0) || (this.guiIDprev == 2) || (this.guiIDprev == 19)) {
                    this.guiID = this.guiIDprev;
                }
                colorType = 0;
                if (this.guiIDprev == 8) {
                    setdnsForHair();
                } else {
                    setdns();
                }
            }
            else if (button.id == 1)
            {
                RaceSlctF();JRMCoreH.Char((byte)0, (byte)RaceSlcted);setchangerace();setdns();
            }
            else if (button.id == -1)
            {
                RaceSlctB();JRMCoreH.Char((byte)0, (byte)RaceSlcted);setchangerace();setdns();
            }
            else if (button.id == 43) {
                scrollOmni -= 3;
            }
            else if (button.id == 44) {
                scrollOmni += 3;
            }
            else if (button.id >= 300 && button.id <= 300 + JRMCoreH.PlyrSkills.length)
            {
                for (byte i = 0; i < JRMCoreH.PlyrSkills.length; i = (byte) (i + 1))
                {
                    if (button.id == 300 + i)
                    {
                        JRMCoreH.Skll((byte) 2, i);
                        JRMCoreH.jrmct(3);
                        this.confirmationWindowOmni = false;
                    }
                }
            }
            else if (button.id >= 330 && button.id <= 330 + JRMCoreH.PlyrSkills.length)
            {
                for (byte i = 0; i < JRMCoreH.PlyrSkills.length; i = (byte)(i + 1)) {
                    if (button.id == 330 + i)
                    {
                        JRMCoreH.Skll((byte)3, i);
                        JRMCoreH.jrmct(3);
                    }
                }
            }
            else if (button.id >= 360 && button.id <= 360 + JRMCoreH.PlyrSkills.length)
            {
                for (byte i = 0; i < JRMCoreH.PlyrSkills.length; i = (byte)(i + 1)) {
                    if (button.id == 360 + i)
                    {
                        this.confirmationWindowOmni = true;
                        this.IDtoProcessConfirmForOmni = i;
                    }
                }
            }
            else if (button.id == 399) {
                this.confirmationWindowOmni = false;
            }
            else
            {
                super.actionPerformed(button);
            }
        }
        else
        {
            super.actionPerformed(button);
        }
    }

    @Override
    public void drawScreen(int x, int y, float f)
    {
        if (this.guiID == 0 && RaceSlcted  > 4) {
            try {
                drawOmniKingsScreen(x,y,f);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else if ((this.guiID == 10 || this.guiID == 11) && JRMCoreH.Race  > 4)
        {
            try {
                drawOmniKingsScreen(x,y,f);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else {
            super.drawScreen(x, y, f);
        }
    }

    private void drawOmniKingsScreen(int x, int y, float f) throws Exception {

        Field tickField = Utils.getPrivateField("tick", JRMCoreGuiScreen.class);
        tickField.setAccessible(true);
        tickOmni = tickField.getInt(JRMCoreGuiScreen.class);

        Field scrollSideField = Utils.getPrivateField("scrollSide", JRMCoreGuiScreen.class);
        scrollSideField.setAccessible(true);
        scrollSideOmni = scrollSideField.getFloat(JRMCoreGuiScreen.class);

        Field dnsField = Utils.getPrivateField("dns", JRMCoreGuiScreen.class);
        dnsField.setAccessible(true);
        dnsOmni = (String)dnsField.get(JRMCoreGuiScreen.class);

        Field dnsSentField = Utils.getPrivateField("dnsSent", JRMCoreGuiScreen.class);
        dnsSentField.setAccessible(true);
        dnsSentOmni = (String)dnsSentField.get(JRMCoreGuiScreen.class);

        Field dnsHField = Utils.getPrivateField("dnsH", JRMCoreGuiScreen.class);
        dnsHField.setAccessible(true);
        dnsHOmni = (String)dnsHField.get(JRMCoreGuiScreen.class);

        Field dnsHSentField = Utils.getPrivateField("dnsHSent", JRMCoreGuiScreen.class);
        dnsHSentField.setAccessible(true);
        dnsHSentOmni = (String)dnsHSentField.get(JRMCoreGuiScreen.class);

        Field dnsauField = Utils.getPrivateField("dnsau", JRMCoreGuiScreen.class);
        dnsauField.setAccessible(true);
        dnsauOmni = (String)dnsauField.get(JRMCoreGuiScreen.class);

        Field ptchField = Utils.getPrivateField("ptch", JRMCoreGuiScreen.class);
        ptchField.setAccessible(true);
        ptchOmni = ptchField.getFloat(JRMCoreGuiScreen.class);

        Field detailListField = Utils.getPrivateField("detailList", JRMCoreGuiScreen.class);
        detailListField.setAccessible(true);
        detailListOmni = (List)detailListField.get(JRMCoreGuiScreen.class);

        int ar = 2000;
        if (this.kdfOmni < ar) {
            this.kdfOmni += 1;
        }
        if (Mouse.isButtonDown(0))
        {
            this.mousePressedOmni = true;
            scrollSideOmni = JRMCoreGuiSliderX00.sliderValue;
        }
        else
        {
            this.mousePressedOmni = false;
            while (Mouse.next())
            {
                int mw = Mouse.getEventDWheel();
                if (mw != 0)
                {
                    if (mw < 0) {
                        this.scrollOmni += this.scrollMouseJumpOmni;
                    } else if ((mw > 0) && (this.scrollOmni > 0)) {
                        this.scrollOmni -= this.scrollMouseJumpOmni;
                    }
                    this.scrollMouseJumpOmni = 1;
                }
            }
        }

        this.xOmni = x;
        this.yOmni = y;

        int posX = this.width / 2;
        int posY = this.height / 2;
        
        if (this.guiIDprev2 != 0)
        {
            this.guiID = this.guiIDprev2;this.guiIDprev2 = 0;
        }

        if ((dnsOmni.length() > 1) && (!dnsOmni.equals(dnsSentOmni)))
        {
            dnsSentOmni = dnsOmni;
            if ((JRMCoreH.JFC()) && (ItemBarberSnC.barberTarget != null))
            {
                if (JRMCoreHJFC.isChildNPC(ItemBarberSnC.barberTarget)) {
                    JRMCoreHJFC.childDNSset(ItemBarberSnC.barberTarget, dnsSentOmni);
                }
            }
            else {
                JRMCoreH.jrmcDataFC(0, dnsSentOmni);
            }
        }
        if ((dnsHOmni.length() != 786) && (!dnsHOmni.equals(dnsHSentOmni))) {
            dnsHOmni = JRMCoreH.dnsHairG1toG2(dnsHOmni);
        }
        if ((dnsHOmni.length() == 786) && (!dnsHOmni.equals(dnsHSentOmni)))
        {
            dnsHSentOmni = dnsHOmni;
            if ((JRMCoreH.JFC()) && (ItemBarberSnC.barberTarget != null))
            {
                if (JRMCoreHJFC.isChildNPC(ItemBarberSnC.barberTarget)) {
                    JRMCoreHJFC.childDNSHset(ItemBarberSnC.barberTarget, dnsHSentOmni);
                }
            }
            else {
                JRMCoreH.jrmcDataFC(1, dnsHSentOmni);
            }
        }
        if (dnsHOmni.length() < 3) {
            if ((JRMCoreH.JFC()) && (ItemBarberSnC.barberTarget != null))
            {
                if (JRMCoreHJFC.isChildNPC(ItemBarberSnC.barberTarget)) {
                    dnsHOmni = JRMCoreHJFC.childDNSH(ItemBarberSnC.barberTarget);
                }
            }
            else {
                dnsHOmni = JRMCoreH.dnsH;
            }
        }
        if (dnsOmni.length() < 3) {
            if ((JRMCoreH.JFC()) && (ItemBarberSnC.barberTarget != null))
            {
                if (JRMCoreHJFC.isChildNPC(ItemBarberSnC.barberTarget)) {
                    dnsOmni = JRMCoreHJFC.childDNS(ItemBarberSnC.barberTarget);
                }
            }
            else {
                dnsOmni = JRMCoreH.dns;
            }
        }
        this.xSize_lo = x;
        this.ySize_lo = y;
        ScaledResolution var5 = new ScaledResolution(mc, mc.displayWidth, mc.displayHeight);
        int var6 = var5.getScaledWidth();
        int var7 = var5.getScaledHeight();
        FontRenderer var8 = mc.fontRenderer;

        this.buttonList.clear();
        tickOmni += 1;
        if (tickOmni >= 20)
        {
            tickOmni = 0;JRMCoreH.jrmct(1);JRMCoreH.jrmct(3);
        }
        this.guiLeft = ((this.width - this.xSize) / 2);
        this.guiTop = ((this.height - this.ySize) / 2);

        if (this.guiID == 0)
        {
            int xSize = 256;
            int ySize = 159;
            int guiLeft = (this.width - xSize) / 2;
            int guiTop = (this.height - ySize) / 2;
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            ResourceLocation guiLocation = new ResourceLocation(wish);
            mc.renderEngine.bindTexture(guiLocation);
            drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);

            this.buttonList.add(new JRMCoreGuiButtons00(10, posX - 150, posY + 65, 20, 20, "X", 0));
            String n = JRMCoreH.trl("jrmc", "Next");int nw = this.fontRendererObj.getStringWidth(n) + 8;
            this.buttonList.add(new JRMCoreGuiButtons00(11, posX + 130, posY + 65, nw, 20, n, 0));

            int race = JRMCoreH.dnsRace("dns");
            if (JRMCoreH.Allow(JRMCoreH.RaceAllow[RaceSlcted]))
            {
                CanRace = true;
            }
            else
            {
                CanRace = false;
                if (RaceSlcted != 0)
                {
                    RaceSlcted = 0;JRMCoreH.Char((byte)0, (byte)RaceSlcted);
                }
            }
            if ((JRMCoreH.Allow(JRMCoreH.GenderAllow[GenderSlcted])) && (JRMCoreH.RaceGenders[RaceSlcted] != 1))
            {
                CanGender = true;
            }
            else
            {
                CanGender = false;
                if (GenderSlcted != 0) {
                    GenderSlcted = 0;
                }
            }
            if (JRMCoreH.Allow("JYC"))
            {
                CanYears = true;
            }
            else
            {
                CanYears = false;
                if (YearsSlcted != 0) {
                    YearsSlcted = 0;
                }
            }
            if (JRMCoreH.RaceCanHaveHair[RaceSlcted].contains("H"))
            {
                CanHair = true;
            }
            else
            {
                CanHair = false;
                if (HairSlcted != 10) {
                    HairSlcted = 10;
                }
            }
            if ((JRMCoreH.RaceCanHaveHair[RaceSlcted].contains("H")) && (JRMCoreH.RaceHairColor[RaceSlcted] == -1))
            {
                CanColor = true;
            }
            else
            {
                ColorSlcted = JRMCoreH.RaceHairColor[RaceSlcted] != -1 ? JRMCoreH.RaceHairColor[RaceSlcted] : 0;CanColor = false;
            }
            if (JRMCoreH.RaceCustomSkin[RaceSlcted] == 2)
            {
                canCustom = true;
            }
            else if (JRMCoreH.RaceCustomSkin[RaceSlcted] == 1)
            {
                canCustom = false;
                if (SkinTypeSlcted != 1)
                {
                    SkinTypeSlcted = 1;
                    setdns();
                }
            }
            else
            {
                canCustom = false;
                if (SkinTypeSlcted != 0)
                {
                    SkinTypeSlcted = 0;
                    setdns();
                }
            }
            if (SkinTypeSlcted == 1) {}
            String Race = JRMCoreH.Races[RaceSlcted];
            String Gender = JRMCoreH.trl("jrmc", JRMCoreH.Genders[GenderSlcted]);
            String Years = JRMCoreH.trl("jrmc", JRMCoreH.Years[YearsSlcted]);
            String TRState = JRMCoreH.trl("jrmc", "TRState");
            String Hair = JRMCoreH.trl("jrmc", "Hair") + " " + (HairSlcted + 1);

            String Color = "" + ColorSlcted;
            String SkinTyp = JRMCoreH.trl("jrmc", JRMCoreH.skinTyps[SkinTypeSlcted]);
            String Tail = JRMCoreH.trl("jrmc", "Tail");

            int i = 0;
            if (CanRace)
            {
                this.buttonList.add(new JRMCoreGuiButtonsA2(-1, guiLeft + 130, guiTop + 5 + i * 10, "<"));
                this.buttonList.add(new JRMCoreGuiButtonsA2(1, guiLeft + 240, guiTop + 5 + i * 10, ">"));
            }
            var8.drawString(Race, guiLeft + 190 - var8.getStringWidth(Race) / 2, guiTop + 5 + i * 10, 0);
            i++;
            if (CanGender)
            {
                this.buttonList.add(new JRMCoreGuiButtonsA2(-2, guiLeft + 130, guiTop + 5 + i * 10, "<"));
                this.buttonList.add(new JRMCoreGuiButtonsA2(2, guiLeft + 240, guiTop + 5 + i * 10, ">"));
                var8.drawString(Gender, guiLeft + 190 - var8.getStringWidth(Gender) / 2, guiTop + 5 + i * 10, 0);
            }
            i++;
            if (CanHair)
            {
                this.buttonList.add(new JRMCoreGuiButtonsA2(-3, guiLeft + 130, guiTop + 5 + i * 10, "<"));
                this.buttonList.add(new JRMCoreGuiButtonsA2(3, guiLeft + 240, guiTop + 5 + i * 10, ">"));
                if (HairSlcted != 12) {
                    var8.drawString(Hair, guiLeft + 190 - var8.getStringWidth(Hair) / 2, guiTop + 5 + i * 10, 0);
                }
                String s = JRMCoreH.trl("jrmc", "CustomHair");int sw = this.fontRendererObj.getStringWidth(s) / 2;
                if (HairSlcted == 12) {
                    this.buttonList.add(new JRMCoreGuiButtons01(5100, guiLeft + 190 - sw, guiTop + 5 + i * 10, sw, s, JRMCoreH.techNCCol[1]).setShadow(false));
                }
            }
            i++;
            if (CanColor)
            {
                String s = JRMCoreH.trl("jrmc", "Color");int sw = this.fontRendererObj.getStringWidth(s) / 2;
                this.buttonList.add(new JRMCoreGuiButtons01(4, guiLeft + 190 - sw, guiTop + 5 + i * 10, sw, s, JRMCoreH.techNCCol[1]).setShadow(false));
            }
            if (RaceSlcted == 5)
            {
                String TransNms = TRState + ": " + JRMCoreH.cldgy + JRMCoreH.TransNms[RaceSlcted][StateSlcted];
                this.buttonList.add(new JRMCoreGuiButtonsA2(60537, guiLeft + 130, guiTop + 5 + i * 10 - 1, "<"));
                this.buttonList.add(new JRMCoreGuiButtonsA2(4999, guiLeft + 240, guiTop + 5 + i * 10 - 1, ">"));
                var8.drawString(TransNms, guiLeft + 190 - var8.getStringWidth(TransNms) / 2, guiTop + 5 + i * 10, 0);
            }
            i++;
            if (GenderSlcted == 1) {
                this.buttonList.add(new JRMCoreGuiSlider01(5001, guiLeft + 190 - 25, guiTop + 4 + i * 10, 50, 10, "", BreastSizeSlcted * 0.1F, 1.0F));
            }
            i++;
            if (CanYears)
            {
                this.buttonList.add(new JRMCoreGuiButtonsA2(-8, guiLeft + 130, guiTop + 5 + i * 10, "<"));
                this.buttonList.add(new JRMCoreGuiButtonsA2(8, guiLeft + 240, guiTop + 5 + i * 10, ">"));
                var8.drawString(Years, guiLeft + 190 - var8.getStringWidth(Years) / 2, guiTop + 5 + i * 10, 0);
            }
            i++;
            i++;
            if (canCustom)
            {
                this.buttonList.add(new JRMCoreGuiButtonsA2(60534, guiLeft + 130, guiTop + 5 + i * 10, "<"));
                this.buttonList.add(new JRMCoreGuiButtonsA2(5002, guiLeft + 240, guiTop + 5 + i * 10, ">"));
            }
            var8.drawString(SkinTyp, guiLeft + 190 - var8.getStringWidth(SkinTyp) / 2, guiTop + 5 + i * 10, 0);
            i++;

            boolean fc = false;
            if (SkinTypeSlcted == 1)
            {
                if (SkinTypeSlcted == 1)
                {
                    String Special = JRMCoreH.trl("jrmc", "BodyType") + " " + (BodyTypeSlcted + 1);
                    var8.drawString(Special, guiLeft + 190 - var8.getStringWidth(Special) / 2, guiTop + 5 + i * 10, 0);

                    this.buttonList.add(new JRMCoreGuiButtonsA2(-5, guiLeft + 130, guiTop + 5 + i * 10, "<"));
                    this.buttonList.add(new JRMCoreGuiButtonsA2(5, guiLeft + 240, guiTop + 5 + i * 10, ">"));i++;

                    this.buttonList.add(new JRMCoreGuiButtonsA2(60524, guiLeft + 130, guiTop + 5 + i * 10, "<"));
                    this.buttonList.add(new JRMCoreGuiButtonsA2(5012, guiLeft + 240, guiTop + 5 + i * 10, ">"));
                    int cls = JRMCoreH.customSknLimits[RaceSlcted][1];
                    if (cls >= 1) {
                        this.buttonList.add(new JRMCoreGuiButtonC1(5003, guiLeft + 190 - 10 + ((cls - 1) * -10 - (cls > 1 ? cls - 2 : 0)), guiTop + 5 - 1 + i * 10, 20, 10, "", BodyColMainSlcted));
                    }
                    if (cls >= 2) {
                        this.buttonList.add(new JRMCoreGuiButtonC1(5004, guiLeft + 190 - 10 + ((cls - 1) * -10 - (cls > 1 ? cls - 2 : 0) + 21), guiTop + 5 - 1 + i * 10, 20, 10, "", BodyColSub1Slcted));
                    }
                    if (cls >= 3) {
                        this.buttonList.add(new JRMCoreGuiButtonC1(5005, guiLeft + 190 - 10 + ((cls - 1) * -10 - (cls > 1 ? cls - 2 : 0) + 42), guiTop + 5 - 1 + i * 10, 20, 10, "", BodyColSub2Slcted));
                    }
                    if (cls >= 4) {
                        this.buttonList.add(new JRMCoreGuiButtonC1(5014, guiLeft + 190 - 10 + ((cls - 1) * -10 - (cls > 1 ? cls - 2 : 0) + 63), guiTop + 5 - 1 + i * 10, 20, 10, "", BodyColSub3Slcted));
                    }
                    i++;

                    int fcs = i;

                    this.buttonList.add(new JRMCoreGuiButtonsA2(60530, guiLeft + 130, guiTop + 5 + i * 10, "<"));
                    this.buttonList.add(new JRMCoreGuiButtonsA2(5006, guiLeft + 240, guiTop + 5 + i * 10, ">"));
                    Special = JRMCoreH.trl("jrmc", "Nose") + " " + (FaceNoseSlcted + 1);
                    var8.drawString(Special, guiLeft + 190 - var8.getStringWidth(Special) / 2, guiTop + 5 + i * 10, 0);
                    i++;

                    Special = JRMCoreH.trl("jrmc", "Mouth") + " " + (FaceMouthSlcted + 1);
                    var8.drawString(Special, guiLeft + 190 - var8.getStringWidth(Special) / 2, guiTop + 5 + i * 10, 0);
                    this.buttonList.add(new JRMCoreGuiButtonsA2(60529, guiLeft + 130, guiTop + 5 + i * 10, "<"));
                    this.buttonList.add(new JRMCoreGuiButtonsA2(5007, guiLeft + 240, guiTop + 5 + i * 10, ">"));
                    i++;

                    Special = JRMCoreH.trl("jrmc", "Eyes") + " " + (EyesSlcted + 1);
                    var8.drawString(Special, guiLeft + 190 - var8.getStringWidth(Special) / 2, guiTop + 5 + i * 10, 0);
                    this.buttonList.add(new JRMCoreGuiButtonsA2(60528, guiLeft + 130, guiTop + 5 + i * 10, "<"));
                    this.buttonList.add(new JRMCoreGuiButtonsA2(5008, guiLeft + 240, guiTop + 5 + i * 10, ">"));
                    i++;

                    cls = JRMCoreH.customSknLimits[RaceSlcted][5];
                    if (cls != 0)
                    {
                        this.buttonList.add(new JRMCoreGuiButtonsA2(60523, guiLeft + 130, guiTop + 5 + i * 10, "<"));
                        this.buttonList.add(new JRMCoreGuiButtonsA2(5013, guiLeft + 240, guiTop + 5 + i * 10, ">"));
                    }
                    if (cls >= 1) {
                        this.buttonList.add(new JRMCoreGuiButtonC1(5009, guiLeft + 190 - 10 + ((cls - 1) * -10 - (cls > 1 ? cls - 2 : 0)), guiTop + 5 - 1 + i * 10, 20, 10, "", EyeCol1Slcted));
                    }
                    if (cls >= 2)
                    {
                        this.buttonList.add(new JRMCoreGuiButtonC1(5010, guiLeft + 190 - 10 + ((cls - 1) * -10 - (cls > 1 ? cls - 2 : 0) + 21), guiTop + 5 - 1 + i * 10, 20, 10, "", EyeCol2Slcted));i++;
                        String s = JRMCoreH.trl("jrmc", "Match");int sw = this.fontRendererObj.getStringWidth(s) / 2;
                        this.buttonList.add(new JRMCoreGuiButtons01(5011, guiLeft + 190 - sw, guiTop + 5 + i * 10, sw, s, JRMCoreH.techNCCol[1]).setShadow(false));
                    }
                    i++;
                    fc = hovered(x, y, guiLeft + 130, guiTop + 5 + fcs * 10 + 2, 120, 7 + i * 3);
                }
            }
            else
            {
                int cls = JRMCoreH.customSknLimits[RaceSlcted][1];
                if (cls >= 2) {
                    this.buttonList.add(new JRMCoreGuiButtonC1(5004, guiLeft + 190 - 10 + ((cls - 0) * -10 - (cls > 1 ? cls - 1 : 0) + 21), guiTop + 5 - 1 + i * 10, 20, 10, "", BodyColSub1Slcted));
                }
            }
            if ((RaceSlcted == 1) && (!tail))
            {
                tail = true;JRMCoreH.Char((byte)103, (byte)(tail ? 1 : 0));
            }
            int k = guiLeft;
            int l = guiTop;

            JRMCoreClient.mc.mouseHelper.mouseXYChange();

            float posXm = Mouse.getX() * 1.0F / (JRMCoreClient.mc.displayWidth * 1.0F);
            float posYm = Mouse.getY() * 1.0F / (JRMCoreClient.mc.displayHeight * 1.0F);
            int mouseX = (int)(var6 * posXm);
            int mouseY = var7 - (int)(var7 * posYm);
            if (fc) {
                func_110423_a(k + 51, l + 155 + 190, 180, k + 51 - this.xSize_lo, l + 80 - this.ySize_lo, mc.thePlayer, false, false, false);
            } else {
                func_110423_a_I(k + 51, l + 155, 60, k + 51 - this.xSize_lo, l + 80 - this.ySize_lo, mc.thePlayer);
            }
            String s = JRMCoreH.trl("jrmc", "Appearance");int sw = this.fontRendererObj.getStringWidth(s) / 2;
            var8.drawString(s, guiLeft + 7, guiTop + 5, 0);
        }
        if (this.guiID == 10)
        {
            int xSize = 256;
            int ySize = 159;
            int guiLeft = (this.width - xSize) / 2;
            int guiTop = (this.height - ySize) / 2;
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            ResourceLocation guiLocation = new ResourceLocation(JRMCoreH.Pwrtyp == 3 ? JRMCoreH.tjsaoc + ":gui.png" : wish);
            mc.renderEngine.bindTexture(guiLocation);
            drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
            if (this.pwrOmni != 3) {
                icons = JRMCoreH.tjjrmc + ":icons.png";
                align(icons);
            }
            if (JRMCoreH.Pwrtyp == 1)
            {
                String n = JRMCoreH.trl("jrmc", "Difficulty");
                int nw = this.fontRendererObj.getStringWidth(n) + 8;
                this.buttonList.add(new JRMCoreGuiButtons00(104, posX + 90 - nw / 2, posY + 55, nw, 20, n, 8046079));
            }
            this.buttonList.add(new JRMCoreGuiButtons00(10, posX - 150, posY + 65, 20, 20, "X", 0));
            if (JRMCoreH.Pwrtyp == 1) {
                JRMCoreClient.bars.showSE(posX / 2, guiTop - 34, 0, 0);
            }
            menuButtonsOmni("CH");
            if ((!JRMCoreConfig.ssurl.contains("empty")) && (JRMCoreConfig.ssurl.contains("ttp")))
            {
                String s = "Server Shop";int sw = this.fontRendererObj.getStringWidth(s);
                this.buttonList.add(new JRMCoreGuiButtons00(3099, guiLeft + 260, guiTop + 85, sw + 8, 20, s, 0));
            }
            if ((!JRMCoreEH.aw) && (JRMCoreEH.dt) && (this.kdfOmni >= ar))
            {
                String s = "Update vanity";int sw = this.fontRendererObj.getStringWidth(s);
                this.buttonList.add(new JRMCoreGuiButtons00(3098, guiLeft + 260, guiTop + 85 - 40 - 42, sw + 8, 20, s, 0));
            }
            if (JRMCoreEH.dt)
            {
                String s = (JRMCoreEH.gk ? "Hide" : "Show") + " own vanity";int sw = this.fontRendererObj.getStringWidth(s);
                this.buttonList.add(new JRMCoreGuiButtons00(3097, guiLeft + 260, guiTop + 85 - 40 - 21, sw + 8, 20, s, 0));
            }
            String s = "Supporters site";int sw = this.fontRendererObj.getStringWidth(s);
            this.buttonList.add(new JRMCoreGuiButtons00(3096, guiLeft + 260, guiTop + 85 - 40, sw + 8, 20, s, 0));

            int gen = JRMCoreH.dnsGender(JRMCoreH.dns);
            String Pwrtyp = JRMCoreH.trl("jrmc", JRMCoreH.Pwrtyps[JRMCoreH.Pwrtyp]);
            String raceName = JRMCoreH.trl("jrmc", "Race");
            String Race = JRMCoreH.Races[JRMCoreH.Race] + " " + (gen >= 1 ? "F" : "M");
            String className = JRMCoreH.trl("jrmc", JRMCoreH.ClassNames[JRMCoreH.Pwrtyp]);
            String Class = JRMCoreH.trl("jrmc", JRMCoreH.cl(JRMCoreH.Pwrtyp)[JRMCoreH.Class]);
            String ClassDesc = JRMCoreH.trl("jrmc", clDesc(JRMCoreH.Pwrtyp)[JRMCoreH.Class]);
            String Level = JRMCoreH.trl("jrmc", "Level");
            String Stats = JRMCoreH.trl("jrmc", "Stats");
            String PowerRelease = JRMCoreH.trl("jrmc", "PowerRelease");
            String TRState = JRMCoreH.trl("jrmc", "TRState");
            String TransNms = JRMCoreH.TransNms(JRMCoreH.Race, JRMCoreH.State, JRMCoreH.StusEfctsMe(17), JRMCoreH.StusEfctsMe(13), JRMCoreH.StusEfctsMe(19));
            String StatIncreaseDesc = JRMCoreH.trl("jrmc", "StatIncreaseDesc");
            String StatIncreaseDesc2 = JRMCoreH.trl("jrmc", "StatIncreaseDesc2");
            String StatIncreaseDesc3 = JRMCoreH.trl("jrmc", "StatIncreaseDesc3");
            String StatIncreaseDesc4 = JRMCoreH.trl("jrmc", "StatIncreaseDesc4");
            String StatIncreaseDesc5 = JRMCoreH.trl("jrmc", "StatIncreaseDesc5");
            String Attributes = JRMCoreH.trl("jrmc", "Attributes");
            String TP = JRMCoreH.trl("jrmc", "TP");
            String TrainingPoints = JRMCoreH.trl("jrmc", "TrainingPoints");
            String RequiredTP = JRMCoreH.trl("jrmc", "RequiredTP");
            String Alignment = JRMCoreH.trl("jrmc", "Alignment");
            String AlignmentDesc = JRMCoreH.trl("jrmc", "AlignmentDesc");
            boolean max = JRMCoreH.attrLvl(JRMCoreH.PlyrAttrbts) >= JRMCoreH.attrLvl(kqGW3Z(false) * 6);

            int i = 0;
            if (this.pwrOmni != 3)
            {
                drawDetailsOmni(Level + ": " + JRMCoreH.cldgy + JRMCoreH.attrLvl(JRMCoreH.PlyrAttrbts), JRMCoreH.cldgy + (max ?
                        JRMCoreH.trl("jrmc", "LevelMax") : JRMCoreH.trl("jrmc", "LevelNext", new Object[] { JRMCoreH.cllr + JRMCoreH.attrLvlNext(JRMCoreH.PlyrAttrbts) + JRMCoreH.cldgy })), guiLeft + 5, guiTop + 5 + i * 10, x, y, var8);

                var8.drawString(PowerRelease + ": " + JRMCoreH.cldgy + JRMCoreH.curRelease + "%", guiLeft + 5 + 128, guiTop + 5 + i * 10, 0);i++;i++;
                var8.drawString(Stats, guiLeft + 5 + 128, guiTop + 5 + i * 10, 0);i++;
            }
            int STRo;
            int STR = STRo = JRMCoreH.PlyrAttrbts[0];
            int DEXo;
            int DEX = DEXo = JRMCoreH.PlyrAttrbts[1];
            int CON = JRMCoreH.PlyrAttrbts[2];
            int WILo;
            int WIL = WILo = JRMCoreH.PlyrAttrbts[3];
            int MNDo;
            int MND = MNDo = JRMCoreH.PlyrAttrbts[4];
            int SPI = JRMCoreH.PlyrAttrbts[5];
            double per = 1.0D;

            boolean st = (JRMCoreH.Pwrtyp == 1) && (((JRMCoreH.State == 0) && (JRMCoreH.State2 == 0)) || ((JRMCoreH.Race != 4) || (((JRMCoreH.State == 4) && (JRMCoreH.State2 == 0)) || ((JRMCoreH.Race == 4) || ((JRMCoreH.Race == 4) && (JRMCoreH.State >= 4) && (JRMCoreH.curRelease == 100) && (JRMCoreH.getArcRsrv() > 0)) || (JRMCoreH.StusEfctsMe(12)) || (JRMCoreH.StusEfctsMe(13)) || (JRMCoreH.StusEfctsMe(19))))));
            String stc = JRMCoreH.StusEfctsMe(19) ? JRMCoreH.cllb : JRMCoreH.clgd;
            boolean c = (JRMCoreH.StusEfctsMe(10)) || (JRMCoreH.StusEfctsMe(11));
            if ((JRMCoreH.Pwrtyp != 3) && (JRMCoreH.Pwrtyp > 0))
            {
                STR = OmniKingsCoreH.TransPwrModAtr(JRMCoreH.PlyrAttrbts, 0, JRMCoreH.State, JRMCoreH.State2, JRMCoreH.Race, JRMCoreH.PlyrSkillX, JRMCoreH.curRelease, JRMCoreH.getArcRsrv(), JRMCoreH.StusEfctsMe(14), JRMCoreH.StusEfctsMe(12), JRMCoreH.StusEfctsMe(13), JRMCoreH.StusEfctsMe(19), JRMCoreH.Pwrtyp, JRMCoreH.PlyrSkills, c);
                DEX = OmniKingsCoreH.TransPwrModAtr(JRMCoreH.PlyrAttrbts, 1, JRMCoreH.State, JRMCoreH.State2, JRMCoreH.Race, JRMCoreH.PlyrSkillX, JRMCoreH.curRelease, JRMCoreH.getArcRsrv(), JRMCoreH.StusEfctsMe(14), JRMCoreH.StusEfctsMe(12), JRMCoreH.StusEfctsMe(13), JRMCoreH.StusEfctsMe(19), JRMCoreH.Pwrtyp, JRMCoreH.PlyrSkills, c);
                WIL = OmniKingsCoreH.TransPwrModAtr(JRMCoreH.PlyrAttrbts, 3, JRMCoreH.State, JRMCoreH.State2, JRMCoreH.Race, JRMCoreH.PlyrSkillX, JRMCoreH.curRelease, JRMCoreH.getArcRsrv(), JRMCoreH.StusEfctsMe(14), JRMCoreH.StusEfctsMe(12), JRMCoreH.StusEfctsMe(13), JRMCoreH.StusEfctsMe(19), JRMCoreH.Pwrtyp, JRMCoreH.PlyrSkills, c);

                int maxCON = OmniKingsCoreH.TransPwrModAtr(JRMCoreH.PlyrAttrbts, 2, JRMCoreH.State, JRMCoreH.State2, JRMCoreH.Race, JRMCoreH.PlyrSkillX, JRMCoreH.curRelease, JRMCoreH.getArcRsrv(), JRMCoreH.StusEfctsMe(14), JRMCoreH.StusEfctsMe(12), JRMCoreH.StusEfctsMe(13), JRMCoreH.StusEfctsMe(19), JRMCoreH.Pwrtyp, JRMCoreH.PlyrSkills, c);

                per = (maxCON > CON ? maxCON : CON) / (CON * 1.0D);
            }
            float wDex = 1.0F;
            float wStr = 1.0F;
            boolean hasWeight = false;
            if ((int)JRMCoreH.WeightOn > 0)
            {
                hasWeight = true;
                wDex = JRMCoreH.weightPerc(1);
                wStr = JRMCoreH.weightPerc(0);
            }
            boolean nc = JRMCoreH.Pwrtyp == 2;
            if ((JRMCoreH.Pwrtyp == 1) || (nc))
            {
                float ncbonus = 0.0F;
                if (nc)
                {
                    int ta = JRMCoreH.SklLvl(0, 2, JRMCoreH.PlyrSkills);
                    ncbonus = ta * 0.04F + JRMCoreH.State * 0.25F;
                }
                int statSPI = JRMCoreH.stat(JRMCoreH.Pwrtyp, 5, SPI, JRMCoreH.Race, JRMCoreH.Class, JRMCoreH.SklLvl_KiBs(this.pwrOmni));
                int sklks = 0;
                if (JRMCoreH.Pwrtyp == 1) {
                    sklks = (int)(JRMCoreH.SklLvl(12) * 0.0025D * statSPI * JRMCoreH.curRelease * 0.01D);
                }
                int stat = JRMCoreH.stat(JRMCoreH.Pwrtyp, 0, STR, JRMCoreH.Race, JRMCoreH.Class, ncbonus);
                float inc = JRMCoreH.statInc(JRMCoreH.Pwrtyp, 0, 1, JRMCoreH.Race, JRMCoreH.Class, 0.0F);
                String statNam = JRMCoreH.trl("jrmc", JRMCoreH.attrStat[JRMCoreH.Pwrtyp][0]);
                int curAtr = (int)(stat * 0.01D * JRMCoreH.curRelease * JRMCoreH.weightPerc(0));
                String Stat = JRMCoreH.cldgy + statNam + ": " + (st ? stc : JRMCoreH.cldr) + (curAtr + sklks);
                String statAttr = JRMCoreH.attrNms(JRMCoreH.Pwrtyp, 0);
                String statAttrW = hasWeight ? "\n" + JRMCoreH.cldgy + JRMCoreH.trl("jrmc", "weightreduction") + ": " + JRMCoreH.cllr + (int)(100.0F - wStr * 100.0F) + "%" : "";
                drawDetailsOmni(Stat, JRMCoreH.cct(new StringBuilder().append(StatIncreaseDesc).append(StatIncreaseDesc2).append(sklks > 0 ? StatIncreaseDesc4 : "").toString(), new Object[] { statAttr, Float.valueOf(inc), Integer.valueOf(stat), Integer.valueOf(0), Integer.valueOf(sklks) }) + statAttrW, guiLeft + 5 + 128, guiTop + 5 + i * 10, x, y, var8);i++;
                if (JRMCoreH.Pwrtyp == 1) {
                    sklks = (int)(JRMCoreH.SklLvl(11) * 0.005D * statSPI * JRMCoreH.curRelease * 0.01D);
                }
                stat = JRMCoreH.stat(JRMCoreH.Pwrtyp, 1, DEX, JRMCoreH.Race, JRMCoreH.Class, ncbonus);
                inc = JRMCoreH.statInc(JRMCoreH.Pwrtyp, 1, 1, JRMCoreH.Race, JRMCoreH.Class, 0.0F);
                statNam = JRMCoreH.trl("jrmc", JRMCoreH.attrStat[JRMCoreH.Pwrtyp][1]);
                curAtr = (int)(stat * 0.01D * JRMCoreH.curRelease * JRMCoreH.weightPerc(1));
                Stat = JRMCoreH.cldgy + statNam + ": " + (st ? stc : JRMCoreH.cldr) + (curAtr + sklks);
                statAttr = JRMCoreH.attrNms(JRMCoreH.Pwrtyp, 1);
                statAttrW = hasWeight ? "\n" + JRMCoreH.cldgy + JRMCoreH.trl("jrmc", "weightreduction") + ": " + JRMCoreH.cllr + (int)(100.0F - wDex * 100.0F) + "%" : "";
                drawDetailsOmni(Stat, JRMCoreH.cct(new StringBuilder().append(StatIncreaseDesc).append(StatIncreaseDesc2).append(StatIncreaseDesc3).append(sklks > 0 ? StatIncreaseDesc4 : "").toString(), new Object[] { statAttr, Float.valueOf(inc), Integer.valueOf(stat), Integer.valueOf((int)(curAtr * (JRMCoreConfig.StatPasDef * 0.01F))), Integer.valueOf(sklks) }) + statAttrW, guiLeft + 5 + 128, guiTop + 5 + i * 10, x, y, var8);
                i++;

                statNam = JRMCoreH.trl("jrmc", "Passive");
                Stat = JRMCoreH.cldgy + statNam + ": " + (st ? stc : JRMCoreH.cldr) + (int)(curAtr * (JRMCoreConfig.StatPasDef * 0.01F) + sklks);
                statAttr = JRMCoreH.attrNms(JRMCoreH.Pwrtyp, 1);
                statAttrW = hasWeight ? "\n" + JRMCoreH.cldgy + JRMCoreH.trl("jrmc", "weightreduction") + ": " + JRMCoreH.cllr + (int)(100.0F - wDex * 100.0F) + "%" : "";
                drawDetailsOmni(Stat, JRMCoreH.cct(new StringBuilder().append(StatIncreaseDesc).append(StatIncreaseDesc2).append(StatIncreaseDesc3).append(sklks > 0 ? StatIncreaseDesc4 : "").toString(), new Object[] { statAttr, Float.valueOf(inc), Integer.valueOf(stat), Integer.valueOf((int)(curAtr * (JRMCoreConfig.StatPasDef * 0.01F))), Integer.valueOf(sklks) }) + statAttrW, guiLeft + 5 + 128 + 5, guiTop + 5 + i * 10, x, y, var8);
                i++;

                stat = JRMCoreH.stat(JRMCoreH.Pwrtyp, 2, CON, JRMCoreH.Race, JRMCoreH.Class, 0.0F);
                inc = JRMCoreH.statInc(JRMCoreH.Pwrtyp, 2, 1, JRMCoreH.Race, JRMCoreH.Class, 0.0F);
                statNam = JRMCoreH.trl("jrmc", JRMCoreH.attrStat[JRMCoreH.Pwrtyp][2]);
                int dr = (int)((1.0D - 1.0D / per) * 100.0D);

                Stat = JRMCoreH.cldgy + statNam + ": " + JRMCoreH.cldr + stat + (JRMCoreH.round(per, 1) != 1.0D ? " R" + dr + "%" : "");
                statAttr = JRMCoreH.attrNms(JRMCoreH.Pwrtyp, 2);
                drawDetailsOmni(Stat, JRMCoreH.cct(StatIncreaseDesc + (JRMCoreH.round(per, 1) != 1.0D ? StatIncreaseDesc5 : ""), new Object[] { statAttr, Float.valueOf(inc), Integer.valueOf(dr) }), guiLeft + 5 + 128, guiTop + 5 + i * 10, x, y, var8);i++;

                stat = JRMCoreH.stat(JRMCoreH.Pwrtyp, 3, CON, JRMCoreH.Race, JRMCoreH.Class, 0.0F);
                inc = JRMCoreH.statInc(JRMCoreH.Pwrtyp, 3, 1, JRMCoreH.Race, JRMCoreH.Class, 0.0F);
                statNam = JRMCoreH.trl("jrmc", JRMCoreH.attrStat[JRMCoreH.Pwrtyp][3]);
                Stat = JRMCoreH.cldgy + statNam + ": " + JRMCoreH.cldr + stat;
                statAttr = JRMCoreH.attrNms(JRMCoreH.Pwrtyp, 2);
                drawDetailsOmni(Stat, JRMCoreH.cct(StatIncreaseDesc, new Object[] { statAttr, Float.valueOf(inc) }), guiLeft + 5 + 128, guiTop + 5 + i * 10, x, y, var8);i++;

                stat = JRMCoreH.stat(JRMCoreH.Pwrtyp, 4, WIL, JRMCoreH.Race, JRMCoreH.Class, 0.0F);
                inc = JRMCoreH.statInc(JRMCoreH.Pwrtyp, 4, 1, JRMCoreH.Race, JRMCoreH.Class, 0.0F);
                statNam = JRMCoreH.trl("jrmc", JRMCoreH.attrStat[JRMCoreH.Pwrtyp][4]);
                Stat = JRMCoreH.cldgy + statNam + ": " + (st ? stc : JRMCoreH.cldr) + (int)(stat * 0.01D * JRMCoreH.curRelease);
                statAttr = JRMCoreH.attrNms(JRMCoreH.Pwrtyp, 3);
                drawDetailsOmni(Stat, JRMCoreH.cct(StatIncreaseDesc + StatIncreaseDesc2, new Object[] { statAttr, Float.valueOf(inc), Integer.valueOf(stat) }), guiLeft + 5 + 128, guiTop + 5 + i * 10, x, y, var8);i++;

                stat = JRMCoreH.stat(JRMCoreH.Pwrtyp, 5, SPI, JRMCoreH.Race, JRMCoreH.Class, JRMCoreH.SklLvl_KiBs(this.pwrOmni));
                int statO = JRMCoreH.stat(JRMCoreH.Pwrtyp, 5, SPI, JRMCoreH.Race, JRMCoreH.Class, 0.0F);
                statO = stat - statO;
                inc = JRMCoreH.statInc(JRMCoreH.Pwrtyp, 5, 1, JRMCoreH.Race, JRMCoreH.Class, 0.0F);
                statNam = JRMCoreH.trl("jrmc", JRMCoreH.attrStat[JRMCoreH.Pwrtyp][5]);
                Stat = JRMCoreH.cldgy + statNam + ": " + JRMCoreH.cldr + stat;
                statAttr = JRMCoreH.attrNms(JRMCoreH.Pwrtyp, 5);
                drawDetailsOmni(Stat, JRMCoreH.cct(StatIncreaseDesc + (statO > 0 ? StatIncreaseDesc4 : ""), new Object[] { statAttr, Float.valueOf(inc), Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(statO) }), guiLeft + 5 + 128, guiTop + 5 + i * 10, x, y, var8);i++;

                boolean dbc = JRMCoreH.Pwrtyp == 1;
                String mod;
                String[] skls;
                String[] sklsNms;
                if (JRMCoreH.Pwrtyp == 2)
                {
                    skls = JRMCoreH.ncSkls;
                    sklsNms = JRMCoreH.ncSklsNms;
                    mod = "nc";
                }
                else
                {
                    skls = JRMCoreH.vlblSkls;
                    sklsNms = JRMCoreH.vlblSklsNms;
                    mod = "dbc";
                }
                int st1 = (JRMCoreH.Pwrtyp == 1) && (JRMCoreH.StusEfctsMe(13)) ? 1 : JRMCoreH.rc_humNam(JRMCoreH.Race) ? JRMCoreH.mstc_humnam() : JRMCoreH.rc_arc(JRMCoreH.Race) ? JRMCoreH.mstc_arc() : JRMCoreH.rc_sai(JRMCoreH.Race) ? JRMCoreH.mstc_sai(JRMCoreH.SklLvlX(1, JRMCoreH.PlyrSkillX) - 1) : JRMCoreH.State;
                inc = JRMCoreH.statInc(JRMCoreH.Pwrtyp, 7, 100, JRMCoreH.Race, JRMCoreH.Class, 0.0F) * 0.01F;

                stat = (int)(JRMCoreH.spdFrm(JRMCoreH.PlyrAttrbts(null)[1], JRMCoreH.SklLvl(dbc ? 2 : 0, JRMCoreH.Pwrtyp), 100.0F, true, false, st1, JRMCoreH.State2, inc) * 100.0F);
                statNam = JRMCoreH.trl("jrmc", JRMCoreH.attrStat[JRMCoreH.Pwrtyp][7]);
                Stat = JRMCoreH.cldgy + statNam + ": " + JRMCoreH.cldr + stat + "%";
                statAttr = JRMCoreH.attrNms(JRMCoreH.Pwrtyp, 1);
                String skll = JRMCoreH.trl(mod, JRMCoreH.SklName(skls[0], skls, sklsNms));
                statAttrW = hasWeight ? "\n" + JRMCoreH.cldgy + JRMCoreH.trl("jrmc", "weightreduction") + ": " + JRMCoreH.cllr + (int)(100.0F - wDex * 100.0F) + "%" : "";
                drawDetailsOmni(Stat, JRMCoreH.cct(JRMCoreH.trl("jrmc", "SpDBDesc"), new Object[] { JRMCoreH.cldg + stat + JRMCoreH.cldgy, JRMCoreH.cllr + statAttr + JRMCoreH.cldgy, JRMCoreH.clbe + skll + JRMCoreH.cldgy }) + statAttrW, guiLeft + 5 + 128, guiTop + 5 + i * 10, x, y, var8);i++;
                if (JRMCoreH.Pwrtyp == 1)
                {
                    inc = JRMCoreH.statInc(JRMCoreH.Pwrtyp, 11, 100, JRMCoreH.Race, JRMCoreH.Class, 0.0F) * 0.01F;

                    stat = (int)(JRMCoreH.spdFrm(JRMCoreH.PlyrAttrbts(null)[4], JRMCoreH.SklLvl(3, (byte)1), 100.0F, true, true, st1, JRMCoreH.State2, inc) * 100.0F);

                    statNam = JRMCoreH.trl("jrmc", JRMCoreH.attrStat[JRMCoreH.Pwrtyp][11]);
                    Stat = JRMCoreH.cldgy + statNam + ": " + JRMCoreH.cldr + stat + "%";
                    statAttr = JRMCoreH.attrNms(JRMCoreH.Pwrtyp, 4);
                    skll = JRMCoreH.trl("dbc", JRMCoreH.SklName(JRMCoreH.vlblSkls[3], JRMCoreH.vlblSkls, JRMCoreH.vlblSklsNms));
                    statAttrW = hasWeight ? "\n" + JRMCoreH.cldgy + JRMCoreH.trl("jrmc", "weightreduction") + ": " + JRMCoreH.cllr + (int)(100.0F - wDex * 100.0F) + "%" : "";
                    drawDetailsOmni(Stat, JRMCoreH.cct(JRMCoreH.trl("jrmc", "FSDBDesc"), new Object[] { JRMCoreH.cldg + stat + JRMCoreH.cldgy, JRMCoreH.cllr + statAttr + JRMCoreH.cldgy, JRMCoreH.clbe + skll + JRMCoreH.cldgy }) + statAttrW, guiLeft + 5 + 128, guiTop + 5 + i * 10, x, y, var8);i++;
                }
                i = 1;
                String requiredTP = JRMCoreH.cct(RequiredTP, new Object[] { JRMCoreH.cllr + JRMCoreH.numSep(JRMCoreH.attrCst(JRMCoreH.PlyrAttrbts, this.mcuOmni)) + JRMCoreH.cldgy, "" });
                drawDetailsOmni(TP + ": " + JRMCoreH.cldgy + JRMCoreH.numSep(JRMCoreH.curTP), TrainingPoints + ",\n" + requiredTP, guiLeft + 5, guiTop + 5 + i * 10, x, y, var8);i++;

                var8.drawString(raceName + ": " + JRMCoreH.cldgy + Race, guiLeft + 5, guiTop + 5 + i * 10, 0);i++;
                drawDetailsOmni(className + ": " + JRMCoreH.cldgy + Class, ClassDesc, guiLeft + 5, guiTop + 5 + i * 10, x, y, var8);i++;
                drawDetailsOmni(Alignment + ": " + JRMCoreH.algnCur(JRMCoreH.align), JRMCoreH.cct(AlignmentDesc, new Object[] { JRMCoreH.align + "%", "" }), guiLeft + 5, guiTop + 5 + i * 10, x, y, var8);i++;

                var8.drawString(TRState + ": " + ((JRMCoreH.StusEfctsMe(14)) && (JRMCoreH.lgndb()) ? JRMCoreH.cldg : (JRMCoreH.StusEfctsMe(10)) || (JRMCoreH.StusEfctsMe(11)) ? JRMCoreH.clpr : JRMCoreH.cldgy) + TransNms, guiLeft + 5, guiTop + 5 + i * 10, 0);i++;

                i++;
                var8.drawString(Attributes, guiLeft + 5, guiTop + 5 + i * 10, 0);i++;

                boolean fz = (!JRMCoreH.StusEfctsMe(10)) && (!JRMCoreH.StusEfctsMe(11));
                boolean en = JRMCoreH.curTP >= cost;
                for (int i1 = 0; i1 < JRMCoreH.PlyrAttrbts.length; i1++)
                {
                    boolean mxd = kqGW3Z(c) > JRMCoreH.PlyrAttrbts[i1];

                    boolean b = (CanUpgradeOmni[i1]) && (en) && (mxd) && (fz);
                    int ypos = guiTop + i * 10 + 3 + i1 * 10;
                    this.buttonList.add(new JRMCoreGuiButtonsA3(110 + i1, guiLeft + 3, ypos, 10, 2, b));

                    String say = null;
                    if (!mxd) {
                        say = JRMCoreH.cct(JRMCoreH.trl("jrmc", "AttributeMaxed"), new Object[0]);
                    } else if (!fz) {
                        say = JRMCoreH.cct(JRMCoreH.trl("dbc", "cantupgradef"), new Object[0]);
                    }
                    if (say != null) {
                        drawDetailsOmni(say, guiLeft + 3, ypos + 1, 10, 10, x, y, var8);
                    }
                }
                String startAttr = JRMCoreH.cldgy + "STR: " + (st ? stc : JRMCoreH.cldr) + STR + (hasWeight ? JRMCoreH.cllr + " -" + (int)JRMCoreH.WeightOn : "");
                String AttrOrig = st ? JRMCoreH.trl("jrmc", "Modified") + ": " + JRMCoreH.clgd + STR + "\n" + JRMCoreH.cldgy + JRMCoreH.trl("jrmc", "Original") + ": " + JRMCoreH.cldr + STRo + "\n" : "";

                String AttrDesc = AttrOrig + JRMCoreH.cldgy + JRMCoreH.attrNms(JRMCoreH.Pwrtyp, 0) + ", " + JRMCoreH.trl("jrmc", JRMCoreH.attrDsc[JRMCoreH.Pwrtyp][0]) + (hasWeight ? "\n" + JRMCoreH.trl("jrmc", "trainingweightworn") + ": " + JRMCoreH.cllr + (int)JRMCoreH.WeightOn : "");
                drawDetailsOmni(startAttr, AttrDesc, guiLeft + 15, guiTop + 5 + i * 10, x, y, var8);i++;

                startAttr = JRMCoreH.cldgy + "DEX: " + (st ? stc : JRMCoreH.cldr) + DEX + (hasWeight ? JRMCoreH.cllr + " -" + (int)JRMCoreH.WeightOn : "");
                AttrOrig = st ? JRMCoreH.trl("jrmc", "Modified") + ": " + JRMCoreH.clgd + DEX + "\n" + JRMCoreH.cldgy + JRMCoreH.trl("jrmc", "Original") + ": " + JRMCoreH.cldr + DEXo + "\n" : "";

                AttrDesc = AttrOrig + JRMCoreH.cldgy + JRMCoreH.attrNms(JRMCoreH.Pwrtyp, 1) + ", " + JRMCoreH.trl("jrmc", JRMCoreH.attrDsc[JRMCoreH.Pwrtyp][1]) + (hasWeight ? "\n" + JRMCoreH.trl("jrmc", "trainingweightworn") + ": " + JRMCoreH.cllr + (int)JRMCoreH.WeightOn : "");
                drawDetailsOmni(startAttr, AttrDesc, guiLeft + 15, guiTop + 5 + i * 10, x, y, var8);i++;

                startAttr = JRMCoreH.cldgy + "CON: " + JRMCoreH.cldr + CON + (JRMCoreH.round(per, 1) != 1.0D ? " x" + JRMCoreH.round(per, 1) : "");
                AttrDesc = JRMCoreH.attrNms(JRMCoreH.Pwrtyp, 2) + ", " + JRMCoreH.trl("jrmc", JRMCoreH.attrDsc[JRMCoreH.Pwrtyp][2]);
                drawDetailsOmni(startAttr, AttrDesc, guiLeft + 15, guiTop + 5 + i * 10, x, y, var8);i++;

                startAttr = JRMCoreH.cldgy + "WIL: " + (st ? stc : JRMCoreH.cldr) + WIL;
                AttrOrig = st ? JRMCoreH.trl("jrmc", "Modified") + ": " + JRMCoreH.clgd + WIL + "\n" + JRMCoreH.cldgy + JRMCoreH.trl("jrmc", "Original") + ": " + JRMCoreH.cldr + WILo + "\n" : "";
                AttrDesc = AttrOrig + JRMCoreH.cldgy + JRMCoreH.attrNms(JRMCoreH.Pwrtyp, 3) + ", " + JRMCoreH.trl("jrmc", JRMCoreH.attrDsc[JRMCoreH.Pwrtyp][3]);
                drawDetailsOmni(startAttr, AttrDesc, guiLeft + 15, guiTop + 5 + i * 10, x, y, var8);i++;

                startAttr = JRMCoreH.cldgy + "MND: " + (MND != MNDo ? JRMCoreH.clgd : JRMCoreH.cldr) + MND;
                AttrOrig = MND != MNDo ? JRMCoreH.trl("jrmc", "Modified") + ": " + JRMCoreH.clgd + MND + "\n" + JRMCoreH.cldgy + JRMCoreH.trl("jrmc", "Original") + ": " + JRMCoreH.cldr + MNDo + "\n" : "";
                AttrDesc = AttrOrig + JRMCoreH.cldgy + JRMCoreH.attrNms(JRMCoreH.Pwrtyp, 4) + ", " + JRMCoreH.trl("jrmc", JRMCoreH.attrDsc[JRMCoreH.Pwrtyp][4]);
                drawDetailsOmni(startAttr, AttrDesc, guiLeft + 15, guiTop + 5 + i * 10, x, y, var8);i++;

                startAttr = JRMCoreH.cldgy + "SPI: " + JRMCoreH.cldr + SPI;
                AttrDesc = JRMCoreH.attrNms(JRMCoreH.Pwrtyp, 5) + ", " + JRMCoreH.trl("jrmc", JRMCoreH.attrDsc[JRMCoreH.Pwrtyp][5]);
                drawDetailsOmni(startAttr, AttrDesc, guiLeft + 15, guiTop + 5 + i * 10, x, y, var8);i++;

                this.buttonList.add(new JRMCoreGuiButtonsA3(-10, guiLeft + 7, guiTop + 0 + i * 10 + 3, 10, 2, true));

                int cost = JRMCoreH.attrCst(JRMCoreH.PlyrAttrbts, this.mcuOmni);
                boolean xm = JRMCoreH.acm(JRMCoreH.PlyrAttrbts);
                String say = JRMCoreH.cct(RequiredTP, new Object[] { JRMCoreH.cldr + JRMCoreH.numSep(cost) + JRMCoreH.cldgy, "" }) + (!en ? "/n(" + JRMCoreH.trl("jrmc", "notenoughtp") + ")" : "");
                if (xm) {
                    say = JRMCoreH.cct(JRMCoreH.trl("jrmc", "AttributeAllMaxed"), new Object[0]);
                } else if (cost == 0) {
                    say = JRMCoreH.cct(JRMCoreH.trl("jrmc", "cantupgrade"), new Object[0]);
                } else if (!fz) {
                    say = JRMCoreH.cct(JRMCoreH.trl("dbc", "cantupgradef"), new Object[0]);
                }
                startAttr = JRMCoreH.cldgy + " UC: " + JRMCoreH.cldr + (cost == 0 ? JRMCoreH.trl("jrmc", "LimitReached") : new StringBuilder().append(JRMCoreH.numSep(cost)).append(" TP").append(this.mcuOmni > 0 ? " x" + JRMCoreH.am(this.mcuOmni) : "").toString());
                AttrDesc = JRMCoreH.trl("jrmc", "UCnam") + ", " + say + (x > 0 ? " x" + JRMCoreH.am(this.mcuOmni) : "");
                drawDetailsOmni(startAttr, AttrDesc, guiLeft + 15, guiTop + 5 + i * 10, x, y, var8);i++;
                if ((JRMCoreH.Pwrtyp == 2) &&
                        (JRMCoreH.PtchVc == 0))
                {
                    this.buttonList.add(new JRMCoreGuiSlider00(196, guiLeft - 30, guiTop + 5 - 20, "", ptchOmni = 0.0F, 1.0F));
                    this.buttonList.add(new JRMCoreGuiButtons00(197, guiLeft - 30, guiTop + 5 + 130 - 20, 20, 20, "Ok", 0));
                    var8.drawString(JRMCoreH.trl("jrmc", "Voice"), guiLeft - 30, guiTop + 5 - 30, JRMCoreH.techNCCol[1]);
                }
            }
            else if (this.pwrOmni == 3)
            {
                String StatIncreaseDescStr = JRMCoreH.trl("saoc", "StatIncreaseDescStr");
                String StatIncreaseDescVit = JRMCoreH.trl("saoc", "StatIncreaseDescVit");

                int WeaponDamage = JRMCoreHSAC.getWeaponDamage(mc.thePlayer.getCurrentEquippedItem(), STR);

                int STRwb = JRMCoreHSAC.getWeaponBonus(mc.thePlayer.getCurrentEquippedItem(), 0);
                int AGIwb = JRMCoreHSAC.getWeaponBonus(mc.thePlayer.getCurrentEquippedItem(), 1);
                int dmg = (int)JRMCoreHSAC.getDamage(WeaponDamage, STR, DEX);

                int requires = JRMCoreHSAC.getWeaponStatName(mc.thePlayer.getCurrentEquippedItem(), "requires");
                int attackMin = JRMCoreHSAC.getWeaponStatName(mc.thePlayer.getCurrentEquippedItem(), "attackMin");
                int attackMax = JRMCoreHSAC.getWeaponStatName(mc.thePlayer.getCurrentEquippedItem(), "attackMax");
                int attackMinOrg = attackMin;
                int attackMaxOrg = attackMax;
                if ((requires > 0) && (requires <= STR) && (mc.thePlayer.getCurrentEquippedItem() != null))
                {
                    attackMin = (int)JRMCoreHSAC.getDamage(attackMin, STR + STRwb, DEX);
                    attackMax = (int)JRMCoreHSAC.getDamage(attackMax, STR + AGIwb, DEX);
                }
                else if (mc.thePlayer.getCurrentEquippedItem() != null)
                {
                    int d1 = (mc.thePlayer.getCurrentEquippedItem().getItem() instanceof ItemSword) ? 4 : 0;

                    Multimap multimap = mc.thePlayer.getCurrentEquippedItem().getAttributeModifiers();
                    if (!multimap.isEmpty())
                    {
                        Iterator iterator = multimap.entries().iterator();
                        while (iterator.hasNext())
                        {
                            Map.Entry entry = (Map.Entry)iterator.next();
                            AttributeModifier attributemodifier = (AttributeModifier)entry.getValue();
                            double d0 = attributemodifier.getAmount();
                            if (attributemodifier.getID() == UUID.fromString("CB3F55D3-645C-4F38-A497-9C13A33DB5CF")) {
                                d0 += EnchantmentHelper.func_152377_a(mc.thePlayer.getCurrentEquippedItem(), EnumCreatureAttribute.UNDEFINED);
                            }
                            if ((attributemodifier.getOperation() != 1) && (attributemodifier.getOperation() != 2)) {
                                d1 += (int)d0;
                            } else {
                                d1 += (int)(d0 * 100.0D);
                            }
                            if (d0 <= 0.0D) {
                                if (d0 < 0.0D) {
                                    d1 = (int)(d1 * -1.0D);
                                }
                            }
                        }
                    }
                    attackMin = 1 + d1;
                    attackMax = 2 + d1;
                }
                else
                {
                    attackMin = 1;
                    attackMax = 2;
                }
                max = JRMCoreH.sao_level >= 255;
                drawDetailsOmni(Level + ": " + JRMCoreH.cldgy + JRMCoreH.sao_level, JRMCoreH.cldgy + (max ?
                        JRMCoreH.trl("jrmc", "LevelMax") : new StringBuilder().append("You need ").append(JRMCoreH.SAOexpNeeded(JRMCoreH.sao_level, JRMCoreH.sao_exp)).append(" to Level up").toString()), guiLeft + 5, guiTop + 5 + i * 10, x, y, var8);

                drawDetailsOmni("AreaLevel: " + JRMCoreHSAC.SAO_getLvlBasedOnDrop(JRMCoreClient.mc.thePlayer), "Current Level of an Area and mobs are probably around this level.", guiLeft + 5 + 128, guiTop + 5 + i * 10, x, y, var8);
                i++;

                String saostat = "EXP: " + JRMCoreH.cldgy + JRMCoreH.sao_exp + "/" + JRMCoreH.SAOexpNeeded(JRMCoreH.sao_level, JRMCoreH.sao_exp);
                String desc = JRMCoreH.trl("saoc", "expDesc");
                drawDetailsOmni(saostat, desc, guiLeft + 5, guiTop + 5 + i * 10, x, y, var8);i++;
                saostat = "COL: " + JRMCoreH.cldgy + JRMCoreH.sao_col;
                desc = JRMCoreH.trl("saoc", "colDesc");
                drawDetailsOmni(saostat, desc, guiLeft + 5, guiTop + 5 + i * 10, x, y, var8);i++;

                i++;
                var8.drawString(Attributes, guiLeft + 5, guiTop + 5 + i * 10, 0);
                var8.drawString(Stats, guiLeft + 5 + 128, guiTop + 5 + i * 10, 0);i++;

                int inc = (int)JRMCoreH.statInc[JRMCoreH.Pwrtyp][0];
                String statNam = JRMCoreH.trl("saoc", JRMCoreH.attrStat[JRMCoreH.Pwrtyp][0]);
                String Stat = JRMCoreH.cldgy + statNam + ": " + JRMCoreH.cldr + attackMin + "-" + attackMax;
                String statAttr = "STR";
                drawDetailsOmni(Stat, JRMCoreH.cct(StatIncreaseDescStr + JRMCoreH.cldgy + "/nSAOC weapon damage " + JRMCoreH.cldr + attackMinOrg + "-" + attackMaxOrg, new Object[] { statAttr, Integer.valueOf(inc) }), guiLeft + 5 + 128, guiTop + 5 + i * 10, x, y, var8);
                for (int i1 = 0; i1 < 3; i1++)
                {
                    int cost = JRMCoreH.attrCst(JRMCoreH.PlyrAttrbts, 0);
                    boolean b = (CanUpgradeOmni[i1]) && (JRMCoreH.sao_ap > 0) && (kqGW3Z(c) > JRMCoreH.PlyrAttrbts[i1]);
                    int ypos = guiTop + i * 10 + 3 + i1 * 10;
                    this.buttonList.add(new JRMCoreGuiButtonsA3(110 + i1, guiLeft + 3, ypos, 10, 2, b));
                    drawDetailsOmni(JRMCoreH.cct(RequiredTP, new Object[] { JRMCoreH.cllr + JRMCoreH.attrCst(JRMCoreH.PlyrAttrbts, 0) + JRMCoreH.cldgy, "" }), guiLeft + 3, ypos + 1, 10, 10, x, y, var8);
                }
                String startAttr = JRMCoreH.cldgy + "STR: " + JRMCoreH.cldr + STR + (STRwb > 0 ? JRMCoreH.clgd + " +" + STRwb : "");
                String AttrDesc = JRMCoreH.trl("saoc", JRMCoreH.attrDsc[JRMCoreH.Pwrtyp][0]);
                drawDetailsOmni(startAttr, AttrDesc, guiLeft + 5 + 10, guiTop + 5 + i * 10, x, y, var8);i++;

                int stat = JRMCoreH.stat(JRMCoreH.Pwrtyp, 2, CON, JRMCoreH.Race, JRMCoreH.Class, 0.0F);
                inc = (int)JRMCoreH.statInc[JRMCoreH.Pwrtyp][2];
                statNam = JRMCoreH.trl("saoc", JRMCoreH.attrStat[JRMCoreH.Pwrtyp][2]);
                Stat = JRMCoreH.cldgy + statNam + ": " + JRMCoreH.cldr + stat;
                statAttr = "VIT";
                drawDetailsOmni(Stat, JRMCoreH.cct(StatIncreaseDescVit, new Object[] { statAttr, Integer.valueOf(inc) }), guiLeft + 5 + 128, guiTop + 5 + i * 10, x, y, var8);

                startAttr = JRMCoreH.cldgy + "AGI: " + JRMCoreH.cldr + DEX + (AGIwb > 0 ? JRMCoreH.clgd + " +" + AGIwb : "");
                AttrDesc = JRMCoreH.trl("saoc", JRMCoreH.attrDsc[JRMCoreH.Pwrtyp][1]);
                drawDetailsOmni(startAttr, AttrDesc, guiLeft + 5 + 10, guiTop + 5 + i * 10, x, y, var8);i++;

                startAttr = JRMCoreH.cldgy + "VIT: " + JRMCoreH.cldr + CON;
                AttrDesc = JRMCoreH.trl("saoc", JRMCoreH.attrDsc[JRMCoreH.Pwrtyp][2]);
                drawDetailsOmni(startAttr, AttrDesc, guiLeft + 5 + 10, guiTop + 5 + i * 10, x, y, var8);i++;

                startAttr = JRMCoreH.sao_ap + JRMCoreH.cldgy + " AP";
                AttrDesc = JRMCoreH.trl("saoc", "AtrPoints");
                if (JRMCoreH.sao_ap > 0) {
                    drawDetailsOmni(startAttr, AttrDesc, guiLeft + 5 + 10, guiTop + 5 + i * 10, x, y, var8);
                }
                i++;
            }
            else
            {
                int stat = JRMCoreH.stat(JRMCoreH.Pwrtyp, 0, STR, JRMCoreH.Race, JRMCoreH.Class, 0.0F);
                float inc = JRMCoreH.statInc[JRMCoreH.Pwrtyp][0];
                String statNam = JRMCoreH.trl("jrmc", JRMCoreH.attrStat[JRMCoreH.Pwrtyp][0]);
                String Stat = JRMCoreH.cldgy + statNam + ": " + JRMCoreH.cldr + stat;
                String statAttr = "STR";
                drawDetailsOmni(Stat, JRMCoreH.cct(StatIncreaseDesc, new Object[] { statAttr, Float.valueOf(inc) }), guiLeft + 5 + 128, guiTop + 5 + i * 10, x, y, var8);

                String startAttr = JRMCoreH.cldgy + "STR: " + JRMCoreH.cldr + STR;
                String AttrDesc = JRMCoreH.trl("jrmc", JRMCoreH.attrDsc[JRMCoreH.Pwrtyp][0]);
                drawDetailsOmni(startAttr, AttrDesc, guiLeft + 5, guiTop + 5 + i * 10, x, y, var8);i++;

                stat = JRMCoreH.stat(JRMCoreH.Pwrtyp, 2, CON, JRMCoreH.Race, JRMCoreH.Class, 0.0F);
                inc = JRMCoreH.statInc[JRMCoreH.Pwrtyp][2];
                statNam = JRMCoreH.trl("jrmc", JRMCoreH.attrStat[JRMCoreH.Pwrtyp][2]);
                Stat = JRMCoreH.cldgy + statNam + ": " + JRMCoreH.cldr + stat;
                statAttr = "CON";
                drawDetailsOmni(Stat, JRMCoreH.cct(StatIncreaseDesc, new Object[] { statAttr, Float.valueOf(inc) }), guiLeft + 5 + 128, guiTop + 5 + i * 10, x, y, var8);

                startAttr = JRMCoreH.cldgy + "CON: " + JRMCoreH.cldr + CON;
                AttrDesc = JRMCoreH.trl("jrmc", JRMCoreH.attrDsc[JRMCoreH.Pwrtyp][2]);
                drawDetailsOmni(startAttr, AttrDesc, guiLeft + 5, guiTop + 5 + i * 10, x, y, var8);i++;
            }
        }
        if (this.guiID == 11)
        {
            int xSize = 256;
            int ySize = 159;
            int guiLeft = (this.width - xSize) / 2;
            int guiTop = (this.height - ySize) / 2;
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            ResourceLocation guiLocation = new ResourceLocation(wish);
            mc.renderEngine.bindTexture(guiLocation);
            drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);

            menuButtonsOmni("SK");
            var8.drawString(JRMCoreH.numSep(JRMCoreH.curTP) + " " + "Training Points", guiLeft + 10, guiTop + 5 + 145, 0);
            if (JRMCoreH.Pwrtyp == 2)
            {
                String l = JRMCoreH.trl("jrmc", "Learn");
                int lw = this.fontRendererObj.getStringWidth(l);
                this.buttonList.add(new JRMCoreGuiButtons01(65336, guiLeft + 250 - lw, guiTop + 5, lw, l, JRMCoreH.techNCCol[1]));
            }
            double[][] rSklsMR = (double[][])null;

            double[][] cSklsMR = (double[][])null;
            String mod;
            String[] rSkls;
            int[][] rSklsLvl;
            String[] rSklsNms;
            String[] cSkls;
            int[][] cSklsLvl;
            String[] cSklsNms;
            String[] skls;
            int[] sklsUps;
            int[][] sklsLvl;
            String[] sklsNms;
            double[][] sklsMR;

            rSkls = JRMCoreH.vlblRSkls;
            rSklsLvl = JRMCoreH.vlblRSklsLvl;
            rSklsNms = JRMCoreH.vlblRSklsNms;
            rSklsMR = JRMCoreH.vlblRSklsMR;
            cSkls = JRMCoreH.vlblCSkls;
            cSklsLvl = JRMCoreH.vlblCSklsLvl;
            cSklsNms = JRMCoreH.vlblCSklsNms;

            skls = JRMCoreH.vlblSkls;
            sklsUps = JRMCoreH.vlblSklsUps;
            sklsLvl = JRMCoreH.vlblSklsLvl;
            sklsNms = JRMCoreH.vlblSklsNms;
            sklsMR = JRMCoreH.vlblSklsMR;
            mod = "dbc";

            int m = JRMCoreH.SklSlt_MU();
            boolean ssm = JRMCoreH.SklSlt_EML();
            int ml = JRMCoreH.SklSlt_AML();

            int b = 0;
            if ((JRMCoreH.PlyrSkillX != null) && (!JRMCoreH.PlyrSkillX.contains("pty")) && (JRMCoreH.PlyrSkillX.length() > 1))
            {
                b++;
                String un = JRMCoreH.SklName(JRMCoreH.PlyrSkillX, rSkls, rSklsNms, JRMCoreH.Race);
                List<String> vlblRSklsNmsList = Arrays.asList(JRMCoreH.vlblRSklsNms);
                if(vlblRSklsNmsList.contains(un))
                {
                    name = un;
                }
                else
                {
                    name = JRMCoreH.trl(mod, un);
                }
                int n = Integer.parseInt(JRMCoreH.PlyrSkillX.substring(2));

                var8.drawString("" + name + (n <= 9 ? " lvl: " + n : ""), guiLeft + 5, guiTop + 15 + b * 10, 0);

                int nw = this.fontRendererObj.getStringWidth(name);
                drawDetailsOmni(JRMCoreH.trl(mod, un + "Desc"), guiLeft + 5, guiTop + 15 + b * 10 + 2, nw, 6, x, y, var8);

                int mz = JRMCoreH.SGMR_X(JRMCoreH.PlyrSkillX, JRMCoreH.Race, rSklsMR);
                int mx = m + mz;
                ssm = JRMCoreH.SklSlt(JRMCoreH.PlyrAttrbts[4], mx);
                if ((JRMCoreH.rSai(JRMCoreH.Race)) && (n < 7)) {
                    this.buttonList.add(new JRMCoreGuiButtonsA3(390, guiLeft - 10, guiTop + 13 + b * 10, 10, 2, ssm));
                } else if ((JRMCoreH.Race == 4) && (n < 6)) {
                    this.buttonList.add(new JRMCoreGuiButtonsA3(390, guiLeft - 10, guiTop + 13 + b * 10, 10, 2, ssm));
                } else if ((JRMCoreH.Race == 5) && (n < 4)) {
                    this.buttonList.add(new JRMCoreGuiButtonsA3(390, guiLeft - 10, guiTop + 13 + b * 10, 10, 2, ssm));
                } else if ((JRMCoreH.Race != 4 && JRMCoreH.Race < 4) && (n < 5)) {
                    this.buttonList.add(new JRMCoreGuiButtonsA3(390, guiLeft - 10, guiTop + 13 + b * 10, 10, 2, ssm));
                }
                if ((JRMCoreH.Race == 4) && (JRMCoreHDBC.auc(n)) && (!JRMCoreH.data(mc.thePlayer.getCommandSenderName(), 16, "").contains(";"))) {
                    this.buttonList.add(new JRMCoreGuiButtonsA3(392, guiLeft + 10 + var8.getStringWidth(name + (n < 7 ? " lvl: " + n : "")), guiTop + 13 + b * 10, 20, 1));
                }
                String[] s3 = rSkls;
                int[][] s4 = rSklsLvl;
                int s2 = 0;
                int s5 = 0;
                for (int i1 = 0; i1 < s3.length; i1++) {
                    if (JRMCoreH.PlyrSkillX.contains(s3[i1]))
                    {
                        int l = s4[JRMCoreH.Race].length >= n + 1 ? n : s4[JRMCoreH.Race].length - 1;
                        s2 = s4[JRMCoreH.Race][l] * (n + 1);
                        s5 = s4[JRMCoreH.Race][l];
                        break;
                    }
                }
                s2 = s5;

                //Set max race skill transformation
                String s = n < (JRMCoreH.Race == 4 ? 6 : JRMCoreH.Race == 5 ? 4 : (JRMCoreH.Race == 1) || (JRMCoreH.Race == 2) ? 7 : 5) ? "C: " + s2 + " M: " + mz : JRMCoreH.trl("jrmc", "Maxed");
                var8.drawString(s, guiLeft + 250 - var8.getStringWidth(s), guiTop + 15 + b * 10, 0);
            }
            if ((JRMCoreH.PlyrSkillY != null) && (!JRMCoreH.PlyrSkillY.contains("pty")) && (!JRMCoreH.PlyrSkillY.contains("Sai")) && (JRMCoreH.Race != 1) && (JRMCoreH.Race != 2) && (JRMCoreH.PlyrSkillY.length() > 1))
            {
                b++;
                String un = JRMCoreH.SklName(JRMCoreH.PlyrSkillY, cSkls, cSklsNms);
                String name = un;

                int n = Integer.parseInt(JRMCoreH.PlyrSkillY.substring(2));
                var8.drawString("" + ((JRMCoreH.Race == 1) || (JRMCoreH.Race == 2) ? JRMCoreH.TransSaiUpNam[n] : new StringBuilder().append(name).append(" lvl: ").append(n + 1).toString()), guiLeft + 5, guiTop + 15 + b * 10, 0);

                int nw = this.fontRendererObj.getStringWidth(name);
                drawDetailsOmni(JRMCoreH.trl(mod, name + "Desc"), guiLeft + 5, guiTop + 15 + b * 10 + 2, nw, 6, x, y, var8);

                int mz = JRMCoreH.SGMR(JRMCoreH.PlyrSkillY, cSkls, cSklsMR);
                int mx = m + mz;
                ssm = JRMCoreH.SklSlt(JRMCoreH.PlyrAttrbts[4], mx);
                if (n <= 8) {
                    this.buttonList.add(new JRMCoreGuiButtonsA3(391, guiLeft - 10, guiTop + 13 + b * 10, 10, 2, ssm));
                }
                String[] s3 = cSkls;
                int[][] s4 = cSklsLvl;
                int s2 = 0;
                int s5 = 0;
                for (int i1 = 0; i1 < s3.length; i1++) {
                    if (JRMCoreH.PlyrSkillY.contains(s3[i1]))
                    {
                        int l = s4[i1].length >= n + 1 ? n : s4[i1].length - 1;
                        s2 = s4[i1][l] * (n + 1);
                        break;
                    }
                }
                String s = n <= 8 ? "C: " + s2 + " M: " + mz : JRMCoreH.trl("jrmc", "Maxed");
                var8.drawString(s, guiLeft + 250 - var8.getStringWidth(s), guiTop + 15 + b * 10, 0);
            }
            int s = 0;
            if (JRMCoreH.PlyrSkills != null)
            {
                int m1 = JRMCoreH.PlyrSkills.length;
                float m2 = 5.0F;
                int sz = 10;
                this.scrollMouseJumpOmni = 1;
                if (m1 > sz)
                {
                    if (m1 - m2 < this.scrollOmni) {
                        this.scrollOmni = ((int)(m1 - m2));
                    } else if (this.scrollOmni < 0) {
                        this.scrollOmni = 0;
                    }
                    if ((this.mousePressedOmni) && (!JRMCoreGuiButtonsA1.clicked)) {
                        this.scrollOmni = ((int)((m1 - m2) * scrollSideOmni));
                    } else {
                        scrollSideOmni = JRMCoreGuiSliderX00.sliderValue = this.scrollOmni / (m1 - m2);
                    }
                }
                else
                {
                    this.scrollOmni = 0;
                }
                if (m1 > sz)
                {
                    if (scrollSideOmni > 0.0F) {
                        this.buttonList.add(new JRMCoreGuiButtonsA1(43, guiLeft + xSize / 2 + 110 + 18, guiTop + 80 - 70, "i"));
                    }
                    if (scrollSideOmni < 1.0F) {
                        this.buttonList.add(new JRMCoreGuiButtonsA1(44, guiLeft + xSize / 2 + 110 + 18, guiTop + 80 + 60, "v"));
                    }
                    this.buttonList.add(new JRMCoreGuiSliderX00(1000000, guiLeft + xSize / 2 + 110 + 18, guiTop + 25, this.mousePressedOmni, scrollSideOmni, 1.0F));
                }
                for (int i = this.scrollOmni; i < (JRMCoreH.PlyrSkills.length > this.scrollOmni + 10 ? this.scrollOmni + 10 : JRMCoreH.PlyrSkills.length); i++)
                {
                    String curSkl = JRMCoreH.PlyrSkills[i];
                    if ((!curSkl.contains("pty")) && (curSkl.length() > 2))
                    {
                        b++;
                        String un = JRMCoreH.SklName(curSkl, skls, sklsNms);
                        String name = JRMCoreH.trl(mod, un);
                        int n = Integer.parseInt(curSkl.substring(2));
                        int d = (JRMCoreH.pwr_ki()) && (!JRMCoreH.rSai(JRMCoreH.Race)) && (curSkl.contains(JRMCoreH.vlblSkls[9])) ? n : JRMCoreH.SklLvl_m(curSkl, skls, n);
                        n = d;
                        if(curSkl.startsWith("GF") && JRMCoreH.Race == 5)
                        {
                            n = 0;
                        }
                        String nm = "" + name + " lvl: " + (n + 1);
                        var8.drawString(nm, guiLeft + 5, guiTop + 20 + b * 10, 0);

                        String a = "";
                        if (JRMCoreH.pwr_ki()) {
                            switch (JRMCoreH.SklID(curSkl, skls))
                            {
                                case 7:
                                    a = "" + JRMCoreConfig.SklMedCat; break;
                            }
                        } else if (JRMCoreH.pwr_cha()) {
                            switch (JRMCoreH.SklID(curSkl, skls))
                            {
                                case 11:
                                    a = "" + JRMCoreConfig.SklMedCat; break;
                            }
                        }
                        int nw = this.fontRendererObj.getStringWidth(name);
                        drawDetailsOmni(JRMCoreH.trl(mod, un + "Desc" + a), guiLeft + 5, guiTop + 20 + b * 10 + 2, nw, 6, x, y, var8);

                        this.buttonList.add(new JRMCoreGuiButtonsA3(360 + i, guiLeft + 243, guiTop + 20 + b * 10 - 2, 10, 3));

                        int mz = JRMCoreH.SGMR(curSkl, skls, sklsMR);
                        int mx = m + mz;
                        ssm = JRMCoreH.SklSlt(JRMCoreH.PlyrAttrbts[4], mx);

                        d = (JRMCoreH.pwr_ki()) && (!JRMCoreH.rSai(JRMCoreH.Race)) && (curSkl.contains(JRMCoreH.vlblSkls[9])) ? -1 : JRMCoreH.SklInit(curSkl, skls, sklsUps);
                        if (n <= d) {
                            this.buttonList.add(new JRMCoreGuiButtonsA3(330 + i, guiLeft - 10, guiTop + 18 + b * 10, 10, 2, ssm));
                        }
                        String[] s3 = skls;
                        int[][] s4 = sklsLvl;
                        int s2 = 0;
                        int s5 = 0;
                        for (int i1 = 0; i1 < s3.length; i1++) {
                            if (curSkl.contains(s3[i1]))
                            {
                                int l = s4[i1].length >= n + 1 ? n : s4[i1].length - 1;
                                s2 = s4[i1][l] * (n + 1);
                                break;
                            }
                        }
                        String st = n <= JRMCoreH.SklInit(curSkl, skls, sklsUps) ? "C: " + s2 + " M: " + mz : JRMCoreH.trl("jrmc", "Maxed");
                        if(curSkl.startsWith("GF") && JRMCoreH.Race == 5)
                        {
                            st = JRMCoreH.trl("jrmc", "Maxed");
                        }
                        var8.drawString(st, guiLeft + 240 - var8.getStringWidth(st), guiTop + 20 + b * 10, 0);
                    }
                    s++;
                }
            }
            drawDetailsOmni("" + JRMCoreH.trl("jrmc", "AvailableMind") + ": " + ml, JRMCoreH.PlyrAttrbts[4] >=
                    kqGW3Z(false) ?
                    JRMCoreH.trl("jrmc", "SkillSysMax", new Object[] { JRMCoreH.clpr + JRMCoreH.PlyrSkills.length + JRMCoreH.cldgy }) :

                    JRMCoreH.trl("jrmc", "SkillSysNext", new Object[] {JRMCoreH.cllr +
                            JRMCoreH.attrNms(JRMCoreH.Pwrtyp, 4) + JRMCoreH.cldgy, JRMCoreH.clpr + JRMCoreH.PlyrSkills.length + JRMCoreH.cldgy }), guiLeft + 5, guiTop + 5, x, y, var8);
            if ((this.confirmationWindowOmni) && (this.IDtoProcessConfirmForOmni >= 0))
            {
                xSize = 140;
                ySize = 71;
                int wpx = 60;
                int wpy = 50;
                GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
                guiLocation = new ResourceLocation(wish);
                mc.renderEngine.bindTexture(guiLocation);
                drawTexturedModalRect(guiLeft + wpx, guiTop + wpy, 0, 159, xSize, ySize);

                String curSkl = JRMCoreH.PlyrSkills[this.IDtoProcessConfirmForOmni];
                if ((!curSkl.contains("pty")) && (curSkl.length() > 2))
                {
                    b++;
                    String name = JRMCoreH.Pwrtyp == 1 ? JRMCoreH.trl("dbc", JRMCoreH.SklName(curSkl, skls, sklsNms)) : JRMCoreH.trl("nc", JRMCoreH.SklName(curSkl, skls, sklsNms));
                    JRMCoreH.txt(JRMCoreH.trl("jrmc", "delskillconfirm", new Object[] { name }), JRMCoreH.cldr, 0, true, guiLeft + wpx + 5, guiTop + wpy + 5, xSize - 10);

                    this.buttonList.add(new JRMCoreGuiButtons00(300 + this.IDtoProcessConfirmForOmni, guiLeft + 5 + wpx, guiTop + 45 + wpy, 40, 20, JRMCoreH.trl("jrmc", "Yes"), 0));
                }
                this.buttonList.add(new JRMCoreGuiButtons00(399, guiLeft + 95 + wpx, guiTop + 45 + wpy, 40, 20, JRMCoreH.trl("jrmc", "No"), 0));
            }
        }
        ufc = false;
        int k;

        for (k = 0; k < this.buttonList.size(); ++k)
        {
            ((GuiButton)this.buttonList.get(k)).drawButton(this.mc, x, y);
        }

        for (k = 0; k < this.labelList.size(); ++k)
        {
            ((GuiLabel)this.labelList.get(k)).func_146159_a(this.mc, x, y);
        }

        if (!detailListOmni.isEmpty())
        {
            Object[] o = (Object[]) detailListOmni.get(0);
            String desc = (String)o[0];
            int ll = Integer.parseInt("" + o[6]);
            int descw = var8.getStringWidth(desc);
            int desch = 1 + var8.getStringWidth(desc) / ll;
            mc.renderEngine.bindTexture(new ResourceLocation("jinryuumodscore:allw.png"));
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.8F);
            desch = JRMCoreH.txt(desc, (String)o[1], Integer.parseInt("" + o[2]), false, 0, 0, ll);
            int xp = 0;
            int yp = 0;
            if (var6 < x + (descw < ll ? descw : ll) + 10) {
                xp += var6 - (x + (descw < ll ? descw : ll)) - 10;
            }
            if (var7 < y + desch * 10 + 10) {
                yp = -(desch * 10 + 20);
            }
            drawTexturedModalRect(x + xp, y + 10 + yp, 5, 5, (descw < ll ? descw : ll) + 10, desch * 10 + 10);
            JRMCoreH.txt(desc, (String)o[1], Integer.parseInt("" + o[2]), Boolean.parseBoolean("" + o[3]), Integer.parseInt("" + o[4]) + xp, Integer.parseInt("" + o[5]) + 10 + yp, ll);
            detailListOmni.clear();
        }

        Utils.setPrivateFinalStatic(tickField, tickOmni);

        Utils.setPrivateFinalStatic(scrollSideField, scrollSideOmni);

        Utils.setPrivateFinalStatic(dnsField, dnsOmni);

        Utils.setPrivateFinalStatic(dnsSentField, dnsSentOmni);

        Utils.setPrivateFinalStatic(dnsHField, dnsHOmni);

        Utils.setPrivateFinalStatic(dnsHSentField, dnsHSentOmni);

        Utils.setPrivateFinalStatic(dnsauField, dnsauOmni);

        Utils.setPrivateFinalStatic(ptchField, ptchOmni);

        Utils.setPrivateFinalStatic(detailListField, detailListOmni);
    }

    private void align(String icons)
    {
        int ySize2 = this.ySize - 10;
        int guiLeft2 = (this.width - this.xSize) / 2;
        int guiTop2 = (this.height - ySize2) / 2;
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        ResourceLocation tx = new ResourceLocation(icons);
        mc.renderEngine.bindTexture(tx);
        drawTexturedModalRect(guiLeft2, guiTop2 - 10, 0, 169, this.xSize, 5);
        int max = this.xSize - 20;
        if (max < 1) {
            max = 1;
        }
        double maxperc = max * 0.01D;
        int var22 = (int)(maxperc * JRMCoreH.align);
        if (var22 > this.xSize) {
            var22 = this.xSize;
        }
        int xSize2 = 5;
        int ySize3 = this.ySize - 10;
        int guiLeft3 = (this.width - xSize2) / 2 - max / 2 + var22;
        int guiTop3 = guiTop2 - 1;
        drawTexturedModalRect(guiLeft3, guiTop3 - 10, 0, 175, xSize2, 7);
    }

    public void menuButtonsOmni(String st) {
        menuButtonsOmni(st, this.buttonList, this.guiLeft, this.guiTop, this.ySize, this.xOmni, this.yOmni, this.fontRendererObj);
    }

    public static void menuButtonsOmni(String st, List buttonList, int guiLeft, int guiTop, int ySize, int x, int y, FontRenderer fontRendererObj)
    {
        int i = 0;
        int rid = 0;
        buttonList.add(new JRMCoreGuiButtons00(10, guiLeft - 22, guiTop + 145, 20, 20, "X", 0));
        String name = "";
        if ((!JRMCoreConfig.ssurl.contains("empty")) && (JRMCoreConfig.ssurl.contains("ttp")))
        {
            name = "Server Shop";
            buttonList.add(new JRMCoreGuiButtons02(3099, guiLeft + i * 21, guiTop + ySize + 2, "$", st.equals("ST") ? 1 : 0, Color.GREEN.darker().darker().getRGB()));
            drawDetailsOmni(JRMCoreH.cct(name, new Object[0]), guiLeft + i * 21, guiTop + ySize + 2 + 1, 20, 20, x, y, fontRendererObj);i++;
        }
        name = JRMCoreH.trl("jrmc", "News");
        buttonList.add(new JRMCoreGuiButtons03(31, guiLeft + i * 21, guiTop + ySize + 2, name.substring(0, 2).toUpperCase(), st.equals("News") ? 1 : 0, 8046079, rid = 0));
        drawDetailsOmni(JRMCoreH.cct(name, new Object[0]), guiLeft + i * 21, guiTop + ySize + 2 + 1, 20, 20, x, y, fontRendererObj);i++;

        int tabX = -60;int tabY = -100;
        name = JRMCoreH.trl("jrmc", "Help");
        buttonList.add(new JRMCoreGuiButtons03(10001, guiLeft + tabX, guiTop + ySize + 2 + tabY, name.substring(0, 2).toUpperCase(), st.equals("Help") ? 1 : 0, 8046079, rid = 8));
        drawDetailsOmni(JRMCoreH.cct(name, new Object[0]), guiLeft + tabX, guiTop + ySize + 2 + 1 + tabY, 20, 20, x, y, fontRendererObj);

        tabY = -80;
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        fontRendererObj.drawString(name, guiLeft + 1 + tabX, guiTop + ySize + 2 + tabY, 0);
        fontRendererObj.drawString(name, guiLeft - 1 + tabX, guiTop + ySize + 2 + tabY, 0);
        fontRendererObj.drawString(name, guiLeft + tabX, guiTop + 1 + ySize + 2 + tabY, 0);
        fontRendererObj.drawString(name, guiLeft + tabX, guiTop - 1 + ySize + 2 + tabY, 0);
        fontRendererObj.drawString(name, guiLeft + tabX, guiTop + ySize + 2 + tabY, 16770587);

        tabX = -60;tabY = -70;
        name = JRMCoreH.trl("jrmc", "CltSettings");
        buttonList.add(new JRMCoreGuiButtons03(109, guiLeft + tabX, guiTop + ySize + 2 + tabY, name.substring(0, 2).toUpperCase(), st.equals("CltSettings") ? 1 : 0, 8046079, rid = 9));
        drawDetailsOmni(JRMCoreH.cct(name, new Object[0]), guiLeft + tabX, guiTop + ySize + 2 + 1 + tabY, 20, 20, x, y, fontRendererObj);
        tabX = -85;
        tabY = -50;
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        fontRendererObj.drawString(name, guiLeft + 1 + tabX, guiTop + ySize + 2 + tabY, 0);
        fontRendererObj.drawString(name, guiLeft - 1 + tabX, guiTop + ySize + 2 + tabY, 0);
        fontRendererObj.drawString(name, guiLeft + tabX, guiTop + 1 + ySize + 2 + tabY, 0);
        fontRendererObj.drawString(name, guiLeft + tabX, guiTop - 1 + ySize + 2 + tabY, 0);
        fontRendererObj.drawString(name, guiLeft + tabX, guiTop + ySize + 2 + tabY, 8107007);
        if ((JRMCoreH.Accepted != 0) && (JRMCoreH.Accepted != 2))
        {
            name = JRMCoreH.trl("jrmc", "CharSheet");
            buttonList.add(new JRMCoreGuiButtons03(100, guiLeft + i * 21, guiTop + ySize + 2, name.substring(0, 2).toUpperCase(), st.equals("CH") ? 1 : 0, 8046079, rid = 1));
            drawDetailsOmni(JRMCoreH.cct(name, new Object[0]), guiLeft + i * 21, guiTop + ySize + 2 + 1, 20, 20, x, y, fontRendererObj);i++;
        }
        if (JRMCoreH.Pwrtyp != 0) {
            if ((JRMCoreH.Pwrtyp == 1) || (JRMCoreH.Pwrtyp == 2))
            {
                name = JRMCoreH.trl("jrmc", "Skills");
                buttonList.add(new JRMCoreGuiButtons03(101, guiLeft + i * 21, guiTop + ySize + 2, name.substring(0, 2).toUpperCase(), st.equals("SK") ? 1 : 0, 8046079, rid = 2));
                drawDetailsOmni(JRMCoreH.cct(name, new Object[0]), guiLeft + i * 21, guiTop + ySize + 2 + 1, 20, 20, x, y, fontRendererObj);i++;
                name = JRMCoreH.Pwrtyp == 1 ? JRMCoreH.trl("jrmc", "KiTechniques") : JRMCoreH.trl("jrmc", "Jutsu");
                buttonList.add(new JRMCoreGuiButtons03(102, guiLeft + i * 21, guiTop + ySize + 2, name.substring(0, 2).toUpperCase(), st.equals("TE") ? 1 : 0, 8046079, rid = 3));
                drawDetailsOmni(JRMCoreH.cct(name, new Object[0]), guiLeft + i * 21, guiTop + ySize + 2 + 1, 20, 20, x, y, fontRendererObj);i++;

                name = JRMCoreH.trl("jrmc", "Training");
                buttonList.add(new JRMCoreGuiButtons03(108, guiLeft + i * 21, guiTop + ySize + 2, name.substring(0, 2).toUpperCase(), st.equals("TR") ? 1 : 0, 8046079, rid = 6));
                drawDetailsOmni(JRMCoreH.cct(name, new Object[0]), guiLeft + i * 21, guiTop + ySize + 2 + 1, 20, 20, x, y, fontRendererObj);i++;

                name = JRMCoreH.Pwrtyp == 2 ? JRMCoreH.trl("nc", "StorySystem") : JRMCoreH.Pwrtyp == 1 ? JRMCoreH.trl("dbc", "SagaSystem") : JRMCoreH.trl("jrmc", "Story");
                buttonList.add(new JRMCoreGuiButtons03(60, guiLeft + i * 21, guiTop + ySize + 2, name.substring(0, 2).toUpperCase(), st.equals("Journal") ? 1 : 0, 8046079, rid = 7));
                drawDetailsOmni(JRMCoreH.cct(name, new Object[0]), guiLeft + i * 21, guiTop + ySize + 2 + 1, 20, 20, x, y, fontRendererObj);i++;
            }
        }
        if (JRMCoreH.Pwrtyp == 1)
        {
            name = JRMCoreH.trl("jrmc", "GroupManager");
            buttonList.add(new JRMCoreGuiButtons03(62, guiLeft + i * 21, guiTop + ySize + 2, name.substring(0, 2).toUpperCase(), st.equals("Group") ? 1 : 0, 8046079, rid = 4));
            drawDetailsOmni(JRMCoreH.cct(name, new Object[0]), guiLeft + i * 21, guiTop + ySize + 2 + 1, 20, 20, x, y, fontRendererObj);i++;
        }
        name = JRMCoreH.trl("jrmc", "ServerConfig");
        buttonList.add(new JRMCoreGuiButtons03(32, guiLeft + i * 21, guiTop + ySize + 2, name.substring(0, 2).toUpperCase(), st.equals("Server") ? 1 : 0, 8046079, rid = 5));
        drawDetailsOmni(JRMCoreH.cct(name, new Object[0]), guiLeft + i * 21, guiTop + ySize + 2 + 1, 20, 20, x, y, fontRendererObj);i++;
        if (JRMCoreH.JYC())
        {
            name = JRMCoreH.trl("jrmc", "Calendar");
            buttonList.add(new JRMCoreGuiButtons02(4902, guiLeft + i * 21, guiTop + ySize + 2, name.substring(0, 2).toUpperCase(), st.equals("Calendar") ? 1 : 0, 8046079));
            drawDetailsOmni(JRMCoreH.cct(name, new Object[0]), guiLeft + i * 21, guiTop + ySize + 2 + 1, 20, 20, x, y, fontRendererObj);i++;
        }
        if (JRMCoreH.JFC())
        {
            name = JRMCoreH.trl("jrmc", "Family");
            buttonList.add(new JRMCoreGuiButtons02(4903, guiLeft + i * 21, guiTop + ySize + 2, name.substring(0, 2).toUpperCase(), st.equals("Family") ? 1 : 0, 8046079));
            drawDetailsOmni(JRMCoreH.cct(name, new Object[0]), guiLeft + i * 21, guiTop + ySize + 2 + 1, 20, 20, x, y, fontRendererObj);i++;
        }
    }


    private static void drawDetailsOmni(String s1, String s2, int xpos, int ypos, int x, int y, FontRenderer var8) {
        int wpos = var8.getStringWidth(s1);
        var8.drawString(s1, xpos, ypos, 0);
        if (xpos < x && xpos + wpos > x && ypos - 3 < y && ypos + 10 > y) {
            int ll = 200;
            Object[] txt = new Object[]{s2, "", 0, true, x + 5, y + 5, Integer.valueOf(ll)};
            detailListOmni.add(txt);
        }
    }

    private static void drawDetailsOmni(String s1, int xpos, int ypos, int w, int h, int x, int y, FontRenderer var8)
    {
        if (hovered(x, y, xpos, ypos, w, h))
        {
            int ll = 200;
            Object[] txt = { s1, "", Integer.valueOf(0), Boolean.valueOf(true), Integer.valueOf(x + 5), Integer.valueOf(y + 5), Integer.valueOf(ll) };
            detailListOmni.add(txt);
        }
    }
}