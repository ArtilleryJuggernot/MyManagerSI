package materiel;

import java.io.Serializable;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import Entity.CatMat;
import Entity.Entreprise;
import Entity.Matériel;
import Entity.Site;
import Entity.Utilisateur;
import util.HibernateSessionFactory;

public class MaterielController {
    private MaterielPage f;
    private Utilisateur user;
    private Entreprise entreprise;
    private Site site;
    private Session session;
    
    
    public MaterielController(MaterielPage f, Utilisateur user) {
        this.f = f;
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.user = user;
        this.session = HibernateSessionFactory.getSessionFactory().openSession();
        int entrepriseId = user.getEntreprise_id();
        this.entreprise = session.get(Entreprise.class, entrepriseId);
        int siteId = user.getSite_id();
        this.site = session.get(Site.class, siteId);
        
        
        init();
        initButtons();
        f.setVisible(true);
    }

    private void init() {
        initButtons();
        setName();
        initTable();
    }
    
    
    // Permet de mettre le nom de l'entreprise et le nom du site
    private void setName() {
    	
    	
    	
    	f.getLblInfo().setText("Liste des catégories de l'entreprise "
    			+ entreprise.getNom()
    			+ " sur le site "
    			+ site.getLibelle());
    }

    private void initButtons() {
        f.getBtnAjouter().addActionListener(e -> {
            // Ajoutez votre logique pour ajouter une ressource
            String libelle = JOptionPane.showInputDialog(f, "Entrez le libellé de la catégorie :");
            if (libelle != null) {
                addMateriel(libelle);
            }
        });

        f.getBtnSupprimer().addActionListener(e -> {
            // Ajoutez votre logique pour supprimer une ressource par ID
            String idStr = JOptionPane.showInputDialog(f, "Entrez l'ID de la catégorie à supprimer :");
            if (idStr != null) {
                try {
                    int id = Integer.parseInt(idStr);
                    removeMateriel(id);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(f, "Veuillez entrer un ID valide.", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    private void addMateriel(String libelle) {
        Transaction transaction = null;

        try {
            // Démarrez la transaction Hibernate
            transaction = session.beginTransaction();

            // Ajoutez votre logique pour ajouter une ressource dans la base de données
            DefaultTableModel model = (DefaultTableModel) f.getTable().getModel();
            model.addRow(new Object[] { getNextId(), libelle });

            // Affichez un message de confirmation
            JOptionPane.showMessageDialog(f, "La catégorie a été ajoutée avec succès.", "Succès", JOptionPane.INFORMATION_MESSAGE);

            // Insertion dans la BDD
            System.out.println("Insertion BDD Catégorie");

            CatMat cm = new CatMat();
            cm.setId(getNextId());
            cm.setLibelle(libelle);
            cm.setEntrepriseId(entreprise.getId());

            // Enregistrez l'entité dans la base de données
            Serializable a = session.save(cm);

            // Validez la transaction
            transaction.commit();

            System.out.println("ID inséré : " + a);
        } catch (HibernateException e) {
            // En cas d'erreur, annulez la transaction
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            // Fermez la session Hibernate
            //session.close();
        }
    }

    
    
    private int getIdCategorieByLibelle(String libelleCategorie) {
    	System.out.println("BEGIN DEBUG ID CAT");
    	System.out.println(libelleCategorie);

    	 String query = "SELECT id FROM CatMat WHERE Libelle = :Libelle";

         Integer result = session.createQuery(query, Integer.class)
                 .setParameter("Libelle", libelleCategorie)
                 .uniqueResult();
        
         return result;
        		
    }

    private void removeMateriel(int id) {
        Transaction transaction = null;

        try {
            // Démarrez la transaction Hibernate
            transaction = session.beginTransaction();

            // Ajoutez votre logique pour supprimer une ressource par ID dans la base de données
            DefaultTableModel model = (DefaultTableModel) f.getTable().getModel();
            int rowCount = model.getRowCount();
            for (int i = 0; i < rowCount; i++) {
                if ((int) model.getValueAt(i, 0) == id) {
                    model.removeRow(i);

                    // Affichez un message de confirmation
                    JOptionPane.showMessageDialog(f, "La catégorie a été supprimée avec succès.", "Succès", JOptionPane.INFORMATION_MESSAGE);
                    System.out.println("BEGIN DEBUG REMOVE");
                    System.out.println(id);
                    CatMat m = session.get(CatMat.class, id);
                    System.out.println(m);
                    System.out.println(m != null);
                    session.delete(m);

                    // Validez la transaction
                    transaction.commit();
                    return;
                }
            }

            // Si l'ID n'est pas trouvé
            JOptionPane.showMessageDialog(f, "Catégorie non trouvée avec l'ID : " + id, "Erreur", JOptionPane.ERROR_MESSAGE);
        } catch (HibernateException e) {
            // En cas d'erreur, annulez la transaction
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    
   

    private int getNextId() {
        // Ajoutez votre logique pour obtenir le prochain ID (à adapter selon votre gestion des ID)
        // Ici, on utilise simplement un compteur basé sur le nombre de lignes dans la table
        DefaultTableModel model = (DefaultTableModel) f.getTable().getModel();
        return model.getRowCount() + 1;
    }
    
    
    private void initTable() {
        // Charger la liste des matériels en fonction du site_id lié à l'utilisateur
        List<CatMat> materiaux = getMateriauxBySiteId(entreprise.getId());
        // Remplir le tableau avec les matériels
        DefaultTableModel model = (DefaultTableModel) f.getTable().getModel();
        for (CatMat materiel : materiaux) {
            model.addRow(new Object[] { materiel.getId(), materiel.getLibelle()});
        }
    }

    private List<CatMat> getMateriauxBySiteId(int entrepriseId) {
    	
    	
 
        Query query = session.createQuery("FROM CatMat WHERE entreprise_id = :entreprise_id", CatMat.class);
        query.setParameter("entreprise_id", entrepriseId);
        return query.list();
    }
}
