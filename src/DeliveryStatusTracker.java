import model.DeliveryResult;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class DeliveryStatusTracker {
    private final Map<String, DeliveryResult> deliveryResults =
            new ConcurrentHashMap<>();

    public DeliveryStatusTracker() {
    }

    public void recordResult(String deliveryId, DeliveryResult result) {
        // Validate arguments and record the result.
        if(deliveryId == null || deliveryId.isBlank() || result == null){
            throw  new IllegalArgumentException("Delivery id or result cannot be null");
        }
        deliveryResults.put(deliveryId,result);
    }

    public Optional<DeliveryResult> getResult(String deliveryId) {
        // Validate the ID and retrieve the result if present.
        if(deliveryId == null || deliveryId.isBlank()){
            throw  new IllegalArgumentException("Delivery id or result cannot be null");
        }
        return Optional.ofNullable(deliveryResults.get(deliveryId));
    }
}
