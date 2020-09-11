package callout.OmniKings.network;

import JinRyuu.JRMCore.JRMCoreH;
import callout.OmniKings.proxy.ServerProxy;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;

import static JinRyuu.JRMCore.JRMCoreH.nbt;

public class MessageSendStringState implements IMessage {

    private String toSend;

    public MessageSendStringState() {
    }

    public MessageSendStringState(String toSend) {
        this.toSend = toSend;
    }

    @Override
    public void toBytes(ByteBuf buf) {
        ByteBufUtils.writeUTF8String(buf, toSend);
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        toSend = ByteBufUtils.readUTF8String(buf);
    }

    public static class MyMessageHandler implements
            IMessageHandler<MessageSendStringState, IMessage> {

        @Override
        public IMessage onMessage(MessageSendStringState message,
                                  MessageContext ctx) {

            String playerStrUUID = message.toSend.split("#")[0];
            Byte playerState = Byte.parseByte(message.toSend.split("#")[1]);

            for (Object player : MinecraftServer.getServer().getConfigurationManager().playerEntityList) {
                if (((EntityPlayer) player).getUniqueID().toString().equals(playerStrUUID)) {
                    ServerProxy.network.sendToAll(new MessageSendStringStateC(((EntityPlayer) player).getUniqueID().toString() + "#" + playerState));
                    JRMCoreH.setByte(playerState, (EntityPlayer)player,"jrmcState");
                }
            }
            return null;
        }
    }
}
