package sr.unasat.jdbc.crud.repositories;

import sr.unasat.jdbc.crud.entities.ContactInformatie;
import sr.unasat.jdbc.crud.entities.Land;
import sr.unasat.jdbc.crud.entities.Persoon;
import sr.unasat.jdbc.crud.services.DatabaseService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContactInformatieRepository {
    private final Connection connection;
    private Statement stmt;
    private PreparedStatement pstmt;


    public ContactInformatieRepository() {
        this.connection = DatabaseService.getConnection();
    }

    public List<ContactInformatie> findAllRecords() {
        List<ContactInformatie> contactList = new ArrayList<>();
        try {
            stmt = connection.createStatement();
            String sql = "select ci.id, ci.adres, ci.telefoon_nummer , p.id pid, p.naam pnaam, l.id lid, l.naam land_naam" +
                    " from contact_informatie ci" +
                    " join persoon p" +
                    " on p.id = ci.persoon_id" +
                    " join land l  " +
                    " on l.id = ci.land_id";
            ResultSet rs = stmt.executeQuery(sql);
            System.out.println("resultset: " + rs);
            //STEP 5: Extract data from result set
            while (rs.next()) {
                //Retrieve by column name
                int id = rs.getInt("id");
                String adres = rs.getString("adres");
                int telefoonNummer = rs.getInt("telefoon_nummer");

                int persoonId = rs.getInt("pid");
                String persoonNaam = rs.getString("pnaam");
                Persoon persoon = new Persoon(persoonId, persoonNaam);

                int landId = rs.getInt("lid");
                String landNaam = rs.getString("land_naam");
                Land land = new Land(landId, landNaam);

                contactList.add(new ContactInformatie(id, adres, telefoonNummer, persoon, land));
                //  persoonList.add(new Persoon(rs.getInt("id"), rs.getString("naam")));
            }
            rs.close();


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return contactList;
    }

    public ContactInformatie findOneRecord(int telNum, String ciAdres) {
        ContactInformatie contactInformatie = null;
        try {
            String sql = "select ci.id, ci.adres, ci.telefoon_nummer , p.id pid, p.naam pnaam, l.id lid, l.naam land_naam" +
                    " from contact_informatie ci" +
                    " join persoon p" +
                    " on p.id = ci.persoon_id" +
                    " join land l" +
                    " on l.id = ci.land_id where ci.telefoon_nummer = ? or ci.adres = ?";
            pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, telNum);
            pstmt.setString(2, ciAdres);
            ResultSet rs = pstmt.executeQuery();
            System.out.println("resultset: " + rs);
            //STEP 5: Extract data from result set
            if (rs.next()) {
                //Retrieve by column name
                int id = rs.getInt("id");
                String adres = rs.getString("adres");
                int telefoonNummer = rs.getInt("telefoon_nummer");

                int persoonId = rs.getInt("pid");
                String persoonNaam = rs.getString("pnaam");
                Persoon persoon = new Persoon(persoonId, persoonNaam);

                int landId = rs.getInt("lid");
                String landNaam = rs.getString("land_naam");
                Land land = new Land(landId, landNaam);

                contactInformatie = new ContactInformatie(id, adres, telefoonNummer, persoon, land);
            }
            rs.close();


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return contactInformatie;
    }

    public int updateOneRecord(ContactInformatie contact) {
        int result = 0;
        try {
            String sql = "update contact_informatie ci set ci.telefoon_nummer = ?, ci.persoon_id = ? where ci.id = ?";
            pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, contact.getTelefoonNummer());
            pstmt.setInt(2, contact.getPersoon().getId());
            pstmt.setInt(3, contact.getId());
            result = pstmt.executeUpdate();
            System.out.println("resultset: " + result);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

}
