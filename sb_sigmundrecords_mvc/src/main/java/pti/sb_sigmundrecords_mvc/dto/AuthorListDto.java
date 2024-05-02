package pti.sb_sigmundrecords_mvc.dto;

import java.util.ArrayList;
import java.util.List;



public class AuthorListDto {
	
	private List<AuthorDto> authorListDto;

	public AuthorListDto() {
		super();
		this.authorListDto = new ArrayList<>();
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
	
	public void order() {
		for(int currentIndex = 0; currentIndex < authorListDto.size(); currentIndex++) {
			AuthorDto currentAuthorDto = this.authorListDto.get(currentIndex);
			
			for(int nextIndex = 0; nextIndex < authorListDto.size(); nextIndex++) {
				AuthorDto nextAuthorDto = this.authorListDto.get(nextIndex);
				
				if(currentAuthorDto.getName().compareTo(nextAuthorDto.getName()) > 0) {
					this.authorListDto.set(currentIndex, nextAuthorDto);
					this.authorListDto.set(nextIndex, currentAuthorDto);
					currentIndex= -1;
					break;
				}
			}
			}
		}
	}
	
