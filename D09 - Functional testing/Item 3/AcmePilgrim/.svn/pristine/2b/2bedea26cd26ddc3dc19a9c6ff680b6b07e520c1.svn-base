package controllers.innkeeper;

import java.util.Collection;
import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import controllers.AbstractController;

import security.LoginService;
import security.UserAccount;
import services.BookingService;
import services.InnkeeperService;
import services.InvoiceService;
import domain.Booking;
import domain.Innkeeper;
import domain.Invoice;
import domain.Landmark;

@Controller
@RequestMapping("/invoice/innkeeper")
public class InvoiceInkeeperController extends AbstractController{
	// Services------------------------------------------------------------
	@Autowired
	private InvoiceService invoiceService;
	@Autowired
	private InnkeeperService innkeeperService;
	@Autowired
	private BookingService bookingService;
	
	// Listing-------------------------------------------------------------
	@RequestMapping("/list")
	public ModelAndView listAll() {
		ModelAndView result;
		UserAccount usAc=LoginService.getPrincipal();
		Innkeeper you=innkeeperService.findByUserAccount(usAc);
		
		Collection<Invoice> invoices;
		invoices=invoiceService.findByKeeper(you.getId());
		String requestURI="invoice/innkeeper/list.do";
		
		result = new ModelAndView("invoice/innkeeper/list");
		result.addObject("invoices",invoices);
		result.addObject("requestURI", requestURI);
		return result;
	}
	@RequestMapping("/listUnpaid")
	public ModelAndView listUnpaid() {
		ModelAndView result;
		UserAccount usAc=LoginService.getPrincipal();
		Innkeeper you=innkeeperService.findByUserAccount(usAc);
		
		Collection<Invoice> invoices;
		invoices=invoiceService.findByKeeperNotPaid(you.getId());
		String requestURI="invoice/innkeeper/list.do";
		
		result = new ModelAndView("invoice/innkeeper/list");
		result.addObject("invoices",invoices);
		result.addObject("requestURI", requestURI);
		return result;
	}
	// Creation -----------------------------------------------------------
	@RequestMapping(value= "/create", method = RequestMethod.GET )
	public ModelAndView create(){
		ModelAndView result;
		Invoice invoice;
	
		invoice=invoiceService.create();
		Assert.notNull(invoice);
		result=createEditModelAndView(invoice);
				
		return result;
	}
	// Save ---------------------------------------------------------------
	@RequestMapping(value="/edit",method=RequestMethod.POST,params="save")		
	public ModelAndView save(@Valid Invoice invoice, BindingResult binding){
		ModelAndView result;
		
		if(binding.hasErrors()){
			result=createEditModelAndView(invoice);
		} else{
			try{
				invoiceService.save(invoice);
				result=new ModelAndView("redirect:list.do");
			} catch(Throwable oops){
				result=createEditModelAndView(invoice,"invoice.commit.error");
			}
		}
		return result;
	}
    // Edition ------------------------------------------------------------
	// Ancillary methods --------------------------------------------------
	protected ModelAndView createEditModelAndView(Invoice invoice){
		ModelAndView result;
		result=createEditModelAndView(invoice,null);		
		return result;
	}
			
	protected ModelAndView createEditModelAndView(Invoice invoice, String message){
		ModelAndView result;
		Collection<Booking> bookings=bookingService.findAllWithoutInvoice();
		
		result=new ModelAndView("invoice/innkeeper/edit");
		result.addObject("invoice", invoice);
		result.addObject("bookings",bookings);
		result.addObject("requestURI","invoice/pilgrim/edit.do");
		
		return result;
	}
}
