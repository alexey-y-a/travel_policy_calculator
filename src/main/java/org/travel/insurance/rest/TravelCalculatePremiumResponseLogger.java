package org.travel.insurance.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.travel.insurance.dto.TravelCalculatePremiumResponse;

@Component
@Slf4j
class TravelCalculatePremiumResponseLogger {

    void log(TravelCalculatePremiumResponse response) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String json = objectMapper.writeValueAsString(response);
            log.info("RESPONSE: " + json);
        } catch (JsonProcessingException e) {
            log.error("Error to convert response to JSON", e);
        }
    }
}
