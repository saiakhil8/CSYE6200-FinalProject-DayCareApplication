package edu.neu.csye6200.repositories;

import edu.neu.csye6200.models.ClassSections;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author SaiAkhil
 */
@Repository
public interface ClassRoomRepository extends JpaRepository<ClassSections, Integer> {

    ClassSections findTopByMinAgeBeforeAndMaxAgeAfterOrderByClassRoomId(int age1, int age2);

    ClassSections findTopByStudentIdsContaining(String studentId);

    long countAllByTeacherIdsContainingAndClassRuleIdNotLike(String teacherId, int classRuleId);
}
