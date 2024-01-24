package util;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EventObject;

import javax.swing.AbstractCellEditor;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EventObject;

public class JButtonColumn extends AbstractCellEditor implements TableCellRenderer, TableCellEditor {
    private JButton renderButton;
    private JButton editButton;
    private Object editorValue;
    private boolean isEditorActive;
    private JTable table;
    private int currentRow;

    public JButtonColumn(JTable table, ActionListener editorListener) {
        this.table = table;
        this.renderButton = new JButton();
        this.editButton = new JButton();

        this.editButton.addActionListener(e -> {
            fireEditingStopped();
            editorListener.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "delete"));
        });

        System.out.println("Before setting ActionListener");
        this.editButton.addActionListener(editorListener);
        System.out.println("After setting ActionListener");
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        renderButton.setText("Supprimer");
        return renderButton;
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        editorValue = value;
        currentRow = row;
        editButton.setText("Supprimer");
        isEditorActive = true;
        return editButton;
    }

    @Override
    public Object getCellEditorValue() {
        return editorValue;
    }

    @Override
    public boolean isCellEditable(EventObject anEvent) {
        return true;
    }

    @Override
    public boolean shouldSelectCell(EventObject anEvent) {
        return true;
    }

    @Override
    public boolean stopCellEditing() {
        isEditorActive = false;
        return super.stopCellEditing();
    }

    @Override
    public void cancelCellEditing() {
        isEditorActive = false;
        super.cancelCellEditing();
    }

    public void actionPerformed(ActionEvent e) {
        if (!isEditorActive) {
            fireEditingStopped();
        }
    }
}
