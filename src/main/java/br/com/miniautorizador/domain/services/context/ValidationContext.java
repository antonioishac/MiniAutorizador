package br.com.miniautorizador.domain.services.context;

import br.com.miniautorizador.api.request.TransactionRequest;
import br.com.miniautorizador.domain.services.IValidationStrategy;
import br.com.miniautorizador.domain.services.enums.ValidationType;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Map;

@AllArgsConstructor
@Component
public class ValidationContext {

    private final Map<ValidationType, IValidationStrategy> validationByType;

    public void validation(TransactionRequest request, ValidationType validationType) {
        IValidationStrategy validationStrategy = validationByType.getOrDefault(validationType, null);
        validationStrategy.validationCard(request);
    }
}
