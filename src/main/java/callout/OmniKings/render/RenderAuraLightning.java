package callout.OmniKings.render;

import JinRyuu.DragonBC.common.DBCClient;
import JinRyuu.DragonBC.common.Npcs.*;
import JinRyuu.JRMCore.JRMCoreClient;
import JinRyuu.JRMCore.JRMCoreConfig;
import JinRyuu.JRMCore.JRMCoreH;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

import java.util.Random;

public class RenderAuraLightning extends RenderDBC {

    private ModelAura aModel;
    private ModelAuraG bModel;
    private String mdid;
    private boolean au_fel;
    private int au_i;
    private long time_start;
    private final float seb;
    private final int lvnm;
    private final int slowdownMax = 200;
    private int slowdown = 0;
    private float[][] lightVertRotation;
    private int lightVertN;

    public RenderAuraLightning() {
        super(new ModelAura(), 0.5F);
        this.mdid = "jinryuudragonbc";
        this.au_fel = true;
        this.au_i = 0;
        this.time_start = 0L;
        this.seb = 0.03F;
        this.lvnm = 10;
        this.lightVertRotation = new float[10][7];
        this.aModel = new ModelAura();
        this.bModel = new ModelAuraG();
    }

    public void renderAura(EntityAura2 par1Entity, double parX, double parY, double parZ, float par8, float par9) {
        float var13 = (float)par1Entity.getAge();
        boolean rot = par1Entity.getRot();
        this.shadowSize = 0.0F;
        if (JRMCoreConfig.CLIENT_DA20) {
            if (JRMCoreConfig.CLIENT_DA12) {
                this.lightning(par1Entity, parX, parY, parZ, par9, 1.0F, var13, rot);
            }
        } else if (JRMCoreConfig.CLIENT_DA12) {
            this.lightning(par1Entity, parX, parY, parZ, par9, 1.0F, var13, rot);
        }

    }
    private void lightning(EntityAura2 e, double par2, double par4, double par6, float par9, float var20, float var13, boolean rot) {
        if (var13 < (float)e.getLightLivingTime() && !rot && slowdown > slowdownMax) {
            slowdown = 0;
            GL11.glPushMatrix();
            Tessellator tessellator2 = Tessellator.instance;
            GL11.glDisable(3553);
            GL11.glDisable(2896);
            GL11.glEnable(3042);
            GL11.glBlendFunc(770, 1);
            double[] adouble = new double[8];
            double[] adouble1 = new double[8];
            double d3 = 0.0D;
            double d4 = 0.0D;
            GL11.glTranslatef((float)par2, (float)par4 + e.height / 2.0F, (float)par6);
            int k1 = 0;
            int nu = (int)(Math.random() * 10.0D) + 1;
            int nu2 = 1;
            if (!JRMCoreClient.mc.isGamePaused()) {
                this.lightVertN = (int)(Math.random() * 10.0D);
            }

            for(int i = 0; i < this.lightVertN; ++i) {
                if (!JRMCoreClient.mc.isGamePaused()) {
                    this.lightVertRotation[i][0] = (float)(Math.random() * 1.0D);
                    this.lightVertRotation[i][1] = (float)(Math.random() * 1.0D);
                    this.lightVertRotation[i][2] = (float)(Math.random() * 1.0D);
                    this.lightVertRotation[i][3] = (float)(Math.random() * 1.2000000476837158D) - 0.6F;
                    this.lightVertRotation[i][4] = (float)(Math.random() * (double)e.height) - e.height / 2.0F;
                    this.lightVertRotation[i][5] = (float)(Math.random() * 1.2000000476837158D) - 0.6F;
                    this.lightVertRotation[i][6] = (float)(Math.random() * 0.20000000298023224D);
                }

                float sc = 0.05F + this.lightVertRotation[i][6];
                GL11.glRotatef(360.0F * this.lightVertRotation[i][0], 1.0F, 0.0F, 0.0F);
                GL11.glRotatef(360.0F * this.lightVertRotation[i][1], 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(360.0F * this.lightVertRotation[i][2], 0.0F, 0.0F, 1.0F);
                GL11.glTranslatef(this.lightVertRotation[i][3], this.lightVertRotation[i][4], this.lightVertRotation[i][5]);
                Random random1 = new Random(e.lightVert[i]);

                for(int j = 0; j < nu2; ++j) {
                    int k = 7;
                    int l = 0;
                    if (j > 0) {
                        k = 7 - j;
                    }

                    if (j > 0) {
                        l = k - 2;
                    }

                    double d5 = adouble[k] - d3;
                    double d6 = adouble1[k] - d4;

                    for(int i1 = k; i1 >= l; --i1) {
                        double d7 = d5;
                        double d8 = d6;
                        d5 += (double)(random1.nextInt(31) - 15) * 0.07000000029802322D;
                        d6 += (double)(random1.nextInt(31) - 15) * 0.07000000029802322D;
                        tessellator2.startDrawing(5);
                        float f2 = 0.5F;
                        if (!JRMCoreH.StusEfctsMe(5)) {
                            tessellator2.setColorRGBA_F(0.9F * f2, 0.9F * f2, 1.0F * f2, 0.3F);
                        } else {
                            tessellator2.setColorRGBA_F(0.8F * f2, 0.05F * f2, 0.1F * f2, 0.4F);
                        }

                        double d9 = 0.1D + (double)k1 * 0.2D;
                        double d10 = 0.1D + (double)k1 * 0.2D;

                        for(int j1 = 0; j1 < 5; ++j1) {
                            double d11 = 0.0D - d9;
                            double d12 = 0.0D - d9;
                            if (j1 == 1 || j1 == 2) {
                                d11 += d9 * 2.0D * (double)sc;
                            }

                            if (j1 == 2 || j1 == 3) {
                                d12 += d9 * 2.0D * (double)sc;
                            }

                            double d13 = 0.0D - d10;
                            double d14 = 0.0D - d10;
                            if (j1 == 1 || j1 == 2) {
                                d13 += d10 * 2.0D * (double)sc;
                            }

                            if (j1 == 2 || j1 == 3) {
                                d14 += d10 * 2.0D * (double)sc;
                            }

                            if (i1 < 8) {
                                tessellator2.addVertex(d13 + d5 * (double)sc, -((double)(i1 * 1 - 7)) * (double)sc, d14 + d6 * (double)sc);
                                tessellator2.addVertex(d11 + d7 * (double)sc, -((double)((i1 + 1) * 1 - 7)) * (double)sc, d12 + d8 * (double)sc);
                            }
                        }

                        tessellator2.draw();
                    }
                }
            }

            GL11.glDisable(3042);
            GL11.glEnable(2896);
            GL11.glEnable(3553);
            GL11.glPopMatrix();
        }
        slowdown++;

    }

    protected float handleRotationFloat(Entity par1Entity, float par2) {
        return (float)par1Entity.ticksExisted + par2;
    }

    public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9) {
        this.renderAura((EntityAura2)par1Entity, par2, par4, par6, par8, par9);
    }
}
