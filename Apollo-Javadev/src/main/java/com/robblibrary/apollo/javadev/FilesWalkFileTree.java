package com.robblibrary.apollo.javadev;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

/**
 * @Desc
 * @Author Remilia
 * @Create 2016-07-09
 */
public class FilesWalkFileTree {
    public static void main(String[] args) throws IOException {
        Path dir = Paths.get("/Users/Remilia/Repository/RobbScarlet/Apollo");
        Files.walkFileTree(dir, new FindJavaVisitor());
    }

    public static class FindJavaVisitor extends SimpleFileVisitor<Path>{
        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
            if (file.toString().endsWith(".xml")){
                System.out.println(file);
            }

            return FileVisitResult.CONTINUE;
        }
    }
}
