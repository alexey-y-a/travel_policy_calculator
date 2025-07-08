package org.travel.insurance.core;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.travel.insurance.dto.ValidationError;

import java.util.Date;
import java.util.Optional;

@Component
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class AgreementDateToInFutureValidation {

    private final DateTimeService dateTimeService;

    public Optional<ValidationError> validateDateToInFuture(TravelCalculatePremiumRequest request) {
        Date dateTo = request.getAgreementDateTo();
        Date currentDateTime = dateTimeService.getCurrentDateTime();
        return (dateTo != null && dateTo.before(currentDateTime))
                ? Optional.of(new ValidationError("agreementDateTo", "Must be in the future!"))
                : Optional.empty();
    }

}
