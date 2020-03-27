package telegramBot.update;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Chat {
    private String id;
    private String first_name;
    private String last_name;
    private String username;
}