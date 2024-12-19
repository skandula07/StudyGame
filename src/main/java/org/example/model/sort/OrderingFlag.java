package org.example.model.sort;

public enum OrderingFlag {
    filename, creation, modified;



    /**
     * @param s - A string that represents the inputted flag
     * @return - returns an enumeration that will decide the way to sort the list
     */
    public static OrderingFlag getFlag(String s) {
        if (s.equals("filename")) {
            return OrderingFlag.filename;
        }
        if (s.equals("creation")) {
            return OrderingFlag.creation;
        }
        if (s.equals("modified")) {
            return OrderingFlag.modified;
        } else {
            throw new IllegalArgumentException(
                    "Must input either 'filename,' 'creation' or 'modified'");
        }
    }


}
