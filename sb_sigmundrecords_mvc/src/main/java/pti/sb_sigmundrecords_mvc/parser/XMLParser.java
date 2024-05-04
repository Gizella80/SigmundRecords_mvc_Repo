package pti.sb_sigmundrecords_mvc.parser;



import java.util.ArrayList;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.Namespace;
import org.jdom2.input.SAXBuilder;
import org.springframework.stereotype.Repository;

import pti.sb_sigmundrecords_mvc.model.Author;

import java.io.File;
import java.io.IOException;



@Repository
public class XMLParser {

	public List<Author> getAllAuthorsByPosition() throws JDOMException, IOException {
		
		List<Author> authors = new ArrayList<>();
	
		SAXBuilder sb = new SAXBuilder();
		Document document = sb.build( new File("C:\\Users\\czegl\\OneDrive\\Dokumentumok\\sigmodRecords.xml"));
		
		Element rootElement = document.getRootElement();
		Namespace namespace = Namespace.getNamespace(rootElement.getNamespaceURI());
		
		List<Element> issueElements = rootElement.getChildren("issue",namespace);
		for(int index_issue = 0; index_issue < issueElements.size(); index_issue++) {
			Element currentIssueElement = issueElements.get(index_issue);
			
			Element articlesElement = currentIssueElement.getChild("articles", namespace);
			List<Element> articleElements = articlesElement.getChildren("article", namespace);
			for(int index_articleElements = 0 ; index_articleElements <  articleElements.size();index_articleElements++ ) {
				Element currentArticleElement = articleElements.get(index_articleElements);
				//Element currentTitleElement = currentArticleElement.getChild("title", namespace);
				//String currentTitle = currentTitleElement.getValue();
				
				Element authorsElement = currentArticleElement.getChild("authors",namespace);
				List<Element> authorElements = authorsElement.getChildren("author", namespace);
				for(int index_authorElements = 0; index_authorElements < authorElements.size(); index_authorElements++) {
					if(authorElements.size() >= 2) {
						Element currentAuthorElement = authorElements.get(index_authorElements);
						String authorsName = currentAuthorElement.getValue();
						String authorPosition = currentAuthorElement.getAttributeValue("position");
						Author author = new Author(authorsName,authorPosition);
						authors.add(author);
					}
				}	
			}
			
		}
		
		for(int i = 0; i < authors.size(); i++) {
			System.out.println(authors.get(i));
		}
		return authors;
	}

}
