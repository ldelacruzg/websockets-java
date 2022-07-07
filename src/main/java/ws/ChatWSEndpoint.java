
package ws;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.websocket.EncodeException;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import models.Message;

@ServerEndpoint(
        value = "/chat", 
        decoders = MessageDecoder.class, 
        encoders = MessageEncoder.class
)
public class ChatWSEndpoint {
    private static Set<Session> sessions = new HashSet<>();
    
    @OnMessage
    public String onMessage(Session session, Message message) {
        System.out.println("Handling message: " + message.toString());
        for (Session s: sessions) {
            try {
                s.getBasicRemote().sendObject(message);
            } catch (IOException ex) {
                Logger.getLogger(ChatWSEndpoint.class.getName()).log(Level.SEVERE, null, ex);
            } catch (EncodeException ex) {
                Logger.getLogger(ChatWSEndpoint.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }
    
    @OnOpen
    public void onOpen(Session session) {
        System.out.println("On open: " + session.getId());
        sessions.add(session);
    }
    
    @OnClose
    public void onClose(Session session) {
        System.out.println("On close: " + session.getId());
        sessions.remove(session);
    }
}
