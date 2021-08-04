package boundaries.account_setting.list_adapter;

import controllers.AnnouncementController;
import entities.announcement.Announcement;
import javafx.scene.control.ListCell;

public class AccountAnnouncementListRowAdapter extends ListCell<Announcement> {
	
	private AnnouncementController myObject;
	
	public AccountAnnouncementListRowAdapter(AnnouncementController arg0)
	{
		this.myObject = arg0;
	}
	
	@Override
	public void updateItem(Announcement param, boolean empty) {
		super.updateItem(param, empty);
		if (param != null) {
			AccountAnnouncementListRowController data = new AccountAnnouncementListRowController(param, myObject);
			setGraphic(data.getBox());
		}
	}
}
