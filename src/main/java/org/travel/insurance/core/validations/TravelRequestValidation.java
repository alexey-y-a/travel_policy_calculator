package org.travel.insurance.core.validations;

import org.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.travel.insurance.dto.ValidationError;

import java.util.Optional;

interface TravelRequestValidation {

    Optional<ValidationError> execute(TravelCalculatePremiumRequest request);
}
