package dashboard;


import Login.loginOption;
import com.jfoenix.controls.JFXButton;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import dbConsole.dbConnection;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


/**
 *
 * @author Abdelrahman Al-Majayda
 * @version 1.0
 * @since   2020-04-3
 * @link https://github.com/itsdarksama
 *
 */


public class DashboardController implements Initializable {

    dbConnection dc;
    String alert;
    Color alertColor;
    int Index;

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

    // Employees Panel
    @FXML
    private AnchorPane emPane;
    @FXML
    private TableView<emData> emTabel;
    @FXML
    private TableColumn<emData, Integer> emIDCoulmn;
    @FXML
    private TableColumn<emData, String> emFnameCoulmn;
    @FXML
    private TableColumn<emData, String> emLnameCoulmn;
    @FXML
    private TableColumn<emData, String> emPosCoulmn;
    @FXML
    private TableColumn<emData, String> emMobileCoulmn;
    @FXML
    private TableColumn<emData, String> emEmailCoulmn;

    @FXML
    private TableColumn<emData, String> emUsernameCoulmn;

    @FXML
    private TableColumn<emData, String> emPassCoulmn;

    @FXML
    private TableColumn<emData, String> emPreCoulmn;

    @FXML
    private JFXTextField emFnameForm;

    @FXML
    private JFXTextField emLnForm;

    @FXML
    private JFXTextField emPosForm;

    @FXML
    private JFXTextField emMobileForm;

    @FXML
    private JFXTextField emIDForm;

    @FXML
    private JFXTextField emEmailForm;

    @FXML
    private JFXTextField emUserForm;

    @FXML
    private JFXTextField emPassForm;

    @FXML
    private JFXComboBox<dashboardOption> emPreCombo;

    //Alerts
    @FXML
    private Label statusLabelWh;
    @FXML
    private Label statusLabelNp;
    @FXML
    private Label statusLabelEm;


    @FXML
    private void sidebarButtons(ActionEvent event) throws SQLException {

        if (dshBtn.isHover()) {
            whPane.setVisible(false);
            npPane.setVisible(false);
            emPane.setVisible(false);
            dshPane.setVisible(true);
            npClear();
            whClear();
            emClear();

        } else if (whBtn.isHover()) {
            dshPane.setVisible(false);
            npPane.setVisible(false);
            emPane.setVisible(false);
            whPane.setVisible(true);
            whTabelStart();
            npClear();
            whClear();

        } else if (needyBtn.isHover()) {
            dshPane.setVisible(false);
            whPane.setVisible(false);
            emPane.setVisible(false);
            npPane.setVisible(true);
            npTabelStart();
            npClear();
            whClear();
            emClear();

        } else {
            dshPane.setVisible(false);
            npPane.setVisible(false);
            whPane.setVisible(false);
            emPane.setVisible(true);
            emTabelStart();
            npClear();
            whClear();
            emClear();
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
            emClear();
        } else if (dshNpBtn.isHover()) {
            dshPane.setVisible(false);
            whPane.setVisible(false);
            npPane.setVisible(true);
            npTabelStart();
            npClear();
            whClear();
            emClear();
        }

    }

