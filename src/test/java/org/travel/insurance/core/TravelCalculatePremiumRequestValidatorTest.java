package org.travel.insurance.core;

import org.junit.jupiter.api.Test;
import org.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.travel.insurance.dto.ValidationError;
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
        List<ValidationError> errors = requestValidator.validate(travelCalculatePremiumRequest);
        assertFalse(errors.isEmpty());
        assertEquals(1, errors.size());
        assertEquals("personFirstName", errors.get(0).getField());
        assertEquals("Must not be empty!", errors.get(0).getMessage());
    }

    @Test
    public void shouldNotReturnErrorWhenPersonFirstNameIsPresent() {
        TravelCalculatePremiumRequest travelCalculatePremiumRequest = mock(TravelCalculatePremiumRequest.class);
        when(travelCalculatePremiumRequest.getPersonFirstName()).thenReturn("firstName");
        List<ValidationError> errors = requestValidator.validate(travelCalculatePremiumRequest);
        assertTrue(errors.isEmpty());
    }

}