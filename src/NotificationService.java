import deliveryTargets.IDeliveryTarget;
import enums.Enums;
import exceptions.ChannelNotRegisteredException;
import exceptions.InvalidNotificationException;
import model.DeliveryResult;
import model.Notification;
import registry.ChannelRegistry;

import channel.IChannel;

import java.util.List;

public class NotificationService {
    private final ChannelRegistry registry;
    public NotificationService(ChannelRegistry registry){
        this.registry = registry;
    }

    public  DeliveryResult  sendNotification(Notification notification, IDeliveryTarget deliveryTarget){

        validateRequest(notification, deliveryTarget);

        IChannel notificationChannel = registry.getChannel(deliveryTarget.getChannelType());

        return notificationChannel.sendNotification(notification);
    }

    private void validateRequest(Notification notification, IDeliveryTarget deliveryTarget){

        if(notification==null){
            throw new InvalidNotificationException("Notification cannot be null");
        }

        if(deliveryTarget==null){
            throw new InvalidNotificationException("Channel type cannot be null or empty");
        }


    }
}
