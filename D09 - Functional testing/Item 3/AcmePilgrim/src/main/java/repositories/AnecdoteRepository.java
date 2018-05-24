package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Anecdote;

@Repository
public interface AnecdoteRepository extends JpaRepository<Anecdote,Integer>{

	@Query("select p.anecdotes from Pilgrim p where p.id=?1")
	Collection<Anecdote> findByPilgrimId(int pilgrimId);
	
	@Query("select count(a) from Anecdote a")
	Integer countAnecdotes();
	
	@Query("select avg(p.anecdotes.size) from Pilgrim p")
	Double avrAnecdotes();
}
