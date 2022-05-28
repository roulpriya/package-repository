CREATE TABLE "group"
(
    id VARCHAR(255) NOT NULL PRIMARY KEY ,
    "name" VARCHAR(255) NOT NULL
);

CREATE TABLE artifact
(
    id VARCHAR(255) NOT NULL PRIMARY KEY ,
    group_id VARCHAR(255) NOT NULL ,
    "name" VARCHAR(255) NOT NULL ,
    latest_version VARCHAR(255) NOT NULL ,
    FOREIGN KEY (group_id) REFERENCES "group" (id)
);

CREATE TABLE artifact_version
(
    id VARCHAR(255) NOT NULL PRIMARY KEY ,
    artifact_id VARCHAR(255) NOT NULL ,
    "version" VARCHAR(255) NOT NULL ,
    FOREIGN KEY (artifact_id) REFERENCES artifact (id)
);

