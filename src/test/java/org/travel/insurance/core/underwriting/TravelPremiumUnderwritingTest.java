package org.travel.insurance.core.underwriting;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.test.util.ReflectionTestUtils;
import org.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TravelPremiumUnderwritingTest {

    @InjectMocks
    private TravelPremiumUnderwritingImpl premiumUnderwriting;

    private TravelRiskPremiumCalculator riskPremiumCalculator1;
    private TravelRiskPremiumCalculator riskPremiumCalculator2;

    @BeforeEach
    public void init() {
        riskPremiumCalculator1 = mock(TravelRiskPremiumCalculator.class);
        riskPremiumCalculator2 = mock(TravelRiskPremiumCalculator.class);
        var riskPremiumCalculators = List.of(riskPremiumCalculator1, riskPremiumCalculator2);
        ReflectionTestUtils.setField(premiumUnderwriting, "riskPremiumCalculators", riskPremiumCalculators);
    }

    @Test
    void shouldCalculatePremiumForOneRisk() {
        when(riskPremiumCalculator1.getRiskIc()).thenReturn("TRAVEL_MEDICAL");
        when(riskPremiumCalculator1.calculatePremium(any())).thenReturn(BigDecimal.ONE);
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getSelectedRisks()).thenReturn(List.of("TRAVEL_MEDICAL"));
        TravelPremiumCalculationResult premiumCalculationResult = premiumUnderwriting.calculatePremium(request);
        assertEquals(BigDecimal.ONE, premiumCalculationResult.getTotalPremium());
    }

    @Test
    void shouldCalculatePremiumForTwoRisks() {
        when(riskPremiumCalculator1.getRiskIc()).thenReturn("TRAVEL_MEDICAL");
        when(riskPremiumCalculator2.getRiskIc()).thenReturn("TRAVEL_EVACUATION");

        when(riskPremiumCalculator1.calculatePremium(any())).thenReturn(BigDecimal.ONE);
        when(riskPremiumCalculator2.calculatePremium(any())).thenReturn(BigDecimal.ONE);

        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getSelectedRisks()).thenReturn(List.of("TRAVEL_MEDICAL", "TRAVEL_EVACUATION"));
        TravelPremiumCalculationResult premiumCalculationResult = premiumUnderwriting.calculatePremium(request);
        assertEquals(new BigDecimal(2), premiumCalculationResult.getTotalPremium());
    }

}