package callout.OmniKings.render;

import JinRyuu.JRMCore.*;
import callout.OmniKings.gui.OmniKingsGuiScreen;
import callout.OmniKings.lib.ModVars;
import callout.OmniKings.models.ModelPlayerBioAndroid;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.common.MinecraftForge;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

public class RenderPlayerOmniKings extends RenderPlayer {
    private static final Logger logger = LogManager.getLogger();
    private static final ResourceLocation texture = new ResourceLocation(ModVars.MOD_ID + ":textures/entity/bioAndroid1.png");
    private static final ResourceLocation RES_ITEM_GLINT = new ResourceLocation("textures/misc/enchanted_item_glint.png");
    public ModelPlayerBioAndroid modelBipedMain;
    public ModelBiped modelArmorChestplate;
    public ModelBiped modelArmor;
    private int pl;
    private static float r = 0.0F;
    private static float g = 0.0F;
    private static float b = 0.0F;
    private static int gen = 1;
    private static int state = 0;

    public RenderPlayerOmniKings(float _scaleX, float _scaleY, float _scaleZ, int _state) {
        this.mainModel = new ModelPlayerBioAndroid(_scaleX, _scaleY, _scaleZ, _state);
        this.shadowSize = 0.5F;
        this.modelBipedMain = (ModelPlayerBioAndroid) this.mainModel;
        this.modelArmorChestplate = new ModelBiped(1.0F);
        this.modelArmor = new ModelBiped(0.5F);
        renderManager = RenderManager.instance;
        state = _state;
    }


    @Override
    protected ResourceLocation getEntityTexture(Entity p_110775_1_) {
        return texture;
    }

    public void doRender(AbstractClientPlayer player, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_) {

        GL11.glColor3f(1.0F, 1.0F, 1.0F);
        ItemStack itemstack = player.inventory.getCurrentItem();
        this.modelArmorChestplate.heldItemRight = this.modelArmor.heldItemRight = this.modelBipedMain.heldItemRight = itemstack != null ? 1 : 0;

        if (itemstack != null && player.getItemInUseCount() > 0)
        {
            EnumAction enumaction = itemstack.getItemUseAction();

            if (enumaction == EnumAction.block)
            {
                this.modelArmorChestplate.heldItemRight = this.modelArmor.heldItemRight = this.modelBipedMain.heldItemRight = 3;
            }
            else if (enumaction == EnumAction.bow)
            {
                this.modelArmorChestplate.aimedBow = this.modelArmor.aimedBow = this.modelBipedMain.aimedBow = true;
            }
        }

        this.modelArmorChestplate.isSneak = this.modelArmor.isSneak = this.modelBipedMain.isSneak = player.isSneaking();
        double d3 = p_76986_4_ - (double)player.yOffset;

        if (player.isSneaking() && !(player instanceof EntityPlayerSP))
        {
            d3 -= 0.125D;
        }

        doRender((EntityLivingBase)player, p_76986_2_, d3, p_76986_6_, p_76986_8_, p_76986_9_);
        this.modelArmorChestplate.aimedBow = this.modelArmor.aimedBow = this.modelBipedMain.aimedBow = false;
        this.modelArmorChestplate.isSneak = this.modelArmor.isSneak = this.modelBipedMain.isSneak = false;
        this.modelArmorChestplate.heldItemRight = this.modelArmor.heldItemRight = this.modelBipedMain.heldItemRight = 0;
    }

