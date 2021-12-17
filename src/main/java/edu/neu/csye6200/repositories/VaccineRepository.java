package edu.neu.csye6200.repositories;

import edu.neu.csye6200.models.Vaccine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author SaiAkhil
 */
@Repository
public interface VaccineRepository extends JpaRepository<Vaccine, Integer> {
    Vaccine findByVaccineName(String name);
}
