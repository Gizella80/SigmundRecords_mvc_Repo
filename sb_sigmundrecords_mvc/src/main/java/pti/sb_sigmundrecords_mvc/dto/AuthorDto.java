package pti.sb_sigmundrecords_mvc.dto;

public class AuthorDto {
	
	private String name;
	private int occurence;
	
	
	public AuthorDto(String name) {
		super();
		this.name = name;
		this.occurence = 1;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getOccurence() {
		return occurence;
	}
	
	public void setOccurence(int occurence) {
		this.occurence = occurence;
	}

	public void incrementOccurence() {
		this.occurence++;
		
	}
	
	@Override
	public String toString() {
		return "AuthorDto [name=" + name + ", occurence=" + occurence + "]";
	}
	

}
