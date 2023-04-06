package Prestito;

import javax.persistence.EntityManager;

import JpaUtil.JpaUtil;

public class ElementoInPrestitoDAO {

	static  EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
	
	public static void saveElInPres (ElementoInPrestito el) {
		try {
			em.getTransaction().begin();
			em.persist(el);
			em.getTransaction().commit();
			System.out.println("Ricoradti di restituire l'articolo entro un mese!");
		} catch (Exception e) {
			System.out.println("Errore nel salvataggio " + e);
		}
	}
	
}
