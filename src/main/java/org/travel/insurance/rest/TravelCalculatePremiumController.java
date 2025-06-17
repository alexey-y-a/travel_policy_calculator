package org.travel.insurance.rest;


import org.travel.insurance.core.TravelCalculatePremiumService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.travel.insurance.dto.TravelCalculatePremiumResponse;

@RestController
@RequestMapping("/insurance/travel")
public class TravelCalculatePremiumController {

	private final TravelCalculatePremiumService calculatePremiumService;

	public TravelCalculatePremiumController(TravelCalculatePremiumService calculatePremiumService) {
		this.calculatePremiumService = calculatePremiumService;
	}

	@PostMapping(path = "/",
			consumes = "application/json",
			produces = "application/json")
	public TravelCalculatePremiumResponse calculatePremium(@RequestBody TravelCalculatePremiumRequest request) {
		return calculatePremiumService.calculatePremium(request);
	}

}