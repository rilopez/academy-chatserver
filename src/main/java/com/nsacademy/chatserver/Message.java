package com.nsacademy.chatserver;

public class Message {
    private User _from;
    private User _to;
    private String _content;

    public Message() {
    }

    public Message(User from, User to, String content) {
        _from = from;
        _to = to;
        _content = content;
    }

    public void setFrom(User from) {
        _from = from;
    }

    public void setTo(User to) {
        _to = to;
    }

    public void setContent(String content) {
        _content = content;
    }

    public User getFrom() {
        return _from;
    }

    public String getContent() {
        return _content;
    }

    public User getTo() {
        return _to;
    }
}
