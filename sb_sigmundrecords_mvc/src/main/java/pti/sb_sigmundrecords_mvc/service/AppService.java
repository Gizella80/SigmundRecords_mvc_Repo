package pti.sb_sigmundrecords_mvc.service;



import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jdom2.JDOMException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pti.sb_sigmundrecords_mvc.db.Database;
import pti.sb_sigmundrecords_mvc.dto.AuthorDto;
import pti.sb_sigmundrecords_mvc.dto.AuthorListDto;
import pti.sb_sigmundrecords_mvc.dto.SavedAuthorDto;
import pti.sb_sigmundrecords_mvc.dto.SavedAuthorListDto;
import pti.sb_sigmundrecords_mvc.model.Author;
import pti.sb_sigmundrecords_mvc.model.SavedAuthor;
import pti.sb_sigmundrecords_mvc.parser.XMLParser;

@Service
public class AppService {
	
	private XMLParser parser;
	private Database db;
	
	
	
	@Autowired
	public AppService(XMLParser parser, Database db) {
		super();
		this.parser = parser;
		this.db = db;
	}

	public AuthorListDto getAuthorsAndOccurences(String orderBy, String yourText) throws JDOMException, IOException {
		
		AuthorListDto authorListDto = new AuthorListDto();
		
		List<Author> authors = parser.getAllAuthorsByPosition();
		
		/*for(int i = 0; i< authors.size(); i++) {
			System.out.println(authors.get(i)+" service");
		}*/
		
		
		
		for(int index = 0; index < authors.size(); index++) {
			
			Author currentAuthor = authors.get(index);
			
			if(yourText == null || currentAuthor.getName().contains(yourText)) {
				
				AuthorDto searchedAuthorDto = authorListDto.getAuthorDtoByName(currentAuthor.getName());
				
				if(searchedAuthorDto != null){
					searchedAuthorDto.incrementOccurence();;
					
				}else {
					AuthorDto  authorDto = new AuthorDto(currentAuthor.getName());
					authorListDto.add(authorDto);
					
				}
					
			}
			
		}
		
		if( (orderBy == null) || (orderBy.equals("abc")))  {
			authorListDto.orderByName();
		}
		else if( orderBy.equals("321")) {
			
			authorListDto.orderByOccurances();
			
		} 
		return authorListDto;
	}
	
	

	

	

	public boolean persistAuthorsByChoice(String choice) throws JDOMException, IOException {
		
		AuthorListDto authorListDto = getAuthorsAndOccurences(null,null);
		
		List<AuthorDto> authorDtoList = authorListDto.getAuthorListDto();
		List<SavedAuthor> savedAuthorsList = new ArrayList<>();
		
		for( int i = 0 ; i < authorDtoList.size(); i++) {
			
			AuthorDto currentAuthorDto = authorDtoList.get(i);
			
			SavedAuthor savedAuthor = new SavedAuthor();
			savedAuthor.setId(0);
			savedAuthor.setName(currentAuthorDto.getName());
			savedAuthor.setOccurence(Integer.toString(currentAuthorDto.getOccurence()));
			savedAuthorsList.add(savedAuthor);
		}
		boolean saved = false;
		if(choice.equals("html")) {
			 
			saved = parser.saveAuthorsToHtml(savedAuthorsList);
			
		}else if(choice.equals("database")) {
			
		 	saved = db.saveAuthorsToDatabase(savedAuthorsList);
		}
		
		
		
		return saved;
	}

	public SavedAuthorListDto readAuthorsFromFile(String read, String address) throws JDOMException, IOException {
		
		SavedAuthorListDto savedAuthorListDto = new SavedAuthorListDto();
		
		List<SavedAuthor> savedAuthors = new ArrayList<>();
		
		if(read.equals("database")) {
			
			savedAuthors = db.getAllSavedAuthors();
			
		}else if(read.equals("xml")) {
			
			savedAuthors = parser.getAllSavedAuthorsFromHtml(address);
		}
		
		if(savedAuthors.size() > 0) {
			for(int index = 0 ; index < savedAuthors.size(); index++) {
				 SavedAuthor currentSavedAuthor = savedAuthors.get(index);
				 SavedAuthorDto savedAuthorDto = new SavedAuthorDto(currentSavedAuthor.getName(),currentSavedAuthor.getOccurence());
				 
				 savedAuthorListDto.getSavedAuthorDtoList().add(savedAuthorDto);
			}
			
		}
		
		
		
		return savedAuthorListDto;
	}
	
	
}
