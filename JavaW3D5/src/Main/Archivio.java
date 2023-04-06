package Main;

import java.util.List;
import java.util.Scanner;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import ElementiCatalogo.ElementoCatalogo;
import ElementiCatalogo.ElementoCatalogoDAO;
import ElementiCatalogo.Libro;
import ElementiCatalogo.Rivista;
import JpaUtil.JpaUtil;
import Prestito.ElementoInPrestito;
import Prestito.ElementoInPrestitoDAO;
import Prestito.Utente;
import Prestito.UtenteDAO;


public class Archivio {

	public static Scanner s = new Scanner(System.in);
	
	//static EntityManagerFactory emf = Persistence.createEntityManagerFactory("JavaW3D5");
	static EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
	
	public static void main(String[] args) {
		
		GestioneCatalogo();
	}

	public static void GestioneCatalogo()  {
		
		while(true) {
				System.out.println("Seleziona una delle seguenti opzioni:"
						+ "\n 1 AGGIUNGI ELEMENTO "
						+ "\n 2 ELIMINA ELEMENTO(ISBN) "
						+ "\n 3 RICERCA UN ELEMENTO "
						+ "\n 4 VISUALIZZA CATALOGO "
						+ "\n 5 GESTIONE UTENTI "
						+ "\n 6 PRENDI IN PRESTITO UN ELEMENTO "
						+ "\n 7 RICERCA DEGLI ELEMENTI IN PRESTITO "
						+ "\n 8 PRESTITI SCADUTI/NON RESTITUITI ");
				int scelta = s.nextInt();
				s.nextLine();
				
				switch (scelta) {
				case 1:
					AggiungiElemento();
					break;
				case 2:
					if(isCatalogoEmpty()  == 0) {	
						System.out.println("Non ci sono elemnti da eliminare, inizia aggiungendone uno! \n");
					} else {       		
						removeByISBN();
					}
					break;
				case 3:
					if(isCatalogoEmpty() == 0) {	
						System.out.println("Non ci sono elemnti da cercare, inizia aggiungendone uno! \n");
					} else {
						ricerca(s);
					}
					break;
				case 4 :
					if(isCatalogoEmpty() == 0) {
						System.out.println("Carica o aggiungi un elemento per visualizzare il catalogo");
					} else {
						List<ElementoCatalogo> res = findAllElements();
						for(ElementoCatalogo e : res) {System.out.println(ElementoCatalogo.toString(e));
						}
					}
					break;
				case 5:
					gestionUtenti();
					break;
				case 6:
					prendiInprestito();
					break;
				case 7:
					visualizzaElInPrestito();
					break;
				case 8:
					visualizzaPrestitiScaduti();
					break;
				default:
					System.out.println("Scelta non valida.");
				}
			}
		}
    
	
		
		  public static void AggiungiUtente() {
		  
		  em.getTransaction().begin(); 
		  em.persist(new Utente(true));
		  em.getTransaction().commit(); 
		  System.out.println("Utente creato!");
		  
		  }
		 
	
	public static void AggiungiElemento() {
		
		String exit = "E";
		while(exit.equals("E")) {
			System.out.println("Seleziona l'elemento da aggiungere L = Libro R = Rivista | E per uscire.");
			String agg = s.nextLine().toUpperCase();
			if(agg.equals("E")) {
				exit = "esci";
			} else if (agg.equals("L")) {
				try {
					em.getTransaction().begin();
					Libro l = new Libro(true);
					l.setISBN();
					l.setAnnoPubb();
					l.setNumOfPage();
					em.persist(l);
					em.getTransaction().commit();
					System.out.println("Libro aggiunto con successo!");
				} catch (Exception e) {
					System.out.println("Qualcosa è andatao storto");
				}
			} else if (agg.equals("R")) {
				try {
					em.getTransaction().begin();
					Rivista r = new Rivista(true);
					r.setISBN();
					r.setAnnoPubb();
					r.setNumOfPage();
					em.persist(r);
					em.getTransaction().commit();
					System.out.println("Rivista aggiunta con successo!");
				} catch (Exception e) {
					System.out.println("Qualcosa è andatao storto");
				}
			}
		}
	}
	
