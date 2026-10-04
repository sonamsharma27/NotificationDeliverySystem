package provider;

import model.DeliveryResult;
import model.Notification;

// Not using this since this is too generic
// We need channel specific provider interfaces
public interface IChannelProvider {
    public  DeliveryResult send(Notification notification);
}
