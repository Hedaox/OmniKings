package callout.OmniKings.event;

import JinRyuu.DragonBC.common.DBCConfig;
import JinRyuu.JRMCore.*;
import JinRyuu.JRMCore.entity.EntityEnergyAtt;
import JinRyuu.JRMCore.entity.EntityNPCshadow;
import JinRyuu.JRMCore.i.ExtendedPlayer;
import callout.OmniKings.core.OmniKingsCoreH;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.StatCollector;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

public class AttackEventOmniKings {

    @SubscribeEvent
    public void CheckHurtEvent(LivingHurtEvent event)
    {
        float amount = event.ammount;
        DamageSource source = event.source;
        EntityLivingBase targetEntity = event.entityLiving;
        if (((targetEntity instanceof EntityPlayer)) && (source != DamageSource.outOfWorld))
        {
            String f = JRMCoreH.getString((EntityPlayer)targetEntity, "jrmcFuzion");
            if (f.contains(","))
            {
                String[] fa = f.split(",");
                if (targetEntity.getCommandSenderName().equalsIgnoreCase(fa[1]))
                {
                    event.ammount = 0.0F;
                    event.setCanceled(true);
                    return;
                }
            }
        }
        if ((targetEntity instanceof EntityNPCshadow))
        {
            EntityNPCshadow e = (EntityNPCshadow)targetEntity;
            if (((source.getEntity() instanceof EntityLivingBase)) && (e.getSummoner() != source.getEntity())) {
                e.setDead();
            }
        }
        if ((source.getDamageType().equals("EnergyAttack")) && ((source.getSourceOfDamage() instanceof EntityEnergyAtt))) {
            event.ammount = 0.0F;
        }
        if (((targetEntity instanceof EntityPlayer)) && ((source.getEntity() instanceof EntityPlayer)))
        {
            String s = JRMCoreH.rwip(FMLCommonHandler.instance().getMinecraftServerInstance(), targetEntity.dimension + "");
            if (s.equalsIgnoreCase("false"))
            {
                event.ammount = 0.0F;
                return;
            }
        }
        if ((!event.isCanceled()) && ((JRMCoreH.DBC())) && (amount != 0.0F) && (source != Ds.OutOfBodyHealth) && (source != Ds.NotAlive) && (source != DamageSource.outOfWorld))
        {
            boolean playerAttacked = false;
            if ((source.getEntity() != null) && ((source.getEntity() instanceof EntityPlayer)))
            {
                EntityPlayer attacker = (EntityPlayer)source.getEntity();

                String f = JRMCoreH.getString(attacker, "jrmcFuzion");
                if (f.contains(","))
                {
                    String[] fa = f.split(",");
                    if ((attacker.getCommandSenderName().equalsIgnoreCase(fa[0])) && (targetEntity.getCommandSenderName().equalsIgnoreCase(fa[1])))
                    {
                        event.setCanceled(true);
                        return;
                    }
                    if ((attacker.getCommandSenderName().equalsIgnoreCase(fa[1])) && (targetEntity.getCommandSenderName().equalsIgnoreCase(fa[0])))
                    {
                        event.setCanceled(true);
                        return;
                    }
                    if (targetEntity.getCommandSenderName().equalsIgnoreCase(fa[1]))
                    {
                        event.setCanceled(true);
                        return;
                    }
                }
                boolean Melee = (source.getSourceOfDamage() == source.getEntity()) && (source.getDamageType().equals("player"));
                boolean energyAtt = (source.getDamageType().equals("EnergyAttack")) && ((source.getSourceOfDamage() instanceof EntityEnergyAtt));
                boolean Projectile = ((source.getSourceOfDamage() instanceof IProjectile)) && (!energyAtt);

                int pwr = JRMCoreH.getByte(attacker, "jrmcPwrtyp");
                int rc = JRMCoreH.getByte(attacker, "jrmcRace");
                int st = JRMCoreH.getByte(attacker, "jrmcState");
                int st2 = JRMCoreH.getByte(attacker, "jrmcState2");
                int cl = JRMCoreH.getByte(attacker, "jrmcClass");
                int rl = JRMCoreH.getByte(attacker, "jrmcRelease");
                String sklx = JRMCoreH.getString(attacker, "jrmcSSltX");
                int resrv = JRMCoreH.getInt(attacker, "jrmcArcRsrv");
                int[] PlyrAttrbts = JRMCoreH.PlyrAttrbts(attacker);
                String[] PlyrSkills = JRMCoreH.getString(attacker, "jrmcSSlts").split(",");

                String ste = JRMCoreH.getString(attacker, "jrmcStatusEff");
                boolean mj = JRMCoreH.StusEfcts(12, ste);
                boolean lg = JRMCoreH.StusEfcts(14, ste);
                boolean mc = JRMCoreH.StusEfcts(13, ste);
                boolean mn = JRMCoreH.StusEfcts(19, ste);
                int ce = JRMCoreH.getInt(attacker, "jrmcStamina");
                int ck = JRMCoreH.getInt(attacker, "jrmcEnrgy");

                int STR = PlyrAttrbts[0];
                int DEX = PlyrAttrbts[1];
                int WIL = PlyrAttrbts[3];
                float dam = amount;

                float den = 0.0F;
                int ml = JRMCoreH.stat(attacker, 0, STR, 0.0F);
                int cst = (int)(ml * 0.1F);
                int cstF = 0;
                int cstF2 = 0;
                int idd = 0;
                int skf;
                if (pwr == 1)
                {
                    boolean c = (JRMCoreH.StusEfcts(10, ste)) || (JRMCoreH.StusEfcts(11, ste));
                    if (Melee)
                    {
                        int sklkf = JRMCoreH.SklLvl(12, PlyrSkills);
                        boolean sklkfe = !JRMCoreH.PlyrSettingsB(attacker, 9);

                        int sklks = 0;
                        int sklks2 = 0;
                        if ((sklkf > 0) && (sklkfe))
                        {
                            int SPI = PlyrAttrbts[5];
                            int statSPI = JRMCoreH.stat(pwr, 5, SPI, rc, cl, JRMCoreH.SklLvl_KiBs(PlyrSkills, pwr));
                            sklks = (int)(sklkf * 0.0025D * statSPI * rl * 0.01D);
                            if (sklks > 0)
                            {
                                cstF = (int)(sklks * DBCConfig.cnfKFc);
                                if (ck <= cstF)
                                {
                                    sklks = 0;cstF = 0;
                                }
                                sklks = (int)(sklks * DBCConfig.cnfKFd);
                            }
                        }
                        STR = OmniKingsCoreH.TransPwrModAtr(PlyrAttrbts, 0, st, st2, rc, sklx, rl, resrv, lg, mj, mc, mn, pwr, PlyrSkills, c);
                        int dmg = JRMCoreH.stat(pwr, 0, STR, rc, cl, 0.0F);
                        double curAtr = dmg * rl * 0.01D * JRMCoreH.weightPerc(0, attacker);

                        boolean sklkfe2 = JRMCoreH.PlyrSettingsB(attacker, 13);
                        boolean sklkfe3 = JRMCoreH.PlyrSettingsI(attacker, 13, 1);
                        skf = JRMCoreH.SklLvl(15, PlyrSkills);
                        if ((sklkf > 0) && (skf > 0) && (sklkfe2))
                        {
                            attacker.worldObj.playSoundAtEntity(attacker, "jinryuudragonbc:DBC4.kiblade2", 1.0F, 1.0F);

                            int cstkw = 0;
                            int kwdam = 0;

                            int dmg1 = (int)(JRMCoreH.stat(pwr, 4, WIL, rc, cl, 0.0F) * 0.01F);
                            float data1 = (int)(0.005D * dmg1 * rl * 0.01D * (sklkfe3 ? DBCConfig.cnfKCsd : DBCConfig.cnfKBld) * JRMCoreConfig.dat5699);
                            float data2 = (int)(0.005D * dmg1 * rl * 0.01D * (sklkfe3 ? DBCConfig.cnfKCsc : DBCConfig.cnfKBlc));
                            cstkw = (int)(cstkw + data2 / (sklkf > 1 ? sklkf * 0.3F + 1.0F : 1.0F));
                            kwdam = (int)(kwdam + sklkf * data1);
                            if (sklks2 > 0)
                            {
                                cstF2 = sklks2;
                                if (ck <= cstF2)
                                {
                                    sklks2 = 0;cstF2 = 0;
                                }
                                sklks2 = 0;
                            }
                            dmg1 = (int)(JRMCoreH.stat(pwr, 4, WIL, rc, cl, 0.0F) * 0.01F);
                            data1 = (float)(dmg1 * rl * 0.01F * JRMCoreH.weightPerc(1, attacker) * (sklkfe3 ? DBCConfig.cnfKCsd : DBCConfig.cnfKBld) * JRMCoreConfig.dat5700);
                            data2 = (float)(dmg1 * rl * 0.01F * JRMCoreH.weightPerc(1, attacker) * (sklkfe3 ? DBCConfig.cnfKCsc : DBCConfig.cnfKBlc));

                            cstkw = (int)(cstkw + data2 / (skf > 1 ? skf * 0.3F + 1.0F : 1.0F));
                            kwdam = (int)(kwdam + skf * data1);
                            if (sklks2 > 0)
                            {
                                cstF2 = sklks2;
                                if (ck <= cstF2)
                                {
                                    sklks2 = 0;
                                    cstF2 = 0;
                                }
                                sklks2 = 0;
                            }
                            if ((cstkw > 0) && (ck >= cstkw))
                            {
                                dam += kwdam;
                                ck -= cstkw;
                                JRMCoreH.setInt(ck - cstkw, attacker, "jrmcEnrgy");
                            }
                        }
                        if (JRMCoreConfig.DebugInfo) {
                            mod_JRMCore.logger.info(attacker.getCommandSenderName() + " attacks " + targetEntity.getCommandSenderName() + " with melee " + curAtr + "+" + sklks + "=" + (curAtr + sklks));
                        }
                        dam = (float)(dam + (curAtr + sklks));
                    }
                    else if (Projectile)
                    {
                        cst = 1;
                        WIL = OmniKingsCoreH.TransPwrModAtr(PlyrAttrbts, 3, st, st2, rc, sklx, rl, resrv, lg, mj, mc, mn, pwr, PlyrSkills, c);
                        int dmg = (int)(JRMCoreH.stat(pwr, 4, WIL, rc, cl, 0.0F) * 0.01F);

                        skf = JRMCoreH.SklLvl(15, PlyrSkills);

                        dam += dmg * rl * 0.005F * skf * JRMCoreH.weightPerc(1, attacker);
                    }
                }
                else if ((pwr == 2) && (Melee))
                {
                    boolean eff_done = false;
                    int effect_dam = 0;
                    if ((JRMCoreH.NC()) && (attacker != null))
                    {
                        idd = ExtendedPlayer.get(attacker).getHandEffect();
                        effect_dam = ExtendedPlayer.get(attacker).getEffect_used2();
                        if ((idd < 3) && (idd > 0) && (attacker != null) && ((attacker instanceof EntityPlayer)) && (
                                (idd == 1) || (idd == 2)))
                        {
                            JRMCoreH.newExpl(targetEntity.worldObj, attacker, targetEntity.posX, targetEntity.posY, targetEntity.posZ, 0.0F, false, 0.0D, attacker, (byte)(2 + idd));

                            eff_done = true;
                            ExtendedPlayer.get(attacker).setHandEffect(0);
                            ExtendedPlayer.get(attacker).setEffect_used(0);
                            ExtendedPlayer.get(attacker).setEffect_used2(0);

                            skf = ExtendedPlayer.get(attacker).getHandEffect();
                        }
                    }
                    int ta = JRMCoreH.SklLvl(0, 2, PlyrSkills);
                    int cj = JRMCoreH.SklLvlY(2, JRMCoreH.getString(attacker, "jrmcSSltY"));
                    den = cl == 1 ? cj * JRMCoreConfig.hedp * 0.01F : 0.0F;
                    int dmg = JRMCoreH.stat(pwr, 0, STR, rc, cl, ta * 0.04F + st * 0.25F);
                    dam += dmg * rl * 0.01F + effect_dam;
                }
                else if ((pwr == 3) && (Melee))
                {
                    int WeaponDamage = JRMCoreHSAC.getWeaponDamage(attacker.getCurrentEquippedItem(), STR);

                    STR += JRMCoreHSAC.getWeaponBonus(attacker.getCurrentEquippedItem(), 0);
                    DEX += JRMCoreHSAC.getWeaponBonus(attacker.getCurrentEquippedItem(), 1);
                    int dmg = (int)JRMCoreHSAC.getDamage(WeaponDamage, STR, DEX);

                    dam += dmg;
                    cst = 0;
                }
                dam = dam <= 0.0F ? 1.0F : dam;
                if ((ce > cst) && (dam > 0.0F))
                {
                    event.ammount = 0.0F;

                    boolean doAttack = true;
                    if ((JRMCoreH.DBC()) && (JRMCoreConfig.sfzns)) {
                        doAttack = !JRMCoreHDBC.JRMCoreEHonLivingHurtSafeZone(targetEntity);
                    }
                    if (doAttack)
                    {
                        playerAttacked = true;
                        if (Melee)
                        {
                            JRMCoreH.setInt(ce - (int)(cst * JRMCoreConfig.cnfPnchc), attacker, "jrmcStamina");
                            if ((cstF > 0) &&
                                    (ck >= cstF)) {
                                JRMCoreH.setInt(ck - cstF, attacker, "jrmcEnrgy");
                            }
                        }
                        int dealt = (int)dam;
                        if ((targetEntity instanceof EntityPlayer))
                        {
                            EntityPlayer targetPlayer = (EntityPlayer)targetEntity;
                            int acc = JRMCoreH.getByte(targetPlayer, "jrmcAccept");
                            if ((acc == 0) || (JRMCoreH.getByte(targetPlayer, "jrmcPwrtyp") == 0))
                            {
                                event.setCanceled(false);
                                return;
                            }
                            dam = damageEntity(targetPlayer, source, dam);

                            JRMCoreH.a1t3(targetPlayer);
                            int epoch = (int)(System.currentTimeMillis() / 1000L);

                            JRMCoreH.setInt(epoch + 5, targetPlayer, "jrmcAttackLstPlyrTm");
                            JRMCoreH.setString(attacker.getUniqueID().toString(), targetPlayer, "jrmcAttackLstPlyrNam");
                            if (energyAtt)
                            {
                                dealt = JRMCoreH.jrmcDam(targetPlayer, (int)dam, source, ((EntityEnergyAtt)source.getSourceOfDamage()).getType());
                            }
                            else if ((pwr == 1) && (Projectile))
                            {
                                skf = JRMCoreH.SklLvl(15, PlyrSkills);
                                int cost = (int)(dam * 0.005D * skf * DBCConfig.cnfKIc);
                                if (ck >= cost)
                                {
                                    JRMCoreH.setInt(ck - cost, attacker, "jrmcEnrgy");
                                    dealt = JRMCoreH.jrmcDam(targetPlayer, (int)(dam * DBCConfig.cnfKId), source);

                                    knockback(targetPlayer, attacker, 1);
                                }
                                else
                                {
                                    event.setCanceled(false);
                                    return;
                                }
                            }
                            else
                            {
                                dealt = JRMCoreH.jrmcDam(targetPlayer, (int)dam, source);
                                knockback(targetPlayer, attacker, 1);
                            }
                        }
                        else if ((!JRMCoreH.DBC()) || (JRMCoreH.DGE(targetEntity)))
                        {
                            if ((pwr == 1) && (Projectile))
                            {
                                skf = JRMCoreH.SklLvl(15, PlyrSkills);
                                int cost = (int)(dam * 0.005D * skf * DBCConfig.cnfKIc);
                                if (ck >= cost)
                                {
                                    JRMCoreH.setInt(ck - cost, attacker, "jrmcEnrgy");
                                    dealt = (int)dam;
                                    damageEntity(targetEntity, source, (int)(dam * DBCConfig.cnfKId));
                                    knockback(targetEntity, attacker, 1);
                                    knockback(targetEntity, attacker, 1);
                                }
                                else
                                {
                                    event.setCanceled(false);
                                    return;
                                }
                            }
                            else
                            {
                                dealt = (int)dam;
                                damageEntity(targetEntity, source, dam);
                                if (idd == 1)
                                {
                                    damageEntity(targetEntity, source, dam);
                                    float push = 1.0F;
                                    targetEntity.motionX += (attacker.posX > targetEntity.posX ? -push : push);
                                    targetEntity.motionY += (attacker.posY > targetEntity.posY ? -push : push);
                                    targetEntity.motionZ += (attacker.posZ > targetEntity.posZ ? -push : push);
                                    targetEntity.velocityChanged = true;
                                }
                                else if (idd == 0)
                                {
                                    knockback(targetEntity, attacker, 1);
                                }
                            }
                        }
                        if ((!attacker.worldObj.isRemote) && ((!JRMCoreH.DBC()) || (JRMCoreH.DGE(targetEntity))) && (attacker != null))
                        {
                            int tp = 1;
                            if ((targetEntity instanceof EntityPlayer))
                            {
                                int[] PlyrAttrbtsTar = JRMCoreH.PlyrAttrbts((EntityPlayer)targetEntity);
                                int rltar = JRMCoreH.getByte(attacker, "jrmcRelease");
                                float tartp = PlyrAttrbtsTar[4] / JRMCoreConfig.TpgnRt * rltar * 0.01F;
                                float atttp = PlyrAttrbts[4] / JRMCoreConfig.TpgnRt * rl;
                                float mult = tartp / atttp;mult = mult > 2.0F ? 2.0F : mult;
                                tp = (int)(tp + atttp * mult);
                            }
                            else if ((targetEntity instanceof EntityNPCshadow))
                            {
                                float atttp = PlyrAttrbts[4] / JRMCoreConfig.TpgnRt;
                                float mult = atttp / (atttp * rl * 0.01F);mult = mult > 2.0F ? 2.0F : mult;
                                tp = (int)(tp + atttp * mult);
                            }
                            else
                            {
                                float atttp = PlyrAttrbts[4] / JRMCoreConfig.TpgnRt * rl * 0.01F;
                                tp = (int)(tp + atttp);
                            }
                            JRMCoreH.jrmcExp(attacker, tp);
                        }
                        if (source.damageType.equalsIgnoreCase("player"))
                        {
                            int id = (int)(Math.random() * 3.0D) + 1;
                            attacker.worldObj.playSoundAtEntity(attacker, "jinryuudragonbc:DBC4.punch" + id, 0.5F, 0.9F / (attacker.worldObj.rand.nextFloat() * 0.4F + 0.8F));
                        }
                        if (attacker != null) {
                            JRMCoreH.setInt(dealt, attacker, "jrmcLastDamageDealt");
                        }
                        if ((targetEntity != null) && ((targetEntity instanceof EntityPlayer)))
                        {
                            JRMCoreH.setInt(dealt, (EntityPlayer)targetEntity, "jrmcLastDamageReceived");
                            if (attacker != null)
                            {
                                int epoch = (int)(System.currentTimeMillis() / 1000L);
                                JRMCoreH.setString(attacker.getCommandSenderName() + ";" + epoch, (EntityPlayer)targetEntity, "jrmcLastAttacker");
                            }
                        }
                        else if (targetEntity != null)
                        {
                            JRMCoreH.nbt(targetEntity).setInteger("jrmcLastDamageReceived", dealt);
                            if (attacker != null)
                            {
                                int epoch = (int)(System.currentTimeMillis() / 1000L);
                                JRMCoreH.nbt(targetEntity).setString("jrmcLastAttacker", attacker.getCommandSenderName() + ";" + epoch);
                            }
                        }
                        return;
                    }
                    if ((attacker instanceof EntityPlayer))
                    {
                        String t = JRMCoreH.cly + StatCollector.translateToLocal("dbc.entitymasters.nofightinsafe");
                        attacker.addChatMessage(new ChatComponentText(JRMCoreH.cly + t));
                        event.ammount = 0.0F;

                        return;
                    }
                }
            }
            if ((!playerAttacked) && (source != DamageSource.outOfWorld) && ((targetEntity instanceof EntityPlayer)))
            {
                EntityPlayer targetPlayer = (EntityPlayer)targetEntity;
                int acc = JRMCoreH.getByte(targetPlayer, "jrmcAccept");
                if ((acc == 0) || (JRMCoreH.getByte(targetPlayer, "jrmcPwrtyp") == 0))
                {
                    event.setCanceled(false);
                    return;
                }
                int curBody = JRMCoreH.getInt(targetPlayer, "jrmcBdy");
                Entity attacker = source.getEntity();

                int[] PlyrAttrbts = JRMCoreH.PlyrAttrbts(targetPlayer);
                byte rc = JRMCoreH.getByte(targetPlayer, "jrmcRace");
                byte rls = JRMCoreH.getByte(targetPlayer, "jrmcRelease");
                if ((!targetPlayer.capabilities.isCreativeMode) && ((JRMCoreH.DBC())))
                {
                    event.ammount = 0.0F;
                    boolean doAttack = true;
                    if ((JRMCoreH.DBC()) && (JRMCoreConfig.sfzns)) {
                        doAttack = !JRMCoreHDBC.JRMCoreEHonLivingHurtSafeZone(targetEntity);
                    }
                    if (doAttack)
                    {
                        amount = damageEntity(targetPlayer, source, amount);

                        int dealt = 0;

                        JRMCoreH.a1t3(targetPlayer);
                        if ((attacker instanceof EntityNPCshadow))
                        {
                            if (curBody > amount) {
                                dealt = JRMCoreH.jrmcDam(targetPlayer, (int)amount, source);
                            } else {
                                attacker.setDead();
                            }
                        }
                        else if ((source.getDamageType().equals("EnergyAttack")) && ((source.getSourceOfDamage() instanceof EntityEnergyAtt))) {
                            dealt = JRMCoreH.jrmcDam(targetPlayer, (int)amount, source, ((EntityEnergyAtt)source.getSourceOfDamage()).getType());
                        } else {
                            dealt = JRMCoreH.jrmcDam(targetPlayer, (int)amount, source);
                        }
                        if (attacker != null) {
                            knockback(targetEntity, attacker, 1);
                        }
                        if (attacker != null) {
                            if ((attacker instanceof EntityPlayer)) {
                                JRMCoreH.setInt(dealt, (EntityPlayer)attacker, "jrmcLastDamageDealt");
                            } else {
                                JRMCoreH.nbt(attacker).setInteger("jrmcLastDamageDealt", dealt);
                            }
                        }
                        if ((targetEntity != null) && ((targetEntity instanceof EntityPlayer)))
                        {
                            JRMCoreH.setInt(dealt, (EntityPlayer)targetEntity, "jrmcLastDamageReceived");
                            if (attacker != null)
                            {
                                int epoch = (int)(System.currentTimeMillis() / 1000L);
                                JRMCoreH.setString(attacker.getCommandSenderName() + ";" + epoch, (EntityPlayer)targetEntity, "jrmcLastAttacker");
                            }
                        }
                        else if (targetEntity != null)
                        {
                            JRMCoreH.nbt(targetEntity).setInteger("jrmcLastDamageReceived", dealt);
                            if (attacker != null)
                            {
                                int epoch = (int)(System.currentTimeMillis() / 1000L);
                                JRMCoreH.nbt(targetEntity).setString("jrmcLastAttacker", attacker.getCommandSenderName() + ";" + epoch);
                            }
                        }
                        return;
                    }
                    if ((attacker instanceof EntityPlayer))
                    {
                        String t = JRMCoreH.cly + StatCollector.translateToLocal("dbc.entitymasters.nofightinsafe");
                        ((EntityPlayer)attacker).addChatMessage(new ChatComponentText(JRMCoreH.cly + t));
                        event.ammount = 0.0F;

                        return;
                    }
                }
            }
        }
    }

