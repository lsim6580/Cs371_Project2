DROP TABLE IF EXISTS Advertisements;
DROP TABLE IF EXISTS Moderators;
DROP TABLE IF EXISTS Users;
DROP TABLE IF EXISTS Categories;
DROP TABLE IF EXISTS Statuses;

Create TABLE IF NOT EXISTS Users (
  userID VARCHAR(20) NOT NULL,
  userFirstName VARCHAR(20) NOT NULL,
  userLastName VARCHAR(20) NOT NULL,
  CONSTRAINT pk_users PRIMARY KEY (userID)
);

CREATE TABLE IF NOT EXISTS Moderators (
  UserID VARCHAR(20) NOT NULL,
  CONSTRAINT pk_moderators PRIMARY KEY (UserID),
  CONSTRAINT fk_moderator_userID FOREIGN KEY (UserID) REFERENCES Users(UserID) ON DELETE RESTRICT
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
  advertisementDetails TEXT NOT NULL ,
  advertisementDateTime DATETIME NOT NULL,
  price FLOAT(10,2) NOT NULL,
  userID VARCHAR(20) NOT NULL,
  moderatorID VARCHAR(20),
  categoryID VARCHAR(3) NOT NULL,
  statusID VARCHAR(2) NOT NULL,
  CONSTRAINT pk_advertisement PRIMARY KEY (advertisementID),
  CONSTRAINT fk_user_advertisement FOREIGN KEY (userID) REFERENCES Users(userID) ON DELETE CASCADE,
  CONSTRAINT  fk_moderator_advertisement FOREIGN KEY (moderatorID) REFERENCES Moderators(UserID) ON DELETE SET NULL,
  CONSTRAINT  fk_category_advertisement FOREIGN KEY (categoryID) REFERENCES Categories(categoryID) ON DELETE RESTRICT,
  CONSTRAINT  fk_status_advertisement FOREIGN KEY (statusID) REFERENCES Statuses(statusID) ON DELETE RESTRICT
);