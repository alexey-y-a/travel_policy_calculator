package org.travel.insurance.core.validations;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.travel.insurance.core.util.ErrorCodeUtil;
import org.travel.insurance.dto.ValidationError;

@Component
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
class ValidationErrorFactory {

    private final ErrorCodeUtil errorCodeUtil;

    ValidationError buildError(String errorCode) {
        String errorDescription = errorCodeUtil.getErrorDescription(errorCode);
        return new ValidationError(errorCode, errorDescription);
    }
}
