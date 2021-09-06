/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Void
 */
public class Search {
   
    static int mobile = 0;
  
    public static ArrayList<ProductList> mobileSearch(String model){
        ArrayList<ProductList> list = new ArrayList<>();
        try {
            Connection con = DriverManager.getConnection("jdbc:sqlite:DBs/mobileDB.db");
            PreparedStatement ps = con.prepareStatement("SELECT * FROM mobiles WHERE mbrand=? COLLATE NOCASE OR mmodel=? COLLATE NOCASE");
            ps.setString(1, model);
            ps.setString(2, model);
            ResultSet rs = ps.executeQuery();
            
            ProductList pl, gl, kl=null;
            
            while(rs.next()){
                pl = new ProductList(rs.getString("mbrand"),rs.getString("mmodel"),
                        rs.getInt("mprice"),rs.getInt("mquantity"),rs.getString("mdescription"),
                        rs.getString("mphoto"));
                 mobile++;
                
                list.add(pl);

            }

        } catch (SQLException ex) {
            Logger.getLogger(MobileDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
   }
    
}
