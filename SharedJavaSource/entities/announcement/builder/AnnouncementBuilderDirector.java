package entities.announcement.builder;

import java.time.LocalDate;

import entities.announcement.Announcement;
import entities.announcement.status.AnnouncementStatus;
import entities.announcement.status.Approved;
import entities.announcement.status.Rejected;
import entities.announcement.status.Testing;
import entities.announcement.type.Donation;
import entities.product.Product;

public class AnnouncementBuilderDirector {

	public static Announcement createsAnnouncementObject(int ID, int OwnerID, float price, String status,
			Product product, LocalDate regDate, String type) {
		AnnouncementBuilder myBuilder;
		AnnouncementStatus myAnnouncementStatus;

		// Type
		if (type.equals(Donation.class.getName()))
			myBuilder = new DonationAnnouncementBuilder();
		else
			myBuilder = new SaleAnnouncementBuilder();

		// Status
		if (status.equals(Approved.class.getName()))
			myAnnouncementStatus = new Approved();
		if (status.equals(Testing.class.getName()))
			myAnnouncementStatus = new Testing();
		else
			myAnnouncementStatus = new Rejected();

		myBuilder.create(ID, OwnerID, regDate);
		myBuilder.setType();
		myBuilder.setCost(price);
		myBuilder.setProduct(product);
		myBuilder.setStatus(myAnnouncementStatus);

		return myBuilder.getAnnouncement();
	}
}
