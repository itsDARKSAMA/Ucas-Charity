package dashboard;


import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import dbConsole.dbConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;


/**
 *
 * @author Abdelrahman Al-Majayda
 * @version 1.0
 * @since   2020-04-3
 * @link https://github.com/itsdarksama
 *
 */

public class empDashboardController implements Initializable {


    dbConnection dc;

    int Index;
    Alert FormAlert;
    Alert DeleteAlert;

    //Sidebar
    @FXML
    private JFXButton dshBtn;

    @FXML
    private JFXButton whBtn;

    @FXML
    private JFXButton needyBtn;

    @FXML
    private JFXButton empBtn;
    //Dashboard Panel
    @FXML
    private AnchorPane dshPane;
    @FXML
    private ImageView dshWhBtn;
    @FXML
    private ImageView dshNpBtn;

    // WareHouse Panel
    @FXML
    private AnchorPane whPane;
    @FXML
    private TableView<whData> whTabel;
    @FXML
    private TableColumn<whData, String> whItemCoulmn;
    @FXML
    private TableColumn<whData, String> whSizeCoulmn;
    @FXML
    private TableColumn<whData, Integer> whQuanCoulmn;
    @FXML
    private TableColumn<whData, Integer> whIDCoulmn;
    @FXML
    private TextField whIdFourn;
    @FXML
    private TextField whItemForm;
    @FXML
    private TextField whSizeForm;
    @FXML
    private TextField whQuanForm;

    // NeedyPepole Panel
    @FXML
    private AnchorPane npPane;
    @FXML
    private TableView<npData> npTabel;
    @FXML
    private TableColumn<npData, String> npFnameCoulmn;
    @FXML
    private TableColumn<npData, String> npLnameCoulmn;
    @FXML
    private TableColumn<npData, String> npMobileCoulmn;
    @FXML
    private TableColumn<npData, Integer> npFmCoulmn;
    @FXML
    private TableColumn<npData, Integer> npIDCoulmn;
    @FXML
    private TextField npFnameForm;
    @FXML
    private TextField npIDForm;
    @FXML
    private TextField npLnForm;
    @FXML
    private TextField npFmForm;
    @FXML
    private TextField npMobileForm;


    @FXML
    private void sidebarButtons(ActionEvent event) throws SQLException {

        if (dshBtn.isHover()) {
            whPane.setVisible(false);
            npPane.setVisible(false);
            dshPane.setVisible(true);
            npClear();
            whClear();
        } else if (whBtn.isHover()) {
            dshPane.setVisible(false);
            npPane.setVisible(false);
            whPane.setVisible(true);
            whTabelStart();
            npClear();
            whClear();

        } else if (needyBtn.isHover()) {
            dshPane.setVisible(false);
            whPane.setVisible(false);
            npPane.setVisible(true);
            npTabelStart();
            npClear();
            whClear();

        }
    }


    @FXML
    private void dshButtons(MouseEvent event) throws SQLException {

        if (dshWhBtn.isHover()) {
            dshPane.setVisible(false);
            npPane.setVisible(false);
            whPane.setVisible(true);
            whTabelStart();
            whClear();
            npClear();

        } else if (dshNpBtn.isHover()) {
            dshPane.setVisible(false);
            whPane.setVisible(false);
            npPane.setVisible(true);
            npTabelStart();
            npClear();
            whClear();

        }

    }