    //Warehousse
    ObservableList<whData> whDataList;
    //NeedyPeople
    ObservableList<npData> npDataList;
    //Employees
    ObservableList<emData> emDataList;


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
        SuccessAlert();
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
        SuccessAlert();
    }


    //Delete whData
    public void DeleteWhData() {


        if (whItemForm.getText().isEmpty() || whSizeForm.getText().isEmpty() || whQuanForm.getText().isEmpty()) {

            ErrorAlert();
            return;

        }

        String whSqlDel = "delete from warehouseDB where whID =?";
        Integer whID = Integer.parseInt(whIdFourn.getText());
        try {
            Connection whConnInsert = dbConnection.getConnection();
            PreparedStatement whStmt = whConnInsert.prepareStatement(whSqlDel);
            whStmt.setInt(1, whID);
            whStmt.executeUpdate();
            whTabelStart();
            whClear();

        } catch (SQLException th) {
            th.printStackTrace();
        }
        SuccessAlert();
    }


    // whForms Clear
    private void whClear() {
        Index = -1;
        whItemForm.clear();
        whSizeForm.clear();
        whQuanForm.clear();
        whIdFourn.clear();
        statusLabelClear();
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
            return;
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
        SuccessAlert();
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
        SuccessAlert();

    }


    //Delete npData
    public void DeletenpData() {

        if (npFnameForm.getText().isEmpty() || npLnForm.getText().isEmpty() || npFmForm.getText().isEmpty() || npMobileForm.getText().isEmpty()) {
            ErrorAlert();
            return;
        }

        String npSqlDel = "delete from needyPeopleDB where npID =?";
        Integer npID = Integer.parseInt(npIDForm.getText());
        try {
            Connection npConnDelete = dbConnection.getConnection();
            PreparedStatement npStmtDel = npConnDelete.prepareStatement(npSqlDel);
            npStmtDel.setInt(1, npID);
            npStmtDel.executeUpdate();
            npTabelStart();
            npClear();

        } catch (SQLException th) {
            th.printStackTrace();
        }
        SuccessAlert();
    }


    // npForms Clear
    private void npClear() {

        Index = -1;
        npFnameForm.clear();
        npLnForm.clear();
        npFmForm.clear();
        npMobileForm.clear();
        npIDForm.clear();
        statusLabelClear();

    }




    @FXML
    private void ClearBtns(ActionEvent event) {

        npClear();
        whClear();
        emClear();
    }

    // Employees Show Tabel Method
    public void emTabelStart() {

        String emSql = "SELECT * FROM employeesDB";

        try {

            Connection emConn = dbConnection.getConnection();
            emDataList = FXCollections.observableArrayList();
            ResultSet emRs = emConn.createStatement().executeQuery(emSql);
            while (emRs.next()) {

                emDataList.add(new emData(emRs.getInt(1), emRs.getString(2)
                        , emRs.getString(3)
                        , emRs.getString(4)
                        , emRs.getString(5)
                        , emRs.getString(6)
                        , emRs.getString(7)
                        , emRs.getString(8)
                        , emRs.getString(9)));

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        emIDCoulmn.setCellValueFactory(new PropertyValueFactory<>("emID"));
        emFnameCoulmn.setCellValueFactory(new PropertyValueFactory<>("emFname"));
        emLnameCoulmn.setCellValueFactory(new PropertyValueFactory<>("emLname"));
        emPosCoulmn.setCellValueFactory(new PropertyValueFactory<>("emPos"));
        emMobileCoulmn.setCellValueFactory(new PropertyValueFactory<>("emMobile"));
        emEmailCoulmn.setCellValueFactory(new PropertyValueFactory<>("emEmail"));
        emUsernameCoulmn.setCellValueFactory(new PropertyValueFactory<>("emUser"));
        emPassCoulmn.setCellValueFactory(new PropertyValueFactory<>("emPass"));
        emPreCoulmn.setCellValueFactory(new PropertyValueFactory<>("emPre"));
        emTabel.setItems(null);
        emTabel.setItems(emDataList);
        emPreCombo.setItems(FXCollections.observableArrayList(dashboardOption.values()));

    }

    // Employees Select
    public void selectEmData() {

        Index = emTabel.getSelectionModel().getSelectedIndex();
        if (Index <= -1) {
            return;
        }

        emIDForm.setText(emIDCoulmn.getCellData(Index).toString());
        emFnameForm.setText(emFnameCoulmn.getCellData(Index));
        emLnForm.setText(emLnameCoulmn.getCellData(Index));
        emPosForm.setText(emPosCoulmn.getCellData(Index));
        emMobileForm.setText(emMobileCoulmn.getCellData(Index));
        emEmailForm.setText(emEmailCoulmn.getCellData(Index));
        emUserForm.setText(emUsernameCoulmn.getCellData(Index));
        emPassForm.setText(emPassCoulmn.getCellData(Index));


    }


    // emForms Clear
    private void emClear() {

        Index = -1;
        emIDForm.clear();
        emFnameForm.clear();
        emLnForm.clear();
        emPosForm.clear();//
        emMobileForm.clear();
        emEmailForm.clear();
        emUserForm.clear();
        emPassForm.clear();
        emPreCombo.getSelectionModel().clearSelection();
        statusLabelClear();

    }

    // Employees Add New Data
    public void addNewEmData() {

        String emSqlInsert = "INSERT INTO employeesDB(fName,lName,position,Mobile,Email,username,password,permissions) VALUES(?,?,?,?,?,?,?,?)";

        String emFname = emFnameForm.getText();
        String emLname = emLnForm.getText();
        String emPos = emPosForm.getText();
        String emMobile = emMobileForm.getText();
        String emEmail = emEmailForm.getText();
        String emUser = emUserForm.getText().toLowerCase();
        String emPass = emPassForm.getText();
        String emPre = emPreCombo.getValue().toString();

        if (emFnameForm.getText().isEmpty() || emLnForm.getText().isEmpty() || emPosForm.getText().isEmpty() || emUserForm.getText().isEmpty() || emPassForm.getText().isEmpty()) {
            ErrorAlert();
            return;
        }


        if (emPreCombo.getValue().toString().equals("admin") || emPreCombo.getValue().toString().equals("employee")) {
            try {
                Connection emConnInsert = dbConnection.getConnection();
                PreparedStatement emStmt = emConnInsert.prepareStatement(emSqlInsert);
                emStmt.setString(1, emFname);
                emStmt.setString(2, emLname);
                emStmt.setString(3, emPos);
                emStmt.setString(4, emMobile);
                emStmt.setString(5, emEmail);
                emStmt.setString(6, emUser);
                emStmt.setString(7, emPass);
                emStmt.setString(8, emPre);
                emStmt.execute();
                emConnInsert.close();
                emTabelStart();
                emClear();
            } catch (SQLException th) {
                th.printStackTrace();
            }
            SuccessAlert();
        }
    }


    //Delete employee Data
    public void DeleteEmData() {



        if (emFnameForm.getText().isEmpty() || emLnForm.getText().isEmpty() || emPosForm.getText().isEmpty() || emUserForm.getText().isEmpty() || emPassForm.getText().isEmpty()) {
            ErrorAlert();
            return;
        }

        String emSqlDel = "delete from employeesDB where emID =?";
        Integer emID = Integer.parseInt(emIDForm.getText());
        try {
            Connection emConnInsert = dbConnection.getConnection();
            PreparedStatement emStmt = emConnInsert.prepareStatement(emSqlDel);
            emStmt.setInt(1, emID);
            emStmt.executeUpdate();
            emTabelStart();
            emClear();

        } catch (SQLException th) {
            th.printStackTrace();
        }
        SuccessAlert();
    }

    // Employees Edit
    public void editEmData() {


        if (emFnameForm.getText().isEmpty() || emLnForm.getText().isEmpty() || emPosForm.getText().isEmpty() || emUserForm.getText().isEmpty() || emPassForm.getText().isEmpty()) {

            ErrorAlert();
            return;

        }

        String emFname = emFnameForm.getText();
        String emLname = emLnForm.getText();
        String emPos = emPosForm.getText();
        String emMobile = emMobileForm.getText();
        String emEmail = emEmailForm.getText();
        String emUser = emUserForm.getText().toLowerCase();
        String emPass = emPassForm.getText();
        String emPre = emPreCombo.getValue().toString();
        Integer emID = Integer.parseInt(emIDForm.getText());
        String emSqlEdit = "UPDATE employeesDB SET fName=? , lName=?, position=? , Mobile=?, Email=?, username=?, password=? , permissions=? where npID=?";

        try {
            Connection emConnInsert = dbConnection.getConnection();
            PreparedStatement emStmtDel = emConnInsert.prepareStatement(emSqlEdit);
            emStmtDel.setString(1, emFname);
            emStmtDel.setString(2, emLname);
            emStmtDel.setString(3, emPos);
            emStmtDel.setString(4, emMobile);
            emStmtDel.setString(5, emEmail);
            emStmtDel.setString(6, emUser);
            emStmtDel.setString(7, emPass);
            emStmtDel.setString(8, emPre);
            emStmtDel.setInt(9, emID);
            emStmtDel.executeUpdate();
            emConnInsert.close();
            emTabelStart();
            emClear();
        } catch (SQLException th) {
            th.printStackTrace();
        }
        SuccessAlert();

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

    //Error Alert
    public void ErrorAlert() {
        alert = "Please Enter All Fileds";
        alertColor = Color.web("#C70039");

        if (whPane.isVisible()) {
            statusLabelWh.setVisible(true);
            statusLabelWh.setText(alert);
            statusLabelWh.setTextFill(alertColor);
            return;
        } else if (npPane.isVisible()) {
            statusLabelNp.setVisible(true);
            statusLabelNp.setText(alert);
            statusLabelNp.setTextFill(alertColor);
        } else if (emPane.isVisible()) {
            statusLabelEm.setVisible(true);
            statusLabelEm.setText(alert);
            statusLabelEm.setTextFill(alertColor);
        } else {
            return;
        }

    }

    //Successful
    public void SuccessAlert() {

        alert = "Success";
        alertColor = Color.web("#07a300");

        if (whPane.isVisible()) {
            statusLabelWh.setVisible(true);
            statusLabelWh.setText(alert);
            statusLabelWh.setTextFill(alertColor);
            return;
        } else if (npPane.isVisible()) {
            statusLabelNp.setVisible(true);
            statusLabelNp.setText(alert);
            statusLabelNp.setTextFill(alertColor);
        } else if (emPane.isVisible()) {
            statusLabelEm.setVisible(true);
            statusLabelEm.setText(alert);
            statusLabelEm.setTextFill(alertColor);
        } else {
            return;
        }

    }
    @FXML
    private void statusLabelClear() {
        statusLabelNp.setText(null);
        statusLabelNp.setVisible(false);
        statusLabelWh.setText(null);
        statusLabelWh.setVisible(false);
        statusLabelEm.setText(null);
        statusLabelEm.setVisible(false);

    }

}
