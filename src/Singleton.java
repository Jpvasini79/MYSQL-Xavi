import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 * Created by Jeix4 on 18/5/17.
 */
public class Singleton {
    private static Connection con=null;
    public static Connection getConnection(){
        try{
            if( con == null ){
                String driver="com.mysql.jdbc.Driver"; //el driver varia segun la DB que usemos
                String url="jdbc:mysql://localhost:3306/battleship";
                String pwd="1234";
                String usr="root";
                Class.forName(driver);
                con = DriverManager.getConnection(url,usr,pwd);
            }
        }
        catch(ClassNotFoundException ex){
            ex.printStackTrace();
        }
        catch(SQLException ex){
            ex.printStackTrace();
        }
        return con;
    }


    public static Connection getConn() {
        return con;
    }
}