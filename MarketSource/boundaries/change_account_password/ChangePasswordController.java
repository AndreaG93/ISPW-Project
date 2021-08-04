package boundaries.change_account_password;

import java.util.Optional;
import controllers.AccountController;
import controllers.support.Support;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class ChangePasswordController {
	private AccountController myAccountController;
	private Stage myStage;

	@FXML
	private Button btn_submit;

	@FXML
	private PasswordField txtfld_password_old;

	@FXML
	private BorderPane root;

	@FXML
	private PasswordField txtfld_password;

	@FXML
	private PasswordField txtfld_password_repeated;

	@FXML
	private Button btn_close;

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
	 * This method is used to update password of current logged user.
	 * 
	 * @param event
	 *            - Represents an {@code ActionEvent} object.
	 */
	@FXML
	void saveChangesEventHandler(ActionEvent event) {
		String oldPass = txtfld_password_old.getText();
		String newPass = txtfld_password.getText();
		String newPassRepeated = txtfld_password_repeated.getText();

		try {

			Support.emptyCheckField(oldPass, "Old Password");
			Support.emptyCheckField(newPass, "Old Password");
			Support.emptyCheckField(newPassRepeated, "Old Password");

			if (oldPass.isEmpty() || oldPass == null)
				throw new Exception("The 'Current password' field is empty.");

			if (newPass.isEmpty() || newPass == null)
				throw new Exception("The 'New password' field is empty.");

			if (newPassRepeated.isEmpty() || newPassRepeated == null)
				throw new Exception("The 'Retype password' field is empty.");

			if (!myAccountController.getCurrentLoggedAccount().getPassword().equals(oldPass))
				throw new Exception("The specified current password don't match.");

			// Check password
			Support.passwordCheckField(newPass, newPassRepeated);

		} catch (Exception e) {
			Support.getDialog(Alert.AlertType.ERROR, "Error", e.getMessage()).show();
			return;
		}

		// Update
		myAccountController.getCurrentLoggedAccount().setPassword(newPass);
		myAccountController.updateAccount();

		Support.getDialog(Alert.AlertType.CONFIRMATION, "Message", "Password update").show();
		this.myStage.close();
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
	}

}
