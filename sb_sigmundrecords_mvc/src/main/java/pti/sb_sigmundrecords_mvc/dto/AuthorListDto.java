package pti.sb_sigmundrecords_mvc.dto;

import java.util.ArrayList;
import java.util.List;


public class AuthorListDto {
	
	private List<AuthorDto> authorListDto;

	

	public AuthorListDto() {
		super();
		authorListDto = new ArrayList<>();
	}

	
	
	public List<AuthorDto> getAuthorListDto() {
		return authorListDto;
	}



	public void setAuthorListDto(List<AuthorDto> authorListDto) {
		this.authorListDto = authorListDto;
	}



	public void add(AuthorDto authorDto) {
		
	this.authorListDto.add(authorDto);
	}
	
	

	public AuthorDto getAuthorDtoByName(String searchedAuthorName) {
		AuthorDto authorDto = null;
		
		
		for(int index = 0; index < authorListDto.size(); index++) {
		
			AuthorDto currentAuthorDto = authorListDto.get(index);
			if( currentAuthorDto.getName().equals(searchedAuthorName) ) {
				
				authorDto = currentAuthorDto;
				break;
			}
		}
		return authorDto;
	}
	
	public void orderByName() {
		for(int currentIndex = 0; currentIndex < authorListDto.size(); currentIndex++) {
			AuthorDto currentAuthorDto = authorListDto.get(currentIndex);
			
			for(int nextIndex = currentIndex + 1; nextIndex < authorListDto.size(); nextIndex++) {
				AuthorDto nextAuthorDto = authorListDto.get(nextIndex);
				
				if(currentAuthorDto.getName().compareTo(nextAuthorDto.getName()) > 0) {
					authorListDto.set(currentIndex, nextAuthorDto);
					authorListDto.set(nextIndex, currentAuthorDto);
					currentIndex= -1;
					break;
					}
				}
			}
		}

	public void orderByOccurances() {
		
			for(int currentIndex = 0; currentIndex < authorListDto.size(); currentIndex++) {
				AuthorDto currentAuthorDto = authorListDto.get(currentIndex);
				
				for(int nextIndex = currentIndex + 1; nextIndex < authorListDto.size(); nextIndex++) {
				AuthorDto nextAuthorDto = authorListDto.get(nextIndex);
					
					if(currentAuthorDto.getOccurence() < nextAuthorDto.getOccurence()) {
						authorListDto.set(currentIndex, nextAuthorDto);
						authorListDto.set(nextIndex, currentAuthorDto);
						currentIndex= -1;
						break;
						}
					}
				}
			}
		

	}
	
