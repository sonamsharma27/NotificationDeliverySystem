import enums.Enums;
import exceptions.ChannelNotRegisteredException;
import exceptions.InvalidNotificationException;
import model.Content;
import model.DeliveryResult;
import model.Notification;
import model.Recipient;
import registry.ChannelRegistry;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

void main() {

    NotificationRegistryInitializer initializer = new NotificationRegistryInitializer();
    ChannelRegistry registry =initializer.initialize();
    NotificationService notificationService = new NotificationService(registry);
    Content content = new Content("Notification body","Notification subject",new ArrayList<>());
    Recipient recipient = new Recipient("8082649344");
    Notification notification = new Notification(content, new HashMap<>(), recipient);
    try {
        DeliveryResult result = notificationService.sendNotification(notification, Enums.ChannelType.SMS);
        System.out.println(result);
    } catch (InvalidNotificationException | ChannelNotRegisteredException e){
        System.out.println(e.getMessage());
    }

}
