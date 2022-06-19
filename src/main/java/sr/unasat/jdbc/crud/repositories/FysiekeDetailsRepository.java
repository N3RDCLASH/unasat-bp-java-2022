package sr.unasat.jdbc.crud.repositories;

import sr.unasat.jdbc.crud.entities.FysiekeDetails;
import sr.unasat.jdbc.crud.entities.Persoon;
import sr.unasat.jdbc.crud.services.Database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FysiekeDetailsRepository {
    final Connection connection;
    Statement stmt;
    PreparedStatement pstmt;

    public FysiekeDetailsRepository() {
        this.connection = Database.getConnection();
    }

    public List<FysiekeDetails> findAllRecords() {
        List<FysiekeDetails> FysiekeDetailsList = new ArrayList<>();

        try {
            stmt = connection.createStatement();
            String sql = "select lengte,gewicht,geslacht,persoon_fysiek_details.id as fysieke_details_id,persoon.naam,persoon.id as persoon_id from persoon_fysiek_details inner join persoon on persoon_fysiek_details.persoon_id = persoon.id";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt("fysieke_details_id");
                int gewicht = rs.getInt("gewicht");
                int lengte = rs.getInt("lengte");
                Persoon persoon = new Persoon(rs.getInt("fysieke_details_id"), rs.getString("naam"));
                String geslacht = rs.getString("geslacht");
                FysiekeDetailsList.add(new FysiekeDetails(id, lengte, gewicht, geslacht, persoon));
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Fysieke details ophalen heeft gefaald.");
        }
        return FysiekeDetailsList;
    }

    public FysiekeDetails findOneRecord(Persoon persoon) {
        FysiekeDetails fysiekeDetails = null;
        try {
            String sql = "select lengte,gewicht,geslacht,persoon_fysiek_details.id as fysieke_details_id,persoon.naam,persoon.id as persoon_id from persoon_fysiek_details inner join persoon on persoon_fysiek_details.persoon_id = persoon.id where persoon_fysiek_details.persoon_id = ?";
            pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, persoon.getId());
            ResultSet rs = pstmt.executeQuery();
            
            //STEP 5: Extract data from result set
            while (rs.next()) {
                //Retrieve by column name
                int id = rs.getInt("fysieke_details_id");
                int gewicht = rs.getInt("gewicht");
                int lengte = rs.getInt("lengte");
                String geslacht = rs.getString("geslacht");
                fysiekeDetails = new FysiekeDetails(id, lengte, gewicht, geslacht, persoon);
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Fysieke details ophalen heeft gefaald");
        }
        return fysiekeDetails;
    }

    public int insertOneRecord(FysiekeDetails fysiekeDetails) {
        int result;
        int insertId = 0;
        try {
            String sql = "insert into persoon_fysiek_details (lengte,gewicht,geslacht,persoon_id) values(?,?,?,?)";
            pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, fysiekeDetails.getLengte());
            pstmt.setFloat(2, fysiekeDetails.getGewicht());
            pstmt.setString(3, fysiekeDetails.getGeslacht());
            pstmt.setInt(4, fysiekeDetails.getPersoon().getId());
            result = pstmt.executeUpdate();
            
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

    public int updateOneRecord(FysiekeDetails fysiekeDetails) {
        int result = 0;
        try {
            String sql = "UPDATE persoon_fysiek_details SET lengte=?,gewicht=?,geslacht=? WHERE persoon_id = ?";
            pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, fysiekeDetails.getLengte());
            pstmt.setFloat(2, fysiekeDetails.getGewicht());
            pstmt.setString(3, fysiekeDetails.getGeslacht());
            pstmt.setInt(4, fysiekeDetails.getPersoon().getId());
            result = pstmt.executeUpdate();
            

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public int deleteOneRecord(Persoon persoon) {
        int result = 0;
        try {
            String sql = "DELETE FROM persoon_fysiek_details WHERE persoon_id = ?";
            pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, persoon.getId());
            result = pstmt.executeUpdate();
            System.out.println("deleted: " + persoon.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public int deleteOneRecord(FysiekeDetails fysiekeDetails) {
        int result = 0;
        try {
            String sql = "DELETE FROM persoon_fysiek_details WHERE id = ?";
            pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, fysiekeDetails.getId());
            result = pstmt.executeUpdate();
            System.out.println("deleted: " + fysiekeDetails.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}

