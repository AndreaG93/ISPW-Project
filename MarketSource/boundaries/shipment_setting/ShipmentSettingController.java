package boundaries.shipment_setting;

import java.util.Optional;

import controllers.support.Support;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.stage.Stage;

public class ShipmentSettingController {

	@FXML
	private Tab id_tab1_Shipment;

	@FXML
	private Tab id_tab3_Informations;

	@FXML
	private Label id_lbl_cmBoxChoise4;

	@FXML
	private Label id_lbl_cmBoxChoise3;

	@FXML
	private Label id_lbl_cmBoxChoise2;

	@FXML
	private Label id_lbl_cmBoxChoise1;

	@FXML
	private Label id_lbl_successOptionChange;

	@FXML
	private DatePicker id_datePicker;

	@FXML
	private Label id_lbl_datePickerChoise;

	@FXML
	private ComboBox<String> id_comboBox4;

	@FXML
	private ComboBox<String> id_comboBox3;

	@FXML
	private ComboBox<String> id_comboBox2;

	@FXML
	private Tab id_tab2_Confirmation;

	@FXML
	private ComboBox<String> id_comboBox1;

	@FXML
	private Button id_btn_confirmOptions;

	@FXML
	private Label lbl_myComboBoxChoice2;

	/**
	 * 
	 * 
	 * 
	 */
	ObservableList<String> list1 = FXCollections.observableArrayList("Bicycle", "Car", "Train", "Drone");
	ObservableList<String> list2 = FXCollections.observableArrayList("High priority", "Standard priority",
			"Low priority");
	ObservableList<String> list3 = FXCollections.observableArrayList("Yes", "No");
	ObservableList<String> list4 = FXCollections.observableArrayList("123AIK", "223AP", "988WQP", "234KKA");

	public void initialize(Stage myStage) {
		id_comboBox1.setItems(list1);
		id_comboBox2.setItems(list2);
		id_comboBox3.setItems(list3);
		id_comboBox4.setItems(list4);
	}

	public void selectElementCmBox(ActionEvent event) {
		// getValue(): give me the current selected value of ComboBox and
		// DatePicker.

		id_lbl_cmBoxChoise1.setText(id_comboBox1.getValue());
		id_lbl_cmBoxChoise2.setText(id_comboBox2.getValue());
		id_lbl_cmBoxChoise3.setText(id_comboBox3.getValue());
		id_lbl_cmBoxChoise4.setText(id_comboBox4.getValue());
	}

	public void selectElementDtPkr(ActionEvent event) {
		id_lbl_datePickerChoise.setText(id_datePicker.getValue().toString());
	}

	public void addPremiumElements(ActionEvent event) {
		id_comboBox1.getItems().addAll("Plane", "Space shuttle");
		id_comboBox2.getItems().addAll("Premium priority");
		id_comboBox4.getItems().addAll("007ABA");
	}

	public void changeTab12(ActionEvent event) {
		id_tab1_Shipment.getTabPane().getSelectionModel().select(id_tab2_Confirmation);
	}

	public void changeTab21(ActionEvent event) {
		id_tab2_Confirmation.getTabPane().getSelectionModel().select(id_tab1_Shipment);
	}

	public void changeTab13(ActionEvent event) {
		id_tab1_Shipment.getTabPane().getSelectionModel().select(id_tab3_Informations);
	}

	public void changeTab23(ActionEvent event) {
		id_tab2_Confirmation.getTabPane().getSelectionModel().select(id_tab3_Informations);
	}

	public void changeTab31(ActionEvent event) {
		id_tab3_Informations.getTabPane().getSelectionModel().select(id_tab1_Shipment);
	}

	public void changeTab32(ActionEvent event) {
		id_tab3_Informations.getTabPane().getSelectionModel().select(id_tab2_Confirmation);
	}

	public void confirmOptions(ActionEvent event) {
		id_btn_confirmOptions.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {

				Alert myDialog = Support.getDialog(Alert.AlertType.CONFIRMATION, "Message",
						"Do you want confirm your options?");

				myDialog.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);

				Optional<ButtonType> result = myDialog.showAndWait();

				if (result.get() == ButtonType.YES) {
					id_lbl_successOptionChange.setText("The shipping options are changed successfully.");
					myDialog.close();
				} else {

					id_lbl_cmBoxChoise1.setText("");
					id_lbl_cmBoxChoise2.setText("");
					id_lbl_cmBoxChoise3.setText("");
					id_lbl_cmBoxChoise4.setText("");
					id_lbl_datePickerChoise.setText("");
					event.consume();
				}
			}
		});

	}

}