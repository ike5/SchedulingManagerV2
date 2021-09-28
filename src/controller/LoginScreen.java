package controller;

import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.control.Alert;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import utils.DBUsers;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

public class LoginScreen implements Initializable {
    public Label zone_id;
    public Label language_zone_id;
    public Label welcome_message;
    private Stage stage;
    private Parent scene;
    public Label username_id;
    public Label password_id;
    public TextField username_field_id;
    public TextField password_field_id;
    public Button login_id;

    ResourceBundle rb = ResourceBundle.getBundle("RBundle", Locale.getDefault());

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        new Test("Login screen initialized!");

        language_zone_id.setText(rb.getString("zone_id"));
        welcome_message.setText(rb.getString("welcome_message"));
        username_id.setText(rb.getString("username"));
        password_id.setText(rb.getString("password"));
        login_id.setText(rb.getString("login_button"));
        username_field_id.setText(rb.getString("username_field"));

    }

    @FXML
    public void usernameOnAction(ActionEvent actionEvent) throws IOException {
        // This actionEvent happens on ENTER
        onLoginAction(actionEvent);
    }

    @FXML
    public void passwordOnAction(ActionEvent actionEvent) throws IOException {
        //TODO Fix bug on clicking Enter

    }


    /**
     * This is the Event from Button click.
     *
     * @param actionEvent
     * @throws IOException
     */
    @FXML
    public void onLoginAction(ActionEvent actionEvent) throws IOException {
        DBUsers userLogin = new DBUsers(username_field_id.getText(), password_field_id.getText());

        if (userLogin.userExists()) {
            new Test("User exists");
            if (userLogin.passwordMatches()) {
                new Test("Password matches");

                //FIXME Make event listeners on ENTER correctly call methods

                // Get event source from button
                stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
                // Load resources from view directory
                scene = FXMLLoader.load(getClass().getResource("/view/Customers.fxml"));
                stage.setScene(new Scene(scene));
                stage.show();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR, rb.getString("incorrect_password"));
                alert.setTitle(rb.getString("password_alert_title"));
                alert.showAndWait();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR, rb.getString("incorrect_username"));
            alert.setTitle(rb.getString("username_alert_title"));
            alert.showAndWait();
        }
    }


    /**
     * Validates a username by matching string values that begin with a number or letter, and contains
     * only numbers and letters.
     *
     * @return
     */
    private boolean validateUsernameString() {
        //FIXME
        // - Allow underscores

        String regexUsername = "^[0-z]+";
        return username_field_id.getText().matches(regexUsername);
    }

    /**
     * Validates password by matching all string values except whitespaces
     *
     * @return Returns true if password field complies with regex
     */
    private boolean validatePasswordString() {
        String regexPassword = "[\\S]+";
        return password_field_id.getText().matches(regexPassword);
    }

    public void onUsernameKeyTyped(KeyEvent keyEvent) {
        //TODO
        // - Create Error messages here in red and hide when done

        if (validateUsernameString()) {
            new Test("Username is Regex compliant? [OK]");
        } else {
            new Test("Username is [not] Regex compliant");
        }
    }

    public void onPasswordKeyTyped(KeyEvent keyEvent) {
        //TODO
        // - Create Error messages here in red and hide when done

        if (validatePasswordString()) {
            new Test("Password is Regex compliant? [OK]");
        } else {
            new Test("Password is [not] Regex compliant");
        }

    }
}
