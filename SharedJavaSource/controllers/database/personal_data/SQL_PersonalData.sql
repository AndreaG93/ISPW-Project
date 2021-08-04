CREATE TABLE PersonalData
(
    ID 			INTEGER
    			REFERENCES Account(ID)
    			ON UPDATE CASCADE
        		ON DELETE CASCADE,
        		
    Name 		VARCHAR(64) NOT NULL,
    Surname 	VARCHAR(64) NOT NULL,
    Gender 		VARCHAR(64),
    Title 		VARCHAR(64),
    Birthday 	DATE,
    
    PRIMARY KEY (ID)
)