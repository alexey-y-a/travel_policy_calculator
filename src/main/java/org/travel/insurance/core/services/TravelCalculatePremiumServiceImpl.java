package org.travel.insurance.core.services;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.travel.insurance.core.underwriting.TravelPremiumUnderwriting;
import org.travel.insurance.core.validations.TravelCalculatePremiumRequestValidator;
import org.travel.insurance.dto.RiskPremium;
import org.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.travel.insurance.dto.TravelCalculatePremiumResponse;
import org.springframework.stereotype.Component;
import org.travel.insurance.dto.ValidationError;
import java.math.BigDecimal;
import java.util.List;

@Component
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class TravelCalculatePremiumServiceImpl implements TravelCalculatePremiumService {

    private final TravelCalculatePremiumRequestValidator requestValidator;
    private final TravelPremiumUnderwriting premiumUnderwriting;

    @Override
    public TravelCalculatePremiumResponse calculatePremium(TravelCalculatePremiumRequest travelCalculatePremiumRequest) {
        List<ValidationError> errors = requestValidator.validate(travelCalculatePremiumRequest);
        return errors.isEmpty()
                ? buildResponse(travelCalculatePremiumRequest, premiumUnderwriting.calculatePremium(travelCalculatePremiumRequest))
                : buildResponse(errors);
    }

    private TravelCalculatePremiumResponse buildResponse(List<ValidationError> errors) {
        return new TravelCalculatePremiumResponse(errors);
    }

    private TravelCalculatePremiumResponse buildResponse(TravelCalculatePremiumRequest travelCalculatePremiumRequest, BigDecimal premium) {
        TravelCalculatePremiumResponse travelCalculatePremiumResponse = new TravelCalculatePremiumResponse();
        travelCalculatePremiumResponse.setPersonFirstName(travelCalculatePremiumRequest.getPersonFirstName());
        travelCalculatePremiumResponse.setPersonLastName(travelCalculatePremiumRequest.getPersonLastName());
        travelCalculatePremiumResponse.setAgreementDateFrom(travelCalculatePremiumRequest.getAgreementDateFrom());
        travelCalculatePremiumResponse.setAgreementDateTo(travelCalculatePremiumRequest.getAgreementDateTo());
        travelCalculatePremiumResponse.setAgreementPremium(premium);
        travelCalculatePremiumResponse.setRisks(buildRisks(travelCalculatePremiumRequest));
        return travelCalculatePremiumResponse;
    }

    private List<RiskPremium> buildRisks(TravelCalculatePremiumRequest request) {
        return request.getSelectedRisks().stream()
                .map(riskIc -> new RiskPremium(riskIc, BigDecimal.ZERO))
                .toList();
    }

}
