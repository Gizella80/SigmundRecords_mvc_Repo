package pti.sb_sigmundrecords_mvc.service;



import java.io.IOException;
import java.util.List;

import org.jdom2.JDOMException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pti.sb_sigmundrecords_mvc.dto.AuthorDto;
import pti.sb_sigmundrecords_mvc.dto.AuthorListDto;
import pti.sb_sigmundrecords_mvc.model.Author;
import pti.sb_sigmundrecords_mvc.parser.XMLParser;

@Service
public class AppService {
	
	private XMLParser parser;
	
	@Autowired
	public AppService(XMLParser parser) {
		super();
		this.parser = parser;
	}

	public AuthorListDto getAuthorsAndOccurences(String orderBy) throws JDOMException, IOException {
		
		AuthorListDto authorListDto = new AuthorListDto();
		
		List<Author> authors = parser.getAllAuthorsByPosition();
		
		for(int i = 0; i< authors.size(); i++) {
			System.out.println(authors.get(i)+" service");
		}
		
		
		for(int index = 0; index < authors.size(); index++) {
			
			Author currentAuthor = authors.get(index);
			
			AuthorDto searchedAuthorDto = authorListDto.getAuthorDtoByName(currentAuthor.getName());
			if(searchedAuthorDto != null){
				searchedAuthorDto.setOccurence();
				
			}else {
				AuthorDto  authorDto = new AuthorDto(currentAuthor.getName());
				authorListDto.add(authorDto);
			}
					
			
			
		}
		
		if( (orderBy == null) || (orderBy.equals("abc")) ) {
			authorListDto.orderByName();
		}
		else if( orderBy.equals("321") ) {
			authorListDto.orderByOccurances();
		}	
		
		
		
		
		//authorListDto.order();
		
		return authorListDto;
	}

	
	
	
}
