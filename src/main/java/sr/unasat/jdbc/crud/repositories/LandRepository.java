package sr.unasat.jdbc.crud.repositories;

import sr.unasat.jdbc.crud.entities.Land;
import sr.unasat.jdbc.crud.services.Database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LandRepository {
    private final Connection connection;
    Statement stmt;
    PreparedStatement pstmt;
    public LandRepository() {
        this.connection = Database.getConnection();
    }

    public List<Land> findAllRecords() {
        List<Land> landList = new ArrayList<>();
        try {
            stmt = connection.createStatement();
            String sql = "select * from land";
            ResultSet rs = stmt.executeQuery(sql);
            //STEP 5: Extract data from result set
            while (rs.next()) {
                int id = rs.getInt("id");
                String naam = rs.getString("naam");
                landList.add(new Land(id, naam));
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return landList;
    }

    public int insertOneRecord(Land land) {
        int result = 0;
        try {
            String sql = "insert into land (naam) values(?)";
            pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, land.getNaam());
            result = pstmt.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public int deleteOneRecord(Land land) {
        int result = 0;
        try {
            String sql = "DELETE FROM land WHERE land.id = ?";
            pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, land.getId());
            result = pstmt.executeUpdate();
            System.out.println("deleted: " + land.getId());

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

}
