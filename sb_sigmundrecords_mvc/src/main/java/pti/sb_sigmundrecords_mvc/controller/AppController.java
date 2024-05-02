package pti.sb_sigmundrecords_mvc.controller;

import java.io.IOException;

import org.jdom2.JDOMException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import pti.sb_sigmundrecords_mvc.dto.AuthorListDto;
import pti.sb_sigmundrecords_mvc.service.AppService;

@Controller
public class AppController {
	
	private AppService service;
	
	@Autowired
	public AppController(AppService service) {
		super();
		this.service = service;
	}
	
	@GetMapping("/author/{position}")
	public String showAuthorsByPosition(
			Model model,
			@PathVariable("position") int position) throws JDOMException, IOException {
		
		AuthorListDto authorListDto = service.getAuthorsByPosition(position);
		for(int i = 0; i < authorListDto.getAuthorListDto().size(); i++) {
			System.out.println(authorListDto.getAuthorListDto().get(i));
		}
		
		
		model.addAttribute("authordtolist", authorListDto);
		
		return "author.html";
	}

}
