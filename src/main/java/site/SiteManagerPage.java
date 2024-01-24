package site;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SiteManagerPage extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable table;
    private JTextField textFieldLibelle;
    private JButton btnInserer;
    private JButton btnSupprimer;
    private JScrollPane scrollPane;
    private JLabel lblTitle;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    SiteManagerPage frame = new SiteManagerPage();
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
    public SiteManagerPage() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 575, 420);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        // Titre
        lblTitle = new JLabel("Listes des sites de ");
        lblTitle.setBounds(10, 10, 300, 20);
        contentPane.add(lblTitle);

        // Tableau
        scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 40, 500, 150);
        contentPane.add(scrollPane);

        table = new JTable();
        table.setModel(new DefaultTableModel(
                new Object[][] {},
                new String[] { "ID", "Libelle", "Nombre de personnes sur le site" }) {
            Class<?>[] columnTypes = new Class[] { Integer.class, String.class, Integer.class };

            boolean[] columnEditable = new boolean[] { false, true, false };

            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return columnTypes[columnIndex];
            }

            @Override
            public boolean isCellEditable(int row, int column) {
                return columnEditable[column];
            }
        });
        
        for (int c = 0; c < table.getColumnCount(); c++)
        {
            Class<?> col_class = table.getColumnClass(c);
            if (c != 1)
            	table.setDefaultEditor(col_class, null);        // remove editor
        }

        scrollPane.setViewportView(table);

        // Formulaire pour insérer un site
        JLabel lblLibelle = new JLabel("Insérer un site");
        lblLibelle.setBounds(10, 200, 89, 14);
        contentPane.add(lblLibelle);

        textFieldLibelle = new JTextField();
        textFieldLibelle.setBounds(109, 201, 141, 20);
        contentPane.add(textFieldLibelle);
        textFieldLibelle.setColumns(10);

        btnInserer = new JButton("Inserer");
        btnInserer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Ajoutez votre logique pour insérer le site
                DefaultTableModel model = (DefaultTableModel) table.getModel();
                int newId = model.getRowCount() + 1; // ID auto-incrémenté (à adapter selon votre logique)
                model.addRow(new Object[] { newId, textFieldLibelle.getText(), 0 });
                textFieldLibelle.setText("");
            }
        });
        btnInserer.setBounds(277, 196, 133, 23);
        contentPane.add(btnInserer);

        // Formulaire pour supprimer un site
        JLabel lblSupprimer = new JLabel("Supprimer un site");
        lblSupprimer.setBounds(10, 230, 150, 14);
        contentPane.add(lblSupprimer);

        JTextField textFieldId = new JTextField();
        textFieldId.setBounds(109, 227, 141, 20);
        contentPane.add(textFieldId);

        btnSupprimer = new JButton("Supprimer le site");
        btnSupprimer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Ajoutez votre logique pour supprimer le site
                DefaultTableModel model = (DefaultTableModel) table.getModel();
                int idToDelete = Integer.parseInt(textFieldId.getText());
                for (int i = 0; i < model.getRowCount(); i++) {
                    int currentId = (int) model.getValueAt(i, 0);
                    if (currentId == idToDelete) {
                        model.removeRow(i);
                        break;
                    }
                }
                textFieldId.setText("");
            }
        });
        btnSupprimer.setBounds(260, 226, 150, 23);
        contentPane.add(btnSupprimer);
    }

    public JTextField getTextFieldLibelle() {
        return textFieldLibelle;
    }

    public JButton getBtnInserer() {
        return btnInserer;
    }

    public JButton getBtnSupprimer() {
        return btnSupprimer;
    }

    public JTable getTable() {
        return table;
    }

    public JScrollPane getScrollPane() {
        return scrollPane;
    }

    public JLabel getLblTitle() {
        return lblTitle;
    }

    public JPanel getContentPane() {
        return contentPane;
    }
}
