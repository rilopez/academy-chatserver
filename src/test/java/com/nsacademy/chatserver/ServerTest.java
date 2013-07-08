package com.nsacademy.chatserver;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertEquals;

public class ServerTest {
    @Test
    public void registerUser() {
        Server server = new Server();
        User alice = new User("alice@wonderland.com");
        User bob = new User("bob@wonderland.com");
        server.registerUser(alice);

        assertThat(server.getRegisteredUsers(), hasItem(alice));
        assertThat(server.getRegisteredUsers(), not(hasItem(bob)));
    }

    @Test
    public void onlyRegisteredUsersCanLogin() {
        Server server = new Server();
        server.registerUserWith("alice@wonderland.com");

        User alice = server.login("alice@wonderland.com");
        assertEquals(UserStatus.ONLINE, alice.getStatus());
        assertThat(server.getOnlineUsers(), hasItem(alice));

        try {
            server.login("bob@wonderland.com");
        } catch (Exception e) {
            assertThat(e.getMessage(), containsString("User not registered"));
        }
    }

    @Test
    public void onlyOnlineUsersCanLogout() {
        Server server = new Server();
        server.registerUserWith("alice@wonderland.com");
        User alice = server.login("alice@wonderland.com");
        server.logout(alice);
        assertEquals(UserStatus.OFFLINE, alice.getStatus());
        assertThat(server.getOnlineUsers(), not(hasItem(alice)));

        try {
            server.logout(alice);
        } catch (Exception e) {
            assertThat(e.getMessage(), containsString("User is not online"));
        }
    }

    @Test
    public void userCanAddAndRemoveContacts() {
        Server server = createServerWithUsers("alice@wonderland.com", "bob@wonderland.com");
        User alice = server.login("alice@wonderland.com");
        User bob = server.login("bob@wonderland.com");

        server.linkContacts(alice, bob);

        assertThat(alice.getContacts(), hasItem(bob));
        assertThat(bob.getContacts(), hasItem(alice));

        server.unlinkContacts(alice, bob);

        assertThat(alice.getContacts(), not(hasItem(bob)));
        assertThat(bob.getContacts(), not(hasItem(alice)));
    }

    @Test
    public void userOnlyCanMessageWithHerContacts() {
        Server server = createServerWithUsers("alice@wonderland.com", "bob@wonderland.com");
        User alice = server.login("alice@wonderland.com");
        User bob = server.login("bob@wonderland.com");
        try {
            server.createMessage(alice, bob, "Hola bob");
        } catch (Exception e) {
            assertThat(e.getMessage(), containsString("is not a contact"));
        }

        server.linkContacts(alice, bob);
        Message message = server.createMessage(alice, bob, "Hola bob");
        assertEquals(alice, message.getFrom());
        assertEquals(bob, message.getTo());
        assertEquals("Hola bob", message.getContent());
    }

    private Server createServerWithUsers(String... emails) {
        Server server = new Server();
        for (String email : emails) {
            server.registerUserWith(email);
        }
        return server;
    }
}
