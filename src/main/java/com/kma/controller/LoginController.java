package com.kma.controller;

import com.kma.dao.UserDao;
import com.kma.model.User;
import com.kma.view.StaffView;
import com.kma.view.LoginView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginController {
    private UserDao userDao;
    private LoginView loginView;
    private StaffView staffView;

    public LoginController(LoginView view) {
        this.loginView = view;
        this.userDao = new UserDao();
        view.addLoginListener(new LoginListener());
    }

    public void showLoginView() {
        loginView.setVisible(true);
    }

    class LoginListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            User user = loginView.getUser();
            if (userDao.checkUser(user)) {
                // if login successfully, display the student management view
                staffView = new StaffView();
                StaffController staffController = new StaffController(staffView);
                staffController.showDataView();
                loginView.setVisible(false);
            } else {
                loginView.showMessage("Username hoặc password không đúng.");
            }
        }
    }
}
