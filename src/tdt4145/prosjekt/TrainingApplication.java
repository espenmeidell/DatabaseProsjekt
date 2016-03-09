package tdt4145.prosjekt;

import tdt4145.prosjekt.db.DatabaseInsert;
import tdt4145.prosjekt.db.DatabaseRetrieve;
import tdt4145.prosjekt.db.OtherMethods;
import tdt4145.prosjekt.models.InputHelper;
import tdt4145.prosjekt.models.Ovelse;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;

/**
 * Created by Espen Meidell <espen.meidell@gmail.com> on 02.03.16.
 */
public class TrainingApplication {
    InputHelper helper = new InputHelper();

    public void run() {
        slettOvelse();
    }


    // USE CASE 2

    private void printOvelser() {
        System.out.println("Tilgjengelige Øvelser: ");
        try {
            DatabaseRetrieve.getOvelser().forEach(o -> System.out.println("\t"+o));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    // USE CASE 4

    private void seeDiffResultGoal() {
        String ovelse = helper.getNotEmptyStringFromUser("Øvelse: ");
        System.out.println("Vil du se differansen for: \n1) Siste Uke \n2) Siste Måned \n3) Siste 3 Måneder");
        int selection = helper.getIntegerInRangeFromUser("", 1, 3);
        LocalDate date = null;
        switch (selection) {
            case 1:
                date = LocalDate.now().minusWeeks(1);
                break;
            case 2:
                date = LocalDate.now().minusMonths(1);
                break;
            case 3:
                date = LocalDate.now().minusMonths(3);
        }

        try {
            System.out.println(OtherMethods.getDifferenceBetweenBestResultAndGoal(ovelse, date));
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }




    // USE CASE 8

    private void addOvelse() {
        System.out.println("Vennligst fyll inn følgende informasjon om øvelsen: ");
        Ovelse ovelse = new Ovelse(helper.getNotEmptyStringFromUser("Navn på øvelsen: "),
                helper.getNotEmptyStringFromUser("Beskrivelse: "),
                helper.getIntegerFromUser("Belastning: "),
                helper.getIntegerFromUser("Repetisjoner: "),
                helper.getIntegerFromUser("Sett: "),
                helper.getNotEmptyStringFromUser("Enhet: "));
        try {
            DatabaseInsert.insertOvelse(ovelse);
            System.out.println("Øvelsen er lagt til i databasen.");
        } catch (Exception e) {
            System.err.println("Noe gikk galt...");
            e.printStackTrace();
        }
    }

    private void slettOvelse() {
        printOvelser();
        String todelete = helper.getNotEmptyStringFromUser("Hvilken øvelse vil du slette? ");
        if (helper.getConfirmDoAction("Er du sikker (y/n)? ")) {
            try {
                DatabaseInsert.slettOvelse(todelete);
                System.out.println("Slettingen er gjennomført.");
            } catch (Exception e) {
                System.err.println("Noe gikk galt med slettingen.");
            }

        } else {
            System.out.println("Slettingen er avbrutt.");
        }
    }


}
