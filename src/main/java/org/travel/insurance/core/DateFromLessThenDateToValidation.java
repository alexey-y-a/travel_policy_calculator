package org.travel.insurance.core;

import org.springframework.stereotype.Component;
import org.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.travel.insurance.dto.ValidationError;

import java.util.Date;
import java.util.Optional;

@Component
public class DateFromLessThenDateToValidation {

    public Optional<ValidationError> validateDateFromLessThenDateTo(TravelCalculatePremiumRequest request) {
        Date dateFrom = request.getAgreementDateFrom();
        Date dateTo = request.getAgreementDateTo();
        return (dateFrom != null && dateTo != null
                && (dateFrom.equals(dateTo) || dateFrom.after(dateTo)))
                ? Optional.of(new ValidationError("agreementDateFrom", "Must be less then agreementDateTo!"))
                : Optional.empty();
    }

}
