package ua.masaltsev.chatsb.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import ua.masaltsev.chatsb.model.Greeting;
import ua.masaltsev.chatsb.model.HelloMessage;
import ua.masaltsev.chatsb.model.Message;
import ua.masaltsev.chatsb.util.MessageProcessor;

@Controller
public class ChatController {
    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public Greeting greeting(HelloMessage message) {
        return MessageProcessor.processHelloMessage(message);
    }

    @MessageMapping("/send")
    @SendTo("/topic/greetings")
    public Message send(Message message) {
        return MessageProcessor.processMessage(message);
    }
}
