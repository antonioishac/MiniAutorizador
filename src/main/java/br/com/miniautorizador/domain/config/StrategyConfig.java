package br.com.miniautorizador.domain.config;

import br.com.miniautorizador.domain.services.IValidationStrategy;
import br.com.miniautorizador.domain.services.enums.ValidationType;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

@Configuration
@AllArgsConstructor
public class StrategyConfig {

    private final List<IValidationStrategy> validationStrategies;

    @Bean
    public Map<ValidationType, IValidationStrategy> validation() {
        Map<ValidationType, IValidationStrategy> validationByType = new EnumMap<>(ValidationType.class);
        validationStrategies.forEach(validationStrategy -> validationByType.put(validationStrategy.validationType(), validationStrategy));
        return validationByType;
    }
}
