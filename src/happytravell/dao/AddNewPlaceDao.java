/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package happytravell.dao;

import happytravell.database.MysqlConnection;
import happytravell.model.PlaceData;
import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 *
 * @author Acer
 */
public class AddNewPlaceDao {
    MysqlConnection mySql = new MysqlConnection();
    public boolean Register(PlaceData place){
        String query = "INSERT INTO places (place_name,description,place_image)"
                + "VALUES (?,?,?)";
        Connection conn = mySql.openConnection();
        if (conn == null) return false;
        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, place.getPlaceName());
            stmt.setString(2, place.getDescription());
            stmt.setBytes(3, place.getPlaceImage());

            int result = stmt.executeUpdate();
            return result > 0;
        }   catch (Exception e) {
            return false;
        }   finally {
            mySql.closeConnection(conn);
        }
    }
}
