package callout.OmniKings.models;

import JinRyuu.DragonBC.common.DBCConfig;
import JinRyuu.DragonBC.common.DBCKiTech;
import JinRyuu.JBRA.ModelBipedDBC;
import JinRyuu.JRMCore.JRMCoreClient;
import JinRyuu.JRMCore.JRMCoreH;
import JinRyuu.JRMCore.i.ExtendedPlayer;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MathHelper;
import org.lwjgl.opengl.GL11;

import java.util.Random;

public class ModelPlayerBioAndroid extends ModelBipedDBC {
    public ModelRenderer bipedHead;
    public ModelRenderer Head2;
    public ModelRenderer FrontHead;
    public ModelRenderer FrontHeadR;
    public ModelRenderer FrontHeadR2;
    public ModelRenderer FrontHeadR3;
    public ModelRenderer FrontHeadL;
    public ModelRenderer FrontHeadL2;
    public ModelRenderer FrontHeadL3;
    public ModelRenderer Mouth;
    public ModelRenderer EarR;
    public ModelRenderer EarL;
    public ModelRenderer bipedBody;
    public ModelRenderer bipedRightArm;
    public ModelRenderer bipedLeftArm;
    public ModelRenderer bipedRightLeg;
    public ModelRenderer bipedLeftLeg;
    public ModelRenderer WingR;
    public ModelRenderer WingL;
    public ModelRenderer Base;
    public ModelRenderer Tail1;
    public ModelRenderer Tail2;
    public ModelRenderer Tail3;
    public ModelRenderer Tail4;
    public ModelRenderer Tail5;
    public ModelRenderer Tail6;
    public ModelRenderer Point;
    public float scaleX = 1.0F;
    public float scaleY = 1.0F;
    public float scaleZ = 1.0F;
    public int state = 0;

