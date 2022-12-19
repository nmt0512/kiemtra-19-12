package com.kma.view;

import com.kma.model.Staff;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;
import java.util.List;

public class StaffView extends JFrame implements ActionListener, ListSelectionListener {
    private JButton addStudentBtn;
    private JButton editStudentBtn;
    private JButton deleteStudentBtn;
    private JMenuBar menuBar;
    private JMenu menu;
    private JMenuItem addMenu;
    private JMenuItem editMenu;
    private JMenuItem deleteMenu;
    private JTable dataTable;
    private JScrollPane scrollPaneDataTable;

    private String [] columnNames = new String [] {"ID", "Name", "Phone", "Birthday", "Email", "Gender", "Department"};
    private Object data = new Object [][] {};

    public StaffView() {
        initComponents();
    }

    private void initComponents()
    {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        addStudentBtn = new JButton("Add");
        editStudentBtn = new JButton("Edit");
        deleteStudentBtn = new JButton("Delete");
        menuBar = new JMenuBar();
        menu = new JMenu("CRUD");
        addMenu = new JMenuItem("Add");
        editMenu = new JMenuItem("Edit");
        deleteMenu = new JMenuItem("Delete");
        menuBar.add(menu);
        menu.add(addMenu);
        menu.add(editMenu);
        menu.add(deleteMenu);
        dataTable = new JTable();
        dataTable.setModel(new DefaultTableModel((Object[][]) data, columnNames));
        scrollPaneDataTable = new JScrollPane();
        scrollPaneDataTable.setViewportView(dataTable);
        scrollPaneDataTable.setPreferredSize(new Dimension(790,380));

        JPanel panel = new JPanel();
        panel.setSize(800, 450);
        panel.add(scrollPaneDataTable);
        panel.add(menuBar);

        SpringLayout layout = new SpringLayout();
        panel.setLayout(layout);
        layout.putConstraint(SpringLayout.NORTH, scrollPaneDataTable, 30, SpringLayout.NORTH, panel);

        this.add(panel);
        this.pack();
        this.setTitle("Student Information");
        this.setSize(800, 450);
        this.setLocationRelativeTo(null);
        this.setResizable(false);

    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    public void showListStaff(List<Staff> list) {
        int size = list.size();
        Object [][] staff = new Object[size][7];
        for (int i = 0; i < size; i++) {
            staff[i][0] = list.get(i).getId();
            staff[i][1] = list.get(i).getName();
            staff[i][2] = list.get(i).getPhone();
            staff[i][3] = list.get(i).getBirthday();
            staff[i][4] = list.get(i).getEmail();
            staff[i][5] = list.get(i).getGender();
            staff[i][6] = list.get(i).getDepartment();
        }
        dataTable.setModel(new DefaultTableModel(staff, columnNames));
        dataTable.getColumnModel().getColumn(0).setPreferredWidth(10);
        dataTable.getColumnModel().getColumn(1).setPreferredWidth(120);
        dataTable.getColumnModel().getColumn(4).setPreferredWidth(100);
        dataTable.getColumnModel().getColumn(6).setPreferredWidth(120);
    }

    public Integer getStaffIdSelected()
    {
        int row = dataTable.getSelectedRow();
        return Integer.parseInt(dataTable.getModel().getValueAt(row,0).toString());
    }

    public Staff getStaffSelected()
    {
        int row = dataTable.getSelectedRow();
        Staff staff = new Staff();
        staff.setId(Integer.parseInt(dataTable.getModel().getValueAt(row,0).toString()));
        staff.setName(dataTable.getModel().getValueAt(row,1).toString());
        staff.setPhone(dataTable.getModel().getValueAt(row,2).toString());
        staff.setBirthday(Timestamp.valueOf(dataTable.getModel().getValueAt(row,3).toString()));
        staff.setEmail(dataTable.getModel().getValueAt(row,4).toString());
        staff.setGender(dataTable.getModel().getValueAt(row,5).toString());
        staff.setDepartment(dataTable.getModel().getValueAt(row,6).toString());
        return staff;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
    }

    public void addAddMenuListener(ActionListener listener)
    {
        addMenu.addActionListener(listener);
    }
    public void addEditMenuListener(ActionListener listener)
    {
        editMenu.addActionListener(listener);
    }
    public void addDeleteMenuListener(ActionListener listener)
    {
        deleteMenu.addActionListener(listener);
    }
}
