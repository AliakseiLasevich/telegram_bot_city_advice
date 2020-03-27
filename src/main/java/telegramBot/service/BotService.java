package telegramBot.service;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import telegramBot.update.Message;
import telegramBot.update.Result;
import telegramBot.update.Update;

@Slf4j
@Service
@PropertySource("classpath:application.properties")
public class BotService {

    @Autowired
    WebClient.Builder builder;

    @Autowired
    CityService cityService;

    @Value("${bot.token}")
    private String token;

    private Integer offset=1;

    public void start() throws InterruptedException {
        while (true) {
            if (!newUpdate()) {
                Thread.sleep(3000);
            } else {
                processUpdate();
            }
        }
    }

    private boolean newUpdate() {
        if (getUpdate().getResult().size() > 0) {
            log.info("New updates!");
            return true;
        }
        log.info("No new updates");
        return false;
    }

    private void processUpdate() {
        Update update = getUpdate();
        log.info("New update. " + update.getResult().size() + " new results.");
        update.getResult().forEach(this::processResult);

        int lastResultNumber = update.getResult().size() - 1;
        increaseOffset(update.getResult().get(lastResultNumber));
    }

    private void processResult(Result result) {
        String chatId = result.getMessage().getChat().getId();
        String advice = cityService.getAdviceByCityName(result.getMessage().getText());
        sendMessage(new Message(chatId, advice));
    }


    public Update getUpdate() {
        return builder
                .baseUrl("https://api.telegram.org/bot" + token)
                .build()
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path("/getUpdates")
                        .queryParam("offset", offset)
                        .queryParam("timeout", 1)
                        .build())
                .retrieve()
                .bodyToMono(Update.class)
                .block();
    }

    public void sendMessage(Message message) {
        builder
                .baseUrl("https://api.telegram.org/bot" + token)
                .build()
                .post()
                .uri("/sendMessage")
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(message))
                .retrieve()
                .bodyToMono(Message.class)
                .block();
    }

    public void increaseOffset(Result lastResultFromUpdate) {
        offset = lastResultFromUpdate.getUpdate_id() + 1;
    }
}