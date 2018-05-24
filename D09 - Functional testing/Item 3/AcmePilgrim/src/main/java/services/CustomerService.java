package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import repositories.CustomerRepository;
import security.UserAccount;
import domain.Customer;

@Transactional
@Service
public class CustomerService {
	@Autowired
	private CustomerRepository customerRepository;
	
	public Customer findByUserAccount(UserAccount userAccount) {
		Customer result=customerRepository.findByUserAccount(userAccount);
		return result;
	}
	// @Query(select p from Profesor p where p.userAccount=?1)
			// Profesor findByUserAccount(UserAccont userAccount);

	public Collection<Customer> findAll() {
		Collection<Customer> result=customerRepository.findAll();
		return result;
	}
}
