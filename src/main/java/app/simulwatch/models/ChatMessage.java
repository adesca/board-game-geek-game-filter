package app.simulwatch.models;

import lombok.Data;

@Data
public class ChatMessage extends SocketMessage {
    String content;
}
