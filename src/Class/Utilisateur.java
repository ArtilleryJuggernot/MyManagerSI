package Class;

import java.sql.Date;

import javax.persistence.Entity;

@Entity
public class Utilisateur {
	private int ID;
	private String Nom;
	private String Email;
	private String Password;
	private Date create_at;
	private Date last_co;
	private int entreprise_id;
	private int Site_id;
	private int Type_compteID;
	
	
	
	
	public Utilisateur(int iD, String nom, String email, String password, Date create_at, Date last_co,
			int entreprise_id, int site_id, int type_compteID) {
		super();
		ID = iD;
		Nom = nom;
		Email = email;
		Password = password;
		this.create_at = create_at;
		this.last_co = last_co;
		this.entreprise_id = entreprise_id;
		Site_id = site_id;
		Type_compteID = type_compteID;
	}
	
	
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getNom() {
		return Nom;
	}
	public void setNom(String nom) {
		Nom = nom;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	public Date getCreate_at() {
		return create_at;
	}
	public void setCreate_at(Date create_at) {
		this.create_at = create_at;
	}
	public Date getLast_co() {
		return last_co;
	}
	public void setLast_co(Date last_co) {
		this.last_co = last_co;
	}
	public int getEntreprise_id() {
		return entreprise_id;
	}
	public void setEntreprise_id(int entreprise_id) {
		this.entreprise_id = entreprise_id;
	}
	public int getSite_id() {
		return Site_id;
	}
	public void setSite_id(int site_id) {
		Site_id = site_id;
	}
	public int getType_compteID() {
		return Type_compteID;
	}
	public void setType_compteID(int type_compteID) {
		Type_compteID = type_compteID;
	}
}
