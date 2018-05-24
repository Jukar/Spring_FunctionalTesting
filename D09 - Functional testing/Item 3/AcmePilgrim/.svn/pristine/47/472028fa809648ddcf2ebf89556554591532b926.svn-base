package repositories;

import java.util.Collection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import domain.Complaint;

@Repository
public interface ComplaintRepository extends JpaRepository<Complaint,Integer>{
//	@Query("select avg(count(c)) from Pilgrim p join p.complains c")
	@Query("select avg(p.complaints.size) from Pilgrim p")
	Double avrComplaint();
	
	@Query("select p.complaints from Pilgrim p where p.id=?1")
	Collection<Complaint> findByPilgrimId(int pilgrimId);
	
	@Query("select c from Complaint c where c.administrator.id=?1 or c.administrator=null")
	Collection<Complaint> findByAdministratorId(int administratorId);
}
