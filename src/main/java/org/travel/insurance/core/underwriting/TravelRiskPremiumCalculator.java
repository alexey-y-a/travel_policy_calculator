package org.travel.insurance.core.underwriting;

import org.travel.insurance.dto.TravelCalculatePremiumRequest;

import java.math.BigDecimal;

interface TravelRiskPremiumCalculator {

    BigDecimal calculatePremium(TravelCalculatePremiumRequest request);

    String getRiskIc();

}
