CREATE TABLE oko1.okozukai
(
    SHORI_NO                    INT NOT NULL,
    USER_ID                     VARCHAR(20) NOT NULL,
    YEAR                        CHAR(4) NOT NULL,
    MONTH                       CHAR(2) NOT NULL,
    DAY                         CHAR(2) NOT NULL,
    HINMOKU_CODE                CHAR(2) NOT NULL,
    IN_MONEY                    INT NOT NULL,
    OUT_MONEY                   INT NOT NULL,
    MEMO                        VARCHAR(20),
    INST_DATE                   DATETIME NOT NULL,
    INST_ID                     VARCHAR(30) NOT NULL,
    UPDATE_DATE                 DATETIME NOT NULL,
    UPDATE_ID                   VARCHAR(30) NOT NULL,
    CONSTRAINT PRIMARY KEY (SHORI_NO, USER_ID)
);
