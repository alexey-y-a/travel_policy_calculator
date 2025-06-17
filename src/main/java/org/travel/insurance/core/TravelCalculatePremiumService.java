package org.travel.insurance.core;

import org.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.travel.insurance.dto.TravelCalculatePremiumResponse;

public interface TravelCalculatePremiumService {

    TravelCalculatePremiumResponse calculatePremium(TravelCalculatePremiumRequest request);

}
