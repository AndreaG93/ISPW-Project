CREATE TABLE Book
(
    ID 						INTEGER 
    						REFERENCES Announcement(ID)
    						ON UPDATE CASCADE
        					ON DELETE CASCADE,
    						
	ISBN	 				VARCHAR(64),
	Title 					VARCHAR(64),
	SubTitle 				VARCHAR(64),
	Author 					VARCHAR(64),
	Editor 					VARCHAR(64),
	NumberOfPages 			VARCHAR(64),
	Type 					VARCHAR(64),

    PRIMARY KEY (ID)
)