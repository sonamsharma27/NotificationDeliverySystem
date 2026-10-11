package deliveryTargets;

import channel.IChannel;
import enums.Enums;

public interface IDeliveryTarget {
      Enums.ChannelType getChannelType();
}
