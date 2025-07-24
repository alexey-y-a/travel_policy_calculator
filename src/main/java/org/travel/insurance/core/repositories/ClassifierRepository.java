package org.travel.insurance.core.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.travel.insurance.core.domain.Classifier;

import java.util.Optional;

public interface ClassifierRepository extends JpaRepository<Classifier, Long> {

    Optional<Classifier> findByTitle(String title);
}
