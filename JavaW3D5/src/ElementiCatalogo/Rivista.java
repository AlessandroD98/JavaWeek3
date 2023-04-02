package ElementiCatalogo;

import java.util.InputMismatchException;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import Main.Archivio;

@Entity
@Table(name= "riviste")
@DiscriminatorValue("Rivista")
public class Rivista extends ElementoCatalogo {

	
	enum Uscita {
		SETTIMANALE, MENSILE, SEMESTRALE
	}
	
	@Enumerated(EnumType.STRING)
	Uscita periodicità;
	private Boolean prova;
	
	public Rivista() {}
	
	public Rivista(Boolean prova) {
		this.setPeriodicità();
		this.prova = prova;
	}
	
	public Rivista(long ISBN, String Titolo, int annoPubb, int numOfPage, Uscita Periodicità) {
		this.iSBN = ISBN;
		this.titolo = Titolo;
		this.annoPubb = annoPubb;
		this.numOfPage = numOfPage;
		this.periodicità = Periodicità;
	}
	
	public Boolean getCheck() {
		return this.prova;
	}
	
	public void setCheck(Boolean check) {
		this.prova = check;
	}
	
	public Uscita getUscitaPeriodicita() {
		return this.periodicità;
	}
	
	@Override
	protected void setTitolo() {
		System.out.println(">> Inserisci il Titolo della Rivista");
		this.titolo = Archivio.s.nextLine().toUpperCase();
	}

	private void setPeriodicità () {
		
		int uscita = 0;
		while(uscita <= 0 || uscita >= 4) {
			System.out.println(">> Seleziona la periodicità della rivista 1 = Settimanale 2 = Mensile 3 = Semestrale");
			try {
				  uscita = getIntFromS(); 
				switch(uscita){
				case 1:
					this.periodicità = Uscita.SETTIMANALE;
					break;
				case 2:
					this.periodicità = Uscita.MENSILE;
					break;
				case 3:
					this.periodicità = Uscita.SEMESTRALE;
					break;
				}
			}catch(InputMismatchException e) {
				System.out.println("Utilizza un valore valido");
	            Archivio.s.nextLine();
			}
			
		}
		
	}
	
}
