CREATE TABLE oko1.hanyou
(
    SORT_NO                     INT NOT NULL,
    DISP_NAME                   VARCHAR(20),
    VALUE                       CHAR(2),
    HANYOU_KBN                  CHAR(2) NOT NULL,
    INST_DATE                   DATETIME NOT NULL,
    INST_ID                     VARCHAR(30) NOT NULL,
    UPDATE_DATE                 DATETIME NOT NULL,
    UPDATE_ID                   VARCHAR(30) NOT NULL
);
