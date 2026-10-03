package registry;

import enums.Enums.*;

import java.nio.channels.Channel;
import java.util.HashMap;
import java.util.Map;
import channel.IChannel;

public class ChannelRegistry {
    private final Map<ChannelType, IChannel> registry;

    public ChannelRegistry(Map<ChannelType, IChannel> registry){
        this.registry=registry;
    }
    public ChannelRegistry(){
        this.registry= new HashMap<>();
    }

    public  IChannel getChannel(ChannelType channelType){
        return  registry.get(channelType);
    }

    public  void addChannel(ChannelType channelType, IChannel channel){
        registry.put(channelType,channel);
    }

}
