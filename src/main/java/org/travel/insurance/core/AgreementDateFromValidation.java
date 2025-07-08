package org.travel.insurance.core;

import org.springframework.stereotype.Component;
import org.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.travel.insurance.dto.ValidationError;

import java.util.Optional;

@Component
public class AgreementDateFromValidation {

    public Optional<ValidationError> validateAgreementDateFrom(TravelCalculatePremiumRequest request) {
        return (request.getAgreementDateFrom() == null)
                ? Optional.of(new ValidationError("agreementDateFrom", "Must not be empty!"))
                : Optional.empty();
    }

}
