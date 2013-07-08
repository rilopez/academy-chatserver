package com.nsacademy.chatserver;

public class User {

    private UserStatus _status;

    public User() {

    }


    private String _email;

    public void setEmail(String email) {
        _email = email;
    }

    public String getEmail() {
        return _email;
    }

    public UserStatus getStatus() {
        return _status;
    }

    public void setStatus(UserStatus status) {
        _status = status;
    }
}
