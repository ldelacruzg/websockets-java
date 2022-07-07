
package ws;

import com.google.gson.Gson;
import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;
import models.Message;

public class MessageDecoder implements Decoder.Text<Message> {
    Gson gson = new Gson();
    
    @Override
    public Message decode(String s) throws DecodeException {
        System.out.println("decoder=== " + s);
        return gson.fromJson(s, Message.class);
    }

    @Override
    public boolean willDecode(String s) { return s != null; }

    @Override
    public void init(EndpointConfig config) {}

    @Override
    public void destroy() {}
    
}