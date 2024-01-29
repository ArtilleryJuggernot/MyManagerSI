package user_manage;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableModel;

import Entity.Site;
import Entity.TypeCompte;
import Entity.Utilisateur;
import crypto.Encryption;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateSessionFactory;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

public class ManageUserController {

    private ManageUserPage f;
    private Utilisateur user;
    private Site site;
    private Session session;
    private boolean isModif;

    public ManageUserController(ManageUserPage f, Utilisateur user) {
        this.f = f;
        this.user = user;
        this.session = HibernateSessionFactory.getSessionFactory().openSession();
        int siteId = user.getSite_id();
        this.site = session.get(Site.class, siteId);

        init();
        f.setVisible(true);
    }

    private void init() {
        loadUsersToTable();
        initSites();
        initTypes();
        f.getUserTable().getSelectionModel().addListSelectionListener(this::setInformationIntoLabel);
        f.getBtnNewUser().addActionListener(e -> onNewUser(e));
        f.getAddButton().addActionListener(e -> addUser(e));
        f.getDeleteButton().addActionListener(e -> deleteUser());
    }

    public void loadUsersToTable() {
        DefaultTableModel userModel = f.getUserModel();
        userModel.setRowCount(0);

        // Charger les utilisateurs depuis la base de données et les ajouter au modèle de tableau
        List<Utilisateur> users = getUsersFromDatabase();
        for (Utilisateur u : users) {
        	Site userSite = session.get(Site.class, u.getSite_id());
        	String NameRole = session.get(TypeCompte.class,u.getType_compteID()).getLibelle();
            Object[] rowData = {u.getID(),u.getNom(), u.getEmail(), userSite.getLibelle(), NameRole};
            userModel.addRow(rowData);
        }
    }
    
    private void initSites() {
        List<Site> sites = loadSitesFromDatabase();

        // Créez un modèle pour la JComboBox
        DefaultComboBoxModel<Site> siteComboBoxModel = new DefaultComboBoxModel<>();

        // Ajoutez les sites au modèle
        for (Site site : sites) {
            siteComboBoxModel.addElement(site);
        }

        // Appliquez le modèle à la JComboBox
        f.getSiteComboBox().setModel(siteComboBoxModel);
    }

    private void initTypes() {
    	List<TypeCompte> tc = loadTypesFromDatabase();
    	
    	DefaultComboBoxModel<TypeCompte> model = new DefaultComboBoxModel<TypeCompte>();
    	
    	for (TypeCompte t : tc) {
    		model.addElement(t);
    	}
    	
    	f.getTypeComboBox().setModel(model);
    }
    

