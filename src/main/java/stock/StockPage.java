package stock;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import Entity.CatMat;
import Entity.Statut;

import javax.swing.JComboBox;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;

public class StockPage extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private JComboBox<String> comboBoxStatut;
	private JButton btnAjouter;
	private JButton btnSupprimer;
	private JLabel lblInfo;
	private JLabel lblStatut;
	private JLabel lblCat;
	private JComboBox<String> comboBoxCat;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StockPage frame = new StockPage();
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
	public StockPage() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 620, 410);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblInfo = new JLabel("Liste des ressources de l'entreprise <entreprise_name> sur le site <site_name>");
		lblInfo.setBounds(10, 10, 600, 20);
		contentPane.add(lblInfo);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 40, 500, 150);
		contentPane.add(scrollPane);

		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {},
			new String[] { "ID", "Libelle", "Description", "Statut" }
		));
		scrollPane.setViewportView(table);

		
		// Mettre les Statut 
        List<Statut> statuts = Statut.getAllStatuts();
        List<String> statutLibelles = new ArrayList<String>();
        for (Statut statut : statuts) {
			statutLibelles.add(statut.getLibelle());
		}
		
		comboBoxStatut = new JComboBox<String>();
		comboBoxStatut.setBounds(98, 201, 150, 20);
		// Ajouter les catégories ici, exemple :
		contentPane.add(comboBoxStatut);
		
		
		// Statut
		
		DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>(statutLibelles.toArray(new String[0]));
        comboBoxStatut.setModel(model);
        
		btnAjouter = new JButton("Ajouter");
		btnAjouter.setBounds(174, 291, 89, 23);
		contentPane.add(btnAjouter);

		btnSupprimer = new JButton("Supprimer par ID");
		btnSupprimer.setBounds(282, 291, 150, 23);
		contentPane.add(btnSupprimer);
		
		lblStatut = new JLabel("Statut :");
		lblStatut.setBounds(20, 204, 49, 14);
		contentPane.add(lblStatut);
		
		lblCat = new JLabel("Catégorie :");
		lblCat.setBounds(9, 239, 79, 14);
		contentPane.add(lblCat);
		
		
		// Catégorie
		
		comboBoxCat = new JComboBox();
		comboBoxCat.setBounds(98, 235, 150, 22);
		contentPane.add(comboBoxCat);
		
		List<CatMat> categories = CatMat.getAllCat();//Statut.getAllStatuts();
        List<String> categoriesLibelles = new ArrayList<String>();
        for (CatMat cat : categories) {
			categoriesLibelles.add(cat.getLibelle());
		}
		
		
		DefaultComboBoxModel<String> model2 = new DefaultComboBoxModel<>(categoriesLibelles.toArray(new String[0]));
        comboBoxCat.setModel(model2);
		
	}

	public JTable getTable() {
		return table;
	}

	public JComboBox<String> getComboBoxCategorie() {
		return comboBoxStatut;
	}

	public JButton getBtnAjouter() {
		return btnAjouter;
	}

	public JButton getBtnSupprimer() {
		return btnSupprimer;
	}

	public JLabel getLblInfo() {
		return lblInfo;
	}
	public JComboBox getComboBoxCat() {
		return comboBoxCat;
	}
}
