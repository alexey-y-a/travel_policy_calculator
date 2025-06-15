package org.travel.insurance.core;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.travel.insurance.rest.TravelCalculatePremiumRequest;
import org.travel.insurance.rest.TravelCalculatePremiumResponse;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;

@Component
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
class TravelCalculatePremiumServiceImpl implements TravelCalculatePremiumService {

    private final DateTimeService dateTimeService;

    @Override
    public TravelCalculatePremiumResponse calculatePremium(TravelCalculatePremiumRequest travelCalculatePremiumRequest) {
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
