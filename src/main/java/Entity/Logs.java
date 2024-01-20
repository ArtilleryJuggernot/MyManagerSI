package Entity;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "logs")
public class Logs {
	@GeneratedValue
	@Id
	private int id;
	private Date Date;
	private int Utilisateur_ID;
	private String Action;
	private String Contenu;
	
	
	public Logs() {
		
	}
	
	
	
	public Logs(int id, java.sql.Date date, int utilisateur_ID, String action, String contenu) {
		super();
		this.id = id;
		Date = date;
		Utilisateur_ID = utilisateur_ID;
		Action = action;
		Contenu = contenu;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getDate() {
		return Date;
	}
	public void setDate(Date date) {
		Date = date;
	}
	public int getUtilisateur_ID() {
		return Utilisateur_ID;
	}
	public void setUtilisateur_ID(int utilisateur_ID) {
		Utilisateur_ID = utilisateur_ID;
	}
	public String getAction() {
		return Action;
	}
	public void setAction(String action) {
		Action = action;
	}
	public String getContenu() {
		return Contenu;
	}
	public void setContenu(String contenu) {
		Contenu = contenu;
	}
	
	
	
}
