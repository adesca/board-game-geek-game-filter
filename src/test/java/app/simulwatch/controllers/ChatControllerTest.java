package app.simulwatch.controllers;

import app.configuration.WebSocketConfig;
import app.simulwatch.models.SocketMessage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Java6Assertions.assertThat;


@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {
        WebSocketConfig.class
})
public class ChatControllerTest {
    ChatController subject = new ChatController();

    @Test
    public void send_serializesDateAsTimestamp() {

    }

//    @Test
//    public void send_generatesRandomUserName_whenItReceivesNoUsername() throws Exception {
//        MockHttpServletRequest dummyRequest = new MockHttpServletRequest();
//        SocketMessage incomingMessage = new SocketMessage();
//        SocketMessage outgoingMessage = subject.send(incomingMessage, dummyRequest);
//
//        assertThat(outgoingMessage.getName()).isNotBlank();
//
//        incomingMessage = SocketMessage.builder().name("").build();
//        outgoingMessage = subject.send(incomingMessage, dummyRequest);
//
//        assertThat(outgoingMessage.getName()).isNotBlank();
//
//        incomingMessage = SocketMessage.builder().name(" ").build();
//        outgoingMessage = subject.send(incomingMessage, dummyRequest);
//
//        assertThat(outgoingMessage.getName()).isNotBlank();
//    }
//
//    @Test
//    public void send_generatesRandomRoom_whenItReceivesNoRoom() throws Exception {
//        MockHttpServletRequest dummyRequest = new MockHttpServletRequest();
//        SocketMessage incomingMessage = new SocketMessage();
//        SocketMessage outgoingMessage = subject.send(incomingMessage, dummyRequest);
//
//        assertThat(outgoingMessage.getRoom()).isNotBlank();
//
//        incomingMessage = SocketMessage.builder().name("").build();
//        outgoingMessage = subject.send(incomingMessage, dummyRequest);
//
//        assertThat(outgoingMessage.getRoom()).isNotBlank();
//
//        incomingMessage = SocketMessage.builder().name(" ").build();
//        outgoingMessage = subject.send(incomingMessage, dummyRequest);
//
//        assertThat(outgoingMessage.getRoom()).isNotBlank();
//    }
}