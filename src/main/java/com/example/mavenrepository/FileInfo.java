package com.example.mavenrepository;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public record FileInfo(String groupId, String artifactId, String version, String file) {

    private static final Pattern regex = Pattern.compile(
            "^(?<groupId>[\\w/]+)/(?<artifactId>\\w+)(?:/(?<version>[\\w.]+)(-[\\w.]+)?)?/(?<file>\\k<artifactId>-\\k<version>(-.+)?\\.\\w+|maven-metadata.xml(?:\\.\\w+)?)$");

    public static FileInfo parse(String path) {
        Matcher matcher = regex.matcher(path);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("Invalid path");
        }

        return new FileInfo(matcher.group("groupId").replace('/', '.'),
                matcher.group("artifactId"),
                matcher.group("version"),
                matcher.group("file"));
    }
}
