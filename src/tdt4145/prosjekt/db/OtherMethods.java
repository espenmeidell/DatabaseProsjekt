package tdt4145.prosjekt.db;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Espen Meidell <espen.meidell@gmail.com> on 02.03.16.
 */
public class OtherMethods {


    public static String getDifferenceBetweenBestResultAndGoal(String ovelse, LocalDate dato) throws SQLException {
        String res = "";
        List<String> mal = DatabaseRetrieve.getMalForOvelse(ovelse);
        String beste = DatabaseRetrieve.getBesteResultatEtter(ovelse, dato);
        if (mal.size() == 0 || beste.length() == 0) return "No results found...";
        int diff = Integer.valueOf(mal.get(mal.size()-1).split(": ")[1]) - Integer.valueOf(beste.split(": ")[1]);
        res = "Nyeste mål,     "+mal.get(mal.size()-1)+"\n"+
                "Beste resultat, "+beste+"\n"+
                "Differansen er: "+Integer.toString(diff);

        return res;
    }




}
