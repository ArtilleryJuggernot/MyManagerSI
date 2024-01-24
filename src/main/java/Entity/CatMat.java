package Entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.Query;
import org.hibernate.Session;

import util.HibernateSessionFactory;

@Entity
@Table(name = "catmat")
public class CatMat {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "Libelle")
	private String Libelle;
	 
	@Column(name = "entreprise_id")
	private int entreprise_id;
	
	public CatMat(){
		
	}
	
	public CatMat(int id, String libelle) {
		super();
		this.id = id;
		Libelle = libelle;
	}

	public int getId() {
		return id;
	}
	
	public int getEntrepriseId(){
		return entreprise_id;
	}

	
	public void setEntrepriseId(int EntrepriseId) {
		this.entreprise_id = EntrepriseId;
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
	
	public static List<CatMat> getAllCat() {
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            String hql = "FROM CatMat";
            Query<CatMat> query = session.createQuery(hql, CatMat.class);
            return query.list();
        }
    }
	
	
}
