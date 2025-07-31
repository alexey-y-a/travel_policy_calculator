package org.travel.insurance.core.underwriting;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.travel.insurance.core.util.DateTimeUtil;
import org.travel.insurance.dto.TravelCalculatePremiumRequest;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
class TravelMedicalRiskPremiumCalculator implements TravelRiskPremiumCalculator {

    private final DateTimeUtil dateTimeUtil;

    @Override
    public BigDecimal calculatePremium(TravelCalculatePremiumRequest request) {
        var daysBetween = dateTimeUtil.getDaysBetween(request.getAgreementDateFrom(), request.getAgreementDateTo());
        return new BigDecimal(daysBetween);
    }

    @Override
    public String getRiskIc() {
        return "TRAVEL_MEDICAL";
    }

}
