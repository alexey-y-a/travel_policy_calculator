package org.travel.insurance.core;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.travel.insurance.rest.TravelCalculatePremiumRequest;
import java.util.Date;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TravelCalculatePremiumServiceImplTest {

    @Mock private DateTimeService dateTimeService;

    @InjectMocks
    private TravelCalculatePremiumServiceImpl travelCalculatePremiumService;
    private TravelCalculatePremiumRequest travelCalculatePremiumRequest;

    @BeforeEach
    public void setUp() {
        travelCalculatePremiumRequest = createRequestWithAllFields();
        when(dateTimeService.getDaysBetween(travelCalculatePremiumRequest.getAgreementDateFrom(), travelCalculatePremiumRequest.getAgreementDateTo())).thenReturn(0L);
    }

    @Test
    public void shouldPopulateFirstName() {
        var travelCalculatePremiumResponse = travelCalculatePremiumService.calculatePremium(travelCalculatePremiumRequest);
        assertEquals(travelCalculatePremiumResponse.getPersonFirstName(), travelCalculatePremiumRequest.getPersonFirstName());
    }

    @Test
    public void shouldPopulateLastName() {
        var travelCalculatePremiumResponse = travelCalculatePremiumService.calculatePremium(travelCalculatePremiumRequest);
        assertEquals(travelCalculatePremiumResponse.getPersonLastName(), travelCalculatePremiumRequest.getPersonLastName());
    }

    @Test
    public void shouldPopulateAgreementDateFrom() {
        var travelCalculatePremiumResponse = travelCalculatePremiumService.calculatePremium(travelCalculatePremiumRequest);
        assertEquals(travelCalculatePremiumResponse.getAgreementDateFrom(), travelCalculatePremiumRequest.getAgreementDateFrom());
    }

    @Test
    public void shouldPopulateAgreementDateTo() {
        var travelCalculatePremiumResponse = travelCalculatePremiumService.calculatePremium(travelCalculatePremiumRequest);
        assertEquals(travelCalculatePremiumResponse.getAgreementDateTo(), travelCalculatePremiumRequest.getAgreementDateTo());
    }

    @Test
    public void shouldPopulateAgreementPrice() {
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