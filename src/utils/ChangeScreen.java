package utils;

import controller.LoginController;
import data.DBAppointment;
import data.DBUsers;
import data.LoginTracker;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import javafx.util.Pair;
import main.Main;
import model.Log;
import model.LogType;
import model.Messages;
import model.User;
import test.Test;

import java.io.*;
import java.nio.file.Path;
import java.sql.Array;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;


@UtilityInterfaces
public class ChangeScreen {
    public static void changeScreen(ActionEvent actionEvent,
                                    DBUsers userLogin,
                                    Pair<String, String> usernameAndPasswordReceived,
                                    Parent scene,
                                    UtilityInterfaces.FunctionalChangeScreenInterface o) {
        Main.resourceBundle = ResourceBundle.getBundle("RBundle", Locale.getDefault());

        // Check if username and password are valid, switch views or present appropriate alerts
        if (userLogin.getUser().isValidUsername()) {
            if (userLogin.getUser().isValidPassword()) {
                Main.user = userLogin.getUser();


                Pair<Boolean, Pair<LocalDateTime, Integer>> upcomingAppointment = DBAppointment.checkUpcomingAppointments();
                if (upcomingAppointment.getKey()) {
                    Messages.warningMessage("Upcoming appointment: " +
                                    upcomingAppointment.getValue().getValue() + "\n" +
                                    "Time: " + ZonedDateTime.of(upcomingAppointment.getValue().getKey(), ZoneId.systemDefault()),
                            "Upcoming appointment");
                }


                /*
                Note the event source is either a Button or a TextField:
                stage = (Stage) ((TextField) actionEvent.getSource()).getScene().getWindow();
                stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
                 */
                Stage stage = o.eventSource(actionEvent);
                stage.setTitle("Welcome " + userLogin.getUser().getUsername() + "!");
                stage.setScene(new Scene(scene));
                stage.show();
            } else {
                Messages.errorMessage(Main.resourceBundle.getString("incorrect_password"), Main.resourceBundle.getString("password_alert_title"));
            }
        } else {
            Messages.errorMessage(Main.resourceBundle.getString("incorrect_username"), Main.resourceBundle.getString("username_alert_title"));
        }
    }
}
