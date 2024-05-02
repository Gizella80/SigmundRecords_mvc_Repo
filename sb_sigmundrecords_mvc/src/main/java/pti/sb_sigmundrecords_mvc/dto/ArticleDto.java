package pti.sb_sigmundrecords_mvc.dto;

import java.util.List;

public class ArticleDto {
	
	private String title;
	private List<AuthorDto> authorDtos;
	
	
	public ArticleDto(String title, List<AuthorDto> authorDtos) {
		super();
		this.title = title;
		this.authorDtos = authorDtos;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<AuthorDto> getAuthorDtos() {
		return authorDtos;
	}

	public void setAuthorDtos(List<AuthorDto> authorDtos) {
		this.authorDtos = authorDtos;
	}

	@Override
	public String toString() {
		return "ArticleDto [title=" + title + ", authorDtos=" + authorDtos + "]";
	}
	
}
