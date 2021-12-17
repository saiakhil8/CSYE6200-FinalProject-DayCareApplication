package edu.neu.csye6200.repositories;

import edu.neu.csye6200.models.ImmunizationTracker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author SaiAkhil
 */
@Repository
public interface ImmunizationRepository extends JpaRepository<ImmunizationTracker, Integer> {
    ImmunizationTracker findTopByStudentId(int studentId);

    ImmunizationTracker findByStudentId(int studentId);
}
