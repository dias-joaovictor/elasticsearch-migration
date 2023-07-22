CREATE TABLE Address
(
    id        INT AUTO_INCREMENT PRIMARY KEY,
    firstName VARCHAR(255) NOT NULL,
    lastName  VARCHAR(255) NOT NULL,
    street    VARCHAR(255) NOT NULL,
    number    VARCHAR(10)  NOT NULL,
    country   VARCHAR(2)   NOT NULL
);

CREATE TABLE Orders
(
    orderId          VARCHAR(255) PRIMARY KEY,
    placedDate  TIMESTAMP      NOT NULL,
    totalAmount DECIMAL(10, 2) NOT NULL,
    shipToId    INT,
    billToId    INT,
    FOREIGN KEY (shipToId) REFERENCES Address (id),
    FOREIGN KEY (billToId) REFERENCES Address (id)
);

CREATE TABLE OrderLine
(
    id         INT AUTO_INCREMENT PRIMARY KEY,
    lineNumber INT            NOT NULL,
    ean        VARCHAR(13)    NOT NULL,
    quantity   INT            NOT NULL,
    price      DECIMAL(10, 2) NOT NULL,
    orderId    VARCHAR(255),
    FOREIGN KEY (orderId) REFERENCES Orders (orderId)
);

CREATE TABLE LineStatus
(
    id          INT AUTO_INCREMENT PRIMARY KEY,
    date        TIMESTAMP    NOT NULL,
    status      VARCHAR(255) NOT NULL,
    orderLineId INT,
    FOREIGN KEY (orderLineId) REFERENCES OrderLine (id)
);
