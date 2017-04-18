/* reset database */
DROP TABLE IF EXISTS Advertisements;
DROP TABLE IF EXISTS Moderators;
DROP TABLE IF EXISTS Users;
DROP TABLE IF EXISTS Categories;
DROP TABLE IF EXISTS Statuses;
/* end reset tables */

/* create tables */
Create TABLE IF NOT EXISTS Users (
  userID VARCHAR(20) NOT NULL,
  userFirstName VARCHAR(20) NOT NULL,
  userLastName VARCHAR(20) NOT NULL,
  CONSTRAINT pk_users PRIMARY KEY (userID)
);

CREATE TABLE IF NOT EXISTS Moderators (
  userID VARCHAR(20) NOT NULL,
  CONSTRAINT pk_moderators PRIMARY KEY (UserID),
  CONSTRAINT fk_moderator_userID FOREIGN KEY (UserID) REFERENCES Users(UserID)
  ON DELETE RESTRICT
);

CREATE TABLE IF NOT EXISTS Statuses (
  statusID VARCHAR(2) NOT NULL,
  statusName VARCHAR(20) NOT NULL,
  CONSTRAINT pk_statuses PRIMARY KEY (statusID)
);

CREATE TABLE IF NOT EXISTS Categories (
  categoryID VARCHAR(3) NOT NULL,
  categoryName VARCHAR(20),
  CONSTRAINT pk_categories PRIMARY KEY (categoryID)
);

CREATE TABLE IF NOT EXISTS Advertisements (
  advertisementID SMALLINT UNSIGNED NOT NULL AUTO_INCREMENT,
  advertisementTitle VARCHAR(20) NOT NULL,
  advertisementDetails TEXT NOT NULL,
  advertisementDateTime DATETIME NOT NULL,
  price FLOAT(10,2) NOT NULL,
  categoryID VARCHAR(3) NOT NULL,
  userID VARCHAR(20) NOT NULL,
  moderatorID VARCHAR(20),
  statusID VARCHAR(2) NOT NULL,
  CONSTRAINT pk_advertisement PRIMARY KEY (advertisementID),
  CONSTRAINT fk_category_advertisement FOREIGN KEY (categoryID) REFERENCES Categories(categoryID)
  ON DELETE RESTRICT,
  CONSTRAINT fk_user_advertisement FOREIGN KEY (userID) REFERENCES Users(userID)
  ON DELETE CASCADE,
  CONSTRAINT fk_moderator_advertisement FOREIGN KEY (moderatorID) REFERENCES Moderators(UserID)
  ON DELETE SET NULL,
  CONSTRAINT fk_status_advertisement FOREIGN KEY (statusID) REFERENCES Statuses(statusID)
  ON DELETE RESTRICT
);
/* end create tables */

/* populate tables */
/* user data */
INSERT INTO Users (userID, userFirstName, userLastName)
VALUES ('jsmith','John','Smith'),('ajackson','Ann','Jackson'),
  ('rkale','Rania','Kale'),('sali','Samir','Ali');

/* moderator data */
INSERT INTO Moderators (userID)
VALUES (SELECT userID FROM Users WHERE userID='jsmith'),
  (SELECT userID FROM Users WHERE userID='ajackson');

/* status data */
INSERT INTO Statuses (statusID, statusName)
VALUES ('PN','Pending'),('AC','Active'),('DI','Disapproved');

/* category data */
INSERT INTO Categories (categoryID, categoryName)
VALUES ('CAT','Cars and Trucks'),('HOU','Housing'),('ELC','Electronics'),
  ('CCA','Child Care');

/* advertisement data */
INSERT INTO Advertisements (advertisementTitle, advertisementDetails, advertisementDateTime,
  price, categoryID, userID, moderatorID, statusID)
VALUES ('2010 Sedan Subaru','2010 sedan car in great chape for sale','2017-02-10','6000',
  (SELECT categoryID FROM Categories WHERE categoryName = 'CAT'),
  (SELECT userID FROM Users WHERE userID = 'rkale'),
  (SELECT moderatorID FROM Moderators WHERE userID = 'jsmith'),
  (SELECT statusID FROM Statuses WHERE statusName = 'Active')),
('Nice Office Desk','Nice office desk for sale','2017-02-15','50.25',
  (SELECT categoryID FROM Categories WHERE categoryName = 'HOU'),
  (SELECT userID FROM Users WHERE userID = 'rkale'),
  (SELECT moderatorID FROM Moderators WHERE userID = 'jsmith'),
  (SELECT statusID FROM Statuses WHERE statusName = 'Active')),
('Smart LG TV for $200 ONLY','Smart LG TV 52 inches! Really cheap!','2017-03-15','200',
  (SELECT categoryID FROM Categories WHERE categoryName = 'ELC'),
  (SELECT userID FROM Users WHERE userID = 'sali'),
  (SELECT moderatorID FROM Moderators WHERE userID = 'jsmith'),
  (SELECT statusID FROM Statuses WHERE statusName = 'Active')),
('HD Tablet for $25 only','Amazon Fire Tablet HD','2017-03-20','25',
  (SELECT categoryID FROM Categories WHERE categoryName = 'ELC'),
  (SELECT userID FROM Users WHERE userID = 'rkale'),
  (SELECT moderatorID FROM Moderators WHERE userID = NULL),
  (SELECT statusID FROM Statuses WHERE statusName = 'Pending')),
('Laptop for $100','Amazing HP laptop for $100','2017-03-20','100',
  (SELECT categoryID FROM Categories WHERE categoryName = 'ELC'),
  (SELECT userID FROM Users WHERE userID = 'rkale'),
  (SELECT moderatorID FROM Moderators WHERE userID = NULL),
  (SELECT statusID FROM Statuses WHERE statusName = 'Pending'));
/* end data population */