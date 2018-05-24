package services;

import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.DuplicateMappingException;
import org.hibernate.annotations.common.AssertionFailure;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.util.AssertionErrors;
import org.springframework.util.Assert;

import repositories.ComplaintRepository;

import com.mchange.util.DuplicateElementException;

import domain.Complaint;
import utilities.AbstractTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml" })
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class ComplaintServiceTest extends AbstractTest {
	@Autowired
	private ComplaintService complaintService;

	// A user who is authenticated as a pilgrim must be able to:
	// Create a complaint, which is immediately set as open, as a pilgrim
	@Test
	public void testCreatePilgrim() {
		authenticate("customer1");
		Complaint complaint = complaintService.create();
		complaint.setTitle("aaaaaaa");
		complaint.setDescription("asfasdsaas");
		complaint.setResolution("asfafsadsads");
		complaintService.saveCreate(complaint);
		authenticate(null);
	}

	// A user who is authenticated as a pilgrim must be able to:
	// Create a complaint, which is immediately set as open.
	// Trying to create a complaint with an Inkeeper, it must be fail
	@Test(expected = IllegalArgumentException.class)
	public void testCreateInkeeper() {
		authenticate("keeper1");
		Complaint complaint = complaintService.create();
		complaint.setTitle("adsadsadadadsa");
		complaint.setDescription("asfasdsaas");
		complaint.setResolution("asfafsadsads");
		complaintService.saveCreate(complaint);
		authenticate(null);
	}

	// A user who is authenticated as a pilgrim must be able to:
	// Create a complaint, which is immediately set as open.
	// Trying with an Admin, it must be fail
	@Test(expected = IllegalArgumentException.class)
	public void testCreateAdmin() {
		authenticate("admin1");
		Complaint complaint = complaintService.create();
		complaint.setTitle("aaaaaaa");
		complaint.setDescription("asfasdsaas");
		complaint.setResolution("asfafsadsads");
		complaintService.saveCreate(complaint);
		authenticate(null);
	}

	// Comment on his or her complaints. -->Discuss test

	// - A user who is authenticated as a pilgrim must be able to:
	// Cancel a complaint that he or she's created.
	// Once a complaint is cancelled, no further discussions are accepted
	// Cancel one that he or she's not created
	@Test(expected = IllegalArgumentException.class)
	public void testCancelPilgrimNotCreated() {
		authenticate("customer0");
		Complaint result = complaintService.findOne(69);
		complaintService.cancel(result);
		authenticate(null);
	}

	// - A user who is authenticated as a pilgrim must be able to:
	// Cancel a complaint that he or she's created.
	// Once a complaint is cancelled, no further discussions are accepted
	// Cancell one that he or she's created
	@Test
	public void testCancelPilgrimCreated() {
		authenticate("customer1");
		Complaint result = complaintService.findOne(69);
		complaintService.cancel(result);
		authenticate(null);
	}

	// A user who is authenticated as an administrator must be able to:
	// -List the complaints in the system.
	@Test
	public void testFindAllAdmin() {
		authenticate("admin1");
		Collection<Complaint> result = complaintService.findAll();
		Assert.isTrue(result.size() == 5);
		authenticate(null);
	}

	// A user who is authenticated as an administrator must be able to:
	// -List the complaints in the system.
	// Trying with a Pilgrim, it must fail
	@Test(expected = IllegalArgumentException.class)
	public void testFindAllPilgrim() {
		authenticate("customer1");
		Collection<Complaint> result = complaintService.findAll();
		authenticate(null);
	}

	// A user who is authenticated as an administrator must be able to:
	// -Select a complaint so that he or she can handle it.
	// Obviously, the selected complaint cannot be already assigned to
	// another administrator.
	@Test
	public void testHandleAdmin() {
		authenticate("admin1");
		Complaint result = complaintService.findOne(72);
		complaintService.handle(result);
		authenticate(null);
	}

	// A user who is authenticated as an administrator must be able to:
	// -Select a complaint so that he or she can handle it.
	// Obviously, the selected complaint cannot be already assigned to
	// another administrator.
	// Trying to assing to another administrator.
	@Test(expected = IllegalArgumentException.class)
	public void testHandleAdminHandled() {
		authenticate("admin1");
		Complaint result = complaintService.findOne(69);
		complaintService.handle(result);
		authenticate(null);
	}

	// A user who is authenticated as an administrator must be able to:
	// -Select a complaint so that he or she can handle it.
	// Obviously, the selected complaint cannot be already assigned to
	// another administrator.
	// The selected complaint cannot be handle by a pilgrim.
	@Test(expected = IllegalArgumentException.class)
	public void testHandlePilgrim() {
		authenticate("customer1");
		Complaint result = complaintService.findOne(72);
		complaintService.handle(result);
		authenticate(null);
	}

	// -Comment on a complaint that he or she handles.-->Test Discuss

	// A user who is authenticated as an administrator must be able to:
	// -Close a complaint that he or she handles. Once a complaint is closed, no
	// further
	// discussions are accepted.
	@Test
	public void testCloseHandled() {
		authenticate("admin2");
		Complaint result = complaintService.findOne(69);
		complaintService.close(result);
		authenticate(null);
	}

	// A user who is authenticated as an administrator must be able to:
	// -Close a complaint that he or she handles. Once a complaint is closed, no
	// further
	// discussions are accepted.
	// The selected complaint cannot be close if you don't handle it.
	@Test(expected = IllegalArgumentException.class)
	public void testCloseNotHandled() {
		authenticate("admin1");
		Complaint result = complaintService.findOne(69);
		complaintService.close(result);
		authenticate(null);
	}

	// A user who is authenticated as an administrator must be able to:
	// Display a dashboard with the following information:
	// -List the ratio of complaints per pilgrim.
	@Test
	public void testComplainRatioAdmin() {
		authenticate("admin1");
		Collection<Object[]> result = complaintService.pilgrimsRateComplaints();
		authenticate(null);
	}

	// A user who is authenticated as an administrator must be able to:
	// Display a dashboard with the following information:
	// -List the ratio of complaints per pilgrim.
	// Trying to do as pilgrim
	@Test(expected = IllegalArgumentException.class)
	public void testComplainRatioPilgrim() {
		authenticate("pilgrim1");
		Collection<Object[]> result = complaintService.pilgrimsRateComplaints();
		authenticate(null);
	}

	// A user who is authenticated as an administrator must be able to:
	// Display a dashboard with the following information:
	// -List the pilgrim/s who have created more complaints. -->PilgrimTest

	// - A user who is authenticated as an innkeeper or pilgrim must be able to:
	// List the complaints against him or her.
	// Trying with a Pilgrim
	@Test
	public void testFindByCustomerPilgrim() {
		authenticate("customer1");
		Collection<Complaint> result = complaintService.findByCustomer();
		authenticate(null);
	}

	// - A user who is authenticated as an innkeeper or pilgrim must be able to:
	// List the complaints against him or her.
	// Trying with an inkeeper
	@Test
	public void testFindByCustomerInkeeper() {
		authenticate("keeper1");
		Collection<Complaint> result = complaintService.findByCustomer();
		authenticate(null);
	}

	// - A user who is authenticated as an innkeeper or pilgrim must be able to:
	// List the complaints against him or her.
	// Trying with an admin
	@Test(expected = IllegalArgumentException.class)
	public void testFindByCustomerAdmin() {
		authenticate("admin1");
		Collection<Complaint> result = complaintService.findByCustomer();
		authenticate(null);
	}

	// - A user who is authenticated as an innkeeper must be able to:
	// List the complaints against him or her.
	// Trying with an Inkeeper
	@Test
	public void testFindReferedToInkeeper() {
		authenticate("keeper1");
		Collection<Complaint> result = complaintService.findByCustomer();
		authenticate(null);
	}

	// - A user who is authenticated as a pilgrim must be able to:
	// List the complaints against him or her.
	// Trying with a pilgrim
	@Test
	public void testFindReferedToPilgrim() {
		authenticate("customer1");
		Collection<Complaint> result = complaintService.findByCustomer();
		Assert.isTrue(result.size() == 1);
		authenticate(null);
	}

	// - A user who is authenticated as a pilgrim must be able to:
	// List the complaints against him or her.
	// Trying with an admin
	@Test(expected = IllegalArgumentException.class)
	public void testFindReferedToAdmin() {
		authenticate("admin1");
		Collection<Complaint> result = complaintService.findByCustomer();
		authenticate(null);
	}

	// - A user who is authenticated as an administrator must be able to:
	// Display a dashboard with the following information:
	// List the complaints grouped by status.
	// Trying with an admin
	@Test
	public void testFindComplaintGroupedByStatus() {
		authenticate("admin1");
		Collection<Complaint> result = complaintService.findOrderedByStatus();
		authenticate(null);
	}
	// - A user who is authenticated as an administrator must be able to:
	// Display a dashboard with the following information:
	// List the complaints grouped by status.
	// Trying with a pilgrim
	@Test(expected = IllegalArgumentException.class)
	public void testFindComplaintGroupedByStatusPilgrim() {
		authenticate("customer0");
		Collection<Complaint> result = complaintService.findOrderedByStatus();
		authenticate(null);
	}
}
