DROP TABLE IF EXISTS ASSETS_OBJECT_FIELD;
DROP TABLE IF EXISTS ASSETS_OBJECT;
CREATE TABLE ASSETS_OBJECT (
    TENANT_ID BIGINT NOT NULL,
    SOURCE_ID BIGINT NOT NULL,
    OBJECT_NAME VARCHAR(32) NOT NULL,
    FULL_OBJECT_NAME VARCHAR(128) NOT NULL,
    CREATOR VARCHAR(32) NULL,
    MODIFIER VARCHAR(32) NULL,
    CREATED_STAMP DATETIME DEFAULT CURRENT_TIMESTAMP,
    LAST_UPDATED_STAMP DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);
CREATE TABLE ASSETS_OBJECT_FIELD (
    TENANT_ID BIGINT NOT NULL ,
    FIELD_NAME VARCHAR(32) NOT NULL ,
    SHORT_NAME VARCHAR(32) NOT NULL ,
    FULL_OBJECT_NAME VARCHAR(128) NOT NULL ,
    FULL_FIELD_NAME VARCHAR(128) NOT NULL ,
    SOURCE_ID BIGINT NOT NULL ,
    CREATOR VARCHAR(32) NULL ,
    MODIFIER VARCHAR(32) NULL ,
    CREATED_STAMP DATETIME DEFAULT CURRENT_TIMESTAMP ,
    LAST_UPDATED_STAMP DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);
