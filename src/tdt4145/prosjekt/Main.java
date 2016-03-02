package tdt4145.prosjekt;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import tdt4145.prosjekt.db.DatabaseInsert;
import tdt4145.prosjekt.db.DatabaseRetrieve;
import tdt4145.prosjekt.models.Mal;
import tdt4145.prosjekt.models.Okt;
import tdt4145.prosjekt.models.Ovelse;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }


    public static void main(String[] args) {
        try {
            System.out.println(DatabaseRetrieve.getBesteResultatEtter("Squats", LocalDate.now().minusYears(1)));

        } catch (Exception e) {
            e.printStackTrace();
        }

        //launch(args);
    }
}
