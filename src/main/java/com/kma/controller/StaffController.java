package com.kma.controller;

import com.kma.dao.StaffDao;
import com.kma.model.Staff;
import com.kma.view.CrudView;
import com.kma.view.StaffView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.List;

public class StaffController {
    private StaffDao staffDao;
    private StaffView staffView;
    private CrudView crudView;

    public StaffController(StaffView view) {
        this.staffView = view;
        staffView.addAddMenuListener(new AddMenuListener());
        staffView.addEditMenuListener(new EditMenuListener());
        staffView.addDeleteMenuListener(new DeleteMenuListener());
        staffDao = new StaffDao();
        crudView = new CrudView();
        crudView.addSaveBtnListener(new SaveBtnListener());
    }

    public void showDataView() {
        List<Staff> staffList = staffDao.findAll();
        staffView.setVisible(true);
        staffView.showListStaff(staffList);
    }

    class AddMenuListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            crudView.setUpdateFlag(false);
            crudView.setVisible(true);
        }
    }

    class EditMenuListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            Staff staff = staffView.getStaffSelected();
            crudView.setStaffToTextField(staff);
            crudView.setUpdateFlag(true);
            crudView.setVisible(true);
        }
    }

    class DeleteMenuListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            staffDao.deleteStaff(staffView.getStaffIdSelected());
            staffView.showMessage("Delete staff successfully");
            staffView.showListStaff(staffDao.findAll());
        }
    }

    class SaveBtnListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            Staff staff = null;
            try {
                staff = crudView.getStaffFromTextField();
            } catch (ParseException ex) {
                ex.printStackTrace();
            }
            if(crudView.getUpdateFlag())
                staffDao.updateStaff(staff);
            else
                staffDao.addStaff(staff);
            crudView.showMessage("Save successfully");
            crudView.setVisible(false);
            staffView.showListStaff(staffDao.findAll());
        }
    }

}
