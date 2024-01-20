package Class;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="Entreprise")
public class Entreprise {
	@Id
	private int id;
	private String Nom;
	private Date DateCreation;
	
	
	
	
	public Entreprise(int id, String nom, Date dateCreation) {
		super();
		this.id = id;
		Nom = nom;
		DateCreation = dateCreation;
	}




	public int getId() {
		return id;
	}




	public void setId(int id) {
		this.id = id;
	}




	public String getNom() {
		return Nom;
	}




	public void setNom(String nom) {
		Nom = nom;
	}




	public Date getDateCreation() {
		return DateCreation;
	}




	public void setDateCreation(Date dateCreation) {
		DateCreation = dateCreation;
	}




	@Override
	public String toString() {
		return "Entreprise [id=" + id + ", Nom=" + Nom + ", DateCreation=" + DateCreation + "]";
	}
}

