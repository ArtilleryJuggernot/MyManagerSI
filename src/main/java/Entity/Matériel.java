package Entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "matériel")
public class Matériel {
	@Id
	private int id;
	private String Libelle;
	private int ID_categorie;
	
	
	
	
	
	
	
	
	public Matériel() {
		super();
	}
	public Matériel(int id, String libelle, int iD_categorie) {
		super();
		this.id = id;
		Libelle = libelle;
		ID_categorie = iD_categorie;
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
	public int getID_categorie() {
		return ID_categorie;
	}
	public void setID_categorie(int iD_categorie) {
		ID_categorie = iD_categorie;
	}
	
	
	
}
