package com.kma;

import com.kma.controller.LoginController;
import com.kma.view.CrudView;
import com.kma.view.LoginView;

public class Main {
    public static void main(String[] args) {
        LoginView view = new LoginView();
        LoginController controller = new LoginController(view);
        // show the login view
        controller.showLoginView();
    }
}