	public static void removeByISBN () {
			
		boolean exit = false;
	    while (!exit) {
	        System.out.println(">> Inserisci il codice ISBN da eliminare | 0 per uscire.");
	        try {
	        	long ISBNtoRemove = s.nextLong();
	            if (ISBNtoRemove == 0) {
	             exit = true;
	            
	            }
	            
	            em.getTransaction().begin();
	            ElementoCatalogo el = em.find(ElementoCatalogo.class, ISBNtoRemove );
	            em.getTransaction().commit();
	            
	            if (el.getISBN() == ISBNtoRemove) {
	            		em.getTransaction().begin();
	            		em.remove(el);
	            		em.getTransaction().commit();
	            	 System.out.println("Articolo eliminato dal DB!");
	            } else {
	                System.out.println("Il codice inserito non esiste");
	            }
	        } catch (Exception e) {
	            System.out.println(">> Inserisci un numero valido");
	            s.nextLine();}
	        /*} finally {
	        	em.close();
	        }*/
	    }
	}

	public static void ricerca(Scanner input ) {
		System.out.println(">> Inserisci la parola chivare con cui vuoi cercare il Libro o la rivista \n autore - anno - ISBN - Titolo ");
		Object query = null;
		
		if(input.hasNextInt()) {
			query = input.nextInt();
		} else if (input.hasNextLong()) {
			query = input.nextLong();
		} else if (input.hasNextLine()) {
			query = input.nextLine().toUpperCase();
		}  else {
			System.out.println("Spiace");
		}
		try {
			
			//Se inserisco una Stringa
			if (query instanceof String) {
				String Nome = (String) query;
				Query q = em.createQuery("SELECT e FROM ElementoCatalogo e WHERE e.autore LIKE :codice OR e.titolo LIKE :codice");
				q.setParameter("codice", "%" + Nome + "%");
				List<ElementoCatalogo> res = q.getResultList();
				res.forEach(el -> System.out.println(ElementoCatalogo.toString(el)));
				
				//Se inserisco ISMB
			} else if (query instanceof Long) {
				Long CodiceLibro = (Long) query;
				Query q = em.createQuery("SELECT e FROM ElementoCatalogo e WHERE e.iSBN = :codice");
				q.setParameter("codice", CodiceLibro);
				List<ElementoCatalogo> res = q.getResultList();
				res.forEach(el -> System.out.println(ElementoCatalogo.toString(el)));
				
				//Se inserisco un Intero
			} else if (query instanceof Integer) {
				Integer Anno = (Integer) query;
				Query q = em.createQuery("SELECT e FROM ElementoCatalogo e WHERE e.annoPubb = :codice");
				q.setParameter("codice", Anno);
				List<ElementoCatalogo> res = q.getResultList();
				res.forEach(el -> System.out.println(ElementoCatalogo.toString(el)));
				
			} else {
				System.out.println("Tipo non supportato");
				return;
			}
		} catch (Exception e) {
			System.out.println("Errore nella ricerca" + e);
		} 
		/*
		 * finally { em.close(); }
		 */
	}
	
	public static void gestionUtenti() {
		boolean exit = false;
		while(!exit) {
		System.out.println("Seleziona una delle seguenti opzioni | 0 per uscire "
				+ "\n 1 AGGIUNGI UN NUOVO UTENTE"
				+ "\n 2 ELIMINA UN UTENTE"
				+ "\n 3 VISUALIZZA TUTTI GLI UTENTI");
		Integer scelta = s.nextInt();
		s.nextLine();
		switch(scelta){
		case 0:
			exit = true;
			break;
		case 1:
			 AggiungiUtente();
			break;
		case 2:
			eliminaUtente();
			break;
		case 3:
			allUtenti();
			break;
			default: System.out.println("Scelta non valida"); 
			} 
		}
		
	}
	
	public static void eliminaUtente () {
		
		boolean exit = false;
	    while (!exit) {
	        System.out.println(">> Inserisci il Numero Tessera dell'utente da eliminare | 0 per uscire.");
	        try {
	        	Long numTessera = s.nextLong();
	            if (numTessera == 0) {
	             exit = true;
	            }
	            em.getTransaction().begin();
	            Utente u = em.find(Utente.class, numTessera);
	            em.getTransaction().commit();
	            
	            if (u != null && u.getNumeroTessera() == numTessera) {
	            		em.getTransaction().begin();
	            		em.remove(u);
	            		em.getTransaction().commit();
	            	 System.out.println("Utente eliminato dal DB!");
	            } else {
	                System.out.println("Il codice inserito non esiste");
	            }
	        } catch (Exception e) {
	            System.out.println(">> Inserisci un numero valido");
	            s.nextLine();
	        } 
			/*
			 * finally { em.close(); }
			 */
	    }
	}
	
