package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import security.UserAccount;

import domain.Pilgrim;

@Repository
public interface PilgrimRepository extends JpaRepository<Pilgrim,Integer>{

	@Query("select p from Pilgrim p where p.userAccount=?1")
	Pilgrim findByUserAccount(UserAccount userAccount);
	
	//@Query("select p from Pilgrim p where p.name=?1 or p.surname=?1 or p.emailAddress=?1")
	@Query("select p from Pilgrim p where p.name like ?1 or p.surname like ?1 or p.emailAddress like ?1")
	Collection<Pilgrim> findPilgrimByKeyword(String keyword);
	
	@Query("select p from Pilgrim p order by p.registers.size desc")
	Collection<Pilgrim> listPilgrimRegistrationDesc();
	
	@Query("select p from Pilgrim p where p.anecdotes.size=(select max(pil.anecdotes.size) from Pilgrim pil)")
	Collection<Pilgrim> findPilgrimWhithMoreAnecdotes();
	
	@Query("select p from Pilgrim p where p.complaints.size=(select max(pil.complaints.size) from Pilgrim pil)")
	Collection<Pilgrim> findPilgimMoreComplaint();
}
