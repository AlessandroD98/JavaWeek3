package Esercizi;

import java.sql.SQLException;

public class Runnable {

	public static void main(String[] args) {
		
		try {
			DBConnection db = new DBConnection();
			
			//Studente s = new Studente("Mario", "Rossi","M", "03/03/1998" , 8, 9);
			//db.creaStudente(s);
			Studente s = db.findStudente(1);
			System.out.println(s);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
