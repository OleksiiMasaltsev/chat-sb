package ua.masaltsev.chatsb.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;
import ua.masaltsev.chatsb.model.Greeting;
import ua.masaltsev.chatsb.model.HelloMessage;
import ua.masaltsev.chatsb.model.Message;

@Controller
public class GreetingController {
    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public Greeting greeting(HelloMessage message) {
        if (message.getName().isBlank()) {
            message.setName("anonymous");
        }
        return new Greeting(HtmlUtils.htmlEscape(message.getName()) + " is online now");
    }

    @MessageMapping("/send")
    @SendTo("/topic/greetings")
    public Message send(Message message) {
        if (message.getContent().isBlank()) {
            throw new RuntimeException("There is no content in the message: " + message);
        }
        if (message.getSender() == null) {
            message.setContent("anonymous: " + message.getContent());
        } else {
            message.setContent(message.getSender() + ": " + message.getContent());
        }
        return message;
    }
}
