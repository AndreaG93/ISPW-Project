package entities.announcement.type;

/**
 * Represents an announcement of sale type.
 * 
 * @author Andrea Graziani
 * @see AnnouncementType
 */
public class Sale implements AnnouncementType {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private double cost;

	@Override
	public double getCost() {
		return this.cost;
	}

	@Override
	public void setCost(double arg0) {
		this.cost = arg0;
	}
}