    public void doRender(EntityLivingBase p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_)
    {
        if (MinecraftForge.EVENT_BUS.post(new RenderLivingEvent.Pre(p_76986_1_, this, p_76986_2_, p_76986_4_, p_76986_6_))) return;
        GL11.glPushMatrix();
        GL11.glDisable(GL11.GL_CULL_FACE);
        this.mainModel.onGround = this.renderSwingProgress(p_76986_1_, p_76986_9_);

        if (this.renderPassModel != null)
        {
            this.renderPassModel.onGround = this.mainModel.onGround;
        }

        this.mainModel.isRiding = p_76986_1_.isRiding();

        if (this.renderPassModel != null)
        {
            this.renderPassModel.isRiding = this.mainModel.isRiding;
        }

        this.mainModel.isChild = p_76986_1_.isChild();

        if (this.renderPassModel != null)
        {
            this.renderPassModel.isChild = this.mainModel.isChild;
        }

        try
        {
            float f2 = this.interpolateRotation(p_76986_1_.prevRenderYawOffset, p_76986_1_.renderYawOffset, p_76986_9_);
            float f3 = this.interpolateRotation(p_76986_1_.prevRotationYawHead, p_76986_1_.rotationYawHead, p_76986_9_);
            float f4;

            if (p_76986_1_.isRiding() && p_76986_1_.ridingEntity instanceof EntityLivingBase)
            {
                EntityLivingBase entitylivingbase1 = (EntityLivingBase)p_76986_1_.ridingEntity;
                f2 = this.interpolateRotation(entitylivingbase1.prevRenderYawOffset, entitylivingbase1.renderYawOffset, p_76986_9_);
                f4 = MathHelper.wrapAngleTo180_float(f3 - f2);

                if (f4 < -85.0F)
                {
                    f4 = -85.0F;
                }

                if (f4 >= 85.0F)
                {
                    f4 = 85.0F;
                }

                f2 = f3 - f4;

                if (f4 * f4 > 2500.0F)
                {
                    f2 += f4 * 0.2F;
                }
            }

            float f13 = p_76986_1_.prevRotationPitch + (p_76986_1_.rotationPitch - p_76986_1_.prevRotationPitch) * p_76986_9_;
            this.renderLivingAt(p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_);
            f4 = this.handleRotationFloat(p_76986_1_, p_76986_9_);
            this.rotateCorpse(p_76986_1_, f4, f2, p_76986_9_);
            float f5 = 0.0625F;
            GL11.glEnable(GL12.GL_RESCALE_NORMAL);
            GL11.glScalef(-1.0F, -1.0F, 1.0F);
            this.preRenderCallback(p_76986_1_, p_76986_9_);
            GL11.glTranslatef(0.0F, -24.0F * f5 - 0.0078125F, 0.0F);
            float f6 = p_76986_1_.prevLimbSwingAmount + (p_76986_1_.limbSwingAmount - p_76986_1_.prevLimbSwingAmount) * p_76986_9_;
            float f7 = p_76986_1_.limbSwing - p_76986_1_.limbSwingAmount * (1.0F - p_76986_9_);

            if (p_76986_1_.isChild())
            {
                f7 *= 3.0F;
            }

            if (f6 > 1.0F)
            {
                f6 = 1.0F;
            }

            GL11.glEnable(GL11.GL_ALPHA_TEST);
            this.mainModel.setLivingAnimations(p_76986_1_, f7, f6, p_76986_9_);
            this.renderModel(p_76986_1_, f7, f6, f4, f3 - f2, f13, f5);
            int j;
            float f8;
            float f9;
            float f10;

            for (int i = 0; i < 4; ++i)
            {
                j = this.shouldRenderPass(p_76986_1_, i, p_76986_9_);

                if (j > 0)
                {
                    this.renderPassModel.setLivingAnimations(p_76986_1_, f7, f6, p_76986_9_);
                    this.renderPassModel.render(p_76986_1_, f7, f6, f4, f3 - f2, f13, f5);

                    if ((j & 240) == 16)
                    {
                        this.func_82408_c(p_76986_1_, i, p_76986_9_);
                        this.renderPassModel.render(p_76986_1_, f7, f6, f4, f3 - f2, f13, f5);
                    }

                    if ((j & 15) == 15)
                    {
                        f8 = (float)p_76986_1_.ticksExisted + p_76986_9_;
                        this.bindTexture(RES_ITEM_GLINT);
                        GL11.glEnable(GL11.GL_BLEND);
                        f9 = 0.5F;
                        GL11.glColor4f(f9, f9, f9, 1.0F);
                        GL11.glDepthFunc(GL11.GL_EQUAL);
                        GL11.glDepthMask(false);

                        for (int k = 0; k < 2; ++k)
                        {
                            GL11.glDisable(GL11.GL_LIGHTING);
                            f10 = 0.76F;
                            GL11.glColor4f(0.5F * f10, 0.25F * f10, 0.8F * f10, 1.0F);
                            GL11.glBlendFunc(GL11.GL_SRC_COLOR, GL11.GL_ONE);
                            GL11.glMatrixMode(GL11.GL_TEXTURE);
                            GL11.glLoadIdentity();
                            float f11 = f8 * (0.001F + (float)k * 0.003F) * 20.0F;
                            float f12 = 0.33333334F;
                            GL11.glScalef(f12, f12, f12);
                            GL11.glRotatef(30.0F - (float)k * 60.0F, 0.0F, 0.0F, 1.0F);
                            GL11.glTranslatef(0.0F, f11, 0.0F);
                            GL11.glMatrixMode(GL11.GL_MODELVIEW);
                            this.renderPassModel.render(p_76986_1_, f7, f6, f4, f3 - f2, f13, f5);
                        }

                        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
                        GL11.glMatrixMode(GL11.GL_TEXTURE);
                        GL11.glDepthMask(true);
                        GL11.glLoadIdentity();
                        GL11.glMatrixMode(GL11.GL_MODELVIEW);
                        GL11.glEnable(GL11.GL_LIGHTING);
                        GL11.glDisable(GL11.GL_BLEND);
                        GL11.glDepthFunc(GL11.GL_LEQUAL);
                    }

                    GL11.glDisable(GL11.GL_BLEND);
                    GL11.glEnable(GL11.GL_ALPHA_TEST);
                }
            }

            GL11.glDepthMask(true);
            this.renderEquippedItems(p_76986_1_, p_76986_9_);
            float f14 = p_76986_1_.getBrightness(p_76986_9_);
            j = this.getColorMultiplier(p_76986_1_, f14, p_76986_9_);
            OpenGlHelper.setActiveTexture(OpenGlHelper.lightmapTexUnit);
            GL11.glDisable(GL11.GL_TEXTURE_2D);
            OpenGlHelper.setActiveTexture(OpenGlHelper.defaultTexUnit);

            if ((j >> 24 & 255) > 0 || p_76986_1_.hurtTime > 0 || p_76986_1_.deathTime > 0)
            {
                GL11.glDisable(GL11.GL_TEXTURE_2D);
                GL11.glDisable(GL11.GL_ALPHA_TEST);
                GL11.glEnable(GL11.GL_BLEND);
                GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
                GL11.glDepthFunc(GL11.GL_EQUAL);

                if (p_76986_1_.hurtTime > 0 || p_76986_1_.deathTime > 0)
                {
                    GL11.glColor4f(f14, 0.0F, 0.0F, 0.4F);
                    this.mainModel.render(p_76986_1_, f7, f6, f4, f3 - f2, f13, f5);

                    for (int l = 0; l < 4; ++l)
                    {
                        if (this.inheritRenderPass(p_76986_1_, l, p_76986_9_) >= 0)
                        {
                            GL11.glColor4f(f14, 0.0F, 0.0F, 0.4F);
                            this.renderPassModel.render(p_76986_1_, f7, f6, f4, f3 - f2, f13, f5);
                        }
                    }
                }

                if ((j >> 24 & 255) > 0)
                {
                    f8 = (float)(j >> 16 & 255) / 255.0F;
                    f9 = (float)(j >> 8 & 255) / 255.0F;
                    float f15 = (float)(j & 255) / 255.0F;
                    f10 = (float)(j >> 24 & 255) / 255.0F;
                    GL11.glColor4f(f8, f9, f15, f10);
                    this.mainModel.render(p_76986_1_, f7, f6, f4, f3 - f2, f13, f5);

                    for (int i1 = 0; i1 < 4; ++i1)
                    {
                        if (this.inheritRenderPass(p_76986_1_, i1, p_76986_9_) >= 0)
                        {
                            GL11.glColor4f(f8, f9, f15, f10);
                            this.renderPassModel.render(p_76986_1_, f7, f6, f4, f3 - f2, f13, f5);
                        }
                    }
                }

                GL11.glDepthFunc(GL11.GL_LEQUAL);
                GL11.glDisable(GL11.GL_BLEND);
                GL11.glEnable(GL11.GL_ALPHA_TEST);
                GL11.glEnable(GL11.GL_TEXTURE_2D);
            }

            GL11.glDisable(GL12.GL_RESCALE_NORMAL);
        }
        catch (Exception exception)
        {
            logger.error("Couldn\'t render entity", exception);
        }

        OpenGlHelper.setActiveTexture(OpenGlHelper.lightmapTexUnit);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        OpenGlHelper.setActiveTexture(OpenGlHelper.defaultTexUnit);
        GL11.glEnable(GL11.GL_CULL_FACE);
        GL11.glPopMatrix();
        this.passSpecialRender(p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_);
        MinecraftForge.EVENT_BUS.post(new RenderLivingEvent.Post(p_76986_1_, this, p_76986_2_, p_76986_4_, p_76986_6_));
    }

