package sr.unasat.jdbc.crud.services;

import io.github.cdimascio.dotenv.Dotenv;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseService  {
    private static DatabaseService instance;
    private static Connection connection;

    private DatabaseService(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("De driver is geregistreerd!");
            Dotenv dotenv = Dotenv.load();

            String URL = dotenv.get("DATABASE_URL");
            String USER = dotenv.get("DATABASE_USER");
            String PASSWORD = dotenv.get("DATABASE_PASSWORD");

            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("De database verbinding is gemaakt!");
        } catch (ClassNotFoundException e) {
            System.out.println("Error: unable to load driver class!");
            throw new RuntimeException(e);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public static Connection getConnection(){
        if (instance == null) {
            instance = new DatabaseService();
        }
        return connection;
    }
}
