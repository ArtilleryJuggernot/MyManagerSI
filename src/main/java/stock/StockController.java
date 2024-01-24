package stock;

import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import org.hibernate.Query;
import org.hibernate.Session;

import Entity.Entreprise;
import Entity.Matériel;
import Entity.Site;
import Entity.Statut;
import Entity.Stock;
import Entity.Utilisateur;
import util.HibernateSessionFactory;
import org.hibernate.Transaction;

public class StockController {
    private StockPage f;
    private Utilisateur user;
    private Entreprise entreprise;
    private Site site;
    private Session session;
    
    
    public StockController(StockPage f, Utilisateur user) {
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
    	
    	f.getLblInfo().setText("Liste des ressources de l'entreprise "
    			+ entreprise.getNom()
    			+ " sur le site "
    			+ site.getLibelle());
    }

    private void initButtons() {
        f.getBtnAjouter().addActionListener(e -> {
            // Ajoutez votre logique pour ajouter une ressource
            String libelle = JOptionPane.showInputDialog(f, "Entrez le libellé de l'entité à insérer :");
            String description = JOptionPane.showInputDialog(f, "Entrez la description de l'entité à insérer :");
            if (libelle != null) {
                String statut = f.getComboBoxCategorie().getSelectedItem().toString();
                String categorie = f.getComboBoxCat().getSelectedItem().toString();
                addMateriel(libelle, description,statut,categorie);
            }
        });

        f.getBtnSupprimer().addActionListener(e -> {
            // Ajoutez votre logique pour supprimer une ressource par ID
            String idStr = JOptionPane.showInputDialog(f, "Entrez l'ID du matériel à supprimer :");
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

    private void addMateriel(String libelle, String description,String Statut, String categorie) {
        Transaction transaction = null;

        try {
            // Début de la transaction Hibernate
            transaction = session.beginTransaction();

            // Logique pour ajouter une ressource dans la base de données
            // Exemple (remplacez avec votre propre logique Hibernate) :
            DefaultTableModel model = (DefaultTableModel) f.getTable().getModel();
            model.addRow(new Object[]{getNextId(), libelle, categorie});

            // Affichez un message de confirmation
            JOptionPane.showMessageDialog(f, "Le matériel a été ajouté avec succès.", "Succès", JOptionPane.INFORMATION_MESSAGE);

            
            int prochainId = getNextId();
            // Insertion dans la BDD : Matériel
            Matériel m = new Matériel();
            System.out.println(getNextId());
            m.setId(prochainId);
            m.setLibelle(libelle);
            m.setSite_id(site.getId());
            m.setID_categorie(getIdCategorieByLibelle(categorie));

            // Insertion dans la BDD : Stock
            Stock s = new Stock();
            s.setID_matériel(prochainId);
            s.setID_Entreprise(entreprise.getId());
            s.setDescription(description);
            s.setStatut_id(getIdStatutByLibelle(Statut));
            s.setSite_id(site.getId());

            session.save(m);
            session.save(s);

            // Validation de la transaction (commit)
            transaction.commit();
        } catch (Exception e) {
            // En cas d'erreur, annulation de la transaction
            if (transaction != null) {
                transaction.rollback();
            }

            // Gestion de l'exception
            e.printStackTrace();
            JOptionPane.showMessageDialog(f, "Une erreur s'est produite lors de l'ajout du matériel.", "Erreur", JOptionPane.ERROR_MESSAGE);
        } finally {
            // Fermeture de la session Hibernate
            if (session != null) {
                session.close();
            }
        }
    }

    
    
    private int getIdCategorieByLibelle(String libelleCategorie) {
    	System.out.println("BEGIN DEBUG ID CAT");
    	System.out.println(libelleCategorie);

    	 String query = "SELECT id FROM CatMat WHERE Libelle = :Libelle";

         Integer result = session.createQuery(query, Integer.class)
                 .setParameter("Libelle", libelleCategorie)
                 .uniqueResult();
        
         System.out.println("ID DE LA CATEGORIE");
         System.out.println(result);
         return result;
        		
    }
    
    
    private int getIdStatutByLibelle(String libelleStatut) {
    	System.out.println("BEGIN DEBUG ID CAT");
    	System.out.println(libelleStatut);

    	 String query = "SELECT id FROM Statut WHERE Libelle = :Libelle";

         Integer result = session.createQuery(query, Integer.class)
                 .setParameter("Libelle", libelleStatut)
                 .uniqueResult();
        
         System.out.println("ID DE LA Statut");
         System.out.println(result);
         return result;
        		
    }

    private void removeMateriel(int id) {
        // Ajoutez votre logique pour supprimer une ressource par ID dans la base de données
        // Mettez à jour la table et affichez un message de confirmation

        // Exemple (remplacez avec votre propre logique Hibernate) :
        DefaultTableModel model = (DefaultTableModel) f.getTable().getModel();
        int rowCount = model.getRowCount();
        for (int i = 0; i < rowCount; i++) {
            if ((int) model.getValueAt(i, 0) == id) {
                model.removeRow(i);

                // Affichez un message de confirmation
                JOptionPane.showMessageDialog(f, "Le matériel a été supprimé avec succès.", "Succès", JOptionPane.INFORMATION_MESSAGE);
                System.out.println("BEGIN DEBUG REMOVE");
                System.out.println(id);
                Matériel m = session.get(Matériel.class, id);
                System.out.println(m);
                System.out.println(m != null);
                session.delete(m);
                
                return;
            }
        }

        // Si l'ID n'est pas trouvé
        JOptionPane.showMessageDialog(f, "Matériel non trouvé avec l'ID : " + id, "Erreur", JOptionPane.ERROR_MESSAGE);
    }
    
   

    private int getNextId() {
        // Ajoutez votre logique pour obtenir le prochain ID (à adapter selon votre gestion des ID)
        // Ici, on utilise simplement un compteur basé sur le nombre de lignes dans la table
        DefaultTableModel model = (DefaultTableModel) f.getTable().getModel();
        return model.getRowCount() + 1;
    }
    
    
    private void initTable() {
    	System.out.println("INIT TABLE");
        // Charger la liste des matériels en fonction du site_id lié à l'utilisateur
        List<Stock> stock = getStockBySiteId(user.getSite_id());
        System.out.println("STOCK :");
        System.out.println(stock);
        // Remplir le tableau avec les matériels
        DefaultTableModel model = (DefaultTableModel) f.getTable().getModel();
        for (Stock s : stock) {
        	System.out.println("LOOP");
        	Matériel m = session.get(Matériel.class, s.getID_matériel());
        	Statut st = session.get(Statut.class, s.getStatut_id());
            model.addRow(new Object[] { s.getID_matériel(), m.getLibelle(), s.getDescription() ,st.getLibelle() });
        }
    }

    private List<Stock> getStockBySiteId(int siteId) {
    	System.out.println("SITE ID");
    	System.out.println(siteId);
        Query<Stock> query = session.createQuery("FROM Stock WHERE site_id = :siteId", Stock.class);
        query.setParameter("siteId", siteId);
        return query.list();
    }
}
