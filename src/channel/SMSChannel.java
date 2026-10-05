package channel;

import exceptions.InvalidNotificationException;
import model.DeliveryResult;
import model.Notification;
import model.Recipient;
import notification.Sms;
import provider.SmsProvider;

public class SMSChannel implements IChannel{

    private final SmsProvider provider;

    public  SMSChannel(SmsProvider provider){
        this.provider = provider;
    }

    @Override
    public DeliveryResult sendNotification(Notification notification){
            validateNotification(notification);
            Sms sms =mapToSms(notification);
            return this.provider.send(sms);
    }

    private Sms mapToSms(Notification notification){
        try{
            return new Sms(notification.getId(), notification.getContent().getBody(),notification.getRecipient().getValue());
        }catch (Exception e){
            throw new InvalidNotificationException("Notification is invalid. One or more fields is invalid/missing: "+e.getMessage());
        }
    }
    //validating the must-have fields
    private void  validateNotification(Notification notification){
        if(notification==null){
           throw  new InvalidNotificationException("Notification cannot be null");
        }
        if(notification.getRecipient()==null){
            throw  new InvalidNotificationException("Recipient cannot be null");
        }
        if(!validateRecipient(notification.getRecipient())){
            throw new InvalidNotificationException("Invalid recipient received: "+notification.getRecipient());
        }
    }

    private boolean validateRecipient(Recipient recipient){
        String phoneNumber=recipient.getValue();
        if(phoneNumber==null){
            return false;
        }
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
