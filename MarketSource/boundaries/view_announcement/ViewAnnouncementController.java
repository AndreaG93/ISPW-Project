package boundaries.view_announcement;

import controllers.AccountController;
import controllers.database.personal_data.PersonalDataDatabase;
import entities.account.Account;
import entities.announcement.Announcement;
import entities.personal_data.PersonalData;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ViewAnnouncementController {

	private Announcement myAnnouncement;
	private AccountController myAccountController;

	private Stage stage;

	@FXML
	private TextField txtfld_paymentMethod;

	@FXML
	private Button btn_transaction;

	@FXML
	private Label lbl_adTitle;

	@FXML
	private Button btn_cancel;

	@FXML
	private TextField txtfld_pubblicationDate;

	@FXML
	private TextField txtfld_cost;

	@FXML
	private TextField txtfld_shippingMethod;

	@FXML
	private TextArea txtr_adDescrption;

	@FXML
	private TextField txtfld_owner;

	/**
	 * This method is used to close current stage.
	 * 
	 * @param arg0
	 *            - An {@link ActionEvent} object.
	 */
	@FXML
	public void closeEventHandler(ActionEvent arg0) {
		stage.close();
	}

	/**
	 * This method is used to initialize the boundary.
	 * 
	 * @param arg0
	 *            - An {@link Stage} object.
	 * @param arg1
	 *            - An {@link Announcement} object.
	 */
	public void initialization(Stage arg0, Announcement arg1, AccountController arg2) {
		this.stage = arg0;
		this.myAnnouncement = arg1;
		this.myAccountController = arg2;
		transactionEnabler();
		
		PersonalData var = PersonalDataDatabase.getInstance().get(this.myAnnouncement.getOwnerID());
		
		lbl_adTitle.setText(arg1.getProduct().getProductName());
		txtr_adDescrption.setText(arg1.getProduct().getProductDescription());

		txtfld_cost.setText(String.valueOf(arg1.getType().getCost()));
		txtfld_owner.setText(var.getName() + " " + var.getSurname());
				
		txtfld_pubblicationDate.setText(arg1.getPublicationDate().toString());
	}

	@FXML
	void transactionEventHandler(ActionEvent event) {

	}
	
	/**
	 * This method is used to enable or disable transaction button
	 */
	private void transactionEnabler()
	{
		Account obj = myAccountController.getCurrentLoggedAccount();
		if (obj == null)
			return;
		
		if(obj.getID() == this.myAnnouncement.getOwnerID())
		{
			btn_transaction.setText("It's your announcement");
			return;
		}
		
		btn_transaction.setDisable(false);
	}
	
	

}