    //Warehousse
    ObservableList<whData> whDataList;
    //NeedyPeople
    ObservableList<npData> npDataList;
    //Employees


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        this.dc = new dbConnection();

    }

    // Warehouse Show Tabel Method
    public void whTabelStart() {

        String whSql = "SELECT * FROM warehouseDB";

        try {

            Connection whConn = dbConnection.getConnection();
            whDataList = FXCollections.observableArrayList();
            ResultSet whRs = whConn.createStatement().executeQuery(whSql);
            while (whRs.next()) {

                whDataList.add(new whData(whRs.getInt(1)
                        , whRs.getString(2)
                        , whRs.getString(3)
                        , whRs.getInt(4)));

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        whIDCoulmn.setCellValueFactory(new PropertyValueFactory<>("whID"));
        whItemCoulmn.setCellValueFactory(new PropertyValueFactory<>("whItem"));
        whSizeCoulmn.setCellValueFactory(new PropertyValueFactory<>("whSize"));
        whQuanCoulmn.setCellValueFactory(new PropertyValueFactory<>("whQuan"));
        whTabel.setItems(null);
        whTabel.setItems(whDataList);

    }

    // Warehouse Add New Data
    public void addNewWhItem() {

        if (whItemForm.getText().isEmpty() || whSizeForm.getText().isEmpty() || whQuanForm.getText().isEmpty()) {
            ErrorAlert();
            return;

        }

        String whSqlInsert = "INSERT INTO warehouseDB(whItem,whSize,whQuan) VALUES(?,?,?)";
        String whItem = whItemForm.getText();
        String whSize = whSizeForm.getText();
        Integer whQuan = Integer.parseInt(whQuanForm.getText());

        try {
            Connection whConnInsert = dbConnection.getConnection();
            PreparedStatement whStmt = whConnInsert.prepareStatement(whSqlInsert);
            whStmt.setString(1, whItem);
            whStmt.setString(2, whSize);
            whStmt.setInt(3, whQuan);
            whStmt.execute();
            whConnInsert.close();
            whTabelStart();
            whClear();
        } catch (SQLException th) {
            th.printStackTrace();
        }

    }

    // Warehouse Select Item
    public void selectWhItem() {
        Index = whTabel.getSelectionModel().getSelectedIndex();
        if (Index <= -1) {
            return;
        }
        whItemForm.setText(whItemCoulmn.getCellData(Index));
        whSizeForm.setText(whSizeCoulmn.getCellData(Index));
        whQuanForm.setText(whQuanCoulmn.getCellData(Index).toString());
        whIdFourn.setText(whIDCoulmn.getCellData(Index).toString());

    }

    // Warehouse Edit Item
    public void editWhItem() {

        if (whItemForm.getText().isEmpty() || whSizeForm.getText().isEmpty() || whQuanForm.getText().isEmpty()) {
            ErrorAlert();
            return;
        }

        String whSqlEdit = "UPDATE warehouseDB SET whItem=?,whSize=?,whQuan=? where whID=?";
        String whItem = whItemForm.getText();
        String whSize = whSizeForm.getText();
        Integer whID = Integer.parseInt(whIdFourn.getText());
        Integer whQuan = Integer.parseInt(whQuanForm.getText());

        try {
            Connection whConnInsert = dbConnection.getConnection();
            PreparedStatement whStmt = whConnInsert.prepareStatement(whSqlEdit);

            whStmt.setString(1, whItem);
            whStmt.setString(2, whSize);
            whStmt.setInt(3, whQuan);
            whStmt.setInt(4, whID);
            whStmt.executeUpdate();
            whConnInsert.close();
            whTabelStart();
            whClear();
        } catch (SQLException th) {
            th.printStackTrace();
        }

    }

    // whForms Clear
    private void whClear() {
        Index = -1;
        whItemForm.clear();
        whSizeForm.clear();
        whQuanForm.clear();
        whIdFourn.clear();

    }


    // NeedyPeople Show Tabel Method
    public void npTabelStart() {

        String npSql = "SELECT * FROM needyPeopleDB";

        try {

            Connection npConn = dbConnection.getConnection();
            npDataList = FXCollections.observableArrayList();
            ResultSet npRs = npConn.createStatement().executeQuery(npSql);
            while (npRs.next()) {

                npDataList.add(new npData(npRs.getInt(1)
                        , npRs.getString(2)
                        , npRs.getString(3)
                        , npRs.getInt(4)
                        , npRs.getString(5)));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //NeedyPepole
        npFnameCoulmn.setCellValueFactory(new PropertyValueFactory<>("npFname"));
        npIDCoulmn.setCellValueFactory(new PropertyValueFactory<>("npID"));
        npLnameCoulmn.setCellValueFactory(new PropertyValueFactory<>("npLname"));
        npFmCoulmn.setCellValueFactory(new PropertyValueFactory<>("npFm"));
        npMobileCoulmn.setCellValueFactory(new PropertyValueFactory<>("npMobile"));
        npTabel.setItems(null);
        npTabel.setItems(npDataList);
    }

    // NeedyPeople Add New Data
    public void addNewNpData() {

        if (npFnameForm.getText().isEmpty() || npLnForm.getText().isEmpty() || npFmForm.getText().isEmpty() || npMobileForm.getText().isEmpty()) {
            ErrorAlert();
        }
        String npSqlInsert = "INSERT INTO needyPeopleDB(npFname,npLname,npFamily,npMobile) VALUES(?,?,?,?)";
        String npFname = npFnameForm.getText();
        String npLname = npLnForm.getText();
        String npMobile = npMobileForm.getText();
        Integer npFamily = Integer.parseInt(npFmForm.getText());
        try {
            Connection npConnInsert = dbConnection.getConnection();
            PreparedStatement npStmt = npConnInsert.prepareStatement(npSqlInsert);
            npStmt.setString(1, npFname);
            npStmt.setString(2, npLname);
            npStmt.setInt(3, npFamily);
            npStmt.setString(4, npMobile);
            npStmt.execute();
            npConnInsert.close();
            npTabelStart();
            npClear();
        } catch (SQLException th) {
            th.printStackTrace();
        }
    }

    // NeedyPeople Select
    public void selectNpData() {
        Index = npTabel.getSelectionModel().getSelectedIndex();
        if (Index <= -1) {
            return;
        }
        npFnameForm.setText(npFnameCoulmn.getCellData(Index));
        npLnForm.setText(npLnameCoulmn.getCellData(Index));
        npFmForm.setText(npFmCoulmn.getCellData(Index).toString());
        npMobileForm.setText(npMobileCoulmn.getCellData(Index));
        npIDForm.setText(npIDCoulmn.getCellData(Index).toString());
    }

    // Needy People Edit
    public void editNpData() {
        if (npFnameForm.getText().isEmpty() || npLnForm.getText().isEmpty() || npFmForm.getText().isEmpty()) {
            ErrorAlert();
            return;
        }

        Integer npID = Integer.parseInt(npIDForm.getText());
        String npFname = npFnameForm.getText();
        String npLname = npLnForm.getText();
        Integer npFamily = Integer.parseInt(npFmForm.getText());
        String npMobile = npMobileForm.getText();
        String npSqlEdit = "UPDATE needyPeopleDB SET npFname=? , npLname=?, npFamily=? , npMobile=? where npID=?";
        try {
            Connection npConnInsert = dbConnection.getConnection();
            PreparedStatement npStmtDel = npConnInsert.prepareStatement(npSqlEdit);
            npStmtDel.setString(1, npFname);
            npStmtDel.setString(2, npLname);
            npStmtDel.setInt(3, npFamily);
            npStmtDel.setString(4, npMobile);
            npStmtDel.setInt(5, npID);
            npStmtDel.executeUpdate();
            npConnInsert.close();
            npTabelStart();
            npClear();
        } catch (SQLException th) {
            th.printStackTrace();
        }
    }

    // npForms Clear
    private void npClear() {
        Index = -1;
        npFnameForm.clear();
        npLnForm.clear();
        npFmForm.clear();
        npMobileForm.clear();
        npIDForm.clear();
    }

    @FXML
    private void ClearBtns(ActionEvent event) {
        npClear();
        whClear();
    }

    public void ErrorAlert() {
        FormAlert = new Alert(Alert.AlertType.ERROR);
        FormAlert.setTitle("UCAS CHAIRTY");
        FormAlert.setContentText("Enter All Fields");
        FormAlert.show();
    }

    @FXML
    public void DeleteDSH() {
//        DeleteAlert = new Alert(Alert.AlertType.ERROR);
//        DeleteAlert.setHeaderText("You Don't Have Permission");
//        DeleteAlert.setContentText("You Don't Have Permission to Make this Change \n Please Call Your System Administrators");
//        DeleteAlert.showAndWait();
//        DeleteAlert.getDialogPane().getScene().getWindow()).setAlwaysOnTop(true);
    }
    @FXML
    private void dshSignout(MouseEvent event) throws IOException {
        Parent view2 = FXMLLoader.load(getClass().getResource("../Login/loginPanel.fxml"));
        Scene scene2 = new Scene(view2);
        Stage Window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Window.setScene(scene2);
        Window.show();
        Window.setResizable(false);
        Window.setTitle("Ucas Chairty");
        Window.centerOnScreen();
    }


}