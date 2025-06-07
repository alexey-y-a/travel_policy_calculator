package org.travel.insurance.core;

import org.junit.jupiter.api.Test;
import org.travel.insurance.rest.TravelCalculatePremiumRequest;
import org.travel.insurance.rest.TravelCalculatePremiumResponse;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TravelCalculatePremiumServiceImplTest {

    private TravelCalculatePremiumServiceImpl travelCalculatePremiumService = new TravelCalculatePremiumServiceImpl();

    @Test
    public void shouldPopulateFirstName() {
        TravelCalculatePremiumRequest travelCalculatePremiumRequest = new TravelCalculatePremiumRequest();
        travelCalculatePremiumRequest.setPersonFirstName("Ivan");
        TravelCalculatePremiumResponse travelCalculatePremiumResponse = travelCalculatePremiumService.calculatePremium(travelCalculatePremiumRequest);
        assertEquals(travelCalculatePremiumResponse.getPersonFirstName(), travelCalculatePremiumRequest.getPersonFirstName());
    }

    @Test
    public void shouldPopulateLastName() {
        TravelCalculatePremiumRequest travelCalculatePremiumRequest = new TravelCalculatePremiumRequest();
        travelCalculatePremiumRequest.setPersonLastName("Ivanov");
        TravelCalculatePremiumResponse travelCalculatePremiumResponse = travelCalculatePremiumService.calculatePremium(travelCalculatePremiumRequest);
        assertEquals(travelCalculatePremiumResponse.getPersonLastName(), travelCalculatePremiumRequest.getPersonLastName());
    }

    @Test
    public void shouldPopulateAgreementDateFrom() {
        TravelCalculatePremiumRequest travelCalculatePremiumRequest = new TravelCalculatePremiumRequest();
        travelCalculatePremiumRequest.setAgreementDateFrom(new Date());
        TravelCalculatePremiumResponse travelCalculatePremiumResponse = travelCalculatePremiumService.calculatePremium(travelCalculatePremiumRequest);
        assertEquals(travelCalculatePremiumResponse.getAgreementDateFrom(), travelCalculatePremiumRequest.getAgreementDateFrom());
    }

    @Test
    public void shouldPopulateAgreementDateTo() {
        TravelCalculatePremiumRequest travelCalculatePremiumRequest = new TravelCalculatePremiumRequest();
        travelCalculatePremiumRequest.setAgreementDateTo(new Date());
        TravelCalculatePremiumResponse travelCalculatePremiumResponse = travelCalculatePremiumService.calculatePremium(travelCalculatePremiumRequest);
        assertEquals(travelCalculatePremiumResponse.getAgreementDateTo(), travelCalculatePremiumRequest.getAgreementDateTo());
    }

}