package org.travel.insurance.core.repositories;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.travel.insurance.core.domain.Classifier;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class ClassifierRepositoryTest {

    @Autowired
    private ClassifierRepository classifierRepository;

    @Test
    @DisplayName("Test: Classifier table is present")
    public void injectedRepositoryAreNotNull() {
        assertNotNull(classifierRepository);
    }

    @Test
    @DisplayName("Test: Can find record by Title")
    public void shouldFindRiskTypeClassifier() {
        Optional<Classifier> riskTypeOpt = classifierRepository.findByTitle("RISK_TYPE");
        assertTrue(riskTypeOpt.isPresent());
        assertEquals("RISK_TYPE", riskTypeOpt.get().getTitle());
    }

    @Test
    @DisplayName("Test: Can't find record with Fake title")
    public void shouldNotFindFakeClassifier() {
        Optional<Classifier> riskTypeOpt = classifierRepository.findByTitle("RISKS_TYPE");
        assertTrue(riskTypeOpt.isEmpty());
    }

}
