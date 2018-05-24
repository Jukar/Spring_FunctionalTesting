package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import repositories.ActorRepository;
import security.UserAccount;
import domain.Actor;

@Transactional
@Service
public class ActorService {
	@Autowired
	private ActorRepository actorRepository;
	
	public Actor findByUserAccount(UserAccount userAccount) {
		Actor result=actorRepository.findByUserAccount(userAccount);
		return result;
	}
	// @Query(select p from Profesor p where p.userAccount=?1)
			// Profesor findByUserAccount(UserAccont userAccount);

	public Collection<Actor> findAll() {
		Collection<Actor> result=actorRepository.findAll();
		return result;
	}
}
