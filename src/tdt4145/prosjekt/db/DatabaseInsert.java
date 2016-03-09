package tdt4145.prosjekt.db;

import tdt4145.prosjekt.models.Mal;
import tdt4145.prosjekt.models.Okt;
import tdt4145.prosjekt.models.Ovelse;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

/**
 * Created by Espen Meidell <espen.meidell@gmail.com> on 24.02.16.
 */
public class DatabaseInsert {
    private static Connection connect = null;
    private static ResultSet resultSet;


    /**
     * Insert ovelse into database
     * @param ovelse Ovelse to insert
     * @throws SQLException Any SQL exception
     */
    public static void insertOvelse(Ovelse ovelse) throws SQLException{
        connect = DriverManager.getConnection("jdbc:mysql://mysql.stud.ntnu.no?" + "user=espenmei_trening&password=eplekake");
        String sql = "INSERT INTO espenmei_treningdb.ovelse\n" +
                "(navn, beskrivelse, belastning, repetisjoner, sett, enhet)\n" +
                "VALUES(?, ?, ?, ?, ?, ?);";
        PreparedStatement statement = connect.prepareStatement(sql);
        statement.setString(1, ovelse.getNavn());
        statement.setString(2, ovelse.getBeskrivelse());
        statement.setInt(3, ovelse.getBelastning());
        statement.setInt(4, ovelse.getRepetisjoner());
        statement.setInt(5, ovelse.getSett());
        statement.setString(6, ovelse.getEnhet());
        statement.executeUpdate();
        connect.close();
    }

    /**
     * Insert a category
     * @param category category name
     * @throws SQLException Any SQL Exception
     */
    public static void insertCategory(String category) throws SQLException{
        connect = DriverManager.getConnection("jdbc:mysql://mysql.stud.ntnu.no?" + "user=espenmei_trening&password=eplekake");
        String sql = "INSERT INTO espenmei_treningdb.kategori\n" +
                "(navn)\n" +
                "VALUES(?)";
        PreparedStatement statement = connect.prepareStatement(sql);
        statement.setString(1, category);
        statement.executeUpdate();
        connect.close();
    }

    /**
     * Insert okt
     * @param okt
     * @throws SQLException
     */
    public static void insertOkt(Okt okt) throws SQLException{
        connect = DriverManager.getConnection("jdbc:mysql://mysql.stud.ntnu.no?" + "user=espenmei_trening&password=eplekake");
        String sql = "INSERT INTO espenmei_treningdb.okt\n" +
                "(navn, dato, starttid, slutttid, form, prestasjon, notat)\n" +
                "VALUES(?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement statement = connect.prepareStatement(sql);
        statement.setString(1, okt.getNavn());
        statement.setDate(2, Date.valueOf(okt.getDato()));
        statement.setTime(3, Time.valueOf(okt.getStart()));
        statement.setTime(4, Time.valueOf(okt.getSlutt()));
        statement.setInt(5, okt.getForm());
        statement.setInt(6, okt.getPrestasjon());
        statement.setString(7, okt.getNotat());
        statement.executeUpdate();
        connect.close();

    }

    /**
     * Okt har ovelse
     * @param okt
     * @param ovelse
     * @throws SQLException
     */
    public static void oktHarOvelse(int okt, String ovelse) throws SQLException {
        connect = DriverManager.getConnection("jdbc:mysql://mysql.stud.ntnu.no?" + "user=espenmei_trening&password=eplekake");
        String sql = "INSERT INTO espenmei_treningdb.okt_har_ovelse\n" +
                "(oktid, ovelse)\n" +
                "VALUES(?, ?)";
        PreparedStatement statement = connect.prepareStatement(sql);
        statement.setInt(1, okt);
        statement.setString(2, ovelse);
        statement.executeUpdate();
        connect.close();
    }


    /**
     * Insert Resultat
     */
    public static void insertResultat(int oktid, String ovelse, int resultat) throws SQLException{
        connect = DriverManager.getConnection("jdbc:mysql://mysql.stud.ntnu.no?" + "user=espenmei_trening&password=eplekake");
        String sql = "INSERT INTO espenmei_treningdb.resultat\n" +
                "(resultat, ovelse, oktid)\n" +
                "VALUES(?, ?, ?);";
        PreparedStatement statement = connect.prepareStatement(sql);
        statement.setInt(1, resultat);
        statement.setString(2, ovelse);
        statement.setInt(3, oktid);
        statement.executeUpdate();
        connect.close();
    }


    /**
     * Nytt mal
     * @param mal
     * @throws SQLException
      */

    public static void nyttMal(Mal mal) throws SQLException{
        connect = DriverManager.getConnection("jdbc:mysql://mysql.stud.ntnu.no?" + "user=espenmei_trening&password=eplekake");
        String sql = "INSERT INTO espenmei_treningdb.mal\n" +
                "(id, mal, dato)\n" +
                "VALUES(?, ?, ?);\n";
        PreparedStatement statement = connect.prepareStatement(sql);
        statement.setInt(1, mal.getId());
        statement.setInt(2, mal.getMal());
        statement.setDate(3, Date.valueOf(mal.getDato()));
        statement.executeUpdate();
        connect.close();
    }

    /**
     * Kopier alle øvelsene i en økt inn i en ny, tom øvelse uten resultater
     * @param oktid
     * @throws SQLException
     */

    public static void copyOkt(int oktid) throws SQLException{
        connect = DriverManager.getConnection("jdbc:mysql://mysql.stud.ntnu.no?" + "user=espenmei_trening&password=eplekake");
        //Finn alle øvelsene som er i denne økten
        ArrayList<String> ovelser = new ArrayList<>();
        String selectSql = "SELECT ovelse\n" +
                "FROM espenmei_treningdb.okt_har_ovelse\n" +
                "WHERE oktid = " + String.valueOf(oktid);
        PreparedStatement statement = connect.prepareStatement(selectSql);
        ResultSet result = statement.executeQuery();
        while(result.next()){
            ovelser.add(result.getString("ovelse"));
        }
        connect.close();

        //lag ny økt med ny økt-ID
        insertOkt(new Okt(null, LocalDate.now(), LocalTime.now(), LocalTime.now(), 0, 0, null));

        connect = DriverManager.getConnection("jdbc:mysql://mysql.stud.ntnu.no?" + "user=espenmei_trening&password=eplekake");
        //Finner største økt-ID
        String biggestIdSql = "SELECT MAX(id)\n" +
                "FROM espenmei_treningdb.okt;\n";
        PreparedStatement statement1 = connect.prepareStatement(biggestIdSql);
        ResultSet result1 = statement1.executeQuery();
        int maxid = -1;
        while(result1.next()){
            maxid = result1.getInt("MAX(id)");
        }
        connect.close();

        //legg til øvelsene i en ny økt

        for(String ovelse : ovelser){
            System.out.println(ovelse);
            oktHarOvelse(maxid, ovelse);
        }


    }


    /*

    METODER FOR SLETTING AV DATA

     */

    /**
     * Slett en øvelse
     * @param navn Navnet på øvelsen man sletter
     * @throws SQLException
     */
    public static void slettOvelse(String navn) throws SQLException{
        connect = DriverManager.getConnection("jdbc:mysql://mysql.stud.ntnu.no?" + "user=espenmei_trening&password=eplekake");
        String sql = "DELETE FROM espenmei_treningdb.ovelse\n" +
                "WHERE navn=?;\n";
        PreparedStatement statement = connect.prepareStatement(sql);
        statement.setString(1, navn);
        statement.executeUpdate();
        connect.close();
    }

}
