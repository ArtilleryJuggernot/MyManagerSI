package com.hj.mymanagersi.mymanagersi;

//MMSI

import org.hibernate.Session;

import Entity.Entreprise;
import login.ControllerLogin;
import login.LoginPage;
import util.HibernateSessionFactory;



import org.hibernate.Session;
import util.HibernateSessionFactory;

public class App {
    public static void main(String[] args) {
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            session.beginTransaction();

            // Exemple : récupérer une entreprise par son ID
            int entrepriseId = 1;
            Entreprise entreprise = session.get(Entreprise.class, entrepriseId);

            if (entreprise != null) {
                System.out.println("Entreprise récupérée : " + entreprise);
            } else {
                System.out.println("Aucune entreprise trouvée avec l'ID : " + entrepriseId);
            }

            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

//public class App {
//
//	public static void main(String[] args) {
//		System.out.println("Test");
//		ControllerLogin c = new ControllerLogin(new LoginPage());
//	
//		Session session = HibernateSessionFactory.getSessionFactory().openSession();
//		Entreprise entreprise = session.get(Entreprise.class, 1);
//		
//		
//		System.out.println(entreprise);
//	}
//
//}
//
