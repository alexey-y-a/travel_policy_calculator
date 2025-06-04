package org.travel.insurance.core;

import org.junit.jupiter.api.Test;
import org.travel.insurance.rest.TravelCalculatePremiumRequest;
import org.travel.insurance.rest.TravelCalculatePremiumResponse;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TravelCalculatePremiumServiceImplTest {

    private TravelCalculatePremiumServiceImpl travelCalculatePremiumService = new TravelCalculatePremiumServiceImpl();

    @Test
    public void shouldPopulateResponse() {
        TravelCalculatePremiumRequest travelCalculatePremiumRequest = new TravelCalculatePremiumRequest();
        travelCalculatePremiumRequest.setPersonFirstName("Ivan");
        travelCalculatePremiumRequest.setPersonLastName("Ivanov");
        travelCalculatePremiumRequest.setAgreementDateFrom(new Date());
        travelCalculatePremiumRequest.setAgreementDateTo(new Date());

        TravelCalculatePremiumResponse travelCalculatePremiumResponse = travelCalculatePremiumService.calculatePremium(travelCalculatePremiumRequest);

        assertEquals(travelCalculatePremiumResponse.getPersonFirstName(), travelCalculatePremiumRequest.getPersonFirstName());
        assertEquals(travelCalculatePremiumResponse.getPersonLastName(), travelCalculatePremiumRequest.getPersonLastName());
        assertEquals(travelCalculatePremiumResponse.getAgreementDateFrom(), travelCalculatePremiumRequest.getAgreementDateFrom());
        assertEquals(travelCalculatePremiumResponse.getAgreementDateTo(), travelCalculatePremiumRequest.getAgreementDateTo());

    }

}