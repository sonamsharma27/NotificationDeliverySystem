package provider;

import enums.Enums;
import exceptions.ProviderRejectedException;
import exceptions.ProviderTimeoutException;
import model.DeliveryResult;
import notification.Sms;

import java.time.Instant;

public class TwilioChannelProvider implements SmsProvider {

    @Override
    public DeliveryResult send(Sms sms){
        String phoneNumber = sms.getMobileNumber();
        String body = sms.getBody();
        try {
            // would contain the call to provider for sending notification
            System.out.println("Twilio sent [SMS] notification to " + phoneNumber + "\n" +  body + "\n");
        }catch(ProviderTimeoutException e){
            // timeout doesn't necessarily mean notification delivery failed
            return  new DeliveryResult(Enums.Status.UNKNOWN, Enums.ErrorCode.PROVIDER_TIMEOUT,"Provider timeout",e.getProviderReference());
        } catch (ProviderRejectedException e){
            return  new DeliveryResult(Enums.Status.FAILED, Enums.ErrorCode.PROVIDER_REJECTED,"Provider rejected",e.getProviderReference());
        }
        return new DeliveryResult(Enums.Status.SUCCESS,null,null,"Twilio-"+phoneNumber+"-" +Instant.now());
    }
}
