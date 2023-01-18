package ua.masaltsev.chatsb.util;

import org.springframework.web.util.HtmlUtils;
import ua.masaltsev.chatsb.model.Greeting;
import ua.masaltsev.chatsb.model.HelloMessage;
import ua.masaltsev.chatsb.model.Message;

public class MessageProcessor {

    private static final String ANONYMOUS = "anonymous";

    public static Greeting processHelloMessage(HelloMessage message) {
        if (message.getName().isBlank()) {
            message.setName(ANONYMOUS);
        }
        return new Greeting(HtmlUtils.htmlEscape(message.getName()) + " is online now");
    }

    public static Message processMessage(Message message) {
        if (message.getContent().isBlank()) {
            throw new RuntimeException("There is no content in the message: " + message);
        }
        if (message.getSender() == null) {
            message.setContent(ANONYMOUS + ": " + message.getContent());
        } else {
            message.setContent(message.getSender() + ": " + message.getContent());
        }
        return message;
    }
}
