package provider;

import enums.Enums;
import model.DeliveryResult;
import notification.Sms;

import java.time.Instant;

public class TwilioChannelProvider implements SmsProvider {

    @Override
    public DeliveryResult send(Sms sms){
        String phoneNumber = sms.getMobileNumber();
        String body = sms.getBody();
        System.out.println("Twilio sent [SMS] notification to " + phoneNumber + "\n" +  phoneNumber+ "\n"+phoneNumber);
        return new DeliveryResult(Enums.Status.SUCCESS,null,null,"Twilio-"+phoneNumber+"-" +Instant.now());
    }
}
