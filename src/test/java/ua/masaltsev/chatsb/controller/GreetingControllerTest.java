package ua.masaltsev.chatsb.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ua.masaltsev.chatsb.model.HelloMessage;
import ua.masaltsev.chatsb.model.Message;

import static org.junit.jupiter.api.Assertions.*;

class GreetingControllerTest {
    private GreetingController controller;

    @BeforeEach
    void setUp() {
        controller = new GreetingController();
    }

    @Test
    void greeting_messageWithNamePassedGreetingReturns_ok() {
        HelloMessage message = new HelloMessage();
        message.setName("Stepan");
        String expected = message.getName() + " is online now";
        String actual = controller.greeting(message).getContent();
        assertEquals(expected, actual);
    }

    @Test
    void greeting_messageWithOutNamePassedGreetingReturns_ok() {
        HelloMessage message = new HelloMessage();
        message.setName("");
        String expected = "anonymous is online now";
        String actual = controller.greeting(message).getContent();
        assertEquals(expected, actual);
    }

    @Test
    void send_messageWithAllAttributes_ok() {
        Message message = new Message();
        message.setSender("Stepan");
        message.setContent("How are you?");
        String expected = message.getSender() + ": " + message.getContent();
        String actual = controller.send(message).getContent();
        assertEquals(expected, actual);
    }

    @Test
    void send_messageWithoutSender_ok() {
        Message message = new Message();
        message.setSender(null);
        message.setContent("How are you?");
        String expected = "anonymous: " + message.getContent();
        String actual = controller.send(message).getContent();
        assertEquals(expected, actual);
    }

    @Test
    void send_messageWithoutContent_notOk() {
        Message message = new Message();
        message.setContent("");
        assertThrows(RuntimeException.class, () -> controller.send(message),
                "There is no content in the message: " + message);
    }
}
