CREATE TABLE "group" (
                         id     VARCHAR(255) NOT NULL PRIMARY KEY,
                         "name" VARCHAR(255) NOT NULL
                     );

CREATE TABLE artifact (
                          uid            BIGSERIAL    NOT NULL PRIMARY KEY,
                          id             VARCHAR(255) NOT NULL,
                          group_id       VARCHAR(255) NOT NULL,
                          "name"         VARCHAR(255) NOT NULL,
                          latest_version VARCHAR(255),
                          FOREIGN KEY (group_id) REFERENCES "group" (id)
                      );

CREATE TABLE artifact_version (
                                  uid          BIGSERIAL    NOT NULL PRIMARY KEY,
                                  artifact_uid BIGINT NOT NULL,
                                  "version"   VARCHAR(255) NOT NULL,
                                  FOREIGN KEY (artifact_uid) REFERENCES artifact (uid)
                              );

