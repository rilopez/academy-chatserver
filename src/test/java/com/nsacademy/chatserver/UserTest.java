package com.nsacademy.chatserver;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UserTest {
    @Test
    public void hasRequiredAttributes() {

        User user= new User();
        user.setEmail("alice@wonderland.com");
        assertEquals("alice@wonderland.com",user.getEmail());
    }

}
