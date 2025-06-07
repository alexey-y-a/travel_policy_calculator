package org.travel.insurance.core;

import org.travel.insurance.rest.TravelCalculatePremiumRequest;
import org.travel.insurance.rest.TravelCalculatePremiumResponse;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

@Component
class TravelCalculatePremiumServiceImpl implements TravelCalculatePremiumService {

    @Override
    public TravelCalculatePremiumResponse calculatePremium(TravelCalculatePremiumRequest travelCalculatePremiumRequest) {
        TravelCalculatePremiumResponse travelCalculatePremiumResponse = new TravelCalculatePremiumResponse();
        travelCalculatePremiumResponse.setPersonFirstName(travelCalculatePremiumRequest.getPersonFirstName());
        travelCalculatePremiumResponse.setPersonLastName(travelCalculatePremiumRequest.getPersonLastName());
        travelCalculatePremiumResponse.setAgreementDateFrom(travelCalculatePremiumRequest.getAgreementDateFrom());
        travelCalculatePremiumResponse.setAgreementDateTo(travelCalculatePremiumRequest.getAgreementDateTo());
        long diff = travelCalculatePremiumRequest.getAgreementDateFrom().getTime() - travelCalculatePremiumRequest.getAgreementDateTo().getTime();
        var daysBetween = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
        travelCalculatePremiumResponse.setAgreementPrice(new BigDecimal(daysBetween));

        return travelCalculatePremiumResponse;
    }

}
