/* AdministratorController.java
 *
 * Copyright (C) 2013 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 * 
 */

package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import domain.Anecdote;

import services.AnecdoteService;

@Controller
@RequestMapping("/anecdote")
public class AnecdoteController extends AbstractController {
	//	Services --------------------------------------------------------------
	@Autowired
	private AnecdoteService anecdoteService ;
	// Constructors -----------------------------------------------------------
	
	public AnecdoteController() {
		super();
	}
		
	// Action-1 ---------------------------------------------------------------		

	@RequestMapping("/list")
	public ModelAndView listAll() {
		ModelAndView result;
		
		Collection<Anecdote> anecdotes;
		anecdotes=anecdoteService.findAll();
		
		result = new ModelAndView("anecdote/list");
		result.addObject("anecdotes",anecdotes);
		String requestURI="anecdote/list.do";
		result.addObject("requestURI", requestURI);
		
		return result;
	}
	
}