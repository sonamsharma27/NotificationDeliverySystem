package provider;

import enums.Enums;
import model.Content;
import model.DeliveryResult;
import model.Notification;

import java.time.Instant;

public class TwilioChannelProvider implements IChannelProvider {

    public DeliveryResult send(Notification notification){
        String phoneNumber = notification.getRecipient().getValue();
        Content content = notification.getContent();
        System.out.println("Twilio sent [SMS] notification to " + phoneNumber + "\n" +  content.getSubject()+ "\n"+content.getBody());
        return new DeliveryResult(Enums.Status.SUCCESS,null,null,"Twilio-"+phoneNumber+"-" +Instant.now());
    }
}
