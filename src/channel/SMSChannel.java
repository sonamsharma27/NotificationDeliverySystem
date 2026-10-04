package channel;

import enums.Enums;
import exceptions.InvalidNotificationException;
import exceptions.ProviderRejectedException;
import exceptions.ProviderTimeoutException;
import model.DeliveryResult;
import model.Notification;
import model.Recipient;
import provider.IChannelProvider;

public class SMSChannel implements IChannel{

    private final IChannelProvider provider;

    public  SMSChannel(IChannelProvider provider){
        this.provider = provider;
    }
    public DeliveryResult sendNotification(Notification notification){
            if(!validateRecipient(notification.getRecipient())){
                throw new InvalidNotificationException("Invalid recipient received: "+notification.getRecipient());
            }

            try {
                return this.provider.send(notification);
            } catch(ProviderTimeoutException e){
                return  new DeliveryResult(Enums.Status.FAILED, Enums.ErrorCode.PROVIDER_TIMEOUT,"Provider timeout",e.getProviderReference());
            } catch (ProviderRejectedException e){
                return  new DeliveryResult(Enums.Status.FAILED, Enums.ErrorCode.PROVIDER_REJECTED,"Provider rejected",e.getProviderReference());
            }
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
