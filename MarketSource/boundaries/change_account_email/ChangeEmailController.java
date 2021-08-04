package boundaries.change_account_email;

import java.util.Optional;

import controllers.AccountController;
import controllers.support.Support;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class ChangeEmailController {
	private AccountController myAccountController;
	private Stage myStage;

	@FXML
	private Button btn_submit;

	@FXML
	private BorderPane root;

	@FXML
	private TextField txtfld_oldEmail;

	@FXML
	private TextField txtfld_newReenteredEmail;

	@FXML
	private TextField txtfld_newEmail;

	@FXML
	private Button btn_close;

	/**
	 * This method is used to update email address of current logged user.
	 * 
	 * @param event
	 *            - Represents an {@code ActionEvent} object.
	 */
	@FXML
	void saveChangesEventHandler(ActionEvent event) {
		String newEmail = txtfld_newEmail.getText();
		String newEmailRetype = txtfld_newReenteredEmail.getText();

		try {
			if (newEmail.isEmpty() || newEmail == null)
				throw new Exception("The 'Email Address' field is empty.");

			if (newEmail.equals(txtfld_oldEmail.getText()))
				throw new Exception("You must use a different email address.");

			Support.emailAddressCheckField(newEmail);

			if (!newEmail.equals(newEmailRetype))
				throw new Exception("The 'Email Addresses' doesn't match.");

		} catch (Exception e) {
			Support.getDialog(Alert.AlertType.ERROR, "Error", e.getMessage()).show();
			return;
		}

		// Update
		myAccountController.getCurrentLoggedAccount().setEmail(newEmail);
		myAccountController.updateAccount();

		Support.getDialog(Alert.AlertType.CONFIRMATION, "Message", "Email updated").show();
		this.myStage.close();
	}

	/**
	 * This method is used to close current {@code Stage} object.
	 * 
	 * @param event
	 *            - Represents an {@code ActionEvent} object.
	 */
	@FXML
	void closeEventHandler(ActionEvent event) {
		Alert myDialog = Support.getDialog(Alert.AlertType.CONFIRMATION, "Message", "Do you want exit?");
		myDialog.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);

		Optional<ButtonType> result = myDialog.showAndWait();
		if (result.get() == ButtonType.YES)
			myStage.close();
		else
			return;
	}

	/**
	 * This method is used to initialize current boundary.
	 * 
	 * @param arg0
	 *            - Represents an {@code Stage} object.
	 * @param arg1
	 *            - Represents an {@code AccountController} object.
	 */
	public void initialization(Stage arg0, AccountController arg1) {
		this.myStage = arg0;
		this.myAccountController = arg1;
		this.txtfld_oldEmail.setText(arg1.getCurrentLoggedAccount().getEmail());
	}

}
