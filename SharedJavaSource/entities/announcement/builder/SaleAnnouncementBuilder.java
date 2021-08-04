package entities.announcement.builder;

import entities.announcement.type.Sale;

public class SaleAnnouncementBuilder extends AnnouncementBuilder {

	@Override
	public void setType() {
		this.myAnnouncement.setType(new Sale());
	}

	@Override
	public void setCost(double arg0) {
		this.myAnnouncement.getType().setCost(arg0);
	}
}
