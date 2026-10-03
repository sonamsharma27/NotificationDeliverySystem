package registry;

import enums.Enums.*;

import java.nio.channels.Channel;
import java.util.HashMap;
import java.util.Map;

public class ChannelRegistry {
    private final Map<ChannelType, Channel> registry;

    public ChannelRegistry(Map<ChannelType, Channel> registry){
        this.registry=registry;
    }
    public ChannelRegistry(){
        this.registry= new HashMap<>();
    }

    public  Channel getChannel(ChannelType channelType){
        return  registry.get(channelType);
    }

    public  void addChannel(ChannelType channelType, Channel channel){
        registry.put(channelType,channel);
    }

}
