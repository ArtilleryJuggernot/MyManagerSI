package login;

import org.hibernate.Session;

import Entity.Utilisateur;
import accueil.AccueilController;
import accueil.AccueilPage;
import crypto.Encryption;
import util.HibernateSessionFactory;

public class ControllerLogin {
    private LoginPage f;

    public ControllerLogin(LoginPage f) {
        super();
        this.f = f;
        init();
        f.setVisible(true);
    }

    private void init() {
        // Ajout des écouteurs pour les boutons de la fenêtre
    	f.getConnectBTN().addActionListener(e -> onConnectButtonClick());
        f.getRegisterBTN().addActionListener(e -> openRegisterWindow());
    }

    // Fonction appelée lorsque le bouton "Se connecter" est cliqué
    private void onConnectButtonClick() {
        String email = f.getEmailTXTfield().getText();
        String password = f.getPasswordTXTField().getText();

        // TODO : Login
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
    
        Utilisateur utilisateur = session.byNaturalId(Utilisateur.class).using("Email", email).load();
        if(utilisateur != null) {
        	System.out.println(Encryption.hashPassword(password));
        	System.out.println(utilisateur.getPassword());
        	if(Encryption.hashPassword(password).equals(utilisateur.getPassword())) {
        		// Login succeed
        		AccueilController c = new AccueilController(new AccueilPage(),utilisateur);
        		f.setVisible(false);
        		

        	}
        	else {
        		System.out.println("Mot de passe incorrect");
        	}
        	
        }
        else {
        	System.out.println("Pas d'utilisateur");
        }
    }

    // Fonction appelée lorsque le bouton "Se créer un compte" est cliqué
    private void openRegisterWindow() {
        
    	ControllerRegister c = new ControllerRegister(new RegisterPage());
    }
}
