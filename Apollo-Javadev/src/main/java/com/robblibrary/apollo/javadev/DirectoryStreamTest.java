package com.robblibrary.apollo.javadev;

import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @Desc
 * @Author Remilia
 * @Create 2016-07-09
 */
public class DirectoryStreamTest {

    public static void main(String[] args){
        Path dir = Paths.get("/Users/Remilia/Repository/RobbScarlet/Apollo/Apollo-Javadev");

        try(DirectoryStream<Path> stream = Files.newDirectoryStream(dir, "*.xml")){
            for(Path entry : stream){
                System.out.println(entry);
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}
