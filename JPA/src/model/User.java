package model;

import java.io.Serializable;
import javax.persistence.*;


@Entity
@Table(name= "user_jpa") //Specifico lo stesso nome della tabella
@NamedQuery(name= "User.findAll", query = "SELECT u FROM User u") //Vado a creare una query personalizzata assegndo un nome ed una funzionalità
public class User implements Serializable {

	//proprietà con lo stesso nome della tabella
	
	@Id // indica che questo è un id
	@GeneratedValue(strategy= GenerationType.IDENTITY) // indica come verrà incrementato l'Id
	@Column(name= "id_user") //Specifico lo stesso nome della colonna
	private Long id;
	
	@Column(nullable = false) //Specifico che il dato sia NOT NULL
	private String name;
	
	@Column(nullable = false)
	private String lastname;
	
	@Column(nullable = false, unique = true) //Specifico che il dato sia NOT NULL ed UNIQUE
	private String email;

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", lastname=" + lastname + ", email=" + email + "]";
	}
	
	
}
