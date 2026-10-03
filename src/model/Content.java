package model;

import java.util.ArrayList;

public class Content {
    private final String body;
    private final String subject;
    private final ArrayList<String> attachments;

    public  Content(String body, String subject, ArrayList<String> attachments){
        this.body=body;
        this.subject=subject;
        this.attachments=attachments;
    }

    public String getBody(){
       return body;
    }

    public String getSubject(){
        return subject;
    }

    public  ArrayList<String> getAttachments(){
        return  attachments;
    }
}
