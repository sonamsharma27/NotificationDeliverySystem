import channel.SMSChannel;
import enums.Enums;
import provider.IChannelProvider;
import provider.TwilioChannelProvider;
import registry.ChannelRegistry;

public class NotificationRegistryInitializer {
    ChannelRegistry registry;
    public NotificationRegistryInitializer(){
        IChannelProvider provider = new TwilioChannelProvider();
            registry.addChannel(Enums.ChannelType.SMS,new SMSChannel(provider));
    }
}
