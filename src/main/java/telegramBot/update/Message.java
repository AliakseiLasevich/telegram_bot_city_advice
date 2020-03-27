package telegramBot.update;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Message {
    private float message_id;
    private Chat Chat;
    private String text;
    private String chat_id;


    public Message(String chat_id, String text) {
        this.chat_id = chat_id;
        this.text = text;
    }
}