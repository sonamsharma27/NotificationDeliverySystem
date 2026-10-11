package model;

import deliveryTargets.IDeliveryTarget;

import java.util.List;

public class NotificationEvent {
    private final String eventId;
    private final Notification notification;
    private final List<IDeliveryTarget> deliveryTargetList;
    public NotificationEvent(String eventId, Notification notification, List<IDeliveryTarget> deliveryTargetList) {
        this.eventId = eventId;
        this.notification = notification;
        this.deliveryTargetList = deliveryTargetList;
    }

    public  String getEventId(){
        return eventId;
    }

    public Notification getNotification() {
        return notification;
    }

    public List<IDeliveryTarget> getDeliveryTargetList() {
        return List.copyOf(deliveryTargetList);
    }


}
