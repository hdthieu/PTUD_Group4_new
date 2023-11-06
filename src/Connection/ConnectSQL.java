
package Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Tri Hieu
 */
public class ConnectSQL {
    public static Connection con = null;
    private static ConnectSQL instance = new ConnectSQL();

    public static ConnectSQL getInstance(){
        return instance;
    }
    public static void connect(){
        String url = "jdbc:sqlserver://localhost:1433; databasename=GROUP4";
        String user = "sa";
        String password = "123";
        try {
            con = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }       
    } 
    public static Connection getConnection(){
    if(con == null){
        new ConnectSQL().connect();
    }
    return con;
}
    public void disconnect(){
         if(con != null){
            try {
                con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void closeConnection() {
        if(con != null){
        try {
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    }
}
    

