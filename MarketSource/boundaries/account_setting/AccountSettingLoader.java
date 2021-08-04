package boundaries.account_setting;

import java.io.IOException;

import controllers.AccountController;
import controllers.AddressController;
import controllers.AnnouncementController;
import controllers.PersonalDataController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AccountSettingLoader {
	// This is the controller class that works with the FXML "GUI_Login.fxml".
	private AccountSettingController controller;

	// The JavaFX FXMLLoader class is used to loading FXML files at runtime.
	final private FXMLLoader FXML_Loader = new FXMLLoader(getClass().getResource("GUI_AccountSetting.fxml"));

	// The JavaFX Stage class is the top-level container for any JavaFX program
	// that has a graphical UI.
	private Stage myStage;

	// The JavaFX Scene class is the container for all content in a scene graph
	private Scene myScene;

	public void start(Stage arg0, AccountController arg1, AnnouncementController arg2, PersonalDataController arg3,
			AddressController arg4) throws IOException {
		this.myStage = arg0;

		try {
			Parent root = FXML_Loader.load();
			controller = FXML_Loader.getController();
			controller.initialization(myStage, arg1, arg2, arg3, arg4);

			myScene = new Scene(root);
			myStage.setTitle("Reserved Area");
			myStage.setScene(myScene);
			myStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
