import enums.Enums;
import model.DeliveryResult;
import model.Notification;
import registry.ChannelRegistry;

import channel.IChannel;

public class NotificationService {
    ChannelRegistry registry;

    public NotificationService(){
        new NotificationRegistryInitializer();
    }

    public  DeliveryResult  sendNotification(Notification notification, Enums.ChannelType channelType){
        IChannel notificationChannel = registry.getChannel(channelType);
        return notificationChannel.sendNotification(notification);
    }
}
