package com.application.neto.meuspedidosvagas.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Email {
    private String key;
    private Message message;
    private boolean async;

    public Email(Message message, boolean async) {
        this.message = message;
        this.async = async;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public boolean isAsync() {
        return async;
    }

    public void setAsync(boolean async) {
        this.async = async;
    }

    @Override
    public String toString() {
        final Gson gson = new GsonBuilder().create();
        return gson.toJson(this);
    }
}
