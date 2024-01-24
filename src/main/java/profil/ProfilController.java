package profil;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.hibernate.Session;

import Entity.Entreprise;
import Entity.Site;
import Entity.TypeCompte;
import Entity.Utilisateur;
import crypto.Encryption;
import util.HibernateSessionFactory;

public class ProfilController {
    private ProfilPage f;
    private Utilisateur user;
    private Session session;
    private Entreprise entreprise;

    public ProfilController(ProfilPage f, Utilisateur user) {
        this.f = f;
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.user = user;

        this.session = HibernateSessionFactory.getSessionFactory().openSession();
        init();
        initButtons();
        f.setVisible(true);
    }

    private void init() {
        // Afficher les informations de l'utilisateur
    	
        f.getLblNom().setText("Nom : " + user.getNom());
        f.getLblEmail().setText("Email : " + user.getEmail());

        // Ajoutez des lignes similaires pour afficher d'autres informations

        // Charger les informations de l'entreprise
        int entrepriseId = user.getEntreprise_id();
        this.entreprise = session.get(Entreprise.class, entrepriseId);
        if (entreprise != null) {
            f.getLblEntreprise().setText("Entreprise : " + entreprise.getNom());
        }
        Site site = session.get(Site.class, user.getSite_id());
        f.getLblSite().setText("Site : " +  site.getLibelle());
        
        TypeCompte tc = session.get(TypeCompte.class, user.getType_compteID());
        f.getLblTypeCompte().setText("Type de compte : "  + tc.getLibelle());
        
        
       
    }

    private void initButtons() {
        f.getBtnModifierMotDePasse().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Logique pour modifier le mot de passe
                String oldPassword = new String(f.getPasswordFieldOld().getPassword());
                String newPassword = new String(f.getPasswordFieldNew().getPassword());
                String confirmNewPassword = new String(f.getPasswordFieldConfirm().getPassword());

                if (validateAndChangePassword(oldPassword, newPassword, confirmNewPassword)) {
                    JOptionPane.showMessageDialog(f.getContentPane(), "Mot de passe modifié avec succès.");
                } else {
                    JOptionPane.showMessageDialog(f.getContentPane(), "La modification du mot de passe a échoué.", "Erreur",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    private boolean validateAndChangePassword(String oldPassword, String newPassword, String confirmNewPassword) {
        
    	if(user.getPassword().equals(Encryption.hashPassword(oldPassword))) {
    		
    		if(newPassword.equals(confirmNewPassword)) {
    			user.setPassword(Encryption.hashPassword(confirmNewPassword));
    			session.save(user);
    			 return true; 
    		}
    	}
    	return false;
    	
       
    }
}
