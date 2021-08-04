package boundaries.payment_setting;

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
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class PaymentSettingController {

	@FXML
	private Tab id_tab3_Informations;

	@FXML
	private Tab id_tab1_Payment;

	@FXML
	private Label id_lbl_successOptionChange;

	@FXML
	private ComboBox<String> id_comboBox2;

	@FXML
	private Tab id_tab2_Confirmation;

	@FXML
	private ComboBox<String> id_comboBox1;

	@FXML
	private TreeView<String> id_myTreeView;

	@FXML
	private Label id_lbl_treeViewChoice;

	@FXML
	private Label id_lbl_cmBoxChoise2;

	@FXML
	private Button id_btn_confirmOptions;

	@FXML
	private Label lbl_myComboBoxChoice2;

	@FXML
	private Label id_lbl_cmBoxChoise1;

	/**
	 * 
	 * 
	 * 
	 */
	ObservableList<String> list1 = FXCollections.observableArrayList("Immediate/online", "On delivery");
	ObservableList<String> list2 = FXCollections.observableArrayList("Via email", "Via fax", "Via courier");

	@SuppressWarnings("unchecked")
	public void initialize(Stage myStage) {
		id_comboBox1.setItems(list1);
		id_comboBox2.setItems(list2);

		// TreeView
		TreeItem<String> myRoot = new TreeItem<>("Type of payment");

		TreeItem<String> nodeA = new TreeItem<>("Cash");
		TreeItem<String> nodeB = new TreeItem<>("Credit card");
		TreeItem<String> nodeC = new TreeItem<>("Gift card");
		TreeItem<String> nodeD = new TreeItem<>("Coupon");

		myRoot.getChildren().addAll(nodeA, nodeB, nodeC, nodeD);

		TreeItem<String> nodeB1 = new TreeItem<>("Visa");
		TreeItem<String> nodeB2 = new TreeItem<>("Mastercard");
		TreeItem<String> nodeB3 = new TreeItem<>("American express");

		nodeB.getChildren().addAll(nodeB1, nodeB2, nodeB3);
		nodeB.setExpanded(true);

		TreeItem<String> nodeC1 = new TreeItem<>("10 $");
		TreeItem<String> nodeC2 = new TreeItem<>("50 $");
		TreeItem<String> nodeC3 = new TreeItem<>("100 $");

		nodeC.getChildren().addAll(nodeC1, nodeC2, nodeC3);
		nodeC.setExpanded(true);

		id_myTreeView.setRoot(myRoot);
	}

	public void selectElementCmBox(ActionEvent event) {
		// getValue(): give me the current selected value of ComboBox and
		// DatePicker.

		id_lbl_cmBoxChoise1.setText(id_comboBox1.getValue());
		id_lbl_cmBoxChoise2.setText(id_comboBox2.getValue());
	}

	public void clickInTreeView(MouseEvent mouseEvent) {
		if (mouseEvent.getClickCount() == 2) {
			TreeItem<String> item = id_myTreeView.getSelectionModel().getSelectedItem();
			id_lbl_treeViewChoice.setText(item.getValue().toString());
		}
	}

	public void addPremiumElements(ActionEvent event) {
		id_comboBox2.getItems().addAll("Via Drone", "Via our company's courier");

	}

	public void changeTab12(ActionEvent event) {
		id_tab1_Payment.getTabPane().getSelectionModel().select(id_tab2_Confirmation);
	}

	public void changeTab21(ActionEvent event) {
		id_tab2_Confirmation.getTabPane().getSelectionModel().select(id_tab1_Payment);
	}

	public void changeTab13(ActionEvent event) {
		id_tab1_Payment.getTabPane().getSelectionModel().select(id_tab3_Informations);
	}

	public void changeTab23(ActionEvent event) {
		id_tab2_Confirmation.getTabPane().getSelectionModel().select(id_tab3_Informations);
	}

	public void changeTab31(ActionEvent event) {
		id_tab3_Informations.getTabPane().getSelectionModel().select(id_tab1_Payment);
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
					id_lbl_successOptionChange.setText("The payment options are changed successfully.");
					myDialog.close();
				} else {

					id_lbl_cmBoxChoise1.setText("");
					id_lbl_cmBoxChoise2.setText("");
					id_lbl_treeViewChoice.setText("");
					event.consume();
				}
			}
		});

	}

}
