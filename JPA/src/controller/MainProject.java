package controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;


import model.User;

public class MainProject {

	static EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPA"); //Mi collego al DB tramite il "nome" inserito nel persistance
	static EntityManager em = emf.createEntityManager();
	
	public static void main(String[] args) {
		
		//Creo un User
		
		User u = new User();
		u.setName("Mario");
		u.setLastname("Rossi");
		u.setEmail("m.rossi@example.com");
		
		try {
			
			//addUser(u);
			User t = findUser(1l); //Torna un User
			System.out.println(t);
			
			t.setEmail("test@example.com"); //Modifico una proprietà
			updateUser(t); //Modifica un User
			System.out.println(t);
			
			removeUser(t); //Rimuove un User
			
			List<User> lista = findAllUsers();
			lista.forEach(au -> System.out.println(au)); //Print di tutti gli Users
			
		}catch(Exception e) {
			
		}finally {
			//Chiusura della transazione nel finally per chiudere sempre in caso di errore
			em.close();
			emf.close();
		}

	}

	public static void addUser(User u) {
		
		em.getTransaction().begin(); // Apro la transazione
		em.persist(u); //Indico cosa salvare
		em.getTransaction().commit(); //Salvo nel DB
		System.out.println("Utente salvatao!");
		
	}

	public static User findUser(Long id) {
		
		em.getTransaction().begin();
		User u = em.find(User.class, id); // Seleziono il tipo di oggetto che voglio tornare nella variabile e la proprietà che voglio andare a trovare
		em.getTransaction().commit();
		return u;
	}

	public static void updateUser(User u) {
		
		em.getTransaction().begin();
		em.merge(u); // Va a sovrascrivere l'elemento selezionato nell () cambiando solo le proprietà che non coincidono
		em.getTransaction().commit();
		System.out.println("Utente modificato nel DB!");
	}
	
	public static void removeUser(User u) {
		
		em.getTransaction().begin();
		em.remove(u);
		em.getTransaction().commit();
		System.out.println("Utente eliminato nel DB!");
	}
	
	public static List<User> findAllUsers() {
		
		Query q = em.createNamedQuery("User.findAll"); //Ritorna una List di User
		return q.getResultList();
	}
	
}
