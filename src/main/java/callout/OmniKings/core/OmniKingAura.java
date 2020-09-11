package callout.OmniKings.core;

import JinRyuu.DragonBC.common.DBCClient;
import JinRyuu.DragonBC.common.Npcs.EntityAura2;
import JinRyuu.DragonBC.common.Npcs.EntityAuraRing;
import JinRyuu.JRMCore.JRMCoreH;
import callout.OmniKings.entities.EntityAuraLightning;
import callout.OmniKings.network.MessageSendStringSoundToPlay;
import callout.OmniKings.proxy.ClientProxy;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;

import java.util.List;

public class OmniKingAura {
    public static Entity auraLightning = null;
    public static Entity auraGod = null;
    public static Entity auraRing = null;
    public static Entity aura = null;
    private static double random = Math.random();


    public static void setAuraLightning(EntityPlayer _player)
    {
        int cr = 0;
        boolean w = false;

        if ((JRMCoreH.plyrs != null) && (JRMCoreH.plyrs.length > 0) && (JRMCoreH.dnn(2)) && (JRMCoreH.dnn(10))) {
            for (int pl = 0; pl < JRMCoreH.plyrs.length; pl++) {
                if (JRMCoreH.plyrs[pl].equals(_player.getCommandSenderName())) {
                    cr = Integer.parseInt(JRMCoreH.dat10[pl].split(";")[0]);
                    w = (JRMCoreH.StusEfcts(7, JRMCoreH.StusEfctsClient(pl))) || ((JRMCoreH.StusEfcts(9, JRMCoreH.StusEfctsClient(pl))) && (JRMCoreH.data(_player.getCommandSenderName(), 3, "0").contains("1")) && (!JRMCoreH.StusEfctsMe(4)));

                }
            }
        }

        auraLightning = new EntityAuraLightning(_player.worldObj, _player.getCommandSenderName(), 1, 0.0F, 0.0F, cr, w);

        _player.worldObj.spawnEntityInWorld(auraLightning);

        if (_player.ticksExisted % 25 == (int) random * 25 || _player.ticksExisted % 70 == 1) {
            ClientProxy.network.sendToServer(new MessageSendStringSoundToPlay("jinryuudragonbc:1610.spark"));
            random = Math.random();
        }
    }

    public static void setAura(EntityPlayer _player, int _color)
    {
        if(_player.ticksExisted%8 == 1) {
            int cr = 0;
            boolean w = false;

            if ((JRMCoreH.plyrs != null) && (JRMCoreH.plyrs.length > 0) && (JRMCoreH.dnn(2)) && (JRMCoreH.dnn(10))) {
                for (int pl = 0; pl < JRMCoreH.plyrs.length; pl++) {
                    if (JRMCoreH.plyrs[pl].equals(_player.getCommandSenderName())) {
                        cr = Integer.parseInt(JRMCoreH.dat10[pl].split(";")[0]);
                        w = (JRMCoreH.StusEfcts(7, JRMCoreH.StusEfctsClient(pl))) || ((JRMCoreH.StusEfcts(9, JRMCoreH.StusEfctsClient(pl))) && (JRMCoreH.data(_player.getCommandSenderName(), 3, "0").contains("1")) && (!JRMCoreH.StusEfctsMe(4)));

                    }
                }
            }

            int al = 50;
            int color = -1;

            if(_color == -1)
            {
                for (int pl = 0; pl < JRMCoreH.plyrs.length; pl++) {
                    String[] dat5 = JRMCoreH.data5[pl].split(";");
                    al = Integer.parseInt(dat5[0]);
                    color = Integer.parseInt(dat5[1]);
                }

                color = color > 0 ? color : JRMCoreH.Algnmnt_rc(al);
            }
            else
            {
                color = _color;
            }

            aura = new EntityAura2(_player.worldObj, _player.getCommandSenderName(), color, 0.0F, 0.0F, cr, w);

            _player.worldObj.spawnEntityInWorld(aura);
        }
    }

