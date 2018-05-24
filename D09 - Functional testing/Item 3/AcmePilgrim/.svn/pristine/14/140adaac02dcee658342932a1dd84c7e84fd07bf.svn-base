package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import domain.Complaint;
import utilities.AbstractTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={
		"classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml"})
@Transactional
@TransactionConfiguration(defaultRollback = false)
public class ComplaintServiceTest extends AbstractTest{
	@Autowired
	private ComplaintService complaintService;
	
	@Test
	public void testFindOne(){
		authenticate("customer0");
		Complaint result=complaintService.findOne(51);
		System.out.println("It works! -> "+result);
		authenticate(null);
	}
	
	@Test
	public void testFindAll(){
		authenticate("admin1");
		Collection<Complaint> result=complaintService.findAll();
		for(Complaint c:result){
			System.out.println(""+c.getTitle());
		}
		authenticate(null);
	}
//	@Test
//	public void testCancel(){
//		
//	}
//	@Test
//	public void testClose(){
//		
//	}
//	@Test
//	public void testSave(){
//		
//	}
}	
