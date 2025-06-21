package org.travel.insurance.core;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.travel.insurance.dto.TravelCalculatePremiumResponse;
import org.springframework.stereotype.Component;
import org.travel.insurance.dto.ValidationError;
import java.math.BigDecimal;
import java.util.List;

@Component
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
class TravelCalculatePremiumServiceImpl implements TravelCalculatePremiumService {

    private final TravelCalculatePremiumRequestValidator requestValidator;
    private final DateTimeService dateTimeService;

    @Override
    public TravelCalculatePremiumResponse calculatePremium(TravelCalculatePremiumRequest travelCalculatePremiumRequest) {
        List<ValidationError> errors = requestValidator.validate(travelCalculatePremiumRequest);
        return errors.isEmpty()
                ? buildResponse(travelCalculatePremiumRequest)
                : buildResponse(errors);
    }

    private TravelCalculatePremiumResponse buildResponse(List<ValidationError> errors) {
        return new TravelCalculatePremiumResponse(errors);
    }

    private TravelCalculatePremiumResponse buildResponse(TravelCalculatePremiumRequest travelCalculatePremiumRequest) {
        TravelCalculatePremiumResponse travelCalculatePremiumResponse = new TravelCalculatePremiumResponse();
        travelCalculatePremiumResponse.setPersonFirstName(travelCalculatePremiumRequest.getPersonFirstName());
        travelCalculatePremiumResponse.setPersonLastName(travelCalculatePremiumRequest.getPersonLastName());
        travelCalculatePremiumResponse.setAgreementDateFrom(travelCalculatePremiumRequest.getAgreementDateFrom());
        travelCalculatePremiumResponse.setAgreementDateTo(travelCalculatePremiumRequest.getAgreementDateTo());

        var daysBetween = dateTimeService.getDaysBetween(travelCalculatePremiumRequest.getAgreementDateFrom(), travelCalculatePremiumRequest.getAgreementDateTo());
        travelCalculatePremiumResponse.setAgreementPrice(new BigDecimal(daysBetween));

        return travelCalculatePremiumResponse;
    }

}
