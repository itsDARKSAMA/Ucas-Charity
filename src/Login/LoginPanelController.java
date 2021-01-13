
package Login;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;


/**
 *
 * @author Abdelrahman Al-Majayda
 * @version 1.0
 * @since   2020-04-3
 * @link https://github.com/itsdarksama
 *
 */

public class LoginPanelController implements Initializable {

    loginModel loginModel = new loginModel();

    @FXML
    private JFXTextField userTF;

    @FXML
    private JFXPasswordField passTF;

    @FXML
    private Label loginLabel;

    @FXML
    private JFXComboBox<loginOption> loginCBox;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        this.loginCBox.setItems(FXCollections.observableArrayList(loginOption.values()));

    }

    //Login Method

    public void checkLogin(ActionEvent event) throws IOException {

        try {
            if (this.userTF.getText().isEmpty() || this.passTF.getText().isEmpty() || this.loginCBox.getSelectionModel().isEmpty()) {
                loginLabel.setText("Please Enter All Fields");
            } else {

                if (this.loginModel.isLogin(this.userTF.getText().toLowerCase(), this.passTF.getText(), ((loginOption) this.loginCBox.getValue()).toString())) {

                    switch (((loginOption) this.loginCBox.getValue()).toString()){
                        case "admin":
                            Parent view2 = FXMLLoader.load(getClass().getResource("../dashboard/dashboard.fxml"));
                            Scene scene2 = new Scene(view2);
                            Stage Window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                            Window.setScene(scene2);
                            Window.show();
                            Window.setResizable(false);
                            Window.setTitle("Ucas Chairty | Admin Dashboard");
                            Window.centerOnScreen();
                            Window.setAlwaysOnTop(false);
                            break;

                        case "employee":
                            Parent view3 = FXMLLoader.load(getClass().getResource("../dashboard/empDashboard.fxml"));
                            Scene scene3 = new Scene(view3);
                            Stage Window2 = (Stage) ((Node) event.getSource()).getScene().getWindow();
                            Window2.setScene(scene3);
                            Window2.show();
                            Window2.setResizable(false);
                            Window2.setTitle("Ucas Chairty | Employees Dashboard");
                            Window2.centerOnScreen();
                            Window2.setAlwaysOnTop(false);
                            break;
                    }





                } else {

                    passTF.clear();
                    loginLabel.setText("Wrong Entries");

                }

            }


        } catch (Exception LocalException) {
            LocalException.printStackTrace();
        }

    }



}
