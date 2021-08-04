package boundaries.account_setting;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Optional;

import boundaries.account_setting.list_adapter.AccountAnnouncementListRowAdapter;
import boundaries.change_account_email.ChangeEmailLoader;
import boundaries.change_account_password.ChangePasswordLoader;
import boundaries.register_announcement.RegisterAnnouncementLoader;
import controllers.AccountController;
import controllers.AddressController;
import controllers.AnnouncementController;
import controllers.PersonalDataController;

import controllers.support.ApplicationObserver;
import controllers.support.Support;
import entities.account.Account;
import entities.address.Address;
import entities.announcement.Announcement;
import entities.personal_data.GenderEnum;
import entities.personal_data.PersonalData;
import entities.personal_data.TitleEnum;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Callback;

public class AccountSettingController implements ApplicationObserver {

	private ObservableList<GenderEnum> genderOptions = FXCollections.observableArrayList();
	private ObservableList<TitleEnum> titleOptions = FXCollections.observableArrayList();

	private Account myAccount;
	private PersonalData myPersonalData;
	private Address myAddress;

	private AccountController myAccountController;
	private AnnouncementController myAnnouncementController;
	private PersonalDataController myPersonalDataController;
	private AddressController myAddressController;

	private Stage stage;
	private boolean isSettingChanged = false;

	@FXML
	private ComboBox<TitleEnum> cmbx_title;

	@FXML
	private TextField txtfld_name;

	@FXML
	private Label lbl_emailAddress;

	@FXML
	private Label lbl_registrationDate;

	@FXML
	private Button btn_exit;

	@FXML
	private TextField txtfld_addressLine1;

	@FXML
	private ComboBox<GenderEnum> cmbx_gender;

	@FXML
	private TextField txtfld_addressLine2;

	@FXML
	private Button btn_saveChanges;

	@FXML
	private Button btn_insertNewAnnouncement;

	@FXML
	private TextField txtfld_fullNameAddress;

	@FXML
	private DatePicker dtpkr_birthday;

	@FXML
	private Label lbl_accountType;

	@FXML
	private TextField txtfld_surname;

	@FXML
	private TextField txtfld_phoneNumber;

	@FXML
	private Button btn_discardChanges;

	@FXML
	private TextField txtfld_town;

	@FXML
	private TextField txtfld_postcode;

	@FXML
	private TextField txtfld_province;

	@FXML
	private ListView<Announcement> ltsv_myAccouncement;

	/**
	 * Field
	 */
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

	@FXML
	void saveEventHandler(ActionEvent event) {
		try {
			personalDataCheck();
			AddressCheck();
		} catch (Exception e) {
			Support.getDialog(Alert.AlertType.ERROR, "Error", e.getMessage()).show();
			return;
		}

		// Setting PersonalData
		this.myPersonalData.setName(this.name);
		this.myPersonalData.setSurname(this.surname);
		this.myPersonalData.setGender(this.gender);
		this.myPersonalData.setTitle(this.title);
		this.myPersonalData.setBirthDate(this.birthday);

		// Setting shipping address...
		this.myAddress.setFullName(this.fullName);
		this.myAddress.setAddressLine1(this.addressLine1);
		this.myAddress.setAddressLine2(this.addressLine2);
		this.myAddress.setTown(this.town);
		this.myAddress.setProvince(this.province);
		this.myAddress.setPostcode(this.postcode);
		this.myAddress.setPhoneNumber(this.phoneNumber);

		this.myAddressController.updateAddress(this.myAddress);
		this.myPersonalDataController.updatePersonalData(this.myPersonalData);

		Support.getDialog(Alert.AlertType.CONFIRMATION, "Message", "Data updated!").show();

		btn_saveChanges.setDisable(true);
		btn_discardChanges.setDisable(true);
	}

	/**
	 * This method is used to initialize the boundary.
	 * 
	 * @param arg0
	 *            - An {@link Stage} object.
	 * @param arg1
	 *            - An {@link AccountController} object.
	 * @param arg3
	 * @param arg4
	 */
	public void initialization(Stage arg0, AccountController arg1, AnnouncementController arg2,
			PersonalDataController arg3, AddressController arg4) {
		this.stage = arg0;
		this.myAccountController = arg1;
		this.myAnnouncementController = arg2;
		this.myPersonalDataController = arg3;
		this.myAddressController = arg4;

		// ComboBoxes Initialization...
		for (GenderEnum obj : GenderEnum.values()) {
			genderOptions.add(obj);
		}

		for (TitleEnum obj : TitleEnum.values()) {
			titleOptions.add(obj);
		}

		cmbx_gender.setItems(genderOptions);
		cmbx_title.setItems(titleOptions);

		this.myAccountController.addObserver(this);
		this.myAnnouncementController.addObserver(this);

		update();
	}

