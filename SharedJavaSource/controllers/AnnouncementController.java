package controllers;

import java.util.Vector;

import controllers.database.announcement.AnnouncementDatabase;
import controllers.support.AbstractController;
import entities.announcement.Announcement;

public class AnnouncementController extends AbstractController {

	private AnnouncementDatabase myDB = AnnouncementDatabase.getInstance();
	
	/**
	 * Constructs a newly allocated {@code AnnouncementController} object.
	 */
	public AnnouncementController() {
	}

	/**
	 * This method updates an existing {@code Announcement} object.
	 * 
	 * @param arg0
	 *            - Represents an {@code Announcement} object.
	 */
	public void updateAnnouncement(Announcement arg0) {
		AnnouncementDatabase.getInstance().update(arg0);
		notifyToObservers();
	}

	/**
	 * This method removes an existing {@code Announcement} object.
	 * 
	 * @param arg0
	 *            - Represents an {@code Announcement} object.
	 */
	public void removeAnnouncement(Announcement arg0) {
		AnnouncementDatabase.getInstance().remove(arg0);
		notifyToObservers();
	}
	
	
	public Vector<Announcement> getAnnouncementObjectByAccountID(int AccountID) {
		return myDB.getAllByAccountID(AccountID);
	}
	
	
	public Vector<Announcement> getAllRegisteredAnnouncementObject() {
		return myDB.getAll();
	} 
	
	
	
	
	
	
}
