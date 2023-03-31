package Esercizi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection {

	private String url = "jdbc:postgresql://localhost:5432/";
	private String dbname = "ExW3D2";
	private String username = "postgres";
	private String password = "root";
	
	Statement st;
	
	public DBConnection() throws SQLException {
	Connection conn = DriverManager.getConnection(url+dbname, username, password);
	st = conn.createStatement();
	creaTabStudenti();
	}
	
	public void creaTabStudenti() throws SQLException {
		String sql = "CREATE TABLE IF NOT EXISTS Studenti("
				+ "id SERIAL PRIMARY KEY,"
				+ "name VARCHAR NOT NULL,"
				+ "surname VARCHAR NOT NULL,"
				+ "gender VARCHAR NOT NULL,"
				+ "birthdate DATE NOT NULL,"
				+ "avg INT2 NOT NULL,"
				+ "min_vote INT2 NOT NULL,"
				+ "max_vote INT2 NOT NULL)";
		this.st.executeUpdate(sql);
	}
	
	public void creaStudente(Studente s) throws SQLException {
		String sql = "INSERT INTO Studenti (name, surname, gender, birthdate, avg, min_vote, max_vote)"
				+ "VALUES ('" +s.getName() + "','" +s.getSurname() + "','" +s.getGender() + "','" +s.getBirthdate() + "','" +s.getAvg() + "','" +s.getMin_vote() + "','" +s.getMax_vote() +"')";
		this.st.executeUpdate(sql);
		System.out.println("Studente creato!");
	}
	
	public Studente findStudente(int id) throws SQLException {
		Studente stu = null;
		String sql = "SELECT * FROM Studenti WHERE id = " + id;
		ResultSet rs = this.st.executeQuery(sql);
		if(rs.next()) {
			long idS = rs.getLong("id");
			String name = rs.getString("name");
			String surname = rs.getString("surname");
			String gender = rs.getString("gender");
			String bithdate = rs.getString("birthdate");
			double avg = rs.getDouble("avg");
			int min_vote = rs.getInt("min_vote");
			int max_vote = rs.getInt("max_vote");
			stu = new Studente(idS, name, surname, gender, bithdate, avg, min_vote, max_vote);
		}
		return stu;
	}
	
}
