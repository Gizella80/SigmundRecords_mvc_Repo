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

	public AuthorListDto getAuthorsByPosition(int position) throws JDOMException, IOException {
		
		AuthorListDto authorListDto = new AuthorListDto();
		
		List<Author> authors = parser.getAllAuthorsByPosition(position);
		
		for(int i = 0; i< authors.size(); i++) {
			System.out.println(authors.get(i));
		}
		
		
		for(int index = 0; index < authors.size(); index++) {
			
			Author currentAuthor = authors.get(index);
			
			AuthorDto authorDto = new AuthorDto( 
					currentAuthor.getName());
			for(int index_ofDtoList = 0; index_ofDtoList < authorListDto.getAuthorListDto().size();index_ofDtoList++) {
				if(currentAuthor.getName().equals(authorListDto.getAuthorListDto().get(index_ofDtoList).getName())){
					authorListDto.getAuthorListDto().get(index_ofDtoList).setOccurence();
					
				}else {
					authorListDto.add(authorDto);
				}
			}
		}
		
		authorListDto.order();
		
		return authorListDto;
	}
	
	
}
