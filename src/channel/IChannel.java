package channel;

import model.DeliveryResult;
import model.Notification;
import enums.Enums.ChannelType;

public interface IChannel {
 public DeliveryResult sendNotification(Notification notification);
}

