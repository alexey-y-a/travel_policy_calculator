package org.travel.insurance.core.underwriting;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.travel.insurance.dto.RiskPremium;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class TravelPremiumCalculationResult {

    private BigDecimal totalPremium;

    private List<RiskPremium> riskPremiums;

}
