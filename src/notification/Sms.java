package notification;

import java.util.List;

public class Sms {
    private final String id;
    private final String body;
    private final String mobileNumber;
    public Sms(String id,String body, String mobileNumber){
        this.id=id;
        this.body=body;
        this.mobileNumber=mobileNumber;
    }

    public String getId() {
        return id;
    }

    public String getBody() {
        return body;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }
}