package edu.neu.csye6200.repositories;

import edu.neu.csye6200.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author SaiAkhil
 */
@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
}
