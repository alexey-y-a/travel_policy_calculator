package org.travel.insurance.core;

import org.travel.insurance.rest.TravelCalculatePremiumRequest;
import org.travel.insurance.rest.TravelCalculatePremiumResponse;
import org.springframework.stereotype.Component;

@Component
class TravelCalculatePremiumServiceImpl implements TravelCalculatePremiumService {

    @Override
    public TravelCalculatePremiumResponse calculatePremium(TravelCalculatePremiumRequest request) {
        TravelCalculatePremiumResponse travelCalculatePremiumResponse = new TravelCalculatePremiumResponse();
        travelCalculatePremiumResponse.setPersonFirstName(request.getPersonFirstName());
        travelCalculatePremiumResponse.setPersonLastName(request.getPersonLastName());
        travelCalculatePremiumResponse.setAgreementDateFrom(request.getAgreementDateFrom());
        travelCalculatePremiumResponse.setAgreementDateTo(request.getAgreementDateTo());
        return travelCalculatePremiumResponse;
    }

}