    public ModelPlayerBioAndroid(float _scaleX, float _scaleY, float _scaleZ, int _state) {

        scaleX = _scaleX;
        scaleY = _scaleY;
        scaleZ = _scaleZ;
        state = _state;

        textureWidth = 64;
        textureHeight = 64;

        if (state == 0)
        {
            bipedHead = new ModelRenderer(this);
            bipedHead.setRotationPoint(0.0F, 0.0F, 0.0F);
            bipedHead.cubeList.add(new ModelBox(bipedHead, 0, 2, -3.0F, -7.0F, -4.0F, 6, 7, 6, 0.0F));

            Head2 = new ModelRenderer(this);
            Head2.setRotationPoint(0.0F, 24.0F, 0.0F);
            bipedHead.addChild(Head2);
            Head2.cubeList.add(new ModelBox(Head2, 0, 32, -3.5F, -33.0F, -2.0F, 7, 5, 5, 0.0F));

            FrontHead = new ModelRenderer(this);
            FrontHead.setRotationPoint(-6.0F, -7.0F, -5.0F);
            bipedHead.addChild(FrontHead);
            FrontHead.cubeList.add(new ModelBox(FrontHead, 25, 45, 5F, -2.5F, -0.5F, 2, 4, 1, 0.0F));

            FrontHeadR = new ModelRenderer(this);
            FrontHeadR.setRotationPoint(-3.0F, -7.0F, -4.0F);
            setRotationAngle(FrontHeadR, 0.0F, 0.0F, 0.2618F);
            bipedHead.addChild(FrontHeadR);
            FrontHeadR.cubeList.add(new ModelBox(FrontHeadR, 24, 6, -3.0F, -4.0F, -1.0F, 7, 6, 3, 0.0F));

            FrontHeadR2 = new ModelRenderer(this);
            FrontHeadR2.setRotationPoint(-5.0F, -12.0F, -3.0F);
            setRotationAngle(FrontHeadR2, -0.2618F, 0.0F, -0.4363F);
            bipedHead.addChild(FrontHeadR2);
            FrontHeadR2.cubeList.add(new ModelBox(FrontHeadR2, 28, 6, -2.673F, -2.5164F, -1.0F, 4, 6, 2, 0.0F));

            FrontHeadR3 = new ModelRenderer(this);
            FrontHeadR3.setRotationPoint(-6.4F, -13.0F, -2.0F);
            setRotationAngle(FrontHeadR3, -0.3491F, 0.0F, -0.4363F);
            bipedHead.addChild(FrontHeadR3);
            FrontHeadR3.cubeList.add(new ModelBox(FrontHeadR3, 31, 6, -0.7459F, -5.3446F, -1.4046F, 2, 5, 1, 0.0F));

            FrontHeadL = new ModelRenderer(this);
            FrontHeadL.setRotationPoint(3.0F, -7.0F, -4.0F);
            setRotationAngle(FrontHeadL, 0.0F, 0.0F, -0.2618F);
            bipedHead.addChild(FrontHeadL);
            FrontHeadL.cubeList.add(new ModelBox(FrontHeadL, 24, 6, -4.0F, -4.0F, -1.0F, 7, 6, 3, 0.0F));

            FrontHeadL2 = new ModelRenderer(this);
            FrontHeadL2.setRotationPoint(5.0F, -12.0F, -3.0F);
            setRotationAngle(FrontHeadL2, -0.2618F, 0.0F, 0.4363F);
            bipedHead.addChild(FrontHeadL2);
            FrontHeadL2.cubeList.add(new ModelBox(FrontHeadL2, 28, 6, -1.327F, -2.5164F, -1.0F, 4, 6, 2, 0.0F));

            FrontHeadL3 = new ModelRenderer(this);
            FrontHeadL3.setRotationPoint(6.4F, -13.0F, -2.0F);
            setRotationAngle(FrontHeadL3, -0.3491F, 0.0F, 0.4363F);
            bipedHead.addChild(FrontHeadL3);
            FrontHeadL3.cubeList.add(new ModelBox(FrontHeadL3, 31, 6, -1.2541F, -5.3446F, -1.4046F, 2, 5, 1, 0.0F));

            Mouth = new ModelRenderer(this);
            Mouth.setRotationPoint(0.0F, 0.0F, 0.0F);
            bipedHead.addChild(Mouth);
            Mouth.cubeList.add(new ModelBox(Mouth, 8, 32, -1.0F, -2.8F, -5.0F, 2, 3, 2, 0.0F));

            EarR = new ModelRenderer(this);
            EarR.setRotationPoint(-3.0F, -5.0F, -2.0F);
            bipedHead.addChild(EarR);
            EarR.cubeList.add(new ModelBox(EarR, 44, 10, -1.0F, -1.3F, -1.0F, 1, 3, 2, 0.0F));

            EarL = new ModelRenderer(this);
            EarL.setRotationPoint(3.0F, -5.0F, -2.0F);
            EarL.mirror = true;
            bipedHead.addChild(EarL);
            EarL.cubeList.add(new ModelBox(EarL, 44, 10, 0.0F, -1.3F, -1.0F, 1, 3, 2, 0.0F));

        }
        else
        {
            bipedHead = new ModelRenderer(this);
            bipedHead.setRotationPoint(0.0F, 0.0F, 1.0F);
            bipedHead.cubeList.add(new ModelBox(bipedHead, 0, 0, -4.0F, -8.0F, -5.0F, 8, 8, 8, 0.0F));

            FrontHeadR = new ModelRenderer(this);
            FrontHeadR.setRotationPoint(-3.0F, -7.0F, -4.0F);
            setRotationAngle(FrontHeadR, 0.0F, 0.0F, -0.1745F);
            FrontHeadR.mirror = true;
            bipedHead.addChild(FrontHeadR);
            FrontHeadR.cubeList.add(new ModelBox(FrontHeadR, 16, 45, -2.0F, -8.0F, -1.5F, 3, 9, 9, 0.0F));

            FrontHeadR2 = new ModelRenderer(this);
            FrontHeadR2.setRotationPoint(-2.1F, -5.6F, -1.0F);
            setRotationAngle(FrontHeadR2, 0.0F, 0.0F, 0.2269F);
            FrontHeadR2.mirror = true;
            bipedHead.addChild(FrontHeadR2);
            FrontHeadR2.cubeList.add(new ModelBox(FrontHeadR2, 33, 0, -3.5F, -1.0F, -4.5F, 6, 2, 9, 0.0F));

            FrontHeadL = new ModelRenderer(this);
            FrontHeadL.setRotationPoint(3.0F, -7.0F, -4.0F);
            setRotationAngle(FrontHeadL, 0.0F, 0.0F, 0.1745F);
            bipedHead.addChild(FrontHeadL);
            FrontHeadL.cubeList.add(new ModelBox(FrontHeadL, 16, 45, -1.0F, -8.0F, -1.5F, 3, 9, 9, 0.0F));

            FrontHeadL2 = new ModelRenderer(this);
            FrontHeadL2.setRotationPoint(2.1F, -5.6F, -1.0F);
            setRotationAngle(FrontHeadL2, 0.0F, 0.0F, -0.2269F);
            bipedHead.addChild(FrontHeadL2);
            FrontHeadL2.cubeList.add(new ModelBox(FrontHeadL2, 33, 0, -2.5F, -1.0F, -4.5F, 6, 2, 9, 0.0F));

            EarR = new ModelRenderer(this);
            EarR.setRotationPoint(-4.0F, -5.0F, -2.0F);
            bipedHead.addChild(EarR);
            EarR.cubeList.add(new ModelBox(EarR, 37, 12, -1.0F, -1.3F, -1.0F, 1, 3, 2, 0.0F));

            EarL = new ModelRenderer(this);
            EarL.setRotationPoint(4.0F, -5.0F, -2.0F);
            bipedHead.addChild(EarL);
            EarL.cubeList.add(new ModelBox(EarL, 37, 12, 0.0F, -1.3F, -1.0F, 1, 3, 2, 0.0F));

        }

        bipedBody = new ModelRenderer(this);
        bipedBody.setRotationPoint(0.2F, 5.4F, -0.2F);
        bipedBody.cubeList.add(new ModelBox(bipedBody, 16, 16, -4.2F, -5.4F, -1.8F, 8, 12, 4, 0.0F));

        bipedRightArm = new ModelRenderer(this, 40, 16);
        bipedRightArm.addBox(-3.0F, -2.0F, -2.0F, 4, 12, 4, 0.0F);
        bipedRightArm.setRotationPoint(-5.0F, 2.0F, 0.0F);

        bipedLeftArm = new ModelRenderer(this, 40, 16);
        bipedLeftArm.mirror = true;
        bipedLeftArm.addBox(-1.0F, -2.0F, -2.0F, 4, 12, 4, 0.0F);
        bipedLeftArm.setRotationPoint(5.0F, 2.0F, 0.0F);


        bipedRightLeg = new ModelRenderer(this);
        bipedRightLeg.setRotationPoint(-2.0F, 12.0F, 0.0F);
        bipedRightLeg.cubeList.add(new ModelBox(bipedRightLeg, 0, 16, -2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F));

        bipedLeftLeg = new ModelRenderer(this);
        bipedLeftLeg.setRotationPoint(2.0F, 12.0F, 0.0F);
        bipedLeftLeg.mirror = true;
        bipedLeftLeg.cubeList.add(new ModelBox(bipedLeftLeg, 0, 16, -2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F));

        if(state == 0 || state > 1)
        {
            WingR = new ModelRenderer(this);
            WingR.setRotationPoint(1.2F, 2.4F, 2.8F);
            setRotationAngle(WingR, 0.4363F, -0.1745F, 0.2618F);
            WingR.cubeList.add(new ModelBox(WingR, 36, 32, -8.0F, -1.0F, 0.0F, 7, 17, 1, 0.0F));

            WingL = new ModelRenderer(this);
            WingL.setRotationPoint(-1.2F, 2.4F, 2.8F);
            setRotationAngle(WingL, 0.4363F, 0.1745F, -0.2618F);
            WingL.mirror = true;
            WingL.cubeList.add(new ModelBox(WingL, 36, 32, 1.0F, -1.0F, 0.0F, 7, 17, 1, 0.0F));

        }

        if(state < 2)
        {
            Base = new ModelRenderer(this);
            Base.setRotationPoint(0.2F, 6.4F, 1.8F);
            Base.cubeList.add(new ModelBox(Base, 4, 33, -2.0F, -1.0F, 0.0F, 4, 4, 4, 0.0F));

            Tail1 = new ModelRenderer(this);
            Tail1.setRotationPoint(0.2F, 7.2F, 5.8F);
            setRotationAngle(Tail1, -0.4363F, 0.0F, 0.0F);
            Tail1.cubeList.add(new ModelBox(Tail1, 2, 44, -1.5F, -1.1782F, -0.5494F, 3, 3, 6, 0.0F));

            Tail2 = new ModelRenderer(this);
            Tail2.setRotationPoint(0.0F, 0.7218F, 4.2506F);
            setRotationAngle(Tail2, -0.1745F, 0.0F, 0.0F);
            Tail1.addChild(Tail2);
            Tail2.cubeList.add(new ModelBox(Tail2, 2, 44, -1.5F, -1.9354F, 0.3088F, 3, 3, 6, 0.0F));

            Tail3 = new ModelRenderer(this);
            Tail3.setRotationPoint(-0.3F, -0.6F, 6.0F);
            setRotationAngle(Tail3, 0.1745F, 0.4363F, 0.0F);
            Tail2.addChild(Tail3);
            Tail3.cubeList.add(new ModelBox(Tail3, 3, 45, -1.5F, -1.3534F, -0.4104F, 3, 3, 5, 0.0F));

            Tail4 = new ModelRenderer(this);
            Tail4.setRotationPoint(0.0F, -0.1F, 4.6F);
            setRotationAngle(Tail4, 0.349F, 0.1745F, -0.0873F);
            Tail3.addChild(Tail4);
            Tail4.cubeList.add(new ModelBox(Tail4, 3, 45, -1.5F, -1.3534F, -0.8104F, 3, 3, 5, 0.0F));

            Tail5 = new ModelRenderer(this);
            Tail5.setRotationPoint(0.0F, 0.3F, 3.0F);
            setRotationAngle(Tail5, 0.2618F, 0.2617F, 0.0F);
            Tail4.addChild(Tail5);
            Tail5.cubeList.add(new ModelBox(Tail5, 3, 45, -1.7221F, -1.5791F, 0.5803F, 3, 3, 5, 0.0F));

            Tail6 = new ModelRenderer(this);
            Tail6.setRotationPoint(-0.3F, -0.2F, 5.2F);
            setRotationAngle(Tail6, 0.0F, -0.1746F, 1.6581F);
            Tail5.addChild(Tail6);
            Tail6.cubeList.add(new ModelBox(Tail6, 24, 32, -1.5F, -1.3534F, -0.4104F, 3, 3, 3, 0.0F));

            Point = new ModelRenderer(this);
            Point.setRotationPoint(-1.0F, -1.0F, 2.0F);
            Tail6.addChild(Point);
            Point.cubeList.add(new ModelBox(Point, 24, 32, 0.5F, 0.6466F, -0.4104F, 1, 1, 5, 0.0F));
        }
        else
        {
            Tail6 = new ModelRenderer(this);
            Tail6.setRotationPoint(0.8F, 4.7342F, 1.4701F);
            setRotationAngle(Tail6, -0.7854F, 0.0F, 0.0F);
            Tail6.cubeList.add(new ModelBox(Tail6, 0, 32, -2.3F, -1.7342F, -1.4701F, 3, 3, 7, 0.0F));

            Point = new ModelRenderer(this);
            Point.setRotationPoint(-2.2F, -2.8051F, 5.6261F);
            Tail6.addChild(Point);
            Point.cubeList.add(new ModelBox(Point, 3, 35, 0.9F, 2.3165F, -2.7034F, 1, 1, 6, 0.0F));
        }
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {

        this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);

        GL11.glPushMatrix();
        GL11.glScalef(this.scaleX, this.scaleY, this.scaleZ);
        GL11.glTranslatef(0.0F, (float) (-1.04F+(5.5F/(1.0F+Math.pow(this.scaleY/0.45F,1.88F)))), 0.0F);

        double playerMotionXZ = Math.abs(entity.motionZ) + Math.abs(entity.motionX);

        if((DBCKiTech.floating || DBCKiTech.dodge_forwHold) && playerMotionXZ > 0.227)
        {
            if(!JRMCoreH.StusEfctsMe(9))
            {
                GL11.glRotatef(90, 1,0,0);
            }
            else
            {
                GL11.glRotatef(90+f4, 1,0,0);
            }
            GL11.glTranslatef(0.0F, 0.0F, 0.25F);
        }
        else
        {
            GL11.glRotatef(0, 0,0,0);
        }
        bipedHead.render(f5);
        bipedBody.render(f5);
        bipedRightArm.render(f5);
        bipedLeftArm.render(f5);
        bipedRightLeg.render(f5);
        bipedLeftLeg.render(f5);
        if(state == 0 || state > 1)
        {
            WingR.render(f5);
            WingL.render(f5);
        }
        if(state < 2)
        {
            Base.render(f5);
            Tail1.render(f5);
        }
        else
        {
            Tail6.render(f5);
        }

        GL11.glPopMatrix();
    }

