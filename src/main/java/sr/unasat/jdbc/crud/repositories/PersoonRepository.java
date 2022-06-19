package sr.unasat.jdbc.crud.repositories;

import sr.unasat.jdbc.crud.entities.Persoon;
import sr.unasat.jdbc.crud.services.Database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PersoonRepository {
    private final Connection connection;
    PreparedStatement pstmt;
    Statement stmt;


    public PersoonRepository() {
        this.connection = Database.getConnection();
    }

    public List<Persoon> findAllRecords() {
        List<Persoon> persoonList = new ArrayList<>();
        try {
            stmt = connection.createStatement();
            String sql = "select * from persoon";
            ResultSet rs = stmt.executeQuery(sql);
            System.out.println("resultset: " + rs);
            //STEP 5: Extract data from result set
            while (rs.next()) {
                // Retrieve by column name
                int id = rs.getInt("id");
                String naam = rs.getString("naam");
//
                persoonList.add(new Persoon(id, naam));
            }
            rs.close();


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return persoonList;
    }

    public int insertOneRecord(Persoon persoon) {
        int result;
        int insertId = 0;

        try {
            String sql = "insert into persoon (naam) values(?)";
            pstmt = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, persoon.getNaam());
            result = pstmt.executeUpdate();
            System.out.println("resultset: " + result);
            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    insertId = generatedKeys.getInt(1);
                } else {
                    throw new SQLException("Creating user failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return insertId;
    }

    public int updateOneRecord(Persoon persoon) {
        int result = 0;
        try {
            String sql = "UPDATE persoon SET naam = ? WHERE id=?";
            pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, persoon.getId());
            pstmt.setString(2, persoon.getNaam());
            result = pstmt.executeUpdate();
            System.out.println("resultset: " + result);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public int deleteOneRecord(Persoon persoon) {
        int result = 0;
        try {
            String sql = "DELETE FROM persoon WHERE persoon.id = ?";
            pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, persoon.getId());
            result = pstmt.executeUpdate();
            System.out.println("deleted: " + persoon.getId());

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

}
