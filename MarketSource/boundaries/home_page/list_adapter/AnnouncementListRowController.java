package boundaries.home_page.list_adapter;

import java.io.IOException;

import boundaries.view_announcement.ViewAnnouncementLoader;
import controllers.AccountController;
import controllers.database.personal_data.PersonalDataDatabase;
import entities.announcement.Announcement;
import entities.personal_data.PersonalData;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class AnnouncementListRowController {

	private Parent root;
	private Announcement myAnnouncement;
	private AccountController myAccountManagerInstance;

	@FXML
	private Hyperlink lbl_title;

	@FXML
	private Label lbl_Info;

	@FXML
	private Label lbl_price;

	
	@FXML
	void announcementViewEventHandler(ActionEvent event) {
		ViewAnnouncementLoader x = new ViewAnnouncementLoader();
		try {
			x.start(new Stage(), myAnnouncement, myAccountManagerInstance);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public AnnouncementListRowController(Announcement arg0, AccountController arg1) {
		this.myAnnouncement = arg0;
		this.myAccountManagerInstance = arg1;

		FXMLLoader FXML_Loader = new FXMLLoader(getClass().getResource("GUI_AnnouncementListRow.fxml"));
		FXML_Loader.setController(this);
		try {
			root = FXML_Loader.load();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		
		PersonalData myObject = PersonalDataDatabase.getInstance().get(myAnnouncement.getOwnerID());
		String name = myObject.getName();
		String surname = myObject.getSurname();
		
		this.lbl_Info.setText("published: " + myAnnouncement.getPublicationDate().toString() + " by " + name + " " + surname);
		this.lbl_price.setText(String.valueOf(myAnnouncement.getType().getCost()));
		this.lbl_title.setText(myAnnouncement.getProduct().getProductName());
		
	}
	
	public Parent getBox() {
		return root;
	}

}
