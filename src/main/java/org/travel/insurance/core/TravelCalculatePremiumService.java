package org.travel.insurance.core;

import org.travel.insurance.rest.TravelCalculatePremiumRequest;
import org.travel.insurance.rest.TravelCalculatePremiumResponse;

public interface TravelCalculatePremiumService {

    TravelCalculatePremiumResponse calculatePremium(TravelCalculatePremiumRequest request);

}
