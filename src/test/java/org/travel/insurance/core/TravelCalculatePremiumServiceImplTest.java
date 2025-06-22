package org.travel.insurance.core;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.travel.insurance.dto.TravelCalculatePremiumResponse;
import org.travel.insurance.dto.ValidationError;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verifyNoInteractions;

@ExtendWith(MockitoExtension.class)
public class TravelCalculatePremiumServiceImplTest {

    @Mock private TravelCalculatePremiumRequestValidator requestValidator;
    @Mock private TravelPremiumUnderwriting premiumUnderwriting;

    @InjectMocks
    private TravelCalculatePremiumServiceImpl travelCalculatePremiumService;

    @Test
    public void shouldReturnResponseWithErrors() {
        TravelCalculatePremiumRequest travelCalculatePremiumRequest = mock(TravelCalculatePremiumRequest.class);
        List<ValidationError> errors = buildValidationErrorList();
        when(requestValidator.validate(travelCalculatePremiumRequest)).thenReturn(errors);
        TravelCalculatePremiumResponse response = travelCalculatePremiumService.calculatePremium(travelCalculatePremiumRequest);
        assertTrue(response.hasErrors());
    }

    @Test
    public void shouldReturnResponseWithValidationErrors() {
        TravelCalculatePremiumRequest travelCalculatePremiumRequest = mock(TravelCalculatePremiumRequest.class);
        List<ValidationError> errors = buildValidationErrorList();
        when(requestValidator.validate(travelCalculatePremiumRequest)).thenReturn(errors);
        TravelCalculatePremiumResponse response = travelCalculatePremiumService.calculatePremium(travelCalculatePremiumRequest);
        assertEquals(1, response.getErrors().size());
        assertEquals("field", response.getErrors().get(0).getField());
        assertEquals("errorMessage", response.getErrors().get(0).getMessage());
    }

    @Test
    public void shouldNotInvokePremiumUnderwritingWhenValidationErrors() {
        TravelCalculatePremiumRequest travelCalculatePremiumRequest = mock(TravelCalculatePremiumRequest.class);
        List<ValidationError> errors = buildValidationErrorList();
        when(requestValidator.validate(travelCalculatePremiumRequest)).thenReturn(errors);
        travelCalculatePremiumService.calculatePremium(travelCalculatePremiumRequest);
        verifyNoInteractions(premiumUnderwriting);
    }

    @Test
    public void shouldReturnResponseWithCorrectPersonFirstName() {
        TravelCalculatePremiumRequest travelCalculatePremiumRequest = mock(TravelCalculatePremiumRequest.class);
        when(travelCalculatePremiumRequest.getPersonFirstName()).thenReturn("personFirstName");
        when(requestValidator.validate(travelCalculatePremiumRequest)).thenReturn(List.of());
        TravelCalculatePremiumResponse response = travelCalculatePremiumService.calculatePremium(travelCalculatePremiumRequest);
        assertEquals("personFirstName", response.getPersonFirstName());
    }

    @Test
    public void shouldReturnResponseWithCorrectPersonLastName() {
        TravelCalculatePremiumRequest travelCalculatePremiumRequest = mock(TravelCalculatePremiumRequest.class);
        when(travelCalculatePremiumRequest.getPersonLastName()).thenReturn("personLastName");
        when(requestValidator.validate(travelCalculatePremiumRequest)).thenReturn(List.of());
        TravelCalculatePremiumResponse response = travelCalculatePremiumService.calculatePremium(travelCalculatePremiumRequest);
        assertEquals("personLastName", response.getPersonLastName());
    }

    @Test
    public void shouldReturnResponseWithCorrectAgreementDateFrom() {
        TravelCalculatePremiumRequest travelCalculatePremiumRequest = mock(TravelCalculatePremiumRequest.class);
        Date dateFrom = new Date();
        when(travelCalculatePremiumRequest.getAgreementDateFrom()).thenReturn(dateFrom);
        when(requestValidator.validate(travelCalculatePremiumRequest)).thenReturn(List.of());
        TravelCalculatePremiumResponse response = travelCalculatePremiumService.calculatePremium(travelCalculatePremiumRequest);
        assertEquals(dateFrom, response.getAgreementDateFrom());
    }

    @Test
    public void shouldReturnResponseWithCorrectAgreementDateTo() {
        TravelCalculatePremiumRequest travelCalculatePremiumRequest = mock(TravelCalculatePremiumRequest.class);
        Date dateTo = new Date();
        when(travelCalculatePremiumRequest.getAgreementDateTo()).thenReturn(dateTo);
        when(requestValidator.validate(travelCalculatePremiumRequest)).thenReturn(List.of());
        TravelCalculatePremiumResponse response = travelCalculatePremiumService.calculatePremium(travelCalculatePremiumRequest);
        assertEquals(dateTo, response.getAgreementDateTo());
    }

    @Test
    public void shouldReturnResponseWithCorrectAgreementPrice() {
        TravelCalculatePremiumRequest travelCalculatePremiumRequest = mock(TravelCalculatePremiumRequest.class);
        when(travelCalculatePremiumRequest.getAgreementDateFrom()).thenReturn(createDate("01.01.2023"));
        when(travelCalculatePremiumRequest.getAgreementDateTo()).thenReturn(createDate("10.01.2023"));
        when(requestValidator.validate(travelCalculatePremiumRequest)).thenReturn(List.of());
        when(premiumUnderwriting.calculatePremium(travelCalculatePremiumRequest)).thenReturn(new BigDecimal(9));
        TravelCalculatePremiumResponse response = travelCalculatePremiumService.calculatePremium(travelCalculatePremiumRequest);
        assertEquals(new BigDecimal(9), response.getAgreementPrice());
    }

    private List<ValidationError> buildValidationErrorList() {
        return List.of(
                new ValidationError("field", "errorMessage")
        );
    }

    private Date createDate(String dateStr) {
        try {
            return new SimpleDateFormat("dd.MM.yyyy").parse(dateStr);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

}