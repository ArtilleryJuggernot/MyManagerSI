package site;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import org.hibernate.Session;
import org.hibernate.query.Query;

import Entity.Entreprise;
import Entity.Site;
import Entity.Utilisateur;
import util.HibernateSessionFactory;

public class ControllerSite {
    private SiteManagerPage f;
    private Utilisateur user;
    private Session session;
    private Entreprise entreprise;

    public ControllerSite(SiteManagerPage f, Utilisateur user) {
        super();
        this.f = f;
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.user = user;

        this.session = HibernateSessionFactory.getSessionFactory().openSession();
        init();
        initTable();
        initButtons();
        f.setVisible(true);
    }

    private void init() {
        // Titre avec le nom de l'entreprise
        this.entreprise = session.get(Entreprise.class, user.getEntreprise_id());
        f.getLblTitle().setText("Listes des sites de " + entreprise.getNom());
    }

    private void initTable() {
        // Charger la liste des sites de l'entreprise
        List<Site> sites = getSitesByEntrepriseId(this.entreprise.getId());

        // Remplir le tableau avec les sites
        DefaultTableModel model = (DefaultTableModel) f.getTable().getModel();
        
        
        // clear
        if (model.getRowCount() > 0) {
            for (int i = model.getRowCount() - 1; i > -1; i--) {
                model.removeRow(i);
            }
        }
        
        for (Site site : sites) {
            model.addRow(new Object[] { site.getId(), site.getLibelle(), site.getNombrePersonnes(), "Supprimer" });
        }
    }

    private void initButtons() {
        // Bouton "Supprimer" pour chaque ligne
        f.getBtnSupprimer().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Récupérer la ligne sélectionnée
                int selectedRow = f.getTable().getSelectedRow();
                
                // Vérifier si une ligne est sélectionnée
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(f, "Veuillez sélectionner une ligne à supprimer.", "Erreur",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Récupérer l'ID du site à supprimer
                int siteId = (int) f.getTable().getValueAt(selectedRow, 0);

                // Supprimer le site dans la base de données
                deleteSite(siteId);

                // Supprimer la ligne du tableau
                DefaultTableModel model = (DefaultTableModel) f.getTable().getModel();
                model.removeRow(selectedRow);
            }
        });

        // Bouton "Insérer"
        f.getBtnInserer().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Désactiver le bouton pour éviter les clics multiples
                f.getBtnInserer().setEnabled(false);

                // Ajouter la logique pour insérer le site
                String libelle = f.getTextFieldLibelle().getText();
                if (libelle.isEmpty()) {
                    JOptionPane.showMessageDialog(f, "Veuillez saisir le libellé du site.", "Erreur",
                            JOptionPane.ERROR_MESSAGE);

                    // Réactiver le bouton
                    f.getBtnInserer().setEnabled(true);
                    return;
                }

                // Insérer dans la base de données
                int newSiteId = insertSite(libelle);

                // Mettre à jour le tableau
                //DefaultTableModel model = (DefaultTableModel) f.getTable().getModel();
                //model.addRow(new Object[] { newSiteId, libelle, 0, "Supprimer" });
                //f.getTextFieldLibelle().setText(""); // Effacer le champ de saisie

                initTable();
                
                // Réactiver le bouton
                f.getBtnInserer().setEnabled(true);
            }
        });
    }

    private List<Site> getSitesByEntrepriseId(int entrepriseId) {
        Query<Site> query = session.createQuery("FROM Site WHERE entreprise.id = :entrepriseId", Site.class);
        query.setParameter("entrepriseId", entrepriseId);
        return query.list();
    }

    private int insertSite(String libelle) {
        // Ouvrir une session Hibernate
        Session session = HibernateSessionFactory.getSessionFactory().openSession();

        int newSiteId = -1;

        try {
            // Créer une nouvelle instance de l'entité Site
            Site nouveauSite = new Site();
            nouveauSite.setLibelle(libelle);
            nouveauSite.setID_entreprise(user.getEntreprise_id());

            // Enregistrer le nouveau site dans la base de données
            session.beginTransaction();
            session.save(nouveauSite);
            session.getTransaction().commit();

            newSiteId = nouveauSite.getId();
        } catch (Exception e) {
            e.printStackTrace(); // À adapter en fonction de votre gestion des erreurs
        } finally {
            // Fermer la session Hibernate
            session.close();
        }

        return newSiteId;
    }

    private void deleteSite(int siteId) {
        // Ouvrir une session Hibernate
        Session session = HibernateSessionFactory.getSessionFactory().openSession();

        try {
            // Récupérer le site à supprimer
            Site siteToDelete = session.get(Site.class, siteId);

            // Supprimer le site dans la base de données
            if (siteToDelete != null) {
                session.beginTransaction();
                session.delete(siteToDelete);
                session.getTransaction().commit();
            }
        } catch (Exception e) {
            e.printStackTrace(); // À adapter en fonction de votre gestion des erreurs
        } finally {
            // Fermer la session Hibernate
            session.close();
        }
    }
}
