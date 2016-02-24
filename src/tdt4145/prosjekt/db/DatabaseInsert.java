package tdt4145.prosjekt.db;

import tdt4145.prosjekt.models.Oving;

import java.sql.*;

/**
 * Created by Espen Meidell <espen.meidell@gmail.com> on 24.02.16.
 */
public class DatabaseInsert {
    private static Connection connect = null;
    private static Statement statement = null;
    private static ResultSet resultSet;


    /**
     * Insert oving into database
     * @param oving Oving to insert
     * @throws SQLException Any SQL exception
     */
    public static void insertOving(Oving oving) throws SQLException{
        connect = DriverManager.getConnection("jdbc:mysql://mysql.stud.ntnu.no?" + "user=espenmei_trening&password=eplekake");
        String sql = "INSERT INTO espenmei_treningdb.ovelse\n" +
                "(navn, beskrivelse, belastning, repetisjoner, sett, enhet)\n" +
                "VALUES(?, ?, ?, ?, ?, ?);";
        PreparedStatement statement = connect.prepareStatement(sql);
        statement.setString(1, oving.getNavnProperty());
        statement.setString(2, oving.getBeskrivelsePropery());
        statement.setInt(3, oving.getBelastningProperty());
        statement.setInt(4, oving.getRepetisjonerProperty());
        statement.setInt(5, oving.getSettProperty());
        statement.setString(6, oving.getEnhetProperty());
        statement.executeUpdate();
    }



}
