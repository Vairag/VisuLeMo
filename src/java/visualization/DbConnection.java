package visualization;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.Serializable;
import java.sql.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author thomas
 */
@ManagedBean
@SessionScoped
public class DbConnection implements Serializable{


    /**
     * Creates a new instance of dbconnection
     */
    public DbConnection() {

    }

    public Connection getConnection() {
        
        Connection con = null;

        String url = "jdbc:mysql://localhost/visulemo";
        String user = "root";
        String password = "";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url, user, password);
            System.out.println("Connection to VisuLeMo is successfull !.");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DbConnection.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
        }
        return con;
    }
    
    public Connection getWebtraceConnection() {
        
        Connection con = null;

        String url = "jdbc:mysql://localhost/webtrace";
        String user = "root";
        String password = "";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url, user, password);
            System.out.println("Connection to webtrace is successfull !.");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DbConnection.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
        }
        return con;
    }
    
    
    public ResultSet getRS(String query){
        
        ResultSet rs = null;
        PreparedStatement pst = null;
        Connection con = getConnection();
        try {
            pst = con.prepareStatement(query);
            pst.execute();
            rs = pst.getResultSet();          
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return rs;
        
    }

    
    
    public void close(Connection con){
        try {
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(DbConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
