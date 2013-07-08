package com.nsacademy.chatserver;

import java.util.ArrayList;
import java.util.List;

public class Server {
    private List<User> _registeredUsers = new ArrayList<User>();
    private List<User> _onlineUsers = new ArrayList<User>();

    public void registerUser(User user) {
        _registeredUsers.add(user);
    }

    public List<User> getRegisteredUsers() {
        return _registeredUsers;
    }

    public User login(String email) {

        User user = new User(email);
        if (!getRegisteredUsers().contains(user)) {
            throw new IllegalArgumentException("User not registered :" + email);
        }
        _onlineUsers.add(user);
        user.setStatus(UserStatus.ONLINE);
        return user;
    }

    public void registerUserWith(String email) {
        User user = new User();
        user.setEmail(email);
        registerUser(user);
    }

    public List<User> getOnlineUsers() {
        return _onlineUsers;
    }

    public void logout(User user) {
        _onlineUsers.remove(user);
        user.setStatus(UserStatus.OFFLINE);
    }
}
