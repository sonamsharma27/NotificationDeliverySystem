package provider;

import model.DeliveryResult;
import model.Notification;

public interface IChannelProvider {
    public  DeliveryResult send(Notification notification);
}
