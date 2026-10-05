import enums.Enums;
import exceptions.ChannelNotRegisteredException;
import exceptions.InvalidNotificationException;
import model.DeliveryResult;
import model.Notification;
import registry.ChannelRegistry;

import channel.IChannel;

public class NotificationService {
    private final ChannelRegistry registry;
    public NotificationService(ChannelRegistry registry){
        this.registry = registry;
    }

    public  DeliveryResult  sendNotification(Notification notification, Enums.ChannelType channelType){

        validateRequest(notification, channelType);

        IChannel notificationChannel = registry.getChannel(channelType);

        return notificationChannel.sendNotification(notification);
    }

    private void validateRequest(Notification notification, Enums.ChannelType channelType){

        if(notification==null){
            throw new InvalidNotificationException("Notification cannot be null");
        }

        if(channelType==null){
            throw new InvalidNotificationException("Channel type cannot be null");
        }


    }
}
