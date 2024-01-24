package profil;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProfilPage extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JLabel lblNom;
    private JLabel lblEmail;
    private JLabel lblEntreprise;
    private JLabel lblSite;
    private JLabel lblTypeCompte;
    private JPasswordField passwordFieldOld;
    private JPasswordField passwordFieldNew;
    private JPasswordField passwordFieldConfirm;
    private JButton btnModifierMotDePasse;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ProfilPage frame = new ProfilPage();
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
    public ProfilPage() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 609, 483);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        lblNom = new JLabel("Nom : ");
        lblNom.setBounds(10, 11, 278, 29);
        contentPane.add(lblNom);

        lblEmail = new JLabel("Email : ");
        lblEmail.setBounds(298, 11, 278, 29);
        contentPane.add(lblEmail);

        lblEntreprise = new JLabel("Entreprise : ");
        lblEntreprise.setBounds(10, 50, 278, 29);
        contentPane.add(lblEntreprise);

        lblSite = new JLabel("Site : ");
        lblSite.setBounds(298, 50, 278, 29);
        contentPane.add(lblSite);

        lblTypeCompte = new JLabel("Type de compte : ");
        lblTypeCompte.setBounds(10, 89, 278, 29);
        contentPane.add(lblTypeCompte);

        // Ajoutez d'autres JLabel pour afficher d'autres informations

        JLabel lblOldPassword = new JLabel("Ancien mot de passe : ");
        lblOldPassword.setBounds(10, 186, 278, 29);
        contentPane.add(lblOldPassword);

        passwordFieldOld = new JPasswordField();
        passwordFieldOld.setBounds(216, 186, 278, 29);
        contentPane.add(passwordFieldOld);

        JLabel lblNewPassword = new JLabel("Nouveau mot de passe : ");
        lblNewPassword.setBounds(10, 226, 278, 29);
        contentPane.add(lblNewPassword);

        passwordFieldNew = new JPasswordField();
        passwordFieldNew.setBounds(216, 226, 278, 29);
        contentPane.add(passwordFieldNew);

        JLabel lblConfirmPassword = new JLabel("Confirmer le nouveau mot de passe : ");
        lblConfirmPassword.setBounds(10, 266, 278, 29);
        contentPane.add(lblConfirmPassword);

        passwordFieldConfirm = new JPasswordField();
        passwordFieldConfirm.setBounds(216, 266, 278, 29);
        contentPane.add(passwordFieldConfirm);

        btnModifierMotDePasse = new JButton("Modifier le mot de passe");
        btnModifierMotDePasse.setBounds(216, 323, 278, 29);
        contentPane.add(btnModifierMotDePasse);

        btnModifierMotDePasse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Ajoutez votre logique pour modifier le mot de passe ici
                String oldPassword = new String(passwordFieldOld.getPassword());
                String newPassword = new String(passwordFieldNew.getPassword());
                String confirmNewPassword = new String(passwordFieldConfirm.getPassword());

                // Ajoutez votre logique de vérification et de modification du mot de passe ici
                if (validateAndChangePassword(oldPassword, newPassword, confirmNewPassword)) {
                    JOptionPane.showMessageDialog(contentPane, "Mot de passe modifié avec succès.");
                } else {
                    JOptionPane.showMessageDialog(contentPane, "La modification du mot de passe a échoué.", "Erreur",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    // Méthode de validation et de modification du mot de passe
    private boolean validateAndChangePassword(String oldPassword, String newPassword, String confirmNewPassword) {
        // Ajoutez votre logique de validation et de modification du mot de passe ici
        // Retournez true si la modification réussit, false sinon
        return true; // Exemple : toujours renvoyer true, à adapter selon vos besoins
    }
	public JLabel getLblEmail() {
		return lblEmail;
	}
	public JLabel getLblNom() {
		return lblNom;
	}
	public JPasswordField getPasswordFieldConfirm() {
		return passwordFieldConfirm;
	}
	public JButton getBtnModifierMotDePasse() {
		return btnModifierMotDePasse;
	}
	public JLabel getLblTypeCompte() {
		return lblTypeCompte;
	}
	public JLabel getLblSite() {
		return lblSite;
	}
	public JPasswordField getPasswordFieldNew() {
		return passwordFieldNew;
	}
	public JPasswordField getPasswordFieldOld() {
		return passwordFieldOld;
	}
	public JLabel getLblEntreprise() {
		return lblEntreprise;
	}
}
