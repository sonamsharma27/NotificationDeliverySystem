package channel;

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
           DeliveryResult result =  this.provider.send(notification);
           return  result;
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
