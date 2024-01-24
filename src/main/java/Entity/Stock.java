package Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "stock")
public class Stock {
	
	@Id
	 @Column(name = "ID_matériel")
	private int ID_matériel;
	
	
	private String description;
	private int ID_Entreprise;
	private int Statut_id;
	
	@GeneratedValue
	private int Site_id;
	
	
	public Stock(int iD_matériel, String description, int iD_Entreprise, int statut_id, int site_id) {
		super();
		ID_matériel = iD_matériel;
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
	
	
	@Override
	public String toString() {
		return "Stock [ID_matériel=" + ID_matériel + ", description=" + description + ", ID_Entreprise=" + ID_Entreprise
				+ ", Statut_id=" + Statut_id + ", Site_id=" + Site_id + "]";
	}
	
	
	
	
}
