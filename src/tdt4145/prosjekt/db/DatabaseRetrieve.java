package tdt4145.prosjekt.db;

import java.sql.*;
import java.time.LocalDate;
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
        connect.close();
        return log;
    };

    public static List<String> getOvelser() throws SQLException{
        ArrayList<String> ovelser = new ArrayList<>();
        connect = DriverManager.getConnection("jdbc:mysql://mysql.stud.ntnu.no?" + "user=espenmei_trening&password=eplekake");
        String sql = "SELECT navn\n" +
                "FROM espenmei_treningdb.ovelse;\n";
        Statement statement = connect.createStatement();
        ResultSet result = statement.executeQuery(sql);
        while (result.next()){
            ovelser.add(result.getString("navn"));
        }
        connect.close();
        return ovelser;
    }


    /**
     * Få progresjon for øvelse i intervallet
     * @param ovelse
     * @param start
     * @param slutt
     * @return
     * @throws SQLException
     */
    public static List<String> getProgresjonForOvelseIntervall(String ovelse, LocalDate start, LocalDate slutt) throws SQLException{
        ArrayList<String> progresjon = new ArrayList<>();
        connect = DriverManager.getConnection("jdbc:mysql://mysql.stud.ntnu.no?" + "user=espenmei_trening&password=eplekake");
        String sql = "SELECT dato, res.ovelse, resultat\n" +
                "FROM espenmei_treningdb.resultat AS res, espenmei_treningdb.okt\n" +
                "WHERE res.oktid = okt.id AND res.ovelse = ? AND dato > ? AND dato < ?\n" +
                "ORDER BY dato";
        PreparedStatement statement = connect.prepareStatement(sql);
        statement.setString(1, ovelse);
        statement.setDate(2, Date.valueOf(start));
        statement.setDate(3, Date.valueOf(slutt));
        ResultSet result = statement.executeQuery();
        while (result.next()) {
            progresjon.add(result.getDate("dato").toString()+" "+result.getString("ovelse")+": "+Integer.toString(result.getInt("resultat")));
        }
        connect.close();
        return progresjon;
    }

    /**
     * Hent ut alle mål
     * @return ArrayList<String> mal
     * @throws SQLException
     */

    public static List<String> getMal() throws SQLException{
        ArrayList<String> mal = new ArrayList<>();
        connect = DriverManager.getConnection("jdbc:mysql://mysql.stud.ntnu.no?" + "user=espenmei_trening&password=eplekake");
        String sql = "SELECT mal\n" +
                "FROM espenmei_treningdb.mal;\n";
        PreparedStatement statement = connect.prepareStatement(sql);
        ResultSet result = statement.executeQuery();
        while (result.next()){
            mal.add(result.getString("mal"));
        }
        return mal;
    }

    /**
     * Få alle målene som er registrert for en øvelse
     * @param ovelse
     * @return
     * @throws SQLException
     */
    public static List<String> getMalForOvelse(String ovelse) throws SQLException{
        ArrayList<String> mal = new ArrayList<>();
        connect = DriverManager.getConnection("jdbc:mysql://mysql.stud.ntnu.no?" + "user=espenmei_trening&password=eplekake");
        String sql = "SELECT mal, dato, ovelse\n" +
                "FROM espenmei_treningdb.mal, espenmei_treningdb.har_mal\n" +
                "WHERE mal.id = har_mal.id AND ovelse=?\n" +
                "ORDER BY DATO";
        PreparedStatement statement = connect.prepareStatement(sql);
        statement.setString(1, ovelse);
        ResultSet result = statement.executeQuery();
        while (result.next()) {
            mal.add(result.getDate("dato").toString()+": "+Integer.toString(result.getInt("mal")));
        }
        connect.close();
        return mal;
    }


    /**
     * Få det beste resultatet for en øvelse etter en gitt dato
     * @param ovelse
     * @param dato
     * @return
     * @throws SQLException
     */
    public static String getBesteResultatEtter(String ovelse, LocalDate dato) throws SQLException{
        connect = DriverManager.getConnection("jdbc:mysql://mysql.stud.ntnu.no?" + "user=espenmei_trening&password=eplekake");
        String sql = "SELECT dato, resultat\n" +
                "FROM espenmei_treningdb.resultat INNER JOIN espenmei_treningdb.okt ON resultat.oktid=okt.id\n" +
                "WHERE resultat = (SELECT MAX(resultat)\n" +
                "\tFROM espenmei_treningdb.resultat INNER JOIN espenmei_treningdb.okt ON resultat.oktid=okt.id\n" +
                "\tWHERE dato > ? AND ovelse=?\n" +
                ")";
        PreparedStatement statement = connect.prepareStatement(sql);
        statement.setDate(1, Date.valueOf(dato));
        statement.setString(2, ovelse);
        ResultSet result = statement.executeQuery();
        String res = "";
        while (result.next()) {
            res = result.getDate("dato").toString()+" "+Integer.toString(result.getInt("resultat"));
        }
        connect.close();
        return  res;
    }


}
