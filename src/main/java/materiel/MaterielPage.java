package materiel;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.JComboBox;
import javax.swing.JButton;

public class MaterielPage extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private JButton btnAjouter;
	private JButton btnSupprimer;
	private JLabel lblInfo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MaterielPage frame = new MaterielPage();
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
	public MaterielPage() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 620, 410);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblInfo = new JLabel("Liste des catégories de l'entreprise <entreprise_name> sur le site <site_name>");
		lblInfo.setBounds(10, 10, 600, 20);
		contentPane.add(lblInfo);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 40, 500, 150);
		contentPane.add(scrollPane);

		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {},
			new String[] { "ID", "Libelle" }
		));
		scrollPane.setViewportView(table);

		btnAjouter = new JButton("Ajouter");
		btnAjouter.setBounds(170, 200, 89, 23);
		contentPane.add(btnAjouter);

		btnSupprimer = new JButton("Supprimer par ID");
		btnSupprimer.setBounds(280, 200, 150, 23);
		contentPane.add(btnSupprimer);
	}

	public JTable getTable() {
		return table;
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
}
