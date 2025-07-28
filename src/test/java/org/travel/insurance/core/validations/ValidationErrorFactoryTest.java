package org.travel.insurance.core.validations;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.travel.insurance.core.util.ErrorCodeUtil;
import org.travel.insurance.core.util.Placeholder;
import org.travel.insurance.dto.ValidationError;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ValidationErrorFactoryTest {

    @Mock private ErrorCodeUtil errorCodeUtil;

    @InjectMocks
    private ValidationErrorFactory factory;

    @Test
    public void shouldReturnValidationErrorWithDescription() {
        when(errorCodeUtil.getErrorDescription("ERROR_CODE"))
                .thenReturn("error description");
        ValidationError error = factory.buildError("ERROR_CODE");
        assertEquals("ERROR_CODE", error.getErrorCode());
        assertEquals("error description", error.getDescription());
    }

    @Test
    public void shouldReturnValidationErrorWithDescriptionUsingPlaceholder() {
        Placeholder placeholder = new Placeholder("PLACEHOLDER", "AAA");
        when(errorCodeUtil.getErrorDescription("ERROR_CODE", List.of(placeholder)))
                .thenReturn("error AAA description");
        ValidationError error = factory.buildError("ERROR_CODE", List.of(placeholder));
        assertEquals("ERROR_CODE", error.getErrorCode());
        assertEquals("error AAA description", error.getDescription());
    }

}