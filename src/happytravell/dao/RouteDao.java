/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package happytravell.dao;

import happytravell.database.MysqlConnection;
import java.sql.PreparedStatement;
import happytravell.model.TravellerData;
import java.sql.*;

/**
 *
 * @author Acer
 */
public class RouteDao {
    MysqlConnection mySql = new MysqlConnection();

    public boolean route(TravellerData user){

        String query = "INSERT INTO route ( route_name,pickup_location,destination)"
                + "VALUES (?,?,?)";
        Connection conn = mySql.openConnection();
        if (conn == null) return false;
        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, user.getroute_name());
            stmt.setString(2, user.getpickup_location());
            stmt.setString(3, user.getdestination());

            int result = stmt.executeUpdate();
            return result > 0;
    }   catch (Exception e) {
            return false;
    }   finally {
        mySql.closeConnection(conn);
    }
    }
}
