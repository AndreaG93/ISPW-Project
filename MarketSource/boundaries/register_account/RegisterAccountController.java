package boundaries.register_account;

import java.io.File;
import java.time.LocalDate;
import java.util.Optional;
import java.util.Vector;

import controllers.AccountController;
import controllers.AddressController;
import controllers.PersonalDataController;
import controllers.database.account.AccountDatabase;
import controllers.database.address.AddressDatabase;
import controllers.database.personal_data.PersonalDataDatabase;
import controllers.support.Support;
import entities.account.type.Standard;
import entities.personal_data.GenderEnum;
import entities.personal_data.TitleEnum;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ProgressBar;

public class RegisterAccountController {

	PersonalDataController myPersonalDataController;
	AddressController myAddressController;
	AccountController myAccountController;

	@FXML
	private ComboBox<TitleEnum> cmbx_title;

	@FXML
	private TextField txtfld_name;

	@FXML
	private TextField txtfld_email;

	@FXML
	private Button btn_back;

	@FXML
	private ComboBox<GenderEnum> cmbx_gender;

	@FXML
	private Button btn_finish;

	@FXML
	private TextField txtfld_fullNameAddress;

	@FXML
	private DatePicker dtpkr_birthday;

	@FXML
	private TextField txtfld_surname;

	@FXML
	private TextField txtfld_phoneNumber;

	@FXML
	private PasswordField txtfld_password_repeated;

	@FXML
	private Button btn_next;

	@FXML
	private TextField txtfld_province;

	@FXML
	private PasswordField txtfld_password;

	@FXML
	private TextField txtfld_addressLine1;

	@FXML
	private TextField txtfld_addressLine2;

	@FXML
	private Button btn_cancel;

	@FXML
	private ImageView imgv_panel;

	@FXML
	private VBox vbx_page_4;

	@FXML
	private VBox vbx_page_3;

	@FXML
	private VBox vbx_page_2;

	@FXML
	private VBox vbx_page_1;

	@FXML
	private TextField txtfld_town;

	@FXML
	private TextField txtfld_postcode;

	@FXML
	private Label lbl_passQuality;

	@FXML
	private ProgressBar prgrssbar_passQuality;

	/**
	 * Field
	 */
	private String email;
	private String password;
	private String fullName;
	private String addressLine1;
	private String addressLine2;
	private String town;
	private String province;
	private String postcode;
	private String phoneNumber;
	private TitleEnum title;
	private String name;
	private String surname;
	private GenderEnum gender;
	private LocalDate birthday;

	private ObservableList<GenderEnum> genderOptions = FXCollections.observableArrayList();
	private ObservableList<TitleEnum> titleOptions = FXCollections.observableArrayList();

	private Stage stage;
	private int page = 1;
	private Vector<VBox> vectorPages = new Vector<>();

	/**
	 * This method checks informations about account.
	 */
	private void accountCheck() throws Exception {

		Support.emailAddressCheckField(txtfld_email.getText());
		Support.passwordCheckField(txtfld_password.getText(), txtfld_password_repeated.getText());

		this.email = txtfld_email.getText();
		this.password = txtfld_password.getText();
	}

	/**
	 * This method checks informations about personal data.
	 */
	private void personalDataCheck() throws Exception {

		Support.onlyLetterCheckField(txtfld_name.getText(), "'Name'");
		Support.onlyLetterCheckField(txtfld_surname.getText(), "'Surname'");
		Support.nullCheckField(dtpkr_birthday.getValue(), "'Birthday'");

		this.title = cmbx_title.getValue();
		this.name = txtfld_name.getText();
		this.surname = txtfld_surname.getText();
		this.gender = cmbx_gender.getValue();
		this.birthday = dtpkr_birthday.getValue();
	}

