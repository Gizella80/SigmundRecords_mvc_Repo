package pti.sb_sigmundrecords_mvc.parser;



import java.util.ArrayList;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.Namespace;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import org.springframework.stereotype.Repository;

import pti.sb_sigmundrecords_mvc.model.Author;
import pti.sb_sigmundrecords_mvc.model.SavedAuthor;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import java.io.FileNotFoundException;


@Repository
public class XMLParser {

	public List<Author> getAllAuthorsByPosition() throws JDOMException, IOException {
		
		List<Author> authors = new ArrayList<>();
		try {
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
			
			/*for(int i = 0; i < authors.size(); i++) {
				System.out.println(authors.get(i));
			}*/
		}catch(FileNotFoundException e) {}
		return authors;
	}

	public boolean saveAuthorsToHtml(List<SavedAuthor> savedAuthorsList) throws IOException {
		
		boolean savedSuccessfull = false;
		
		FileWriter writer = new FileWriter("author.xml");
		XMLOutputter outputter = new XMLOutputter(Format.getPrettyFormat());
		

		Document doc = new Document();
		
		
		Element rootElement = new Element("authors");
		
		
		for(int i= 0; i <savedAuthorsList.size(); i++) {
			
			SavedAuthor savedAuthor = savedAuthorsList.get(i);
			
			Element authorElement = new Element("author");
			authorElement.setText(savedAuthor.getName());
			authorElement.setAttribute("occurence", savedAuthor.getOccurence());
			rootElement.addContent(authorElement);
			savedSuccessfull = true;
			
		}
		
		doc.setRootElement(rootElement);
		
		
		
		outputter.output(doc, writer);
		writer.close();
		
		return savedSuccessfull;

	}

	public List<SavedAuthor> getAllSavedAuthorsFromHtml(String address) throws JDOMException, IOException {
		
		List<SavedAuthor> savedAuthors = new ArrayList<>();
		
		SAXBuilder sb = new SAXBuilder();
		Document document = sb.build( new File(address));
		
		
		
		Element rootElement = document.getRootElement();
		
		//Element authorsElement = rootElement.getChild("authors");
		
		List<Element> authorsElements = rootElement.getChildren("author");
		for(int index_authorsElements = 0; index_authorsElements < authorsElements.size(); index_authorsElements++) {
			
			Element currentAuthorElement = authorsElements.get(index_authorsElements);
			String occurence = currentAuthorElement.getAttributeValue("occurence");
			String name = currentAuthorElement.getValue();
			
			SavedAuthor savedAuthor = new SavedAuthor();
			savedAuthor.setName(name);
			savedAuthor.setOccurence(occurence);
			savedAuthors.add(savedAuthor);
		}
		
		
		return savedAuthors;
	}

}
