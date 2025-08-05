/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package happytravell.controller;

import happytravell.view.AdminReviewsDetailsView;

/**
 *
 * @author Acer
 */
public class AdminReviewsDetailsController {
    private AdminReviewsDetailsView ReviewView;
    public AdminReviewsDetailsController(AdminReviewsDetailsView adminReviewsDetailsView) {
        this.ReviewView = adminReviewsDetailsView;
    }
    public void open(){
    this.ReviewView.setVisible(true);
    } 
    public void close(){
    this.ReviewView.dispose();
    }
}
