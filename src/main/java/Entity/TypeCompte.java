package Entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "typeCompte")
public class TypeCompte {
	@Id
	private int id;
	private String Libelle;
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
	public TypeCompte(int id, String libelle) {
		super();
		this.id = id;
		Libelle = libelle;
	}
	public TypeCompte() {
		super();
	}
	
	
	
	
	
	
	
	
}