package tdt4145.prosjekt;

import tdt4145.prosjekt.db.DatabaseInsert;
import tdt4145.prosjekt.db.DatabaseRetrieve;
import tdt4145.prosjekt.db.OtherMethods;
import tdt4145.prosjekt.models.InputHelper;
import tdt4145.prosjekt.models.Okt;
import tdt4145.prosjekt.models.Ovelse;

import javax.xml.crypto.Data;
import java.sql.SQLException;
import java.time.LocalDate;

/**
 * Created by Espen Meidell <espen.meidell@gmail.com> on 02.03.16.
 */
public class TrainingApplication {
    InputHelper helper = new InputHelper();

    public void run() {
        System.out.println("Velg et alternativ: ");
        System.out.println("\t 1  - Legg til økt");
        System.out.println("\t 2  - Legg til øvelse");
        System.out.println("\t 3  - Legg til øvelse i økt");
        System.out.println("\t 4  - Legg til resultat");
        System.out.println("\t 5  - Se progresjon for øvelse");
        System.out.println("\t 6  - Se registrerte mål");
        System.out.println("\t 7  - Se diff. på beste resultat og mål");
        System.out.println("\t 8  - Lag ny økt basert på mal");
        System.out.println("\t 9  - Se sammenheng mellom form, resultat og forhold");
        System.out.println("\t 10 - Se trengingslogg");
        int valg = helper.getIntegerInRangeFromUser("> ", 1, 10);
        switch (valg){
            case 1:
                leggTilOkt();
                break;
            case 2:
                addOvelse();
                break;
            case 3:
                leggTilOvelseIOkt();
                break;
            case 4:
                leggTilResultat();
                break;
            case 5:
                seProgresjon();
                break;
            case 6:
                seMal();
                break;
            case 7:
                seeDiffResultGoal();
                break;
            case 8:
                kopierOkt();
                break;
            case 9:
                System.out.println("Uteøkter:");
                printUteSammenheng();
                System.out.println();
                System.out.println("Inneøkter:");
                printInneSammenheng();
                break;
            case 10:
                printNotatLog();
                break;
        }
        System.out.println();
        run();


    }

    private void printOkter() {
        System.out.println("Registrerte Økter: ");
        try {
            DatabaseRetrieve.getOkter().forEach(System.out::println);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void registerUteData() {
        printOkter();
        int id = helper.getIntegerFromUser("Hvilken øvelse vil du registrere utedata for? ");
        int temp = helper.getIntegerFromUser("Hva var temperaturen? ");
        String vaer = helper.getNotEmptyStringFromUser("Hvordan var været? ");
        try {
            DatabaseInsert.setUteData(id, temp, vaer);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void registerInneData() {
        printOkter();
        int id = helper.getIntegerFromUser("Hvilken øvelse vil du registrere innedata for? ");
        int luft = helper.getIntegerInRangeFromUser("Hvordan var luftkvaliteten (mellom 1 og 10)? ", 1, 10);
        int tilskuere = helper.getIntegerFromUser("Hvor mange tilskuere? ");
        try {
            DatabaseInsert.setInneData(id, luft, tilskuere);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // USE CASE 1

    private void leggTilOvelseIOkt() {
        printOkter();
        int id = helper.getIntegerFromUser("Hvilken øvelse vil du legge til øvelse for? ");
        try {
            System.out.println("Følgende er registrert på denne økten:");
            DatabaseRetrieve.getOvelseriOkt(id).forEach(System.out::println);
            System.out.println("Tilgjengelige Øvelser: ");
            DatabaseRetrieve.getOvelser().forEach(System.out::println);
            String toreg = helper.getNotEmptyStringFromUser("Hvilken vil du legge til? ");
            DatabaseInsert.oktHarOvelse(id, toreg);
            DatabaseRetrieve.getOvelseriOkt(id).forEach(System.out::println);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void leggTilResultat() {
        printOkter();
        int id = helper.getIntegerFromUser("Hvilken økt vil du registrere for? ");
        try {
            System.out.println("Følgende øvelser er registrert: ");
            DatabaseRetrieve.getOvelseriOkt(id).forEach(System.out::println);
            String ovelse = helper.getNotEmptyStringFromUser("Hvilken øvelse vil du registrere for? ");
            int resultat = helper.getIntegerFromUser("Hva ble resultatet? ");
            DatabaseInsert.insertResultat(id, ovelse, resultat);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private void printResultater() {
        printOkter();
        int id = helper.getIntegerFromUser("Hvilken økt vil du se registrerte resultater for? ");
        try {
            DatabaseRetrieve.getResultateriOkt(id).forEach(System.out::println);
        } catch (SQLException e) {
            e.printStackTrace();
        }
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

    // USE CASE 2/3

    private void seProgresjon() {
        printOvelser();
        String ovelse = helper.getNotEmptyStringFromUser("Hvilken øvelse vil du se progresjonen for? ");
        try {
            DatabaseRetrieve.getProgresjonForOvelseIntervall(ovelse, LocalDate.now().minusYears(10), LocalDate.now().plusYears(1)).forEach(System.out::println);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void seMal() {
        printOvelser();
        String ovelse = helper.getNotEmptyStringFromUser("Hvilken øvelse vil du se mål for? ");
        try {
            DatabaseRetrieve.getMalForOvelse(ovelse).forEach(System.out::println);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }





    // USE CASE 4

    private void seeDiffResultGoal() {
        System.out.println("Tilgjengelige Øvelser:");
        printOvelser();
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

    //USE CASE 5
    private void kopierOkt() {
        printOkter();
        int id = helper.getIntegerFromUser("Velg en ID å kopiere: ");
        String name = helper.getNotEmptyStringFromUser("Navnet på ny økt: ");
        try {
            DatabaseInsert.copyOkt(id, name);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // USE CASE 6
    private void printUteSammenheng() {
        try {
            DatabaseRetrieve.getSammenhengUteData().forEach(System.out::println);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void printInneSammenheng() {
        try {
            DatabaseRetrieve.getSammenhengInneData().forEach(System.out::println);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    // USE CASE 7

    private void printNotatLog() {
        try {
            DatabaseRetrieve.getTreningsNotatLog().forEach(System.out::println);
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

    private void leggTilOkt() {
        Okt okt = new Okt(
                helper.getNotEmptyStringFromUser("Navn på økten: "),
                helper.getDateFromUser("Dato: "),
                helper.getTimeFromUser("Starttid: "),
                helper.getTimeFromUser("Slutttid: "),
                helper.getIntegerInRangeFromUser("Form: ", 1, 10),
                helper.getIntegerInRangeFromUser("Prestasjon: ", 1, 10),
                helper.getNotEmptyStringFromUser("Notat: ")
        );
        try {
            DatabaseInsert.insertOkt(okt);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
