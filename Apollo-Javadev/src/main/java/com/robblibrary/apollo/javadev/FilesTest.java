package com.robblibrary.apollo.javadev;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileAttribute;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.PosixFilePermissions;
import java.util.Set;

/**
 * @Desc
 * @Author Remilia
 * @Create 2016-07-09
 */
public class FilesTest {

    public static void main(String[] args) throws IOException {
        Path target = Paths.get("/tmp/Mystuff.txt");
        Files.delete(target);
        Set<PosixFilePermission> perms = PosixFilePermissions.fromString("rwxrwxrwx");
        FileAttribute<Set<PosixFilePermission>> attr = PosixFilePermissions.asFileAttribute(perms);
        Files.createFile(target, attr);
    }
}
