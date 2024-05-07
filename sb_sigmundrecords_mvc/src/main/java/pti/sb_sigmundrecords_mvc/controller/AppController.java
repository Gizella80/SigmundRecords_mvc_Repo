package pti.sb_sigmundrecords_mvc.controller;

import java.io.IOException;

import org.jdom2.JDOMException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestParam;

import pti.sb_sigmundrecords_mvc.dto.AuthorListDto;
import pti.sb_sigmundrecords_mvc.dto.SavedAuthorListDto;
import pti.sb_sigmundrecords_mvc.service.AppService;

@Controller
public class AppController {
	
	private AppService service;
	
	@Autowired
	public AppController(AppService service) {
		super();
		this.service = service;
	}
	
	@GetMapping("/author")
	public String showAuthors(
			Model model) throws JDOMException, IOException {
		
		AuthorListDto authorListDto = service.getAuthorsAndOccurences(null,null);
		
		
		model.addAttribute("authordtolist", authorListDto);
		
		return "author.html";
	}
	
	@GetMapping("/author/order")
	public String showAuthorsWithSelectedOrder(
							Model model,
							@RequestParam("orderby") String order
						) throws JDOMException, IOException {
		
		AuthorListDto authorListDto = service.getAuthorsAndOccurences(order,null);
		
		
		model.addAttribute("authordtolist", authorListDto);
		
		return "author.html";
	}
	
	@GetMapping("/author/search")
	public String searchForText(
			Model model,
			@RequestParam("yourtext") String yourText) throws JDOMException, IOException {
		
		AuthorListDto authorListDto = service.getAuthorsAndOccurences(null,yourText);
		
		model.addAttribute("authordtolist", authorListDto);
		
		
		return "author.html";
	}
	
	@GetMapping("/author/save")
	public String saveAuthorsByChoice(
			Model model,
			@RequestParam("choice") String choice) throws JDOMException, IOException {
		
		boolean saved = service.persistAuthorsByChoice(choice);
		String saveSuccess = "";
		
		if(saved == false) {
			saveSuccess = "Something went wrong!!";
			
		}else if( saved == true) {
			saveSuccess = "Successfully saved!!";
		}
		
		model.addAttribute("saveSuccess", saveSuccess);
		
		return "saveauthor.html";
	}
	
	@GetMapping("/author/read")
	public String showAuthorsFromFile(
			Model model,
			@RequestParam("read") String read,
			@RequestParam("address") String address) throws JDOMException, IOException {
		
		String targetPage = "author.html";
		String error = "ERROR Try Again!";
		SavedAuthorListDto savedAuthorListDto = service.readAuthorsFromFile(read, address);
		if(savedAuthorListDto != null) {
			
			targetPage = "readauthor.html";
		}else {
			targetPage = "author.html";
		}
		
		model.addAttribute("savedauthorlistdto", savedAuthorListDto);
		model.addAttribute("error", error);
		
		return targetPage;
	}
	
	
	
	
	
	

}
