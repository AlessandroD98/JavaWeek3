package Prestito;

import javax.persistence.EntityManager;

import JpaUtil.JpaUtil;

public class UtenteDAO {

static  EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
	
	public static Utente findUtente(Long i) {
		Utente u = null;
		try {
			em.getTransaction().begin();
			u = em.find(Utente.class, i);
			em.getTransaction().commit();
		} catch (Exception e) {
			System.out.println("Error " + e);
		}
		return u;
	}

}
