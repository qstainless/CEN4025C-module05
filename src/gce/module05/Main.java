package gce.module05;

import gce.module05.model.Data;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * This program implements an application that allows a user to create, view,
 * and delete to-do items. The to-do items are saved to a text file in the
 * local filesystem, which is loaded and parsed every time the application
 * runs.
 * <p>
 * Course: CEN 4025C-33718 Software Development II
 * <p>
 * Instructor: Dr. Dhrgam AL Kafaf
 *
 * @author Guillermol Castaneda Echegaray
 * @version 1.0
 * @since 2020-05-14
 */
public class Main extends Application {

    /**
     * Entry point of the application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Initialize the application by first loading the to-do items saved in
     * a text file. If the file does not exist the GUI will open with an
     * empty to-do list.
     */
    @Override
    public void init() {
        try {
            Data.getInstance().loadItems();
        } catch (Exception e) {
            System.out.println("");
        }
    }

    /**
     * Starts the GUI
     *
     * @param primaryStage the primary stage
     */
    @Override
    public void start(Stage primaryStage) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("view/MainStage.fxml"));
            primaryStage.setTitle("Castaneda - CEN4025C - Module 02");
            primaryStage.setScene(new Scene(root, 700, 400));
            primaryStage.show();
        } catch (Exception e) {
            System.out.println("It seems that JavaFX is not properly installed in your system.\n\n" +
                    "Program cannot continue. Exiting.");
        }
    }
}