	/**
	 * This method remove current logged {@code Account} object.
	 * 
	 * @param event
	 *            - Represents an {@code ActionEvent} object.
	 */
	@FXML
	void removeAccountEventHandler(ActionEvent event) {

		/**
		 * Open a dialog.
		 */
		Optional<ButtonType> result = Support
				.getDialog(Alert.AlertType.CONFIRMATION, "ATTENTION", "You want remove your account?").showAndWait();

		if (result.get() == ButtonType.OK) {
			this.myAccountController.removeObserver(this);
			myAccountController.removeCurrentLoggedAccount();
			stage.close();
		} else
			return;
	}

	@FXML
	void changePasswordEventHandler(ActionEvent event) {
		ChangePasswordLoader x = new ChangePasswordLoader();
		try {
			x.start(new Stage(), myAccountController);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@FXML
	void changeEmailHandler(ActionEvent event) {
		ChangeEmailLoader x = new ChangeEmailLoader();
		try {
			x.start(new Stage(), myAccountController);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * This method is used to close current stage.
	 * 
	 * @param arg0
	 *            - An {@link ActionEvent} object.
	 */
	@FXML
	void closeEventHandler(ActionEvent event) {

		Optional<ButtonType> result;

		if (this.isSettingChanged)
			result = Support.getDialog(Alert.AlertType.CONFIRMATION, "Message", "Are you sure? Any change will lost.")
					.showAndWait();
		else
			result = Support.getDialog(Alert.AlertType.CONFIRMATION, "Message", "You want exit?").showAndWait();

		if (result.get() == ButtonType.OK) {
			myAccountController.removeObserver(this);
			this.stage.close();
		} else
			return;
	}

	@FXML
	void discardChangeEventHandler(ActionEvent event) {
		/**
		 * Open a dialog.
		 */
		Optional<ButtonType> result = Support.getDialog(Alert.AlertType.CONFIRMATION, "Message", "Discard changes?")
				.showAndWait();

		if (result.get() == ButtonType.OK)
			update();
		this.isSettingChanged = false;
	}

	/**
	 * This method is used to update boundary.
	 */
	public void update() {

		/**
		 * Update Announcement List
		 */

		ObservableList<Announcement> myList = FXCollections.observableArrayList();
		for (Announcement object : this.myAnnouncementController
				.getAnnouncementObjectByAccountID(myAccountController.getCurrentLoggedAccount().getID()))
			myList.add(object);

		ltsv_myAccouncement.setItems(myList);

		ltsv_myAccouncement.setCellFactory(new Callback<ListView<Announcement>, ListCell<Announcement>>() {

			@Override
			public ListCell<Announcement> call(ListView<Announcement> param) {
				return new AccountAnnouncementListRowAdapter(myAnnouncementController);
			}
		});

		this.myAccount = this.myAccountController.getCurrentLoggedAccount();
		this.myPersonalData = this.myPersonalDataController.getPersonalData(this.myAccount.getID());
		this.myAddress = this.myAddressController.getPersonalData(this.myAccount.getID());

		// Personal Data
		txtfld_name.setText(myPersonalData.getName());
		txtfld_surname.setText(myPersonalData.getSurname());
		cmbx_gender.setValue(myPersonalData.getGender());
		cmbx_title.setValue(myPersonalData.getTitle());
		dtpkr_birthday.setValue(myPersonalData.getBirthDate());

		// Address
		txtfld_fullNameAddress.setText(myAddress.getFullName());
		txtfld_addressLine1.setText(myAddress.getAddressLine1());
		txtfld_addressLine2.setText(myAddress.getAddressLine2());
		txtfld_town.setText(myAddress.getTown());
		txtfld_postcode.setText(myAddress.getPostcode());
		txtfld_province.setText(myAddress.getProvince());
		txtfld_phoneNumber.setText(myAddress.getPhoneNumber());

		// Account Details
		lbl_emailAddress.setText(myAccount.getEmail());
		lbl_registrationDate.setText(myAccount.getRegistrationDate().toString());
		lbl_accountType.setText(myAccount.getType().toString());

		btn_saveChanges.setDisable(true);
		btn_discardChanges.setDisable(true);

	}

	@FXML
	void enableSaveChangesEventHandler() {
		btn_saveChanges.setDisable(false);
		btn_discardChanges.setDisable(false);
	}

	@FXML
	void registerNewAnnouncementEventHandler(ActionEvent event) {
		RegisterAnnouncementLoader x = new RegisterAnnouncementLoader();
		try {
			x.start(new Stage(), myAccountController);
			System.out.println("ASDA");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