    public static void setAuraRing(EntityPlayer _player, int _color)
    {
        if(_player.ticksExisted%30 == 1) {
            int cr = 0;
            boolean w = false;

            if ((JRMCoreH.plyrs != null) && (JRMCoreH.plyrs.length > 0) && (JRMCoreH.dnn(2)) && (JRMCoreH.dnn(10))) {
                for (int pl = 0; pl < JRMCoreH.plyrs.length; pl++) {
                    if (JRMCoreH.plyrs[pl].equals(_player.getCommandSenderName())) {
                        cr = Integer.parseInt(JRMCoreH.dat10[pl].split(";")[0]);
                        w = (JRMCoreH.StusEfcts(7, JRMCoreH.StusEfctsClient(pl))) || ((JRMCoreH.StusEfcts(9, JRMCoreH.StusEfctsClient(pl))) && (JRMCoreH.data(_player.getCommandSenderName(), 3, "0").contains("1")) && (!JRMCoreH.StusEfctsMe(4)));

                    }
                }
            }
            int al = 50;
            int color = -1;

            if(_color == -1)
            {
                for (int pl = 0; pl < JRMCoreH.plyrs.length; pl++) {
                    String[] dat5 = JRMCoreH.data5[pl].split(";");
                    al = Integer.parseInt(dat5[0]);
                    color = Integer.parseInt(dat5[1]);
                }

                color = color > 0 ? color : JRMCoreH.Algnmnt_rc(al);
            }
            else
            {
                color = _color;
            }

            auraRing = new EntityAuraRing(_player.worldObj, _player.getCommandSenderName(), color, 0.0F, 0.0F, cr);

            _player.worldObj.spawnEntityInWorld(auraRing);
        }
    }

    public static void setAuraGod(EntityPlayer _player)
    {
        if(_player.ticksExisted%4 == 1) {
            int cr = 0;
            boolean w = false;

            if ((JRMCoreH.plyrs != null) && (JRMCoreH.plyrs.length > 0) && (JRMCoreH.dnn(2)) && (JRMCoreH.dnn(10))) {
                for (int pl = 0; pl < JRMCoreH.plyrs.length; pl++) {
                    if (JRMCoreH.plyrs[pl].equals(_player.getCommandSenderName())) {
                        cr = Integer.parseInt(JRMCoreH.dat10[pl].split(";")[0]);
                        w = (JRMCoreH.StusEfcts(7, JRMCoreH.StusEfctsClient(pl))) || ((JRMCoreH.StusEfcts(9, JRMCoreH.StusEfctsClient(pl))) && (JRMCoreH.data(_player.getCommandSenderName(), 3, "0").contains("1")) && (!JRMCoreH.StusEfctsMe(4)));

                    }
                }
            }

            auraGod = new EntityAura2(_player.worldObj, _player.getCommandSenderName(), 16646144, 0.0F, 0.0F, cr, w);

            boolean plyrSP = (DBCClient.mc.thePlayer.getCommandSenderName().equals(_player.getCommandSenderName())) && (DBCClient.mc.gameSettings.thirdPersonView == 0);
            ((EntityAura2) auraGod).setAlp(plyrSP ? 0.05F : 0.2F);
            ((EntityAura2) auraGod).setTex("aurai");
            ((EntityAura2) auraGod).setTexL2("aurai2");
            ((EntityAura2) auraGod).setColL2(16747301);

            _player.worldObj.spawnEntityInWorld(auraGod);
        }
        if (_player.ticksExisted%55 == 1)
        {
            ClientProxy.network.sendToServer(new MessageSendStringSoundToPlay("jinryuudragonbc:1610.aurag"));
        }
    }

    public static void removeAura(EntityPlayer _player)
    {
        //remove not godly OmniKingAura.aura on player
        List<EntityAura2> AurasNearPlayer = _player.worldObj.getEntitiesWithinAABB(EntityAura2.class, AxisAlignedBB.getBoundingBox(_player.posX, _player.posY, _player.posZ, _player.posX , _player.posY, _player.posZ));
        if (AurasNearPlayer.size() > 0) {
            for (EntityAura2 aura : AurasNearPlayer) {
                if(aura != OmniKingAura.auraGod)
                {
                    aura.setDead();
                }
            }
        }
        //remove not godly OmniKingAura.aura on player
        List<EntityAuraRing> aurasRingNearPlayer = _player.worldObj.getEntitiesWithinAABB(EntityAuraRing.class, AxisAlignedBB.getBoundingBox(_player.posX, _player.posY, _player.posZ, _player.posX , _player.posY, _player.posZ));
        if (aurasRingNearPlayer.size() > 0) {
            for (EntityAuraRing aura : aurasRingNearPlayer) {
                if(aura != OmniKingAura.auraRing)
                {
                    aura.setDead();
                }
            }
        }
    }


}
