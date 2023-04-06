package ElementiCatalogo;

import javax.persistence.EntityManager;

import JpaUtil.JpaUtil;

public class ElementoCatalogoDAO {

	static  EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
	
	
	public static ElementoCatalogo findElCat (Long l) {
		ElementoCatalogo el = null;
		try {
			em.getTransaction().begin();
			el = em.find(ElementoCatalogo.class, l);
			em.getTransaction().commit();
		} catch (Exception e) {
			System.out.println("Error " + e);
			}
		return el;
	}
}
