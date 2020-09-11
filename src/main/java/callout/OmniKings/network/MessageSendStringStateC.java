package callout.OmniKings.network;

import callout.OmniKings.event.RenderPlayerEventOmniKings;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import io.netty.buffer.ByteBuf;

import java.util.UUID;

public class MessageSendStringStateC implements IMessage {

    private String toSend;

    public MessageSendStringStateC() {
    }

    public MessageSendStringStateC(String toSend) {
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

    public static class MyMessageHandlerC implements
            IMessageHandler<MessageSendStringStateC, IMessage> {

        @Override
        public IMessage onMessage(MessageSendStringStateC message,
                                  MessageContext ctx) {

            UUID playerStrUUID = UUID.fromString(message.toSend.split("#")[0]);
            Byte playerState = Byte.parseByte(message.toSend.split("#")[1]);

            RenderPlayerEventOmniKings.states.put(playerStrUUID, playerState);
            return null;
        }
    }
}
