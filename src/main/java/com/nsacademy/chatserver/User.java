package com.nsacademy.chatserver;

public class User {

    private UserStatus _status;

    public User(String email) {
        _email = email;
    }

    private String _email;


    public User() {

    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        User user = (User) o;

        if (!_email.equals(user._email)) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        return _email != null ? _email.hashCode() : 0;
    }
}
