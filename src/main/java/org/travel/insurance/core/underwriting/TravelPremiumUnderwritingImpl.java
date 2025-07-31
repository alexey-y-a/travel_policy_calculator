package org.travel.insurance.core.underwriting;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.travel.insurance.core.util.DateTimeUtil;
import org.travel.insurance.dto.TravelCalculatePremiumRequest;

import java.math.BigDecimal;
import java.util.List;

@Component
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
class TravelPremiumUnderwritingImpl implements TravelPremiumUnderwriting {

    private final List<TravelRiskPremiumCalculator> riskPremiumCalculators;

    @Override
    public BigDecimal calculatePremium(TravelCalculatePremiumRequest request) {
        return request.getSelectedRisks().stream()
                .map(riskIc -> calculatePremiumForRisk(riskIc, request))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private BigDecimal calculatePremiumForRisk(String riskIc, TravelCalculatePremiumRequest request) {
        var riskPremiumCalculator = findRiskPremiumCalculator(riskIc);
        return riskPremiumCalculator.calculatePremium(request);
    }

    private TravelRiskPremiumCalculator findRiskPremiumCalculator(String riskIc) {
        return riskPremiumCalculators.stream()
                .filter(riskCalculator -> riskCalculator.getRiskIc().equals(riskIc))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Not supported riskIc = " + riskIc));
    }

}
