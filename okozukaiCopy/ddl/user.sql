CREATE TABLE oko1.user
(
    USER_ID                     VARCHAR(20) NOT NULL,
    USER_PASSWORD               VARCHAR(20) NOT NULL,
    INST_DATE                   DATETIME NOT NULL,
    INST_ID                     VARCHAR(30) NOT NULL,
    UPDATE_DATE                 DATETIME NOT NULL,
    UPDATE_ID                   VARCHAR(30) NOT NULL,
    CONSTRAINT PRIMARY KEY (USER_ID)
);
