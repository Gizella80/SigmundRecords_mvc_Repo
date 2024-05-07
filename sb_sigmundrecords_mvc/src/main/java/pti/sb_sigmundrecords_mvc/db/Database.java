package pti.sb_sigmundrecords_mvc.db;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.SelectionQuery;
import org.springframework.stereotype.Repository;

import pti.sb_sigmundrecords_mvc.model.SavedAuthor;

@Repository
public class Database {
	
	private SessionFactory sessionFactory;
	
	public Database(){
		
		Configuration config = new Configuration();
		config.configure();
		
		sessionFactory = config.buildSessionFactory();
	}

	public boolean saveAuthorsToDatabase(List<SavedAuthor> savedAuthorsList) {
		
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		boolean successfullySaved = false;
		
		for(int index = 0; index < savedAuthorsList.size(); index++) {
			
			SavedAuthor savedAuthor = savedAuthorsList.get(index);
			
			session.persist(savedAuthor);
			successfullySaved = true;
		}
		
		tx.commit();
		session.close();
		
		
		return  successfullySaved;
	}

	public List<SavedAuthor> getAllSavedAuthors() {
		
		List<SavedAuthor> savedAuthors = null;
		
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		SelectionQuery<SavedAuthor> query = session.createSelectionQuery("Select sa FROM SavedAuthor sa" , SavedAuthor.class);
		
		savedAuthors = query.getResultList();
		
		tx.commit();
		session.close();
		
		
		return savedAuthors;
	}
	
	
	


}
