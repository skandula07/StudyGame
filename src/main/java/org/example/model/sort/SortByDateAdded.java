package org.example.model.sort;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.util.Comparator;


/**
 * a Comparator class to help sort the file list by their creation date
 */
public class SortByDateAdded implements Comparator<File> {
    /**
     * @param o1 the first file to be compared.
     * @param o2 the second file to be compared.
     * @return an integer that represents
     *     whether the first file is greater than, less than or equal to the second file
     */
    @Override
    public int compare(File o1, File o2) {
        FileTime first;
        FileTime second;
        try {
            first = Files.readAttributes(o1.toPath(), BasicFileAttributes.class).creationTime();
            second = Files.readAttributes(o2.toPath(), BasicFileAttributes.class).creationTime();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return first.compareTo(second);
    }

}