    protected float damageEntity(EntityPlayer targetPlayer, DamageSource source, float amount)
    {
        if (!targetPlayer.isEntityInvulnerable())
        {
            if (amount <= 0.0F) {
                return 0.0F;
            }
            if ((!source.isUnblockable()) && (targetPlayer.isBlocking()) && (amount > 0.0F)) {
                amount = (1.0F + amount) * 0.5F;
            }
            amount = ApplyArmor(targetPlayer, targetPlayer.inventory.armorInventory, source, amount);
            if (amount <= 0.0F) {
                return 0.0F;
            }
            if (amount != 0.0F) {
                targetPlayer.addExhaustion(source.getHungerDamage());
            }
        }
        return amount;
    }

    protected void damageEntity(EntityLivingBase targetEntity, DamageSource source, float amount)
    {
        if (!targetEntity.isEntityInvulnerable())
        {
            if (amount <= 0.0F) {
                return;
            }
            float f1 = amount;
            if (amount != 0.0F)
            {
                JRMCoreH.jrmctAll(4, targetEntity.getEntityId() + ";take;" + amount);
                float f2 = targetEntity.getHealth();
                targetEntity.setHealth(f2 - amount);
                targetEntity.func_110142_aN().func_94547_a(source, f2, amount);
                targetEntity.setAbsorptionAmount(targetEntity.getAbsorptionAmount() - amount);
            }
        }
    }

