package edu.neu.csye6200.repositories;

import edu.neu.csye6200.models.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author SaiAkhil
 */
@Repository
public interface AdminRepository extends JpaRepository<Admin, Integer> {
    public Admin getByEmailIdAndPassword(String emailId, String password);
}