	/**
	 * This method checks informations about address.
	 */
	private void AddressCheck() throws Exception {

		Support.emptyCheckField(txtfld_fullNameAddress.getText(), "'Full Name'");
		Support.emptyCheckField(txtfld_addressLine1.getText(), "'Address Line 1'");
		Support.emptyCheckField(txtfld_town.getText(), "'Town'");
		Support.emptyCheckField(txtfld_province.getText(), "'Province'");
		Support.emptyCheckField(txtfld_postcode.getText(), "'PostCode'");
		Support.emptyCheckField(txtfld_phoneNumber.getText(), "'Phone Number'");

		this.fullName = txtfld_fullNameAddress.getText();
		this.addressLine1 = txtfld_addressLine1.getText();
		this.addressLine2 = txtfld_addressLine2.getText();
		this.town = txtfld_town.getText();
		this.province = txtfld_province.getText();
		this.postcode = txtfld_postcode.getText();
		this.phoneNumber = txtfld_phoneNumber.getText();
	}

	/**
	 * This method register an {@code Account}, a {@code PersonalData} and an
	 * {@code Address} object into database.
	 * 
	 * @param event
	 *            - Represents a {@code ActionEvent} object.
	 */
	@FXML
	private synchronized void finishEventHandler(ActionEvent event) {

		int ID = AccountDatabase.getInstance().getNewID();

		AccountDatabase.getInstance().insert(ID, email, password, Standard.class.getName());
		AddressDatabase.getInstance().insert(ID, fullName, addressLine1, addressLine2, town, postcode, province,
				phoneNumber);
		PersonalDataDatabase.getInstance().insert(ID, name, surname, title, gender, birthday);

		this.stage.close();
	}

	/**
	 * This method is used to initialize the boundary.
	 * 
	 * @param arg0
	 *            - An {@link Stage} object.
	 */
	public void initialization(Stage arg0) {
		this.stage = arg0;

		/**
		 * Pages initialization...
		 */
		this.vectorPages.add(vbx_page_1);
		this.vectorPages.add(vbx_page_2);
		this.vectorPages.add(vbx_page_3);
		this.vectorPages.add(vbx_page_4);

		/**
		 * Image Panel initialization...
		 */
		File file = new File("src/panel.png");
		Image image = new Image(file.toURI().toString());
		this.imgv_panel.setImage(image);
		this.imgv_panel.fitHeightProperty().bind(this.stage.heightProperty());

		/**
		 * ComboBoxes initialization...
		 */
		for (GenderEnum obj : GenderEnum.values()) {
			genderOptions.add(obj);
		}

		for (TitleEnum obj : TitleEnum.values()) {
			titleOptions.add(obj);
		}

		cmbx_gender.setItems(genderOptions);
		cmbx_title.setItems(titleOptions);

		updateCurrentBoundary();
	}

	/**
	 * This method is used to close current stage.
	 * 
	 * @param arg0
	 *            - An {@link ActionEvent} object.
	 */
	@FXML
	public void closeEventHandler(ActionEvent arg0) {
		Alert dialog = Support.getDialog(Alert.AlertType.CONFIRMATION, "Message", "You want abort registration?");
		Optional<ButtonType> result = dialog.showAndWait();
		if (result.get() == ButtonType.OK) {
			stage.close();
		} else {
			return;
		}
	}

	@FXML
	void backEventHandler(ActionEvent event) {
		this.page--;
		updateCurrentBoundary();
	}

	@FXML
	void nextEventHandler(ActionEvent event) {
		try {
			switch (this.page) {
			case 1: {
				accountCheck();
				break;
			}
			case 2: {
				personalDataCheck();
				break;
			}
			case 3: {
				AddressCheck();
				break;
			}
			}
		} catch (Exception e) {
			Support.getDialog(Alert.AlertType.ERROR, "Error", e.getMessage()).show();
			return;
		}

		this.page++;
		updateCurrentBoundary();
	}

	public void updateCurrentBoundary() {
		for (VBox x : vectorPages) {
			x.setVisible(false);
		}

		btn_finish.setDisable(true);

		if (this.page == 1) {
			vbx_page_1.setVisible(true);
			btn_back.setDisable(true);
			btn_next.setDisable(false);

		}
		if (this.page == 2) {
			vbx_page_2.setVisible(true);
			btn_back.setDisable(false);
			btn_next.setDisable(false);

		}
		if (this.page == 3) {
			vbx_page_3.setVisible(true);
			btn_back.setDisable(false);
			btn_next.setDisable(false);
		}
		if (this.page == 4) {
			vbx_page_4.setVisible(true);
			btn_back.setDisable(false);
			btn_next.setDisable(true);
			btn_finish.setDisable(false);
		}
	}
}