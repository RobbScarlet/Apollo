package com.robblibrary.apollo.javadev;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @Desc
 * @Author Remilia
 * @Create 2016-07-09
 */
public class PathTest {

    public static void main(String[] args) throws IOException {
        // Path listing = FileSystems.getDefault().getPath("/usr/bin/zip");
        Path listing = Paths.get("/usr/bin/zip");
        System.out.println("File Name [" + listing.getFileName() + "]");
        System.out.println("File System [" + listing.getFileSystem() + "]");
        System.out.println("Number of Name elements int the path [" + listing.getNameCount() + "]");
        System.out.println("Parent Path [" + listing.getParent() + "]");
        System.out.println("Root of Path [" + listing.getRoot() + "]");
        System.out.println("Subpath from Root, 2 elements deep [" + listing.subpath(0, 2) + "]");
    }
}
