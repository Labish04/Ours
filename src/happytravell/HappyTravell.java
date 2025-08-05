/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package happytravell;

import happytravell.controller.LoginController;
import happytravell.view.LoginPageView;


public class HappyTravell {
   public static void main(String[] args) {
      
       LoginPageView view = new LoginPageView();
       LoginController controller = new LoginController(view);
       controller.open();
       
        
    }
    
}
