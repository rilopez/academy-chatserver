package com.nsacademy.chatserver;

import java.util.ArrayList;
import java.util.List;

public class Server {
    private List<User> _registeredUsers = new ArrayList<User>();

    public void registerUser(User user) {
        _registeredUsers.add(user);
    }

    public List<User> getRegisteredUsers() {
        return _registeredUsers;
    }
}
