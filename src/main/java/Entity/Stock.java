package Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "stock")
public class Stock {
	@Id
	private int ID_matériel;
	private String Nom;
	private String description;
	private int ID_Entreprise;
	private int Statut_id;
	@GeneratedValue
	private int Site_id;
	public Stock(int iD_matériel, String nom, String description, int iD_Entreprise, int statut_id, int site_id) {
		super();
		ID_matériel = iD_matériel;
		Nom = nom;
		this.description = description;
		ID_Entreprise = iD_Entreprise;
		Statut_id = statut_id;
		Site_id = site_id;
	}
	public Stock() {
		super();
	}
	public int getID_matériel() {
		return ID_matériel;
	}
	public void setID_matériel(int iD_matériel) {
		ID_matériel = iD_matériel;
	}
	public String getNom() {
		return Nom;
	}
	public void setNom(String nom) {
		Nom = nom;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getID_Entreprise() {
		return ID_Entreprise;
	}
	public void setID_Entreprise(int iD_Entreprise) {
		ID_Entreprise = iD_Entreprise;
	}
	public int getStatut_id() {
		return Statut_id;
	}
	public void setStatut_id(int statut_id) {
		Statut_id = statut_id;
	}
	public int getSite_id() {
		return Site_id;
	}
	public void setSite_id(int site_id) {
		Site_id = site_id;
	}
	
	
	
	
	
	
	
}
