package com.example.jthaicustomersupport;

import java.util.ArrayList;

public class Ticket {
    private String name;
    private String subject;
    private String bodyOfTicket;
    private ArrayList attachments;

    public Ticket(String name, String subject, String bodyOfTicket, ArrayList attachments) {
        this.name = name;
        this.subject = subject;
        this.bodyOfTicket = bodyOfTicket;
        this.attachments = attachments;
    }

    public Ticket() {
    }

    public String getName() {
        return name;
    }

    public String getSubject() {
        return subject;
    }

    public String getBodyOfTicket() {
        return bodyOfTicket;
    }

    public ArrayList getAttachments() {
        return attachments;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setBodyOfTicket(String bodyOfTicket) {
        this.bodyOfTicket = bodyOfTicket;
    }

    public void setAttachments(ArrayList attachments) {
        this.attachments = attachments;
    }
}
