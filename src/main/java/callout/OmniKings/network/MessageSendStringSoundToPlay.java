package callout.OmniKings.network;

import JinRyuu.JRMCore.JRMCoreH;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;

import java.util.UUID;

public class MessageSendStringSoundToPlay implements IMessage {

    private String toSend;

    public MessageSendStringSoundToPlay() {
    }

    public MessageSendStringSoundToPlay(String toSend) {
        this.toSend = toSend;
    }

    @Override
    public void toBytes(ByteBuf buf) {
        ByteBufUtils.writeUTF8String(buf,toSend);
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        toSend = ByteBufUtils.readUTF8String(buf);
    }

    public static class MyMessageHandler implements
            IMessageHandler<MessageSendStringSoundToPlay, IMessage> {

        @Override
        public IMessage onMessage(MessageSendStringSoundToPlay message,
                                  MessageContext ctx) {

            String SoundStr = message.toSend;

            for (Object player: MinecraftServer.getServer().getConfigurationManager().playerEntityList) {
                if(((EntityPlayer)player).getUniqueID().toString().equals(ctx.getServerHandler().playerEntity.getUniqueID().toString()))
                {
                    ((EntityPlayer)player).worldObj.playSoundAtEntity((EntityPlayer)player, SoundStr,1,1);
                }
            }

            return null;
        }
    }
}
