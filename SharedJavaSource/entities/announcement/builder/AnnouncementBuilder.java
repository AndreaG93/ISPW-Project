package entities.announcement.builder;

import java.time.LocalDate;

import entities.account.Account;
import entities.announcement.Announcement;
import entities.announcement.status.AnnouncementStatus;
import entities.announcement.type.AnnouncementType;
import entities.product.Product;

/**
 * The AnnouncementBuilder {@code class} is used to build a new
 * {@code Announcement} object.
 * 
 * @author Andrea Graziani
 * @version 1.0
 * @see Announcement
 */
public abstract class AnnouncementBuilder {
	protected Announcement myAnnouncement;

	/**
	 * This abstract method is used to set Announcement type.
	 * 
	 * @see AnnouncementType
	 */
	public abstract void setType();

	/**
	 * This abstract method is used to set announcement's cost.
	 * 
	 * @param arg0
	 *            - Represents a double that represents announcement's cost.
	 */
	public abstract void setCost(double arg0);

	/**
	 * Create a newly allocated {@code Announcement} object.
	 * 
	 * @param arg0
	 *            - Represents an {@code Account} object that represents
	 *            announcement's owner.
	 * @see Account
	 * @see Announcement
	 */
	public void create(int arg0, int arg1, LocalDate arg2) {
		this.myAnnouncement = new Announcement(arg0, arg1, arg2);
	}

	
	public void setProduct(Product arg0) {
		this.myAnnouncement.setProduct(arg0);
	}

	/**
	 * This method is used to set announcement's status.
	 * 
	 * @param arg0
	 *            - Represents a {@code AnnouncementStatus} object.
	 * @see AnnouncementStatus
	 */
	public void setStatus(AnnouncementStatus arg0) {
		this.myAnnouncement.setStatus(arg0);
	}

	/**
	 * Get builded {@code Announcement} object.
	 * 
	 * @return An {@code Announcement} object.
	 * @see Announcement
	 */
	public Announcement getAnnouncement() {
		return this.myAnnouncement;
	}
}
