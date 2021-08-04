package boundaries.login;

import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.util.Optional;

import controllers.AccountController;
import controllers.support.Support;

public class LoginLoader {

	// This is the controller class that works with the FXML "GUI_Login.fxml".
	private LoginController controller;

	// The JavaFX FXMLLoader class is used to loading FXML files at runtime.
	final private FXMLLoader FXML_Loader = new FXMLLoader(getClass().getResource("GUI_Login.fxml"));

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
			myStage.setTitle("Login");
			myStage.setScene(myScene);
			myStage.setResizable(false);
			myStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent event) {
                	Alert myDialog = Support.getDialog(Alert.AlertType.CONFIRMATION, "Message", "Do you want exit?");
            		myDialog.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);

            		Optional<ButtonType> result = myDialog.showAndWait();
            		if (result.get() == ButtonType.YES)
            			myStage.close();
            		else
            			event.consume();
                }
            });
			myStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
