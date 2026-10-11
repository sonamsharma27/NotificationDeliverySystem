package deliveryTargets;

import enums.Enums;

public class SmsTarget implements  IDeliveryTarget{
    private final String phoneNumber;
    private final Enums.ChannelType channelType;

    public SmsTarget(Enums.ChannelType channelType, String phoneNumber){
        this.phoneNumber=phoneNumber;
        this.channelType=channelType;
    }
    @Override
    public Enums.ChannelType getChannelType() {
        return channelType;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

}
