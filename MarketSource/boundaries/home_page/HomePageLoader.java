package boundaries.home_page;

import controllers.AccountController;
import controllers.AddressController;
import controllers.AnnouncementController;
import controllers.PersonalDataController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class HomePageLoader extends Application {

	// This is the controller class that works with the FXML "GUI_Login.fxml".
	private HomePageController controller;

	// The JavaFX FXMLLoader class is used to loading FXML files at runtime.
	final private FXMLLoader FXML_Loader = new FXMLLoader(getClass().getResource("GUI_HomePage.fxml"));

	// The JavaFX Stage class is the top-level container for any JavaFX program
	// that has a graphical UI.
	private Stage myStage;

	// The JavaFX Scene class is the container for all content in a scene graph
	private Scene myScene;

	public static void main(String[] arg0) {
		launch(arg0);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		this.myStage = primaryStage;

		try {
			Parent root = FXML_Loader.load();
			controller = FXML_Loader.getController();
			controller.initialization(myStage, new AccountController(), new AnnouncementController(),
					new PersonalDataController(), new AddressController());

			myScene = new Scene(root);
			myStage.setTitle("ISPW application");
			myStage.setScene(myScene);
			myStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
