package com.nsacademy.chatserver;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsNot.not;

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
}
