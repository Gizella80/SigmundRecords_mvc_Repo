package pti.sb_sigmundrecords_mvc.dto;

import java.util.ArrayList;
import java.util.List;

public class SavedAuthorListDto {
	
	private List<SavedAuthorDto> savedAuthorDtoList;

	public SavedAuthorListDto() {
		super();
		this.savedAuthorDtoList = new ArrayList<>();;
	}

	public List<SavedAuthorDto> getSavedAuthorDtoList() {
		return savedAuthorDtoList;
	}

	public void setSavedAuthorDtoList(List<SavedAuthorDto> savedAuthorDtoList) {
		this.savedAuthorDtoList = savedAuthorDtoList;
	}
	
	public void add(SavedAuthorDto savedAuthorDto) {
		
		this.savedAuthorDtoList.add(savedAuthorDto);
	}
	
	@Override
	public String toString() {
		return "SavedAuthorListDto [savedAuthorDtoList=" + savedAuthorDtoList + "]";
	}
	
	
}