    public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity entity)
    {
        double playerMotionXZ = Math.abs(entity.motionZ) + Math.abs(entity.motionX);

        if(!((DBCKiTech.floating || DBCKiTech.dodge_forwHold) && playerMotionXZ > 0.227)) {
            this.bipedHead.rotateAngleY = par4 / (180F / (float) Math.PI);
            this.bipedHead.rotateAngleX = par5 / (180F / (float) Math.PI);
        }
        else
        {
            this.bipedHead.rotateAngleY = par4 / (180F / (float) Math.PI);
            this.bipedHead.rotateAngleX = -1.5F;
        }

        this.bipedHeadwear.rotateAngleY = this.bipedHead.rotateAngleY;
        this.bipedHeadwear.rotateAngleX = this.bipedHead.rotateAngleX;

        if((DBCKiTech.floating || DBCKiTech.dodge_forwHold) && playerMotionXZ > 0.227) {
            this.bipedRightArm.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 2.0F * par2 * 0.01F;
            this.bipedLeftArm.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float) Math.PI) * 2.0F * par2 * 0.01F;
            this.bipedRightArm.rotateAngleY = 0.0F;
            this.bipedLeftArm.rotateAngleY = 0.0F;
            this.bipedRightArm.rotateAngleZ = 0.25F;
            this.bipedLeftArm.rotateAngleZ = -0.25F;

            this.bipedRightLeg.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float) Math.PI) * 2.0F * par2 * 0.01F;
            this.bipedLeftLeg.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 2.0F * par2 * 0.01F;
            this.bipedRightLeg.rotateAngleY = 0.0F;
            this.bipedLeftLeg.rotateAngleY = 0.0F;
            this.bipedRightLeg.rotateAngleZ = 0.2F;
            this.bipedLeftLeg.rotateAngleZ = -0.2F;
        }
        else
        {
            this.bipedRightArm.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float) Math.PI) * 2.0F * par2 * 0.5F;
            this.bipedLeftArm.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 2.0F * par2 * 0.5F;
            this.bipedRightArm.rotateAngleY = 0.0F;
            this.bipedLeftArm.rotateAngleY = 0.0F;
            this.bipedRightArm.rotateAngleZ = 0.0F;
            this.bipedLeftArm.rotateAngleZ = 0.0F;

            this.bipedRightLeg.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 1.4F * par2;
            this.bipedLeftLeg.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float) Math.PI) * 1.4F * par2;
            this.bipedRightLeg.rotateAngleY = 0.0F;
            this.bipedLeftLeg.rotateAngleY = 0.0F;
            this.bipedRightLeg.rotateAngleZ = 0.0F;
            this.bipedLeftLeg.rotateAngleZ = 0.0F;
        }
        if(state == 0 || state > 1)
        {
            this.WingR.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 2.0F * par2 * 0.05F + 0.1745F;
            this.WingL.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 2.0F * par2 * 0.05F + 0.1745F;
        }

        if(state < 2)
        {
            //tail
            float randomInt = (float) Math.random();
            Tail1.rotateAngleX = MathHelper.cos((entity.ticksExisted+randomInt)*0.05F)*0.5F;
            Tail1.rotateAngleY = MathHelper.cos((entity.ticksExisted+randomInt)*0.05F)*0.5F;
            Tail2.rotateAngleZ = MathHelper.cos((entity.ticksExisted+randomInt)*0.05F)*0.5F;
            Tail3.rotateAngleX = MathHelper.cos((entity.ticksExisted+randomInt)*0.05F)*0.5F;
            Tail4.rotateAngleZ = MathHelper.cos((entity.ticksExisted+randomInt)*0.05F)*0.5F;
            Tail5.rotateAngleX = MathHelper.cos((entity.ticksExisted+randomInt)*0.05F)*0.5F;
            Tail6.rotateAngleZ = MathHelper.cos((entity.ticksExisted+randomInt)*0.05F)*0.5F;
        }

        if (this.isRiding)
        {
            this.bipedRightArm.rotateAngleX += -((float)Math.PI / 5F);
            this.bipedLeftArm.rotateAngleX += -((float)Math.PI / 5F);
            this.bipedRightLeg.rotateAngleX = -((float)Math.PI * 2F / 5F);
            this.bipedLeftLeg.rotateAngleX = -((float)Math.PI * 2F / 5F);
            this.bipedRightLeg.rotateAngleY = ((float)Math.PI / 10F);
            this.bipedLeftLeg.rotateAngleY = -((float)Math.PI / 10F);
        }

        if (this.heldItemLeft != 0)
        {
            this.bipedLeftArm.rotateAngleX = this.bipedLeftArm.rotateAngleX * 0.5F - ((float)Math.PI / 10F) * (float)this.heldItemLeft;
        }

        if (this.heldItemRight != 0)
        {
            this.bipedRightArm.rotateAngleX = this.bipedRightArm.rotateAngleX * 0.5F - ((float)Math.PI / 10F) * (float)this.heldItemRight;
        }

        this.bipedRightArm.rotateAngleY = 0.0F;
        this.bipedLeftArm.rotateAngleY = 0.0F;
        float f6;
        float f7;

        if (this.aimedBow)
        {
            f6 = 0.0F;
            f7 = 0.0F;
            this.bipedRightArm.rotateAngleZ = 0.0F;
            this.bipedLeftArm.rotateAngleZ = 0.0F;
            this.bipedRightArm.rotateAngleY = -(0.1F - f6 * 0.6F) + this.bipedHead.rotateAngleY;
            this.bipedLeftArm.rotateAngleY = 0.1F - f6 * 0.6F + this.bipedHead.rotateAngleY + 0.4F;
            this.bipedRightArm.rotateAngleX = -((float)Math.PI / 2F) + this.bipedHead.rotateAngleX;
            this.bipedLeftArm.rotateAngleX = -((float)Math.PI / 2F) + this.bipedHead.rotateAngleX;
            this.bipedRightArm.rotateAngleX -= f6 * 1.2F - f7 * 0.4F;
            this.bipedLeftArm.rotateAngleX -= f6 * 1.2F - f7 * 0.4F;
            this.bipedRightArm.rotateAngleZ += MathHelper.cos(par3 * 0.09F) * 0.05F + 0.05F;
            this.bipedLeftArm.rotateAngleZ -= MathHelper.cos(par3 * 0.09F) * 0.05F + 0.05F;
            this.bipedRightArm.rotateAngleX += MathHelper.sin(par3 * 0.067F) * 0.05F;
            this.bipedLeftArm.rotateAngleX -= MathHelper.sin(par3 * 0.067F) * 0.05F;
        }

        EntityPlayer p = null;
        if (entity instanceof EntityPlayer) {
            p = (EntityPlayer)entity;
            if (entity == JRMCoreClient.mc.thePlayer && (entity != JRMCoreClient.mc.thePlayer || JRMCoreClient.mc.gameSettings.thirdPersonView == 0)) {
                this.blk = false;
                this.KiAttack = 0;
            } else {
                ExtendedPlayer props = ExtendedPlayer.get(p);
                boolean block = props.getBlocking() != 0;
                int kishoot = props.getAnimKiShoot();
                this.blk = block;
                this.KiAttack = kishoot;
            }
        }

        int pwr = 0;
        if (p != null && JRMCoreH.plyrs != null && JRMCoreH.plyrs.length > 0 && !p.isInvisible() && JRMCoreH.dnn(1)) {
            for(int pl = 0; pl < JRMCoreH.plyrs.length; ++pl) {
                if (JRMCoreH.plyrs[pl].equals(p.getCommandSenderName()) && JRMCoreH.data1.length >= JRMCoreH.plyrs.length) {
                    String[] s = JRMCoreH.data1[pl].split(";");
                    pwr = Integer.parseInt(s[2]);
                    break;
                }
            }
        }

        bipedRightLeg.rotateAngleY = 0.0F;
        bipedLeftLeg.rotateAngleY = 0.0F;

        ModelRenderer var10000;
        float var8;
        float var9;
        if (this.onGround > -9990.0F) {
            var8 = this.onGround;

            bipedRightArm.rotationPointZ = MathHelper.sin(bipedBody.rotateAngleY) * 5.0F;
            bipedRightArm.rotationPointX = -MathHelper.cos(bipedBody.rotateAngleY) * 5.0F;
            var10000 = bipedRightArm;
            var10000.rotateAngleY += bipedBody.rotateAngleY;
            var8 = 1.0F - this.onGround;
            var8 *= var8;
            var8 *= var8;
            var8 = 1.0F - var8;
            var9 = MathHelper.sin(var8 * 3.1415927F);
            f6 = MathHelper.sin(this.onGround * 3.1415927F) * -(this.bipedHead.rotateAngleX - 0.7F) * 0.75F;
            if (pwr == 2 && par2 > 0.9F && var9 != 0.0F) {
                bipedRightArm.rotateAngleX = 0.0F;
                bipedRightArm.rotateAngleX = (float)((double)bipedRightArm.rotateAngleX - ((double)var9 * 1.2D + (double)f6));
                var10000 = bipedRightArm;
                var10000.rotateAngleY += bipedBody.rotateAngleY * 2.0F;
                bipedRightArm.rotateAngleZ = MathHelper.sin(this.onGround * 3.1415927F) * -0.4F;
            } else {
                bipedRightArm.rotateAngleX = (float)((double)bipedRightArm.rotateAngleX - ((double)var9 * 1.2D + (double)f6));
                var10000 = bipedRightArm;
                var10000.rotateAngleY += bipedBody.rotateAngleY * 2.0F;
                if (y == 3) {
                    bipedRightArm.rotateAngleX = 0.0F;
                    bipedRightArm.rotateAngleZ = 0.5F;
                } else if (y == 1) {
                    bipedRightArm.rotateAngleZ = MathHelper.sin(this.onGround * 3.1415927F) * -0.4F;
                } else {
                    bipedRightArm.rotateAngleZ = 0.2F;
                }
            }
        }

        if (this.blk && this.heldItemRight == 0) {
            bipedRightArm.rotateAngleZ = 0.0F;
            bipedLeftArm.rotateAngleZ = 0.0F;
            bipedRightArm.rotateAngleY = -(0.1F * 0.6F) + (this.bipedHead.rotateAngleY < -0.2F ? -0.2F : this.bipedHead.rotateAngleY) - 0.8F;
            bipedLeftArm.rotateAngleY = 0.1F * 0.6F + (this.bipedHead.rotateAngleY > 0.2F ? 0.2F : this.bipedHead.rotateAngleY) + 0.8F;
            bipedRightArm.rotateAngleX = -1.5707964F + (this.bipedHead.rotateAngleX < -0.5F ? -0.5F : (this.bipedHead.rotateAngleX > 0.5F ? 0.5F : this.bipedHead.rotateAngleX));
            bipedLeftArm.rotateAngleX = -1.5707964F + (this.bipedHead.rotateAngleX < -0.5F ? -0.5F : (this.bipedHead.rotateAngleX > 0.5F ? 0.5F : this.bipedHead.rotateAngleX));
        }

        if ((this.KiAttack == 1 || this.KiAttack == 8 || this.KiAttack == 9) && this.heldItemRight == 0) {
            f6 = 0.0F;
            f7 = 0.0F;
            bipedRightArm.rotateAngleZ = 0.0F;
            bipedLeftArm.rotateAngleZ = 0.0F;
            bipedRightArm.rotateAngleY = -(0.1F - f6 * 0.6F) + (this.bipedHead.rotateAngleY < -0.2F ? -0.2F : this.bipedHead.rotateAngleY) - 0.5F;
            bipedLeftArm.rotateAngleY = 0.1F - f6 * 0.6F + (this.bipedHead.rotateAngleY > 0.2F ? 0.2F : this.bipedHead.rotateAngleY) + 0.5F;
            bipedRightArm.rotateAngleX = -1.5707964F + (this.bipedHead.rotateAngleX < -0.5F ? -0.5F : (this.bipedHead.rotateAngleX > 0.5F ? 0.5F : this.bipedHead.rotateAngleX));
            bipedLeftArm.rotateAngleX = -1.5707964F + (this.bipedHead.rotateAngleX < -0.5F ? -0.5F : (this.bipedHead.rotateAngleX > 0.5F ? 0.5F : this.bipedHead.rotateAngleX));
        }

        if ((this.KiAttack == 2 || this.KiAttack == 5 || this.KiAttack == 4 || this.KiAttack == 7) && this.heldItemRight == 0) {
            f6 = 0.0F;
            f7 = 0.0F;
            bipedRightArm.rotateAngleZ = 0.0F;
            bipedRightArm.rotateAngleY = -(0.1F - f6 * 0.6F) + (this.bipedHead.rotateAngleY < -0.2F ? -0.2F : this.bipedHead.rotateAngleY) - 0.1F;
            bipedRightArm.rotateAngleX = -1.5707964F + this.bipedHead.rotateAngleX;
        }

        if (this.KiAttack == 3 && this.heldItemRight == 0) {
            f6 = 0.0F;
            f7 = 0.0F;
            bipedRightArm.rotateAngleZ = -0.3F;
            bipedRightArm.rotateAngleX = -3.0F;
        }

        if (this.KiAttack == 6 && this.heldItemRight == 0) {
            f6 = 0.0F;
            f7 = 0.0F;
            bipedRightArm.rotateAngleZ = -0.3F;
            bipedRightArm.rotateAngleX = -3.0F;
            bipedLeftArm.rotateAngleZ = 0.3F;
            bipedLeftArm.rotateAngleX = -3.0F;
        }
    }

    public void renderHalo(float par1) {
        float f6 = scaleY;
        GL11.glPushMatrix();
        GL11.glScalef((0.5F + 0.5F * f6) * (g <= 1 ? 1.0F : 0.85F), 1.4F * f6, (0.5F + 0.5F * f6) * (g <= 1 ? 1.0F : 0.85F));
        GL11.glTranslatef(0.0F, -f6 + 1, 0.0F);
        this.halo.rotateAngleY = this.bipedHead.rotateAngleY;
        this.halo.rotateAngleX = this.bipedHead.rotateAngleX;
        this.halo.rotationPointX = this.bipedHead.rotationPointX;
        this.halo.rotationPointY = this.bipedHead.rotationPointY;
        this.halo.render(par1);
        GL11.glPopMatrix();
    }

    public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
