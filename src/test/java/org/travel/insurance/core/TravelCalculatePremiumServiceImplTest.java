package org.travel.insurance.core;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.travel.insurance.rest.TravelCalculatePremiumRequest;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class TravelCalculatePremiumServiceImplTest {

    private DateTimeService dateTimeService;
    private TravelCalculatePremiumServiceImpl travelCalculatePremiumService;

    @BeforeEach
    public void setUp() {
        dateTimeService = new DateTimeService();
        travelCalculatePremiumService = new TravelCalculatePremiumServiceImpl(dateTimeService);
    }

    @Test
    public void shouldPopulateFirstName() {
        var travelCalculatePremiumRequest = createRequestWithAllFields();
        var travelCalculatePremiumResponse = travelCalculatePremiumService.calculatePremium(travelCalculatePremiumRequest);
        assertEquals(travelCalculatePremiumResponse.getPersonFirstName(), travelCalculatePremiumRequest.getPersonFirstName());
    }

    @Test
    public void shouldPopulateLastName() {
        var travelCalculatePremiumRequest = createRequestWithAllFields();
        var travelCalculatePremiumResponse = travelCalculatePremiumService.calculatePremium(travelCalculatePremiumRequest);
        assertEquals(travelCalculatePremiumResponse.getPersonLastName(), travelCalculatePremiumRequest.getPersonLastName());
    }

    @Test
    public void shouldPopulateAgreementDateFrom() {
        var travelCalculatePremiumRequest = createRequestWithAllFields();
        var travelCalculatePremiumResponse = travelCalculatePremiumService.calculatePremium(travelCalculatePremiumRequest);
        assertEquals(travelCalculatePremiumResponse.getAgreementDateFrom(), travelCalculatePremiumRequest.getAgreementDateFrom());
    }

    @Test
    public void shouldPopulateAgreementDateTo() {
        var travelCalculatePremiumRequest = createRequestWithAllFields();
        var travelCalculatePremiumResponse = travelCalculatePremiumService.calculatePremium(travelCalculatePremiumRequest);
        assertEquals(travelCalculatePremiumResponse.getAgreementDateTo(), travelCalculatePremiumRequest.getAgreementDateTo());
    }

    @Test
    public void shouldPopulateAgreementPrice() {
        var travelCalculatePremiumRequest = createRequestWithAllFields();
        var travelCalculatePremiumResponse = travelCalculatePremiumService.calculatePremium(travelCalculatePremiumRequest);
        assertNotNull(travelCalculatePremiumResponse.getAgreementPrice());
    }

    private TravelCalculatePremiumRequest createRequestWithAllFields() {
        var travelCalculatePremiumRequest = new TravelCalculatePremiumRequest();
        travelCalculatePremiumRequest.setPersonFirstName("Ivan");
        travelCalculatePremiumRequest.setPersonLastName("Ivanov");
        travelCalculatePremiumRequest.setAgreementDateFrom(new Date());
        travelCalculatePremiumRequest.setAgreementDateTo(new Date());
        return travelCalculatePremiumRequest;
    }

}