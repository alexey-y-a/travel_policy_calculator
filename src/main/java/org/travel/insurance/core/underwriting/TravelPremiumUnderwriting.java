package org.travel.insurance.core.underwriting;

import org.travel.insurance.dto.TravelCalculatePremiumRequest;

import java.math.BigDecimal;

public interface TravelPremiumUnderwriting {

    BigDecimal calculatePremium(TravelCalculatePremiumRequest request);

}
