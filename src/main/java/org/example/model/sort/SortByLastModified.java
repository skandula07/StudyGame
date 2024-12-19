package org.example.model.sort;

import java.io.File;
import java.util.Comparator;

/**
 * a Comparator class to help sort the file list by their last modified time
 */
public class SortByLastModified implements Comparator<File> {

    /**
     * @param o1 the first file to be compared.
     * @param o2 the second file to be compared.
     * @return an integer that represents
     *     whether the first file is greater than, less than or equal to the second file
     */
    @Override
    public int compare(File o1, File o2) {
        if (o1.lastModified() > o2.lastModified()) {
            return 1;
        }
        if (o1.lastModified() < o2.lastModified()) {
            return -1;
        } else {
            return 0;
        }
    }

}
