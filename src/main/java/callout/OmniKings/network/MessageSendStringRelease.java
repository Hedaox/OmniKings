package callout.OmniKings.network;

import callout.OmniKings.event.RenderPlayerEventOmniKings;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;

import java.util.UUID;

public class MessageSendStringRelease implements IMessage {

    private String toSend;

    public MessageSendStringRelease() {
    }

    public MessageSendStringRelease(String toSend) {
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
            IMessageHandler<MessageSendStringRelease, IMessage> {

        @Override
        public IMessage onMessage(MessageSendStringRelease message,
                                  MessageContext ctx) {

            String playerStrUUID = message.toSend.split("#")[0];
            Byte playerRelease = Byte.parseByte(message.toSend.split("#")[1]);

            for (Object player: MinecraftServer.getServer().getConfigurationManager().playerEntityList) {
                if(((EntityPlayer)player).getUniqueID().toString().equals(playerStrUUID))
                {
                    RenderPlayerEventOmniKings.releases.put(((EntityPlayer) player).getUniqueID(), playerRelease);
                }
            }

            return null;
        }
    }
}
