package entities.announcement.type;

/**
 * Represents an announcement of donation type.
 * 
 * @author Andrea Graziani
 * @see AnnouncementType
 */
public class Donation implements AnnouncementType {

	private static final long serialVersionUID = 1L;

	@Override
	public double getCost() {
		return 0;
	}

	@Override
	public void setCost(double arg0) {
	}
}
