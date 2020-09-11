package callout.OmniKings.network;

import callout.OmniKings.core.OmniKingsCoreH;
import com.google.gson.Gson;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;

public class MessageSendClientSomethingC implements IMessage {

    private String toSend;

    public MessageSendClientSomethingC() {
    }

    public MessageSendClientSomethingC(String toSend) {
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
            IMessageHandler<MessageSendClientSomethingC, IMessage> {

        @Override
        public IMessage onMessage(MessageSendClientSomethingC message,
                                  MessageContext ctx) {

            String messageToSend = message.toSend.split("#")[0];
            String whatToSend = message.toSend.split("#")[1];

            if(whatToSend.equals("TransBAStBnP"))
            {
                OmniKingsCoreH.TransBaStBnP = new Gson().fromJson(messageToSend, float[][].class);
            }

            return null;
        }
    }
}
