CREATE TABLE Dress
(
    ID 						INTEGER 
    						REFERENCES Announcement(ID)
    						ON UPDATE CASCADE
        					ON DELETE CASCADE,
    						
	DressName	 			VARCHAR(64),
	Brand 					VARCHAR(64),
	Color 					VARCHAR(64),
	Gender 					VARCHAR(64),
	Size 					VARCHAR(64),

    PRIMARY KEY (ID)
)