package login;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;

public class LoginPage extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField EmailTXTfield;
	private JPasswordField passwordTXTField;
	private JButton ConnectBTN;
	private JButton RegisterBTN;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginPage frame = new LoginPage();
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
	public LoginPage() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 470, 365);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel ManageMySI = new JLabel("Manage My SI");
		ManageMySI.setFont(new Font("Tahoma", Font.PLAIN, 18));
		ManageMySI.setBounds(182, 31, 159, 43);
		contentPane.add(ManageMySI);
		
		JLabel Email = new JLabel("Email : ");
		Email.setFont(new Font("Tahoma", Font.PLAIN, 14));
		Email.setBounds(62, 98, 49, 14);
		contentPane.add(Email);
		
		EmailTXTfield = new JTextField();
		EmailTXTfield.setBounds(147, 97, 96, 20);
		contentPane.add(EmailTXTfield);
		EmailTXTfield.setColumns(10);
		
		passwordTXTField = new JPasswordField();
		passwordTXTField.setColumns(10);
		passwordTXTField.setBounds(147, 147, 96, 20);
		contentPane.add(passwordTXTField);
	
		JLabel password = new JLabel("Password :");
		password.setFont(new Font("Tahoma", Font.PLAIN, 14));
		password.setBounds(53, 148, 68, 14);
		contentPane.add(password);
		
		ConnectBTN = new JButton("Se connecter");
		ConnectBTN.setBounds(182, 193, 114, 20);
		contentPane.add(ConnectBTN);
		
		RegisterBTN = new JButton("Se créer un compte");
		RegisterBTN.setBounds(160, 257, 164, 32);
		contentPane.add(RegisterBTN);
	}
	public JButton getConnectBTN() {
		return ConnectBTN;
	}
	public JTextField getEmailTXTfield() {
		return EmailTXTfield;
	}
	public JTextField getPasswordTXTField() {
		return passwordTXTField;
	}
	public JButton getRegisterBTN() {
		return RegisterBTN;
	}
}
