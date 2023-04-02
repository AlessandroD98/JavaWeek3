package ElementiCatalogo;




import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

import Main.Archivio;


@Entity
@Table(name= "libri")
@DiscriminatorValue("Libro")
public class Libro extends ElementoCatalogo{
	
	
	String autore;
	String genere;
	private Boolean prova;
	
	public Libro() {}
	
	public Libro(Boolean prova) {
		super();
		this.setAutore();
		this.setGenere();
		this.prova = prova;
	}
	
	public Libro(long ISBN, String Titolo, int AnnoPubb, int numOfPage, String autore, String genere) {
		this.iSBN = ISBN;
		this.titolo = Titolo;
		this.annoPubb = AnnoPubb;
		this.numOfPage = numOfPage;
		this.autore = autore;
		this.genere = genere;
	}
	
	public Boolean getCheck() {
		return this.prova;
	}
	
	public void setCheck(Boolean prova) {
		this.prova = prova;
	}
	
	public String getAutore() {
		return this.autore;
	}
	
	public String getGenere() {
		return this.autore;
	}
	
	@Override
	protected void setTitolo() {
		System.out.println(">> Inserisci il Titolo del Libro");
		this.titolo = Archivio.s.nextLine().toUpperCase();
	}
	
	private void setAutore() {
		System.out.println(">> Inserisci l'autore del Libro");
		this.autore = Archivio.s.nextLine().toUpperCase();
	}
	
	private void setGenere() {
		System.out.println(">> Inserisci il genere del Libro");
		this.genere = Archivio.s.nextLine();
	}
	
}
