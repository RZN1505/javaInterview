package websocket;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

@Controller
public class ContentController {
    @MessageMapping("/hello")
    @SendTo("/topic/contents")
    public Content content(Hello message) throws Exception {
        Thread.sleep(1000); // simulated delay
        return new Content("Hello, " + HtmlUtils.htmlEscape(message.getName()) + "!");
    }
}
