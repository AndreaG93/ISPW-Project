package boundaries.home_page.list_adapter;

import controllers.AccountController;
import entities.announcement.Announcement;
import javafx.scene.control.ListCell;

public class AnnouncementListRowAdapter extends ListCell<Announcement> {
	
	private AccountController myAccountManagerInstance;
	
	public AnnouncementListRowAdapter(AccountController arg0)
	{
		this.myAccountManagerInstance = arg0;
	}
	
	@Override
	public void updateItem(Announcement param, boolean empty) {
		super.updateItem(param, empty);
		if (param != null) {
			AnnouncementListRowController data = new AnnouncementListRowController(param, myAccountManagerInstance);
			setGraphic(data.getBox());
		}
	}
}
