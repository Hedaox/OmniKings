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

public class MessageSendMaxAttrC implements IMessage {

    private int toSend;

    public MessageSendMaxAttrC() {
    }

    public MessageSendMaxAttrC(int toSend) {
        this.toSend = toSend;
    }

    @Override
    public void toBytes(ByteBuf buf) {
        ByteBufUtils.writeVarInt(buf, toSend, 4);
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        toSend = ByteBufUtils.readVarInt(buf, 4);
    }

    public static class MyMessageHandlerC implements
            IMessageHandler<MessageSendMaxAttrC, IMessage> {

        @Override
        public IMessage onMessage(MessageSendMaxAttrC message,
                                  MessageContext ctx) {

            RenderPlayerEventOmniKings.maxAttr = message.toSend;

            return null;
        }
    }
}
