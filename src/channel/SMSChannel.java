package channel;

import enums.Enums;
import exceptions.InvalidNotificationException;
import exceptions.ProviderRejectedException;
import exceptions.ProviderTimeoutException;
import model.DeliveryResult;
import model.Notification;
import model.Recipient;
import notification.Sms;
import provider.IChannelProvider;
import provider.SmsProvider;

public class SMSChannel implements IChannel{

    private final SmsProvider provider;

    public  SMSChannel(SmsProvider provider){
        this.provider = provider;
    }

    @Override
    public DeliveryResult sendNotification(Notification notification){
            if(!validateRecipient(notification.getRecipient())){
                throw new InvalidNotificationException("Invalid recipient received: "+notification.getRecipient());
            }

            try {
                Sms sms =maptoSms(notification);
                return this.provider.send(sms);
            } catch(ProviderTimeoutException e){
                return  new DeliveryResult(Enums.Status.FAILED, Enums.ErrorCode.PROVIDER_TIMEOUT,"Provider timeout",e.getProviderReference());
            } catch (ProviderRejectedException e){
                return  new DeliveryResult(Enums.Status.FAILED, Enums.ErrorCode.PROVIDER_REJECTED,"Provider rejected",e.getProviderReference());
            }
    }

    private Sms maptoSms(Notification notification){
        return new Sms(notification.getId(), notification.getContent().getBody(),notification.getRecipient().getValue());
    }
    boolean validateRecipient(Recipient recipient){
        String phoneNumber=recipient.getValue();
        if(phoneNumber.length()!=10){
            return false;
        }
        for(int i=0; i<phoneNumber.length(); i++){
            if(!(phoneNumber.charAt(i)>='0' && phoneNumber.charAt(i)<='9')){
                return  false;
            }
        }
        return true;
    }
}
