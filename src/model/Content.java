package model;

import java.util.ArrayList;
import java.util.List;

public class Content {
    private final String body;
    private final String subject;
    private final List<String> attachments;

    public  Content(String body, String subject, List<String> attachments){
        this.body=body;
        this.subject=subject;
        this.attachments= List.copyOf(attachments);
    }

    public String getBody(){
       return body;
    }

    public String getSubject(){
        return subject;
    }

    public  List<String> getAttachments(){
        return  attachments;
    }
}
