package entities.announcement.builder;

import entities.announcement.type.Donation;

public class DonationAnnouncementBuilder extends AnnouncementBuilder {

	@Override
	public void setType() {
		this.myAnnouncement.setType(new Donation());
	}

	@Override
	public void setCost(double arg0) {
	}
}
