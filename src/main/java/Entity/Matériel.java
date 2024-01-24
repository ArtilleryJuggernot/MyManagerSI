package Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "matériel")
public class Matériel {
	 
	

	@Id
	 @Column(name = "id")
	private int id;
	
	@Column(name = "Libelle")
	private String Libelle;
	
	
	@Column(name = "ID_categorie")
	private int ID_categorie;
	
	
	@Column(name = "site_id")  
    private int site_id;



	
	
	
	
	
	
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
	

    public int getSite_id() {
        return site_id;
    }

    public void setSite_id(int site_id) {
        this.site_id = site_id;
    }
	
	
    @Override
    public String toString() {
		return "Matériel [id=" + id + ", Libelle=" + Libelle + ", ID_categorie=" + ID_categorie + ", site_id=" + site_id
				+ "]";
	}
}
