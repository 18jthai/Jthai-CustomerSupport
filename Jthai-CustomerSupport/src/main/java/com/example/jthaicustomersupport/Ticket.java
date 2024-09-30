package com.example.jthaicustomersupport;

import java.util.ArrayList;

public class Ticket {
    private String name;
    private String subject;
    private String bodyOfTicket;
    private Attachment attachments;

    public Ticket(String name, String subject, String bodyOfTicket, Attachment attachments) {
        this.name = name;
        this.subject = subject;
        this.bodyOfTicket = bodyOfTicket;
        this.attachments = attachments;
    }

    public Ticket() {
        super();
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

    public Attachment getAttachments() {
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

    public void setAttachments(Attachment attachments) {
        this.attachments = attachments;
    }

    public boolean hasImage() {
        return attachments.getName().length() > 0 && attachments.getContents().length > 0;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "name='" + name + '\'' +
                ", subject='" + subject + '\'' +
                ", bodyOfTicket='" + bodyOfTicket + '\'' +
                ", attachments=" + attachments +
                '}';
    }
}
