package org.travel.insurance.core.validations;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.travel.insurance.core.ErrorCodeUtil;
import org.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.travel.insurance.dto.ValidationError;

import java.util.Optional;

@Component
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class PersonFirstNameValidation implements TravelRequestValidation {

    private final ErrorCodeUtil errorCodeUtil;

    @Override
    public Optional<ValidationError> execute(TravelCalculatePremiumRequest request) {
        return (request.getPersonFirstName() == null || request.getPersonFirstName().isEmpty())
                ? Optional.of(buildError("ERROR_CODE_7"))
                : Optional.empty();
    }

    private ValidationError buildError(String errorCode) {
        String errorDescription = errorCodeUtil.getErrorDescription(errorCode);
        return new ValidationError(errorCode, errorDescription);
    }

}
