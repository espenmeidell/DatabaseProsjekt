package tdt4145.prosjekt.db;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Espen Meidell <espen.meidell@gmail.com> on 01.03.16.
 */
public class DatabaseRetrieve {
    private static Connection connect = null;
    private static ResultSet resultSet;

    public static List<String> getTreningsNotatLog() throws SQLException{
        ArrayList<String> log = new ArrayList<>();
        connect = DriverManager.getConnection("jdbc:mysql://mysql.stud.ntnu.no?" + "user=espenmei_trening&password=eplekake");
        String sql = "SELECT navn, dato, notat\n" +
                "FROM espenmei_treningdb.okt\n" +
                "ORDER BY dato;";
        Statement statement = connect.createStatement();
        ResultSet result = statement.executeQuery(sql);
        while (result.next()) {
            log.add(result.getString("navn")+", "+result.getDate("dato").toString()+": "+result.getString("notat"));
        }

        return log;
    };


}
