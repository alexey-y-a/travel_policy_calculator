package org.travel.insurance.core.underwriting;

import org.travel.insurance.dto.RiskPremium;

import java.math.BigDecimal;
import java.util.List;

public record TravelPremiumCalculationResult(
        BigDecimal totalPremium,
        List<RiskPremium> riskPremiums
) {}
