package org.travel.insurance.core.validations;

import org.junit.jupiter.api.Test;
import org.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.travel.insurance.dto.ValidationError;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class DateFromLessThenDateToValidationTest {

    private DateFromLessThenDateToValidation validation = new DateFromLessThenDateToValidation();

    @Test
    public void shouldReturnErrorWhenDateFromIsAfterDateTo() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getAgreementDateFrom()).thenReturn(createDate("10.01.2025"));
        when(request.getAgreementDateTo()).thenReturn(createDate("01.01.2025"));
        Optional<ValidationError> errorOpt = validation.validateDateFromLessThenDateTo(request);
        assertTrue(errorOpt.isPresent());
        assertEquals("agreementDateFrom", errorOpt.get().getField());
        assertEquals("Must be less then agreementDateTo!", errorOpt.get().getMessage());
    }

    @Test
    public void shouldReturnErrorWhenDateFromIsEqualsDateTo() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getAgreementDateFrom()).thenReturn(createDate("01.01.2025"));
        when(request.getAgreementDateTo()).thenReturn(createDate("01.01.2025"));
        Optional<ValidationError> errorOpt = validation.validateDateFromLessThenDateTo(request);
        assertTrue(errorOpt.isPresent());
        assertEquals("agreementDateFrom", errorOpt.get().getField());
        assertEquals("Must be less then agreementDateTo!", errorOpt.get().getMessage());
    }

    @Test
    public void shouldNotReturnErrorWhenDateFromIsLessDateTo() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getAgreementDateFrom()).thenReturn(createDate("01.01.2025"));
        when(request.getAgreementDateTo()).thenReturn(createDate("10.01.2025"));
        Optional<ValidationError> errorOpt = validation.validateDateFromLessThenDateTo(request);
        assertTrue(errorOpt.isEmpty());
    }

    private Date createDate(String dateStr) {
        try {
            return new SimpleDateFormat("dd.MM.yyyy").parse(dateStr);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

}
