package com.example.mesajlasmabotu;

public class Message {
    public static final int USER_TYPE = 1;
    public static final int BOT_TYPE = 2;

    private String message;
    private int type;

    public Message(String message, int type) {
        this.message = message;
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public int getType() {
        return type;
    }
}
