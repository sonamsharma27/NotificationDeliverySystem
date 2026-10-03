package model;

import java.time.Instant;
import java.util.Map;
import java.util.UUID;

public class Notification {
   private final String id;
   private final Content content;
   private final Map<String,String> metadata;
   private final Instant createdAt;
   private final Recipient recipient;

    public  Notification(Content content, Map<String,String> metadata, Recipient recipient){
        this.id=UUID.randomUUID().toString();
        this.content=content;
        this.metadata=metadata;
        this.recipient=recipient;
        this.createdAt=Instant.now();
    }

    public String getId() {
        return id;
    }

    public Content getContent() {
        return content;
    }

    public Map<String, String> getMetadata() {
        return metadata;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Recipient getRecipient() {
        return recipient;
    }

}
