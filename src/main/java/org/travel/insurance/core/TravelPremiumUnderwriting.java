package org.travel.insurance.core;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.travel.insurance.dto.TravelCalculatePremiumRequest;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
class TravelPremiumUnderwriting {
    
    private final DateTimeService dateTimeService;

    BigDecimal calculatePremium(TravelCalculatePremiumRequest travelCalculatePremiumRequest) {
        var daysBetween = dateTimeService.getDaysBetween(travelCalculatePremiumRequest.getAgreementDateFrom(), travelCalculatePremiumRequest.getAgreementDateTo());
        return new BigDecimal(daysBetween);
    }
}
