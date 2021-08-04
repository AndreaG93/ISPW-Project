package entities.announcement.type;

import java.io.Serializable;

public interface AnnouncementType extends Serializable{
	/**
	 * Return the price of the announcement.
	 * 
	 * @return A double that represents announcement's price.
	 */
	public double getCost();

	/**
	 * Set the price of the announcement.
	 * 
	 * @param arg0
	 *            - Represents announcement's price.
	 */
	public void setCost(double arg0);
}
