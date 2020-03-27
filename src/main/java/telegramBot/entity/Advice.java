package telegramBot.entity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Table(name = "advice")
public class Advice {

    @Id
    private Long id;

    @Column(name = "advice")
    private String advice;

}
