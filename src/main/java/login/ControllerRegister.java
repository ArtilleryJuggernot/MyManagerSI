package login;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;

import Entity.Entreprise;
import Entity.Site;
import Entity.Utilisateur;
import crypto.Encryption;

import org.hibernate.Session;
import org.hibernate.Query;
import util.HibernateSessionFactory;

public class ControllerRegister {
    private RegisterPage f;

    public ControllerRegister(RegisterPage f) {
        super();
        this.f = f;
        init();
        f.setVisible(true);
    }

    private void init() {
        f.getRegisterButton().addActionListener(e -> onRegisterButtonClicked());
    }

    private void onRegisterButtonClicked() {
        // Récupérer les valeurs des champs de la fenêtre d'inscription
    	
    	// TODO : Faire les if qui vont bien pour 
    	// - Regex
    	// - Verif pas 2 fois le même mail
    	
        String adminName = f.getAdminNameField().getText();
        String email = f.getEmailField().getText();
        char[] password = f.getPasswordField().getPassword();
        char[] confirmPassword = f.getConfirmPasswordField().getPassword();
        String companyName = f.getCompanyNameField().getText();

        // Vérifier si les mots de passe correspondent
        if (passwordMatch(password, confirmPassword)) {
            // Hasher le mot de passe (SHA-256)
        	
            String hashedPassword = Encryption.hashPassword(confirmPassword);

            // Enregistrer l'utilisateur dans la base de données
            registerUser(adminName, email, hashedPassword, companyName);
        } else {
            // Afficher un message d'erreur si les mots de passe ne correspondent pas
            // (vous pouvez également le faire dans l'interface graphique).
            System.out.println("Les mots de passe ne correspondent pas.");
        }
    }

    private boolean passwordMatch(char[] password, char[] confirmPassword) {
        // Comparer les deux mots de passe
        return new String(password).equals(new String(confirmPassword));
    }

    

    private void registerUser(String adminName, String email, String hashedPassword, String companyName) {
        // Code pour enregistrer l'utilisateur dans la base de données avec Hibernate
        // Assumez que vous avez une session Hibernate configurée (voir votre
        // configuration Hibernate).

        Session session = HibernateSessionFactory.getSessionFactory().openSession();

        try {
            // Creation de l'entreprise
            Entreprise newEntreprise = new Entreprise();
            newEntreprise.setNom(f.getCompanyNameField().getText());  // Remplacez par le nom réel de l'entreprise
            newEntreprise.setDateCreation(new Date(System.currentTimeMillis()));  // Utilisation de la date actuelle

            // Enregistrement de la nouvelle entreprise dans la base de données
            session.save(newEntreprise);
            
            Query query = session.createQuery("select max(id) from Entreprise");
            Integer maxEntrepriseId = (Integer) query.uniqueResult();
            int newEntrepriseId = (maxEntrepriseId != null) ? maxEntrepriseId + 1 : 1;


         // Récupération de l'ID de la nouvelle entreprise
            newEntreprise.setId(newEntrepriseId);

            System.out.println("LE ID QUI VA BIEN : " + newEntrepriseId);

            Query querySite = session.createQuery("select max(id) from Site");
            Integer maxSiteId = (Integer) querySite.uniqueResult();
            int newSiteId = (maxSiteId != null) ? maxSiteId + 1 : 1;

            
            // Création du site
            Site defaultSite = new Site();
            defaultSite.setLibelle("Site Principal");
            defaultSite.setID_entreprise(newEntrepriseId);
            defaultSite.setId(newSiteId);
            // Enregistrement du nouveau site dans la base de données
            session.save(defaultSite);

            
            
            
            Utilisateur newUser = new Utilisateur();
            newUser.setNom(adminName);
            newUser.setEmail(email);
            newUser.setPassword(hashedPassword);
            newUser.setCreate_at(new Date(System.currentTimeMillis()));
            newUser.setEntreprise_id(newEntrepriseId);
            newUser.setSite_id(newSiteId);
            newUser.setType_compteID(1); // Administrateur

            session.save(newUser);
            
            f.setVisible(false);

            System.out.println("Utilisateur et Entreprise enregistré avec succès.");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
    }
}
