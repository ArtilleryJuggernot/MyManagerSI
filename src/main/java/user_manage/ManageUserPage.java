package user_manage;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import org.hibernate.procedure.internal.Util;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import Entity.Site;
import Entity.TypeCompte;
import Entity.Utilisateur;

public class ManageUserPage extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private DefaultTableModel userModel;
    private JTable userTable;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JComboBox<Site> siteComboBox;
    private JComboBox<TypeCompte> typeComboBox;
    private JButton addButton;
    private JButton deleteButton;
    private JScrollPane scrollPane;
    private JTextField mailBox;
    private JLabel lblIDInput;
    private JButton btnNewUser;

    public ManageUserPage() {
        initializeUI();
    }

    private void initializeUI() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 699, 745);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        userModel = new DefaultTableModel();
        userModel.addColumn("Identifiant");
        userModel.addColumn("Nom d'utilisateur");
        userModel.addColumn("Adresse mail");
        userModel.addColumn("Site");
        userModel.addColumn("Type de compte");

        userTable = new JTable(userModel);
        contentPane.setLayout(null);

        scrollPane = new JScrollPane(userTable);
        scrollPane.setBounds(5, 5, 652, 459);
        contentPane.add(scrollPane);
        JLabel label = new JLabel("Type de compte:");
        label.setBounds(5, 608, 110, 23);
        contentPane.add(label);
        typeComboBox = new JComboBox<>();
        typeComboBox.setBounds(100, 608, 221, 23);
        contentPane.add(typeComboBox);
        passwordField = new JPasswordField(20);
        passwordField.setBounds(100, 574, 221, 23);
        contentPane.add(passwordField);
        JLabel label_1 = new JLabel("Mot de passe:");
        label_1.setBounds(5, 574, 105, 23);
        contentPane.add(label_1);
        JLabel label_2 = new JLabel("Site:");
        label_2.setBounds(5, 550, 89, 23);
        contentPane.add(label_2);
        deleteButton = new JButton("Supprimer");
        deleteButton.setBounds(204, 655, 171, 23);
        contentPane.add(deleteButton);
        
                addButton = new JButton("Ajouter");
                addButton.setBounds(5, 655, 171, 23);
                contentPane.add(addButton);
                JLabel label_3 = new JLabel("Nom d'utilisateur:");
                label_3.setBounds(5, 506, 105, 23);
                contentPane.add(label_3);
                
                        usernameField = new JTextField(20);
                        usernameField.setBounds(100, 506, 221, 23);
                        contentPane.add(usernameField);
                        
                        
                                siteComboBox = new JComboBox<>();
                                siteComboBox.setBounds(100, 550, 221, 23);
                                contentPane.add(siteComboBox);
                                
                                JLabel label_3_1 = new JLabel("Mail utilisateur :");
                                label_3_1.setBounds(5, 528, 105, 23);
                                contentPane.add(label_3_1);
                                
                                mailBox = new JTextField();
                                mailBox.setBounds(99, 529, 222, 20);
                                contentPane.add(mailBox);
                                mailBox.setColumns(10);
                                
                                JLabel lblID = new JLabel("ID : ");
                                lblID.setBounds(5, 481, 23, 14);
                                contentPane.add(lblID);
                                
                                lblIDInput = new JLabel("");
                                lblIDInput.setBounds(28, 481, 49, 14);
                                contentPane.add(lblIDInput);
                                
                                btnNewUser = new JButton("Créer un nouvel utilisateur");
                                btnNewUser.setBounds(384, 655, 188, 23);
                                contentPane.add(btnNewUser);

    }

    public DefaultTableModel getUserModel() {
        return userModel;
    }

    public JTable getUserTable() {
        return userTable;
    }

    public JTextField getUsernameField() {
        return usernameField;
    }

    public JPasswordField getPasswordField() {
        return passwordField;
    }

    public JComboBox<Site> getSiteComboBox() {
        return siteComboBox;
    }

    public JComboBox<TypeCompte> getTypeComboBox() {
        return typeComboBox;
    }
	public JScrollPane getScrollPane() {
		return scrollPane;
	}
	public JTextField getMailBox() {
		return mailBox;
	}
	public JLabel getLblIDInput() {
		return lblIDInput;
	}
	public JButton getBtnNewUser() {
		return btnNewUser;
	}
	public JButton getAddButton() {
		return addButton;
	}
	public JButton getDeleteButton() {
		return deleteButton;
	}
}
