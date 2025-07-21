package org.travel.insurance.rest;


import com.google.common.base.Stopwatch;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.travel.insurance.core.services.TravelCalculatePremiumService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.travel.insurance.dto.TravelCalculatePremiumResponse;

@RestController
@RequestMapping("/insurance/travel")
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class TravelCalculatePremiumController {

	private final TravelCalculatePremiumRequestLogger requestLogger;
	private final TravelCalculatePremiumResponseLogger responseLogger;
	private final TravelCalculatePremiumRequestExecutionTimeLogger executionTimeLogger;
	private final TravelCalculatePremiumService calculatePremiumService;

	@PostMapping(path = "/",
			consumes = "application/json",
			produces = "application/json")
	public TravelCalculatePremiumResponse calculatePremium(@RequestBody TravelCalculatePremiumRequest request) {
		Stopwatch stopwatch = Stopwatch.createStarted();
		TravelCalculatePremiumResponse response = processRequest(request);
		executionTimeLogger.logExecutionTime(stopwatch);
		return response;
	}

	private TravelCalculatePremiumResponse processRequest(TravelCalculatePremiumRequest request) {
		requestLogger.log(request);
		TravelCalculatePremiumResponse response = calculatePremiumService.calculatePremium(request);
		responseLogger.log(response);
		return response;
	}

}