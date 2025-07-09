package org.travel.insurance.core.validations;

import org.springframework.stereotype.Component;
import org.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.travel.insurance.dto.ValidationError;

import java.util.Optional;

@Component
public class AgreementDateToValidation {

    public Optional<ValidationError> validateAgreementDateTo(TravelCalculatePremiumRequest request) {
        return (request.getAgreementDateTo() == null)
                ? Optional.of(new ValidationError("agreementDateTo", "Must not be empty!"))
                : Optional.empty();
    }

}
