// MMSI

import org.hibernate.Session;

import Class.Entreprise;
import Login.ControllerLogin;
import Login.LoginPage;
import util.HibernateSessionFactory;

public class App {

	public static void main(String[] args) {
		System.out.println("Test");
		ControllerLogin c = new ControllerLogin(new LoginPage());
	
		Session session = HibernateSessionFactory.getSessionFactory().openSession();
		Entreprise entreprise = session.get(Entreprise.class, 1);
		
		
		System.out.println(entreprise);
	}

}

