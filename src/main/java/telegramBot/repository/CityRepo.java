package telegramBot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import telegramBot.entity.City;

public interface CityRepo extends JpaRepository<City, Long> {
    City findByNameIgnoreCase(String name);
}
