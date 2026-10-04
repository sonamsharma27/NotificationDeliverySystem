import enums.Enums;
import exceptions.ChannelNotRegisteredException;
import exceptions.InvalidNotificationException;
import model.DeliveryResult;
import model.Notification;
import registry.ChannelRegistry;

import channel.IChannel;

public class NotificationService {
    ChannelRegistry registry;
    public NotificationService(ChannelRegistry registry){
        this.registry = registry;
    }

    public  DeliveryResult  sendNotification(Notification notification, Enums.ChannelType channelType){

        if(channelType==null){
            throw new InvalidNotificationException("Channel type cannot be null");
        }

        if(notification==null){
            throw new InvalidNotificationException("Notification cannot be null");
        }

        IChannel notificationChannel = registry.getChannel(channelType);
        if(notificationChannel==null){
            throw new ChannelNotRegisteredException("No channel registered with type: "+channelType);
        }

        return notificationChannel.sendNotification(notification);
    }
}
