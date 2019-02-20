package app.simulwatch.controllers;

import app.simulwatch.models.ChatMessage;
import app.simulwatch.models.SocketMessage;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;

import static org.springframework.util.StringUtils.isEmpty;

@Controller
public class ChatController {

    @MessageMapping("/register")
    @SendToUser("/queue/me")
    public SocketMessage send(SocketMessage message, SimpMessageHeaderAccessor headerAccessor) throws Exception {
        SocketMessage response = new SocketMessage();
        String sessionId = headerAccessor.getSessionId();
        if (isEmpty(message.getName())) {
            response.setName(sessionId);
        }

        if (isEmpty(message.getRoom())) {
            response.setRoom(sessionId);
        }

        return response;
    }

    @MessageMapping("/room/{room}/messages")
    public ChatMessage send(ChatMessage message) throws Exception {
        System.out.println("***************** Received a chat message " + message);
        return message;
    }
}