    protected void renderModel(EntityLivingBase entity, float p_77036_2_, float p_77036_3_, float p_77036_4_, float p_77036_5_, float p_77036_6_, float p_77036_7_)
    {
        if ((JRMCoreH.plyrs != null) && (JRMCoreH.plyrs.length > 0) && (!entity.isInvisible()) && (JRMCoreH.dnn(1))) {
            for (int pl = 0; pl < JRMCoreH.plyrs.length; pl++) {
                if ((JRMCoreH.plyrs[pl].equals(entity.getCommandSenderName())) && (JRMCoreH.data1.length >= JRMCoreH.plyrs.length)) {
                    //get color info
                    String[] s = JRMCoreH.data1[pl].split(";");
                    String dns = s[1];
                    int skintype = JRMCoreH.dnsSkinT(dns);
                    int eyec1 = skintype == 0 ? 0 : JRMCoreH.dnsEyeC1(dns);
                    int eyec2 = skintype == 0 ? 0 : JRMCoreH.dnsEyeC2(dns);
                    if (state == 4)
                    {
                        eyec1 = 16711680;
                        eyec2 = 16711680;
                    }
                    int bodycm = skintype == 0 ? 0 : JRMCoreH.dnsBodyCM(dns);
                    int bodyc2 = skintype == 0 ? 0 : JRMCoreH.dnsBodyC1(dns);
                    int bodyc3 = skintype == 0 ? 0 : JRMCoreH.dnsBodyC2(dns);
                    int bodyc4 = skintype == 0 ? 0 : JRMCoreH.dnsBodyC3(dns);
                    int realState = state;
                    int textureState = realState;
                    int eyeState = realState;
                    if(realState > 2)
                    {
                        textureState = 2;
                    }
                    if(realState > 1)
                    {
                        eyeState = 1;
                    }

                    //Body1
                    bindTexture(new ResourceLocation(ModVars.MOD_ID + ":textures/entity/bioAndroid" + textureState + "Body1.png"));
                    glColor3f(bodycm);
                    this.mainModel.render(entity, p_77036_2_, p_77036_3_, p_77036_4_, p_77036_5_, p_77036_6_, p_77036_7_);
                    //Body2
                    bindTexture(new ResourceLocation(ModVars.MOD_ID + ":textures/entity/bioAndroid" + textureState + "Body2.png"));
                    glColor3f(bodyc2);
                    this.mainModel.render(entity, p_77036_2_, p_77036_3_, p_77036_4_, p_77036_5_, p_77036_6_, p_77036_7_);
                    //Body3
                    if(realState < 2)
                    {
                        bindTexture(new ResourceLocation(ModVars.MOD_ID + ":textures/entity/bioAndroid" + textureState + "Body3.png"));
                        glColor3f(bodyc3);
                        this.mainModel.render(entity, p_77036_2_, p_77036_3_, p_77036_4_, p_77036_5_, p_77036_6_, p_77036_7_);
                    }
                    //Body4
                    bindTexture(new ResourceLocation(ModVars.MOD_ID + ":textures/entity/bioAndroid" + textureState + "Body4.png"));
                    glColor3f(bodyc4);
                    this.mainModel.render(entity, p_77036_2_, p_77036_3_, p_77036_4_, p_77036_5_, p_77036_6_, p_77036_7_);
                    //Body
                    bindTexture(new ResourceLocation(ModVars.MOD_ID + ":textures/entity/bioAndroid" + textureState + "Body.png"));
                    GL11.glColor3f(255, 255, 255);
                    this.mainModel.render(entity, p_77036_2_, p_77036_3_, p_77036_4_, p_77036_5_, p_77036_6_, p_77036_7_);

                    //Eyes
                    //Sclera
                    bindTexture(new ResourceLocation(ModVars.MOD_ID + ":textures/entity/bioAndroid" + eyeState + "Sclera" + OmniKingsGuiScreen.EyesSlcted + ".png"));
                    GL11.glColor3f(255, 255, 255);
                    this.mainModel.render(entity, p_77036_2_, p_77036_3_, p_77036_4_, p_77036_5_, p_77036_6_, p_77036_7_);
                    //CorneaR
                    bindTexture(new ResourceLocation(ModVars.MOD_ID + ":textures/entity/bioAndroid" + eyeState + "EyesRCornea" + OmniKingsGuiScreen.EyesSlcted + ".png"));
                    glColor3f(eyec1);
                    this.mainModel.render(entity, p_77036_2_, p_77036_3_, p_77036_4_, p_77036_5_, p_77036_6_, p_77036_7_);
                    //CorneaL
                    bindTexture(new ResourceLocation(ModVars.MOD_ID + ":textures/entity/bioAndroid" + eyeState + "EyesLCornea" + OmniKingsGuiScreen.EyesSlcted + ".png"));
                    glColor3f(eyec2);
                    this.mainModel.render(entity, p_77036_2_, p_77036_3_, p_77036_4_, p_77036_5_, p_77036_6_, p_77036_7_);

                    //Halo when player is dead
                    if ((JRMCoreH.plyrs[pl].equals(entity.getCommandSenderName())) && (JRMCoreH.aliveState(pl))) {
                        bindTexture(new ResourceLocation("jinryuudragonbc:armor/halo.png"));
                        GL11.glColor3f(1.0F, 1.0F, 1.0F);
                        this.modelBipedMain.renderHalo(0.0625F);
                        break;
                    }

                    //Bruises when players are injured
                    if (JRMCoreConfig.CLIENT_DA19) {
                        GL11.glPushMatrix();
                        GL11.glEnable(3042);
                        GL11.glDisable(2896);
                        GL11.glBlendFunc(770, 771);
                        GL11.glAlphaFunc(516, 0.003921569F);
                        GL11.glDepthMask(false);

                        int[] PlyrAttrbts = new int[JRMCoreH.PlyrAttrbts.length];
                        String[] stri = JRMCoreH.dat14[pl].split(",");
                        for (int i = 0; i < PlyrAttrbts.length; i++) {
                            PlyrAttrbts[i] = Integer.parseInt(stri[i]);
                        }
                        int pwr = Integer.parseInt(s[2]);
                        int cls = Integer.parseInt(s[3]);
                        int maxBody = JRMCoreH.stat(pwr, 2, PlyrAttrbts[2], 5, cls, 0.0F);

                        int curBody = Integer.parseInt(JRMCoreH.data(entity.getCommandSenderName(), 8, "200"));

                        float one = maxBody / 100.0F;
                        int perc = (int) (curBody / one);
                        if (perc < 70) {
                            GL11.glColor4f(0.66F, 0.0F, 1.0F, 1.0F);
                            bindTexture(new ResourceLocation("jinryuumodscore:cc/bruises1.png"));
                            this.mainModel.render(entity, p_77036_2_, p_77036_3_, p_77036_4_, p_77036_5_, p_77036_6_, p_77036_7_);
                        }
                        if (perc < 55) {
                            GL11.glColor4f(0.66F, 0.0F, 1.0F, 1.0F);
                            bindTexture(new ResourceLocation("jinryuumodscore:cc/bruises2.png"));
                            this.mainModel.render(entity, p_77036_2_, p_77036_3_, p_77036_4_, p_77036_5_, p_77036_6_, p_77036_7_);
                        }
                        if (perc < 35) {
                            GL11.glColor4f(0.66F, 0.0F, 1.0F, 1.0F);
                            bindTexture(new ResourceLocation("jinryuumodscore:cc/bruises3.png"));
                            this.mainModel.render(entity, p_77036_2_, p_77036_3_, p_77036_4_, p_77036_5_, p_77036_6_, p_77036_7_);
                        }
                        if (perc < 20) {
                            GL11.glColor4f(0.66F, 0.0F, 1.0F, 1.0F);
                            bindTexture(new ResourceLocation("jinryuumodscore:cc/bruises4.png"));
                            this.mainModel.render(entity, p_77036_2_, p_77036_3_, p_77036_4_, p_77036_5_, p_77036_6_, p_77036_7_);
                        }
                        GL11.glDepthMask(true);
                        GL11.glEnable(2896);
                        GL11.glDisable(3042);
                        GL11.glPopMatrix();
                    }
                }
            }
        }
        else if (!entity.isInvisibleToPlayer(Minecraft.getMinecraft().thePlayer))
        {
            GL11.glPushMatrix();
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.15F);
            GL11.glDepthMask(false);
            GL11.glEnable(GL11.GL_BLEND);
            GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
            GL11.glAlphaFunc(GL11.GL_GREATER, 0.003921569F);
            this.mainModel.render(entity, p_77036_2_, p_77036_3_, p_77036_4_, p_77036_5_, p_77036_6_, p_77036_7_);
            GL11.glDisable(GL11.GL_BLEND);
            GL11.glAlphaFunc(GL11.GL_GREATER, 0.1F);
            GL11.glPopMatrix();
            GL11.glDepthMask(true);
        }
        else
        {
            this.mainModel.setRotationAngles(p_77036_2_, p_77036_3_, p_77036_4_, p_77036_5_, p_77036_6_, p_77036_7_, entity);
        }
    }

    private float interpolateRotation(float p_77034_1_, float p_77034_2_, float p_77034_3_)
    {
        float f3;

        for (f3 = p_77034_2_ - p_77034_1_; f3 < -180.0F; f3 += 360.0F)
        {
            ;
        }

        while (f3 >= 180.0F)
        {
            f3 -= 360.0F;
        }

        return p_77034_1_ + p_77034_3_ * f3;
    }

    public static void glColor3f(int c)
    {
        float h2 = (c >> 16 & 0xFF) / 255.0F;
        float h3 = (c >> 8 & 0xFF) / 255.0F;
        float h4 = (c & 0xFF) / 255.0F;
        float h1 = 1.0F;
        float r = h1 * h2;float g = h1 * h3;float b = h1 * h4;

        GL11.glColor3f(r, g, b);
    }

    private static float getR()
    {
        return r;
    }

    private static float getG()
    {
        return g;
    }

    private static float getB()
    {
        return b;
    }

    private void func_aam(ModelRenderer ra, ModelRenderer la, int id, boolean fp)
    {
        if (id == 0)
        {
            if (JRMCoreConfig.CLIENT_DA18)
            {
                GL11.glPushMatrix();
                GL11.glEnable(3042);
                GL11.glBlendFunc(770, 771);
                GL11.glAlphaFunc(516, 0.003921569F);
                GL11.glDepthMask(false);

                GL11.glTranslatef(-0.5F, -0.1F, -0.1F);

                GL11.glRotatef(40.0F, 0.0F, 0.0F, -1.0F);
                GL11.glRotatef(80.0F, -1.0F, 0.0F, 0.0F);
                GL11.glRotatef(-20.0F, 0.0F, 0.0F, 1.0F);
            }
            ra.render(0.0625F);
            if (JRMCoreConfig.CLIENT_DA18) {
                GL11.glPopMatrix();
            }
        }
        else if ((id == 2) || (id == 3))
        {
            GL11.glPushMatrix();
            GL11.glTranslatef(-0.2F, 0.0F, -0.1F);
            GL11.glRotatef(10.0F, -1.0F, 0.0F, 0.0F);
            GL11.glRotatef(20.0F, 0.0F, 0.0F, -1.0F);
            ra.render(0.0625F);
            GL11.glPopMatrix();
        }
        else if ((id == 4) || (id == 5))
        {
            GL11.glPushMatrix();
            GL11.glTranslatef(-0.2F, 0.4F, -0.1F);
            GL11.glRotatef(10.0F, -1.0F, 0.0F, 0.0F);
            GL11.glRotatef(20.0F, 0.0F, 0.0F, -1.0F);
            GL11.glRotatef(40.0F, 0.0F, 0.0F, 1.0F);
            ra.render(0.0625F);
            GL11.glPopMatrix();
        }
    }

    private void func_aam(int id, boolean s, boolean fp)
    {
        if (s)
        {
            if (id == 0)
            {
                if (JRMCoreConfig.CLIENT_DA18)
                {
                    GL11.glEnable(3042);
                    GL11.glBlendFunc(770, 771);
                    GL11.glAlphaFunc(516, 0.003921569F);
                    GL11.glDepthMask(false);

                    GL11.glTranslatef(-0.5F, -0.1F, -0.1F);

                    GL11.glRotatef(40.0F, 0.0F, 0.0F, -1.0F);
                    GL11.glRotatef(80.0F, -1.0F, 0.0F, 0.0F);
                    GL11.glRotatef(-20.0F, 0.0F, 0.0F, 1.0F);
                }
            }
            else if (id == 1)
            {
                GL11.glTranslatef(-0.2F, -0.4F, -0.8F);
                GL11.glRotatef(50.0F, 1.0F, 0.0F, 1.0F);
                GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(20.0F, 0.0F, 0.0F, 1.0F);
            }
            else if ((id == 2) || (id == 3))
            {
                GL11.glTranslatef(-0.2F, 0.0F, -0.1F);
                GL11.glRotatef(10.0F, -1.0F, 0.0F, 0.0F);
                GL11.glRotatef(20.0F, 0.0F, 0.0F, -1.0F);
            }
            else if ((id == 4) || (id == 5))
            {
                GL11.glTranslatef(-0.2F, 0.4F, -0.1F);
                GL11.glRotatef(10.0F, -1.0F, 0.0F, 0.0F);
                GL11.glRotatef(20.0F, 0.0F, 0.0F, -1.0F);
                GL11.glRotatef(40.0F, 0.0F, 0.0F, 1.0F);
            }
        }
        else if (id == 0)
        {
            if (JRMCoreConfig.CLIENT_DA18)
            {
                GL11.glTranslatef(-0.2F, -0.4F, -0.8F);

                GL11.glRotatef(50.0F, 1.0F, 0.0F, 1.0F);
                GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(20.0F, 0.0F, 0.0F, 1.0F);
            }
        }
        else if (id == 3)
        {
            GL11.glTranslatef(0.1F, -0.2F, -0.5F);
            GL11.glTranslatef(-0.2F, 0.0F, -0.1F);
            GL11.glRotatef(10.0F, -1.0F, 0.0F, 0.0F);
            GL11.glRotatef(20.0F, 0.0F, 0.0F, -1.0F);
            GL11.glRotatef(115.0F, 0.0F, 1.0F, 0.0F);
        }
        else if (id == 5)
        {
            GL11.glTranslatef(-0.2F, -0.4F, -0.8F);
            GL11.glTranslatef(-0.4F, 0.1F, -0.1F);
            GL11.glRotatef(42.0F, -1.0F, 0.0F, 0.0F);
            GL11.glRotatef(10.0F, 0.0F, 0.0F, 1.0F);
            GL11.glRotatef(115.0F, 0.0F, 1.0F, 0.0F);
            GL11.glTranslatef(-0.6F, 0.08F, 0.3F);
        }
    }

    public static void kss(Entity e, boolean b, int id, int kf, int ki)
    {
        GL11.glPushMatrix();

        GL11.glEnable(3042);
        GL11.glDisable(2896);
        GL11.glBlendFunc(770, 771);
        GL11.glAlphaFunc(516, 0.003921569F);
        GL11.glDepthMask(true);
        float scale = 1.0F;
        GL11.glScalef(scale, scale, scale);

        float red = 1.0F;float green = 1.0F;float blue = 1.0F;
        String seee = JRMCoreH.StusEfctsClient((EntityPlayer)e);

        String[] dat5 = JRMCoreH.data(e.getCommandSenderName(), 5, "50;0").split(";");
        int aaa = Integer.parseInt(dat5[0]);
        int ccc = Integer.parseInt(dat5[1]);
        String[] a = JRMCoreH.data(e.getCommandSenderName(), 1, "0;0;0;0;0;0;0;0;0;0;0;0;0;0;0;0;0;0;0").split(";");
        int rrr = Integer.parseInt(a[0]);
        String[] st = JRMCoreH.data(e.getCommandSenderName(), 2, "0;0").split(";");
        int sss = Integer.parseInt(st[0]);

        ccc = ccc > 0 ? ccc : JRMCoreH.Algnmnt_rc(aaa);

        boolean vvv = JRMCoreH.StusEfcts(17, seee);
        boolean lsaaa = JRMCoreH.lgndb(seee, rrr, sss);
        boolean iii = JRMCoreH.StusEfcts(19, seee);
        int color = JRMCoreHDBC.col_fe(2, ccc, 1, rrr, sss, vvv, lsaaa, iii);

        float alfa = 0.6F;float h1 = 1.0F;
        float h2 = (color >> 16 & 0xFF) / 255.0F;
        float h3 = (color >> 8 & 0xFF) / 255.0F;
        float h4 = (color & 0xFF) / 255.0F;

        red = h1 * h2;green = h1 * h3;blue = h1 * h4;
        if (red > 1.0F) {
            red = 1.0F;
        }
        if (green > 1.0F) {
            green = 1.0F;
        }
        if (blue > 1.0F) {
            blue = 1.0F;
        }
        GL11.glTranslatef(-0.06F, -0.05F, 0.0F);
        JRMCoreClient.mc.renderEngine.bindTexture(new ResourceLocation(JRMCoreH.tjjrmc + ":allw.png"));
        if (gen == 2) {
            GL11.glRotatef(7.0F, 0.0F, 0.0F, 1.0F);
        }
        if (id == 0)
        {
            float scl = kf * 0.02F + ki * 0.02F;
            GL11.glTranslatef(0.0F, -scl * 0.7F, 0.0F);
            GL11.glScalef(1.0F, 1.0F + scl, 1.0F);

            float ex = e.ticksExisted;
            float r4 = (MathHelper.cos(ex / 2.0F) / 3.0F - 0.2F) / 8.0F;
            GL11.glTranslatef(0.0F, -r4, 0.0F);
            GL11.glColor4f(red, green, blue, alfa);
            GL11.glRotatef(ex * 25.0F, 0.0F, 1.0F, 0.0F);
            JRMCoreHJBRA.model2.render(0.0625F, id);

            GL11.glTranslatef(0.0F, -0.12F, 0.0F);
            GL11.glScalef(scale * 1.3F, scale * 1.18F, scale * 1.3F);
            GL11.glColor4f(red * 0.8F, green * 0.8F, blue * 0.8F, alfa * 0.8F);
            JRMCoreHJBRA.model2.render(0.0625F, id);
        }
        if (id == 1)
        {
            GL11.glTranslatef(0.0F, 0.6F, 0.0F);
            GL11.glColor4f(red, green, blue, alfa);

            GL11.glRotatef(-3.0F, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(5.0F, 0.0F, 0.0F, 1.0F);

            GL11.glRotatef(90.0F, 1.0F, 0.0F, 0.0F);
            GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
            JRMCoreHJBRA.model2.render(0.0625F, id);
        }
        GL11.glEnable(2896);
        GL11.glDepthMask(true);
        GL11.glPopMatrix();
    }

    public static void ow(boolean b)
    {
        GL11.glPushMatrix();

        float scale = 1.0F;
        GL11.glScalef(scale, scale, scale);
        float f1 = 0.0020714286F;
        GL11.glScalef(f1, f1, f1);

        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        if (b)
        {
            JRMCoreClient.mc.getTextureManager().bindTexture(new ResourceLocation("jinryuujyearsc:watch/hw0.png"));
            GL11.glNormal3f(0.0F, 0.0F, -1.0F);
        }
        else
        {
            JRMCoreClient.mc.renderEngine.bindTexture(new ResourceLocation("jinryuujyearsc:watch/hw0.png"));
        }
        if (gen == 2) {
            GL11.glRotatef(7.0F, 0.0F, 0.0F, 1.0F);
        }
        GL11.glTranslatef(-33.0F, 0.0F, 0.0F);

        GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);

        GL11.glTranslatef(32.0F, 0.0F, 0.0F);

        GL11.glTranslatef(0.0F, 150.0F, 0.0F);

        JRMCoreHC.dtm(-96.0F, 0.0F, 0, 0, 128.0F, 128.0F, -64.0F);

        GL11.glRotatef(-90.0F, 0.0F, 1.0F, 0.0F);

        JRMCoreHC.dtm(-64.0F, 0.0F, 128, 0, 128.0F, 128.0F, -32.0F);
        GL11.glRotatef(-90.0F, 0.0F, 1.0F, 0.0F);
        JRMCoreHC.dtm(-32.0F, 0.0F, 128, 0, 128.0F, 128.0F, -64.0F);
        GL11.glRotatef(-90.0F, 0.0F, 1.0F, 0.0F);
        JRMCoreHC.dtm(-64.0F, 0.0F, 128, 0, 128.0F, 128.0F, -96.0F);
        GL11.glRotatef(-90.0F, 0.0F, 1.0F, 0.0F);

        GL11.glTranslatef(-104.0F, -70.0F, -65.0F);

        GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
        scale = 2.0F;
        float scaley = 4.0F;
        GL11.glScalef(scale, scaley, scale);

        int s = (int)(JRMCoreClient.mc.thePlayer.worldObj.getWorldTime() % 24000L / 1000L) + 6;
        int w = s > 24 ? s - 24 : s;
        w = w == 24 ? 0 : w;
        int m = (int)(JRMCoreClient.mc.thePlayer.worldObj.getWorldTime() % 24000L - (int)(JRMCoreClient.mc.thePlayer.worldObj.getWorldTime() % 24000L / 1000L) * 1000);
        float mi = m / 16.67F;
        int min = (int)mi;
        String var34 = (w < 10 ? "0" + w : Integer.valueOf(w)) + ":" + (min < 10 ? "0" + min : Integer.valueOf(min));

        FontRenderer fontRenderer = JRMCoreClient.mc.fontRenderer;

        String n = "" + var34;int nw = fontRenderer.getStringWidth(n);
        fontRenderer.drawString(n, (int)(-96.0F / scale), -(int)(150.0F / scaley), 0);

        GL11.glPopMatrix();
    }

    private void func_aam2(ModelRenderer ra, ModelRenderer la, int id, boolean fp)
    {
        if (id == 0)
        {
            if (JRMCoreConfig.CLIENT_DA18)
            {
                GL11.glPushMatrix();
                GL11.glTranslatef(-0.2F, -0.4F, -0.8F);
                GL11.glRotatef(50.0F, 1.0F, 0.0F, 1.0F);
                GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(20.0F, 0.0F, 0.0F, 1.0F);
                la.render(0.0625F);
                GL11.glPopMatrix();
            }
        }
        else if (id == 3)
        {
            GL11.glPushMatrix();
            GL11.glTranslatef(0.1F, -0.2F, -0.5F);
            GL11.glTranslatef(-0.2F, 0.0F, -0.1F);
            GL11.glRotatef(10.0F, -1.0F, 0.0F, 0.0F);
            GL11.glRotatef(20.0F, 0.0F, 0.0F, -1.0F);
            GL11.glRotatef(115.0F, 0.0F, 1.0F, 0.0F);
            la.render(0.0625F);
            GL11.glPopMatrix();
        }
        else if (id == 5)
        {
            GL11.glPushMatrix();
            GL11.glTranslatef(-0.2F, -0.4F, -0.8F);
            GL11.glTranslatef(-0.4F, 0.1F, -0.1F);
            GL11.glRotatef(42.0F, -1.0F, 0.0F, 0.0F);
            GL11.glRotatef(10.0F, 0.0F, 0.0F, 1.0F);
            GL11.glRotatef(115.0F, 0.0F, 1.0F, 0.0F);
            GL11.glTranslatef(-0.6F, 0.08F, 0.3F);
            la.render(0.0625F);
            GL11.glPopMatrix();
        }
    }

}