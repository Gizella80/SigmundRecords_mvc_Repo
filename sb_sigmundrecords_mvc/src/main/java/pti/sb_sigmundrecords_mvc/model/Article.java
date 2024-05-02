package pti.sb_sigmundrecords_mvc.model;

import java.util.List;

public class Article {
	
	private String title;
	private List<Author> authors;
	
	public Article(String title, List<Author> authors) {
		super();
		this.title = title;
		this.authors = authors;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<Author> getAuthors() {
		return authors;
	}

	public void setAuthors(List<Author> authors) {
		this.authors = authors;
	}

	
	
	

}
