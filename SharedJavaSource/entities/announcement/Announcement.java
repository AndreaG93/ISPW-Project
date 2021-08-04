package entities.announcement;

import java.time.LocalDate;
import java.util.Calendar;
import entities.announcement.status.AnnouncementStatus;
import entities.announcement.type.AnnouncementType;
import entities.product.Product;

public class Announcement implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private final LocalDate publicationDate;
	private final int ID;
	private final int ownerID;

	private Product product;
	private AnnouncementType type;
	private AnnouncementStatus status;
	
	/**
	 * Constructs a newly allocated {@link Announcement} object.
	 * 
	 * @param arg0 - Represents an ID.
	 * @param arg1 - Represents an ID of account owner.
	 */
	public Announcement(int arg0, int arg1, LocalDate arg2) {
		this.ID = arg0;
		this.ownerID = arg1;
		this.publicationDate = arg2;
	}

	/*********************************************************************************************************
	 * Getter methods
	 */

	public int getID() {
		return ID;
	}

	public int getOwnerID() {
		return ownerID;
	}
	
	/**
	 * Return the publication date of announcement.
	 * 
	 * @return a {@link Calendar} object.
	 */
	public LocalDate getPublicationDate() {
		return publicationDate;
	}

	/**
	 * Return the type of announcement.
	 * 
	 * @return a {@link AnnouncementType} object.
	 */
	public AnnouncementType getType() {
		return type;
	}

	/**
	 * Return the announcement's status.
	 * 
	 * @return a {@link AnnouncementStatus} object.
	 */
	public AnnouncementStatus getStatus() {
		return status;
	}

	

	/*********************************************************************************************************
	 * Setter methods
	 */

	/**
	 * Set announcement's type.
	 * 
	 * @param type
	 *            - An {@link AnnouncementType} object.
	 */
	public void setType(AnnouncementType type) {
		this.type = type;
	}

	/**
	 * Set announcement's status.
	 * 
	 * @param description
	 *            - Represents a {@link AnnouncementStatus} object.
	 */
	public void setStatus(AnnouncementStatus status) {
		this.status = status;
	}

	
	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

}
