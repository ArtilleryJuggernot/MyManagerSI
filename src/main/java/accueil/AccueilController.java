package accueil;

import java.awt.Frame;
import java.sql.Date;

import org.hibernate.Session;

import Entity.Entreprise;
import Entity.Matériel;
import Entity.Site;
import Entity.TypeCompte;
import Entity.Utilisateur;
import login.ControllerLogin;
import login.LoginPage;
import materiel.MaterielController;
import materiel.MaterielPage;
import profil.ProfilController;
import profil.ProfilPage;
import site.ControllerSite;
import site.SiteManagerPage;
import stock.StockController;
import stock.StockPage;
import user_manage.ManageUserController;
import user_manage.ManageUserPage;
import util.HibernateSessionFactory;


public class AccueilController {
    private AccueilPage f;
    private Utilisateur user;
    private Session session;
    public AccueilController(AccueilPage f, Utilisateur user) {
        super();
        this.f = f;
        this.user = user;
        this.session = HibernateSessionFactory.getSessionFactory().openSession();
        init();
        f.setVisible(true);
    }

    private void init() {
        setInformation();
        displayOnlyAuth();
        btnAssign();
    }
    
    
    private void setInformation() {
    	// Récupération des informations nécessaires
    	
    	String NameUtilisateur = user.getNom();
    	String NameEntreprise = session.get(Entreprise.class, user.getEntreprise_id()).getNom();
    	String NameSite = session.get(Site.class,user.getSite_id()).getLibelle();
    	String NameRole = session.get(TypeCompte.class,user.getType_compteID()).getLibelle();
    	

    	
    	
    	f.getUserNameLabel().setText(f.getUserNameLabel().getText() + NameUtilisateur + "</html>");
    	f.getEntrepriseNameLabel().setText(f.getEntrepriseNameLabel().getText() + NameEntreprise + "</html>");
    	
    	f.getSiteNameLabel().setText(f.getSiteNameLabel().getText() + NameSite + "</html>");

    	f.getRoleNameLabel().setText(f.getRoleNameLabel().getText() + NameRole + "</html>");
    	

    }

    
    private void displayOnlyAuth() {
    	if (user.getType_compteID() > 1) { // n'est pas administrateur
    		this.f.getAdminButton().setVisible(false);
    		this.f.getLogsButton().setVisible(false);
    	}
    	
    	if (user.getType_compteID() == 3) { // salarié
    		this.f.getSitesButton().setVisible(false);
    	}
    }
    
    private void btnAssign() {
    	f.getSitesButton().addActionListener(e -> onSitesButton());
    	f.getLogoutButton().addActionListener(e -> disconnect());
    	f.getProfilButton().addActionListener(e -> onProfilButton());
    	f.getMaterialButton().addActionListener(e -> onMaterielButton());
    	f.getStockButton().addActionListener(e -> onStockButton());
    	f.getAdminButton().addActionListener(e -> onAdminButton());
    }
    
    private void onSitesButton() {
    	ControllerSite c = new ControllerSite(new SiteManagerPage(),user);
		//f.setVisible(false);
    }
    
    private void onProfilButton(){
    	ProfilController c = new ProfilController(new ProfilPage(), user);
    }
    
    
    private void onMaterielButton() {
    	MaterielController c = new MaterielController(new MaterielPage(), user);
    }
    
    private void onStockButton() {
    	StockController c = new StockController(new StockPage(), user);
    }
    
    private void onAdminButton() {
    	ManageUserController c = new ManageUserController(new ManageUserPage(), user);
    }
    
    
    
    private void disconnect() {
    	Frame[] frames = Frame.getFrames();
    	for (Frame frame : frames) {
			frame.setVisible(false);
		}
    	
    	// TODO 
    	user.setLast_co(new Date(System.currentTimeMillis()));
        session.update(user);
        session.save(user);

		ControllerLogin c = new ControllerLogin(new LoginPage());
    }
   
}