	public static void allUtenti () {
		
		Query query = em.createQuery("SELECT u FROM Utente u");
		List<Utente> lis = query.getResultList();
		lis.forEach(u -> System.out.println(u.toString()));
	}
	
	public static void prendiInprestito() {
		System.out.println(">> Inserisci il numero di tessera per iniziare");
		Long nt = s.nextLong();
		try {
	         Utente u = UtenteDAO.findUtente(nt);
	         if(u != null && u.getNumeroTessera() == nt) {
	        	System.out.println(">> Inserisci il codice del Libro/Rivista che vuoi prendere in prestito");
	        	long l = s.nextLong();
	        	s.nextLine();
	        	ElementoCatalogo el = ElementoCatalogoDAO.findElCat(l);
	        	if(el != null && el.getISBN() == l) {
	        		ElementoInPrestito ep = new ElementoInPrestito();
	        		ep.setElementoPrestato(el);
	        		ep.setUtente(u);
	        		ep.setInizioPrestito();
	        		ep.setRestituzionePrevista();
	        		ElementoInPrestitoDAO.saveElInPres(ep);
	        		
	        	} else {
	        		System.out.println("Codice non trovato!");	        		
	        		}
	        	} else {
	        	 System.out.println("Numero tessera non trovato");
	         }
		} catch (Exception e) {
			System.out.println("Inserisci un valore valido");
		}
	}
	
	public static void visualizzaElInPrestito() {
		
		System.out.println("Inserisci il numero di tessera");
		Long nt = s.nextLong();
		try {
			Utente u = UtenteDAO.findUtente(nt);
	         if(u != null && u.getNumeroTessera() == nt) {
	        	 System.out.println("Ciao " + u.getNome());
	        	List<ElementoInPrestito> lista = findAllPrestitiByNT(nt);
	        	lista.forEach(el -> System.out.println(el.toString()));
	         } else {
	        	 System.out.println("Numero tessera non trovato");
	         }
		} catch (Exception e) {
			System.out.println("Inserisci un valore valido " + e);
		}
	}
	
	public static void visualizzaPrestitiScaduti() {
		if(findAllPrestitiNR().size() != 0) {
			System.out.println("Prestiti acnora non restituiti");
			List<ElementoInPrestito> list = findAllPrestitiNR();
			list.forEach(el -> ElementoInPrestito.toString(el));
		} else {
			System.out.println("Non ci sono prestiti/prestiti da restituire");
		}
		if(findAllPrestitiSC().size() != 0) {
			System.out.println("Prestiti acnora non restituiti");
			List<ElementoInPrestito> list = findAllPrestitiSC();
			list.forEach(el -> ElementoInPrestito.toString(el));
		}else {
			System.out.println("Non ci sono prestiti/prestiti scaduti");
		}
	}
	
	public static long isUtentiEmpty () {
		
		Query countQuery1 = em.createQuery("SELECT COUNT(*) FROM Utente");
		long count = (long) countQuery1.getSingleResult();
		return count;
	}
	
	public static List<ElementoInPrestito> findAllPrestitiByNT(Long n) {
		Query query = em.createQuery("SELECT p FROM ElementoInPrestito p WHERE p.numeroTessera = :t" );
		query.setParameter("t", n);
		return query.getResultList();
	}
	
	public static List<ElementoInPrestito> findAllPrestitiNR () {
		Query query = em.createQuery("SELECT p FROM ElementoInPrestito p WHERE p.restituzioneEffettiva = null" );
		return query.getResultList();
	}
	
	public static List<ElementoInPrestito> findAllPrestitiSC () {
		Query query = em.createQuery("SELECT p FROM ElementoInPrestito p WHERE p.restituzioneEffettiva > p.restituzionePrevista" );
		return query.getResultList();
	}
	
	
	public static long isCatalogoEmpty () {
	
	Query countQuery1 = em.createQuery("SELECT COUNT(*) FROM ElementoCatalogo");
    long count = (long) countQuery1.getSingleResult();
	return count;
}

	public static List<ElementoCatalogo> findAllElements() {

		Query q = em.createNamedQuery("Elementi.FindAll"); 
		return q.getResultList();
	}
}
