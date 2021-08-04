CREATE TABLE Address
(
    ID 						INTEGER 
    						REFERENCES Account(ID)
    						ON UPDATE CASCADE
        					ON DELETE CASCADE,
    						
    FullName   				VARCHAR(64),
	AddressLine1 			VARCHAR(64),
	AddressLine2			VARCHAR(64),
	Town 					VARCHAR(64),
	Postcode 				VARCHAR(64),
	Province 				VARCHAR(64),
	PhoneNumber				VARCHAR(64),

    PRIMARY KEY (ID)
)