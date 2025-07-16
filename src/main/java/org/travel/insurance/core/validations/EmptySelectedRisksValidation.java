package org.travel.insurance.core.validations;

import org.springframework.stereotype.Component;
import org.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.travel.insurance.dto.ValidationError;

import java.util.Optional;

@Component
public class EmptySelectedRisksValidation implements TravelRequestValidation {
    @Override
    public Optional<ValidationError> execute(TravelCalculatePremiumRequest request) {
        return (request.getSelectedRisks() == null || request.getSelectedRisks().isEmpty())
                ? Optional.of(new ValidationError("selectedRisks", "Must not be empty!"))
                : Optional.empty();
    }

}
