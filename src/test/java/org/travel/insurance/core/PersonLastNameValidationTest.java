package org.travel.insurance.core;

import org.junit.jupiter.api.Test;
import org.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.travel.insurance.dto.ValidationError;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class PersonLastNameValidationTest {

    private PersonLastNameValidation validation = new PersonLastNameValidation();

    @Test
    public void shouldReturnErrorWhenPersonLastNameIsNull() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonLastName()).thenReturn(null);
        Optional<ValidationError> errorOpt = validation.validatePersonLastName(request);
        assertTrue(errorOpt.isPresent());
        assertEquals("personLastName", errorOpt.get().getField());
        assertEquals("Must not be empty!", errorOpt.get().getMessage());
    }

    @Test
    public void shouldReturnErrorWhenPersonLastNameIsEmpty() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonLastName()).thenReturn("");
        Optional<ValidationError> errorOpt = validation.validatePersonLastName(request);
        assertTrue(errorOpt.isPresent());
        assertEquals("personLastName", errorOpt.get().getField());
        assertEquals("Must not be empty!", errorOpt.get().getMessage());
    }

    @Test
    public void shouldNotReturnErrorWhenPersonLastNameIsPresent() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonLastName()).thenReturn("Petrov");
        Optional<ValidationError> errorOpt = validation.validatePersonLastName(request);
        assertTrue(errorOpt.isEmpty());
    }

}