    public void addUser(ActionEvent e) {
    	int id = Integer.parseInt(f.getLblIDInput().getText()); // ID de la personne 
        String username = f.getUsernameField().getText();
        String usermail = f.getMailBox().getText();
        String password = new String(f.getPasswordField().getPassword());
        Site selectedSite = (Site) f.getSiteComboBox().getSelectedItem();
        TypeCompte selectedType = (TypeCompte)f.getTypeComboBox().getSelectedItem();

        // Valider les champs du formulaire (vous pouvez ajouter des validations supplémentaires)
        if (username.isEmpty()) {
        	
        	if(!this.isModif && password.isEmpty()) {
        		 JOptionPane.showMessageDialog(f, "Veuillez remplir tous les champs du formulaire.", "Erreur", JOptionPane.ERROR_MESSAGE);
                 return;
        	}

        }

        // Créer un nouvel utilisateur
        Utilisateur user = session.get(Utilisateur.class,id);
        // verifier si l'utilisateur existe etc
        
        // l'utilisateur n'existe pas
        if (user == null) {
        	user = new Utilisateur();
        }
        user.setNom(username);
        
        if(!password.isEmpty())
        	user.setPassword(Encryption.hashPassword(password));
        
        user.setEmail(usermail);
        user.setEntreprise_id(this.user.getEntreprise_id());
        user.setSite_id(selectedSite.getId());
        user.setType_compteID(selectedType.getId());
        
        user.setCreate_at(new Date(System.currentTimeMillis()));
        Transaction transaction = null;

        try {
            // Début de la transaction Hibernate
            transaction = session.beginTransaction();

            // Sauvegarder l'utilisateur dans la base de données
            session.save(user);

            // Commit de la transaction
            transaction.commit();

            // Actualiser le tableau des utilisateurs
            loadUsersToTable();

            // Afficher un message de confirmation
            JOptionPane.showMessageDialog(f, "L'utilisateur a été ajouté/modifié avec succès.", "Succès", JOptionPane.INFORMATION_MESSAGE);

            // Réinitialiser les champs du formulaire
            f.getUsernameField().setText("");
            f.getPasswordField().setText("");

        } catch (HibernateException error) {
            // Gestion des erreurs Hibernate
            if (transaction != null) {
                transaction.rollback();
            }
            error.printStackTrace();
            JOptionPane.showMessageDialog(f, "Une erreur s'est produite lors de l'ajout de l'utilisateur.", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void deleteUser() {
        int idUser = Integer.parseInt(f.getLblIDInput().getText());

        // Vérifier si une ligne est sélectionnée
        if (idUser < 0) {
            JOptionPane.showMessageDialog(f, "Veuillez sélectionner un utilisateur à supprimer.", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }

       
        Utilisateur userToDelete = session.get(Utilisateur.class, idUser);
        long nbAdminUser = countAdminsInEnterprise(this.user.getEntreprise_id());
        
        if(nbAdminUser < 2 && userToDelete.getType_compteID() == 1) {
            JOptionPane.showMessageDialog(f, "Il n'y a qu'un seul administrateur, créer un autre administrateur pour supprimer cet utilisateur", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if (userToDelete != null) {
            Transaction transaction = null;

            try {
                // Début de la transaction Hibernate
                transaction = session.beginTransaction();

                // Supprimer l'utilisateur de la base de données
                session.delete(userToDelete);

                // Commit de la transaction
                transaction.commit();

                // Actualiser le tableau des utilisateurs
                loadUsersToTable();

                // Afficher un message de confirmation
                JOptionPane.showMessageDialog(f, "L'utilisateur a été supprimé avec succès.", "Succès", JOptionPane.INFORMATION_MESSAGE);

            } catch (HibernateException e) {
                // Gestion des erreurs Hibernate
                if (transaction != null) {
                    transaction.rollback();
                }
                e.printStackTrace();
                JOptionPane.showMessageDialog(f, "Une erreur s'est produite lors de la suppression de l'utilisateur.", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    public long countAdminsInEnterprise(int enterpriseId) {
        try {
            // Ouvrir une transaction
            Transaction transaction = session.beginTransaction();

            // Exécuter la requête HQL pour compter les administrateurs dans une entreprise
            Query query = session.createQuery("SELECT COUNT(*) FROM Utilisateur u " +
                                              "WHERE u.Type_compteID = 1 " +
                                              "AND u.entreprise_id = :enterpriseId");
            query.setParameter("enterpriseId", enterpriseId);
            long adminCount = (long) query.uniqueResult();

            // Terminer la transaction
            transaction.commit();

            // Retourner le nombre d'administrateurs
            return adminCount;
        } catch (Exception e) {
            // Gérer les exceptions selon vos besoins
            e.printStackTrace();
            return -1; // Valeur par défaut en cas d'erreur
        }
    }


    private List<Utilisateur> getUsersFromDatabase() {
        // Charger tous les utilisateurs pour le site actuel depuis la base de données
        Query query = session.createQuery("FROM Utilisateur WHERE entreprise_id = :entrepriseID");
        query.setParameter("entrepriseID", site.getID_entreprise());
        return query.list();
    }

    private Utilisateur getUserByUsername(String username) {
        // Récupérer l'utilisateur à partir du nom d'utilisateur
        Query query = session.createQuery("FROM Utilisateur WHERE nom = :username AND site_id = :siteId");
        query.setParameter("username", username);
        query.setParameter("siteId", site.getId());
        return (Utilisateur) query.uniqueResult();
    }

    public List<Site> loadSitesFromDatabase() {
        // Charger tous les sites depuis la base de données
        Query query = session.createQuery("FROM Site WHERE ID_entreprise = :entrepriseId");
        query.setParameter("entrepriseId", user.getEntreprise_id());
        return query.list();
    }

    public List<TypeCompte> loadTypesFromDatabase() {
        // Charger tous les types de compte depuis la base de données
        Query query = session.createQuery("FROM TypeCompte");
        return query.list();
    }
    
    public void setInformationIntoLabel(ListSelectionEvent e) {
        int selectedRow = f.getUserTable().getSelectedRow();
        if (selectedRow != -1) {
        	DefaultTableModel model = (DefaultTableModel) f.getUserModel();
            
        	
        	f.getAddButton().setText("Modifier");
        	this.isModif = true;
        	// Recupération des informations
        	
        	String id =  model.getValueAt(selectedRow, 0).toString();
            String nom =  model.getValueAt(selectedRow, 1).toString();
            String mail =  model.getValueAt(selectedRow, 2).toString(); 
            String siteLibelle = model.getValueAt(selectedRow, 3).toString();
            String typeCompte =  model.getValueAt(selectedRow, 4).toString();
            
            selectSiteInComboBox(siteLibelle);
            selectTypeCompteInComboBox(typeCompte);
            f.getLblIDInput().setText(id);
            f.getUsernameField().setText(nom);
            f.getMailBox().setText(mail);
            
            
        }
        
    }
    
    
    
    private void selectSiteInComboBox(String siteLibelle) {
        JComboBox<Site> siteComboBox = f.getSiteComboBox();
        ComboBoxModel<Site> siteComboBoxModel = siteComboBox.getModel();

        for (int i = 0; i < siteComboBoxModel.getSize(); i++) {
            Site site = siteComboBoxModel.getElementAt(i);
            if (site.getLibelle().equals(siteLibelle)) {
                // Sélectionnez l'élément avec le libellé correspondant
                siteComboBox.setSelectedIndex(i);
                return; // Arrêtez la recherche une fois que vous avez trouvé et sélectionné l'élément
            }
        }
    }
    
    private void selectTypeCompteInComboBox(String typeCompteLibelle) {
        JComboBox<TypeCompte> typeComboBox = f.getTypeComboBox();
        ComboBoxModel<TypeCompte> typeComboBoxModel = typeComboBox.getModel();

        for (int i = 0; i < typeComboBoxModel.getSize(); i++) {
            TypeCompte typeCompte = typeComboBoxModel.getElementAt(i);
            if (typeCompte.getLibelle().equals(typeCompteLibelle)) {
                // Sélectionnez l'élément avec le libellé correspondant
                typeComboBox.setSelectedIndex(i);
                return; // Arrêtez la recherche une fois que vous avez trouvé et sélectionné l'élément
            }
        }
    }
    
    
    private void onNewUser(ActionEvent e) {
    	f.getAddButton().setText("Ajouter");
    	this.isModif = false;
    	
    	f.getUserTable().clearSelection(); // Déselectionne tout
    	selectSiteInComboBox(f.getUserModel().getValueAt(0, 3).toString()); // Site par défaut = le premier de la liste
        selectTypeCompteInComboBox("Salarié");
        int nextID = getNextUserId();
        
        f.getLblIDInput().setText(String.valueOf(nextID));
        f.getUsernameField().setText("");
        f.getMailBox().setText("");
    }
    
    private int getNextUserId() {
        try {
            // Ouvrir une transaction
            Transaction transaction = session.beginTransaction();

            // Exécuter la requête HQL pour obtenir le maximum de l'ID
            Query query = session.createQuery("SELECT COALESCE(MAX(id), 0) FROM Utilisateur");
            int maxId = (int) query.uniqueResult();

            // Terminer la transaction
            transaction.commit();

            // Retourner maxID + 1
            return maxId + 1;
        } catch (Exception e) {
            // Gérer les exceptions selon vos besoins
            e.printStackTrace();
            return -1; // Valeur par défaut en cas d'erreur
        }
    }
    
}
