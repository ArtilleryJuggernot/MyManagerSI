package Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "site")
public class Site {
	@GeneratedValue
	@Id
	private int id;
	private String Libelle;
	private int ID_entreprise;
	
	
	
	
	
	public Site() {
		super();
	}
	public Site(int id, String libelle, int iD_entreprise) {
		super();
		this.id = id;
		Libelle = libelle;
		ID_entreprise = iD_entreprise;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLibelle() {
		return Libelle;
	}
	public void setLibelle(String libelle) {
		Libelle = libelle;
	}
	public int getID_entreprise() {
		return ID_entreprise;
	}
	public void setID_entreprise(int iD_entreprise) {
		ID_entreprise = iD_entreprise;
	}
	
}

