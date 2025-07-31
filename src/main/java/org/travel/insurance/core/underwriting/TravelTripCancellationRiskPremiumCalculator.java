package org.travel.insurance.core.underwriting;

import org.springframework.stereotype.Component;
import org.travel.insurance.dto.TravelCalculatePremiumRequest;

import java.math.BigDecimal;

@Component
class TravelTripCancellationRiskPremiumCalculator implements TravelRiskPremiumCalculator {

    @Override
    public BigDecimal calculatePremium(TravelCalculatePremiumRequest request) {
        return BigDecimal.ZERO;
    }

    @Override
    public String getRiskIc() {
        return "TRAVEL_CANCELLATION";
    }

}
