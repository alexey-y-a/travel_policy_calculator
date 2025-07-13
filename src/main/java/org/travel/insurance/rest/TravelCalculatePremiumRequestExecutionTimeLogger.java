package org.travel.insurance.rest;

import com.google.common.base.Stopwatch;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
class TravelCalculatePremiumRequestExecutionTimeLogger {

    void logExecutionTime(Stopwatch stopwatch) {
        stopwatch.stop();
        long elapsedMillis = stopwatch.elapsed().toMillis();
        log.info("Request processing time (ms): " + elapsedMillis);
    }

}
