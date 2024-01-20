package Entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "catégoriematériel")
public class CatégorieMatériel {
	@Id
	private int id;
	private String Libelle;
	
	public CatégorieMatériel(){
		
	}
	
	public CatégorieMatériel(int id, String libelle) {
		super();
		this.id = id;
		Libelle = libelle;
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
	
	
	
	
}
