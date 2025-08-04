package org.travel.insurance.core.validations;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;
import org.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.travel.insurance.dto.ValidationError;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TravelCalculatePremiumRequestValidatorImplTest {

    @Test
    public void shouldNotReturnErrors() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        TravelRequestValidation validation1 = mock(TravelRequestValidation.class);
        when(validation1.validate(request)).thenReturn(Optional.empty());
        when(validation1.validateList(request)).thenReturn(List.of());
        TravelRequestValidation validation2 = mock(TravelRequestValidation.class);
        when(validation2.validate(request)).thenReturn(Optional.empty());
        when(validation2.validateList(request)).thenReturn(List.of());
        var validator = new TravelCalculatePremiumRequestValidatorImpl(List.of(validation1, validation2));
        List<ValidationError> errors = validator.validate(request);
        assertTrue(errors.isEmpty());
    }

    @Test
    public void shouldReturnSingleErrors() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        TravelRequestValidation validation1 = mock(TravelRequestValidation.class);
        when(validation1.validate(request)).thenReturn(Optional.of(new ValidationError()));
        TravelRequestValidation validation2 = mock(TravelRequestValidation.class);
        when(validation2.validate(request)).thenReturn(Optional.of(new ValidationError()));
        var validator = new TravelCalculatePremiumRequestValidatorImpl(List.of(validation1, validation2));
        List<ValidationError> errors = validator.validate(request);
        assertEquals(2, errors.size());
    }

    @Test
    public void shouldReturnListErrors() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        TravelRequestValidation validation1 = mock(TravelRequestValidation.class);
        when(validation1.validate(request)).thenReturn(Optional.empty());
        when(validation1.validateList(request)).thenReturn(List.of(new ValidationError()));
        TravelRequestValidation validation2 = mock(TravelRequestValidation.class);
        when(validation2.validate(request)).thenReturn(Optional.empty());
        when(validation2.validateList(request)).thenReturn(List.of(new ValidationError()));
        var validator = new TravelCalculatePremiumRequestValidatorImpl(List.of(validation1, validation2));
        List<ValidationError> errors = validator.validate(request);
        assertEquals(2, errors.size());
    }
}
