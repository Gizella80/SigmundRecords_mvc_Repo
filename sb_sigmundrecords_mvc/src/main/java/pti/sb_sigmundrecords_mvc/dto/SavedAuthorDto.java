package pti.sb_sigmundrecords_mvc.dto;

public class SavedAuthorDto {
	
	private String name;
	private String occurence;
	
	public SavedAuthorDto(String name, String occurence) {
		super();
		this.name = name;
		this.occurence = occurence;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOccurence() {
		return occurence;
	}

	public void setOccurence(String occurence) {
		this.occurence = occurence;
	}

	@Override
	public String toString() {
		return "SavedAuthorDto [name=" + name + ", occurence=" + occurence + "]";
	}
	
	
}
