package com.application.neto.meuspedidosvagas.model;

import java.util.List;

public class Message {
    private String text;
    private String subject;
    private String from_email;
    private String from_name;
    private List<To> to;

    public Message(String text, String subject, String from_email, String from_name, List<To> to) {
        this.text = text;
        this.subject = subject;
        this.from_email = from_email;
        this.from_name = from_name;
        this.to = to;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getFrom_email() {
        return from_email;
    }

    public void setFrom_email(String from_email) {
        this.from_email = from_email;
    }

    public String getFrom_name() {
        return from_name;
    }

    public void setFrom_name(String from_name) {
        this.from_name = from_name;
    }

    public List<To> getTo() {
        return to;
    }

    public void setTo(List<To> to) {
        this.to = to;
    }
}
