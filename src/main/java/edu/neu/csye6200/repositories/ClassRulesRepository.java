package edu.neu.csye6200.repositories;

import edu.neu.csye6200.models.ClassRules;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author SaiAkhil
 */
@Repository
public interface ClassRulesRepository extends JpaRepository<ClassRules, Integer> {
    ClassRules findTopByMinAgeBeforeAndMaxAgeAfter(int age1, int age2);
}
