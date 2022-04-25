package AccountDaoPkg;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionManager {

    private static Connection connection;

    private static String connectionString = "jdbc:postgresql://heffalump.db.elephantsql.com:5432/cooxempx";
    private static String username = "cooxempx";
    private static String password = "A6ogaUhI-fGPCvdt30R-CBqb1GIqnszG";

    public static Connection getConnection(){
        try {
            if(connection == null || connection.isClosed()){
                Class.forName("org.postgresql.Driver");
                connection = DriverManager.getConnection(connectionString, username, password);
                }
                return connection;
            } catch (Exception e){

            } return null;
    }

}
