package telegramBot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import telegramBot.entity.City;
import telegramBot.repository.CityRepo;

@Service
public class CityServiceImpl implements CityService {

    @Autowired
    CityRepo cityRepository;

    @Override
    public String getAdviceByCityName(String cityName) {
        City city = cityRepository.findByNameIgnoreCase(cityName);
        if ((city != null) && (city.getAdvice().getAdvice() != null)) {
            return city.getAdvice().getAdvice();
        }
        return "No city " + cityName + " found. Try again.";
    }
}
