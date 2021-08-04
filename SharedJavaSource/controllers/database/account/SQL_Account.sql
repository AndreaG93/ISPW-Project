CREATE TABLE Account
(
    ID 					INTEGER,
    Email 				VARCHAR(64) NOT NULL,
    Password 			VARCHAR(64) NOT NULL,
    Type			 	VARCHAR(64) NOT NULL,
    RegistrationDate 	DATE        NOT NULL,
    PRIMARY KEY (ID),
    UNIQUE (Email)
)
