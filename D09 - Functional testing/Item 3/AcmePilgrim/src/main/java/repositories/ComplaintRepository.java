package repositories;

import java.util.Collection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import domain.Complaint;
import domain.Customer;

@Repository
public interface ComplaintRepository extends JpaRepository<Complaint,Integer>{
	@Query("select p.name,p.complaints.size from Pilgrim p")
	Collection<Object[]> pilgrimsAndNumberComplaints();
	
	@Query("select p.complaints from Pilgrim p where p.id=?1")
	Collection<Complaint> findByPilgrimId(int pilgrimId);
	
	@Query("select c from Complaint c where c.administrator.id=?1 or c.administrator=null")
	Collection<Complaint> findByAdministratorId(int administratorId);
	
	@Query("select c from Complaint c where status=?1")
	Collection<Complaint> findByStatus(String status);

	@Query("select c from Complaint c join c.reffered r where r=?1")
	Collection<Complaint> findByCustomer(Customer customer);
	
	@Query("select c from Complaint c order by c.status")
	Collection<Complaint> findOrderedByStatus();
}
