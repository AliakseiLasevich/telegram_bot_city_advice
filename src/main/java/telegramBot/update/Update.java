package telegramBot.update;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class Update {
    private boolean ok;
    private List<Result> result;

}
