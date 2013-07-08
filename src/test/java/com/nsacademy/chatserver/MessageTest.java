package com.nsacademy.chatserver;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MessageTest {
    @Test
    public void hasRequiredAttributes() {
        Message message= new Message();
        User alice = new User();
        User bob= new User();
        message.setFrom(alice);
        message.setTo(bob);
        message.setContent("Hola Bob");
        assertEquals(alice,message.getFrom());
        assertEquals(bob,message.getTo());
        assertEquals("Hola Bob",message.getContent());

    }

}