    private float ApplyArmor(EntityLivingBase entity, ItemStack[] inventory, DamageSource source, double damage)
    {
        int armorNum = 0;
        int armorVal = 0;
        for (ItemStack stack : inventory) {
            if (stack != null) {
                armorNum += 1;
            }
        }
        if ((entity instanceof EntityPlayer)) {
            for (int i = 0; i < 1; i++)
            {
                ItemStack ws = ExtendedPlayer.get((EntityPlayer)entity).inventory.func_70301_a(0);
                if (ws != null)
                {
                    if (ws != null)
                    {
                        armorNum += 1;

                        float armorMax = 5.0F;

                        int itemProtect = (int)(damage * (armorMax / (armorMax + 25.0F)));
                        itemProtect = (int)(damage < 30.0D ? itemProtect : armorMax);

                        int itemDamage = damage > 5000.0D ? 2 : damage > 10000.0D ? 3 : 1;
                        itemDamage = itemDamage < 1 ? 1 : itemDamage;
                        armorVal += itemProtect;
                        ws.damageItem(itemDamage, entity);
                    }
                    if (ws.stackSize <= 0) {
                        ExtendedPlayer.get((EntityPlayer)entity).inventory.func_70299_a(0, null);
                    }
                }
            }
        }
        if (armorNum == 0) {
            return (float)damage;
        }
        for (int i = 0; i < 4; i++)
        {
            ItemStack stack = inventory[i];
            if (stack != null)
            {
                if (stack != null) {
                    if (((stack.getItem() instanceof ItemArmor)) && (!source.isUnblockable()))
                    {
                        ItemArmor armor = (ItemArmor)stack.getItem();
                        float armorMax = armor.damageReduceAmount;

                        int itemProtect = (int)(damage * (armorMax / (armorMax + 25.0F)));
                        itemProtect = (int)(damage < 30.0D ? itemProtect : armorMax);

                        int itemDamage = damage > 5000.0D ? 2 : damage > 10000.0D ? 3 : 1;
                        itemDamage = itemDamage < 1 ? 1 : itemDamage;
                        armorVal += itemProtect;
                        stack.damageItem(itemDamage, entity);
                    }
                }
                if (stack.stackSize <= 0) {
                    inventory[i] = null;
                }
            }
        }
        return (float)(damage - armorVal);
    }

    protected void knockback(EntityLivingBase targetEntity, Entity attacker, int knockbackStrength)
    {
        if (knockbackStrength > 0)
        {
            float var25 = MathHelper.sqrt_double(attacker.motionX * attacker.motionX + attacker.motionZ * attacker.motionZ);
            if (var25 > 0.0F) {
                targetEntity.addVelocity(attacker.motionX * knockbackStrength * 0.6000000238418579D / var25, 0.1D, attacker.motionZ * knockbackStrength * 0.6000000238418579D / var25);
            }
        }
    }
}
