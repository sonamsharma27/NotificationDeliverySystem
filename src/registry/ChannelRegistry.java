package registry;

import enums.Enums.*;

import java.nio.channels.Channel;
import java.util.HashMap;
import java.util.Map;
import channel.IChannel;
import exceptions.ChannelNotRegisteredException;

public class ChannelRegistry {
    private final Map<ChannelType, IChannel> registry;

    public ChannelRegistry(Map<ChannelType, IChannel> registry){
        this.registry=registry;
    }
    public ChannelRegistry(){
        this.registry= new HashMap<>();
    }

    public  IChannel getChannel(ChannelType channelType){
        IChannel channel = registry.get(channelType);
        if(channel == null){
            throw  new ChannelNotRegisteredException("Channel not registered with type: "+channelType);
        }
        return  channel;
    }

    //"The registry is populated during application initialization and treated as immutable during runtime.
    // Therefore concurrent reads are safe. If dynamic registration becomes a requirement,
    // the data structure and synchronization strategy can be revisited"
    public  void addChannel(ChannelType channelType, IChannel channel){
        registry.put(channelType,channel);
    }

}
