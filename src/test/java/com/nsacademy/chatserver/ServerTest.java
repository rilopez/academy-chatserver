package com.nsacademy.chatserver;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class ServerTest {
    @Test
    public void registerUser() {
        Server server= new Server();

        User alice = new User();
        User bob = new User();
        server.registerUser(alice);

        assertThat(server.getRegisteredUsers(), hasItem(alice));
        assertThat(server.getRegisteredUsers(), not(hasItem(bob)));
    }

    @Test
    public void onlyRegisteredUsersCanLogin() {
        Server server= new Server();
        server.registerUserWith("alice@wonderland.com");

        User alice = server.login("alice@wonderland.com");
        assertEquals(UserStatus.ONLINE,alice.getStatus());
        assertThat(server.getOnlineUsers(),hasItem(alice));

        try {
            server.login("bob@wonderland.com");
        } catch (Exception e) {
            assertThat(e.getMessage(),containsString("User not registered"));
        }
    }

    @Test
    public void onlyOnlineUsersCanLogout() {

        Server server= new Server();
        server.registerUserWith("alice@wonderland.com");


        User alice = server.login("alice@wonderland.com");
        server.logout(alice);
        assertEquals(UserStatus.OFFLINE,alice.getStatus());
        assertThat(server.getOnlineUsers(),not(hasItem(alice)));

        try {
            server.logout(alice);
        } catch (Exception e) {
            assertThat(e.getMessage(),containsString("User is not online"));
        }
    }

}
