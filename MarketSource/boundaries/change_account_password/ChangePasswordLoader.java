package boundaries.change_account_password;

import java.io.IOException;

import controllers.AccountController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ChangePasswordLoader {
	// This is the controller class that works with the FXML "GUI_Login.fxml".
	private ChangePasswordController controller;

	// The JavaFX FXMLLoader class is used to loading FXML files at runtime.
	final private FXMLLoader FXML_Loader = new FXMLLoader(getClass().getResource("GUI_ChangePassword.fxml"));

	// The JavaFX Stage class is the top-level container for any JavaFX program
	// that has a graphical UI.
	private Stage myStage;

	// The JavaFX Scene class is the container for all content in a scene graph
	private Scene myScene;

	public void start(Stage arg0, AccountController arg1) throws IOException {
		this.myStage = arg0;

		try {
			Parent root = FXML_Loader.load();
			controller = FXML_Loader.getController();
			controller.initialization(myStage, arg1);

			myScene = new Scene(root);
			myStage.setTitle("Change your password.");
			myStage.setScene(myScene);
			myStage.setResizable(false);
			myStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
