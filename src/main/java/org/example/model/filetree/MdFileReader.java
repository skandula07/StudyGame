package org.example.model.filetree;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class MdFileReader {

    private StringBuilder content;

    private ArrayList<File> allFiles;
    private ArrayList<String> listOfContent;

    public MdFileReader(ArrayList files) {
        this.allFiles = files;
        content = new StringBuilder();
        listOfContent = new ArrayList<>();
    }

    private void oneFile(File file) {
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                listOfContent.add(scanner.nextLine());
            }

        } catch (FileNotFoundException e) {
        }
    }

    private void formatHeaders(String s) {
        if (!s.isEmpty()) {
            if (s.substring(0, 1).contains("#")) {
                if(listOfContent.indexOf(s) > 0) {
                    String dummy = "\n\n" + s;
                    content.append(dummy);
                } else {
                    content.append(s);
                }
            }
        }
    }

    private void formatEmphasis1(String s) {
        if (s.contains("[[") && s.contains("]]")) {
            int bracketEnd = s.indexOf("]]");
            int bracketStart = s.indexOf("[[");
            content.append("\n- " + s.substring(bracketStart + 2, bracketEnd));
        }
    }

    private void formatEmphasis2(String s1, String s2) {
        if (s1.contains("[[") && !s1.contains("]]")) {
            StringBuilder helperString = new StringBuilder();
            int bracketStart = s1.indexOf("[[");
            helperString.append("\n- " + s1.substring(bracketStart + 2));
            if (s2.indexOf("]]") < s2.indexOf("[[") || !s2.contains("[[")) {
                helperString.append(s2.substring(1, s2.indexOf("]]")));
                content.append(helperString);
            }
        }
    }

    private void reformat() {

        for (int i = 0; i < listOfContent.size() - 1; i++) {
            String s = listOfContent.get(i);
            String s1 = listOfContent.get(i + 1);

           formatHeaders(s);

            if (s.contains("[[")) {
                StringBuilder helperString = new StringBuilder();
                formatEmphasis1(s);
                s = s.substring(s.indexOf("]]") + 2);
                formatEmphasis2(s, s1);
            }
        }
        formatEmphasis1(listOfContent.get(listOfContent.size() - 1));
    }


    public StringBuilder read() {
        for(File f : allFiles) {
            oneFile(f);
        }
        reformat();
        return content;
    }
}
