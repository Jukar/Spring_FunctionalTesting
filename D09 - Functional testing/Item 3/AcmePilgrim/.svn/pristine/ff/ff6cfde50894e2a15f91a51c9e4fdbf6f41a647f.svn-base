package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import domain.Discuss;

@Repository
public interface DiscussRepository extends JpaRepository<Discuss,Integer>{
	@Query("select d from Discuss d join d.complaint c where c.id=?1")
	Collection<Discuss> findByComplaintId(int complaintId);

}
