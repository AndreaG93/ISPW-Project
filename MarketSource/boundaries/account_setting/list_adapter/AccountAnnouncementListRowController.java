package boundaries.account_setting.list_adapter;

import java.io.IOException;
import java.util.Optional;

import boundaries.announcement_setting.AnnouncementSettingLoader;
import controllers.AnnouncementController;
import controllers.support.Support;
import entities.announcement.Announcement;
import entities.announcement.status.Approved;
import entities.announcement.status.Testing;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class AccountAnnouncementListRowController {

	private Parent root;
	private Announcement myAnnouncement;
	private AnnouncementController myAnnouncementController;

	@FXML
    private Label lbl_status;

    @FXML
    private Hyperlink lbl_title;

    @FXML
    private Label lbl_Info;

    @FXML
    void announcementViewEventHandler(ActionEvent event) {
    	AnnouncementSettingLoader x = new AnnouncementSettingLoader();
		try {
			x.start(new Stage(), this.myAnnouncement, this.myAnnouncementController);
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
    	
    }

    @FXML
    void deleteEventHandler(ActionEvent event) {
    	
    	Alert dialog = Support.getDialog(Alert.AlertType.CONFIRMATION, "Message", "Are your sure?");
		Optional<ButtonType> result = dialog.showAndWait();
		if (result.get() == ButtonType.OK) {
			myAnnouncementController.removeAnnouncement(this.myAnnouncement);
		} else {
			return;
		}
    	

    }

	public AccountAnnouncementListRowController(Announcement arg0, AnnouncementController arg1) {
		
		
		
		this.myAnnouncement = arg0;
		this.myAnnouncementController = arg1;
		
		FXMLLoader FXML_Loader = new FXMLLoader(getClass().getResource("GUI_AccountAnnouncementListRow.fxml"));
		FXML_Loader.setController(this);
		try {
			root = FXML_Loader.load();
		} catch (IOException e) {
			throw new RuntimeException(e); 
		}
		
		/**
		 * Setting boundary
		 */
		
		lbl_title.setText(this.myAnnouncement.getProduct().getProductName());
		lbl_Info.setText("Published: " + this.myAnnouncement.getPublicationDate().toString());
		lbl_status.setText(this.myAnnouncement.getStatus().toString());
		
		if(this.myAnnouncement.getStatus().getClass().getName().equals(Approved.class.getName()))
			lbl_status.setTextFill(Color.GREEN);
		if(this.myAnnouncement.getStatus().getClass().getName().equals(Testing.class.getName()))
			lbl_status.setTextFill(Color.ORANGE);
		else
			lbl_status.setTextFill(Color.RED);	
	}

	public Parent getBox() {
		return root;
	}

}
