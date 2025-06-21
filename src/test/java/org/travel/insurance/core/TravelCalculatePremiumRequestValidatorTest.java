package org.travel.insurance.core;

import org.junit.jupiter.api.Test;
import org.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.travel.insurance.dto.ValidationError;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TravelCalculatePremiumRequestValidatorTest {

    private TravelCalculatePremiumRequestValidator requestValidator = new TravelCalculatePremiumRequestValidator();

    @Test
    public void shouldReturnErrorWhenPersonFirstNameIsNull() {
        TravelCalculatePremiumRequest travelCalculatePremiumRequest = mock(TravelCalculatePremiumRequest.class);
        when(travelCalculatePremiumRequest.getPersonFirstName()).thenReturn(null);
        when(travelCalculatePremiumRequest.getPersonLastName()).thenReturn("lastName");
        when(travelCalculatePremiumRequest.getAgreementDateFrom()).thenReturn(createDate("01.01.2023"));
        when(travelCalculatePremiumRequest.getAgreementDateTo()).thenReturn(createDate("10.01.2023"));
        List<ValidationError> errors = requestValidator.validate(travelCalculatePremiumRequest);
        assertFalse(errors.isEmpty());
        assertEquals(1, errors.size());
        assertEquals("personFirstName", errors.get(0).getField());
        assertEquals("Must not be empty!", errors.get(0).getMessage());
    }

    @Test
    public void shouldReturnErrorWhenPersonFirstNameIsEmpty() {
        TravelCalculatePremiumRequest travelCalculatePremiumRequest = mock(TravelCalculatePremiumRequest.class);
        when(travelCalculatePremiumRequest.getPersonFirstName()).thenReturn("");
        when(travelCalculatePremiumRequest.getPersonLastName()).thenReturn("lastName");
        when(travelCalculatePremiumRequest.getAgreementDateFrom()).thenReturn(createDate("01.01.2023"));
        when(travelCalculatePremiumRequest.getAgreementDateTo()).thenReturn(createDate("10.01.2023"));
        List<ValidationError> errors = requestValidator.validate(travelCalculatePremiumRequest);
        assertFalse(errors.isEmpty());
        assertEquals(1, errors.size());
        assertEquals("personFirstName", errors.get(0).getField());
        assertEquals("Must not be empty!", errors.get(0).getMessage());
    }

    @Test
    public void shouldReturnErrorWhenPersonLastNameIsNull() {
        TravelCalculatePremiumRequest travelCalculatePremiumRequest = mock(TravelCalculatePremiumRequest.class);
        when(travelCalculatePremiumRequest.getPersonFirstName()).thenReturn("firstName");
        when(travelCalculatePremiumRequest.getPersonLastName()).thenReturn(null);
        when(travelCalculatePremiumRequest.getAgreementDateFrom()).thenReturn(createDate("01.01.2023"));
        when(travelCalculatePremiumRequest.getAgreementDateTo()).thenReturn(createDate("10.01.2023"));
        List<ValidationError> errors = requestValidator.validate(travelCalculatePremiumRequest);
        assertFalse(errors.isEmpty());
        assertEquals(1, errors.size());
        assertEquals("personLastName", errors.get(0).getField());
        assertEquals("Must not be empty!", errors.get(0).getMessage());
    }

    @Test
    public void shouldReturnErrorWhenPersonLastNameIsEmpty() {
        TravelCalculatePremiumRequest travelCalculatePremiumRequest = mock(TravelCalculatePremiumRequest.class);
        when(travelCalculatePremiumRequest.getPersonFirstName()).thenReturn("firstName");
        when(travelCalculatePremiumRequest.getPersonLastName()).thenReturn("");
        when(travelCalculatePremiumRequest.getAgreementDateFrom()).thenReturn(createDate("01.01.2023"));
        when(travelCalculatePremiumRequest.getAgreementDateTo()).thenReturn(createDate("10.01.2023"));
        List<ValidationError> errors = requestValidator.validate(travelCalculatePremiumRequest);
        assertFalse(errors.isEmpty());
        assertEquals(1, errors.size());
        assertEquals("personLastName", errors.get(0).getField());
        assertEquals("Must not be empty!", errors.get(0).getMessage());
    }

    @Test
    public void shouldReturnErrorWhenAgreementDateFromIsNull() {
        TravelCalculatePremiumRequest travelCalculatePremiumRequest = mock(TravelCalculatePremiumRequest.class);
        when(travelCalculatePremiumRequest.getPersonFirstName()).thenReturn("firstName");
        when(travelCalculatePremiumRequest.getPersonLastName()).thenReturn("lastName");
        when(travelCalculatePremiumRequest.getAgreementDateFrom()).thenReturn(null);
        when(travelCalculatePremiumRequest.getAgreementDateTo()).thenReturn(createDate("10.01.2023"));
        List<ValidationError> errors = requestValidator.validate(travelCalculatePremiumRequest);
        assertFalse(errors.isEmpty());
        assertEquals(1, errors.size());
        assertEquals("agreementDateFrom", errors.get(0).getField());
        assertEquals("Must not be empty!", errors.get(0).getMessage());
    }

    @Test
    public void shouldReturnErrorWhenAgreementDateToIsNull() {
        TravelCalculatePremiumRequest travelCalculatePremiumRequest = mock(TravelCalculatePremiumRequest.class);
        when(travelCalculatePremiumRequest.getPersonFirstName()).thenReturn("firstName");
        when(travelCalculatePremiumRequest.getPersonLastName()).thenReturn("lastName");
        when(travelCalculatePremiumRequest.getAgreementDateFrom()).thenReturn(createDate("01.01.2023"));
        when(travelCalculatePremiumRequest.getAgreementDateTo()).thenReturn(null);
        List<ValidationError> errors = requestValidator.validate(travelCalculatePremiumRequest);
        assertFalse(errors.isEmpty());
        assertEquals(1, errors.size());
        assertEquals("agreementDateTo", errors.get(0).getField());
        assertEquals("Must not be empty!", errors.get(0).getMessage());
    }

    @Test
    public void shouldReturnErrorWhenDateFromIsEqualsDateTo() {
        TravelCalculatePremiumRequest travelCalculatePremiumRequest = mock(TravelCalculatePremiumRequest.class);
        when(travelCalculatePremiumRequest.getPersonFirstName()).thenReturn("firstName");
        when(travelCalculatePremiumRequest.getPersonLastName()).thenReturn("lastName");
        when(travelCalculatePremiumRequest.getAgreementDateFrom()).thenReturn(createDate("01.01.2023"));
        when(travelCalculatePremiumRequest.getAgreementDateTo()).thenReturn(createDate("01.01.2023"));
        List<ValidationError> errors = requestValidator.validate(travelCalculatePremiumRequest);
        assertFalse(errors.isEmpty());
        assertEquals(1, errors.size());
        assertEquals("agreementDateFrom", errors.get(0).getField());
        assertEquals("Must be less then agreementDateTo!", errors.get(0).getMessage());
    }

    @Test
    public void shouldReturnErrorWhenDateFromIsAfterDateTo() {
        TravelCalculatePremiumRequest travelCalculatePremiumRequest = mock(TravelCalculatePremiumRequest.class);
        when(travelCalculatePremiumRequest.getPersonFirstName()).thenReturn("firstName");
        when(travelCalculatePremiumRequest.getPersonLastName()).thenReturn("lastName");
        when(travelCalculatePremiumRequest.getAgreementDateFrom()).thenReturn(createDate("10.01.2023"));
        when(travelCalculatePremiumRequest.getAgreementDateTo()).thenReturn(createDate("01.01.2023"));
        List<ValidationError> errors = requestValidator.validate(travelCalculatePremiumRequest);
        assertFalse(errors.isEmpty());
        assertEquals(1, errors.size());
        assertEquals("agreementDateFrom", errors.get(0).getField());
        assertEquals("Must be less then agreementDateTo!", errors.get(0).getMessage());
    }

    @Test
    public void shouldNotReturnErrorWhenPersonFirstNameIsPresent() {
        TravelCalculatePremiumRequest travelCalculatePremiumRequest = mock(TravelCalculatePremiumRequest.class);
        when(travelCalculatePremiumRequest.getPersonFirstName()).thenReturn("firstName");
        when(travelCalculatePremiumRequest.getPersonLastName()).thenReturn("lastName");
        when(travelCalculatePremiumRequest.getAgreementDateFrom()).thenReturn(createDate("01.01.2023"));
        when(travelCalculatePremiumRequest.getAgreementDateTo()).thenReturn(createDate("10.01.2023"));
        List<ValidationError> errors = requestValidator.validate(travelCalculatePremiumRequest);
        assertTrue(errors.isEmpty());
    }

    private Date createDate(String dateStr) {
        try {
            return new SimpleDateFormat("dd.MM.yy").parse(dateStr);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

}