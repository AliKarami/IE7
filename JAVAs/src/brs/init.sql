CREATE TABLE IF NOT EXISTS Customer (
  cstmr_id INT NOT NULL PRIMARY KEY,
  name VARCHAR(50) NULL,
  family VARCHAR(70) NULL,
  fund INT NOT NULL);


CREATE TABLE IF NOT EXISTS Symbol (
  symb_name varchar(10) NOT NULL PRIMARY KEY);

CREATE TABLE IF NOT EXISTS Log (
  log_id INTEGER NOT NULL PRIMARY KEY IDENTITY ,
  desc VARCHAR(200) NOT NULL);

CREATE TABLE IF NOT EXISTS DepReqs (
  cstmr_id INT NOT NULL,
  amount INT NOT NULL,
  DepReq_id INT NOT NULL  PRIMARY KEY IDENTITY,
  CONSTRAINT fk_DepReqs_1
    FOREIGN KEY (cstmr_id)
    REFERENCES Customer (cstmr_id)
    ON DELETE CASCADE
    ON UPDATE CASCADE);

CREATE TABLE IF NOT EXISTS Property (
  prop_id INT NOT NULL PRIMARY KEY IDENTITY,
  symb_name VARCHAR(10) NOT NULL,
  amount INT NOT NULL,
  CONSTRAINT fk_Property_1
    FOREIGN KEY (symb_name)
    REFERENCES Symbol (symb_name)
    ON DELETE NO ACTION
    ON UPDATE CASCADE);

CREATE TABLE IF NOT EXISTS Properties (
  prop_id INT NOT NULL,
  cstmr_id INT NOT NULL,
  CONSTRAINT fk_Properties_1
    FOREIGN KEY (cstmr_id)
    REFERENCES Customer (cstmr_id)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT fk_Properties_2
    FOREIGN KEY (prop_id)
    REFERENCES Property (prop_id)
    ON DELETE CASCADE
    ON UPDATE CASCADE);

CREATE TABLE IF NOT EXISTS Requests (
  req_id INT NOT NULL PRIMARY KEY IDENTITY,
  cstmr_id INT NOT NULL,
  symb_name VARCHAR(10) NOT NULL,
  type CHAR(3) NOT NULL,
  forSale BOOLEAN NOT NULL,
  quantity INT NOT NULL,
  price INT NOT NULL,
  state INT NOT NULL,
  date_created DATETIME NOT NULL,
  date_fixed DATETIME NULL,
  CONSTRAINT fk_Requests_1
    FOREIGN KEY (cstmr_id)
    REFERENCES Customer (cstmr_id)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT fk_Requests_2
    FOREIGN KEY (symb_name)
    REFERENCES Symbol (symb_name)
    ON DELETE NO ACTION
    ON UPDATE CASCADE);

CREATE TABLE IF NOT EXISTS Buyers (
  buyer_id INT NOT NULL,
  symb_name VARCHAR(10) NOT NULL,
  req_id INT NOT NULL,
  CONSTRAINT fk_Buyers_1
    FOREIGN KEY (symb_name)
    REFERENCES Symbol (symb_name)
    ON DELETE NO ACTION
    ON UPDATE CASCADE,
  CONSTRAINT fk_Buyers_2
    FOREIGN KEY (buyer_id)
    REFERENCES Customer (cstmr_id)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT fk_Buyers_3
    FOREIGN KEY (req_id)
    REFERENCES Requests (req_id)
    ON DELETE CASCADE
    ON UPDATE CASCADE);

CREATE TABLE IF NOT EXISTS Sellers (
  seller_id INT NOT NULL,
  symb_name VARCHAR(10) NOT NULL,
  req_id INT NOT NULL,
  CONSTRAINT fk_Sellers_1
    FOREIGN KEY (symb_name)
    REFERENCES Symbol (symb_name)
    ON DELETE NO ACTION
    ON UPDATE CASCADE,
  CONSTRAINT fk_Sellers_2
    FOREIGN KEY (seller_id)
    REFERENCES Customer (cstmr_id)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT fk_Sellers_3
    FOREIGN KEY (req_id)
    REFERENCES Requests (req_id)
    ON DELETE CASCADE
    ON UPDATE CASCADE);
