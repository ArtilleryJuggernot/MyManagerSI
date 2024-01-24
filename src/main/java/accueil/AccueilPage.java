package accueil;

import java.awt.EventQueue;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.border.EmptyBorder;

public class AccueilPage extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JLabel userNameLabel;
    private JLabel entrepriseNameLabel;
    private JLabel siteNameLabel;
    private JLabel roleNameLabel;
    private JButton adminButton;
    private JButton profilButton;
    private JButton logoutButton;
    private JButton logsButton;
    private JButton materialButton;
    private JButton sitesButton;
    private JButton stockButton;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    AccueilPage frame = new AccueilPage();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public AccueilPage() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 626, 429);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        // Labels pour afficher les informations utilisateur (à remplacer par vos données)
        userNameLabel = new JLabel("<html><b>Nom d'utilisateur : </b> ");
        userNameLabel.setBounds(10, 10, 400, 20);
        contentPane.add(userNameLabel);

        entrepriseNameLabel = new JLabel("<html><b>Nom de l'entreprise : </b>  ");
        entrepriseNameLabel.setBounds(10, 30, 400, 20);
        contentPane.add(entrepriseNameLabel);

        siteNameLabel = new JLabel("<html><b>Nom du site : </b>  ");
        siteNameLabel.setBounds(10, 50, 400, 20);
        contentPane.add(siteNameLabel);

        roleNameLabel = new JLabel("<html><b>Rôle : </b> ");
        roleNameLabel.setBounds(10, 70, 400, 20);
        contentPane.add(roleNameLabel);

        // Boutons (à cacher ou montrer dans le contrôleur)
        profilButton = new JButton("Page de Profil");
        profilButton.setBounds(10, 197, 150, 30);
        contentPane.add(profilButton);

        stockButton = new JButton("Gestion de Stock");
        stockButton.setBounds(185, 141, 150, 30);
        contentPane.add(stockButton);

        materialButton = new JButton("Gestion de Matériel");
        materialButton.setBounds(10, 141, 150, 30);
        contentPane.add(materialButton);

        sitesButton = new JButton("Gestion des Sites");
        sitesButton.setBounds(185, 197, 150, 30);
        contentPane.add(sitesButton);

        logsButton = new JButton("Accès aux Logs d'Applications");
        logsButton.setBounds(226, 314, 200, 30);
        contentPane.add(logsButton);

        adminButton = new JButton("Administration des Utilisateurs");
        adminButton.setBounds(10, 314, 200, 30);
        contentPane.add(adminButton);

        // Bouton de déconnexion en haut à droite
        logoutButton = new JButton("Se déconnecter");
        logoutButton.setBounds(390, 25, 123, 30);
        contentPane.add(logoutButton);
        
        

        // Ajout de l'image en bas à droite
        Image originalImage;
		try {
			originalImage = ImageIO.read(new File("src/img/ManageMySI.png"));
			Image scaledImage = originalImage.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
	        ImageIcon imageIcon = new ImageIcon(scaledImage);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        
    }
	public JLabel getUserNameLabel() {
		return userNameLabel;
	}
	public JLabel getEntrepriseNameLabel() {
		return entrepriseNameLabel;
	}
	public JLabel getSiteNameLabel() {
		return siteNameLabel;
	}
	public JLabel getRoleNameLabel() {
		return roleNameLabel;
	}
	public JButton getAdminButton() {
		return adminButton;
	}
	public JButton getProfilButton() {
		return profilButton;
	}
	public JButton getLogoutButton() {
		return logoutButton;
	}
	public JButton getLogsButton() {
		return logsButton;
	}
	public JButton getMaterialButton() {
		return materialButton;
	}
	public JButton getSitesButton() {
		return sitesButton;
	}
	public JButton getStockButton() {
		return stockButton;
	}
}
