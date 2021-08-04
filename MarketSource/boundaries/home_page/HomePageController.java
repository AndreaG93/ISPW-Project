package boundaries.home_page;

import java.io.IOException;
import java.util.Optional;
import boundaries.account_setting.AccountSettingLoader;
import boundaries.home_page.list_adapter.AnnouncementListRowAdapter;
import boundaries.login.LoginLoader;
import boundaries.payment_setting.PaymentSettingLoader;
import boundaries.register_account.RegisterAccountLoader;
import boundaries.register_announcement.RegisterAnnouncementLoader;
import boundaries.shipment_setting.ShipmentSettingLoader;
import controllers.AccountController;
import controllers.AddressController;
import controllers.AnnouncementController;
import controllers.PersonalDataController;
import controllers.database.personal_data.PersonalDataDatabase;
import controllers.support.ApplicationObserver;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import javafx.util.Callback;
import entities.account.Account;
import entities.announcement.Announcement;
import entities.personal_data.PersonalData;

public class HomePageController implements ApplicationObserver {
	private AccountController myAccountController;
	private AnnouncementController myAnnouncementController;
	private PersonalDataController myPersonalDataController;
	private AddressController myAddressController;

	private ObservableList<Announcement> myAnnouncementList = FXCollections.observableArrayList();

	@FXML
	private Button btn_registerAccount;

	@FXML
	private Button btn_login;

	@FXML
	private Button btn_registerAnnouncement;

	@FXML
	private Hyperlink hprlnk_currentAccount;

	@FXML
	private ListView<Announcement> lstv_listAnnouncement;

	/**
	 * This method is used to initialize boundaries.shipment_setting.
	 * @param event
	 * @throws Exception
	 */
	@FXML
	public void createShipmentSettingWindow(ActionEvent event) throws Exception {

		ShipmentSettingLoader x = new ShipmentSettingLoader();
		try {
			x.start(new Stage());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method is used to initialize boundaries.payment_setting.
	 * @param event
	 * @throws Exception
	 */
	@FXML
	public void createPaymentSettingWindow(ActionEvent event) throws Exception {

		PaymentSettingLoader x = new PaymentSettingLoader();
		try {
			x.start(new Stage());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	void dataAccountEventHandler(ActionEvent event) {

		AccountSettingLoader x = new AccountSettingLoader();
		try {
			x.start(new Stage(), myAccountController, myAnnouncementController, myPersonalDataController,
					myAddressController);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	void RegisterAnnouncementEventHandler(ActionEvent event) {
		RegisterAnnouncementLoader x = new RegisterAnnouncementLoader();
		try {
			x.start(new Stage(), myAccountController);
			System.out.println("ASDA");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	void RegisterEventHandler(ActionEvent event) {
		RegisterAccountLoader x = new RegisterAccountLoader();
		try {
			x.start(new Stage());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Stage stage;

	/**
	 * This method is used to initialize the boundary.
	 * 
	 * @param arg1
	 * @param arg2
	 * 
	 * @param event
	 *            - An {@link Stage} object.
	 * @see Stage
	 */
	public void initialization(Stage arg0, AccountController arg1, AnnouncementController arg2,
			PersonalDataController arg3, AddressController arg4) {
		this.stage = arg0;
		this.myAccountController = arg1;
		this.myAnnouncementController = arg2;
		this.myPersonalDataController = arg3;
		this.myAddressController = arg4;

		// Observer registration
		this.myAccountController.addObserver(this);
		this.myAnnouncementController.addObserver(this);
		this.myPersonalDataController.addObserver(this);
		this.myAddressController.addObserver(this);
		update();
	}

	/**
	 * This method is used to update the Announcement list.
	 */
	private void viewAllAnnouncement() {

		myAnnouncementList.clear();

		for (Announcement object : this.myAnnouncementController.getAllRegisteredAnnouncementObject()) {
			myAnnouncementList.add(object);

		}
		lstv_listAnnouncement.setItems(myAnnouncementList);

		lstv_listAnnouncement.setCellFactory(new Callback<ListView<Announcement>, ListCell<Announcement>>() {

			@Override
			public ListCell<Announcement> call(ListView<Announcement> param) {
				return new AnnouncementListRowAdapter(myAccountController);
			}
		});
	}

	/**
	 * This method is used to update the boundary when a user do login o login
	 * Out
	 */
	public void update() {
		viewAllAnnouncement();
		Account loggedAccount = myAccountController.getCurrentLoggedAccount();

		if (loggedAccount != null) {

			PersonalData var = PersonalDataDatabase.getInstance()
					.get(myAccountController.getCurrentLoggedAccount().getID());

			String name = var.getName();
			String surname = var.getSurname();

			hprlnk_currentAccount.setDisable(false);
			btn_registerAnnouncement.disableProperty().set(false);
			hprlnk_currentAccount.setText("Hello " + name + " " + surname + "! Click here to open your Reserved Area!");
			btn_login.setText("Logout");
			btn_login.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {

					Alert dialog = new Alert(Alert.AlertType.CONFIRMATION);
					dialog.setTitle("Message");
					dialog.setHeaderText("You want logout?");
					Optional<ButtonType> result = dialog.showAndWait();
					if (result.get() == ButtonType.OK) {
						myAccountController.logout();
					} else {
						return;
					}
				}
			});
		} else {
			hprlnk_currentAccount.setDisable(true);
			btn_registerAnnouncement.disableProperty().set(true);
			hprlnk_currentAccount.setText("You are not logged");
			btn_login.setText("Login");
			btn_login.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					LoginLoader x = new LoginLoader();
					try {
						x.start(new Stage(), myAccountController);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			});
		}

	}

}
