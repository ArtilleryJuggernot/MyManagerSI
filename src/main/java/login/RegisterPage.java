package login;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;

public class RegisterPage extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField adminNameField;
    private JTextField emailField;
    private JPasswordField passwordField;
    private JPasswordField confirmPasswordField;
    private JTextField companyNameField;
    private JButton registerButton;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    RegisterPage frame = new RegisterPage();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public RegisterPage() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 545, 385);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel Register = new JLabel("Register");
        Register.setFont(new Font("Tahoma", Font.PLAIN, 18));
        Register.setBounds(228, 11, 120, 33);
        contentPane.add(Register);

        JLabel lblAdminName = new JLabel("Nom de l'administrateur:");
        lblAdminName.setBounds(38, 68, 160, 14);
        contentPane.add(lblAdminName);

        adminNameField = new JTextField();
        adminNameField.setBounds(208, 65, 200, 20);
        contentPane.add(adminNameField);
        adminNameField.setColumns(10);

        JLabel lblEmail = new JLabel("Adresse mail:");
        lblEmail.setBounds(38, 103, 120, 14);
        contentPane.add(lblEmail);

        emailField = new JTextField();
        emailField.setBounds(208, 100, 200, 20);
        contentPane.add(emailField);
        emailField.setColumns(10);

        JLabel lblPassword = new JLabel("Mot de passe:");
        lblPassword.setBounds(38, 138, 120, 14);
        contentPane.add(lblPassword);

        passwordField = new JPasswordField();
        passwordField.setBounds(208, 135, 200, 20);
        contentPane.add(passwordField);

        JLabel lblConfirmPassword = new JLabel("Confirmer le mot de passe:");
        lblConfirmPassword.setBounds(38, 173, 160, 14);
        contentPane.add(lblConfirmPassword);

        confirmPasswordField = new JPasswordField();
        confirmPasswordField.setBounds(208, 170, 200, 20);
        contentPane.add(confirmPasswordField);

        JLabel lblCompanyName = new JLabel("Nom de l'entreprise:");
        lblCompanyName.setBounds(38, 208, 160, 14);
        contentPane.add(lblCompanyName);

        companyNameField = new JTextField();
        companyNameField.setBounds(208, 205, 200, 20);
        contentPane.add(companyNameField);
        companyNameField.setColumns(10);

        registerButton = new JButton("S'inscrire");
        registerButton.setBounds(208, 236, 120, 23);
        contentPane.add(registerButton);
    }
	public JTextField getAdminNameField() {
		return adminNameField;
	}
	public JTextField getEmailField() {
		return emailField;
	}
	public JPasswordField getPasswordField() {
		return passwordField;
	}
	public JPasswordField getConfirmPasswordField() {
		return confirmPasswordField;
	}
	public JTextField getCompanyNameField() {
		return companyNameField;
	}
	public JButton getRegisterButton() {
		return registerButton;
	}
}
