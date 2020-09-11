package callout.OmniKings.network;

import JinRyuu.JRMCore.JRMCoreH;
import callout.OmniKings.core.OmniKingsCoreH;
import callout.OmniKings.proxy.ServerProxy;
import com.google.gson.Gson;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;

public class MessageAskServerSomething implements IMessage {

    private String toSend;

    public MessageAskServerSomething() {
    }

    public MessageAskServerSomething(String toSend) {
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
            IMessageHandler<MessageAskServerSomething, IMessage> {

        @Override
        public IMessage onMessage(MessageAskServerSomething message,
                                  MessageContext ctx) {

            if(message.toSend.equals("TransBAStBnP"))
            {
                String jsonTransBaStBnP = new Gson().toJson(OmniKingsCoreH.TransBaStBnPO);
                ServerProxy.network.sendTo(new MessageSendClientSomethingC(jsonTransBaStBnP + "#TransBAStBnP"), ctx.getServerHandler().playerEntity);
            }
            else if(message.toSend.split("#")[0].equals("SetInteger"))
            {
                String playerUUIDStr = message.toSend.split("#")[1];
                String thingToChangeStr =   message.toSend.split("#")[2];
                int valueToSet =  Integer.parseInt(message.toSend.split("#")[3]);
                EntityPlayer player = ctx.getServerHandler().playerEntity;
                if(player.getUniqueID().toString().equals(playerUUIDStr))
                {
                    JRMCoreH.setInt(valueToSet, player, thingToChangeStr);
                }
            }
            return null;
        }
    }
}
