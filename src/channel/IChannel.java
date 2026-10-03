package channel;

import model.DeliveryResult;
import model.Notification;
import enums.Enums.ChannelType;

interface INotificationChannel {
 public DeliveryResult sendNotification(Notification notification);
 public DeliveryResult sendNotification(Notification notification, ChannelType channelType);
}

