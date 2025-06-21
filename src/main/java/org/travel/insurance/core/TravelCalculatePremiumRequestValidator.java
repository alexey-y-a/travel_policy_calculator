package org.travel.insurance.core;

import org.springframework.stereotype.Component;
import org.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.travel.insurance.dto.ValidationError;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Component
public class TravelCalculatePremiumRequestValidator {

    public List<ValidationError> validate(TravelCalculatePremiumRequest travelCalculatePremiumRequest) {
        List<ValidationError> errors = new ArrayList<>();
        validatePersonFirstName(travelCalculatePremiumRequest).ifPresent(errors::add);
        validatePersonLastName(travelCalculatePremiumRequest).ifPresent(errors::add);
        validateAgreementDateFrom(travelCalculatePremiumRequest).ifPresent(errors::add);
        validateAgreementDateTo(travelCalculatePremiumRequest).ifPresent(errors::add);
        validateDateFromLessThenDateTo(travelCalculatePremiumRequest).ifPresent(errors::add);
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

    private Optional<ValidationError> validateAgreementDateTo(TravelCalculatePremiumRequest travelCalculatePremiumRequest) {
        return (travelCalculatePremiumRequest.getAgreementDateTo() == null)
                ? Optional.of(new ValidationError("agreementDateTo", "Must not be empty!"))
                : Optional.empty();
    }

    private Optional<ValidationError> validateDateFromLessThenDateTo(TravelCalculatePremiumRequest travelCalculatePremiumRequest) {
        Date dateFrom = travelCalculatePremiumRequest.getAgreementDateFrom();
        Date dateTo = travelCalculatePremiumRequest.getAgreementDateTo();
        return (dateFrom != null && dateTo != null
                && (dateFrom.equals(dateTo) || dateFrom.after(dateTo)))
                ? Optional.of(new ValidationError("agreementDateFrom", "Must be less then agreementDateTo!"))
                : Optional.empty();
    }

}
