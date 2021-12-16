package edu.neu.csye6200.repositories;

import edu.neu.csye6200.models.ClassRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author SaiAkhil
 */
@Repository
public interface ClassRoomRepository extends JpaRepository<ClassRoom, Integer> {

    ClassRoom findTopByMinAgeBeforeAndMaxAgeAfterOrderByClassRoomId(int age1, int age2);

    long countAllByTeacherIdsContainingAndClassRuleIdNotLike(String teacherId, int classRuleId);
}
