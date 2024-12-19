package org.example.model.sort;

import java.io.File;
import java.util.Comparator;


/**
 * a Comparator class to help sort the file list by their name
 */
public class SortByName implements Comparator<File> {

    /**
     * @param name - a string of a file's path
     * @return the file's direct name
     */
    public String convertName(String name) {
        name = name.substring(name.lastIndexOf("/") + 1);
        return name;
    }

    /**
     * @param f1 the first file to be compared.
     * @param f2 the second file to be compared.
     * @return an integer that represents
     *     whether the first file is greater than, less than or equal to the second file
     */
    @Override
    public int compare(File f1, File f2) {
        String first = convertName(f1.getName());
        String second = convertName(f1.getName());
        return first.compareTo(second);
    }

}