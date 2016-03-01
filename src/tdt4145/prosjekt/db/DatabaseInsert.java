package tdt4145.prosjekt.db;

import tdt4145.prosjekt.models.Okt;
import tdt4145.prosjekt.models.Ovelse;

import java.sql.*;

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





}
