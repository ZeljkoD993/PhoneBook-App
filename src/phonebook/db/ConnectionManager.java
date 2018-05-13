package phonebook.db;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {

    private  static ConnectionManager instance = null;

    private static final String USERNAME = "root";
    private static final String PASSWORD = "";
    private static final String DB_NAME = "jdbc:mysql://localhost/phonebook?useSSL=false&serverTimezone=UTC\n";

    private Connection connection = null;

    ConnectionManager(){

    }

    public static ConnectionManager getInstance(){
        if(instance == null){
            instance = new ConnectionManager();
        }
        return instance;
    }

    private boolean openConnection(){
        try{
            connection = DriverManager.getConnection(DB_NAME,USERNAME,PASSWORD);
            return true;
        } catch(SQLException e){
            System.err.println(e);
            return false;
        }

    }

    public Connection getConnection(){
        if (connection == null){
            if (openConnection()){
                System.out.println("Konekcija otvorena");
                return connection;
            } else{
                return null;
            }
        }
        return connection;
    }

    public void close(){
        System.out.println("Konekcija zatvorena");
        try{
            connection.close();
            connection = null;
        } catch(Exception e){

        }
    }

}
