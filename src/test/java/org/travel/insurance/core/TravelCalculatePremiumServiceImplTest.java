package org.travel.insurance.core;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.travel.insurance.dto.ValidationError;
import java.util.Date;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TravelCalculatePremiumServiceImplTest {

    @Mock private TravelCalculatePremiumRequestValidator requestValidator;
    @Mock private DateTimeService dateTimeService;

    @InjectMocks
    private TravelCalculatePremiumServiceImpl travelCalculatePremiumService;

    @Test
    public void shouldPopulateFirstName() {
        var travelCalculatePremiumRequest = createRequestWithAllFields();
        when(dateTimeService.getDaysBetween(travelCalculatePremiumRequest.getAgreementDateFrom(), travelCalculatePremiumRequest.getAgreementDateTo())).thenReturn(0L);
        when(requestValidator.validate(travelCalculatePremiumRequest)).thenReturn(List.of());
        var travelCalculatePremiumResponse = travelCalculatePremiumService.calculatePremium(travelCalculatePremiumRequest);
        assertEquals(travelCalculatePremiumResponse.getPersonFirstName(), travelCalculatePremiumRequest.getPersonFirstName());
    }

    @Test
    public void shouldPopulateLastName() {
        var travelCalculatePremiumRequest = createRequestWithAllFields();
        when(dateTimeService.getDaysBetween(travelCalculatePremiumRequest.getAgreementDateFrom(), travelCalculatePremiumRequest.getAgreementDateTo())).thenReturn(0L);
        when(requestValidator.validate(travelCalculatePremiumRequest)).thenReturn(List.of());
        var travelCalculatePremiumResponse = travelCalculatePremiumService.calculatePremium(travelCalculatePremiumRequest);
        assertEquals(travelCalculatePremiumResponse.getPersonLastName(), travelCalculatePremiumRequest.getPersonLastName());
    }

    @Test
    public void shouldPopulateAgreementDateFrom() {
        var travelCalculatePremiumRequest = createRequestWithAllFields();
        when(dateTimeService.getDaysBetween(travelCalculatePremiumRequest.getAgreementDateFrom(), travelCalculatePremiumRequest.getAgreementDateTo())).thenReturn(0L);
        when(requestValidator.validate(travelCalculatePremiumRequest)).thenReturn(List.of());
        var travelCalculatePremiumResponse = travelCalculatePremiumService.calculatePremium(travelCalculatePremiumRequest);
        assertEquals(travelCalculatePremiumResponse.getAgreementDateFrom(), travelCalculatePremiumRequest.getAgreementDateFrom());
    }

    @Test
    public void shouldPopulateAgreementDateTo() {
        var travelCalculatePremiumRequest = createRequestWithAllFields();
        when(dateTimeService.getDaysBetween(travelCalculatePremiumRequest.getAgreementDateFrom(), travelCalculatePremiumRequest.getAgreementDateTo())).thenReturn(0L);
        when(requestValidator.validate(travelCalculatePremiumRequest)).thenReturn(List.of());
        var travelCalculatePremiumResponse = travelCalculatePremiumService.calculatePremium(travelCalculatePremiumRequest);
        assertEquals(travelCalculatePremiumResponse.getAgreementDateTo(), travelCalculatePremiumRequest.getAgreementDateTo());
    }

    @Test
    public void shouldPopulateAgreementPrice() {
        var travelCalculatePremiumRequest = createRequestWithAllFields();
        when(dateTimeService.getDaysBetween(travelCalculatePremiumRequest.getAgreementDateFrom(), travelCalculatePremiumRequest.getAgreementDateTo())).thenReturn(0L);
        when(requestValidator.validate(travelCalculatePremiumRequest)).thenReturn(List.of());
        var travelCalculatePremiumResponse = travelCalculatePremiumService.calculatePremium(travelCalculatePremiumRequest);
        assertNotNull(travelCalculatePremiumResponse.getAgreementPrice());
    }

    @Test
    public void shouldReturnResponseWithErrors() {
        var travelCalculatePremiumRequest = new TravelCalculatePremiumRequest();
        var validationError = new ValidationError("field", "message");
        when(requestValidator.validate(travelCalculatePremiumRequest)).thenReturn(List.of(validationError));
        var travelCalculatePremiumResponse = travelCalculatePremiumService.calculatePremium(travelCalculatePremiumRequest);
        assertTrue(travelCalculatePremiumResponse.hasErrors());
    }

    @Test
    public void shouldReturnResponseWithCorrectErrorCount() {
        var travelCalculatePremiumRequest = new TravelCalculatePremiumRequest();
        var validationError = new ValidationError("field", "message");
        when(requestValidator.validate(travelCalculatePremiumRequest)).thenReturn(List.of(validationError));
        var travelCalculatePremiumResponse = travelCalculatePremiumService.calculatePremium(travelCalculatePremiumRequest);
        assertEquals(1, travelCalculatePremiumResponse.getErrors().size());
    }

    @Test
    public void shouldReturnResponseWithCorrectError() {
        var travelCalculatePremiumRequest = new TravelCalculatePremiumRequest();
        var validationError = new ValidationError("field", "message");
        when(requestValidator.validate(travelCalculatePremiumRequest)).thenReturn(List.of(validationError));
        var travelCalculatePremiumResponse = travelCalculatePremiumService.calculatePremium(travelCalculatePremiumRequest);
        assertEquals("field", travelCalculatePremiumResponse.getErrors().get(0).getField());
        assertEquals("message", travelCalculatePremiumResponse.getErrors().get(0).getMessage());
        assertNull(travelCalculatePremiumResponse.getPersonFirstName());
    }

    @Test
    public void allFieldsMustBeEmptyWhenResponseContainsError() {
        var travelCalculatePremiumRequest = new TravelCalculatePremiumRequest();
        var validationError = new ValidationError("field", "message");
        when(requestValidator.validate(travelCalculatePremiumRequest)).thenReturn(List.of(validationError));
        var travelCalculatePremiumResponse = travelCalculatePremiumService.calculatePremium(travelCalculatePremiumRequest);
        assertNull(travelCalculatePremiumResponse.getPersonFirstName());
        assertNull(travelCalculatePremiumResponse.getPersonLastName());
        assertNull(travelCalculatePremiumResponse.getAgreementDateFrom());
        assertNull(travelCalculatePremiumResponse.getAgreementDateTo());
        assertNull(travelCalculatePremiumResponse.getAgreementPrice());
    }

    @Test
    public void shouldNOtBeInteractionWithDateTimeServiceWhenResponseContainsError() {
        var travelCalculatePremiumRequest = new TravelCalculatePremiumRequest();
        var validationError = new ValidationError("field", "message");
        when(requestValidator.validate(travelCalculatePremiumRequest)).thenReturn(List.of(validationError));
        var travelCalculatePremiumResponse = travelCalculatePremiumService.calculatePremium(travelCalculatePremiumRequest);
        verifyNoInteractions(dateTimeService);
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