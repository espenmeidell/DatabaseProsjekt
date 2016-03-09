package tdt4145.prosjekt.models;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;

/**
 * Makes getting valid input from user simpler
 * Created by Espen Meidell <espen.meidell@gmail.com> on 02.03.16.
 */
public class InputHelper {

    /*
    INPUT HELPER METHODS
     */

    public Scanner sc = new Scanner(System.in);
    /**
     * Get a LocalDate from console
     * @param prompt Prompt to show user
     * @return LocalDate
     */
    public LocalDate getDateFromUser(String prompt) {
        LocalDate date = null;
        while (true) {
            System.out.print(prompt);
            String datestring = sc.nextLine();
            try {
                date = LocalDate.parse(datestring);
                break;
            } catch (Exception e) {
                System.err.println("Use format YYYY-MM-DD");
                continue;
            }
        }
        return date;
    }

    /**
     * Get a LocalTime from console
     * @param prompt Promt to show user
     * @return LocalTime
     */
    public LocalTime getTimeFromUser(String prompt) {
        LocalTime time = null;
        while (true) {
            System.out.print(prompt);
            String timestring = sc.nextLine();
            try {
                time = LocalTime.parse(timestring);
                break;
            } catch (Exception e) {
                System.err.println("Use format HH:MM");
                continue;
            }
        }
        return time;
    }

    /**
     * Get a string where length > 0 from console
     * @param prompt Prompt to show user
     * @return String
     */
    public String getNotEmptyStringFromUser(String prompt) {
        System.out.print(prompt);
        String input = sc.nextLine();
        while (input.length() == 0) {
            System.err.println("String cannot be empty!");
            input = sc.nextLine();
        }
        return input;
    }

    /**
     * Get an integer from console
     * @param prompt Pront text to show
     * @return integer
     */
    public int getIntegerFromUser(String prompt) {
        System.out.print(prompt);
        while (true) {
            try {
                String input = sc.nextLine();
                int i = Integer.parseInt(input);
                return i;
            } catch (Exception e) {
                System.err.println("Could not parse integer");
                continue;
            }
        }
    }

    /**
     * Get an integer in range from console
     * @param prompt Prompt text to show
     * @param min Minimum value
     * @param max Maximum value
     * @return integer
     */
    public int getIntegerInRangeFromUser(String prompt, int min, int max) {
        while (true) {
            int i = getIntegerFromUser(prompt);
            if (i >= min && i <= max) {
                return i;
            }
            System.err.println("The number was not between "+min+" and "+max);
        }
    }

    /**
     * Get confirmation of action (y/n)
     * @param prompt Prompt text
     * @return
     */
    public boolean getConfirmDoAction(String prompt) {
        while (true) {
            String res = getNotEmptyStringFromUser(prompt);
            switch (res.toLowerCase()) {
                case "yes":
                    return true;
                case "y":
                    return true;
                case "no":
                    return false;
                case "n":
                    return false;
                default:
                    continue;
            }
        }
    }

}
