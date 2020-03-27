package telegramBot.beans;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class Beans {

    @Bean
    @Qualifier("builder")
    public WebClient.Builder getWebclientBuilder() {
        return WebClient.builder();
    }

}
