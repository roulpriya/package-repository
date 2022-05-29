package com.example.mavenrepository;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class FileInfoTest {

    @Test
    void parse() {
        String file = "com/example/priya/1.0-SNAPSHOT/priya-1.0-SNAPSHOT.jar";
        FileInfo fileInfo = FileInfo.parse(file);
        FileInfo expected = new FileInfo("com.example", "priya", "1.0", "priya-1.0-SNAPSHOT.jar");
        assertEquals(expected, fileInfo);
    }

    @Test
    void parseWithEmptyVersion() {
        String file = "com/example/priya/maven-metadata.xml";
        FileInfo fileInfo = FileInfo.parse(file);
        FileInfo expected = new FileInfo("com.example", "priya", null, "maven-metadata.xml");
        assertEquals(expected, fileInfo);
    }

    @Test
    void parseWithInvalidPath(){
        String file = "com/priyambada/priya/1.0-SNAPSHOT-2/priya-1.0-SNAPSHOT-2.jar";
        assertThrows(IllegalArgumentException.class, () -> FileInfo.parse(file));
    }

    @Test
    void parseWithoutVersionQualifier() {
        String file = "com/example/priya/1.0/priya-1.0.jar";
        FileInfo fileInfo = FileInfo.parse(file);
        FileInfo expected = new FileInfo("com.example", "priya", "1.0", "priya-1.0.jar");
        assertEquals(expected, fileInfo);

    }

}
