CREATE TABLE Announcement
(
	ID 						INTEGER, 
    OwnerAccountID			INTEGER NOT NULL
    						REFERENCES Account(ID)
    						ON UPDATE CASCADE
        					ON DELETE CASCADE,
    Price					FLOAT,
	Type 					VARCHAR(64),
	Status 					VARCHAR(64),
	RegistrationDate 		DATE NOT NULL,

    PRIMARY KEY (ID)
)