package provider;

import model.DeliveryResult;
import notification.Sms;

public interface SmsProvider {
    DeliveryResult send(Sms sms);
}
