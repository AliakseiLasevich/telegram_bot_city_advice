package telegramBot;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import telegramBot.bot.BotService;

@Slf4j
@ComponentScan(basePackages = "telegramBot")
@EnableAutoConfiguration
public class Runner {

    public static void main(String[] args) throws InterruptedException {
        ApplicationContext context = new AnnotationConfigApplicationContext(Runner.class);
        BotService botService = context.getBean(BotService.class);
        botService.start();
    }

}

