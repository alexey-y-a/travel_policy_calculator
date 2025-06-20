package org.travel.insurance.core;

import org.springframework.stereotype.Component;
import org.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.travel.insurance.dto.ValidationError;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class TravelCalculatePremiumRequestValidator {

    public List<ValidationError> validate(TravelCalculatePremiumRequest travelCalculatePremiumRequest) {
        List<ValidationError> errors = new ArrayList<>();
        validatePersonFirstName(travelCalculatePremiumRequest).ifPresent(errors::add);
        validatePersonLastName(travelCalculatePremiumRequest).ifPresent(errors::add);
        validateAgreementDateFrom(travelCalculatePremiumRequest).ifPresent(errors::add);
        return errors;
    }

    private Optional<ValidationError> validatePersonFirstName(TravelCalculatePremiumRequest travelCalculatePremiumRequest) {
        return (travelCalculatePremiumRequest.getPersonFirstName() == null || travelCalculatePremiumRequest.getPersonFirstName().isEmpty())
                ? Optional.of(new ValidationError("personFirstName", "Must not be empty!"))
                : Optional.empty();
    }

    private Optional<ValidationError> validatePersonLastName(TravelCalculatePremiumRequest travelCalculatePremiumRequest) {
        return (travelCalculatePremiumRequest.getPersonLastName() == null || travelCalculatePremiumRequest.getPersonLastName().isEmpty())
                ? Optional.of(new ValidationError("personLastName", "Must not be empty!"))
                : Optional.empty();
    }

    private Optional<ValidationError> validateAgreementDateFrom(TravelCalculatePremiumRequest travelCalculatePremiumRequest) {
        return (travelCalculatePremiumRequest.getAgreementDateFrom() == null)
                ? Optional.of(new ValidationError("agreementDateFrom", "Must not be empty!"))
                : Optional.empty();
    }

}
