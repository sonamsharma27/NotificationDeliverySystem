import channel.IChannel;
import channel.SMSChannel;
import enums.Enums;
import provider.IChannelProvider;
import provider.SmsProvider;
import provider.TwilioChannelProvider;
import registry.ChannelRegistry;

public class NotificationRegistryInitializer {

    public  NotificationRegistryInitializer(){}

    public ChannelRegistry initialize(){
        ChannelRegistry registry = new ChannelRegistry();
        SmsProvider provider = new TwilioChannelProvider();
        // if there is some config, we will instantiate provider based on config
        IChannel smsChannel = new SMSChannel(provider);
        registry.addChannel(Enums.ChannelType.SMS,smsChannel);
        return registry;
    }
}
