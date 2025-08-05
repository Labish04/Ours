/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package happytravell.controller;

import happytravell.view.AdminRegisterView;
import happytravell.view.SignupAsView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import happytravell.controller.AdminRegisterController;
import happytravell.view.LoginPageView;
import happytravell.view.TravellerRegisterView;

/**
 *
 * @author Acer
 */
public class SignupAsController {
     private SignupAsView signupAsView = new SignupAsView();

    public SignupAsController(SignupAsView signupAsView) {
        this.signupAsView = signupAsView;
        this.signupAsView.adminNavigation(new AdminNav());
        this.signupAsView.travellerNavigation(new TravellerNav());
        this.signupAsView.loginNavigation(new LoginNav());
    }
    void open() {
        this.signupAsView.setVisible(true);
    }

    void close() {
        this.signupAsView.dispose();
    }

    class AdminNav implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            AdminRegisterView adminView = new AdminRegisterView();
            AdminRegisterController adminRegisterController = new AdminRegisterController(adminView);
            adminRegisterController.open();
            close();
            
        }
    }

    class TravellerNav implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
           TravellerRegisterView travellerView = new TravellerRegisterView();
           TravellerRegisterController travellerRegisterController = new TravellerRegisterController(travellerView);
           travellerRegisterController.open();
           close();
        }
    }

class LoginNav implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            LoginPageView loginView = new LoginPageView();
            LoginController loginController = new LoginController(loginView);
            loginController.open();
            close();
            
        }
    }




}
