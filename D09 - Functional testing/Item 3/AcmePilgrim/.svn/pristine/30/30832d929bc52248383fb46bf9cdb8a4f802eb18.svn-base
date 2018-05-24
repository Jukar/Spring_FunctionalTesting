package services;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Administrator;
import domain.CreditCard;
import domain.Innkeeper;

import security.LoginService;
import security.UserAccount;
import utilities.AbstractTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={
		"classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml"})
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class AdministratorServiceTest extends AbstractTest{
	@Autowired
	private AdministratorService administratorService;
	
	//----------------------------------------------------
	// POSITIVE TEST CASES
	//----------------------------------------------------
	//A user who is authenticated as an administrator must be able to:
	//View his or her profile, which consists of the personal information 
	//that Acme Pilgrim, Inc. stores about him or her.
	//With an existing administrator, we will check that he can watch his own profile
	@Test
	public void testFindByUser(){
		authenticate("admin1");
		UserAccount userAccount=LoginService.getPrincipal();
		Administrator admin=administratorService.findByUserAccount(userAccount);
		Assert.isTrue(admin.getName().equals("adminName1"));
		authenticate(null);
	}
	
	//A user who is authenticated as an administrator must be able to:
	//Register a new administrator of the system.
	//With an existing administrator, we will try to register a new administrator instance
	@Test
	public  void testRegister(){
		authenticate("admin1");
		Administrator admin=administratorService.create();
		
		admin.setName("name");
		admin.setSurname("surname");
		admin.setContactPhone("954954954");
		admin.setEmailAddress("Correo@gmail.com");
		
		administratorService.save(admin);
		authenticate(null);
	}
	
	
	//----------------------------------------------------
	// NEGATIVE TEST CASES
	//----------------------------------------------------
	//A user who is authenticated as an administrator must be able to:
	//View his or her profile, which consists of the personal information 
	//that Acme Pilgrim, Inc. stores about him or her.
	//With an existing administrator, we will try to obtain a wrong administrator and catch the exception
	@Test(expected=IllegalArgumentException.class)
	public void testFindByUserException(){
		authenticate("admin2");
		UserAccount userAccount=LoginService.getPrincipal();
		Administrator admin=administratorService.findByUserAccount(userAccount);
		Assert.isTrue(admin.getName().equals("adminName1"));
		authenticate(null);
	}
	
	//A user who is authenticated as an administrator must be able to:
	//Register a new administrator of the system.
	//With an existing pilgrim, we will try to register a new administrator instance and
	//capture the exception.
	@Test(expected=IllegalArgumentException.class)
	public  void testRegisterException(){
		authenticate("customer0");
		Administrator admin=administratorService.create();
		
		admin.setName("name");
		admin.setSurname("surname");
		admin.setContactPhone("954954954");
		admin.setEmailAddress("Correo@gmail.com");
		
		administratorService.save(admin);
		authenticate(null);
	}
}
