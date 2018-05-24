package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import domain.Invoice;
import domain.Pilgrim;


@Repository
public interface InvoiceRepository extends JpaRepository<Invoice,Integer>{
	@Query("select i from Invoice i where i.booking.pilgrim.id=?1")
	Collection<Invoice> findByPilgrim(int id);
	
	@Query("select i from Invoice i where i.booking.lodge.innkeeper.id=?1")
	Collection<Invoice> findByKeeper(int id);

	@Query("select i from Invoice i where i.paidMoment is null and i.booking.pilgrim.id=?1")
	Collection<Invoice> findNotPaidByPilgrim(int pilgrimId);
	
	@Query("select i from Invoice i where i.paidMoment is null and i.booking.lodge.innkeeper.id=?1")
	Collection<Invoice> findNotPaidByKeeper(int keeperId);

}
