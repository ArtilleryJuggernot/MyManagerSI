package Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.Session;

import util.HibernateSessionFactory;

@Entity
@Table(name = "site")
public class Site {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
	private String Libelle;
	private int ID_entreprise;
	
	@ManyToOne
	@JoinColumn(name = "ID_entreprise", insertable = false, updatable = false)
	private Entreprise entreprise;

	
	
	
	
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
	
	
	public long getNombrePersonnes() {
        // Utilisation de COUNT avec la relation entre Site et Personne
        String query = "SELECT COUNT(u) FROM Utilisateur u WHERE u.Site_id = :siteId";
       Session session = HibernateSessionFactory.getSessionFactory().openSession();
        return session.createQuery(query, Long.class)
                .setParameter("siteId", this.id)
                .getSingleResult();
    }
	
	public static void deleteSite(int siteId) {
	    // Utilisez Hibernate pour supprimer le site dans la base de données
	    Session session = HibernateSessionFactory.getSessionFactory().openSession();
	    try {
	        Site site = session.get(Site.class, siteId);
	        
	        if (site != null) {
	            session.delete(site);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        session.close();
	    }
	}

	
}

