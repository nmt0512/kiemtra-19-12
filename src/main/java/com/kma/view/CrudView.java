package com.kma.view;

import com.kma.model.Staff;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class CrudView extends JFrame implements ActionListener {
    private Boolean updateFlag;
    private JLabel idLabel;
    private JLabel nameLabel;
    private JLabel birthdayLabel;
    private JLabel phoneLabel;
    private JLabel emailLabel;
    private JLabel genderLabel;
    private JLabel departmentLabel;
    private JTextField idField;
    private JTextField nameField;
    private JTextField birthdayField;
    private JTextField phoneField;
    private JTextField emailField;
    private JRadioButton maleBtn;
    private JRadioButton femaleBtn;
    private JButton saveBtn;
    private ButtonGroup genderBtnGroup;
    private JComboBox<String> departmentComboBox;
    private String[] comboBoxData = new String[]{"Công nghệ thông tin", "Khảo thí", "Hành chính", "Đào tạo"};

    public CrudView() {
        initComponent();
    }

    private void initComponent() {
        updateFlag = false;
        idLabel = new JLabel("Id");
        nameLabel = new JLabel("Name");
        phoneLabel = new JLabel("Phone");
        birthdayLabel = new JLabel("Birthday");
        emailLabel = new JLabel("Email");
        genderLabel = new JLabel("Gender");
        departmentLabel = new JLabel("Department");
        idField = new JTextField(10);
        nameField = new JTextField(20);
        birthdayField = new JTextField(15);
        phoneField = new JTextField(15);
        emailField = new JTextField(20);
        maleBtn = new JRadioButton("Male");
        femaleBtn = new JRadioButton("Female");
        genderBtnGroup = new ButtonGroup();
        genderBtnGroup.add(maleBtn);
        genderBtnGroup.add(femaleBtn);
        departmentComboBox = new JComboBox<>(comboBoxData);
        saveBtn = new JButton("Save");
        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        JPanel panel3 = new JPanel();
        JPanel panel4 = new JPanel();
        JPanel panel5 = new JPanel();
        JPanel panel6 = new JPanel();
        JPanel panel7 = new JPanel();
        JPanel panel8 = new JPanel();

        panel1.add(idLabel);
        panel2.add(nameLabel);
        panel3.add(phoneLabel);
        panel4.add(birthdayLabel);
        panel5.add(emailLabel);
        panel6.add(genderLabel);
        panel7.add(departmentLabel);
        panel8.add(saveBtn);

        panel1.add(idField);
        panel2.add(nameField);
        panel3.add(phoneField);
        panel4.add(birthdayField);
        panel5.add(emailField);
        panel6.add(maleBtn);
        panel6.add(femaleBtn);
        panel7.add(departmentComboBox);

        FlowLayout flowLayout = new FlowLayout();
        flowLayout.setHgap(10);
        flowLayout.setVgap(10);
        flowLayout.setAlignment(FlowLayout.LEFT);
        panel1.setLayout(flowLayout);
        panel2.setLayout(flowLayout);
        panel3.setLayout(flowLayout);
        panel4.setLayout(flowLayout);
        panel5.setLayout(flowLayout);
        panel6.setLayout(flowLayout);
        panel7.setLayout(flowLayout);
        panel8.setLayout(flowLayout);

        this.setSize(400, 400);
        this.setLayout(new GridLayout(8, 1));
        this.add(panel1);
        this.add(panel2);
        this.add(panel3);
        this.add(panel4);
        this.add(panel5);
        this.add(panel6);
        this.add(panel7);
        this.add(panel8);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    public void setUpdateFlag(Boolean flag) {
        this.updateFlag = flag;
    }

    public Boolean getUpdateFlag() {
        return updateFlag;
    }

    public void setStaffToTextField(Staff staff) {
        idField.setText(staff.getId().toString());
        nameField.setText(staff.getName());
        phoneField.setText(staff.getPhone());
        birthdayField.setText(staff.getBirthday().toString());
        emailField.setText(staff.getEmail());
        if (staff.getGender().trim().equals("Male"))
            maleBtn.setSelected(true);
        else
            femaleBtn.setSelected(true);
        departmentComboBox.setSelectedItem(staff.getDepartment());
    }

    public Staff getStaffFromTextField() throws ParseException {
        if (validateEmail() && validatePhone() && validateBirthday()) {
            Staff staff = new Staff();
            staff.setId(Integer.parseInt(idField.getText()));
            staff.setName(nameField.getText());
            staff.setPhone(phoneField.getText());

            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            Date parsedDate = dateFormat.parse(birthdayField.getText());
            staff.setBirthday(new Timestamp(parsedDate.getTime()));

            staff.setEmail(emailField.getText());
            if (maleBtn.isSelected())
                staff.setGender(maleBtn.getText());
            else if (femaleBtn.isSelected())
                staff.setGender(femaleBtn.getText());
            staff.setDepartment(departmentComboBox.getSelectedItem().toString());
            return staff;
        }
        if (!validateEmail()) {
            showMessage("Sai định dạng email");
            emailField.setFocusable(true);
        } else if(!validatePhone()){
            showMessage("Sai định dạng số điện thoại");
            phoneField.setFocusable(true);
        } else {
            showMessage("Sai định dạng ngày sinh");
            birthdayField.setFocusable(true);
        }
        return null;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }

    private Boolean validatePhone() {
        String regex = "[0-9]{10}";
        if (phoneField.getText().matches(regex))
            return true;
        return false;
    }

    private Boolean validateEmail() {
        String regex = "\\w+@\\w+\\.\\w+";
        if (emailField.getText().matches(regex))
            return true;
        return false;
    }

    private Boolean validateBirthday() {
        String regex = "\\d{2}/\\d{2}/\\d{4}";
        if (birthdayField.getText().matches(regex))
            return true;
        return false;
    }

    public void addSaveBtnListener(ActionListener listener) {
        saveBtn.addActionListener(listener);
    }
}
