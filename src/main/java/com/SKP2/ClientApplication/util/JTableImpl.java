package com.SKP2.ClientApplication.util;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class JTableImpl extends JTable {
    public JTableImpl(String[] header, Object[][] data) {
        setModel(new DefaultTableModel(data, header) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        });

        getTableHeader().setForeground(Color.WHITE);
        getTableHeader().setBackground(Color.GREEN);
        setForeground(Color.BLACK);
        setBackground(Color.DARK_GRAY);
    }

    public void setMyTableHeaderBackground(Color color) {
        getTableHeader().setBackground(color);
    }
}
