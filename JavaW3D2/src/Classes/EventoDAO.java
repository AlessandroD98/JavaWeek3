package Classes;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;


import utils.JpaUtil;

public class EventoDAO {

	static EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
	
public static void addEvent(Evento e) {

	em.getTransaction().begin();
	em.persist(e);
	em.getTransaction().commit();
	System.out.println("Evento salvato nel DB!");
	}
	
public static Evento getById(Long id) {
	
	em.getTransaction().begin();
	Evento e = em.find(Evento.class, id);
	em.getTransaction().commit();
	return e;
}

public static void delete(Evento e) {
	em.getTransaction().begin();
	em.remove(e);
	em.getTransaction().commit();
	System.out.println("Evento eliminato dal DB!");
}

public static void updateEvent(Evento e) {
	
	em.getTransaction().begin();
	em.merge(e);
	em.getTransaction().commit();
	System.out.println("Evento modificato nel DB!");
	}

public static List<Evento> findAllEvents() {
	
	Query q = em.createNamedQuery("Evento.findAll");
	return q.getResultList();
}

}
