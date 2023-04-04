package ElementiCatalogo;

import java.util.Random;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import Main.Archivio;


@Entity
@Table(name = "catalogo")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "Articolo", discriminatorType = DiscriminatorType.STRING)
@NamedQuery(name = "Elementi.FindAll", query = "SELECT e FROM ElementoCatalogo e")
public class ElementoCatalogo {

	@Id
	protected long iSBN;
	protected String titolo;
	protected int annoPubb;
	protected int numOfPage;
	

	public ElementoCatalogo(long iSBN, String titolo, int annoPubb, int numOfPage) {
		super();
		this.iSBN = iSBN;
		this.titolo = titolo;
		this.annoPubb = annoPubb;
		this.numOfPage = numOfPage;
	}

	public ElementoCatalogo() {
		//this.setISBN();
		//this.setTitolo();
		//this.setAnnoPubb();
		//this.setNumOfPage();
	}
	
	public int getNumOfPage() {
		return this.numOfPage;
	}
	
	public int getAnnoPubb() {
		return this.annoPubb;
	}
	
	public long getISBN() {
		return this.iSBN;
	}
	
	public String getTitolo() {
		return this.titolo;
	}
	
	public void setNumOfPage() {
		Random num = new Random();
		int max=200 , min= 10;
		this.numOfPage = num.nextInt(max - min + 1) + min; 
	}
	
	public void setISBN () {
		
		long num = System.currentTimeMillis();
		Random rnum = new Random(num);
		long isbn = Math.abs(rnum.nextLong()) % 9000000000000L + 1000000000000L;
		isbn *= 10L;
		this.iSBN = isbn;
	}
	
	protected void setTitolo() {
			System.out.println(">> Inserisci il Titolo");
			this.titolo = Archivio.s.nextLine().toUpperCase();
	}
	
	public void setAnnoPubb() {
		
		System.out.println(">> Inserisci l'anno di pubblicazione");
		String anno = Archivio.s.nextLine();
		
		
		while(true) {
			if(anno == null)
				break;
			try {
				this.annoPubb = Integer.parseInt(anno);
				break;
			}catch(NumberFormatException e) {
				System.out.println("Utilizza un valore valido es. 2020");
			 anno = Archivio.s.nextLine();
			}
		}	
	}
	
	public static String toString(ElementoCatalogo e) {
		if (e instanceof Libro ) {
			Libro l = (Libro) e;
			return "Tipologia : Libro" + " \n Titolo: " + l.getTitolo() + " \n Autore: " + l.getAutore() + " \n Anno di pubblicazione: " + l.getAnnoPubb() + " \n Genere: " + l.getGenere()+ " \n Numero di pagine: " + l.getNumOfPage() + " \n Codice ISBN: " + l.getISBN() + "\n";
		} else if (e instanceof Rivista) {
			Rivista r = (Rivista) e;
			return "Tipologia : Rivista" +  " \n Titolo: " + r.getTitolo() + " \n Anno di pubblicazione: " + r.getAnnoPubb() + " \n Numero di pagine: " + r.getNumOfPage()+ " \n Periodicità: " + r.getUscitaPeriodicita() + " \n Codice ISBN: " + r.getISBN() + "\n";
		} else return "Inserisci un Libro o una Rivista";
	}
	
	public static int getIntFromS() {
		int num = Archivio.s.nextInt();
		Archivio.s.nextLine();
		return num;
	}
	
}
