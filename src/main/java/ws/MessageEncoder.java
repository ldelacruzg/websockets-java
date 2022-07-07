
package ws;

import com.google.gson.Gson;
import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;
import models.Message;

public class MessageEncoder implements Encoder.Text<Message> {
    Gson gson = new Gson();

    @Override
    public String encode(Message message) throws EncodeException {
        System.out.println("encode=== " + message.toString());
        return gson.toJson(message);
    }

    @Override
    public void init(EndpointConfig config) {}

    @Override
    public void destroy() {}
    
}
