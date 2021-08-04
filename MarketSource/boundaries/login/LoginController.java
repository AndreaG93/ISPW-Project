package boundaries.login;

import java.io.IOException;
import java.util.Optional;

import boundaries.register_account.RegisterAccountLoader;
import controllers.AccountController;
import controllers.support.Support;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.scene.control.Hyperlink;

public class LoginController {

	private AccountController myAccountController;
	private Stage stage;

	@FXML
	private TextField txtfld_username;

	@FXML
	private PasswordField txtfld_password;

	@FXML
	private Button btn_submit;

	@FXML
	private BorderPane root;

	@FXML
	private Hyperlink hprlnk_register;

	/**
	 * This method is used to open registration page.
	 * 
	 * @param event
	 *            - An {@link ActionEvent} object.
	 */
	@FXML
	void registrationEventHandler(ActionEvent event) {
		RegisterAccountLoader x = new RegisterAccountLoader();
		try {
			x.start(stage);
		} catch (IOException e) {
			e.printStackTrace();
		}
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
			stage.close();
		else
			return;
	}
	

	/**
	 * This method is used to do login.
	 * 
	 * @param event
	 *            - An {@link ActionEvent} object.
	 */
	@FXML
	void loginEventHandler(ActionEvent event) {
		try {
			Support.emptyCheckField(txtfld_username.getText(), "'Email Address'");
			Support.emptyCheckField(txtfld_password.getText(), "'Password'");
			myAccountController.login(txtfld_username.getText(), txtfld_password.getText());
		} catch (Exception e) {
			Support.getDialog(Alert.AlertType.ERROR, "Error", e.getMessage()).show();
		} finally {
			if (myAccountController.getCurrentLoggedAccount() != null) {
				this.stage.close();
				Support.getDialog(Alert.AlertType.INFORMATION, "Success", "You are now logged.").show();
			}
		}
	}

	/**
	 * This method is used to initialize the boundary.
	 * 
	 * @param arg0
	 *            - An {@link Stage} object.
	 * @param arg1
	 *            - An {@link AccountController} object.
	 */
	public void initialization(Stage arg0, AccountController arg1) {
		this.stage = arg0;
		this.myAccountController = arg1;	
	}
}
